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
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.world.BOPWorldSettings;

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
                if (landSeaVal == BiomeGenBase.deepOcean.biomeID)
                {
                    out[index] = climate.getRandomOceanBiome(this, true).biomeID;
                }
                else if (landSeaVal == BiomeGenBase.mushroomIsland.biomeID && climate.biomeType != BiomeType.ICY)
                {
                    // keep mushroom islands, unless it's in an icy climate in which case, replace
                    out[index] = BiomeGenBase.mushroomIsland.biomeID;
                }
                else if (landSeaVal == 0)
                {
                    out[index] = climate.getRandomOceanBiome(this, false).biomeID;
                }
                else
                {
                    out[index] = climate.getRandomLandBiome(this).biomeID;
                }
            }
        }

        return out;
    }

}
