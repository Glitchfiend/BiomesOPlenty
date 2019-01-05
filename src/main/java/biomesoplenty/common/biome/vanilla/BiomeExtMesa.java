package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import net.minecraft.init.Biomes;

public class BiomeExtMesa extends ExtendedBiomeWrapper
{
    public BiomeExtMesa()
    {
        super(Biomes.MESA);
        
        // other plants
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(0.3F).with(BOPFoliage.DESERTGRASS).generationAttempts(8).create());  
    }
}
