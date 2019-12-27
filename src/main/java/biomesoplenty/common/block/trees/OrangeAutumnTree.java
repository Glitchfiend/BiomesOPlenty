package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class OrangeAutumnTree extends TreeDefaultConfig
{
   @Override
   protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
   {
      return (random.nextInt(10) == 0 ? BOPBiomeFeatures.BIG_ORANGE_AUTUMN_TREE : BOPBiomeFeatures.ORANGE_AUTUMN_TREE);
   }
}