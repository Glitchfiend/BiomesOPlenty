/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGem.GemType;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenThicket extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
    
    public BiomeGenThicket()
    {
        this.setHeight(biomeHeight);
        this.setColor(7248193);
        this.setTemperatureRainfall(0.6F, 0.2F);
        
        this.addWeight(BiomeType.COOL, 5);
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(17);
        treeGenerator.add(1, new GeneratorBasicTree(1, false, 4, 7, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        treeGenerator.add(4,  new GeneratorBush(1, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
       
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, new GeneratorWaterside(6, 7, Blocks.gravel.getDefaultState()));

        this.addGenerator("flowers", GeneratorStage.FLOWERS, new GeneratorFlora(5, Blocks.red_flower.getDefaultState()));
        this.addGenerator("thorns", GeneratorStage.FLOWERS, new GeneratorFlora(55, BlockBOPPlant.paging.getVariantState(AllPlants.THORN)));
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, new GeneratorFlora(5, BlockBOPPlant.paging.getVariantState(AllPlants.SHRUB)));

        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(5, BlockBOPPlant.paging.getVariantState(AllPlants.LEAFPILE), 256));
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(AllPlants.DEADLEAFPILE), 256));
        
        this.addGenerator("amber", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, GemType.AMBER), 12, 4, 32));
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 11049591;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 10854765;
    }
}
