/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.client.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// Adapted from TTFTCUTS' work here
// https://github.com/TTFTCUTS/Pioneer/blob/master/src/main/java/ttftcuts/pioneer/map/MapColours.java
public class BiomeMapColours
{
    public static final boolean RANDOM_COLOURS = false;
    public static Map<Integer, Integer> biomeColours = new HashMap<Integer, Integer>();
    public static Random rand = new Random(50);

    static
    {
        // Hardcoded colours

        // Temperature layer colours. 0 is cold, 8 is hot
        /*biomeColours.put(0, 0x91CDFF);
        biomeColours.put(1, 0x93DBFD);
        biomeColours.put(2, 0x9CE3E9);
        biomeColours.put(3, 0xA5EBD5);
        biomeColours.put(4, 0xAEF3C1);
        biomeColours.put(5, 0xCCF4A2);
        biomeColours.put(6, 0xE8E9A0);
        biomeColours.put(7, 0xF3D09D);
        biomeColours.put(8, 0xFDB69B);

        // Rainfall layer colours. 0 is wet, 11 is dry.
        biomeColours.put(0, 0x82FFE3);
        biomeColours.put(1, 0x99FFD9);
        biomeColours.put(2, 0xB1FFCE);
        biomeColours.put(3, 0xC8FFC4);
        biomeColours.put(4, 0xD7FDBE);
        biomeColours.put(5, 0xDDF6BB);
        biomeColours.put(6, 0xE3EEB8);
        biomeColours.put(7, 0xE8E7B5);
        biomeColours.put(8, 0xEEE0B2);
        biomeColours.put(9, 0xF4D9AF);
        biomeColours.put(10, 0xFAD1AC);
        biomeColours.put(11, 0xFFCCAA);*/

        // Climate colours. See the values in BOPClimates
        /*biomeColours.put(0, 0xF3F3F3);
        biomeColours.put(1, 0xCFE2F3);
        biomeColours.put(2, 0xA2C4C9);
        biomeColours.put(3, 0x45818E);
        biomeColours.put(4, 0xD9EAD3);
        biomeColours.put(5, 0xE6B8AF);
        biomeColours.put(6, 0xD2EA98);
        biomeColours.put(7, 0xA8EAB6);
        biomeColours.put(8, 0x6AA84F);
        biomeColours.put(9, 0x69EE79);
        biomeColours.put(10, 0xEDFFB7);
        biomeColours.put(11, 0xFCE39D);
        biomeColours.put(12, 0xFFFBE0);
        biomeColours.put(13, 0xA6947F);*/
    }

    public static int getBiomeMapColour(int biomeId)
    {
        if (RANDOM_COLOURS)
        {
            // Who can be bothered coming up with colours manually?
            try
            {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                return ByteBuffer.wrap(digest.digest(ByteBuffer.allocate(4).putInt(biomeId).array())).getInt() & 0xFFFFFF;
            }
            catch (Exception e)
            {
                return 0;
            }
        }

        if (biomeColours.containsKey(biomeId)) {
            return biomeColours.get(biomeId);
        }

        int colour = getBiomeMapColourRaw(Registry.BIOME.getByValue(biomeId));
        biomeColours.put(biomeId, colour);
        return colour;
    }

