/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPNetherFeatures;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.TREE_THRESHOLD;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class BOPNetherPlacements
{
    public static final PlacedFeature BLACKSTONE_BULBS = register("blackstone_bulbs", BOPNetherFeatures.BLACKSTONE_BULB.placed(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLACKSTONE_SPINES = register("blackstone_spines", BOPNetherFeatures.BLACKSTONE_SPINES.placed(CountPlacement.of(120), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLOOD_LAKE = register("blood_lake", BOPNetherFeatures.BLOOD_LAKE.placed(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLOOD_SPRING = register("blood_spring", BOPNetherFeatures.BLOOD_SPRING.placed(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));
    public static final PlacedFeature BRIMSTONE_BUD = register("brimstone_bud", BOPNetherFeatures.BRIMSTONE_BUD.placed(CountPlacement.of(200), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BRIMSTONE_CLUSTER = register("brimstone_cluster", BOPNetherFeatures.BRIMSTONE_CLUSTER.placed(CountPlacement.of(22), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature DEAD_GRASS_45 = register("dead_grass_45", BOPNetherFeatures.DEAD_GRASS.placed(CountPlacement.of(45), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature FLESH_TENDON = register("flesh_tendon", BOPNetherFeatures.FLESH_TENDON.placed(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_UNDERGROWTH = register("flower_undergrowth", BOPNetherFeatures.UNDERGROWTH_FLOWERS.placed(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature HAIR = register("hair", BOPNetherFeatures.HAIR.placed(CountPlacement.of(30), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature HANGING_FLESH_TENDONS = register("hanging_flesh_tendons", BOPNetherFeatures.HANGING_FLESH_TENDON.placed(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature INFERNO_DELTA = register("inferno_delta", BOPNetherFeatures.INFERNO_DELTA.placed(CountOnEveryLayerPlacement.of(6), BiomeFilter.biome()));
    public static final PlacedFeature INFERNO_LAVA_SPRING = register("inferno_lava_spring", BOPNetherFeatures.INFERNO_LAVA_SPRING.placed(CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));
    public static final PlacedFeature INFERNO_SPLATTER = register("inferno_splatter", BOPNetherFeatures.INFERNO_SPLATTER.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature LARGE_FUMAROLE = register("large_fumarole", BOPNetherFeatures.LARGE_FUMAROLE.placed(CountPlacement.of(175), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature LARGE_ROSE_QUARTZ = register("large_rose_quartz", BOPNetherFeatures.LARGE_ROSE_QUARTZ.placed(CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature NETHER_BONE_SPINE = register("nether_bone_spine", BOPNetherFeatures.NETHER_BONE_SPINE.placed(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature NETHER_BRAMBLE = register("nether_bramble", BOPNetherFeatures.NETHER_BRAMBLE.placed(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature NETHER_VINES = register("nether_vines", BOPNetherFeatures.NETHER_VINES.placed(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPNetherFeatures.OBSIDIAN_SPLATTER.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature POROUS_FLESH = register("porous_flesh", BOPNetherFeatures.POROUS_FLESH.placed(CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
    public static final PlacedFeature PUS_BUBBLES = register("pus_bubbles", BOPNetherFeatures.PUS_BUBBLES.placed(CountPlacement.of(18), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
    public static final PlacedFeature SMALL_CRYSTAL = register("small_crystal", BOPNetherFeatures.SMALL_CRYSTAL.placed(CountPlacement.of(90), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature SMALL_FUMAROLE = register("small_fumarole", BOPNetherFeatures.SMALL_FUMAROLE.placed(CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature SPROUTS_UNDERGROWTH = register("sprouts_undergrowth", BOPNetherFeatures.SPROUTS_UNDERGROWTH.placed(CountPlacement.of(75), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature TREES_UNDERGROWTH = register("trees_undergrowth", BOPNetherFeatures.TREES_UNDERGROWTH.placed(netherTreePlacement(PlacementUtils.countExtra(30, 0.2F, 1))));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }

    private static ImmutableList.Builder<PlacementModifier> netherTreePlacementBase(PlacementModifier modifier)
    {
        return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.FULL_RANGE).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> netherTreePlacement(PlacementModifier modifier)
    {
        return netherTreePlacementBase(modifier).build();
    }

    public static List<PlacementModifier> netherTreePlacement(PlacementModifier modifier, Block survivalTestBlock)
    {
        return netherTreePlacementBase(modifier).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(survivalTestBlock.defaultBlockState(), BlockPos.ZERO))).build();
    }
}
