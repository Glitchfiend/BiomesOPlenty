package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class FirTree extends BigTreeDefaultConfig
{
   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
   {
      return BOPBiomeFeatures.FIR_TREE;
   }

   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getBigFeature(Random random)
   {
      return BOPBiomeFeatures.FIR_TREE_LARGE;
   }
}