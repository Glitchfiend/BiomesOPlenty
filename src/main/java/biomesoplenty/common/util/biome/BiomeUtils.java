/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.biome;

import com.google.common.base.CaseFormat;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class BiomeUtils
{
    
    public static String getBiomeIdentifier(BiomeGenBase biome)
    {
        // Vanilla Biomes are typically named in upper camel case, sometimes with spaces
        // We follow the same convention with BOP Biomes
        // return a standardised identifier for use in json files, etc by converting to lowercase with underscores 
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, biome.biomeName.replace(" ", ""));
    }
    
    public static BlockPos spiralOutwardsLookingForBiome(World world, BiomeGenBase biomeToFind, double startX, double startZ, int maxDist)
    {
        // TODO: make default sample spacing dependent on size of biomes
        return spiralOutwardsLookingForBiome(world, biomeToFind, startX, startZ, maxDist, 64);
    }
    
    // sample points in an archimedean spiral starting from startX,startY each one sampleSpace apart
    // stop when the specified biome is found (and return the position it was found at) or when we reach maxDistance (and return null)
    public static BlockPos spiralOutwardsLookingForBiome(World world, BiomeGenBase biomeToFind, double startX, double startZ, int maxDist, int sampleSpace)
    {
        if (maxDist <= 0 || sampleSpace <= 0) {throw new IllegalArgumentException("maxDist and sampleSpace must be positive");}
        WorldChunkManager chunkManager = world.getWorldChunkManager();
        double a = sampleSpace / Math.sqrt(Math.PI);
        double b = 2 * Math.sqrt(Math.PI);
        double x = 0;
        double z = 0;
        double dist = 0;
        int n = 0;
        for (n = 0; dist < maxDist; ++n)
        {
            double rootN = Math.sqrt(n);
            dist = a * rootN;
            x = startX + (dist * Math.sin(b * rootN));
            z = startZ + (dist * Math.cos(b * rootN));
            // chunkManager.genBiomes is the first layer returned from initializeAllBiomeGenerators()
            // chunkManager.biomeIndexLayer is the second layer returned from initializeAllBiomeGenerators(), it's zoomed twice from genBiomes (>> 2) this one is actual size
            // chunkManager.getBiomeGenAt uses biomeIndexLayer to get the biome
            BiomeGenBase[] biomesAtSample = chunkManager.getBiomeGenAt(null, (int)x, (int)z, 1, 1, false);
            // System.out.println(n+" At ("+((int)x)+","+((int)z)+") biome is "+biomesAtSample[0].biomeName+" distance "+((int)dist));
            if (biomesAtSample[0] == biomeToFind)
            {
                System.out.println("Found "+biomeToFind.biomeName+" after "+n+" samples at ("+((int)x)+","+((int)z)+") distance "+((int)dist));
                return new BlockPos((int)x, 0, (int)z);
            }
        }
        System.out.println("Failed to find "+biomeToFind.biomeName+" gave up after "+n+" samples distance "+((int)dist));
        return null;
    }
    
}
