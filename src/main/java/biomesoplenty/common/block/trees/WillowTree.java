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

public class WillowTree extends AbstractTreeGrower
{
   @Override
   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      return (random.nextInt(7) == 0 ? BOPTreeFeatures.CYPRESS_TREE : BOPTreeFeatures.WILLOW_TREE);
   }
}