/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RainbowBirchTree extends AbstractTreeGrower
{
   @Override
   protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      return (random.nextInt(10) == 0 ? BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE.getHolder().orElseThrow() : BOPTreeFeatures.RAINBOW_BIRCH_TREE.getHolder().orElseThrow());
   }
}