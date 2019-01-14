/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.client.util;

import biomesoplenty.common.world.BOPLayerUtil;
import com.google.common.collect.ImmutableList;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.AreaDimension;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.LayerUtil;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryStack.stackPush;

public class GenLayerVisualizer
{
    private static VisualizerThread visualizerThread;

    public static void run()
    {
        visualizerThread = new VisualizerThread();
        visualizerThread.start();
    }

    private static class VisualizerThread extends Thread
    {
        private static final int WINDOW_WIDTH = 1000;
        private static final int WINDOW_HEIGHT = 1000;
        private static final int CANVAS_WIDTH = 100;
        private static final int CANVAS_HEIGHT = 100;

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

        int[] biomeIds = new int[CANVAS_WIDTH * CANVAS_HEIGHT];
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

        private void populateBiomeIds()
        {
            OverworldBiomeProviderSettings settingsProvider = BiomeProviderType.VANILLA_LAYERED.createSettings();
            OverworldGenSettings settings = settingsProvider.getGeneratorSettings();

            int[] aint = new int[1];
            ImmutableList<IAreaFactory<LazyArea>> factoryList = BOPLayerUtil.buildOverworldProcedure(WorldType.DEFAULT, settings, (seedModifier) -> {
                ++aint[0];
                return new LazyAreaLayerContext(1, aint[0], 0, seedModifier);
            });

            IAreaFactory<LazyArea> biomeAreaFactory = factoryList.get(0);
            AreaDimension areaDimension = new AreaDimension(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
            LazyArea area = biomeAreaFactory.make(areaDimension);

            for (int i = 0; i < CANVAS_HEIGHT; ++i)
            {
                for (int j = 0; j < CANVAS_WIDTH; ++j)
                {
                    this.biomeIds[j + i * CANVAS_WIDTH] = area.getValue(j, i);
                }
            }
        }

        private int getColourForBiomeId(int biomeId, BlockPos pos)
        {
            Biome biome = Biome.getBiome(biomeId, null);

            if (biome == null)
                return 0xFFFF0000;

            return BiomeMapColours.getBiomeMapColour(biome);
            /*IBlockState topBlock = biome.getSurfaceBuilder().getConfig().getTop();

            if (topBlock.getBlock() == Blocks.GRASS)
                return biome.getGrassColor(pos);
            else if (topBlock.getBlock() == Blocks.WATER || isOcean(biome) || biome == Biomes.RIVER || biome == Biomes.SWAMP)
                return biome.getWaterColor();

            return topBlock.getMapColor(null, pos).colorValue;*/
        }

        private void genTexture()
        {
            int[] textureColours = new int[CANVAS_WIDTH * CANVAS_HEIGHT];

            // Translate colours into texture colours
            for (int x = 0; x < CANVAS_WIDTH; x++)
            {
                for (int y = 0; y < CANVAS_HEIGHT; y++)
                {
                    int color = getColourForBiomeId(this.biomeIds[x + y * CANVAS_WIDTH], new BlockPos(x, 0, y));

                    color = ((color >> 16) & 0xFF | color & 0xFF00 | (color << 16) & 0xFF0000);

                    textureColours[x + ((CANVAS_WIDTH - 1) - y) * CANVAS_HEIGHT] = color;
                }
            }

            glBindTexture(GL_TEXTURE_2D, textureId);
            glTexImage2D(GL_TEXTURE_2D, 0,GL_RGBA, CANVAS_WIDTH, CANVAS_HEIGHT, 0, GL_RGBA, GL_UNSIGNED_BYTE, textureColours);
        }

        public void run()
        {
            // Create the window and setup glfw
            long window = GLFW.glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Gen Layer Visualizer", 0,0 );

            if (window == 0)
                throw new RuntimeException("Failed to create the GLFW window");

            try ( MemoryStack stack = stackPush())
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
            this.populateBiomeIds();

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

        private static boolean isOcean(Biome biome)
        {
            return biome == Biomes.WARM_OCEAN || biome == Biomes.LUKEWARM_OCEAN || biome == Biomes.OCEAN || biome == Biomes.COLD_OCEAN || biome == Biomes.FROZEN_OCEAN || biome == Biomes.DEEP_WARM_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN || biome == Biomes.DEEP_OCEAN || biome == Biomes.DEEP_COLD_OCEAN || biome == Biomes.DEEP_FROZEN_OCEAN;
        }
    }
}
