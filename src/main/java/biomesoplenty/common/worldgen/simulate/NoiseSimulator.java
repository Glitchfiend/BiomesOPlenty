/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.simulate;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import biomesoplenty.common.worldgen.BOPClimate;
import biomesoplenty.common.worldgen.BOPNoiseSampler;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.compress.utils.Lists;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import javax.swing.*;
import java.awt.*;
import java.nio.IntBuffer;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryStack.stackPush;

public class NoiseSimulator
{
    private static SimulatorThread simulatorThread;

    public static void run()
    {
        simulatorThread = new SimulatorThread();
        simulatorThread.start();
    }

    private static class SimulatorThread extends Thread
    {
        private static final int WINDOW_WIDTH = 1000;
        private static final int WINDOW_HEIGHT = 1000;
        private static final int CANVAS_WIDTH = 1024;
        private static final int CANVAS_HEIGHT = 1024;

        private static final int DEFAULT_FIRST_OCTAVE_VALUE = -7;

        private static final String VERTEX_SHADER_SRC =
                "#version 330\n" +
                        "layout(location = 0) in vec3 position;\n" +
                        "layout(location = 1) in vec2 vertexUV;\n" +
                        "out vec2 uv;\n" +
                        "void main()\n" +
                        "{\n" +
                        "   gl_Position.xyz = position;\n" +
                        "   gl_Position.w = 1.0;\n" +
                        "   uv = vertexUV;\n" +
                        "}";

        private static final String FRAGMENT_SHADER_SRC =
                "#version 330\n" +
                        "in vec2 uv;\n" +
                        "out vec3 color;\n" +
                        "uniform sampler2D sampler;\n" +
                        "void main()\n" +
                        "{\n" +
                        "   color = texture(sampler, uv).rgb;\n" +
                        "}";

        private int[] textureColours = new int[CANVAS_WIDTH * CANVAS_HEIGHT];

        private int firstOctave = DEFAULT_FIRST_OCTAVE_VALUE;
        private List<Double> amplitudes = Lists.newArrayList();
        private List<JSlider> amplitudeSliders = Lists.newArrayList();
        private List<JLabel> amplitudeLabels = Lists.newArrayList();

        private NoiseSimulationHelper sampler = new NoiseSimulationHelper(new Random().nextLong());
        private BOPClimate.ParameterList<ResourceKey<Biome>> params;

        private boolean showNoise = false;

        private int vertexBuffer = 0;
        private int textureId = 0;

        private void setupOpenGL()
        {
            GL.createCapabilities();
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

            int vertexArray = glGenVertexArrays();
            int programId = glCreateProgram();

            glBindVertexArray(vertexArray);
            int vertexShader = makeShader(GL_VERTEX_SHADER, VERTEX_SHADER_SRC);
            int fragmentShader = makeShader(GL_FRAGMENT_SHADER, FRAGMENT_SHADER_SRC);

            glAttachShader(programId, vertexShader);
            glAttachShader(programId, fragmentShader);
            glLinkProgram(programId);
            glUseProgram(programId);

            int[] linked = new int[1];
            glGetProgramiv(programId, GL_LINK_STATUS, linked);

            if (linked[0] == 0)
            {
                throw new RuntimeException("Failed to link shaders! " + glGetProgramInfoLog(programId));
            }

            glDetachShader(programId, vertexShader);
            glDetachShader(programId, fragmentShader);
            glDeleteShader(vertexShader);
            glDeleteShader(fragmentShader);

            float VERTICES[] =
                    {
                            // Vertex 1 data
                            -1.0F, -1.0F, 0.0F,
                            -1.0F, 1.0F, 0.0F,
                            1.0F, -1.0F, 0.0F,

                            // Vertex 2 data
                            -1.0F, 1.0F, 0.0F,
                            1.0F, 1.0F, 0.0F,
                            1.0F, -1.0F, 0.0F,

                            // UV 1 data
                            0.0F, 0.0F,
                            0.0F, 1.0F,
                            1.0F, 0.0F,

                            // UV 2 data
                            0.0F, 1.0F,
                            1.0F, 1.0F,
                            1.0F, 0.0F
                    };

            this.vertexBuffer = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
            glBufferData(GL_ARRAY_BUFFER, VERTICES, GL_STATIC_DRAW);

            // Specify the format and the offset of the vertex data (the pointer)
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 72);

