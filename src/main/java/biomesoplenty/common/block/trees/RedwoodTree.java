package biomesoplenty.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.AbstractBigTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class RedwoodTree extends AbstractBigTree
{
   @Nullable
   @Override
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
   {
      return BOPBiomeFeatures.REDWOOD_TREE;
   }

   @Nullable
   @Override
   protected AbstractTreeFeature<NoFeatureConfig> getBigTreeFeature(Random random)
   {
	   return BOPBiomeFeatures.REDWOOD_TREE_MEDIUM;
   }
}