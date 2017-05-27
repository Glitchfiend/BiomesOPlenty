package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorMahoganyTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenTropicalRainforest extends BOPOverworldBiome
{
    
    public BiomeGenTropicalRainforest()
    {
        super("tropical_rainforest", new PropsBuilder("Tropical Rainforest").withGuiColour(0x88E140).withTemperature(1.2F).withRainfall(1.0F).withWaterColor(0x5DFF00));

        // terrain
        this.terrainSettings.avgHeight(67).heightVariation(10, 25);
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;

        this.addWeight(BOPClimates.TROPICAL, 5);
        
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        
        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.SAND.getDefaultState()).create());
        
        // quicksand
        this.addGenerator("quicksand", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.2F).liquid(BOPBlocks.sand).frozenLiquid((IBlockState)null).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(15.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mahogany", 6, (new GeneratorMahoganyTree.Builder()).create());
        treeGenerator.add("jungle", 2, (new GeneratorBasicTree.Builder()).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).minHeight(8).maxHeight(12).trunkFruit(Blocks.COCOA.getDefaultState()).vine(Blocks.VINE.getDefaultState()).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("orange_cosmos", 4, (new GeneratorFlora.Builder().with(BOPFlowers.ORANGE_COSMOS).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).placeOn(BlockQueries.fertile).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("poison_ivy", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(BOPPlants.POISONIVY).create());
        this.addGenerator("double_fern", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.2F).with(BlockDoublePlant.EnumPlantType.FERN).create());
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.SPROUT).create());
        this.addGenerator("rafflesia", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.RAFFLESIA).create());
        this.addGenerator("melons", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.015625F).placeOn(this.topBlock).with(Blocks.MELON_BLOCK.getDefaultState()).create());
        
        // shrooms
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        
        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.WATERLILY.getDefaultState()).create());
        this.addGenerator("medium_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPLilypad.LilypadType.MEDIUM).create());
        this.addGenerator("small_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.SMALL).create());
        this.addGenerator("tiny_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.TINY).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("flower_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BlockBOPLilypad.LilypadType.FLOWER).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(3.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 5, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).generationAttempts(128).create());
        grassGenerator.add("mediumgrass", 5, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).generationAttempts(128).create());
        grassGenerator.add("dampgrass", 5, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).generationAttempts(128).create());
        grassGenerator.add("fern", 3, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        
        // gem
        this.addGenerator("topaz", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TOPAZ).create());

    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("topaz");}
        if (!settings.isEnabled(GeneratorType.POISON_IVY)) {this.removeGenerator("poison_ivy");}
        if (!settings.isEnabled(GeneratorType.QUICKSAND)) {this.removeGenerator("quicksand");}
        
        if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.isEnabled(GeneratorType.WATER_PLANTS)) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily"); this.removeGenerator("flower_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.isEnabled(GeneratorType.TREES)) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(15.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mahogany", 6, (new GeneratorMahoganyTree.Builder()).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).create());
        treeGenerator.add("jungle", 2, (new GeneratorBasicTree.Builder()).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).minHeight(8).maxHeight(12).trunkFruit(Blocks.COCOA.getDefaultState()).vine(Blocks.VINE.getDefaultState()).create());
        }
        
        GeneratorWeighted flowerGen = (GeneratorWeighted)this.getGenerator("flowers");
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {flowerGen.removeGenerator("bluebells"); flowerGen.removeGenerator("clover"); flowerGen.removeGenerator("swampflower"); flowerGen.removeGenerator("deathbloom"); flowerGen.removeGenerator("glowflower"); flowerGen.removeGenerator("blue_hydrangeas"); flowerGen.removeGenerator("pink_daffodil"); flowerGen.removeGenerator("white_anemones"); flowerGen.removeGenerator("orange_cosmos"); flowerGen.removeGenerator("wildflowers"); flowerGen.removeGenerator("violet"); flowerGen.removeGenerator("hibiscus"); flowerGen.removeGenerator("goldenrods"); flowerGen.removeGenerator("icy_irises"); flowerGen.removeGenerator("wilted_lily"); flowerGen.removeGenerator("lily_of_the_valley"); flowerGen.removeGenerator("bromeliad"); this.removeGenerator("bromeliad");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.isEnabled(GeneratorType.GRASSES)) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 11002176 : 12836929;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 8970560 : 10870849;
    }
}
