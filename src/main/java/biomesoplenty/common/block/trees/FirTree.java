package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FirTree extends AbstractMegaTreeGrower
{
   @Nullable
   @Override
   protected ConfiguredFeature<?, ?> getConfiguredMegaFeature(Random random)
   {
      return BOPTreeFeatures.FIR_TREE_LARGE;
   }

   @Nullable
   @Override
   protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean flowers)
   {
      return BOPTreeFeatures.FIR_TREE;
   }
}