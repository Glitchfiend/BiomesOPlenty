/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.client.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// Adapted from TTFTCUTS' work here
// https://github.com/TTFTCUTS/Pioneer/blob/master/src/main/java/ttftcuts/pioneer/map/MapColours.java
public class BiomeMapColours
{
    public static Map<Biome, Integer> biomeColours = new HashMap<Biome, Integer>();
    public static Random rand = new Random(50);

    static
    {
        // Hardcoded colours
    }

    public static int getBiomeMapColour(Biome biome)
    {
        if (biomeColours.containsKey(biome)) {
            return biomeColours.get(biome);
        }

        int colour = getBiomeMapColourRaw(biome);
        biomeColours.put(biome, colour);
        return colour;
    }

    public static int getBiomeMapColourRaw(Biome biome)
    {
        boolean treebased = false;
        int colour = getTopColour(biome);

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST))
        {
            colour = blend(biome.getFoliageColor(BlockPos.ORIGIN), 0xff0b7000, 0.35);
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
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(0,64,0);
        return getBiomeBlockColourForCoords(biome, pos);
    }

    public static int getBiomeBlockColourForCoords(Biome biome, BlockPos pos)
    {
        IBlockState topBlock = biome.getSurfaceBuilder().getConfig().getTop();
        int colour;

        if (topBlock == Blocks.GRASS.getDefaultState()) { // uuuugh
            colour = topBlock.getMapColor(null, pos).colorValue | 0xFF000000;
            int tint = biome.getGrassColor(pos) | 0xFF000000;
            colour = blend(colour,tint, 0.75);
        } else {
            colour = getBlockColourRaw(topBlock);
        }

        return colour;
    }

    public static int getBlockColourRaw(IBlockState block)
    {
        Minecraft mc = Minecraft.getInstance();
        BlockRendererDispatcher brd = mc.getBlockRendererDispatcher();
        BlockModelShapes shapes = brd.getBlockModelShapes();
        BlockColors colours = mc.getBlockColors();

        int colour = block.getMapColor(null, null).colorValue | 0xFF000000;
        int fallback = colour;

        if (block == Blocks.GRASS.getDefaultState()) {
        }
        else
        {
            try
            {
                IBakedModel topmodel = shapes.getModel(block);
                List<BakedQuad> topquads = topmodel.getQuads(block, EnumFacing.UP, rand);

                for (BakedQuad quad : topquads)
                {
                    colour = block.getMapColor(null, null).colorValue | 0xFF000000;

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

        return block.getMapColor(null, null).colorValue | 0xFF000000;
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
