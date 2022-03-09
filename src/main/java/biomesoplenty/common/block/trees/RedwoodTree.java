package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RedwoodTree extends AbstractMegaTreeGrower
{
   @Nullable
   @Override
   protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(Random random)
   {
      return BOPTreeFeatures.REDWOOD_TREE_MEDIUM;
   }

   @Nullable
   @Override
   protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean flowers)
   {
      return BOPTreeFeatures.REDWOOD_TREE;
   }
}