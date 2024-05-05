/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.placement;

import biomesoplenty.worldgen.feature.BOPNetherFeatures;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BOPNetherPlacements
{
    public static final ResourceKey<PlacedFeature> BLACKSTONE_BULBS = BOPPlacementUtils.createKey("blackstone_bulbs");
    public static final ResourceKey<PlacedFeature> BLACKSTONE_SPINES = BOPPlacementUtils.createKey("blackstone_spines");
    public static final ResourceKey<PlacedFeature> BLOOD_LAKE = BOPPlacementUtils.createKey("blood_lake");
    public static final ResourceKey<PlacedFeature> BLOOD_SPRING = BOPPlacementUtils.createKey("blood_spring");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_BUD = BOPPlacementUtils.createKey("brimstone_bud");
    public static final ResourceKey<PlacedFeature> BRIMSTONE_CLUSTER = BOPPlacementUtils.createKey("brimstone_cluster");
    public static final ResourceKey<PlacedFeature> DEAD_GRASS_45 = BOPPlacementUtils.createKey("dead_grass_45");
    public static final ResourceKey<PlacedFeature> EYEBULB = BOPPlacementUtils.createKey("eyebulb");
    public static final ResourceKey<PlacedFeature> FLESH_TENDON = BOPPlacementUtils.createKey("flesh_tendon");
    public static final ResourceKey<PlacedFeature> FLOWER_UNDERGROWTH = BOPPlacementUtils.createKey("flower_undergrowth");
    public static final ResourceKey<PlacedFeature> HAIR = BOPPlacementUtils.createKey("hair");
    public static final ResourceKey<PlacedFeature> HANGING_FLESH_TENDONS = BOPPlacementUtils.createKey("hanging_flesh_tendons");
    public static final ResourceKey<PlacedFeature> INFERNO_LAVA_LAKE = BOPPlacementUtils.createKey("inferno_lava_lake");
    public static final ResourceKey<PlacedFeature> INFERNO_LAVA_SPRING = BOPPlacementUtils.createKey("inferno_lava_spring");
    public static final ResourceKey<PlacedFeature> INFERNO_SPLATTER = BOPPlacementUtils.createKey("inferno_splatter");
    public static final ResourceKey<PlacedFeature> LARGE_FUMAROLE = BOPPlacementUtils.createKey("large_fumarole");
    public static final ResourceKey<PlacedFeature> LARGE_ROSE_QUARTZ = BOPPlacementUtils.createKey("large_rose_quartz");
    public static final ResourceKey<PlacedFeature> NETHER_BONE_SPINE = BOPPlacementUtils.createKey("nether_bone_spine");
    public static final ResourceKey<PlacedFeature> NETHER_BRAMBLE = BOPPlacementUtils.createKey("nether_bramble");
    public static final ResourceKey<PlacedFeature> NETHER_VINES = BOPPlacementUtils.createKey("nether_vines");
    public static final ResourceKey<PlacedFeature> OBSIDIAN_SPLATTER = BOPPlacementUtils.createKey("obsidian_splatter");
    public static final ResourceKey<PlacedFeature> POROUS_FLESH = BOPPlacementUtils.createKey("porous_flesh");
    public static final ResourceKey<PlacedFeature> PUS_BUBBLES = BOPPlacementUtils.createKey("pus_bubbles");
    public static final ResourceKey<PlacedFeature> SMALL_CRYSTAL = BOPPlacementUtils.createKey("small_crystal");
    public static final ResourceKey<PlacedFeature> SMALL_FUMAROLE = BOPPlacementUtils.createKey("small_fumarole");
    public static final ResourceKey<PlacedFeature> SPROUTS_UNDERGROWTH = BOPPlacementUtils.createKey("sprouts_undergrowth");
    public static final ResourceKey<PlacedFeature> TREES_UNDERGROWTH = BOPPlacementUtils.createKey("trees_undergrowth");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> BLACKSTONE_BULB = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.BLACKSTONE_BULB);
        final Holder<ConfiguredFeature<?, ?>> BLACKSTONE_SPINES = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.BLACKSTONE_SPINES);
        final Holder<ConfiguredFeature<?, ?>> BLOOD_LAKE = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.BLOOD_LAKE);
        final Holder<ConfiguredFeature<?, ?>> BLOOD_SPRING = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.BLOOD_SPRING);
        final Holder<ConfiguredFeature<?, ?>> BRIMSTONE_BUD = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.BRIMSTONE_BUD);
        final Holder<ConfiguredFeature<?, ?>> BRIMSTONE_CLUSTER = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.BRIMSTONE_CLUSTER);
        final Holder<ConfiguredFeature<?, ?>> DEAD_GRASS = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.DEAD_GRASS);
        final Holder<ConfiguredFeature<?, ?>> EYEBULB = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.EYEBULB);
        final Holder<ConfiguredFeature<?, ?>> FLESH_TENDON = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.FLESH_TENDON);
        final Holder<ConfiguredFeature<?, ?>> UNDERGROWTH_FLOWERS = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.UNDERGROWTH_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> HAIR = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.HAIR);
        final Holder<ConfiguredFeature<?, ?>> HANGING_FLESH_TENDON = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.HANGING_FLESH_TENDON);
        final Holder<ConfiguredFeature<?, ?>> INFERNO_LAVA_LAKE = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.INFERNO_LAVA_LAKE);
        final Holder<ConfiguredFeature<?, ?>> INFERNO_LAVA_SPRING = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.INFERNO_LAVA_SPRING);
        final Holder<ConfiguredFeature<?, ?>> INFERNO_SPLATTER = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.INFERNO_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> LARGE_FUMAROLE = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.LARGE_FUMAROLE);
        final Holder<ConfiguredFeature<?, ?>> LARGE_ROSE_QUARTZ = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.LARGE_ROSE_QUARTZ);
        final Holder<ConfiguredFeature<?, ?>> NETHER_BONE_SPINE = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.NETHER_BONE_SPINE);
        final Holder<ConfiguredFeature<?, ?>> NETHER_BRAMBLE = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.NETHER_BRAMBLE);
        final Holder<ConfiguredFeature<?, ?>> NETHER_VINES = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.NETHER_VINES);
        final Holder<ConfiguredFeature<?, ?>> OBSIDIAN_SPLATTER = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.OBSIDIAN_SPLATTER);
        final Holder<ConfiguredFeature<?, ?>> POROUS_FLESH = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.POROUS_FLESH);
        final Holder<ConfiguredFeature<?, ?>> PUS_BUBBLES = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.PUS_BUBBLES);
        final Holder<ConfiguredFeature<?, ?>> SMALL_CRYSTAL = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.SMALL_CRYSTAL);
        final Holder<ConfiguredFeature<?, ?>> SMALL_FUMAROLE = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.SMALL_FUMAROLE);
        final Holder<ConfiguredFeature<?, ?>> SPROUTS_UNDERGROWTH = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.SPROUTS_UNDERGROWTH);
        final Holder<ConfiguredFeature<?, ?>> TREES_UNDERGROWTH = configuredFeatureGetter.getOrThrow(BOPNetherFeatures.TREES_UNDERGROWTH);

        register(context, BOPNetherPlacements.BLACKSTONE_BULBS, BLACKSTONE_BULB, List.of(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.BLACKSTONE_SPINES, BLACKSTONE_SPINES, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.BLOOD_LAKE, BLOOD_LAKE, List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.BLOOD_SPRING, BLOOD_SPRING, List.of(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.BRIMSTONE_BUD, BRIMSTONE_BUD, List.of(CountPlacement.of(175), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.BRIMSTONE_CLUSTER, BRIMSTONE_CLUSTER, List.of(CountPlacement.of(14), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.DEAD_GRASS_45, DEAD_GRASS, List.of(CountPlacement.of(45), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.EYEBULB, EYEBULB, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.FLESH_TENDON, FLESH_TENDON, List.of(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.FLOWER_UNDERGROWTH, UNDERGROWTH_FLOWERS, List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.HAIR, HAIR, List.of(CountPlacement.of(22), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.HANGING_FLESH_TENDONS, HANGING_FLESH_TENDON, List.of(CountPlacement.of(85), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.INFERNO_LAVA_LAKE, INFERNO_LAVA_LAKE, List.of(CountOnEveryLayerPlacement.of(3), BiomeFilter.biome()));
        register(context, BOPNetherPlacements.INFERNO_LAVA_SPRING, INFERNO_LAVA_SPRING, List.of(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.INFERNO_SPLATTER, INFERNO_SPLATTER, List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.LARGE_FUMAROLE, LARGE_FUMAROLE, List.of(CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.LARGE_ROSE_QUARTZ, LARGE_ROSE_QUARTZ, List.of(CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.NETHER_BONE_SPINE, NETHER_BONE_SPINE, List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.NETHER_BRAMBLE, NETHER_BRAMBLE, List.of(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.NETHER_VINES, NETHER_VINES, List.of(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.OBSIDIAN_SPLATTER, OBSIDIAN_SPLATTER, List.of(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.POROUS_FLESH, POROUS_FLESH, List.of(CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.PUS_BUBBLES, PUS_BUBBLES, List.of(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.SMALL_CRYSTAL, SMALL_CRYSTAL, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.SMALL_FUMAROLE, SMALL_FUMAROLE, List.of(CountPlacement.of(40), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.SPROUTS_UNDERGROWTH, SPROUTS_UNDERGROWTH, List.of(CountPlacement.of(75), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        register(context, BOPNetherPlacements.TREES_UNDERGROWTH, TREES_UNDERGROWTH, netherTreePlacement(PlacementUtils.countExtra(40, 0.2F, 1)));
    }

    private static ImmutableList.Builder<PlacementModifier> netherTreePlacementBase(PlacementModifier modifier)
    {
        PlacementModifier treeThreshold = SurfaceWaterDepthFilter.forMaxDepth(0);
        return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(treeThreshold).add(PlacementUtils.FULL_RANGE).add(BiomeFilter.biome());
    }

    private static List<PlacementModifier> netherTreePlacement(PlacementModifier modifier)
    {
        return netherTreePlacementBase(modifier).build();
    }

    private static List<PlacementModifier> netherTreePlacement(PlacementModifier modifier, Block survivalTestBlock)
    {
        return netherTreePlacementBase(modifier).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(survivalTestBlock.defaultBlockState(), BlockPos.ZERO))).build();
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
