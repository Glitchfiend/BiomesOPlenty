package biomesoplenty.common.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;

public class BOPNoiseGeneratorSettings
{
    public static final NoiseGeneratorSettings BOP = new NoiseGeneratorSettings(
            new StructureSettings(true),
            NoiseSettings.create(
                    -64, 384,
                    new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D),
                    new NoiseSlider(-0.078125D, 2, 8),
                    new NoiseSlider(0.1171875D, 3, 0),
                    1, 2, false, false, false,
                    TerrainProvider.overworld(false)),
            Blocks.STONE.defaultBlockState(),
            Blocks.WATER.defaultBlockState(),
            BOPSurfaceRuleData.overworld(),
            63, false, true, true, true, true, false);

    static
    {
        Registry.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, new ResourceLocation(BiomesOPlenty.MOD_ID, "overworld"), BOP);
    }
}
