/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import java.util.List;

import biomesoplenty.common.init.ModBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSubBiomesBOP extends BOPGenLayer
{
    private GenLayer subBiomesInit;
    
    public GenLayerSubBiomesBOP(long seed, GenLayer biomesLayer, GenLayer subBiomesInit)
    {
        super(seed);
        this.parent = biomesLayer;
        this.subBiomesInit = subBiomesInit;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] initVals = this.subBiomesInit.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; ++z)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(z + areaY));
                int biomeID = biomeIds[x + 1 + (z + 1) * (areaWidth + 2)];
                
                // subBiomeInit comes from an instance of GenLayerRiverInit 
                // ...which produces random 'oceans' of zeros, and 'continents' of random values between 2 and 300001
                // when combined with % 29, the result is 'oceans' of zeros and 'continents' of random values between 0 and 28
                // I'm not sure whether or not these align with the oceans and continents in the biome layer...
                int initVal = (initVals[x + 1 + (z + 1) * (areaWidth + 2)] % 29);
                
                // if we're on one of the 'continents', then there's a one in 29 chance of trying a rare biome
                boolean tryRareBiome = (initVal == 1);
                boolean tryRareHillsBiome = (initVal == 2);
                

                if (biomeID != 0 && tryRareBiome)
                {
                    // convert to mutated biome
                    out[x + z * areaWidth] = this.getRareSubBiome(biomeID);
                }
                else if (this.nextInt(3) != 0 && !tryRareHillsBiome)
                {
                    // leave biome unchanged
                    out[x + z * areaWidth] = biomeID;
                }
                else
                {
                    int altBiomeID = this.getCommonSubBiome(biomeID);
                    if (altBiomeID == biomeID)
                    {
                        // leave biome unchanged
                        out[x + z * areaWidth] = biomeID;
                    }
                    else
                    {
                        if (tryRareHillsBiome)
                        {
                            altBiomeID = this.getRareSubBiome(altBiomeID);
                        }

                        int biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
                        int biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
                        int biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
                        int biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];
                        int surroundingSameCount = 0;

                        if (biomesEqualOrMesaPlateau(biomeNorth, biomeID)) {++surroundingSameCount;}
                        if (biomesEqualOrMesaPlateau(biomeEast, biomeID)) {++surroundingSameCount;}
                        if (biomesEqualOrMesaPlateau(biomeWest, biomeID)) {++surroundingSameCount;}
                        if (biomesEqualOrMesaPlateau(biomeSouth, biomeID)) {++surroundingSameCount;}
                        
                        if (surroundingSameCount >= 3)
                        {
                            // if at least 3 out of the 4 surrounding biomes are the same (or mesa plateau) then replace the biome
                            out[x + z * areaWidth] = altBiomeID;
                        }
                        else
                        {
                            // else leave the biome unchanged
                            out[x + z * areaWidth] = biomeID;
                        }
                    }
                }
            }
        }

        return out;
    }
    
    
    
    // Given a biomeId, return the biomeId of a rare alternative biome which you could sometimes find a patch of within the outer biome 
    // This is usually the 'mutated' version of a biome
    public int getRareSubBiome(int biomeId)
    {
        return BiomeGenBase.getIdForBiome(BiomeGenBase.getMutationForBiome(BiomeGenBase.getBiome(biomeId)));
    }    
    
    // Given a biomeId, return the biomeId of a reasonably common alternative biome which you might expect to find a patch of within the outer biome 
    // For many biomes, this is the 'hills' version
    public int getCommonSubBiome(int biomeId)
    {
        
        List<Integer> subBiomeIds = ModBiomes.subBiomesMap.get(biomeId);
        if (subBiomeIds == null) {return biomeId;}
        int n = subBiomeIds.size();
        return (n == 0 ? biomeId : (n == 1 ? subBiomeIds.get(0).intValue() : subBiomeIds.get(this.nextInt(n)).intValue()));
        
    }
    
    
    
    
}