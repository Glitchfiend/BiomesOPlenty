/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import net.minecraft.world.level.levelgen.placement.RandomChancePlacement;
import net.minecraft.world.level.levelgen.placement.OffsetPlacement;
import net.minecraft.world.level.levelgen.feature.CuboidPlacement;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomBlockProvider;
import net.minecraft.util.valueproviders.UniformInt;
import biomesoplenty.init.ModTags;
import biomesoplenty.worldgen.feature.misc.*;
import biomesoplenty.worldgen.feature.tree.*;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.worldgen.placement.BOPTreePlacements;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPEndFeatures
{
    public static final ResourceKey<Feature> ANOMALY = BOPFeatureUtils.createKey("anomaly");
    public static final ResourceKey<Feature> BARNACLES = BOPFeatureUtils.createKey("barnacles");
    public static final ResourceKey<Feature> DEAD_CORAL = BOPFeatureUtils.createKey("dead_coral");
    public static final ResourceKey<Feature> DEAD_CORAL_PATCH = BOPFeatureUtils.createKey("dead_coral_patch");
    public static final ResourceKey<Feature> ENDERPHYTE_BONEMEAL = BOPFeatureUtils.createKey("enderphyte_bonemeal");
    public static final ResourceKey<Feature> ENDSCRAPER = BOPFeatureUtils.createKey("endscraper");
    public static final ResourceKey<Feature> FLOWER_END_WILDS = BOPFeatureUtils.createKey("flower_end_wilds");
    public static final ResourceKey<Feature> JAGGED_SANDSTONE = BOPFeatureUtils.createKey("jagged_sandstone");
    public static final ResourceKey<Feature> LIQUID_NULL_LAKE = BOPFeatureUtils.createKey("liquid_null_lake");
    public static final ResourceKey<Feature> LIQUID_NULL_SPRING = BOPFeatureUtils.createKey("liquid_null_spring");
    public static final ResourceKey<Feature> LUMALOOP = BOPFeatureUtils.createKey("lumaloop");
    public static final ResourceKey<Feature> MONOLITH = BOPFeatureUtils.createKey("monolith");
    public static final ResourceKey<Feature> NULL_PLANT_BONEMEAL = BOPFeatureUtils.createKey("null_plant_bonemeal");
    public static final ResourceKey<Feature> PATCH_ENDERPHYTES = BOPFeatureUtils.createKey("patch_enderphytes");
    public static final ResourceKey<Feature> PATCH_NULL_PLANTS = BOPFeatureUtils.createKey("patch_null_plants");
    public static final ResourceKey<Feature> TIDEPOOL = BOPFeatureUtils.createKey("tidepool");
    public static final ResourceKey<Feature> TREES_END_CORRUPTION = BOPFeatureUtils.createKey("trees_end_corruption");
    public static final ResourceKey<Feature> TREES_END_WILDS = BOPFeatureUtils.createKey("trees_end_wilds");
    public static final ResourceKey<Feature> WISPJELLY = BOPFeatureUtils.createKey("wispjelly");

    public static void bootstrap(BootstrapContext<Feature> context)
    {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        final Holder<PlacedFeature> EMPYREAL_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.EMPYREAL_TREE_CHECKED);
        final Holder<PlacedFeature> NULL_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.NULL_TREE_CHECKED);

        register(context, BOPEndFeatures.ANOMALY, new AnomalyFeature());
        register(context, BOPEndFeatures.BARNACLES, new BarnacleFeature());

        Holder<Feature> deadCoralBlock = Holder.direct(new DeadCoralBlockFeature(new RandomBlockProvider(blocks.getOrThrow(ModTags.Blocks.DEAD_CORAL_BLOCKS))));
        register(context, BOPEndFeatures.DEAD_CORAL, new SimpleRandomSelectorFeature(HolderSet.direct(
                PlacementUtils.inlinePlaced(new CoralTreeFeature(PlacementUtils.inlinePlaced(deadCoralBlock))),
                PlacementUtils.inlinePlaced(new CoralClawFeature(PlacementUtils.inlinePlaced(deadCoralBlock))),
                PlacementUtils.inlinePlaced(deadCoralBlock,
                        OffsetPlacement.vertical(UniformInt.of(-3, -1)),
                        new CuboidPlacement(UniformInt.of(3, 5), UniformInt.of(3, 5), false, false),
                        new RandomChancePlacement(0.9F))
        )));
        register(context, BOPEndFeatures.DEAD_CORAL_PATCH, new DeadCoralPatchFeature());
        register(context, BOPEndFeatures.ENDERPHYTE_BONEMEAL, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.ENDERPHYTE.defaultBlockState())));
        register(context, BOPEndFeatures.ENDSCRAPER, new EndscraperFeature());
        register(context, BOPEndFeatures.FLOWER_END_WILDS, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.ENDBLOOM)));
        register(context, BOPEndFeatures.JAGGED_SANDSTONE, new JaggedSandstoneFeature());
        register(context, BOPEndFeatures.LIQUID_NULL_LAKE, new BOPLakeFeature(BlockStateProvider.of(BOPBlocks.LIQUID_NULL), BlockStateProvider.of(BOPBlocks.NULL_END_STONE)));
        register(context, BOPEndFeatures.LIQUID_NULL_SPRING, new SpringFeature(BOPFluids.LIQUID_NULL.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.END_STONE, BOPBlocks.NULL_END_STONE, BOPBlocks.UNMAPPED_END_STONE)));
        register(context, BOPEndFeatures.LUMALOOP, new LumaloopFeature());
        register(context, BOPEndFeatures.MONOLITH, new MonolithFeature());
        register(context, BOPEndFeatures.NULL_PLANT_BONEMEAL, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.NULL_PLANT.defaultBlockState())));
        register(context, BOPEndFeatures.PATCH_ENDERPHYTES, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.ENDERPHYTE)));
        register(context, BOPEndFeatures.PATCH_NULL_PLANTS, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.NULL_PLANT)));
        register(context, BOPEndFeatures.TIDEPOOL, new TidepoolFeature());
        register(context, BOPEndFeatures.TREES_END_CORRUPTION, new RandomSelectorFeature(ImmutableList.of(new WeightedPlacedFeature(NULL_TREE_CHECKED, 0.075F)), NULL_TREE_CHECKED));
        register(context, BOPEndFeatures.TREES_END_WILDS, new RandomSelectorFeature(ImmutableList.of(new WeightedPlacedFeature(EMPYREAL_TREE_CHECKED, 0.075F)), EMPYREAL_TREE_CHECKED));
        register(context, BOPEndFeatures.WISPJELLY, new WispjellyFeature());
    }

    private static void register(BootstrapContext<Feature> context, ResourceKey<Feature> key, Feature feature)
    {
        context.register(key, feature);
    }
}
