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
        Set<ResourceKey<Biome>> iglooBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.COLD_DESERT).add(BOPBiomes.SNOWY_CONIFEROUS_FOREST).add(BOPBiomes.SNOWY_FIR_CLEARING).add(BOPBiomes.SNOWY_MAPLE_FOREST).build();
        Set<ResourceKey<Biome>> jungleTempleBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.RAINFOREST).add(BOPBiomes.FLOODPLAIN).build();
        Set<ResourceKey<Biome>> swampHutBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.BOG).add(BOPBiomes.BAYOU).add(BOPBiomes.MARSH).add(BOPBiomes.WETLAND).build();
        Set<ResourceKey<Biome>> woodlandMansionBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.CONIFEROUS_FOREST).add(BOPBiomes.DEAD_FOREST).add(BOPBiomes.MYSTIC_GROVE).add(BOPBiomes.OMINOUS_WOODS).add(BOPBiomes.SEASONAL_FOREST).add(BOPBiomes.WOODLAND).build();
        Set<ResourceKey<Biome>> beachStructureBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.DUNE_BEACH).build();

        Set<ResourceKey<Biome>> plainsVillageBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.GRASSLAND).add(BOPBiomes.LAVENDER_FIELD).add(BOPBiomes.MEDITERRANEAN_FOREST).add(BOPBiomes.PRAIRIE).add(BOPBiomes.SHRUBLAND).add(BOPBiomes.WOODLAND).build();
        Set<ResourceKey<Biome>> desertVillageBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.LUSH_DESERT).build();
        Set<ResourceKey<Biome>> savannaVillageBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.DRYLAND).build();
        Set<ResourceKey<Biome>> snowyVillageBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.COLD_DESERT).add(BOPBiomes.SNOWY_CONIFEROUS_FOREST).build();
        Set<ResourceKey<Biome>> taigaVillageBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.CONIFEROUS_FOREST).add(BOPBiomes.DEAD_FOREST).add(BOPBiomes.FIELD).add(BOPBiomes.TUNDRA).build();

        Set<ResourceKey<Biome>> ruinedPortalDesertBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.COLD_DESERT).add(BOPBiomes.DRYLAND).add(BOPBiomes.DRY_BONEYARD).add(BOPBiomes.DUNE_BEACH).add(BOPBiomes.LUSH_DESERT).add(BOPBiomes.LUSH_SAVANNA).add(BOPBiomes.VOLCANIC_PLAINS).add(BOPBiomes.WASTELAND).build();
        Set<ResourceKey<Biome>> ruinedPortalJungleBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.FUNGAL_JUNGLE).add(BOPBiomes.RAINFOREST).add(BOPBiomes.RAINFOREST_CLIFFS).add(BOPBiomes.TROPICS).build();
        Set<ResourceKey<Biome>> ruinedPortalMountainBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.CRAG).add(BOPBiomes.HIGHLAND).add(BOPBiomes.HIGHLAND_MOOR).add(BOPBiomes.JADE_CLIFFS).add(BOPBiomes.VOLCANO).build();
        Set<ResourceKey<Biome>> ruinedPortalStandardBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.BAMBOO_GROVE).add(BOPBiomes.BOREAL_FOREST).add(BOPBiomes.CHERRY_BLOSSOM_GROVE).add(BOPBiomes.CONIFEROUS_FOREST).add(BOPBiomes.DEAD_FOREST).add(BOPBiomes.DENSE_WOODLAND).add(BOPBiomes.FIELD).add(BOPBiomes.FIR_CLEARING).add(BOPBiomes.FORESTED_FIELD).add(BOPBiomes.GRASSLAND).add(BOPBiomes.PASTURE).add(BOPBiomes.LAVENDER_FIELD).add(BOPBiomes.LAVENDER_FOREST).add(BOPBiomes.MEDITERRANEAN_FOREST).add(BOPBiomes.MEDITERRANEAN_LAKES).add(BOPBiomes.MUSKEG).add(BOPBiomes.MYSTIC_GROVE).add(BOPBiomes.OLD_GROWTH_DEAD_FOREST).add(BOPBiomes.OMINOUS_WOODS).add(BOPBiomes.ORCHARD).add(BOPBiomes.PRAIRIE).add(BOPBiomes.REDWOOD_FOREST).add(BOPBiomes.ROCKY_SHRUBLAND).add(BOPBiomes.SCRUBLAND).add(BOPBiomes.SHRUBLAND).add(BOPBiomes.SEASONAL_FOREST).add(BOPBiomes.PUMPKIN_PATCH).add(BOPBiomes.SNOWY_CONIFEROUS_FOREST).add(BOPBiomes.SNOWY_FIR_CLEARING).add(BOPBiomes.SNOWY_MAPLE_FOREST).add(BOPBiomes.TUNDRA).add(BOPBiomes.WOODED_SCRUBLAND).add(BOPBiomes.WOODLAND).build();
        Set<ResourceKey<Biome>> ruinedPortalSwampBiomes = ImmutableSet.<ResourceKey<Biome>>builder().add(BOPBiomes.BAYOU).add(BOPBiomes.BOG).add(BOPBiomes.MARSH).add(BOPBiomes.FLOODPLAIN).add(BOPBiomes.WETLAND).build();

        Set<ResourceKey<Biome>> mineshaftBiomes = ImmutableSet.copyOf(BOPBiomes.getOverworldBiomes().stream().filter((key -> key != BOPBiomes.ORIGIN_VALLEY)).toList());

        register(mapper, StructureFeatures.MINESHAFT, mineshaftBiomes);

        register(mapper, StructureFeatures.IGLOO, iglooBiomes);
        register(mapper, StructureFeatures.JUNGLE_TEMPLE, jungleTempleBiomes);
        register(mapper, StructureFeatures.SWAMP_HUT, swampHutBiomes);
        register(mapper, StructureFeatures.WOODLAND_MANSION, woodlandMansionBiomes);
        register(mapper, StructureFeatures.BURIED_TREASURE, beachStructureBiomes);
        register(mapper, StructureFeatures.SHIPWRECK_BEACHED, beachStructureBiomes);

        register(mapper, StructureFeatures.VILLAGE_PLAINS, plainsVillageBiomes);
        register(mapper, StructureFeatures.VILLAGE_DESERT, desertVillageBiomes);
        register(mapper, StructureFeatures.VILLAGE_SAVANNA, savannaVillageBiomes);
        register(mapper, StructureFeatures.VILLAGE_SNOWY, snowyVillageBiomes);
        register(mapper, StructureFeatures.VILLAGE_TAIGA, taigaVillageBiomes);

        register(mapper, StructureFeatures.PILLAGER_OUTPOST, plainsVillageBiomes);
        register(mapper, StructureFeatures.PILLAGER_OUTPOST, desertVillageBiomes);
        register(mapper, StructureFeatures.PILLAGER_OUTPOST, savannaVillageBiomes);
        register(mapper, StructureFeatures.PILLAGER_OUTPOST, snowyVillageBiomes);
        register(mapper, StructureFeatures.PILLAGER_OUTPOST, taigaVillageBiomes);

        register(mapper, StructureFeatures.RUINED_PORTAL_DESERT, ruinedPortalDesertBiomes);
        register(mapper, StructureFeatures.RUINED_PORTAL_JUNGLE, ruinedPortalJungleBiomes);
        register(mapper, StructureFeatures.RUINED_PORTAL_MOUNTAIN, ruinedPortalMountainBiomes);
        register(mapper, StructureFeatures.RUINED_PORTAL_STANDARD, ruinedPortalStandardBiomes);
        register(mapper, StructureFeatures.RUINED_PORTAL_SWAMP, ruinedPortalSwampBiomes);
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
