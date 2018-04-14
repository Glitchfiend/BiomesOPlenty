package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;

public class BiomeExtSavanna extends ExtendedBiomeWrapper
{
    public BiomeExtSavanna()
    {
        super(Biomes.SAVANNA);
        
     // quicksand
        this.addGenerator("quicksand", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.1F).liquid(BOPBlocks.sand).frozenLiquid((IBlockState)null).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 3, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());        
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.8F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("wildflower", 4, (new GeneratorFlora.Builder()).with(BOPFlowers.WILDFLOWER).create());
        flowerGenerator.add("clover", 2, (new GeneratorFlora.Builder().with(BOPFlowers.CLOVER).create()));
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());    
    }
}
