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
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorMixedLily;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.util.BlockPos;

public class BiomeGenSacredSprings extends BOPBiome
{
    public BiomeGenSacredSprings()
    {
        // terrain
        this.terrainSettings.avgHeight(62).heightVariation(11, 45);
        
        this.setColor(39259);
        this.setTemperatureRainfall(1.2F, 0.9F);
        
        this.addWeight(BOPClimates.WARM_TEMPERATE, 3);
        
        // trees
        this.addGenerator("sacred_oak_trees", GeneratorStage.POST, (new GeneratorBigTree.Builder()).amountPerChunk(0.2F).log(BOPWoods.SACRED_OAK).leaves(BOPTrees.SACRED_OAK).minHeight(35).maxHeight(40).trunkWidth(2).foliageDensity(2.0D).create());
        this.addGenerator("leaves_clusters", GeneratorStage.TREE, (new GeneratorBush.Builder()).amountPerChunk(12.5F).maxHeight(2).create());
    
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.75F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.15F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("pink_daffodils", 3, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_DAFFODIL).create()));
        flowerGenerator.add("poppies", 2, (new GeneratorFlora.Builder().with(EnumFlowerType.POPPY).create()));
        flowerGenerator.add("paeonias", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.PAEONIA).create());
        
        // other plants
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.5F).with(BOPPlants.LEAFPILE).create());
    
        // water plants
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
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 39285 : 39259;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 39285 : 39259;
    }
}
