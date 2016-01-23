package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBulbTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenDeciduousForest extends BOPBiome
{   
    public BiomeGenDeciduousForest()
    {
        // terrain
        this.terrainSettings.avgHeight(64);

        this.setColor(0xC1B749);
        this.setTemperatureRainfall(0.6F, 0.8F);
        this.addWeight(BOPClimates.COOL_TEMPERATE, 7);  
        
        this.canGenerateVillages = false;
         
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(18);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("birch_bush", 2, (new GeneratorBush.Builder()).log(BlockPlanks.EnumType.BIRCH).leaves(BlockPlanks.EnumType.BIRCH).maxHeight(2).create());
        treeGenerator.add("oak_bush", 3, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("tall_oak", 6, (new GeneratorBulbTree.Builder()).minHeight(10).maxHeight(20).vine(null).create());
  
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(10.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("fern", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
               
        // shrooms
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("blue_milk_caps", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        
        // other plants
        this.addGenerator("bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.BUSH).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BOPPlants.LEAFPILE).generationAttempts(64).create());        
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());

        // water plants
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(32).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.REED).generationAttempts(32).create());

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
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 0xC1B749 : 0xAD8E41;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 0xC4C93A : 0xAFA248;
    }
}
