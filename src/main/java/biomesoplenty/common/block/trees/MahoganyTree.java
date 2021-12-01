package biomesoplenty.common.block.trees;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class MahoganyTree extends TreeDefaultConfig
{
   @Override
   protected Feature<? extends TreeConfiguration> getFeature(Random random)
   {
      return null;
// TODO:     return BOPFeatures.MAHOGANY_TREE;
   }
}