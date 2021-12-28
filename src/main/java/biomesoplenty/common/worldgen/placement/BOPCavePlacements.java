/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPCaveFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

public class BOPCavePlacements
{
    // Glowing grotto
    public static final PlacedFeature GLOWING_GROTTO_VEGETATION = register("glowing_grotto_vegetation", BOPCaveFeatures.GLOWING_MOSS_PATCH.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature GLOWING_GROTTO_MUD = register("glowing_grotto_mud", BOPCaveFeatures.MUD_PATCH.placed(CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature GLOWWORM_SILK_STRANDS = register("glowworm_silk_strands", BOPCaveFeatures.GLOWWORM_SILK.placed(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
    public static final PlacedFeature GIANT_GLOWSHROOM_CAVE = register("giant_glowshroom_cave", BOPCaveFeatures.GIANT_GLOWSHROOM_CAVE.placed(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature HUGE_GLOWSHROOM_CAVE = register("huge_glowshroom_cave", BOPCaveFeatures.HUGE_GLOWSHROOM_CAVE.placed(CountPlacement.of(75), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature MEDIUM_GLOWSHROOM_CAVE = register("medium_glowshroom_cave", BOPCaveFeatures.MEDIUM_GLOWSHROOM_CAVE.placed(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature SMALL_GLOWSHROOM_CAVE = register("small_glowshroom_cave", BOPCaveFeatures.SMALL_GLOWSHROOM_CAVE.placed(CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature EXTRA_GLOW_LICHEN = register("extra_glow_lichen", BOPCaveFeatures.EXTRA_GLOW_LICHEN.placed(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

    // Spider nest
    public static final PlacedFeature HANGING_COBWEBS = register("hanging_cobwebs", BOPCaveFeatures.HANGING_COBWEB.placed(CountPlacement.of(200), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
    public static final PlacedFeature CORNER_COBWEBS = register("corner_cobwebs", BOPCaveFeatures.CORNER_COBWEBS.placed(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 24), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature SPIDER_EGGS = register("spider_eggs", BOPCaveFeatures.SPIDER_EGG.placed(CountPlacement.of(30), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature STRINGY_COBWEB = register("stringy_cobweb", BOPCaveFeatures.STRINGY_COBWEB.placed(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final PlacedFeature WEBBING = register("webbing", BOPCaveFeatures.WEBBING.placed(CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