            // Enable the vertex attribute specified by the index
            // This is stored in the currently bound VAO
            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1);

            // Create a texture for the screen to be rendered to
            this.textureId = glGenTextures();
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, textureId);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        }

        private void refreshNoise()
        {
            System.out.println("Noise refreshed. First octave " + this.firstOctave);
            System.out.println("Amplitudes " + this.amplitudes);
            this.sampler.rarenessNoise = NormalNoise.create(this.sampler.random, new NormalNoise.NoiseParameters(this.firstOctave, this.amplitudes));
            this.redraw();
        }

        private void redraw()
        {
            // Translate colours into texture colours
            for (int x = 0; x < CANVAS_WIDTH; x++)
            {
                for (int z = 0; z < CANVAS_HEIGHT; z++)
                {
                    BOPNoiseSampler.BOPFlatNoiseData data = this.sampler.noiseData(x, z, Blender.empty());

                    int ay = 0;
                    for (int y = -8; y < 40; y++)
                    {
                        double offset = sampler.offset(y * 8, data.terrainInfo());

                        if (offset < -4)
                        {
                            ay = y + 3;
                            break;
                        }
                    }

                    int color;

                    if (this.showNoise)
                    {
                        double rareness = data.rareness();
                        if (rareness < 0.35D) color = 0x00FF00;
                        else color = 0xFF0000;
                    }
                    else
                    {
                        ResourceKey<Biome> biome = params.findValue(sampler.sampleBOP(x, ay, z), Biomes.THE_VOID);

                        if (biome.location().getNamespace().equals("biomesoplenty"))
                        {
                            color = 0xFF0000;
                        }
                        else
                        {
                            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);

                            if (types.contains(BiomeDictionary.Type.OCEAN)) color = 0x000070;
                            else if (types.contains(BiomeDictionary.Type.RIVER)) color = 0x3030AF;
                            else if (types.contains(BiomeDictionary.Type.BEACH)) color = 0xFADE55;
                            else if (types.contains(BiomeDictionary.Type.MUSHROOM)) color = 0x805f8a;
                            else color = 0x00FF00;
                        }
                    }

                    color = ((color >> 16) & 0xFF | color & 0xFF00 | (color << 16) & 0xFF0000);
                    this.textureColours[x + ((CANVAS_WIDTH - 1) - z) * CANVAS_HEIGHT] = color;
                }
            }
        }

        private void refreshAmplitudes()
        {
            for (int i = 0; i < this.amplitudeSliders.size(); i++)
            {
                JSlider slider = this.amplitudeSliders.get(i);

                if (i >= this.amplitudes.size())
                    this.amplitudes.add(i, (double)slider.getValue() / 10000.0D);
                else
                    this.amplitudes.set(i, (double)slider.getValue() / 10000.0D);
            }
        }

        private void addAmplitude(Frame frame, JPanel panel)
        {
            var slider = new JSlider(JSlider.HORIZONTAL, 0, 20000, 0);
            int index = this.amplitudeSliders.size();
            var label = new JLabel("Amplitude " + index);

            slider.addChangeListener((e) ->
            {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                {
                    this.refreshAmplitudes();
                    this.refreshNoise();
                }
            });

            this.amplitudeSliders.add(index, slider);
            this.amplitudeLabels.add(label);
            panel.add(label);
            panel.add(slider);
            frame.pack();

            // Update for new amplitude
            this.refreshAmplitudes();
            this.refreshNoise();
        }

        private void removeAmplitude(Frame frame, JPanel panel)
        {
            if (this.amplitudeSliders.isEmpty() || this.amplitudeLabels.isEmpty())
                return;

            var slider = this.amplitudeSliders.get(this.amplitudeSliders.size() - 1);
            var label = this.amplitudeLabels.get(this.amplitudeLabels.size() - 1);

            this.amplitudeSliders.remove(slider);
            this.amplitudeLabels.remove(label);
            this.amplitudes.remove(amplitudes.size() - 1);
            panel.remove(slider);
            panel.remove(label);
            frame.pack();

            // Update for new amplitude
            this.refreshAmplitudes();
            this.refreshNoise();
        }

        private void setupGeneration()
        {
            ImmutableList.Builder<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> builder = ImmutableList.builder();
            (new BOPOverworldBiomeBuilder()).addBiomes(BuiltinRegistries.BIOME, builder::add);
            this.params = new BOPClimate.ParameterList<>(builder.build());
        }

        private void createTweaker()
        {
            System.setProperty("java.awt.headless", "false");

            JFrame frame = new JFrame("Noise tweaker");

            JPanel sliderPanel = new JPanel();
            sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
            sliderPanel.add(new JLabel("First octave"));
            var firstOctaveSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, -DEFAULT_FIRST_OCTAVE_VALUE);
            firstOctaveSlider.addChangeListener((e) ->
            {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                {
                    this.firstOctave = -source.getValue();
                    this.refreshNoise();
                }
            });
            sliderPanel.add(firstOctaveSlider);
            frame.add(sliderPanel, BorderLayout.NORTH);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

            var addAmplitudeButton = new JButton("Add amplitude");
            var removeAmplitudeButton = new JButton("Remove amplitude");
            var toggleNoise = new JButton("Toggle noise");
            var regenButton = new JButton("Regenerate");
            addAmplitudeButton.addActionListener((e) -> this.addAmplitude(frame, sliderPanel) );
            removeAmplitudeButton.addActionListener((e) -> this.removeAmplitude(frame, sliderPanel) );
            toggleNoise.addActionListener((e) ->
            {
                this.showNoise = !this.showNoise;
                this.redraw();
            });
            regenButton.addActionListener((e) ->
            {
                this.sampler = new NoiseSimulationHelper(new Random().nextLong());
                this.refreshNoise();
            });
            bottomPanel.add(addAmplitudeButton);
            bottomPanel.add(removeAmplitudeButton);
            bottomPanel.add(toggleNoise);
            bottomPanel.add(regenButton);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        }

        private void genTexture()
        {
            glBindTexture(GL_TEXTURE_2D, textureId);
            glTexImage2D(GL_TEXTURE_2D, 0,GL_RGBA, CANVAS_WIDTH, CANVAS_HEIGHT, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureColours);
        }

        public void run()
        {
            // Create the window and setup glfw
            long window = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Noise Visualizer", 0,0 );

            if (window == 0)
                throw new RuntimeException("Failed to create the GLFW window");

            try (MemoryStack stack = stackPush())
            {
                IntBuffer pWidth = stack.mallocInt(1); // int*
                IntBuffer pHeight = stack.mallocInt(1); // int*

                // Get the window size passed to glfwCreateWindow
                GLFW.glfwGetWindowSize(window, pWidth, pHeight);

                // Get the resolution of the primary monitor
                GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

                // Center the window
                GLFW.glfwSetWindowPos(window,
                        (vidmode.width() - pWidth.get(0)) / 2,
                        (vidmode.height() - pHeight.get(0)) / 2
                );
            }

            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1); // Enable V-Sync
            GLFW.glfwShowWindow(window);

            this.setupOpenGL();
            this.setupGeneration();
            this.createTweaker();
            this.refreshNoise();

            while (!GLFW.glfwWindowShouldClose(window))
            {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

                this.genTexture();
                glDrawArrays(GL_TRIANGLES, 0, 6);

                GLFW.glfwSwapBuffers(window); // swap the color buffers
                GLFW.glfwPollEvents();
            }
        }

        private static int makeShader(int type, String source)
        {
            int shader = glCreateShader(type);
            glShaderSource(shader, source);
            glCompileShader(shader);

            int[] compiled = new int[1];
            glGetShaderiv(shader, GL_COMPILE_STATUS, compiled);

            if (compiled[0] == 0)
            {
                throw new RuntimeException("Failed to compile shader! " + glGetShaderInfoLog(shader));
            }

            return shader;
        }
    }
}
