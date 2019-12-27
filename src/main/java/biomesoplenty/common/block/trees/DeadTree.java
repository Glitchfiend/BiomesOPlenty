package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class DeadTree extends TreeDefaultConfig
{
   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
   {
      return random.nextInt(10) == 0 ? BOPBiomeFeatures.DYING_TREE : BOPBiomeFeatures.SMALL_DEAD_TREE;
   }
}