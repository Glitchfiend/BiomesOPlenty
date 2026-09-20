/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.worldgen.feature.misc.*;
import biomesoplenty.worldgen.feature.tree.*;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.worldgen.placement.BOPTreePlacements;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.init.ModTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;

public class BOPNetherFeatures
{
    public static final ResourceKey<Feature> BLACKSTONE_BULB = BOPFeatureUtils.createKey("blackstone_bulb");
    public static final ResourceKey<Feature> BLACKSTONE_SPINES = BOPFeatureUtils.createKey("blackstone_spines");
    public static final ResourceKey<Feature> BLOOD_LAKE = BOPFeatureUtils.createKey("blood_lake");
    public static final ResourceKey<Feature> BLOOD_SPRING = BOPFeatureUtils.createKey("blood_spring");
    public static final ResourceKey<Feature> ORPIMENT_BUD = BOPFeatureUtils.createKey("orpiment_bud");
    public static final ResourceKey<Feature> ORPIMENT_CLUSTER = BOPFeatureUtils.createKey("orpiment_cluster");
    public static final ResourceKey<Feature> ORPIMENT_FIRE = BOPFeatureUtils.createKey("orpiment_fire");
    public static final ResourceKey<Feature> DEAD_GRASS = BOPFeatureUtils.createKey("dead_grass");
    public static final ResourceKey<Feature> EYEBULB = BOPFeatureUtils.createKey("eyebulb");
    public static final ResourceKey<Feature> FLESH_TENDON = BOPFeatureUtils.createKey("flesh_tendon");
    public static final ResourceKey<Feature> HAIR = BOPFeatureUtils.createKey("hair");
    public static final ResourceKey<Feature> HANGING_FLESH_TENDON = BOPFeatureUtils.createKey("hanging_flesh_tendon");
    public static final ResourceKey<Feature> INFERNO_LAVA_LAKE = BOPFeatureUtils.createKey("inferno_lava_lake");
    public static final ResourceKey<Feature> INFERNO_LAVA_SPRING = BOPFeatureUtils.createKey("inferno_lava_spring");
    public static final ResourceKey<Feature> INFERNO_SPLATTER = BOPFeatureUtils.createKey("inferno_splatter");
    public static final ResourceKey<Feature> LARGE_FUMAROLE = BOPFeatureUtils.createKey("large_fumarole");
    public static final ResourceKey<Feature> LARGE_ROSE_QUARTZ = BOPFeatureUtils.createKey("large_rose_quartz");
    public static final ResourceKey<Feature> NETHER_BONE_SPINE = BOPFeatureUtils.createKey("nether_bone_spine");
    public static final ResourceKey<Feature> NETHER_BRAMBLE = BOPFeatureUtils.createKey("nether_bramble");
    public static final ResourceKey<Feature> NETHER_VINES = BOPFeatureUtils.createKey("nether_vines");
    public static final ResourceKey<Feature> OBSIDIAN_SPLATTER = BOPFeatureUtils.createKey("obsidian_splatter");
    public static final ResourceKey<Feature> POROUS_FLESH = BOPFeatureUtils.createKey("porous_flesh");
    public static final ResourceKey<Feature> PUS_BUBBLES = BOPFeatureUtils.createKey("pus_bubbles");
    public static final ResourceKey<Feature> SMALL_CRYSTAL = BOPFeatureUtils.createKey("small_crystal");
    public static final ResourceKey<Feature> SMALL_FUMAROLE = BOPFeatureUtils.createKey("small_fumarole");
    public static final ResourceKey<Feature> SPROUTS_UNDERGROWTH = BOPFeatureUtils.createKey("sprouts_undergrowth");
    public static final ResourceKey<Feature> TREES_UNDERGROWTH = BOPFeatureUtils.createKey("trees_undergrowth");
    public static final ResourceKey<Feature> UNDERGROWTH_FLOWERS = BOPFeatureUtils.createKey("undergrowth_flowers");

