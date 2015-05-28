/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSubBiomesBOP extends GenLayer
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
        
        // the mutated versions of vanilla biomes aren't actually saved to static variables in BiomeGenBase
        // instead, they just manually create mutated versions of many of their biomes via a hard coded list in BiomeGenBase
        // and by default assume a biome id which is the old one + 128
        // this severely limits the number of new biomes we can add (we'd have to keep the number below 128 to avoid clashes)
        // we hard code the list of vanilla biomes with mutated versions below, which enables other biomes to use the biome ids which are not taken
                
        if (
            biomeId == BiomeGenBase.plains.biomeID ||
            biomeId == BiomeGenBase.desert.biomeID ||
            biomeId == BiomeGenBase.forest.biomeID ||
            biomeId == BiomeGenBase.taiga.biomeID ||
            biomeId == BiomeGenBase.swampland.biomeID ||
            biomeId == BiomeGenBase.icePlains.biomeID ||
            biomeId == BiomeGenBase.jungle.biomeID ||
            biomeId == BiomeGenBase.jungleEdge.biomeID ||
            biomeId == BiomeGenBase.coldTaiga.biomeID ||
            biomeId == BiomeGenBase.savanna.biomeID ||
            biomeId == BiomeGenBase.savannaPlateau.biomeID ||
            biomeId == BiomeGenBase.mesa.biomeID ||
            biomeId == BiomeGenBase.mesaPlateau.biomeID ||
            biomeId == BiomeGenBase.mesaPlateau_F.biomeID ||
            biomeId == BiomeGenBase.birchForest.biomeID ||
            biomeId == BiomeGenBase.birchForestHills.biomeID ||
            biomeId == BiomeGenBase.roofedForest.biomeID ||
            biomeId == BiomeGenBase.megaTaiga.biomeID ||
            biomeId == BiomeGenBase.extremeHills.biomeID ||
            biomeId == BiomeGenBase.extremeHillsPlus.biomeID ||
            biomeId == BiomeGenBase.megaTaigaHills.biomeID
        )
        {
            if (BiomeGenBase.getBiome(biomeId + 128) != null)
            {
                return biomeId + 128;
            }
        }
        
        // TODO: add BOP rare sub biomes here
        
        return biomeId;
    }    
    
    // Given a biomeId, return the biomeId of a reasonably common alternative biome which you might expect to find a patch of within the outer biome 
    // For many biomes, this is the 'hills' version
    public int getCommonSubBiome(int biomeId)
    {
        if (biomeId == BiomeGenBase.desert.biomeID)
        {
            return BiomeGenBase.desertHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.forest.biomeID)
        {
            return BiomeGenBase.forestHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.birchForest.biomeID)
        {
            return BiomeGenBase.birchForestHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.roofedForest.biomeID)
        {
            return BiomeGenBase.plains.biomeID;
        }
        else if (biomeId == BiomeGenBase.taiga.biomeID)
        {
            return BiomeGenBase.taigaHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.megaTaiga.biomeID)
        {
            return BiomeGenBase.megaTaigaHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.coldTaiga.biomeID)
        {
            return BiomeGenBase.coldTaigaHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.plains.biomeID)
        {
            if (this.nextInt(3) == 0)
            {
                return BiomeGenBase.forestHills.biomeID;
            }
            else
            {
                return BiomeGenBase.forest.biomeID;
            }
        }
        else if (biomeId == BiomeGenBase.icePlains.biomeID)
        {
            return BiomeGenBase.iceMountains.biomeID;
        }
        else if (biomeId == BiomeGenBase.jungle.biomeID)
        {
            return BiomeGenBase.jungleHills.biomeID;
        }
        else if (biomeId == BiomeGenBase.ocean.biomeID)
        {
            return BiomeGenBase.deepOcean.biomeID;
        }
        else if (biomeId == BiomeGenBase.extremeHills.biomeID)
        {
            return BiomeGenBase.extremeHillsPlus.biomeID;
        }
        else if (biomeId == BiomeGenBase.savanna.biomeID)
        {
            return BiomeGenBase.savannaPlateau.biomeID;
        }
        else if (biomesEqualOrMesaPlateau(biomeId, BiomeGenBase.mesaPlateau_F.biomeID))
        {
            return BiomeGenBase.mesa.biomeID;
        }
        else if (biomeId == BiomeGenBase.deepOcean.biomeID && this.nextInt(3) == 0)
        {
            // occasional islands within the oceans
            if (this.nextInt(2) == 0)
            {
                return BiomeGenBase.plains.biomeID;
            }
            else
            {
                return BiomeGenBase.forest.biomeID;
            }
        }
        
        // BOP sub biomes from here on
        
        if (biomeId == BiomeGenBase.frozenOcean.biomeID && BOPBiomes.arctic.isPresent())
        {
            biomeId = BOPBiomes.arctic.get().biomeID;
        }
        
        
        return biomeId;
    }
    
    
    
    
}