/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBigMushroom;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorMixedLily;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;


// This class is not intended to be used in the game
// It just contains one of every common generator, so it can be used as a template when creating new biomes - just delete lines you don't want and adjust weightings
public class BiomeGenDummyTemplate extends BOPBiome
{

    public BiomeGenDummyTemplate()
    {
        super(null, null);
        
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(6, 21).octaves(1, 1, 1, 1, 1, 1);
        
        this.skyColor = 0xFFFFFF;

        this.addWeight(BOPClimates.COOL_TEMPERATE, 10);
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(BOPBlocks.mud.getDefaultState()).create());

        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        // TODO: standard trees here?  Is there such a thing as standard trees?
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("deadgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DEADGRASS).create());
        grassGenerator.add("desertgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DESERTGRASS).create());
        grassGenerator.add("dunegrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DUNEGRASS).create());        
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("ferns", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("doublegrass", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());

        // other plants
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.BUSH).create());
        this.addGenerator("cattails", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.CATTAIL).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("desert_sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.DESERTSPROUTS).create());
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.KORU).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("poison_ivy", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.POISONIVY).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.SHRUB).create());
        this.addGenerator("spectral_ferns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.SPECTRALFERN).create());
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.SPROUT).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.THORN).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("wild_rice", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.WILDRICE).create());
        this.addGenerator("witherwart", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.WITHERWART).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockTallGrass.EnumType.DEAD_BUSH).create());
        this.addGenerator("eyebulbs", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPDoublePlant.DoublePlantType.EYEBULB).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
        this.addGenerator("tall_cattails", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL).create());
        this.addGenerator("tall_ferns", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.5F).with(BlockDoublePlant.EnumPlantType.FERN).create());
        this.addGenerator("river_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.5F).placeOn(BlockQueries.litFertileWaterside).with(BlockBOPPlant.paging.getVariantState(BOPPlants.RIVERCANE)).minHeight(1).maxHeight(3).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.5F).placeOn(BlockQueries.litFertileWaterside).with(Blocks.reeds.getDefaultState()).minHeight(1).maxHeight(3).create());
        // TODO root
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("mixed_lily", GeneratorStage.LILYPAD, (new GeneratorMixedLily.Builder()).amountPerChunk(0.5F).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        
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
        flowerGenerator.add("bluebells", 1, (new GeneratorFlora.Builder().with(BOPFlowers.BLUEBELLS).create()));
        flowerGenerator.add("bromeliads", 1, (new GeneratorFlora.Builder().with(BOPFlowers.BROMELIAD).create()));
        flowerGenerator.add("burning_blossoms", 1, (new GeneratorFlora.Builder().with(BOPFlowers.BURNING_BLOSSOM).create()));
        flowerGenerator.add("clover", 1, (new GeneratorFlora.Builder().with(BOPFlowers.CLOVER).create()));
        flowerGenerator.add("wilted_lily", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WILTED_LILY).create()));
        flowerGenerator.add("deathbloom", 1, (new GeneratorFlora.Builder().with(BOPFlowers.DEATHBLOOM).create()));
        flowerGenerator.add("enterlotus", 1, (new GeneratorFlora.Builder().with(BOPFlowers.ENDERLOTUS).create()));
        flowerGenerator.add("glowflower", 1, (new GeneratorFlora.Builder().with(BOPFlowers.GLOWFLOWER).create()));
        flowerGenerator.add("goldenrods", 1, (new GeneratorFlora.Builder().with(BOPFlowers.GOLDENROD).create()));
        flowerGenerator.add("icy_irises", 1, (new GeneratorFlora.Builder().with(BOPFlowers.ICY_IRIS).create()));
        flowerGenerator.add("lavender", 1, (new GeneratorFlora.Builder().with(BOPFlowers.LAVENDER).create()));
        flowerGenerator.add("lily_of_the_valley", 1, (new GeneratorFlora.Builder().with(BOPFlowers.LILY_OF_THE_VALLEY).create()));
        flowerGenerator.add("miners_delight", 1, (new GeneratorFlora.Builder().with(BOPFlowers.MINERS_DELIGHT).create()));
        flowerGenerator.add("orange_cosmos", 1, (new GeneratorFlora.Builder().with(BOPFlowers.ORANGE_COSMOS).create()));
        flowerGenerator.add("pink_daffodil", 1, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_DAFFODIL).create()));
        flowerGenerator.add("hibiscus", 1, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_HIBISCUS).create()));
        flowerGenerator.add("roses", 1, (new GeneratorFlora.Builder().with(BOPFlowers.ROSE).create()));
        flowerGenerator.add("swampflower", 1, (new GeneratorFlora.Builder().with(BOPFlowers.SWAMPFLOWER).create()));
        flowerGenerator.add("violet", 1, (new GeneratorFlora.Builder().with(BOPFlowers.VIOLET).create()));
        flowerGenerator.add("white_anemones", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("wildflowers", 1, (new GeneratorFlora.Builder().with(BOPFlowers.WILDFLOWER).create()));
        flowerGenerator.add("paeonias", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.PAEONIA).create());
        flowerGenerator.add("tall_roses", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.ROSE).create());
        flowerGenerator.add("sunflowers", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.SUNFLOWER).create());
        flowerGenerator.add("syringas", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.SYRINGA).create());

        // shrooms
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.brown_mushroom.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.red_mushroom.getDefaultState()).create());
        this.addGenerator("blue_milk_caps", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        this.addGenerator("flat_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).create());
        this.addGenerator("glowshrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).create());
        this.addGenerator("portobellos", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.PORTOBELLO).create());
        this.addGenerator("shadow_shrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.SHADOW_SHROOM).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());

        // big mushrooms
        GeneratorWeighted mushroomGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("big_mushrooms", GeneratorStage.BIG_SHROOM, mushroomGenerator);
        mushroomGenerator.add("brown_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).create());
        mushroomGenerator.add("red_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).create());
        
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.AMBER).create());

        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("amber");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xFFFFFF;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xFFFFFF;
    }
}
