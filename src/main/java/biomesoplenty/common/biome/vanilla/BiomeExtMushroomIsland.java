package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Biomes;

public class BiomeExtMushroomIsland extends ExtendedBiomeWrapper
{
    public BiomeExtMushroomIsland()
    {
        super(Biomes.MUSHROOM_ISLAND);
        
        // shrooms
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("glowshrooms_surface", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).create());
    }
}
