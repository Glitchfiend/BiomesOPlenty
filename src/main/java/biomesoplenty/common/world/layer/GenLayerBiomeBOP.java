/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import static net.minecraftforge.common.BiomeManager.BiomeType.COOL;
import static net.minecraftforge.common.BiomeManager.BiomeType.DESERT;
import static net.minecraftforge.common.BiomeManager.BiomeType.ICY;
import static net.minecraftforge.common.BiomeManager.BiomeType.WARM;

import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class GenLayerBiomeBOP extends GenLayerBiome
{
    private List<BiomeEntry>[] biomes;
    
    public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType, String chunkProviderSettings)
    {
        super(seed, parentLayer, worldType, chunkProviderSettings);
        
        biomes = ReflectionHelper.getPrivateValue(GenLayerBiome.class, this, "biomes");
        
        //TODO: Use vanilla biome weights
    }
    
    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] inputBiomeIds = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] outputBiomeIds = IntCache.getIntCache(areaWidth * areaHeight);

        for (int x = 0; x < areaHeight; ++x)
        {
            for (int z = 0; z < areaWidth; ++z)
            {
                this.initChunkSeed((long)(z + areaX), (long)(x + areaY));
                int k1 = inputBiomeIds[z + x * areaWidth];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;

                /*if (this.field_175973_g != null && this.field_175973_g.fixedBiome >= 0)
                {
                    aint1[j1 + i1 * areaWidth] = this.field_175973_g.fixedBiome;
                }
                else */if (isBiomeOceanic(k1))
                {
                    outputBiomeIds[z + x * areaWidth] = k1;
                }
                else if (k1 == BiomeGenBase.mushroomIsland.biomeID)
                {
                    outputBiomeIds[z + x * areaWidth] = k1;
                }
                else if (k1 == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.nextInt(3) == 0)
                        {
                            outputBiomeIds[z + x * areaWidth] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else
                        {
                            outputBiomeIds[z + x * areaWidth] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                    }
                    else
                    {
                        outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(DESERT).biome.biomeID;
                    }
                }
                else if (k1 == 2)
                {
                    if (l1 > 0)
                    {
                        outputBiomeIds[z + x * areaWidth] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(WARM).biome.biomeID;
                    }
                }
                else if (k1 == 3)
                {
                    if (l1 > 0)
                    {
                        outputBiomeIds[z + x * areaWidth] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(COOL).biome.biomeID;
                    }
                }
                else if (k1 == 4)
                {
                    outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(ICY).biome.biomeID;
                }
                else
                {
                    outputBiomeIds[z + x * areaWidth] = BiomeGenBase.mushroomIsland.biomeID;
                }
            }
        }

        return outputBiomeIds;
    }

    @Override
    protected BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type)
    {
        List<BiomeEntry> biomeList = biomes[type.ordinal()];
        int totalWeight = WeightedRandom.getTotalWeight(biomeList);
        int weight = nextInt(totalWeight);
        return (BiomeEntry)WeightedRandom.getRandomItem(biomeList, weight);
    }
}
