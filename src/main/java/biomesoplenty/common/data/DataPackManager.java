/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.data;

import biomesoplenty.api.biome.BiomeProviders;
import biomesoplenty.common.worldgen.*;
import biomesoplenty.common.worldgen.surface.DifferentialSurfaceRuleSource;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataPackManager
{
    private static final Codec<WorldGenSettings> DIRECT_WGS_CODEC = RecordCodecBuilder.<WorldGenSettings>create((p_64626_) -> {
        return p_64626_.group(Codec.LONG.fieldOf("seed").stable().forGetter(WorldGenSettings::seed), Codec.BOOL.fieldOf("generate_features").orElse(true).stable().forGetter(WorldGenSettings::generateFeatures), Codec.BOOL.fieldOf("bonus_chest").orElse(false).stable().forGetter(WorldGenSettings::generateBonusChest), MappedRegistry.directCodec(Registry.LEVEL_STEM_REGISTRY, Lifecycle.stable(), LevelStem.CODEC).xmap(LevelStem::sortMap, Function.identity()).fieldOf("dimensions").forGetter(WorldGenSettings::dimensions), Codec.STRING.optionalFieldOf("legacy_custom_options").stable().forGetter((p_158959_) -> {
            return p_158959_.legacyCustomOptions;
        })).apply(p_64626_, p_64626_.stable(WorldGenSettings::new));
    }).comapFlatMap(WorldGenSettings::guardExperimental, Function.identity());

    public static WorldGenSettings mergeWorldGenSettings(RegistryAccess registryAccess, WorldGenSettings currentSettings, WorldGenSettings newSettings)
    {
        // Do not merge if the chunk generator isn't ours or the new settings don't use a MultiNoiseBiomeSource
        if (!(currentSettings.overworld() instanceof BOPNoiseBasedChunkGenerator) || !(newSettings.overworld().getBiomeSource() instanceof MultiNoiseBiomeSource) || currentSettings.equals(newSettings))
            return newSettings;

        MultiNoiseBiomeSource biomeSource = (MultiNoiseBiomeSource)newSettings.overworld().getBiomeSource();
        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);

        // Register biome providers from the parameters
        BiomeProviders.register("datapack", 10);

        ImmutableList<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>> externalParameters = biomeSource.parameters.values().stream().map(pair -> {
            Climate.ParameterPoint point = pair.getFirst();
            Supplier<Biome> biomeSupplier = pair.getSecond();
            Biome biome = biomeSupplier.get();
            Climate.Parameter uniqueness = BiomeProviders.getUniquenessParameter(BiomeProviders.getIndex("datapack"));

            BOPClimate.ParameterPoint bopPoint = BOPClimate.parameters(point.temperature(), point.humidity(), point.continentalness(), point.erosion(), point.depth(), point.weirdness(), uniqueness, Climate.Parameter.span(-1.0F, 0.35F), BOPClimate.unquantizeCoord(point.offset()));
            return Pair.of(bopPoint, biomeSupplier);
        }).collect(ImmutableList.toImmutableList());

        BOPNoiseBasedChunkGenerator currentOverworldChunkGenerator = (BOPNoiseBasedChunkGenerator)currentSettings.overworld();
        NoiseGeneratorSettings currentNoiseGeneratorSettings = currentOverworldChunkGenerator.settings.get();
        NoiseBasedChunkGenerator newOverworldChunkGenerator = (NoiseBasedChunkGenerator)newSettings.overworld();
        NoiseGeneratorSettings newNoiseGeneratorSettings = newOverworldChunkGenerator.settings.get();

        SurfaceRules.RuleSource mergedOverworldRuleSource = new DifferentialSurfaceRuleSource(BOPSurfaceRuleData.overworld(), ImmutableList.of(newNoiseGeneratorSettings.surfaceRule()));
        NoiseGeneratorSettings mergedNoiseGeneratorSettings = BOPNoiseGeneratorSettings.overworld(newNoiseGeneratorSettings.noiseSettings(), mergedOverworldRuleSource);
        ChunkGenerator mergedChunkGenerator = BOPWorldType.chunkGenerator(registryAccess, currentSettings.seed(), () -> mergedNoiseGeneratorSettings, externalParameters);

        BiomesOPlenty.logger.info("Merged generation settings with datapack");
        return BOPWorldType.settings(registryAccess, currentSettings.seed(), currentSettings.generateFeatures(), currentSettings.generateBonusChest(), mergedChunkGenerator);
    }

    public static <T> Pair<WorldGenSettings, Lifecycle> readWorldGenSettings(Dynamic<T> dynamicWorldGenSettings, DataFixer dataFixer, int version)
    {
        DataResult<WorldGenSettings> directWorldGenSettingsResult = DIRECT_WGS_CODEC.parse(dynamicWorldGenSettings);
        DataResult<WorldGenSettings> dataPackedWorldGenSettingsResult = WorldGenSettings.CODEC.parse(dynamicWorldGenSettings);
        Optional<WorldGenSettings> directWorldGenSettingsOptional = directWorldGenSettingsResult.result();
        Optional<WorldGenSettings> dataPackedWorldGenSettingsOptional = dataPackedWorldGenSettingsResult.resultOrPartial(Util.prefix("WorldGenSettings: ", BiomesOPlenty.logger::error));

        BiomesOPlenty.logger.info(directWorldGenSettingsOptional.isPresent() && directWorldGenSettingsOptional.get().overworld() instanceof BOPNoiseBasedChunkGenerator);
        BiomesOPlenty.logger.info(dataPackedWorldGenSettingsOptional.isEmpty());
        BiomesOPlenty.logger.info(dataPackedWorldGenSettingsOptional.get().overworld() instanceof BOPNoiseBasedChunkGenerator);

        if (directWorldGenSettingsOptional.isPresent() && directWorldGenSettingsOptional.get().overworld() instanceof BOPNoiseBasedChunkGenerator && (dataPackedWorldGenSettingsOptional.isEmpty() || !(dataPackedWorldGenSettingsOptional.get().overworld() instanceof BOPNoiseBasedChunkGenerator)))
        {
            BiomesOPlenty.logger.info("Using merged world generation settings for datapack");

            // Register the datapack provider if we're dealing with a datapack
            if (dataPackedWorldGenSettingsOptional.isPresent())
            {
                BiomeProviders.register("datapack", 10);
            }

            return Pair.of(directWorldGenSettingsOptional.get(), directWorldGenSettingsResult.lifecycle());
        }
        else
        {
            return Pair.of(dataPackedWorldGenSettingsOptional.orElseGet(() -> {
                RegistryAccess registryaccess = RegistryAccess.RegistryHolder.readFromDisk(dynamicWorldGenSettings);
                return WorldGenSettings.makeDefault(registryaccess);
            }), dataPackedWorldGenSettingsResult.lifecycle());
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event)
    {
        BiomeProviders.reset();
    }
}
