/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.biome;

import java.util.Arrays;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class BiomeUtils
{
    public static BlockPos findBiome(World world, BiomeGenBase biome, BlockPos startPos)
    {
        int radius = 256;
        WorldChunkManager chunkManager = world.getWorldChunkManager();
        BlockPos pos1 = null;
        BlockPos pos2 = null;
        
        for (int x = -10; x <= 10; x++)
        {
            for (int z = -10; z <= 10; z++)
            {
                if (pos1 == null)
                {
                    BlockPos foundPos = chunkManager.findBiomePosition(startPos.getX() + (x * 512), startPos.getZ() + (z * 512), 256, Arrays.asList(biome), world.rand);

                    if (foundPos != null && world.getBiomeGenForCoords(foundPos) == biome)
                    {
                        pos1 = foundPos;
                    }
                }
                
                if (pos2 == null)
                {
                    BlockPos foundPos = chunkManager.findBiomePosition(startPos.getX() + (x * 512), startPos.getZ() + (-z * 512), 256, Arrays.asList(biome), world.rand);

                    if (foundPos != null && world.getBiomeGenForCoords(foundPos) == biome)
                    {
                        pos2 = foundPos;
                    }
                }
                
                if (pos1 != null && pos2 != null)
                {
                    break;
                }
            }
        }
        
        if (pos1 != null && pos2 != null)
        {
            if (startPos.distanceSq(pos1) < startPos.distanceSq(pos2)) return pos1;
            else return pos2;
        }
        else
        {
            if (pos1 != null) return pos1;
            else return pos2;
        }
    }
}
