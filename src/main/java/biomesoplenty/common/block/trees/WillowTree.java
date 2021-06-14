package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class WillowTree extends TreeDefaultConfig
{
   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
   {
      return BOPFeatures.WILLOW_TREE;
   }
}