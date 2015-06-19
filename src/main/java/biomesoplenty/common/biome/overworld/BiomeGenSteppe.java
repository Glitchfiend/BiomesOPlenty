/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenSteppe extends BOPBiome
{
    public BiomeGenSteppe()
    {
        // terrain
        this.terrainSettings.avgHeight(68).heightVariation(6, 20);
        
        this.setColor(13413215);
        this.setTemperatureRainfall(0.7F, 0.05F);
        
        this.addWeight(BiomeType.DESERT, 5);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        // should the Steppe not have some splattered gravel and coarse dirt?
        
        // other plants
        this.addGenerator("dead_bushes", GeneratorStage.DEAD_BUSH,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(Blocks.deadbush.getDefaultState()).generationAttempts(4).create());
        
        // grasses
        this.addGenerator("shrubs", GeneratorStage.GRASS,(new GeneratorGrass.Builder()).amountPerChunk(1.5F).with(BlockBOPPlant.paging.getVariantState(BOPPlants.SHORTGRASS)).create());

        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("ruby");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        double noise = field_180281_af.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 13214328 : 12885629;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = field_180281_af.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 13214328 : 12885629;
    }
}
