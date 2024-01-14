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
    public static final ResourceKey<PlacedFeature> ENDERPHYTE_BONEMEAL = BOPPlacementUtils.createKey("enderphyte_bonemeal");
    public static final ResourceKey<PlacedFeature> FLOWER_END_WILDS = BOPPlacementUtils.createKey("flower_end_wilds");
    public static final ResourceKey<PlacedFeature> NULL_LAKE = BOPPlacementUtils.createKey("null_lake");
    public static final ResourceKey<PlacedFeature> NULL_TREES = BOPPlacementUtils.createKey("null_trees");
    public static final ResourceKey<PlacedFeature> PATCH_ENDERPHYTES = BOPPlacementUtils.createKey("patch_enderphytes");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> ANOMALY = configuredFeatureGetter.getOrThrow(BOPEndFeatures.ANOMALY);
        final Holder<ConfiguredFeature<?, ?>> BARNACLES = configuredFeatureGetter.getOrThrow(BOPEndFeatures.BARNACLES);
        final Holder<ConfiguredFeature<?, ?>> DEAD_CORAL = configuredFeatureGetter.getOrThrow(BOPEndFeatures.DEAD_CORAL);
        final Holder<ConfiguredFeature<?, ?>> ENDERPHYTE_BONEMEAL = configuredFeatureGetter.getOrThrow(BOPEndFeatures.ENDERPHYTE_BONEMEAL);
        final Holder<ConfiguredFeature<?, ?>> FLOWER_END_WILDS = configuredFeatureGetter.getOrThrow(BOPEndFeatures.FLOWER_END_WILDS);
        final Holder<ConfiguredFeature<?, ?>> NULL_LAKE = configuredFeatureGetter.getOrThrow(BOPEndFeatures.NULL_LAKE);
        final Holder<ConfiguredFeature<?, ?>> NULL_TREES = configuredFeatureGetter.getOrThrow(BOPEndFeatures.NULL_TREES);
        final Holder<ConfiguredFeature<?, ?>> PATCH_ENDERPHYTES = configuredFeatureGetter.getOrThrow(BOPEndFeatures.PATCH_ENDERPHYTES);

        register(context, BOPEndPlacements.ANOMALY, ANOMALY, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.BARNACLES, BARNACLES, List.of(CountPlacement.of(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.DEAD_CORAL, DEAD_CORAL, NoiseBasedCountPlacement.of(20, 400.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        register(context, BOPEndPlacements.ENDERPHYTE_BONEMEAL, ENDERPHYTE_BONEMEAL, PlacementUtils.isEmpty());
        register(context, BOPEndPlacements.FLOWER_END_WILDS, FLOWER_END_WILDS, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.NULL_LAKE, NULL_LAKE, List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, BOPEndPlacements.NULL_TREES, NULL_TREES, treePlacement(PlacementUtils.countExtra(0, 0.2F, 1)));
        register(context, BOPEndPlacements.PATCH_ENDERPHYTES, PATCH_ENDERPHYTES, VegetationPlacements.worldSurfaceSquaredWithCount(5));
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
