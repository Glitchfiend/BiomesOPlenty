package biomesoplenty.block.trees;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BOPTreeGrowerImpl extends AbstractTreeGrower implements BOPTreeGrower {

  private final float secondaryChance;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers;

  public BOPTreeGrowerImpl(float secondaryChance,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
    this.secondaryChance = secondaryChance;
    this.tree = tree;
    this.secondaryTree = secondaryTree;
    this.flowers = flowers;
    this.secondaryFlowers = secondaryFlowers;
  }

  @Override
  public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random,
      boolean hasFlowers) {
    if (random.nextFloat() < this.secondaryChance) {
      if (hasFlowers && this.secondaryFlowers.isPresent()) {
        return this.secondaryFlowers.get();
      }

      if (this.secondaryTree.isPresent()) {
        return this.secondaryTree.get();
      }
    }

    return hasFlowers && this.flowers.isPresent() ? this.flowers.get() : this.tree.orElse(null);
  }
}
