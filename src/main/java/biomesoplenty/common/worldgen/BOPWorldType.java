package biomesoplenty.common.worldgen;

import biomesoplenty.init.ModConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.world.ForgeWorldPreset;

import java.util.List;
import java.util.function.Supplier;

public class BOPWorldType extends ForgeWorldPreset
{
    public BOPWorldType()
    {
        super(new IChunkGeneratorFactory()
        {
            @Override
            public WorldGenSettings createSettings(RegistryAccess dynamicRegistries, long seed, boolean generateStructures, boolean bonusChest, String generatorSettings)
            {
                return settings(dynamicRegistries, seed, generateStructures, bonusChest, chunkGenerator(dynamicRegistries, seed, ImmutableList.of()));
            }

            @Override
            public ChunkGenerator createChunkGenerator(RegistryAccess dynamicRegistries, long seed, String generatorSettings)
            {
                return chunkGenerator(dynamicRegistries, seed, ImmutableList.of());
            }
        });
    }

    public static WorldGenSettings settings(RegistryAccess dynamicRegistries, long seed, boolean generateStructures, boolean bonusChest, ChunkGenerator chunkGenerator)
    {
        Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        return new WorldGenSettings(seed, generateStructures, bonusChest,
                WorldGenSettings.withOverworld(dimensionTypeRegistry,
                        bopDimensions(dynamicRegistries, seed),
                        chunkGenerator));
    }

    public static ChunkGenerator chunkGenerator(RegistryAccess dynamicRegistries, long seed, List<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>> externalParameters)
    {
        return chunkGenerator(dynamicRegistries, seed, () -> dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY).getOrThrow(BOPNoiseGeneratorSettings.OVERWORLD), externalParameters);
    }

    public static ChunkGenerator chunkGenerator(RegistryAccess dynamicRegistries, long seed, Supplier<NoiseGeneratorSettings> noiseGeneratorSettingsSupplier, List<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>> externalParameters)
    {
        return new BOPNoiseBasedChunkGenerator(dynamicRegistries.registryOrThrow(Registry.NOISE_REGISTRY), BOPMultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY), externalParameters, false), seed, noiseGeneratorSettingsSupplier);
    }

    private static MappedRegistry<LevelStem> bopDimensions(RegistryAccess dynamicRegistries, long seed)
    {
        MappedRegistry<LevelStem> defaultDimensions = DimensionType.defaultDimensions(dynamicRegistries, seed);
        Registry<Biome> biomeRegistry = dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY);
        Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        Registry<NormalNoise.NoiseParameters> noiseParametersRegistry = dynamicRegistries.registryOrThrow(Registry.NOISE_REGISTRY);
        Registry<NoiseGeneratorSettings> noiseGeneratorSettings = dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);

        if (ModConfig.GenerationConfig.useBopNether.get())
        {
            defaultDimensions.register(LevelStem.NETHER, new LevelStem(() -> {
                return dimensionTypeRegistry.getOrThrow(DimensionType.NETHER_LOCATION);
            }, new BOPNoiseBasedChunkGenerator(noiseParametersRegistry, BOPMultiNoiseBiomeSource.Preset.NETHER.biomeSource(biomeRegistry, true), seed, () -> {
                return noiseGeneratorSettings.getOrThrow(BOPNoiseGeneratorSettings.NETHER);
            })), Lifecycle.stable());
        }

        return defaultDimensions;
    }
}
