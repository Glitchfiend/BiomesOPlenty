/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;

public class BiomeGenLushDesert extends BOPBiome
{
    public BiomeGenLushDesert()
    {
        super("lush_desert", new PropsBuilder("Lush Desert").withGuiColour(0x8AA92D).withTemperature(1.2F).withRainfall(0.5F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(4, 15);

        this.topBlock = Blocks.HARDENED_CLAY.getDefaultState();
        this.fillerBlock = Blocks.HARDENED_CLAY.getDefaultState();
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = true;
        
        this.addWeight(BOPClimates.HOT_DESERT, 5);
        
        // trees
        IBlockPosQuery emptyHardenedClay = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("decaying_tree", 4, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).placeOn(emptyHardenedClay).minHeight(5).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).maxHeight(12).foliageHeight(2).create());
        treeGenerator.add("dead_tree", 2, (new GeneratorBigTree.Builder()).amountPerChunk(0.5F).placeOn(emptyHardenedClay).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.AIR.getDefaultState()).create());
        treeGenerator.add("oak_bush", 5, (new GeneratorBush.Builder()).maxHeight(2).placeOn(emptyHardenedClay).create());
        
        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.SAND.getDefaultState()).create());
        
        // oases
        this.addGenerator("oases", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(10).maxRadius(7).replace(Blocks.HARDENED_CLAY.getDefaultState()).with(Blocks.GRASS.getDefaultState()).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(1.5F).waterLakeForBiome(this).create());
        
        // other plants
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(0.8F).with(BOPPlants.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("bromeliad", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder().amountPerChunk(0.5F).with(BOPFlowers.BROMELIAD).generationAttempts(8).create()));
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.75F).with(BOPPlants.LEAFPILE).generationAttempts(32).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.BUSH).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.9F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(Blocks.DEADBUSH.getDefaultState()).create());
        this.addGenerator("cacti", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(1.5F).generationAttempts(3).placeOn(this.topBlock).with(Blocks.CACTUS.getDefaultState()).minHeight(1).maxHeight(2).create());
        
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
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("ruby");}
        
        if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.GRASSES)) {this.removeGenerator("desertgrass");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.isEnabled(GeneratorType.WATER_PLANTS)) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.isEnabled(GeneratorType.TREES)) {this.removeGenerator("trees");
        
        IBlockPosQuery emptyHardenedClay = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("decaying_tree", 4, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).placeOn(emptyHardenedClay).minHeight(5).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).maxHeight(12).foliageHeight(2).create());
        treeGenerator.add("dead_tree", 2, (new GeneratorBigTree.Builder()).amountPerChunk(0.5F).placeOn(emptyHardenedClay).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BlockPlanks.EnumType.DARK_OAK).leaves(Blocks.AIR.getDefaultState()).create());
        treeGenerator.add("oak_bush", 5, (new GeneratorBush.Builder()).maxHeight(2).placeOn(emptyHardenedClay).create());
        }
        
        GeneratorWeighted flowerGen = (GeneratorWeighted)this.getGenerator("flowers");
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {flowerGen.removeGenerator("bluebells"); flowerGen.removeGenerator("clover"); flowerGen.removeGenerator("swampflower"); flowerGen.removeGenerator("deathbloom"); flowerGen.removeGenerator("glowflower"); flowerGen.removeGenerator("blue_hydrangeas"); flowerGen.removeGenerator("pink_daffodil"); flowerGen.removeGenerator("white_anemones"); flowerGen.removeGenerator("orange_cosmos"); flowerGen.removeGenerator("wildflowers"); flowerGen.removeGenerator("violet"); flowerGen.removeGenerator("hibiscus"); flowerGen.removeGenerator("goldenrods"); flowerGen.removeGenerator("icy_irises"); flowerGen.removeGenerator("wilted_lily"); flowerGen.removeGenerator("lily_of_the_valley"); flowerGen.removeGenerator("bromeliad"); this.removeGenerator("bromeliad");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.isEnabled(GeneratorType.GRASSES)) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
}
