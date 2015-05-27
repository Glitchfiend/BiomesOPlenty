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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// This is the same as vanilla minecraft's GenLayerHills code, just de-obfuscated a bit - might change it slightly later to support hilly versions of BOP biomes
public class GenLayerHillsBOP extends GenLayer
{
    private static final Logger logger = LogManager.getLogger();
    private GenLayer riversInitLayer;

    public GenLayerHillsBOP(long seed, GenLayer biomesLayer, GenLayer riversInitLayer)
    {
        super(seed);
        this.parent = biomesLayer;
        this.riversInitLayer = riversInitLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] riverVals = this.riversInitLayer.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; ++z)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(z + areaY));
                int biomeID = biomeIds[x + 1 + (z + 1) * (areaWidth + 2)];
                int l1 = riverVals[x + 1 + (z + 1) * (areaWidth + 2)];
                boolean flag = (l1 - 2) % 29 == 0;

                if (biomeID > 255)
                {
                    logger.debug("old! " + biomeID);
                }

                if (biomeID != 0 && l1 >= 2 && (l1 - 2) % 29 == 1 && biomeID < 128)
                {
                    if (BiomeGenBase.getBiome(biomeID + 128) != null)
                    {
                        out[x + z * areaWidth] = biomeID + 128;
                    }
                    else
                    {
                        out[x + z * areaWidth] = biomeID;
                    }
                }
                else if (this.nextInt(3) != 0 && !flag)
                {
                    out[x + z * areaWidth] = biomeID;
                }
                else
                {
                    int i2 = biomeID;
                    int j2;

                    if (biomeID == BiomeGenBase.desert.biomeID)
                    {
                        i2 = BiomeGenBase.desertHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.forest.biomeID)
                    {
                        i2 = BiomeGenBase.forestHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.birchForest.biomeID)
                    {
                        i2 = BiomeGenBase.birchForestHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.roofedForest.biomeID)
                    {
                        i2 = BiomeGenBase.plains.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.taiga.biomeID)
                    {
                        i2 = BiomeGenBase.taigaHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.megaTaiga.biomeID)
                    {
                        i2 = BiomeGenBase.megaTaigaHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.coldTaiga.biomeID)
                    {
                        i2 = BiomeGenBase.coldTaigaHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.plains.biomeID)
                    {
                        if (this.nextInt(3) == 0)
                        {
                            i2 = BiomeGenBase.forestHills.biomeID;
                        }
                        else
                        {
                            i2 = BiomeGenBase.forest.biomeID;
                        }
                    }
                    else if (biomeID == BiomeGenBase.icePlains.biomeID)
                    {
                        i2 = BiomeGenBase.iceMountains.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.jungle.biomeID)
                    {
                        i2 = BiomeGenBase.jungleHills.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.ocean.biomeID)
                    {
                        i2 = BiomeGenBase.deepOcean.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.extremeHills.biomeID)
                    {
                        i2 = BiomeGenBase.extremeHillsPlus.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.savanna.biomeID)
                    {
                        i2 = BiomeGenBase.savannaPlateau.biomeID;
                    }
                    else if (biomesEqualOrMesaPlateau(biomeID, BiomeGenBase.mesaPlateau_F.biomeID))
                    {
                        i2 = BiomeGenBase.mesa.biomeID;
                    }
                    else if (biomeID == BiomeGenBase.deepOcean.biomeID && this.nextInt(3) == 0)
                    {
                        j2 = this.nextInt(2);

                        if (j2 == 0)
                        {
                            i2 = BiomeGenBase.plains.biomeID;
                        }
                        else
                        {
                            i2 = BiomeGenBase.forest.biomeID;
                        }
                    }

                    if (flag && i2 != biomeID)
                    {
                        if (BiomeGenBase.getBiome(i2 + 128) != null)
                        {
                            i2 += 128;
                        }
                        else
                        {
                            i2 = biomeID;
                        }
                    }

                    if (i2 == biomeID)
                    {
                        out[x + z * areaWidth] = biomeID;
                    }
                    else
                    {
                        j2 = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                        int k2 = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                        int l2 = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                        int i3 = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                        int j3 = 0;

                        if (biomesEqualOrMesaPlateau(j2, biomeID))
                        {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(k2, biomeID))
                        {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(l2, biomeID))
                        {
                            ++j3;
                        }

                        if (biomesEqualOrMesaPlateau(i3, biomeID))
                        {
                            ++j3;
                        }

                        if (j3 >= 3)
                        {
                            out[x + z * areaWidth] = i2;
                        }
                        else
                        {
                            out[x + z * areaWidth] = biomeID;
                        }
                    }
                }
            }
        }

        return out;
    }
}