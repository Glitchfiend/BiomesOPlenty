/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPCaveFeatures;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.register;

public class BOPCavePlacements
{
    // Glowing grotto
    public static final RegistryObject<PlacedFeature> GLOWING_GROTTO_VEGETATION = register("glowing_grotto_vegetation", BOPCaveFeatures.GLOWING_MOSS_PATCH, () -> List.of(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> GLOWING_GROTTO_MUD = register("glowing_grotto_mud", BOPCaveFeatures.MUD_PATCH, () -> List.of(CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> GLOWWORM_SILK_STRANDS = register("glowworm_silk_strands", BOPCaveFeatures.GLOWWORM_SILK, () -> List.of(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> GIANT_GLOWSHROOM_CAVE = register("giant_glowshroom_cave", BOPCaveFeatures.GIANT_GLOWSHROOM_CAVE, () -> List.of(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> HUGE_GLOWSHROOM_CAVE = register("huge_glowshroom_cave", BOPCaveFeatures.HUGE_GLOWSHROOM_CAVE, () -> List.of(CountPlacement.of(75), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> MEDIUM_GLOWSHROOM_CAVE = register("medium_glowshroom_cave", BOPCaveFeatures.MEDIUM_GLOWSHROOM_CAVE, () -> List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SMALL_GLOWSHROOM_CAVE = register("small_glowshroom_cave", BOPCaveFeatures.SMALL_GLOWSHROOM_CAVE, () -> List.of(CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> EXTRA_GLOW_LICHEN = register("extra_glow_lichen", BOPCaveFeatures.EXTRA_GLOW_LICHEN, () -> List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

    // Spider nest
    public static final RegistryObject<PlacedFeature> HANGING_COBWEBS = register("hanging_cobwebs", BOPCaveFeatures.HANGING_COBWEB, () -> List.of(CountPlacement.of(200), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> CORNER_COBWEBS = register("corner_cobwebs", BOPCaveFeatures.CORNER_COBWEBS, () -> List.of(CountPlacement.of(40), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 24), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SPIDER_EGGS = register("spider_eggs", BOPCaveFeatures.SPIDER_EGG, () -> List.of(CountPlacement.of(35), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> STRINGY_COBWEB = register("stringy_cobweb", BOPCaveFeatures.STRINGY_COBWEB, () -> List.of(CountPlacement.of(90), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> WEBBING = register("webbing", BOPCaveFeatures.WEBBING, () -> List.of(CountPlacement.of(18), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

    public static void setup() {}
}
