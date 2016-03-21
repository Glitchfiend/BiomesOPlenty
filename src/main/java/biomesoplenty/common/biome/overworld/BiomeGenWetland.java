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
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.entities.EntitySnail;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenWetland extends BOPBiome
{    
    
    public BiomeGenWetland()
    {
        super("wetland", new PropsBuilder("Wetland").withGuiColour(0x4F9657).withTemperature(0.6F).withRainfall(0.7F).withWaterColor(0x636084));

        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(5, 15).octaves(1, 1, 1, 1, 0, 0).sidewaysNoise(0.0F);

        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();

        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 7);
        this.addWeight(BOPClimates.COLD_SWAMP, 5);
        
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySnail.class, 8, 1, 2));
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(6).maxRadius(8).with(BOPBlocks.mud.getDefaultState()).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow", 3, (new GeneratorBasicTree.Builder()).log(BOPWoods.WILLOW).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).minHeight(8).maxHeight(12).maxLeavesRadius(2).vine(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).leavesOffset(0).create());
        treeGenerator.add("spruce", 5, (new GeneratorTaigaTree.Builder()).maxHeight(13).create()); // TODO: implement pine cones
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(10.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("fern", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.7F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("swampflower", 2, (new GeneratorFlora.Builder()).with(BOPFlowers.SWAMPFLOWER).create());
        flowerGenerator.add("blue_orchid", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.BLUE_ORCHID).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.KORU).create());
        this.addGenerator("cattail", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(8.0F).with(BOPPlants.CATTAIL).create());
        this.addGenerator("double_cattail", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(10.0F).with(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(1.0F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(Blocks.reeds.getDefaultState()).minHeight(1).maxHeight(3).create());
        this.addGenerator("river_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(2.0F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(BlockBOPPlant.paging.getVariantState(BOPPlants.RIVERCANE)).minHeight(1).maxHeight(3).create());
        
        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.waterlily.getDefaultState()).create());
        this.addGenerator("medium_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPLilypad.LilypadType.MEDIUM).create());
        this.addGenerator("small_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.SMALL).create());
        this.addGenerator("tiny_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.TINY).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("flower_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.35F).with(BlockBOPLilypad.LilypadType.FLOWER).create());
        
        // shrooms
        this.addGenerator("flat_mushroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("blue_milk_caps", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(Blocks.red_mushroom.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.brown_mushroom.getDefaultState()).create());

        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("malachite");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopMushrooms) {this.removeGenerator("toadstools"); this.removeGenerator("flat_mushroom"); this.removeGenerator("blue_milk_caps"); this.removeGenerator("portobellos");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily"); this.removeGenerator("flower_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.generateBopTrees) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow", 3, (new GeneratorBasicTree.Builder()).log(BlockPlanks.EnumType.OAK).leaves(Blocks.leaves.getStateFromMeta(BlockPlanks.EnumType.OAK.getMetadata()).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).minHeight(8).maxHeight(12).maxLeavesRadius(2).vine(Blocks.leaves.getStateFromMeta(BlockPlanks.EnumType.OAK.getMetadata()).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).leavesOffset(0).create());
        treeGenerator.add("spruce", 5, (new GeneratorTaigaTree.Builder()).maxHeight(13).create()); // TODO: implement pine cones
        }
        
        GeneratorWeighted flowerGen = (GeneratorWeighted)this.getGenerator("flowers");
        if (!settings.generateBopFlowers) {flowerGen.removeGenerator("bluebells"); flowerGen.removeGenerator("clover"); flowerGen.removeGenerator("swampflower"); flowerGen.removeGenerator("deathbloom"); flowerGen.removeGenerator("glowflower"); flowerGen.removeGenerator("blue_hydrangeas"); flowerGen.removeGenerator("pink_daffodil"); flowerGen.removeGenerator("white_anemones"); flowerGen.removeGenerator("orange_cosmos"); flowerGen.removeGenerator("wildflowers"); flowerGen.removeGenerator("violet"); flowerGen.removeGenerator("hibiscus"); flowerGen.removeGenerator("goldenrods"); flowerGen.removeGenerator("icy_irises"); flowerGen.removeGenerator("wilted_lily"); flowerGen.removeGenerator("lily_of_the_valley"); flowerGen.removeGenerator("bromeliad"); this.removeGenerator("bromeliad");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x5A935F;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x4F9657;
    }
    
   
}
