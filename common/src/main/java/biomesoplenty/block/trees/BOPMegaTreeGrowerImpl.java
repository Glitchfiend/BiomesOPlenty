package biomesoplenty.block.trees;

import java.util.Optional;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BOPMegaTreeGrowerImpl extends AbstractMegaTreeGrower implements BOPMegaTreeGrower {

  private final float secondaryChance;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers;
  private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers;

  public BOPMegaTreeGrowerImpl(float secondaryChance,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
      Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
    this.secondaryChance = secondaryChance;
    this.megaTree = megaTree;
    this.secondaryMegaTree = secondaryMegaTree;
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

  @Override
  public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource $$0) {
    return this.secondaryMegaTree.isPresent() && $$0.nextFloat() < this.secondaryChance
        ? this.secondaryMegaTree.get() : this.megaTree.orElse(null);
  }
}
