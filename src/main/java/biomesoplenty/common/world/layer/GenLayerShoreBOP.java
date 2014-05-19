package biomesoplenty.common.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import biomesoplenty.api.BOPBiomeManager;

public class GenLayerShoreBOP extends GenLayer
{
    public GenLayerShoreBOP(long seed, GenLayer parent)
    {
        super(seed);
        
        this.parent = parent;
    }

    @Override
    public int[] getInts(int x, int z, int width, int length)
    {
        int[] inputBiomeIds = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
        int[] outputBiomeIds = IntCache.getIntCache(width * length);

        for (int i1 = 0; i1 < length; ++i1)
        {
            for (int j1 = 0; j1 < width; ++j1)
            {
                this.initChunkSeed((long)(j1 + x), (long)(i1 + z));
                int landBiomeId = inputBiomeIds[j1 + 1 + (i1 + 1) * (width + 2)];
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(landBiomeId);
                int l1;
                int i2;
                int j2;
                int k2;

                if (landBiomeId == BiomeGenBase.mushroomIsland.biomeID)
                {
                    l1 = inputBiomeIds[j1 + 1 + (i1 + 1 - 1) * (width + 2)];
                    i2 = inputBiomeIds[j1 + 1 + 1 + (i1 + 1) * (width + 2)];
                    j2 = inputBiomeIds[j1 + 1 - 1 + (i1 + 1) * (width + 2)];
                    k2 = inputBiomeIds[j1 + 1 + (i1 + 1 + 1) * (width + 2)];

                    if (l1 != BiomeGenBase.ocean.biomeID && i2 != BiomeGenBase.ocean.biomeID && j2 != BiomeGenBase.ocean.biomeID && k2 != BiomeGenBase.ocean.biomeID)
                    {
                        outputBiomeIds[j1 + i1 * width] = landBiomeId;
                    }
                    else
                    {
                        outputBiomeIds[j1 + i1 * width] = BiomeGenBase.mushroomIslandShore.biomeID;
                    }
                }
                else if (biomegenbase != null && biomegenbase.getBiomeClass() == BiomeGenJungle.class)
                {
                    l1 = inputBiomeIds[j1 + 1 + (i1 + 1 - 1) * (width + 2)];
                    i2 = inputBiomeIds[j1 + 1 + 1 + (i1 + 1) * (width + 2)];
                    j2 = inputBiomeIds[j1 + 1 - 1 + (i1 + 1) * (width + 2)];
                    k2 = inputBiomeIds[j1 + 1 + (i1 + 1 + 1) * (width + 2)];

                    if (this.func_151631_c(l1) && this.func_151631_c(i2) && this.func_151631_c(j2) && this.func_151631_c(k2))
                    {
                        if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                        {
                            outputBiomeIds[j1 + i1 * width] = landBiomeId;
                        }
                        else
                        {
                            outputBiomeIds[j1 + i1 * width] = BiomeGenBase.beach.biomeID;
                        }
                    }
                    else
                    {
                        outputBiomeIds[j1 + i1 * width] = BiomeGenBase.jungleEdge.biomeID;
                    }
                }
                else if (landBiomeId != BiomeGenBase.extremeHills.biomeID && landBiomeId != BiomeGenBase.extremeHillsPlus.biomeID && landBiomeId != BiomeGenBase.extremeHillsEdge.biomeID)
                {
                    if (biomegenbase != null && biomegenbase.func_150559_j())
                    {
                        this.func_151632_a(inputBiomeIds, outputBiomeIds, j1, i1, width, landBiomeId, BiomeGenBase.coldBeach.biomeID);
                    }
                    else if (landBiomeId != BiomeGenBase.mesa.biomeID && landBiomeId != BiomeGenBase.mesaPlateau_F.biomeID)
                    {
                        if (!BOPBiomeManager.isBiomeOceanic(landBiomeId) && landBiomeId != BiomeGenBase.ocean.biomeID && landBiomeId != BiomeGenBase.deepOcean.biomeID && landBiomeId != BiomeGenBase.river.biomeID && landBiomeId != BiomeGenBase.swampland.biomeID)
                        {
                            l1 = inputBiomeIds[j1 + 1 + (i1 + 1 - 1) * (width + 2)];
                            i2 = inputBiomeIds[j1 + 1 + 1 + (i1 + 1) * (width + 2)];
                            j2 = inputBiomeIds[j1 + 1 - 1 + (i1 + 1) * (width + 2)];
                            k2 = inputBiomeIds[j1 + 1 + (i1 + 1 + 1) * (width + 2)];

                            if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                            {
                                outputBiomeIds[j1 + i1 * width] = landBiomeId;
                            }
                            else
                            {
                                outputBiomeIds[j1 + i1 * width] = BiomeGenBase.beach.biomeID;
                            }
                        }
                        else
                        {
                            outputBiomeIds[j1 + i1 * width] = landBiomeId;
                        }
                    }
                    else
                    {
                        l1 = inputBiomeIds[j1 + 1 + (i1 + 1 - 1) * (width + 2)];
                        i2 = inputBiomeIds[j1 + 1 + 1 + (i1 + 1) * (width + 2)];
                        j2 = inputBiomeIds[j1 + 1 - 1 + (i1 + 1) * (width + 2)];
                        k2 = inputBiomeIds[j1 + 1 + (i1 + 1 + 1) * (width + 2)];

                        if (!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2))
                        {
                            if (this.func_151633_d(l1) && this.func_151633_d(i2) && this.func_151633_d(j2) && this.func_151633_d(k2))
                            {
                                outputBiomeIds[j1 + i1 * width] = landBiomeId;
                            }
                            else
                            {
                                outputBiomeIds[j1 + i1 * width] = BiomeGenBase.desert.biomeID;
                            }
                        }
                        else
                        {
                            outputBiomeIds[j1 + i1 * width] = landBiomeId;
                        }
                    }
                }
                else
                {
                    this.func_151632_a(inputBiomeIds, outputBiomeIds, j1, i1, width, landBiomeId, BiomeGenBase.stoneBeach.biomeID);
                }
            }
        }

