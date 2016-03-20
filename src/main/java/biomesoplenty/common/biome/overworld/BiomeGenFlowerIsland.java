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
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.entities.EntitySnail;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBigFlower;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplatter;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenFlowerIsland extends BOPBiome
{

    public BiomeGenFlowerIsland()
    {
        super("flower_island", new PropsBuilder("Flower Island").withGuiColour(0x74D374).withTemperature(0.6F).withRainfall(0.8F));

        // terrain
        this.terrainSettings.avgHeight(70).heightVariation(10, 15).octaves(0, 0, 1, 1, 2, 2);

        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.DAISY);

        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        clearWeights();
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySnail.class, 6, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityButterfly.class, 6, 2, 4));
        // TODO: implement rosester? this.spawnableCreatureList.add(new SpawnListEntry(EntityRosester.class, 10, 4, 4));
        
        // regular grass
        IBlockPosQuery emptyGrass = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("grass_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(6.0F).replace(emptyGrass).with(Blocks.grass.getDefaultState()).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(1);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("red_big_flowers", 1, (new GeneratorBigFlower.Builder()).flowerType(GeneratorBigFlower.BigFlowerType.RED).create());
        treeGenerator.add("yellow_big_flowers", 1, (new GeneratorBigFlower.Builder()).flowerType(GeneratorBigFlower.BigFlowerType.YELLOW).create());
        treeGenerator.add("oak_bush", 2, (new GeneratorBush.Builder()).maxHeight(2).altLeaves(BOPTrees.FLOWERING).create());
        
        // grasses      
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.75F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());

        // other plants
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.SPROUT).create());
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("dandelion", 3, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 5, (new GeneratorFlora.Builder().with(EnumFlowerType.POPPY).create()));
        flowerGenerator.add("white_anemones", 4, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("blue_hydrangeas", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        flowerGenerator.add("sunflowers", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.SUNFLOWER).create());
        flowerGenerator.add("oxeye_daisy", 2, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("rose", 2, (new GeneratorDoubleFlora.Builder().with(BlockDoublePlant.EnumPlantType.ROSE).create()));
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create());

        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("peridot");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.generateBopTrees) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(1);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("red_big_flowers", 1, (new GeneratorBigFlower.Builder()).flowerType(GeneratorBigFlower.BigFlowerType.RED).create());
        treeGenerator.add("yellow_big_flowers", 1, (new GeneratorBigFlower.Builder()).flowerType(GeneratorBigFlower.BigFlowerType.YELLOW).create());
        treeGenerator.add("oak_bush", 2, (new GeneratorBush.Builder()).maxHeight(2).create());
        }
        
        GeneratorWeighted flowerGen = (GeneratorWeighted)this.getGenerator("flowers");
        if (!settings.generateBopFlowers) {flowerGen.removeGenerator("bluebells"); flowerGen.removeGenerator("clover"); flowerGen.removeGenerator("swampflower"); flowerGen.removeGenerator("deathbloom"); flowerGen.removeGenerator("glowflower"); flowerGen.removeGenerator("blue_hydrangeas"); flowerGen.removeGenerator("pink_daffodil"); flowerGen.removeGenerator("white_anemones"); flowerGen.removeGenerator("orange_cosmos"); flowerGen.removeGenerator("wildflowers"); flowerGen.removeGenerator("violet"); flowerGen.removeGenerator("hibiscus"); flowerGen.removeGenerator("goldenrods"); flowerGen.removeGenerator("icy_irises"); flowerGen.removeGenerator("wilted_lily"); flowerGen.removeGenerator("lily_of_the_valley"); flowerGen.removeGenerator("bromeliad"); this.removeGenerator("bromeliad");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x74D374;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x66E266;
    }
}
