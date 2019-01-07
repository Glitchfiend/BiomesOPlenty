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
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPFlatPlant;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.world.generator.GeneratorBigMushroom;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenMysticGrove extends BOPOverworldBiome
{    
    
    public BiomeGenMysticGrove()
    {
        super("mystic_grove", new PropsBuilder("Mystic Grove").withGuiColour(0x69CFDB).withTemperature(0.7F).withRainfall(0.8F).withWaterColor(0xFF107A));

        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(5, 20);
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SILTY);

        this.skyColor = 0x88E8D0;
        this.fogColor = 0xFFAAC9;

        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        this.canSpawnInBiome = false;
        
        this.beachBiomeLocation = null;
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 1);
        
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 4, 4));      
        
        // hot springs
        this.addGenerator("hot_springs", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.5F).liquid(BOPBlocks.hot_spring_water).frozenLiquid((IBlockState)null).create());

        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("ivy_tree", 7, (new GeneratorBasicTree.Builder()).minHeight(5).maxHeight(9).maxLeavesRadius(2).leaves(Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).vine(BOPBlocks.ivy.getDefaultState()).create());
        treeGenerator.add("magic", 17, (new GeneratorBasicTree.Builder()).log(BOPWoods.MAGIC).leaves(BOPTrees.MAGIC).create());
        treeGenerator.add("jacaranda", 9, (new GeneratorBasicTree.Builder()).minHeight(5).maxHeight(7).log(BOPWoods.JACARANDA).leaves(BOPTrees.JACARANDA).create());
        treeGenerator.add("oak_large", 8, (new GeneratorBigTree.Builder()).minHeight(9).altLeaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.FLOWERING).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("blue_hydrangeas", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        flowerGenerator.add("syringa", 1, (new GeneratorDoubleFlora.Builder().with(BlockDoublePlant.EnumPlantType.SYRINGA).create()));
        flowerGenerator.add("glowflower", 5, (new GeneratorFlora.Builder().with(BOPFlowers.GLOWFLOWER).create()));
        flowerGenerator.add("houstonia", 3, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.HOUSTONIA).create()));
        flowerGenerator.add("pink_daffodil", 2, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_DAFFODIL).create()));
        flowerGenerator.add("oxeye_daisy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));

        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(Blocks.WATERLILY.getDefaultState()).create());
        
        // shrooms
        this.addGenerator("glowshrooms_surface", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
        this.addGenerator("big_red_mushroom", GeneratorStage.BIG_SHROOM,(new GeneratorBigMushroom.Builder()).amountPerChunk(0.4F).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).create());
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0x69CFDB);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0x70E099);
    }
}
