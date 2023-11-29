/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class FloweringOakTree extends AbstractTreeGrower
{
   @Nullable
   @Override
   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      if (random.nextInt(10) == 0)
      {
         return flowers ? BOPTreeFeatures.BIG_FLOWERING_OAK_TREE_BEES : BOPTreeFeatures.BIG_FLOWERING_OAK_TREE;
      }
      else
      {
         return flowers ? BOPTreeFeatures.FLOWERING_OAK_TREE_BEES : BOPTreeFeatures.FLOWERING_OAK_TREE;
      }
   }
}