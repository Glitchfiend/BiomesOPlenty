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
import biomesoplenty.common.block.BlockFoliage;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockFoliage.FoliageType;
import biomesoplenty.common.block.BlockGem.GemType;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
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
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted();
        treeGenerator.add(1, new GeneratorBasicTree(17, false, 4, 3, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        treeGenerator.add(4,  new GeneratorBush(17, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
       
        //TODO: Add the rest of the generators, requires plant blocks
        this.addGenerator("flowers", GeneratorStage.FLOWERS, new GeneratorFlora(5, Blocks.red_flower.getDefaultState()));
        //this.addGenerator("thorns", GeneratorStage.FLOWERS, new GeneratorFlora(55, BOPBlocks.foliage.getDefaultState().withProperty(BlockBOPPlant., FoliageType.LEAFPILE));
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(5, BOPBlocks.foliage.getDefaultState().withProperty(BlockFoliage.VARIANT, FoliageType.LEAFPILE)));
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(10, BOPBlocks.foliage.getDefaultState().withProperty(BlockFoliage.VARIANT, FoliageType.DEADLEAFPILE)));
        
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
