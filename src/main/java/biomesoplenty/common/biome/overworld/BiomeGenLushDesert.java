/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenLushDesert extends BOPOverworldBiome
{
    public BiomeGenLushDesert()
    {
        super("lush_desert", new PropsBuilder("Lush Desert").withGuiColour(0x8AA92D).withTemperature(1.2F).withRainfall(0.4F));

        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(8, 40).octaves(2, 2, 1, 0, 1, 1);

        this.topBlock = Blocks.HARDENED_CLAY.getDefaultState();
        this.fillerBlock = Blocks.HARDENED_CLAY.getDefaultState();
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = true;
        
        this.addWeight(BOPClimates.SAVANNA, 2);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        
        // trees
        IBlockPosQuery suitableTreePosition = BlockQuery.buildAnd().withAltitudeBetween(62, 68).states(this.topBlock).withAirAbove().create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(4);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("decaying_tree", 3, (new GeneratorBigTree.Builder()).placeOn(suitableTreePosition).minHeight(5).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).maxHeight(12).foliageHeight(3).create());
        treeGenerator.add("twiglet", 5, (new GeneratorTwigletTree.Builder()).placeOn(suitableTreePosition).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.OAK).leaves(BlockPlanks.EnumType.OAK).create());
        treeGenerator.add("dead_tree", 1, (new GeneratorBigTree.Builder()).placeOn(suitableTreePosition).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.AIR.getDefaultState()).create());
        
        this.addGenerator("grass_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(4.0F).generationAttempts(128).replace(suitableTreePosition).with(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY)).create());  
        
        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(5).maxRadius(7).replace(Blocks.HARDENED_CLAY.getDefaultState()).with(Blocks.SAND.getDefaultState()).create());
        
        // oases
        this.addGenerator("oases", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(10).maxRadius(7).replace(Blocks.HARDENED_CLAY.getDefaultState()).with(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY)).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(1.5F).waterLakeForBiome(this).create());
        
        // other plants
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(1.8F).with(BOPPlants.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("bromeliad", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder().amountPerChunk(0.2F).with(BOPFlowers.BROMELIAD).generationAttempts(8).create()));
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.75F).placeOn(BlockQueries.fertile).with(BOPPlants.LEAFPILE).generationAttempts(32).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.BUSH).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(Blocks.DEADBUSH.getDefaultState()).create());
        this.addGenerator("cacti", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.2F).generationAttempts(3).placeOn(this.topBlock).with(Blocks.CACTUS.getDefaultState()).minHeight(1).maxHeight(2).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("wildflowers", 4, (new GeneratorFlora.Builder().with(BOPFlowers.WILDFLOWER).create()));
        flowerGenerator.add("white_anemones", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // water plants
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.REED).generationAttempts(32).create());
        
        // grasses (note weighting must be quite high as the grasses will only grow on the splattered grass blocks)
        GeneratorWeighted grassGenerator = new GeneratorWeighted(12.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("tallgrass", 3, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());    
    }
    
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
        if (rand.nextInt(64) == 0)
        {
            (new WorldGenFossils()).generate(worldIn, rand, pos);
        }
    }
}
