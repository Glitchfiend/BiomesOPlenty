/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import java.util.Iterator;
import java.util.Map.Entry;

import biomesoplenty.common.init.ModBiomes;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLargeIsland extends BOPGenLayer 
{
    public GenLayerLargeIsland(long seed, GenLayer parent) 
    {
        super(seed);
        this.parent = parent;
    }
   
    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) 
    {
        int outerWidth = areaWidth + 2;
        
        int[] biomeIds = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; ++z)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                int biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                int biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                int biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                int biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                int centerVal = biomeIds[x + 1 + (z + 1) * outerWidth];

                this.initChunkSeed((long)(x + areaX), (long)(z + areaY));

                if (centerVal == 0 && biomeNorth == 0 && biomeEast == 0 && biomeWest == 0 && biomeSouth == 0 && this.nextInt(50) == 0)
                {
                    out[x + z * areaWidth] = getRandomIslandBiome();
                }
                else
                {
                    out[x + z * areaWidth] = centerVal;
                }
            }
        }

        return out;
    }
    
    public int getRandomIslandBiome()
    {
        int weight = this.nextInt(ModBiomes.totalIslandBiomesWeight);
        Iterator<Entry<Integer, Integer>> iterator = ModBiomes.islandBiomesMap.entrySet().iterator();
        
        Entry<Integer, Integer> item;
        do
        {
            item = iterator.next();
            weight -= item.getValue();
        }
        while (weight >= 0);
        return item.getKey();
    }
}
