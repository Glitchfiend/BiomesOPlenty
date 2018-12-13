package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Biomes;

public class BiomeExtDesert extends ExtendedBiomeWrapper
{
    public BiomeExtDesert()
    {
        super(Biomes.DESERT);

        // other plants
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BOPPlants.TINYCACTUS).generationAttempts(16).create()); 
    }
}