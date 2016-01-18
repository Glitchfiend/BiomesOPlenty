package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorMixedLily;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;

public class BiomeGenRainforest extends BOPBiome
{    
    public BiomeGenRainforest()
    {
        // terrain
        this.terrainSettings.avgHeight(80).heightVariation(50, 50).sidewaysNoise(1.5D);

        this.setColor(0x14E26F);
        this.setTemperatureRainfall(1.0F, 1.0F);
        this.addWeight(BOPClimates.TROPICAL, 10);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(20);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("birch", 1, (new GeneratorBasicTree.Builder()).log(BlockPlanks.EnumType.BIRCH).leaves(BlockPlanks.EnumType.BIRCH).minHeight(5).maxHeight(8).create());
        treeGenerator.add("oak", 4, (new GeneratorBasicTree.Builder()).minHeight(8).maxHeight(12).create());
        treeGenerator.add("large_oak", 1, (new GeneratorBigTree.Builder()).log(BlockPlanks.EnumType.OAK).leaves(BlockPlanks.EnumType.OAK).create());
 
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("fern", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("pink_daffodil", 3, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_DAFFODIL).create()));
        flowerGenerator.add("orange_cosmos", 4, (new GeneratorFlora.Builder().with(BOPFlowers.ORANGE_COSMOS).create()));
        flowerGenerator.add("blue_orchid", 2, (new GeneratorFlora.Builder().with(EnumFlowerType.BLUE_ORCHID).create()));

        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.CLOVERPATCH).create());

        // water plants
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(32).create());
        this.addGenerator("mixed_lily", GeneratorStage.LILYPAD, (new GeneratorMixedLily.Builder()).amountPerChunk(1.0F).generationAttempts(96).create());
        
        // gem
        this.addGenerator("topaz", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TOPAZ).create());
 
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("topaz");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x1AD86C;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x14E26F;
    }
}
