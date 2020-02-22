/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import biomesoplenty.common.world.BOPOverworldGenSettings;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeUtil
{
    public static int getBiomeSize(World world)
    {
        // TODO
        return BOPOverworldGenSettings.BiomeSize.MEDIUM.getValue();
    }

    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ)
    {
        int sampleSpacing = 4 << BiomeUtil.getBiomeSize(world);
        int maxDist = sampleSpacing * 100;
        return spiralOutwardsLookingForBiome(world, biomeToFind, startX, startZ, maxDist, sampleSpacing);
    }

    // sample points in an archimedean spiral starting from startX,startY each one sampleSpace apart
    // stop when the specified biome is found (and return the position it was found at) or when we reach maxDistance (and return null)
    public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ, int maxDist, int sampleSpace)
    {
        if (maxDist <= 0 || sampleSpace <= 0) {throw new IllegalArgumentException("maxDist and sampleSpace must be positive");}

        if (world.isClientSide)
            return null;

        double a = sampleSpace / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x = 0;
        double z = 0;
        double dist = 0;
        int n = 0;
        String biomeName = "biome";
        for (n = 0; dist < maxDist; ++n)
        {
            double rootN = Math.sqrt(n);
            dist = a * rootN;
            x = startX + (dist * Math.sin(b * rootN));
            z = startZ + (dist * Math.cos(b * rootN));
            // chunkManager.genBiomes is the first layer returned from initializeAllBiomeGenerators()
            // chunkManager.biomeIndexLayer is the second layer returned from initializeAllBiomeGenerators(), it's zoomed twice from genBiomes (>> 2) this one is actual size
            // chunkManager.getBiomeGenAt uses biomeIndexLayer to get the biome
            Biome biomeAtSample = world.getBiomeManager().getBiome(new BlockPos(x, 0, z));
            if (biomeAtSample == biomeToFind)
            {
                BiomesOPlenty.logger.info("Found "+ biomeName +" after "+n+" samples, spaced "+sampleSpace+" blocks apart at ("+((int)x)+","+((int)z)+") distance "+((int)dist));
                return new BlockPos((int)x, 0, (int)z);
            }
        }
        BiomesOPlenty.logger.info("Failed to find "+biomeName+" gave up after "+n+" samples, spaced "+sampleSpace+" blocks apart distance "+((int)dist));
        return null;
    }
}
