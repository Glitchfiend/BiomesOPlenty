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

import java.util.ArrayList;
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
import biomesoplenty.common.biome.BOPBiomeManager;

import com.google.common.collect.ImmutableList;

public class GenLayerBiomeBOP extends GenLayerBiome
{
    private List<BiomeEntry>[] biomes;
    
    public GenLayerBiomeBOP(long seed, GenLayer parentLayer, WorldType worldType, String chunkProviderSettings)
    {
        super(seed, parentLayer, worldType, chunkProviderSettings);
        
        biomes = ReflectionHelper.getPrivateValue(GenLayerBiome.class, this, "biomes");
        
        //TODO: Use vanilla biome weights
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values())
        {
            ImmutableList<BiomeEntry> biomesToAdd = BOPBiomeManager.getBiomes(type);
            int idx = type.ordinal();

            if (biomes[idx] == null) biomes[idx] = new ArrayList<BiomeEntry>();
            if (biomesToAdd != null) biomes[idx].addAll(biomesToAdd);
        }
    }
    
    // Get array of biome IDs covering the requested area
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
                int parentVal = inputBiomeIds[z + x * areaWidth];
                
                // Value from parent layer consists of
                // 4 high bits which determine whether the biome will be a 'special' variant
                // 8 low bits which determine the base biome type
                int specialVal = (parentVal & 3840) >> 8;
                int baseVal = parentVal & -3841;

                // Note: vanilla GenLayerBiome has a section here which deals with fixed biomes on a custom world
                // This doesn't apply in BOP
                
                if (isBiomeOceanic(baseVal)) // special case for oceans
                {
                    outputBiomeIds[z + x * areaWidth] = baseVal;
                }
                else if (baseVal == BiomeGenBase.mushroomIsland.biomeID) // special case for mushroom island
                {
                    outputBiomeIds[z + x * areaWidth] = parentVal;
                }
                else if (baseVal == 1)  // baseVal = 1 means a DESERT region
                {
                    if (specialVal > 0)
                    {
                        // there is a special biome for DESERT : mesa plateau
                        if (this.nextInt(3) == 0)
                        {
                            outputBiomeIds[z + x * areaWidth] = BiomeGenBase.mesaPlateau.biomeID;
                        }
                        else
                        {
                            // otherwise get a (weighted) random biome from the DESERT list
                            outputBiomeIds[z + x * areaWidth] = BiomeGenBase.mesaPlateau_F.biomeID;
                        }
                    }
                    else
                    {
                        outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(DESERT).biome.biomeID;
                    }
                }
                else if (baseVal == 2) // baseVal = 2 means a WARM region
                {
                    if (specialVal > 0)
                    {
                        // there is a special biome for WARM : jungle
                        outputBiomeIds[z + x * areaWidth] = BiomeGenBase.jungle.biomeID;
                    }
                    else
                    {
                        // otherwise get a (weighted) random biome from the WARM list
                        outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(WARM).biome.biomeID;
                    }
                }
                else if (baseVal == 3) // baseVal = 3 means a COOL region
                {
                    if (specialVal > 0)
                    {
                        // there is a special biome for COOL : mega taiga
                        outputBiomeIds[z + x * areaWidth] = BiomeGenBase.megaTaiga.biomeID;
                    }
                    else
                    {
                        // otherwise get a (weighted) random biome from the COOL list
                        outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(COOL).biome.biomeID;
                    }
                }
                else if (baseVal == 4) // baseVal = 4 means a ICY region
                {
                    // I would have expected the ice spikes to be the special biome for ICY, apparently not - not sure where the ice spikes fit in...
                    // Regardless of specialVal, get a (weighted) random biome from the ICY list
                    outputBiomeIds[z + x * areaWidth] = getWeightedBiomeEntry(ICY).biome.biomeID;
                }
                else
                {
                    // default for any other value is mushroom island - I don't think we should ever get here
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
