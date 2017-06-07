/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.init.Blocks;
 
public class BiomeGenWhiteBeach extends BOPOverworldBiome
{
    public BiomeGenWhiteBeach()
    {
        super("white_beach", new PropsBuilder("White Beach").withTemperature(1.0F).withRainfall(1.0F).withGuiColour(0xF3F1E4));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(1, 1);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.hasBiomeEssence = false;
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
        
        this.skyColor = 507391;
        this.fogColor = 0xB5F8FF;
        
        clearWeights();
        
        this.topBlock = BOPBlocks.white_sand.getDefaultState();
        this.fillerBlock = BOPBlocks.white_sand.getDefaultState();
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}

        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("toadstools"); this.removeGenerator("flat_mushroom"); this.removeGenerator("blue_milk_caps"); this.removeGenerator("portobellos");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
    }
}
