package biomesoplenty.block.trees;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public interface BOPMegaTreeGrower extends BOPTreeGrower {
  ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random);
}
