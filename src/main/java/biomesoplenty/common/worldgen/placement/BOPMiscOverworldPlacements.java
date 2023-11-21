/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPMiscOverworldFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.createKey;

public class BOPMiscOverworldPlacements
{
    public static final ResourceKey<PlacedFeature> CRAG_MOSS = createKey("crag_moss");
    public static final ResourceKey<PlacedFeature> CRAG_SPLATTER = createKey("crag_splatter");
    public static final ResourceKey<PlacedFeature> DISK_BLACK_SAND = createKey("disk_black_sand");
    public static final ResourceKey<PlacedFeature> DISK_CALCITE = createKey("disk_calcite");
    public static final ResourceKey<PlacedFeature> DISK_GRAVEL_EXTRA = createKey("disk_gravel_extra");
    public static final ResourceKey<PlacedFeature> DISK_ORANGE_SAND = createKey("disk_orange_sand");
    public static final ResourceKey<PlacedFeature> DISK_WHITE_SAND = createKey("disk_white_sand");
    public static final ResourceKey<PlacedFeature> DISK_WHITE_SAND_EXTRA = createKey("disk_white_sand_extra");

    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_GRAVEL = createKey("disk_hot_spring_gravel");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_CALCITE = createKey("disk_hot_spring_calcite");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_BASALT = createKey("disk_hot_spring_basalt");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_ORANGE = createKey("disk_hot_spring_orange");
    public static final ResourceKey<PlacedFeature> DISK_HOT_SPRING_YELLOW = createKey("disk_hot_spring_yellow");

    public static final ResourceKey<PlacedFeature> DISK_MUD = createKey("disk_mud");
    public static final ResourceKey<PlacedFeature> MOSSY_BLACK_SAND_SPLATTER = createKey("mossy_black_sand_splatter");
    public static final ResourceKey<PlacedFeature> MUD_SPLATTER = createKey("mud_splatter");
    public static final ResourceKey<PlacedFeature> LAKE_WATER = createKey("lake_water");
    public static final ResourceKey<PlacedFeature> LAKE_WATER_EXTRA = createKey("lake_water_extra");
    public static final ResourceKey<PlacedFeature> LAKE_WATER_MARSH = createKey("lake_water_marsh");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_SURFACE_EXTRA = createKey("lake_lava_surface_extra");
    public static final ResourceKey<PlacedFeature> SPRING_LAVA_VOLCANO = createKey("spring_lava_volcano");
    public static final ResourceKey<PlacedFeature> SPRING_WATER_EXTRA = createKey("spring_water_extra");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
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
        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_ORANGE = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_ORANGE);
        final Holder<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_YELLOW = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_HOT_SPRING_YELLOW);

        final Holder<ConfiguredFeature<?, ?>> DISK_MUD = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_MUD);
        final Holder<ConfiguredFeature<?, ?>> DISK_ORANGE_SAND = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_ORANGE_SAND);
        final Holder<ConfiguredFeature<?, ?>> DISK_WHITE_SAND = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_WHITE_SAND);
        final Holder<ConfiguredFeature<?, ?>> DISK_WHITE_SAND_EXTRA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA);
        final Holder<ConfiguredFeature<?, ?>> MOSSY_BLACK_SAND_SPLATTER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.MOSSY_BLACK_SAND_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> MUD_SPLATTER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.MUD_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> LAKE_WATER = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.WATER_LAKE);
        final Holder<ConfiguredFeature<?, ?>> LAKE_LAVA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.LAVA_LAKE_VOLCANO);
        final Holder<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANO = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO);
        final Holder<ConfiguredFeature<?, ?>> SPRING_WATER_EXTRA = configuredFeatureGetter.getOrThrow(BOPMiscOverworldFeatures.SPRING_WATER_EXTRA);

        register(context, BOPMiscOverworldPlacements.CRAG_MOSS, CRAG_MOSS, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.CRAG_SPLATTER, CRAG_SPLATTER, List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_BLACK_SAND, DISK_BLACK_SAND, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_CALCITE, DISK_CALCITE, List.of(CountPlacement.of(14), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_GRAVEL_EXTRA, DISK_GRAVEL_EXTRA, List.of(CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));

        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_GRAVEL, DISK_HOT_SPRING_GRAVEL, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_CALCITE, DISK_HOT_SPRING_CALCITE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_BASALT, DISK_HOT_SPRING_BASALT, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_ORANGE, DISK_HOT_SPRING_ORANGE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_HOT_SPRING_YELLOW, DISK_HOT_SPRING_YELLOW, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));

        register(context, BOPMiscOverworldPlacements.DISK_MUD, DISK_MUD, List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_ORANGE_SAND, DISK_ORANGE_SAND, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_WHITE_SAND, DISK_WHITE_SAND, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.DISK_WHITE_SAND_EXTRA, DISK_WHITE_SAND_EXTRA, List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.MOSSY_BLACK_SAND_SPLATTER, MOSSY_BLACK_SAND_SPLATTER, List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.MUD_SPLATTER, MUD_SPLATTER, List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.LAKE_WATER, LAKE_WATER, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_WATER_EXTRA, LAKE_WATER, CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_WATER_MARSH, LAKE_WATER, CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.LAKE_LAVA_SURFACE_EXTRA, LAKE_LAVA, CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        register(context, BOPMiscOverworldPlacements.SPRING_LAVA_VOLCANO, SPRING_LAVA_VOLCANO, List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPMiscOverworldPlacements.SPRING_WATER_EXTRA, SPRING_WATER_EXTRA, List.of(CountPlacement.of(128), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)), BiomeFilter.biome()));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers)
    {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers)
    {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
