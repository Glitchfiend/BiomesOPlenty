/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeEdgeBOP extends GenLayer
{

    public GenLayerBiomeEdgeBOP(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] parentVals = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(y + areaY));
                int biomeId = parentVals[x + 1 + (y + 1) * (areaWidth + 2)];

                if (!this.replaceBiomeEdgeIfNecessary(parentVals, out, x, y, areaWidth, biomeId, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID) && !this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mesa.biomeID) && !this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesa.biomeID) && !this.replaceBiomeEdge(parentVals, out, x, y, areaWidth, biomeId, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.taiga.biomeID))
                {
                    int northBiomeId;
                    int eastBiomeId;
                    int westBiomeId;
                    int southBiomeId;

                    if (biomeId == BiomeGenBase.desert.biomeID)
                    {
                        northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
                        eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
                        westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
                        southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

                        if (northBiomeId != BiomeGenBase.icePlains.biomeID && eastBiomeId != BiomeGenBase.icePlains.biomeID && westBiomeId != BiomeGenBase.icePlains.biomeID && southBiomeId != BiomeGenBase.icePlains.biomeID)
                        {
                            out[x + y * areaWidth] = biomeId;
                        }
                        else
                        {
                            out[x + y * areaWidth] = BiomeGenBase.extremeHillsPlus.biomeID;
                        }
                    }
                    else if (biomeId == BiomeGenBase.swampland.biomeID)
                    {
                        northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
                        eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
                        westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
                        southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

                        if (northBiomeId != BiomeGenBase.desert.biomeID && eastBiomeId != BiomeGenBase.desert.biomeID && westBiomeId != BiomeGenBase.desert.biomeID && southBiomeId != BiomeGenBase.desert.biomeID && northBiomeId != BiomeGenBase.coldTaiga.biomeID && eastBiomeId != BiomeGenBase.coldTaiga.biomeID && westBiomeId != BiomeGenBase.coldTaiga.biomeID && southBiomeId != BiomeGenBase.coldTaiga.biomeID && northBiomeId != BiomeGenBase.icePlains.biomeID && eastBiomeId != BiomeGenBase.icePlains.biomeID && westBiomeId != BiomeGenBase.icePlains.biomeID && southBiomeId != BiomeGenBase.icePlains.biomeID)
                        {
                            if (northBiomeId != BiomeGenBase.jungle.biomeID && southBiomeId != BiomeGenBase.jungle.biomeID && eastBiomeId != BiomeGenBase.jungle.biomeID && westBiomeId != BiomeGenBase.jungle.biomeID)
                            {
                                out[x + y * areaWidth] = biomeId;
                            }
                            else
                            {
                                out[x + y * areaWidth] = BiomeGenBase.jungleEdge.biomeID;
                            }
                        }
                        else
                        {
                            out[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
                        }
                    }
                    else
                    {
                        out[x + y * areaWidth] = biomeId;
                    }
                }
            }
        }

        return out;
    }

    private boolean replaceBiomeEdgeIfNecessary(int[] parentVals, int[] out, int x, int y, int areaWidth, int biomeId, int fromBiomeId, int toBiomeId)
    {
        if (!biomesEqualOrMesaPlateau(biomeId, fromBiomeId))
        {
            return false;
        }
        else
        {
            int northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
            int eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
            int westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
            int southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

            if (this.canBiomesBeNeighbors(northBiomeId, fromBiomeId) && this.canBiomesBeNeighbors(eastBiomeId, fromBiomeId) && this.canBiomesBeNeighbors(westBiomeId, fromBiomeId) && this.canBiomesBeNeighbors(southBiomeId, fromBiomeId))
            {
                out[x + y * areaWidth] = biomeId;
            }
            else
            {
                out[x + y * areaWidth] = toBiomeId;
            }

            return true;
        }
    }

    private boolean replaceBiomeEdge(int[] parentVals, int[] out, int x, int y, int areaWidth, int biomeId, int fromBiomeId, int toBiomeId)
    {
        if (biomeId != fromBiomeId)
        {
            return false;
        }
        else
        {
            int northBiomeId = parentVals[x + 1 + (y + 1 - 1) * (areaWidth + 2)];
            int eastBiomeId = parentVals[x + 1 + 1 + (y + 1) * (areaWidth + 2)];
            int westBiomeId = parentVals[x + 1 - 1 + (y + 1) * (areaWidth + 2)];
            int southBiomeId = parentVals[x + 1 + (y + 1 + 1) * (areaWidth + 2)];

            if (biomesEqualOrMesaPlateau(northBiomeId, fromBiomeId) && biomesEqualOrMesaPlateau(eastBiomeId, fromBiomeId) && biomesEqualOrMesaPlateau(westBiomeId, fromBiomeId) && biomesEqualOrMesaPlateau(southBiomeId, fromBiomeId))
            {
                out[x + y * areaWidth] = biomeId;
            }
            else
            {
                out[x + y * areaWidth] = toBiomeId;
            }

            return true;
        }
    }

    private boolean canBiomesBeNeighbors(int biomeA, int biomeB)
    {
        if (biomesEqualOrMesaPlateau(biomeA, biomeB))
        {
            return true;
        }
        else
        {
            BiomeGenBase biomegenbaseA = BiomeGenBase.getBiome(biomeA);
            BiomeGenBase biomegenbaseB = BiomeGenBase.getBiome(biomeB);

            if (biomegenbaseA != null && biomegenbaseB != null)
            {
                BiomeGenBase.TempCategory tempcategory = biomegenbaseA.getTempCategory();
                BiomeGenBase.TempCategory tempcategory1 = biomegenbaseB.getTempCategory();
                return tempcategory == tempcategory1 || tempcategory == BiomeGenBase.TempCategory.MEDIUM || tempcategory1 == BiomeGenBase.TempCategory.MEDIUM;
            }
            else
            {
                return false;
            }
        }
    }
}