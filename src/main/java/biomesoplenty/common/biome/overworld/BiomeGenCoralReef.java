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
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
 
public class BiomeGenCoralReef extends BOPBiome
{
    public BiomeGenCoralReef()
    {
        super("coral_reef", new PropsBuilder("Coral Reef").withGuiColour(18285));
        
        // terrain
        this.terrainSettings.avgHeight(45).heightVariation(5, 10); 
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.beachBiomeLocation = BiomeUtils.getLocForBiome(Biomes.beach);
        
        clearWeights();

        // coral
        this.addGenerator("pink_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.PINK)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("orange_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ORANGE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("blue_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.BLUE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("glowing_coral", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(15.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.GLOWING)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        
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
