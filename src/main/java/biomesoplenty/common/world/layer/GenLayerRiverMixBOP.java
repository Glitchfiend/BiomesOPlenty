/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiverMixBOP extends BOPGenLayer
{
    private GenLayer biomesBranch;
    private GenLayer riversBranch;

    public GenLayerRiverMixBOP(long seed, GenLayer biomesBranch, GenLayer riversBranch)
    {
        super(seed);
        this.biomesBranch = biomesBranch;
        this.riversBranch = riversBranch;
    }

    @Override
    public void initWorldGenSeed(long worldSeed)
    {
        this.biomesBranch.initWorldGenSeed(worldSeed);
        this.riversBranch.initWorldGenSeed(worldSeed);
        super.initWorldGenSeed(worldSeed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.biomesBranch.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] riverValues = this.riversBranch.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            if (biomeIds[i] != BiomeGenBase.frozenOcean.biomeID && biomeIds[i] != BiomeGenBase.ocean.biomeID && biomeIds[i] != BiomeGenBase.deepOcean.biomeID && biomeSupportsRivers(biomeIds[i]))
            {
                if (riverValues[i] == BiomeGenBase.river.biomeID)
                {
                    if (biomeIds[i] == BiomeGenBase.icePlains.biomeID || (BOPBiomes.frozen_desert.isPresent() && biomeIds[i] == BOPBiomes.frozen_desert.get().biomeID) || (BOPBiomes.snowy_forest.isPresent() && biomeIds[i] == BOPBiomes.snowy_forest.get().biomeID) || (BOPBiomes.alps.isPresent() && biomeIds[i] == BOPBiomes.alps.get().biomeID))
                    {
                        out[i] = BiomeGenBase.frozenRiver.biomeID;
                    }
                    else if (biomeIds[i] != BiomeGenBase.mushroomIsland.biomeID && biomeIds[i] != BiomeGenBase.mushroomIslandShore.biomeID)
                    {
                        out[i] = riverValues[i] & 255;
                    }
                    else
                    {
                        out[i] = BiomeGenBase.mushroomIslandShore.biomeID;
                    }
                }
                else
                {
                    out[i] = biomeIds[i];
                }
            }
            else
            {
                out[i] = biomeIds[i];
            }
        }

        return out;
    }
    
    private boolean biomeSupportsRivers(int biomeId)
    {
        //Check if the biome id is valid
        if (biomeId >= 0 && biomeId < BiomeGenBase.getBiomeGenArray().length)
        {
            BiomeGenBase biome = BiomeGenBase.getBiome(biomeId);

            if (biome != null && biome instanceof BOPBiome)
            {
                BOPBiome bopBiome = (BOPBiome)biome;
                return bopBiome.canGenerateRivers;
            }
        }

        return true;
    }
}