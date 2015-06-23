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
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.world.BOPWorldSettings;

public class GenLayerBiomeBOP extends GenLayer
{
    
    private BOPWorldSettings settings;
    private GenLayer landMassLayer;
    private GenLayer climateLayer;
    
    public GenLayerBiomeBOP(long seed, GenLayer landMassLayer, GenLayer climateLayer, BOPWorldSettings settings)
    {
        super(seed);
        this.landMassLayer = landMassLayer;
        this.climateLayer = climateLayer;
        this.settings = settings;
    }
    
    
    // Get array of biome IDs covering the requested area
    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] landSeaValues = this.landMassLayer.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] climateValues = this.climateLayer.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);
        
        for (int x = 0; x < areaHeight; ++x)
        {
            for (int z = 0; z < areaWidth; ++z)
            {
                int index = z + x * areaWidth;
                this.initChunkSeed((long)(z + areaX), (long)(x + areaY));
                int landSeaVal = landSeaValues[index];
                BOPClimates climate = BOPClimates.lookup(climateValues[index]);
                
                if (landSeaVal == 0)
                {
                    out[index] = 0;
                }
                else
                {
                
                    switch (climate)
                    {
                        case ICE_CAP:
                            out[index] = BiomeGenBase.icePlains.biomeID;
                            break;
                            
                        case FROZEN_DESERT:
                            out[index] = BiomeGenBase.icePlains.biomeID;
                            break;
                            
                        case TUNDRA:
                            out[index] = BiomeGenBase.coldTaiga.biomeID;
                            break;
                            
                        case COLD_DESERT:
                            out[index] = BiomeGenBase.extremeHills.biomeID;
                            break;
                            
                        case BOREAL:
                            out[index] = BiomeGenBase.coldTaiga.biomeID;
                            break;
                            
                        case COLD_SWAMP:
                            out[index] = BiomeGenBase.plains.biomeID;
                            break;
                            
                        case WET_TEMPERATE:
                            out[index] = BiomeGenBase.forest.biomeID;
                            break;
                            
                        case DRY_TEMPERATE:
                            out[index] = BiomeGenBase.plains.biomeID;
                            break;
                            
                        case COOL_TEMPERATE:
                            out[index] = BiomeGenBase.plains.biomeID;
                            break;
                            
                        case WARM_TEMPERATE:
                            out[index] = BiomeGenBase.plains.biomeID;
                            break;
                            
                        case HOT_SWAMP:
                            out[index] = BiomeGenBase.swampland.biomeID;
                            break;
                            
                        case TROPICAL:
                            out[index] = BiomeGenBase.jungle.biomeID;
                            break;
                            
                        case MEDITERANEAN:
                            out[index] = BiomeGenBase.mesa.biomeID;
                            break;
                            
                        case SAVANNA:
                            out[index] = BiomeGenBase.savanna.biomeID;
                            break;
                            
                        case HOT_DESERT:
                            out[index] = BiomeGenBase.desert.biomeID;
                            break;
                            
                    }
                }
            }
        }

        return out;
    }

}
