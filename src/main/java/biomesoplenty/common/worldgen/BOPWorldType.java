package biomesoplenty.common.worldgen;

import biomesoplenty.init.ModConfig;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.world.ForgeWorldPreset;

public class BOPWorldType extends ForgeWorldPreset
{
    public BOPWorldType()
    {
        super(new IChunkGeneratorFactory()
        {
            @Override
            public WorldGenSettings createSettings(RegistryAccess dynamicRegistries, long seed, boolean generateStructures, boolean bonusChest, String generatorSettings)
            {
                Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
                return new WorldGenSettings(seed, generateStructures, bonusChest,
                        WorldGenSettings.withOverworld(dimensionTypeRegistry,
                                bopDimensions(dynamicRegistries, seed),
                                createChunkGenerator(dynamicRegistries, seed, generatorSettings)));
            }

            @Override
            public ChunkGenerator createChunkGenerator(RegistryAccess dynamicRegistries, long seed, String generatorSettings)
            {
                return new BOPNoiseBasedChunkGenerator(dynamicRegistries.registryOrThrow(Registry.NOISE_REGISTRY), BOPMultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY), false), seed, () -> {
                    return dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY).getOrThrow(BOPNoiseGeneratorSettings.OVERWORLD);
                });
            }
        });
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
