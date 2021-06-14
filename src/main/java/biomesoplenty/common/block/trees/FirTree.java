package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class FirTree extends BigTreeDefaultConfig
{
   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
   {
      return BOPFeatures.FIR_TREE;
   }

   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getBigFeature(Random random)
   {
      return BOPFeatures.FIR_TREE_LARGE;
   }
}