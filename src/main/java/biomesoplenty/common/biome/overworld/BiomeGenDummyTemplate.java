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
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPFlatPlant;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorBigMushroom;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;


// This class is not intended to be used in the game
// It just contains one of every common generator, so it can be used as a template when creating new biomes - just delete lines you don't want and adjust weightings
public class BiomeGenDummyTemplate extends BOPOverworldBiome
{

    public BiomeGenDummyTemplate()
    {
        super(null, null);
        
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(6, 21).octaves(1, 1, 1, 1, 1, 1);
        
        this.skyColor = 0xFFFFFF;

        this.addWeight(BOPClimates.COOL_TEMPERATE, 10);
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.GRAVEL.getDefaultState()).create());
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(BOPBlocks.mud.getDefaultState()).create());

        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        // TODO: standard trees here?  Is there such a thing as standard trees?
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("deadgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.DEADGRASS).create());
        grassGenerator.add("desertgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.DESERTGRASS).create());
        grassGenerator.add("dunegrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.DUNEGRASS).create());        
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("ferns", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        grassGenerator.add("doublegrass", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());

        // other plants
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPFoliage.BERRYBUSH).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPFoliage.BUSH).create());
        this.addGenerator("cattails", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.CATTAIL).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPFlatPlant.PlantType.DEADLEAFPILE).create());
        this.addGenerator("desert_sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPFoliage.DESERTSPROUTS).create());
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPFoliage.KORU).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPFlatPlant.PlantType.LEAFPILE).create());
        this.addGenerator("spectral_ferns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPFoliage.SPECTRALFERN).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.THORN).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockTallGrass.EnumType.DEAD_BUSH).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
        this.addGenerator("tall_cattails", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL).create());
        this.addGenerator("tall_ferns", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockDoublePlant.EnumPlantType.FERN).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.5F).placeOn(BlockQueries.litFertileWaterside).with(Blocks.REEDS.getDefaultState()).minHeight(1).maxHeight(3).create());
        // TODO root
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.0F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("alliums", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.ALLIUM).create()));
        flowerGenerator.add("blue_orchids", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.BLUE_ORCHID).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("houstonias", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.HOUSTONIA).create()));
        flowerGenerator.add("orange_tulips", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.ORANGE_TULIP).create()));
        flowerGenerator.add("oxeye_daisies", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("pink_tulips", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.PINK_TULIP).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.POPPY).create()));
        flowerGenerator.add("red_tulips", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.RED_TULIP).create()));
        flowerGenerator.add("white_tulips", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.WHITE_TULIP).create()));
        flowerGenerator.add("blue_hydrangeas", 1, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        flowerGenerator.add("burning_blossoms", 1, (new GeneratorFlora.Builder().with(BOPFlowers.BURNING_BLOSSOM).create()));
        flowerGenerator.add("wilted_lily", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WILTED_LILY).create()));
        flowerGenerator.add("deathbloom", 1, (new GeneratorFlora.Builder().with(BOPFlowers.DEATHBLOOM).create()));
        flowerGenerator.add("glowflower", 1, (new GeneratorFlora.Builder().with(BOPFlowers.GLOWFLOWER).create()));
        flowerGenerator.add("goldenrods", 1, (new GeneratorFlora.Builder().with(BOPFlowers.GOLDENROD).create()));
        flowerGenerator.add("lavender", 1, (new GeneratorFlora.Builder().with(BOPFlowers.LAVENDER).create()));
        flowerGenerator.add("orange_cosmos", 1, (new GeneratorFlora.Builder().with(BOPFlowers.ORANGE_COSMOS).create()));
        flowerGenerator.add("pink_daffodil", 1, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_DAFFODIL).create()));
        flowerGenerator.add("hibiscus", 1, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_HIBISCUS).create()));
        flowerGenerator.add("roses", 1, (new GeneratorFlora.Builder().with(BOPFlowers.ROSE).create()));
        flowerGenerator.add("violet", 1, (new GeneratorFlora.Builder().with(BOPFlowers.VIOLET).create()));
        flowerGenerator.add("wildflowers", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WILDFLOWER).create()));
        flowerGenerator.add("paeonias", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.PAEONIA).create());
        flowerGenerator.add("tall_roses", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.ROSE).create());
        flowerGenerator.add("sunflowers", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.SUNFLOWER).create());
        flowerGenerator.add("syringas", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.SYRINGA).create());

        // shrooms
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("glowshrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());

        // big mushrooms
        GeneratorWeighted mushroomGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("big_mushrooms", GeneratorStage.BIG_SHROOM, mushroomGenerator);
        mushroomGenerator.add("brown_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).create());
        mushroomGenerator.add("red_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).create());
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0xFFFFFF);
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0xFFFFFF);
    }
}
