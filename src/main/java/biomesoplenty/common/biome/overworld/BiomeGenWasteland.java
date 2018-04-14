/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenWasteland extends BOPOverworldBiome
{    
    
    public BiomeGenWasteland()
    {
        super("wasteland", new PropsBuilder("Wasteland").withGuiColour(0x5A5440).withTemperature(2.0F).withRainfall(0.0F).withWaterColor(0xCC5100).withRainDisabled());

        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(1,5);
        
        this.topBlock = BOPBlocks.dried_sand.getDefaultState();
        this.fillerBlock = BOPBlocks.dried_sand.getDefaultState();
        this.seaFloorBlock = BOPBlocks.dried_sand.getDefaultState();

        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        this.canSpawnInBiome = false;
        
        this.beachBiomeLocation = null;

        //this.skyColor = 0x89A3B7;
        this.fogColor = 0xDBDDC1;
        this.fogDensity = 0.5F;

        this.addWeight(BOPClimates.WASTELAND, 50);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        // trees
        IBlockPosQuery emptyDriedSand = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.3F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dead_tree", 1, (new GeneratorBigTree.Builder()).placeOn(emptyDriedSand).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.AIR.getDefaultState()).create());
        treeGenerator.add("dying_tree", 3, (new GeneratorBigTree.Builder()).placeOn(emptyDriedSand).minHeight(5).maxHeight(12).foliageHeight(1).log(BOPWoods.DEAD).leaves(BOPTrees.DEAD).create());
        
        // other plants
        this.addGenerator("dead_grass", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.DEADGRASS).create());
        this.addGenerator("desertgrass", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.DESERTGRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.05F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("wilted_lily", 1, (new GeneratorFlora.Builder()).with(BOPFlowers.WILTED_LILY).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.05F).waterLakeForBiome(this).create());
        this.addGenerator("poison_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.05F).liquid(BOPBlocks.poison).frozenLiquid((IBlockState)null).create());
        
        // spikes
        //this.addGenerator("spikes", GeneratorStage.PRE, (new GeneratorSpike.Builder()).amountPerChunk(0.2F).create());
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x9DA078;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x999E55;
    }
}
