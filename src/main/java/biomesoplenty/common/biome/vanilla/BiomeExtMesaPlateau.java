package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import net.minecraft.init.Biomes;

public class BiomeExtMesaPlateau extends ExtendedBiomeWrapper
{
    public BiomeExtMesaPlateau()
    {
        super(Biomes.MESA_CLEAR_ROCK);
        
        // other plants
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(0.3F).with(BOPFoliage.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.TINYCACTUS).generationAttempts(16).create());  
    }
}
