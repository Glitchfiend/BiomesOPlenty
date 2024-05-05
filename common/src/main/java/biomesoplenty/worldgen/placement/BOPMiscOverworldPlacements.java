/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.placement;

import biomesoplenty.worldgen.feature.BOPMiscOverworldFeatures;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class BOPMiscOverworldPlacements
{
    public static final ResourceKey<PlacedFeature> CRAG_MOSS = BOPPlacementUtils.createKey("crag_moss");
    public static final ResourceKey<PlacedFeature> CRAG_SPLATTER = BOPPlacementUtils.createKey("crag_splatter");
    public static final ResourceKey<PlacedFeature> DISK_BLACK_SAND = BOPPlacementUtils.createKey("disk_black_sand");
    public static final ResourceKey<PlacedFeature> DISK_CALCITE = BOPPlacementUtils.createKey("disk_calcite");
    public static final ResourceKey<PlacedFeature> DISK_GRAVEL_EXTRA = BOPPlacementUtils.createKey("disk_gravel_extra");
    public static final ResourceKey<PlacedFeature> DISK_ORANGE_SAND = BOPPlacementUtils.createKey("disk_orange_sand");
    public static final ResourceKey<PlacedFeature> DISK_WHITE_SAND = BOPPlacementUtils.createKey("disk_white_sand");
    public static final ResourceKey<PlacedFeature> DISK_WHITE_SAND_EXTRA = BOPPlacementUtils.createKey("disk_white_sand_extra");
    public static final ResourceKey<PlacedFeature> DISK_WHITE_SANDSTONE = BOPPlacementUtils.createKey("disk_white_sandstone");

    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_GRAVEL = BOPPlacementUtils.createKey("disk_hot_spring_gravel");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_CALCITE = BOPPlacementUtils.createKey("disk_hot_spring_calcite");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_BASALT = BOPPlacementUtils.createKey("disk_hot_spring_basalt");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_PACKED_MUD = BOPPlacementUtils.createKey("disk_hot_spring_packed_mud");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_THERMAL_CALCITE = BOPPlacementUtils.createKey("disk_hot_spring_thermal_calcite");

    public static final ResourceKey<PlacedFeature> DISK_VOLCANO_SMOOTH_BASALT = BOPPlacementUtils.createKey("disk_volcano_smooth_basalt");
    public static final ResourceKey<PlacedFeature> DISK_VOLCANO_BLACK_SANDSTONE = BOPPlacementUtils.createKey("disk_volcano_black_sandstone");
    public static final ResourceKey<PlacedFeature> DISK_VOLCANO_MAGMA = BOPPlacementUtils.createKey("disk_volcano_magma");

    public static final ResourceKey<PlacedFeature> DISK_MUD = BOPPlacementUtils.createKey("disk_mud");
    public static final ResourceKey<PlacedFeature> MOSSY_BLACK_SAND_SPLATTER = BOPPlacementUtils.createKey("mossy_black_sand_splatter");
    public static final ResourceKey<PlacedFeature> MUD_SPLATTER = BOPPlacementUtils.createKey("mud_splatter");
    public static final ResourceKey<PlacedFeature> LAKE_WATER = BOPPlacementUtils.createKey("lake_water");
    public static final ResourceKey<PlacedFeature> LAKE_WATER_EXTRA = BOPPlacementUtils.createKey("lake_water_extra");
    public static final ResourceKey<PlacedFeature> LAKE_WATER_MARSH = BOPPlacementUtils.createKey("lake_water_marsh");
    public static final ResourceKey<PlacedFeature> LAKE_HOT_SPRING = BOPPlacementUtils.createKey("lake_hot_spring");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_SURFACE_EXTRA = BOPPlacementUtils.createKey("lake_lava_surface_extra");
    public static final ResourceKey<PlacedFeature> SPRING_LAVA_VOLCANO = BOPPlacementUtils.createKey("spring_lava_volcano");
    public static final ResourceKey<PlacedFeature> SPRING_WATER_EXTRA = BOPPlacementUtils.createKey("spring_water_extra");
    public static final ResourceKey<PlacedFeature> ORIGIN_GRAVEL_CLIFFS = BOPPlacementUtils.createKey("origin_gravel_cliffs");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> CRAG_MOSS = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.CRAG_MOSS);
        final Holder<ConfiguredFeature<?, ?>> CRAG_SPLATTER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.CRAG_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> DISK_BLACK_SAND = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_BLACK_SAND);
        final Holder<ConfiguredFeature<?, ?>> DISK_CALCITE = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_CALCITE);
        final Holder<ConfiguredFeature<?, ?>> DISK_GRAVEL_EXTRA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA);

        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_GRAVEL = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_GRAVEL);
        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_CALCITE = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_CALCITE);
        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_BASALT = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_BASALT);
        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_PACKED_MUD = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_PACKED_MUD);
        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_THERMAL_CALCITE = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_THERMAL_CALCITE);

        final Holder<ConfiguredFeature<?, ?>> DISK_VOLCANO_SMOOTH_BASALT = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_VOLCANO_SMOOTH_BASALT);
        final Holder<ConfiguredFeature<?, ?>> DISK_VOLCANO_BLACK_SANDSTONE = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_VOLCANO_BLACK_SANDSTONE);
        final Holder<ConfiguredFeature<?, ?>> DISK_VOLCANO_MAGMA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_VOLCANO_MAGMA);

        final Holder<ConfiguredFeature<?, ?>> DISK_MUD = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_MUD);
        final Holder<ConfiguredFeature<?, ?>> DISK_ORANGE_SAND = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_ORANGE_SAND);
        final Holder<ConfiguredFeature<?, ?>> DISK_WHITE_SAND = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_WHITE_SAND);
        final Holder<ConfiguredFeature<?, ?>> DISK_WHITE_SAND_EXTRA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA);
        final Holder<ConfiguredFeature<?, ?>> DISK_WHITE_SANDSTONE = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_WHITE_SANDSTONE);
        final Holder<ConfiguredFeature<?, ?>> MOSSY_BLACK_SAND_SPLATTER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.MOSSY_BLACK_SAND_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> MUD_SPLATTER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.MUD_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> LAKE_WATER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.WATER_LAKE);
        final Holder<ConfiguredFeature<?, ?>> LAKE_HOT_SPRING = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.HOT_SPRING_LAKE);
        final Holder<ConfiguredFeature<?, ?>> LAKE_LAVA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.LAVA_LAKE_VOLCANO);
        final Holder<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANO = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO);
        final Holder<ConfiguredFeature<?, ?>> SPRING_WATER_EXTRA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.SPRING_WATER_EXTRA);
        final Holder<ConfiguredFeature<?, ?>> ORIGIN_GRAVEL_CLIFFS = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.ORIGIN_GRAVEL_CLIFFS);

        register(context, BOPMiscOverworldPlacements.CRAG_MOSS, CRAG_MOSS, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.CRAG_SPLATTER, CRAG_SPLATTER, List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_BLACK_SAND, DISK_BLACK_SAND, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_CALCITE, DISK_CALCITE, List.of(CountPlacement.of(14), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_GRAVEL_EXTRA, DISK_GRAVEL_EXTRA, List.of(CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));

        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_GRAVEL, DISK_HOT_SPRING_GRAVEL, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_CALCITE, DISK_HOT_SPRING_CALCITE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_BASALT, DISK_HOT_SPRING_BASALT, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_PACKED_MUD, DISK_HOT_SPRING_PACKED_MUD, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_THERMAL_CALCITE, DISK_HOT_SPRING_THERMAL_CALCITE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));

        register(context, BOPMiscOverworldPlacements.DISK_VOLCANO_SMOOTH_BASALT, DISK_VOLCANO_SMOOTH_BASALT, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.LAVA)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_VOLCANO_BLACK_SANDSTONE, DISK_VOLCANO_BLACK_SANDSTONE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.LAVA)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_VOLCANO_MAGMA, DISK_VOLCANO_MAGMA, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.LAVA)), BiomeFilter.biome()));

        register(context, BOPMiscOverworldPlacements.DISK_MUD, DISK_MUD, List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_ORANGE_SAND, DISK_ORANGE_SAND, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_WHITE_SAND, DISK_WHITE_SAND, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_WHITE_SAND_EXTRA, DISK_WHITE_SAND_EXTRA, List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_WHITE_SANDSTONE, DISK_WHITE_SANDSTONE, List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.MOSSY_BLACK_SAND_SPLATTER, MOSSY_BLACK_SAND_SPLATTER, List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.MUD_SPLATTER, MUD_SPLATTER, List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.LAKE_WATER, LAKE_WATER, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_WATER_EXTRA, LAKE_WATER, CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_WATER_MARSH, LAKE_WATER, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_HOT_SPRING, LAKE_HOT_SPRING, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_LAVA_SURFACE_EXTRA, LAKE_LAVA, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO, SPRING_LAVA_VOLCANO, List.of(CountPlacement.of(96), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(96), VerticalAnchor.absolute(192)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA, SPRING_WATER_EXTRA, List.of(CountPlacement.of(96), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(72), VerticalAnchor.absolute(192)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.ORIGIN_GRAVEL_CLIFFS, ORIGIN_GRAVEL_CLIFFS, BiomeFilter.biome());
    }

    protected static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers)
    {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers)
    {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
