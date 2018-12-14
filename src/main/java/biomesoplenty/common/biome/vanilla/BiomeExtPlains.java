package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import net.minecraft.init.Biomes;

public class BiomeExtPlains extends ExtendedBiomeWrapper
{
    public BiomeExtPlains()
    {
        super(Biomes.PLAINS);
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
    }
}
