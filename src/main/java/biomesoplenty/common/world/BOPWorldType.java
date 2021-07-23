/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world;


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
import net.minecraftforge.common.world.ForgeWorldType;

public class BOPWorldType extends ForgeWorldType
{
    public BOPWorldType()
    {
        super(null);
    }

    @Override
    public ChunkGenerator createChunkGenerator(Registry<Biome> biomeRegistry, Registry<NoiseGeneratorSettings> dimensionSettingsRegistry, long seed, String generatorSettings)
    {
        return new NoiseBasedChunkGenerator(new BOPBiomeProvider(seed, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(NoiseGeneratorSettings.OVERWORLD));
    }

    @Override
    public WorldGenSettings createSettings(RegistryAccess dynamicRegistries, long seed, boolean generateFeatures, boolean generateBonusChest, String generatorSettings)
    {
        Registry<Biome> biomeRegistry = dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY);
        Registry<NoiseGeneratorSettings> dimensionSettingsRegistry = dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
        Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);

        return new WorldGenSettings(seed, generateFeatures, generateBonusChest, WorldGenSettings.withOverworld(dimensionTypeRegistry, bopDimensions(dimensionTypeRegistry, biomeRegistry, dimensionSettingsRegistry, seed), createChunkGenerator(biomeRegistry, dimensionSettingsRegistry, seed, null)));
    }

    private static ChunkGenerator bopNetherGenerator(Registry<Biome> biomeRegistry, Registry<NoiseGeneratorSettings> dimensionSettingsRegistry, long seed)
    {
        return new NoiseBasedChunkGenerator(new BOPNetherBiomeProvider(seed, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(NoiseGeneratorSettings.NETHER));
    }

    public static MappedRegistry<LevelStem> bopDimensions(Registry<DimensionType> dimensionTypes, Registry<Biome> biomeRegistry, Registry<NoiseGeneratorSettings> dimensionSettingsRegistry, long seed)
    {
        MappedRegistry<LevelStem> registry = DimensionType.defaultDimensions(dimensionTypes, biomeRegistry, dimensionSettingsRegistry, seed);

        if (ModConfig.GenerationConfig.useBopNether.get())
        {
            registry.register(LevelStem.NETHER, new LevelStem(() -> dimensionTypes.getOrThrow(DimensionType.NETHER_LOCATION), bopNetherGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
        }

        return registry;
    }
}