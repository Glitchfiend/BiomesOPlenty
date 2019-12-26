package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class HellbarkTree extends TreeNoConfig
{
   @Override
   protected Feature<?> getFeature(Random random)
   {
      return BOPBiomeFeatures.HELLBARK_TREE;
   }
}