    public static int getBiomeMapColourRaw(Biome biome)
    {
        boolean treebased = false;
        int colour = getTopColour(biome);

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST))
        {
            colour = blend(biome.getFoliageColor(), 0xff0b7000, 0.35);
            treebased = true;
        }

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.LUSH) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE))
        {
            colour = blend(colour, 0xff0b7000, Math.min(0.25, 0.25f * 0.025));
            colour = brightness(colour, 1.0 - Math.min(0.1, 0.25f * 0.015));

            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE))
                treebased = true;
        }

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN))
        {
            colour = blend(colour, 0xff4582ff, 0.7); // sea blue
        }

        if (biome.getDepth() > 0.0)
        {
            double mod = Math.min(biome.getDepth() * 0.2 + 1.0, 1.35);
            colour = brightness(colour, mod);
        } else if (biome.getDepth() <= -1.2) {
            colour = brightness(colour, 0.9);
        }

        if (treebased)
        {
            colour = temptint(colour, biome.getTemperature(new BlockPos(0, 64, 0)));
        }

        if (biome.getDefaultTemperature() < 0.15F)
        {
            colour = blend(colour, 0xffffffff, 0.5); // icy pale cyan
            //colour = blend(colour, 0xffc9e4ff, 0.25);
            colour = brightness(colour, 1.2);
        }

        return colour | 0xFF000000;
    }

    public static int getTopColour(Biome biome)
    {
        BlockPos.Mutable pos = new BlockPos.Mutable(0,64,0);
        return getBiomeBlockColourForCoords(biome, pos);
    }

    public static int getBiomeBlockColourForCoords(Biome biome, BlockPos pos)
    {
        BlockState topBlock = biome.getSurfaceBuilder().getConfig().getTop();
        int colour;

        if (topBlock == Blocks.GRASS.getDefaultState())
        { // uuuugh
            colour = topBlock.getMaterialColor(null, pos).colorValue | 0xFF000000;
            int tint = biome.getGrassColor(pos.getX(), pos.getZ()) | 0xFF000000;
            colour = blend(colour, tint, 0.75);
        }
        else
        {
            colour = getBlockColourRaw(topBlock);
        }

        return colour;
    }

    public static int getBlockColourRaw(BlockState block)
    {
        Minecraft mc = Minecraft.getInstance();
        BlockRendererDispatcher brd = mc.getBlockRendererDispatcher();
        BlockModelShapes shapes = brd.getBlockModelShapes();
        BlockColors colours = mc.getBlockColors();

        int colour = block.getMaterialColor(null, null).colorValue | 0xFF000000;
        int fallback = colour;

        if (block == Blocks.GRASS.getDefaultState()) {
        }
        else
        {
            try
            {
                IBakedModel topmodel = shapes.getModel(block);
                List<BakedQuad> topquads = topmodel.getQuads(block, Direction.UP, rand);

                for (BakedQuad quad : topquads)
                {
                    colour = block.getMaterialColor(null, null).colorValue | 0xFF000000;

                    if (quad.hasTintIndex())
                    {
                        int tint = colours.getColor(block, null, null, quad.getTintIndex()) | 0xFF000000;
                        colour = blend(colour, tint, 0.75);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                colour = fallback;
            }
        }

        return block.getMaterialColor(null, null).colorValue | 0xFF000000;
    }

    public static int intAverage(int a, int b)
    {
        return (int)( ((((a) ^ (b)) & 0xfffefefeL) >> 1) + ((a) & (b)) );
    }

    public static int blend(int a, int b, double mix)
    {
        if (mix == 0) {
            return a;
        } else if (mix == 1) {
            return b;
        } else if (mix == 0.5) {
            return intAverage(a,b);
        }

        int ar = (a & 0x00FF0000) >> 16;
        int ag = (a & 0x0000FF00) >> 8;
        int ab = (a & 0x000000FF);

        int br = (b & 0x00FF0000) >> 16;
        int bg = (b & 0x0000FF00) >> 8;
        int bb = (b & 0x000000FF);

        int mr = (int)Math.min(255,Math.max(0,Math.floor(ar * (1.0-mix) + br * mix)));
        int mg = (int)Math.min(255,Math.max(0,Math.floor(ag * (1.0-mix) + bg * mix)));
        int mb = (int)Math.min(255,Math.max(0,Math.floor(ab * (1.0-mix) + bb * mix)));

        return (mr << 16) | (mg << 8) | (mb) | 0xFF000000;
    }

    public static int brightness(int col, double light)
    {
        int r = (col & 0x00FF0000) >> 16;
        int g = (col & 0x0000FF00) >> 8;
        int b = (col & 0x000000FF);

        r = (int)Math.min(255,Math.floor(r * light));
        g = (int)Math.min(255,Math.floor(g * light));
        b = (int)Math.min(255,Math.floor(b * light));

        return (r << 16) | (g << 8) | (b) | 0xFF000000;
    }

    public static int temptint(int col, double temp)
    {
        int r = (col & 0x00FF0000) >> 16;
        int g = (col & 0x0000FF00) >> 8;
        int b = (col & 0x000000FF);

        double limit = 0.25;
        double factor = Math.max(-limit, Math.min(limit, (temp - 0.4) * 0.75));

        r = (int)Math.min(255,Math.floor(r * (1+factor)));
        g = (int)Math.min(255,Math.floor(g * (1+factor * 0.5)));
        b = (int)Math.min(255,Math.floor(b * (1-factor * 2.5)));

        return (r << 16) | (g << 8) | (b) | 0xFF000000;
    }
}
