package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPFlatPlant;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.init.Biomes;

public class BiomeExtJungleHills extends ExtendedBiomeWrapper
{
    public BiomeExtJungleHills()
    {
        super(Biomes.JUNGLE_HILLS);
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        
        // other plants
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.8F).placeOn(BlockQueries.fertile).with(BlockBOPFlatPlant.PlantType.LEAFPILE).generationAttempts(64).create());
        
     	// flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("orange_cosmos", 4, (new GeneratorFlora.Builder().with(BOPFlowers.ORANGE_COSMOS).create()));
    }
}
