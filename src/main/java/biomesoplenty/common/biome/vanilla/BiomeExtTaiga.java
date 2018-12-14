package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.biome.overworld.BOPOverworldBiome;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.init.Biomes;

public class BiomeExtTaiga extends ExtendedBiomeWrapper
{
    public BiomeExtTaiga()
    {
        super(Biomes.TAIGA);
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
            this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.2F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.3F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("violet", 1, (new GeneratorFlora.Builder().with(BOPFlowers.VIOLET).create()));
    }
}
