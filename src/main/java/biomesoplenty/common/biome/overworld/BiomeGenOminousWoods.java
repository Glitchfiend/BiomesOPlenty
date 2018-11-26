/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenOminousWoods extends BOPOverworldBiome
{    
    
    public BiomeGenOminousWoods()
    {
        super("ominous_woods", new PropsBuilder("Ominous Woods").withGuiColour(0x3F4151).withTemperature(0.45F).withRainfall(0.6F).withWaterColor(0x1E1B26));

        // terrain
        this.terrainSettings.avgHeight(68).heightVariation(5, 15);

        this.skyColor = 0x34333D;

        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
        
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        this.canSpawnInBiome = false;
        
        this.beachBiomeLocation = null;

        this.fogColor = 0x34333D;
        this.fogDensity = 0.3F;

        this.addWeight(BOPClimates.COLD_SWAMP, 1);
        
        this.spawnableCreatureList.clear();
        
        // lakes
        this.addGenerator("poison_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.1F).liquid(BOPBlocks.poison).frozenLiquid((IBlockState)null).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(12);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow_tree", 3, (new GeneratorBasicTree.Builder()).log(BOPWoods.WILLOW).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).minHeight(5).maxHeight(9).maxLeavesRadius(2).vine(BOPBlocks.willow_vine.getDefaultState()).create());
        treeGenerator.add("umbran_spruce", 5, (new GeneratorTaigaTree.Builder()).log(BOPWoods.UMBRAN).leaves(BOPTrees.UMBRAN).maxHeight(20).create()); // TODO: implement pine cones
        treeGenerator.add("dead_tree", 2, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.AIR.getDefaultState()).create());
        treeGenerator.add("mega_umbran", 4, (new GeneratorTaigaTree.Builder()).log(BOPWoods.UMBRAN).leaves(BOPTrees.UMBRAN).minHeight(20).maxHeight(30).trunkWidth(2).create());
        treeGenerator.add("dying_tree", 1, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(1).log(BOPWoods.DEAD).leaves(BOPTrees.DEAD).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.2F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.7F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("deathbloom", 2, (new GeneratorFlora.Builder()).with(BOPFlowers.DEATHBLOOM).create());
        
        // other plants
        this.addGenerator("poison_ivy", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(BOPPlants.POISONIVY).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.THORN).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).placeOn(BlockQueries.fertile).with(BOPPlants.DEADLEAFPILE).create());

        // shrooms
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());

        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0x3F4151);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0x3F4151);
    }
}
