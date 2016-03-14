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
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorPineTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenHeathland extends BOPBiome
{    
    public BiomeGenHeathland()
    {
        super("heathland", new PropsBuilder("Heathland").withGuiColour(0xADAE68).withTemperature(0.75F).withRainfall(0.2F));

        // terrain
        this.terrainSettings.avgHeight(75).heightVariation(7, 10).octaves(0, 1, 2, 2, 1, 0).sidewaysNoise(0.1D);

        this.addWeight(BOPClimates.DRY_TEMPERATE, 5);
        
        this.canGenerateVillages = true;
        
        // TODO: why is there SO many horses?
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 1, 2, 6));
 
        // sand and gravel
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.sand.getDefaultState()).create());
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
         
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(3.0F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("wildflower", 7, (new GeneratorFlora.Builder()).with(BOPFlowers.WILDFLOWER).create());
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("allium", 4, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.ALLIUM).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(6.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        
        // trees
        IBlockPosQuery suitableTreePosition = BlockQuery.buildAnd().withAltitudeBetween(77, 90).materials(Material.ground, Material.grass).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(12);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("red_bush", 7, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.air).withNonDecayingLeaf(BOPTrees.MAPLE).create());
        treeGenerator.add("small_bush", 11, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.air).withNonDecayingLeaf(BlockPlanks.EnumType.OAK).create());
        treeGenerator.add("oak_bush", 3, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("oak", 2, (new GeneratorBasicTree.Builder()).minHeight(3).maxHeight(5).create());
        treeGenerator.add("decaying_tree", 1, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).minHeight(5).maxHeight(12).foliageHeight(2).create());
        treeGenerator.add("pine", 15, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).log(BOPWoods.PINE).leaves(BOPTrees.PINE).placeOn(suitableTreePosition).create());  
        
        // other plants
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BOPPlants.THORN).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(4.5F).with(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(6.0F).with(BOPPlants.DESERTGRASS).create());
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("peridot");}
        
        if (!settings.generateThorns) {this.removeGenerator("thorns");}
        
        if (!settings.generateBopSoils) {this.topBlock = Blocks.grass.getDefaultState(); this.fillerBlock = Blocks.dirt.getDefaultState();}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.generateBopTrees) {this.removeGenerator("trees");
        
        IBlockPosQuery suitableTreePosition = BlockQuery.buildAnd().withAltitudeBetween(77, 90).materials(Material.ground, Material.grass).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(12);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("red_bush", 7, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.air).withNonDecayingLeaf(BlockPlanks.EnumType.BIRCH).create());
        treeGenerator.add("small_bush", 11, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.air).withNonDecayingLeaf(BlockPlanks.EnumType.OAK).create());
        treeGenerator.add("oak_bush", 3, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("oak", 2, (new GeneratorBasicTree.Builder()).minHeight(3).maxHeight(5).create());
        treeGenerator.add("decaying_tree", 1, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).minHeight(5).maxHeight(12).foliageHeight(2).create());
        treeGenerator.add("pine", 15, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).log(BlockPlanks.EnumType.SPRUCE).leaves(BlockPlanks.EnumType.SPRUCE).placeOn(suitableTreePosition).create());  
        }
        
        GeneratorWeighted flowerGen = (GeneratorWeighted)this.getGenerator("flowers");
        if (!settings.generateBopFlowers) {flowerGen.removeGenerator("bluebells"); flowerGen.removeGenerator("clover"); flowerGen.removeGenerator("swampflower"); flowerGen.removeGenerator("deathbloom"); flowerGen.removeGenerator("glowflower"); flowerGen.removeGenerator("blue_hydrangeas"); flowerGen.removeGenerator("pink_daffodil"); flowerGen.removeGenerator("white_anemones"); flowerGen.removeGenerator("orange_cosmos"); flowerGen.removeGenerator("wildflowers"); flowerGen.removeGenerator("violet"); flowerGen.removeGenerator("hibiscus"); flowerGen.removeGenerator("goldenrods"); flowerGen.removeGenerator("icy_irises"); flowerGen.removeGenerator("wilted_lily"); flowerGen.removeGenerator("lily_of_the_valley"); flowerGen.removeGenerator("bromeliad"); this.removeGenerator("bromeliad");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass"); this.removeGenerator("desertgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 0xAD9D68 : 0xADAE68;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 0xB6A763 : 0xB6C663;
    }
    
}
