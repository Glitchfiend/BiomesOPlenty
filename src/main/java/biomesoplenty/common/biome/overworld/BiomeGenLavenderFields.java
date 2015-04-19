/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.api.block.BOPTreeEnums.AllTrees;
import biomesoplenty.api.block.BOPWoodEnums.AllWoods;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPFlower2.FlowerType;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLeaves3;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPLog3;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGem.GemType;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
 
public class BiomeGenLavenderFields extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.125F, 0.05F);

    public BiomeGenLavenderFields()
    {
        this.setHeight(biomeHeight);
        this.setColor(11035852);
        this.setTemperatureRainfall(0.6F, 0.7F);
    
        this.addWeight(BiomeType.WARM, 3);
        
        this.addGenerator("lavender", GeneratorStage.FLOWERS, new GeneratorFlora(999, BOPBlocks.flower2.getDefaultState().withProperty(BlockBOPFlower2.VARIANT, FlowerType.LAVENDER)));
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(1);
        treeGenerator.add(3, new GeneratorBasicTree(1, false, 4, 7, BOPBlocks.log_3.getDefaultState().withProperty(BlockBOPLog.getVariantProperty(BlockBOPLog3.PAGENUM), AllWoods.JACARANDA), BOPBlocks.leaves_3.getDefaultState().withProperty(BlockBOPLeaves.getVariantProperty(BlockBOPLeaves3.PAGENUM), AllTrees.JACARANDA)));
        treeGenerator.add(1, new GeneratorBigTree(1, false, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20);
        grassGenerator.add(1, new GeneratorGrass(1, BlockBOPPlant.getVariantState(AllPlants.WHEATGRASS)));
        grassGenerator.add(3, new GeneratorGrass(1, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        
        this.addGenerator("peridot", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, GemType.PERIDOT), 12, 4, 32));
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 10601325;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 10601325;
    }
}
