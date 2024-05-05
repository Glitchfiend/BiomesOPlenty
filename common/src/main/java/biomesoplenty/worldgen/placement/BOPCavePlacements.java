/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.placement;

import biomesoplenty.worldgen.feature.BOPCaveFeatures;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BOPCavePlacements
{
    public static final ResourceKey<PlacedFeature> GLOWING_GROTTO_VEGETATION = BOPPlacementUtils.createKey("glowing_grotto_vegetation");
    public static final ResourceKey<PlacedFeature> GLOWING_GROTTO_MUD = BOPPlacementUtils.createKey("glowing_grotto_mud");
    public static final ResourceKey<PlacedFeature> GLOWWORM_SILK_STRANDS = BOPPlacementUtils.createKey("glowworm_silk_strands");
    public static final ResourceKey<PlacedFeature> GIANT_GLOWSHROOM_CAVE = BOPPlacementUtils.createKey("giant_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> HUGE_GLOWSHROOM_CAVE = BOPPlacementUtils.createKey("huge_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> MEDIUM_GLOWSHROOM_CAVE = BOPPlacementUtils.createKey("medium_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> SMALL_GLOWSHROOM_CAVE = BOPPlacementUtils.createKey("small_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> EXTRA_GLOW_LICHEN = BOPPlacementUtils.createKey("extra_glow_lichen");
    public static final ResourceKey<PlacedFeature> HANGING_COBWEBS = BOPPlacementUtils.createKey("hanging_cobwebs");
    public static final ResourceKey<PlacedFeature> CORNER_COBWEBS = BOPPlacementUtils.createKey("corner_cobwebs");
    public static final ResourceKey<PlacedFeature> SPIDER_EGGS = BOPPlacementUtils.createKey("spider_eggs");
    public static final ResourceKey<PlacedFeature> STRINGY_COBWEB = BOPPlacementUtils.createKey("stringy_cobweb");
    public static final ResourceKey<PlacedFeature> WEBBING = BOPPlacementUtils.createKey("webbing");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> WEBBING = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.WEBBING);
        final Holder<ConfiguredFeature<?, ?>> STRINGY_COBWEB = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.STRINGY_COBWEB);
        final Holder<ConfiguredFeature<?, ?>> SPIDER_EGG = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.SPIDER_EGG);
        final Holder<ConfiguredFeature<?, ?>> CORNER_COBWEBS = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.CORNER_COBWEBS);
        final Holder<ConfiguredFeature<?, ?>> HANGING_COBWEB = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.HANGING_COBWEB);
        final Holder<ConfiguredFeature<?, ?>> EXTRA_GLOW_LICHEN = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.EXTRA_GLOW_LICHEN);
        final Holder<ConfiguredFeature<?, ?>> SMALL_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.SMALL_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> MEDIUM_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.MEDIUM_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> HUGE_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.HUGE_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> GIANT_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.GIANT_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> GLOWWORM_SILK = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.GLOWWORM_SILK);
        final Holder<ConfiguredFeature<?, ?>> MUD_PATCH = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.MUD_PATCH);
        final Holder<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.GLOWING_MOSS_PATCH);

        register(context, BOPCavePlacements.GLOWING_GROTTO_VEGETATION, GLOWING_MOSS_PATCH, List.of(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.GLOWING_GROTTO_MUD, MUD_PATCH, List.of(CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.GLOWWORM_SILK_STRANDS, GLOWWORM_SILK, List.of(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.GIANT_GLOWSHROOM_CAVE, GIANT_GLOWSHROOM_CAVE, List.of(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.HUGE_GLOWSHROOM_CAVE, HUGE_GLOWSHROOM_CAVE, List.of(CountPlacement.of(75), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.MEDIUM_GLOWSHROOM_CAVE, MEDIUM_GLOWSHROOM_CAVE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.SMALL_GLOWSHROOM_CAVE, SMALL_GLOWSHROOM_CAVE, List.of(CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.EXTRA_GLOW_LICHEN, EXTRA_GLOW_LICHEN, List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
        register(context, BOPCavePlacements.HANGING_COBWEBS, HANGING_COBWEB, List.of(CountPlacement.of(200), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.CORNER_COBWEBS, CORNER_COBWEBS, List.of(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 24), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.SPIDER_EGGS, SPIDER_EGG, List.of(CountPlacement.of(35), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.STRINGY_COBWEB, STRINGY_COBWEB, List.of(CountPlacement.of(250), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, BOPCavePlacements.WEBBING, WEBBING, List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
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
