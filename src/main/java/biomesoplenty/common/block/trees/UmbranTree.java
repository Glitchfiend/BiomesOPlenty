package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPFeatures;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class UmbranTree extends BigTreeDefaultConfig
{
   @Override
   protected Feature<? extends TreeConfiguration> getFeature(Random random)
   {
      return BOPFeatures.UMBRAN_TREE;
   }

   @Override
   protected Feature<? extends TreeConfiguration> getBigFeature(Random random)
   {
      return BOPFeatures.TALL_UMBRAN_TREE;
   }
}