/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
 
public class BiomeGenKelpForest extends BOPBiome
{
    public BiomeGenKelpForest()
    {
        super("kelp_forest", new PropsBuilder("Kelp Forest").withGuiColour(27468));

        // terrain
        this.terrainSettings.avgHeight(40).heightVariation(5, 5);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.ocean);
        
        clearWeights();

        // algae
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
         
        // kelp
        this.addGenerator("kelp", GeneratorStage.LILYPAD, (new GeneratorColumns.Builder()).amountPerChunk(5.0F).replace(BlockQueries.waterCovered).placeOn(BlockQueries.groundBlocks).with(BOPBlocks.seaweed.getDefaultState()).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("kelp_tall", GeneratorStage.LILYPAD, (new GeneratorColumns.Builder()).amountPerChunk(6.0F).replace(BlockQueries.waterCovered).placeOn(BlockQueries.groundBlocks).with(BOPBlocks.seaweed.getDefaultState()).minHeight(8).maxHeight(18).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        
        // gem
        this.addGenerator("sapphire", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.SAPPHIRE).create()); 
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("sapphire");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
    }
}