    public static void bootstrap(BootstrapContext<Feature> context)
    {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        final Holder<PlacedFeature> BIG_HELLBARK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_HELLBARK_TREE_CHECKED);
        final Holder<PlacedFeature> HELLBARK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.HELLBARK_TREE_CHECKED);

        register(context, BOPNetherFeatures.BLACKSTONE_BULB, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.BLACKSTONE_BULB)));
        register(context, BOPNetherFeatures.BLACKSTONE_SPINES, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.BLACKSTONE_SPINES)));
        register(context, BOPNetherFeatures.BLOOD_LAKE, new BOPLakeFeature(BlockStateProvider.of(BOPBlocks.BLOOD), BlockStateProvider.of(BOPBlocks.FLESH)));
        register(context, BOPNetherFeatures.BLOOD_SPRING, new SpringFeature(BOPFluids.BLOOD.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.NETHERRACK, BOPBlocks.FLESH, BOPBlocks.POROUS_FLESH)));
        register(context, BOPNetherFeatures.ORPIMENT_BUD, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.ORPIMENT_BUD)));
        register(context, BOPNetherFeatures.ORPIMENT_CLUSTER, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.ORPIMENT_CLUSTER)));
        register(context, BOPNetherFeatures.ORPIMENT_FIRE, new SimpleBlockFeature(BlockStateProvider.of(Blocks.FIRE)));
        register(context, BOPNetherFeatures.DEAD_GRASS, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.DEAD_GRASS)));
        register(context, BOPNetherFeatures.EYEBULB, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.EYEBULB)));
        register(context, BOPNetherFeatures.FLESH_TENDON, new FleshTendonFeature());
        register(context, BOPNetherFeatures.HAIR, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.HAIR)));
        register(context, BOPNetherFeatures.HANGING_FLESH_TENDON, new HangingFleshTendonFeature());
        register(context, BOPNetherFeatures.INFERNO_LAVA_LAKE, new BOPLakeFeature(BlockStateProvider.of(Blocks.LAVA), BlockStateProvider.of(BOPBlocks.ORPIMENT)));
        register(context, BOPNetherFeatures.INFERNO_LAVA_SPRING, new SpringFeature(Fluids.LAVA.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.NETHERRACK)));
        register(context, BOPNetherFeatures.INFERNO_SPLATTER, new InfernoSplatterFeature());
        register(context, BOPNetherFeatures.LARGE_FUMAROLE, new LargeFumaroleFeature());
        register(context, BOPNetherFeatures.LARGE_ROSE_QUARTZ, new LargeRoseQuartzFeature(30, UniformInt.of(3, 7), UniformFloat.of(0.3F, 1.8F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
        register(context, BOPNetherFeatures.NETHER_BONE_SPINE, new BoneSpineFeature());
        register(context, BOPNetherFeatures.NETHER_BRAMBLE, new BrambleFeature());
        register(context, BOPNetherFeatures.NETHER_VINES, new NetherVinesFeature());
        register(context, BOPNetherFeatures.OBSIDIAN_SPLATTER, new ObsidianSplatterFeature());
        register(context, BOPNetherFeatures.POROUS_FLESH, new OreFeature(new TagMatchTest(ModTags.Blocks.FLESH), BOPBlocks.POROUS_FLESH.defaultBlockState(), 16));
        register(context, BOPNetherFeatures.PUS_BUBBLES, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.PUS_BUBBLE)));
        register(context, BOPNetherFeatures.SMALL_CRYSTAL, new SmallCrystalFeature());
        register(context, BOPNetherFeatures.SMALL_FUMAROLE, new SmallFumaroleFeature());
        register(context, BOPNetherFeatures.SPROUTS_UNDERGROWTH, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.SPROUT)));
        register(context, BOPNetherFeatures.TREES_UNDERGROWTH, new RandomSelectorFeature(ImmutableList.of(new WeightedPlacedFeature(BIG_HELLBARK_TREE_CHECKED, 0.4F)), HELLBARK_TREE_CHECKED));
        register(context, BOPNetherFeatures.UNDERGROWTH_FLOWERS, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.BURNING_BLOSSOM)));
    }

    private static void register(BootstrapContext<Feature> context, ResourceKey<Feature> key, Feature feature)
    {
        context.register(key, feature);
    }
}
