/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.world.BOPWorldSettings;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class GenLayerBiomeBOP extends BOPGenLayer
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
        
        // debugging
        //BOPClimates.printWeights();
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
                
                // At this point, oceans and land have been assigned, and so have mushroom islands
                if (landSeaVal == BiomeGenBase.getIdForBiome(Biomes.deepOcean))
                {
                    out[index] = BiomeGenBase.getIdForBiome(climate.getRandomOceanBiome(this, true));
                }
                else if ((landSeaVal == BiomeGenBase.getIdForBiome(Biomes.mushroomIsland) || ModBiomes.islandBiomesMap.containsKey(landSeaVal)) && climate.biomeType != BiomeType.ICY)
                {
                    // keep islands, unless it's in an icy climate in which case, replace
                    out[index] = landSeaVal;
                }
                else if (landSeaVal == 0)
                {
                    out[index] = BiomeGenBase.getIdForBiome(climate.getRandomOceanBiome(this, false));
                }
                else
                {
                    out[index] = BiomeGenBase.getIdForBiome(climate.getRandomLandBiome(this));
                }
            }
        }

        return out;
    }

}
