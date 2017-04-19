/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.BOPGenLayer;
import biomesoplenty.common.init.ModBiomes;
import biomesoplenty.common.world.BOPWorldSettings;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class GenLayerBiomeBOPHell extends BOPGenLayer
{
    private BOPWorldSettings settings;

    public GenLayerBiomeBOPHell(long seed, BOPWorldSettings settings)
    {
        super(seed);
        this.settings = settings;
        
        // debugging
        //BOPClimates.printWeights();
    }
    
    // Get array of biome IDs covering the requested area
    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);
        
        for (int x = 0; x < areaHeight; ++x)
        {
            for (int z = 0; z < areaWidth; ++z)
            {
                int index = z + x * areaWidth;
                this.initChunkSeed((long)(z + areaX), (long)(x + areaY));
                out[index] = Biome.getIdForBiome(BOPClimates.HELL.getRandomBiome(this));
            }
        }

        return out;
    }

}
