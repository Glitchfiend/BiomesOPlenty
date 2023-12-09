/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import biomesoplenty.api.BOPAPI;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class BOPTreeGrowers
{
    public static final TreeGrower DEAD = register("dead", 0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.SMALL_DEAD_TREE), Optional.of(BOPTreeFeatures.DYING_TREE), Optional.empty(), Optional.empty());
    public static final TreeGrower FIR = register("fir", Optional.of(BOPTreeFeatures.FIR_TREE_LARGE), Optional.of(BOPTreeFeatures.FIR_TREE), Optional.empty());
    public static final TreeGrower FLOWERING_OAK = register("flowering_oak", 0.1F, Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE_BEES));
    public static final TreeGrower HELLBARK = register("hellbark", Optional.of(BOPTreeFeatures.BIG_HELLBARK_TREE), Optional.of(BOPTreeFeatures.HELLBARK_TREE), Optional.empty());
    public static final TreeGrower JACARANDA = register("jacaranda", 0.1F, Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.JACARANDA_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.JACARANDA_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE_BEES));
    public static final TreeGrower MAGIC = register("magic", Optional.of(BOPTreeFeatures.BIG_MAGIC_TREE), Optional.of(BOPTreeFeatures.MAGIC_TREE), Optional.empty());
    public static final TreeGrower MAHOGANY = register("mahogany", Optional.empty(), Optional.of(BOPTreeFeatures.MAHOGANY_TREE), Optional.empty());
    public static final TreeGrower ORANGE_MAPLE = register("orange_maple", Optional.of(BOPTreeFeatures.BIG_ORANGE_MAPLE_TREE), Optional.of(BOPTreeFeatures.ORANGE_MAPLE_TREE), Optional.empty());
    public static final TreeGrower ORIGIN = register("origin", Optional.of(BOPTreeFeatures.BIG_ORIGIN_TREE), Optional.of(BOPTreeFeatures.ORIGIN_TREE), Optional.empty());
    public static final TreeGrower PALM = register("palm", Optional.empty(), Optional.of(BOPTreeFeatures.PALM_TREE), Optional.empty());
    public static final TreeGrower PINE = register("pine", Optional.empty(), Optional.of(BOPTreeFeatures.PINE_TREE), Optional.empty());
    public static final TreeGrower RAINBOW_BIRCH = register("rainbow_birch", Optional.of(BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE), Optional.of(BOPTreeFeatures.RAINBOW_BIRCH_TREE), Optional.empty());
    public static final TreeGrower RED_MAPLE = register("red_maple", Optional.of(BOPTreeFeatures.BIG_RED_MAPLE_TREE), Optional.of(BOPTreeFeatures.RED_MAPLE_TREE), Optional.empty());
    public static final TreeGrower REDWOOD = register("redwood", 0.1F, Optional.of(BOPTreeFeatures.REDWOOD_TREE_MEDIUM), Optional.of(BOPTreeFeatures.REDWOOD_TREE_LARGE), Optional.of(BOPTreeFeatures.REDWOOD_TREE), Optional.empty(), Optional.empty(), Optional.empty());
    public static final TreeGrower SNOWBLOSSOM = register("snowblossom", Optional.empty(), Optional.of(BOPTreeFeatures.SNOWBLOSSOM_TREE), Optional.empty());
    public static final TreeGrower UMBRAN = register("umbran", Optional.of(BOPTreeFeatures.TALL_UMBRAN_TREE), Optional.of(BOPTreeFeatures.UMBRAN_TREE), Optional.empty());
    public static final TreeGrower WILLOW = register("willow", 0.2F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.WILLOW_TREE), Optional.of(BOPTreeFeatures.CYPRESS_TREE), Optional.empty(), Optional.empty());
    public static final TreeGrower YELLOW_MAPLE = register("yellow_maple", Optional.of(BOPTreeFeatures.BIG_YELLOW_MAPLE_TREE), Optional.of(BOPTreeFeatures.YELLOW_MAPLE_TREE), Optional.empty());

    private static TreeGrower register(String name, float secondaryChance, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers)
    {
        return new TreeGrower(String.format("%s:%s", BOPAPI.MOD_ID, name), secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
    }

    private static TreeGrower register(String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers)
    {
        return register(name, 0.1F, megaTree, Optional.empty(), tree, Optional.empty(), flowers, Optional.empty());
    }
}
