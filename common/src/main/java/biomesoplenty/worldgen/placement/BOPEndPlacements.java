/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.placement;

import biomesoplenty.util.worldgen.BOPPlacementUtils;
import biomesoplenty.worldgen.feature.BOPEndFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class BOPEndPlacements
{
    public static final ResourceKey<PlacedFeature> ANOMALY = BOPPlacementUtils.createKey("anomaly");
    public static final ResourceKey<PlacedFeature> BARNACLES = BOPPlacementUtils.createKey("barnacles");
    public static final ResourceKey<PlacedFeature> DEAD_CORAL = BOPPlacementUtils.createKey("dead_coral");
    public static final ResourceKey<PlacedFeature> DEAD_CORAL_PATCH = BOPPlacementUtils.createKey("dead_coral_patch");
    public static final ResourceKey<PlacedFeature> ENDERPHYTE_BONEMEAL = BOPPlacementUtils.createKey("enderphyte_bonemeal");
    public static final ResourceKey<PlacedFeature> FLOWER_END_WILDS = BOPPlacementUtils.createKey("flower_end_wilds");
    public static final ResourceKey<PlacedFeature> JAGGED_SANDSTONE = BOPPlacementUtils.createKey("jagged_sandstone");
    public static final ResourceKey<PlacedFeature> LIQUID_NULL_LAKE = BOPPlacementUtils.createKey("liquid_null_lake");
    public static final ResourceKey<PlacedFeature> LIQUID_NULL_SPRING = BOPPlacementUtils.createKey("liquid_null_spring");
    public static final ResourceKey<PlacedFeature> LUMALOOP = BOPPlacementUtils.createKey("lumaloop");
    public static final ResourceKey<PlacedFeature> MONOLITH = BOPPlacementUtils.createKey("monolith");
    public static final ResourceKey<PlacedFeature> NULL_PLANT_BONEMEAL = BOPPlacementUtils.createKey("null_plant_bonemeal");
    public static final ResourceKey<PlacedFeature> PATCH_ENDERPHYTES = BOPPlacementUtils.createKey("patch_enderphytes");
    public static final ResourceKey<PlacedFeature> PATCH_NULL_PLANTS = BOPPlacementUtils.createKey("patch_null_plants");
    public static final ResourceKey<PlacedFeature> TIDEPOOL = BOPPlacementUtils.createKey("tidepool");
    public static final ResourceKey<PlacedFeature> TREES_END_CORRUPTION = BOPPlacementUtils.createKey("trees_end_corruption");
    public static final ResourceKey<PlacedFeature> TREES_END_WILDS = BOPPlacementUtils.createKey("trees_end_wilds");
    public static final ResourceKey<PlacedFeature> WISPJELLY = BOPPlacementUtils.createKey("wispjelly");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> ANOMALY = configuredFeatureGetter.getOrThrow(BOPEndFeatures.ANOMALY);
        final Holder<ConfiguredFeature<?, ?>> BARNACLES = configuredFeatureGetter.getOrThrow(BOPEndFeatures.BARNACLES);
        final Holder<ConfiguredFeature<?, ?>> DEAD_CORAL = configuredFeatureGetter.getOrThrow(BOPEndFeatures.DEAD_CORAL);
        final Holder<ConfiguredFeature<?, ?>> DEAD_CORAL_PATCH = configuredFeatureGetter.getOrThrow(BOPEndFeatures.DEAD_CORAL_PATCH);
        final Holder<ConfiguredFeature<?, ?>> ENDERPHYTE_BONEMEAL = configuredFeatureGetter.getOrThrow(BOPEndFeatures.ENDERPHYTE_BONEMEAL);
        final Holder<ConfiguredFeature<?, ?>> FLOWER_END_WILDS = configuredFeatureGetter.getOrThrow(BOPEndFeatures.FLOWER_END_WILDS);
        final Holder<ConfiguredFeature<?, ?>> JAGGED_SANDSTONE = configuredFeatureGetter.getOrThrow(BOPEndFeatures.JAGGED_SANDSTONE);
        final Holder<ConfiguredFeature<?, ?>> LIQUID_NULL_LAKE = configuredFeatureGetter.getOrThrow(BOPEndFeatures.LIQUID_NULL_LAKE);
        final Holder<ConfiguredFeature<?, ?>> LIQUID_NULL_SPRING = configuredFeatureGetter.getOrThrow(BOPEndFeatures.LIQUID_NULL_SPRING);
        final Holder<ConfiguredFeature<?, ?>> LUMALOOP = configuredFeatureGetter.getOrThrow(BOPEndFeatures.LUMALOOP);
        final Holder<ConfiguredFeature<?, ?>> MONOLITH = configuredFeatureGetter.getOrThrow(BOPEndFeatures.MONOLITH);
        final Holder<ConfiguredFeature<?, ?>> NULL_PLANT_BONEMEAL = configuredFeatureGetter.getOrThrow(BOPEndFeatures.NULL_PLANT_BONEMEAL);
        final Holder<ConfiguredFeature<?, ?>> PATCH_ENDERPHYTES = configuredFeatureGetter.getOrThrow(BOPEndFeatures.PATCH_ENDERPHYTES);
        final Holder<ConfiguredFeature<?, ?>> PATCH_NULL_PLANTS = configuredFeatureGetter.getOrThrow(BOPEndFeatures.PATCH_NULL_PLANTS);
        final Holder<ConfiguredFeature<?, ?>> TIDEPOOL = configuredFeatureGetter.getOrThrow(BOPEndFeatures.TIDEPOOL);
        final Holder<ConfiguredFeature<?, ?>> TREES_END_CORRUPTION = configuredFeatureGetter.getOrThrow(BOPEndFeatures.TREES_END_CORRUPTION);
        final Holder<ConfiguredFeature<?, ?>> TREES_END_WILDS = configuredFeatureGetter.getOrThrow(BOPEndFeatures.TREES_END_WILDS);
        final Holder<ConfiguredFeature<?, ?>> WISPJELLY = configuredFeatureGetter.getOrThrow(BOPEndFeatures.WISPJELLY);

        register(context, BOPEndPlacements.ANOMALY, ANOMALY, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.BARNACLES, BARNACLES, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.DEAD_CORAL, DEAD_CORAL, List.of(CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPEndPlacements.DEAD_CORAL_PATCH, DEAD_CORAL_PATCH, List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.ENDERPHYTE_BONEMEAL, ENDERPHYTE_BONEMEAL, PlacementUtils.isEmpty());
        register(context, BOPEndPlacements.FLOWER_END_WILDS, FLOWER_END_WILDS, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.JAGGED_SANDSTONE, JAGGED_SANDSTONE, List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.LIQUID_NULL_LAKE, LIQUID_NULL_LAKE, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.LIQUID_NULL_SPRING, LIQUID_NULL_SPRING, List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPEndPlacements.LUMALOOP, LUMALOOP, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.MONOLITH, MONOLITH, List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.NULL_PLANT_BONEMEAL, NULL_PLANT_BONEMEAL, PlacementUtils.isEmpty());
        register(context, BOPEndPlacements.PATCH_ENDERPHYTES, PATCH_ENDERPHYTES, VegetationPlacements.worldSurfaceSquaredWithCount(5));
        register(context, BOPEndPlacements.PATCH_NULL_PLANTS, PATCH_NULL_PLANTS, List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, BOPEndPlacements.TIDEPOOL, TIDEPOOL, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        register(context, BOPEndPlacements.TREES_END_CORRUPTION, TREES_END_CORRUPTION, treePlacement(PlacementUtils.countExtra(0, 0.2F, 1)));
        register(context, BOPEndPlacements.TREES_END_WILDS, TREES_END_WILDS, treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
        register(context, BOPEndPlacements.WISPJELLY, WISPJELLY, List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
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
