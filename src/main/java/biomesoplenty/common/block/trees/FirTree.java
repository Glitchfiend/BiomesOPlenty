package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class FirTree extends BigTree
{
   @Nullable
   @Override
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
   {
      return BOPBiomeFeatures.FIR_TREE;
   }

   @Nullable
   @Override
   protected AbstractTreeFeature<NoFeatureConfig> getBigTreeFeature(Random random)
   {
      return BOPBiomeFeatures.FIR_TREE_LARGE;
   }
}