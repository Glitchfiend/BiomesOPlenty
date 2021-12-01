package biomesoplenty.common.block.trees;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class RedwoodTree extends BigTreeDefaultConfig
{
   @Override
   protected Feature<? extends TreeConfiguration> getFeature(Random random)
   {
      return null;
      // TODO: return BOPFeatures.REDWOOD_TREE;
   }

   @Override
   protected Feature<? extends TreeConfiguration> getBigFeature(Random random)
   {
      return null;
// TODO:     return BOPFeatures.REDWOOD_TREE_MEDIUM;
   }
}