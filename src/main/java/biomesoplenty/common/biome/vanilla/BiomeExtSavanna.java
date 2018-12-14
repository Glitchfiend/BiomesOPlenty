package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.init.Biomes;

public class BiomeExtSavanna extends ExtendedBiomeWrapper
{
    public BiomeExtSavanna()
    {
        super(Biomes.SAVANNA);
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.8F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("wildflower", 4, (new GeneratorFlora.Builder()).with(BOPFlowers.WILDFLOWER).create());
    }
}
