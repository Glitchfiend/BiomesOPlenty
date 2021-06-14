package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class PalmTree extends TreeDefaultConfig
{
   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
   {
      return BOPFeatures.PALM_TREE;
   }
}