/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StrongholdConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

public class BOPStructureSettings extends StructureSettings
{
    private final ImmutableMap<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> configuredStructures;

    public BOPStructureSettings(Optional<StrongholdConfiguration> strongholdConfiguration, Map<StructureFeature<?>, StructureFeatureConfiguration> structureConfig)
    {
        super(strongholdConfiguration, structureConfig);

        HashMap<StructureFeature<?>, ImmutableMultimap.Builder<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureBiomes = new HashMap<>();
        StructureFeatures.registerStructures((configuredStructureFeature, biome) ->
        {
            structureBiomes.computeIfAbsent(configuredStructureFeature.feature, (feature) -> ImmutableMultimap.builder()).put(configuredStructureFeature, biome);
        });

        registerStructuresBOP((configuredStructureFeature, biome) ->
        {
            structureBiomes.computeIfAbsent(configuredStructureFeature.feature, (feature) -> ImmutableMultimap.builder()).put(configuredStructureFeature, biome);
        });
        this.configuredStructures = structureBiomes.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, (entry) -> entry.getValue().build()));
    }

    public BOPStructureSettings(boolean enableStrongholds)
    {
        this(enableStrongholds ? Optional.of(DEFAULT_STRONGHOLD) : Optional.empty(), Maps.newHashMap(DEFAULTS));
    }

    @Override
    public ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> structures(StructureFeature<?> structure)
    {
        return this.configuredStructures.getOrDefault(structure, ImmutableMultimap.of());
    }

    public static void registerStructuresBOP(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> mapper)
    {
        Set<ResourceKey<Biome>> iglooBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.SNOWY_CONIFEROUS_FOREST).add(BOPBiomes.SNOWY_FIR_CLEARING).add(BOPBiomes.SNOWY_MAPLE_FOREST).build();

        register(mapper, StructureFeatures.IGLOO, iglooBiomes);
        // TODO: Register structures here
    }

    private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> mapper, ConfiguredStructureFeature<?, ?> structure, Set<ResourceKey<Biome>> biomes)
    {
        biomes.forEach((p_194770_) -> {
            mapper.accept(structure, p_194770_);
        });
    }

    private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> mapper, ConfiguredStructureFeature<?, ?> structure, ResourceKey<Biome> biome)
    {
        mapper.accept(structure, biome);
    }
}
