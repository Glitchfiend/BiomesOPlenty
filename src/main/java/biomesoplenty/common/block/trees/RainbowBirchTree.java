package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class RainbowBirchTree extends AbstractTreeGrower
{
   @Override
   protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean flowers)
   {
      return (random.nextInt(10) == 0 ? BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE : BOPTreeFeatures.RAINBOW_BIRCH_TREE);
   }
}