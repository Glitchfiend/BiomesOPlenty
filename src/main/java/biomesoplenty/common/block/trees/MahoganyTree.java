package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class MahoganyTree extends TreeNoConfig
{
   @Override
   protected Feature<?> getFeature(Random random)
   {
      return BOPBiomeFeatures.MAHOGANY_TREE;
   }
}