/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.block.trees;

import biomesoplenty.forge.api.BOPAPI;
import biomesoplenty.forge.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class BOPTreeGrowers
{
    public static final TreeGrower DEAD = register("dead", 0.5F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.DEAD_TWIGLET_TREE), Optional.of(BOPTreeFeatures.SMALL_DEAD_TREE), Optional.empty(), Optional.empty());
    public static final TreeGrower FIR = register("fir", Optional.of(BOPTreeFeatures.FIR_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.FIR_TREE_LARGE), Optional.empty());
    public static final TreeGrower FLOWERING_OAK = register("flowering_oak", 0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE_BEES));
    public static final TreeGrower HELLBARK = register("hellbark", Optional.of(BOPTreeFeatures.HELLBARK_TREE), Optional.of(BOPTreeFeatures.BIG_HELLBARK_TREE));
    public static final TreeGrower JACARANDA = register("jacaranda", 0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.JACARANDA_TREE), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE), Optional.of(BOPTreeFeatures.JACARANDA_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE_BEES));
    public static final TreeGrower MAGIC = register("magic", Optional.of(BOPTreeFeatures.MAGIC_TREE), Optional.of(BOPTreeFeatures.BIG_MAGIC_TREE));
    public static final TreeGrower MAHOGANY = register("mahogany", Optional.of(BOPTreeFeatures.MAHOGANY_TREE), Optional.empty());
    public static final TreeGrower ORANGE_MAPLE = register("orange_maple", Optional.of(BOPTreeFeatures.ORANGE_MAPLE_TREE), Optional.of(BOPTreeFeatures.BIG_ORANGE_MAPLE_TREE));
    public static final TreeGrower ORIGIN = register("origin", Optional.of(BOPTreeFeatures.ORIGIN_TREE), Optional.of(BOPTreeFeatures.BIG_ORIGIN_TREE));
    public static final TreeGrower PALM = register("palm", Optional.of(BOPTreeFeatures.PALM_TREE), Optional.empty());
    public static final TreeGrower PINE = register("pine", Optional.of(BOPTreeFeatures.PINE_TREE), Optional.empty());
    public static final TreeGrower RAINBOW_BIRCH = register("rainbow_birch", Optional.of(BOPTreeFeatures.RAINBOW_BIRCH_TREE), Optional.of(BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE));
    public static final TreeGrower RED_MAPLE = register("red_maple", Optional.of(BOPTreeFeatures.RED_MAPLE_TREE), Optional.of(BOPTreeFeatures.BIG_RED_MAPLE_TREE));
    public static final TreeGrower REDWOOD = register("redwood", Optional.of(BOPTreeFeatures.REDWOOD_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.REDWOOD_TREE_MEDIUM), Optional.of(BOPTreeFeatures.REDWOOD_TREE_LARGE));
    public static final TreeGrower SNOWBLOSSOM = register("snowblossom", Optional.of(BOPTreeFeatures.SNOWBLOSSOM_TREE), Optional.empty());
    public static final TreeGrower UMBRAN = register("umbran", Optional.of(BOPTreeFeatures.UMBRAN_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.TALL_UMBRAN_TREE), Optional.empty());
    public static final TreeGrower WILLOW = register("willow", Optional.of(BOPTreeFeatures.WILLOW_TREE), Optional.of(BOPTreeFeatures.CYPRESS_TREE));
    public static final TreeGrower YELLOW_MAPLE = register("yellow_maple", Optional.of(BOPTreeFeatures.YELLOW_MAPLE_TREE), Optional.of(BOPTreeFeatures.BIG_YELLOW_MAPLE_TREE));

    private static TreeGrower register(String name, float secondaryChance, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers)
    {
        return new TreeGrower(String.format("%s:%s", BOPAPI.MOD_ID, name), secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
    }

    private static TreeGrower register(String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree)
    {
        return register(name, 0.1F, Optional.empty(), Optional.empty(), tree, secondaryTree, Optional.empty(), Optional.empty());
    }

    private static TreeGrower register(String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree)
    {
        return register(name, 0.1F, megaTree, secondaryMegaTree, tree, secondaryTree, Optional.empty(), Optional.empty());
    }
}