        return outputBiomeIds;
    }

    private void func_151632_a(int[] inputBiomeIds, int[] outputBiomeIds, int x, int z, int width, int landBiomeId, int beachBiomeId)
    {
        if (isBiomeOceanic(landBiomeId))
        {
            outputBiomeIds[x + z * width] = landBiomeId;
        }
        else
        {
            int j1 = inputBiomeIds[x + 1 + (z + 1 - 1) * (width + 2)];
            int k1 = inputBiomeIds[x + 1 + 1 + (z + 1) * (width + 2)];
            int l1 = inputBiomeIds[x + 1 - 1 + (z + 1) * (width + 2)];
            int i2 = inputBiomeIds[x + 1 + (z + 1 + 1) * (width + 2)];

            if (!isBiomeOceanic(j1) && !isBiomeOceanic(k1) && !isBiomeOceanic(l1) && !isBiomeOceanic(i2))
            {
                outputBiomeIds[x + z * width] = landBiomeId;
            }
            else
            {
                outputBiomeIds[x + z * width] = beachBiomeId;
            }
        }
    }
    
    protected static boolean isBiomeOceanic(int biomeId)
    {
    	return BOPBiomeManager.isBiomeOceanic(biomeId) || GenLayer.isBiomeOceanic(biomeId);
    }

    private boolean func_151631_c(int p_151631_1_)
    {
        return BiomeGenBase.getBiome(p_151631_1_) != null && BiomeGenBase.getBiome(p_151631_1_).getBiomeClass() == BiomeGenJungle.class ? true : p_151631_1_ == BiomeGenBase.jungleEdge.biomeID || p_151631_1_ == BiomeGenBase.jungle.biomeID || p_151631_1_ == BiomeGenBase.jungleHills.biomeID || p_151631_1_ == BiomeGenBase.forest.biomeID || p_151631_1_ == BiomeGenBase.taiga.biomeID || isBiomeOceanic(p_151631_1_);
    }

    private boolean func_151633_d(int p_151633_1_)
    {
        return BiomeGenBase.getBiome(p_151633_1_) != null && BiomeGenBase.getBiome(p_151633_1_) instanceof BiomeGenMesa;
    }
}