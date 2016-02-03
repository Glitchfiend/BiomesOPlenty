/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
 
public class BiomeGenCoralReef extends BOPBiome
{
    public BiomeGenCoralReef()
    {
        // terrain
        this.terrainSettings.avgHeight(50).heightVariation(5, 10); 
        
        this.setColor(18285);
    
        this.canSpawnInBiome = false;
        
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
    }
}
