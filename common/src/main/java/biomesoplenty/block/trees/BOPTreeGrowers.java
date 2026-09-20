/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block.trees;

import net.minecraft.util.random.WeightedList;
import net.minecraft.util.random.Weighted;
import biomesoplenty.api.BOPAPI;
import biomesoplenty.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Optional;

public class BOPTreeGrowers
{
    public static final TreeGrower CYPRESS = register("cypress", Optional.of(BOPTreeFeatures.CYPRESS_TREE), Optional.empty());
    public static final TreeGrower DEAD = register("dead", 0.5F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.DEAD_TWIGLET_TREE), Optional.of(BOPTreeFeatures.SMALL_DEAD_TREE), Optional.empty(), Optional.empty());
    public static final TreeGrower EMPYREAL = register("empyreal", Optional.of(BOPTreeFeatures.EMPYREAL_TREE), Optional.empty());
    public static final TreeGrower FIR = register("fir", Optional.of(BOPTreeFeatures.FIR_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.FIR_TREE_LARGE), Optional.empty());
    public static final TreeGrower FLOWERING_OAK = register("flowering_oak", 0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE_BEES));
    public static final TreeGrower HELLBARK = register("hellbark", Optional.of(BOPTreeFeatures.HELLBARK_TREE), Optional.of(BOPTreeFeatures.BIG_HELLBARK_TREE));
    public static final TreeGrower JACARANDA = register("jacaranda", 0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.JACARANDA_TREE), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE), Optional.of(BOPTreeFeatures.JACARANDA_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE_BEES));
    public static final TreeGrower MAGIC = register("magic", Optional.of(BOPTreeFeatures.MAGIC_TREE), Optional.of(BOPTreeFeatures.BIG_MAGIC_TREE));
    public static final TreeGrower MAHOGANY = register("mahogany", Optional.of(BOPTreeFeatures.MAHOGANY_TREE), Optional.empty());
    public static final TreeGrower MAPLE = register("maple", WeightedList.of(new Weighted<>(BOPTreeFeatures.PINK_MAPLE_TREE, 9), new Weighted<>(BOPTreeFeatures.MAGENTA_MAPLE_TREE, 9), new Weighted<>(BOPTreeFeatures.PURPLE_MAPLE_TREE, 9), new Weighted<>(BOPTreeFeatures.BIG_PINK_MAPLE_TREE, 1), new Weighted<>(BOPTreeFeatures.BIG_MAGENTA_MAPLE_TREE, 1), new Weighted<>(BOPTreeFeatures.BIG_PURPLE_MAPLE_TREE, 1)), WeightedList.of(), WeightedList.of(), Optional.of(BOPTreeFeatures.MAGENTA_MAPLE_TREE));
    public static final TreeGrower ORIGIN_OAK = register("origin_oak", Optional.of(BOPTreeFeatures.ORIGIN_OAK_TREE), Optional.of(BOPTreeFeatures.BIG_ORIGIN_OAK_TREE));
    public static final TreeGrower PALM = register("palm", Optional.of(BOPTreeFeatures.PALM_TREE), Optional.empty());
    public static final TreeGrower PINE = register("pine", Optional.of(BOPTreeFeatures.PINE_TREE), Optional.of(BOPTreeFeatures.PINE_TREE_SMALL));
    public static final TreeGrower REDWOOD = register("redwood", Optional.of(BOPTreeFeatures.REDWOOD_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.REDWOOD_TREE_MEDIUM), Optional.of(BOPTreeFeatures.REDWOOD_TREE_LARGE));
    public static final TreeGrower SNOWBLOSSOM = register("snowblossom", Optional.of(BOPTreeFeatures.SNOWBLOSSOM_TREE), Optional.empty());
    public static final TreeGrower UMBRAN = register("umbran", Optional.of(BOPTreeFeatures.UMBRAN_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.TALL_UMBRAN_TREE), Optional.empty());
    public static final TreeGrower WILLOW = register("willow", Optional.of(BOPTreeFeatures.WILLOW_TREE), Optional.of(BOPTreeFeatures.BAYOU_TREE));

    private static TreeGrower register(String name, float secondaryChance, Optional<ResourceKey<Feature>> megaTree, Optional<ResourceKey<Feature>> secondaryMegaTree, Optional<ResourceKey<Feature>> tree, Optional<ResourceKey<Feature>> secondaryTree, Optional<ResourceKey<Feature>> flowers, Optional<ResourceKey<Feature>> secondaryFlowers)
    {
        return new TreeGrower(String.format("%s:%s", BOPAPI.MOD_ID, name),
                weighted(tree, secondaryTree, secondaryChance),
                weighted(megaTree, secondaryMegaTree, secondaryChance),
                weighted(flowers, secondaryFlowers, secondaryChance),
                tree.orElse(null));
    }

    private static TreeGrower register(String name, WeightedList<ResourceKey<Feature>> trees, WeightedList<ResourceKey<Feature>> megaTrees, WeightedList<ResourceKey<Feature>> flowers, Optional<ResourceKey<Feature>> tree)
    {
        return new TreeGrower(String.format("%s:%s", BOPAPI.MOD_ID, name),
                trees,
                megaTrees,
                flowers,
                tree.orElse(null));
    }

    private static WeightedList<ResourceKey<Feature>> weighted(Optional<ResourceKey<Feature>> primary, Optional<ResourceKey<Feature>> secondary, float secondaryChance)
    {
        if (primary.isEmpty()) return WeightedList.of();
        if (secondary.isEmpty()) return WeightedList.of(primary.get());

        int secondaryWeight = Math.max(1, Math.round(secondaryChance * 100.0F));
        int primaryWeight = Math.max(1, 100 - secondaryWeight);
        return WeightedList.of(new Weighted<>(primary.get(), primaryWeight), new Weighted<>(secondary.get(), secondaryWeight));
    }

    private static TreeGrower register(String name, Optional<ResourceKey<Feature>> tree, Optional<ResourceKey<Feature>> secondaryTree)
    {
        return register(name, 0.1F, Optional.empty(), Optional.empty(), tree, secondaryTree, Optional.empty(), Optional.empty());
    }

    private static TreeGrower register(String name, Optional<ResourceKey<Feature>> tree, Optional<ResourceKey<Feature>> secondaryTree, Optional<ResourceKey<Feature>> megaTree, Optional<ResourceKey<Feature>> secondaryMegaTree)
    {
        return register(name, 0.1F, megaTree, secondaryMegaTree, tree, secondaryTree, Optional.empty(), Optional.empty());
    }
}
