package biomesoplenty.block.trees;

import biomesoplenty.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class BOPTreeGrowers {

  private static BOPMegaTreeGrowerImpl register(float secondaryChance,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
    return new BOPMegaTreeGrowerImpl(secondaryChance,
        megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
  }

  private static BOPTreeGrowerImpl register(
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree) {
    return new BOPTreeGrowerImpl(0.1F, tree, secondaryTree, Optional.empty(), Optional.empty());
  }

  private static BOPMegaTreeGrowerImpl register(
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree) {
    return register(0.1F, megaTree, secondaryMegaTree, tree, secondaryTree, Optional.empty(), Optional.empty());
  }

  public static final BOPTreeGrowerImpl CYPRESS = register(Optional.of(BOPTreeFeatures.CYPRESS_TREE), Optional.empty());
  public static final BOPMegaTreeGrowerImpl DEAD = register(0.5F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.DEAD_TWIGLET_TREE), Optional.of(BOPTreeFeatures.SMALL_DEAD_TREE), Optional.empty(), Optional.empty());
  public static final BOPTreeGrowerImpl EMPYREAL = register(Optional.of(BOPTreeFeatures.EMPYREAL_TREE), Optional.empty());
  public static final BOPMegaTreeGrowerImpl FIR = register(Optional.of(BOPTreeFeatures.FIR_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.FIR_TREE_LARGE), Optional.empty());
  public static final BOPMegaTreeGrowerImpl FLOWERING_OAK = register(0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE), Optional.of(BOPTreeFeatures.FLOWERING_OAK_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE_BEES));
  public static final BOPTreeGrowerImpl HELLBARK = register(Optional.of(BOPTreeFeatures.HELLBARK_TREE), Optional.of(BOPTreeFeatures.BIG_HELLBARK_TREE));
  public static final BOPMegaTreeGrowerImpl JACARANDA = register(0.1F, Optional.empty(), Optional.empty(), Optional.of(BOPTreeFeatures.JACARANDA_TREE), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE), Optional.of(BOPTreeFeatures.JACARANDA_TREE_BEES), Optional.of(BOPTreeFeatures.BIG_JACARANDA_TREE_BEES));
  public static final BOPTreeGrowerImpl MAGIC = register(Optional.of(BOPTreeFeatures.MAGIC_TREE), Optional.of(BOPTreeFeatures.BIG_MAGIC_TREE));
  public static final BOPTreeGrowerImpl MAHOGANY = register(Optional.of(BOPTreeFeatures.MAHOGANY_TREE), Optional.empty());
  public static final BOPTreeGrowerImpl ORANGE_MAPLE = register(Optional.of(BOPTreeFeatures.ORANGE_MAPLE_TREE), Optional.of(BOPTreeFeatures.BIG_ORANGE_MAPLE_TREE));
  public static final BOPTreeGrowerImpl ORIGIN = register(Optional.of(BOPTreeFeatures.ORIGIN_TREE), Optional.of(BOPTreeFeatures.BIG_ORIGIN_TREE));
  public static final BOPTreeGrowerImpl PALM = register(Optional.of(BOPTreeFeatures.PALM_TREE), Optional.empty());
  public static final BOPTreeGrowerImpl PINE = register(Optional.of(BOPTreeFeatures.PINE_TREE), Optional.empty());
  public static final BOPTreeGrowerImpl RAINBOW_BIRCH = register(Optional.of(BOPTreeFeatures.RAINBOW_BIRCH_TREE), Optional.of(BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE));
  public static final BOPTreeGrowerImpl RED_MAPLE = register(Optional.of(BOPTreeFeatures.RED_MAPLE_TREE), Optional.of(BOPTreeFeatures.BIG_RED_MAPLE_TREE));
  public static final BOPMegaTreeGrowerImpl REDWOOD = register(Optional.of(BOPTreeFeatures.REDWOOD_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.REDWOOD_TREE_MEDIUM), Optional.of(BOPTreeFeatures.REDWOOD_TREE_LARGE));
  public static final BOPTreeGrowerImpl SNOWBLOSSOM = register(Optional.of(BOPTreeFeatures.SNOWBLOSSOM_TREE), Optional.empty());
  public static final BOPMegaTreeGrowerImpl UMBRAN = register(Optional.of(BOPTreeFeatures.UMBRAN_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.TALL_UMBRAN_TREE), Optional.empty());
  public static final BOPTreeGrowerImpl WILLOW = register(Optional.of(BOPTreeFeatures.WILLOW_TREE), Optional.of(BOPTreeFeatures.BAYOU_TREE));
  public static final BOPTreeGrowerImpl YELLOW_MAPLE = register(Optional.of(BOPTreeFeatures.YELLOW_MAPLE_TREE), Optional.of(BOPTreeFeatures.BIG_YELLOW_MAPLE_TREE));
}
