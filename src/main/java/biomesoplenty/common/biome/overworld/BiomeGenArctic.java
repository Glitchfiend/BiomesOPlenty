/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenArctic extends BOPBiome
{    
    public BiomeGenArctic()
    {
        // terrain
        this.bopMinHeight = 63;
        this.bopMaxHeight = 70;
        
        this.setColor(14540253);
        this.setEnableSnow();
        this.setTemperatureRainfall(0.05F, 0.5F);

        this.addWeight(BiomeType.ICY, 10);

        this.spawnableCreatureList.clear();
        
        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("tanzanite");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 11176526;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 11903827;
    }
}
