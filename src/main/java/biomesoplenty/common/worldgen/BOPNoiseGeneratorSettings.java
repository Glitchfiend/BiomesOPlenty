package biomesoplenty.common.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.Map;
import java.util.Optional;

public class BOPNoiseGeneratorSettings
{
    public static final NoiseGeneratorSettings BOP_OVERWORLD = new NoiseGeneratorSettings(
            new BOPStructureSettings(true),
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

    public static final NoiseGeneratorSettings BOP_NETHER = nether();

    private static NoiseGeneratorSettings nether()
    {
        Map<StructureFeature<?>, StructureFeatureConfiguration> map = Maps.newHashMap(StructureSettings.DEFAULTS);
        map.put(StructureFeature.RUINED_PORTAL, new StructureFeatureConfiguration(25, 10, 34222645));
        return new NoiseGeneratorSettings(new StructureSettings(Optional.empty(), map), NoiseSettings.create(0, 128, new NoiseSamplingSettings(1.0D, 3.0D, 80.0D, 60.0D), new NoiseSlider(0.9375D, 3, 0), new NoiseSlider(2.5D, 4, -1), 1, 2, false, false, false, TerrainProvider.nether()), Blocks.NETHERRACK.defaultBlockState(), Blocks.LAVA.defaultBlockState(), BOPSurfaceRuleData.nether(), 32, false, false, false, false, false, true);
    }

    static
    {
        Registry.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, new ResourceLocation(BiomesOPlenty.MOD_ID, "overworld"), BOP_OVERWORLD);
        Registry.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, new ResourceLocation(BiomesOPlenty.MOD_ID, "nether"), BOP_NETHER);
    }
}
