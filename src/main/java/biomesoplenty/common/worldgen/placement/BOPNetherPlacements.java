/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPNetherFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPNetherPlacements
{
    public static final PlacedFeature BLACKSTONE_BULBS = register("blackstone_bulbs", BOPNetherFeatures.BLACKSTONE_BULB.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLACKSTONE_SPINES = register("blackstone_spines", BOPNetherFeatures.BLACKSTONE_SPINES.placed(CountPlacement.of(160), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLOOD_LAKE = register("blood_lake", BOPNetherFeatures.BLOOD_LAKE.placed(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLOOD_SPRING = register("blood_spring", BOPNetherFeatures.BLOOD_SPRING.placed(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));
    public static final PlacedFeature BONE_SPINE = register("bone_spine", BOPNetherFeatures.BONE_SPINE.placed(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature FLESH_TENDON = register("flesh_tendon", BOPNetherFeatures.FLESH_TENDON.placed(CountPlacement.of(65), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature HAIR = register("hair", BOPNetherFeatures.HAIR.placed(CountPlacement.of(30), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature HANGING_FLESH_TENDONS = register("hanging_flesh_tendons", BOPNetherFeatures.HANGING_FLESH_TENDON.placed(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature LARGE_ROSE_QUARTZ = register("large_rose_quartz", BOPNetherFeatures.LARGE_ROSE_QUARTZ.placed(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPNetherFeatures.OBSIDIAN_SPLATTER.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature POROUS_FLESH = register("porous_flesh", BOPNetherFeatures.POROUS_FLESH.placed(CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
    public static final PlacedFeature PUS_BUBBLES = register("pus_bubbles", BOPNetherFeatures.PUS_BUBBLES.placed(CountPlacement.of(18), InSquarePlacement.spread(), PlacementUtils.RANGE_10_10, BiomeFilter.biome()));
    public static final PlacedFeature SMALL_CRYSTAL = register("small_crystal", BOPNetherFeatures.SMALL_CRYSTAL.placed(CountPlacement.of(120), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
