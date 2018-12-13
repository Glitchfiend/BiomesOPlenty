package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Biomes;

public class BiomeExtDesertHills extends ExtendedBiomeWrapper
{
    public BiomeExtDesertHills()
    {
        super(Biomes.DESERT_HILLS);
        
        // other plants
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BOPPlants.TINYCACTUS).generationAttempts(16).create());
    }
}