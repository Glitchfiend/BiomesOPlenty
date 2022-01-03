package biomesoplenty.common.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.world.ForgeWorldPreset;
import terrablender.api.BiomeProviders;
import terrablender.api.WorldPresetUtils;
import terrablender.worldgen.*;
import terrablender.worldgen.TBNoiseGeneratorSettings;

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
                return WorldPresetUtils.settings(dynamicRegistries, seed, generateStructures, bonusChest, bopDimensions(dynamicRegistries, seed), WorldPresetUtils.chunkGenerator(dynamicRegistries, seed));
            }

            @Override
            public ChunkGenerator createChunkGenerator(RegistryAccess dynamicRegistries, long seed, String generatorSettings)
            {
                return WorldPresetUtils.chunkGenerator(dynamicRegistries, seed);
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
            }, new TBNoiseBasedChunkGenerator(noiseParametersRegistry, NETHER.biomeSource(biomeRegistry, true), seed, () -> {
                return noiseGeneratorSettings.getOrThrow(TBNoiseGeneratorSettings.NETHER);
            })), Lifecycle.stable());
        }

        return defaultDimensions;
    }

    private static final TBMultiNoiseBiomeSource.Preset NETHER = new TBMultiNoiseBiomeSource.Preset(new ResourceLocation(BiomesOPlenty.MOD_ID, "nether"), (biomeRegistry) -> {
        return new TBClimate.ParameterList<>(ImmutableList.of(
                Pair.of(TBClimate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(Biomes.NETHER_WASTES)),
                Pair.of(TBClimate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(Biomes.SOUL_SAND_VALLEY)),
                Pair.of(TBClimate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(Biomes.CRIMSON_FOREST)),
                Pair.of(TBClimate.parameters(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.375F), () -> biomeRegistry.getOrThrow(Biomes.WARPED_FOREST)),
                Pair.of(TBClimate.parameters(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.175F), () -> biomeRegistry.getOrThrow(Biomes.BASALT_DELTAS)),

                Pair.of(TBClimate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, getUniqueness(), 1.0F, 0.0F), () -> biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.WITHERED_ABYSS, Biomes.NETHER_WASTES))),
                Pair.of(TBClimate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, getUniqueness(), 0.0F, 0.0F), () ->  biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.CRYSTALLINE_CHASM, Biomes.SOUL_SAND_VALLEY))),
                Pair.of(TBClimate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, getUniqueness(), 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.UNDERGROWTH, Biomes.CRIMSON_FOREST))),
                Pair.of(TBClimate.parameters(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, getUniqueness(), 0.0F, 0.375F), () -> biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.VISCERAL_HEAP, Biomes.WARPED_FOREST))),
                Pair.of(TBClimate.parameters(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, getUniqueness(), 0.0F, 0.175F), () -> biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.ERUPTING_INFERNO, Biomes.BASALT_DELTAS)))));
    });

    private static float getUniqueness()
    {
        return BiomeProviderUtils.getUniquenessMidPoint(BiomeProviders.getIndex(BOPBiomeProvider.LOCATION));
    }
}
