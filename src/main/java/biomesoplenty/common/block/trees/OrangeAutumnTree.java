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

public class OrangeAutumnTree extends AbstractTreeGrower
{
   @Override
   protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      return (random.nextInt(10) == 0 ? BOPTreeFeatures.BIG_ORANGE_AUTUMN_TREE.getHolder().orElseThrow() : BOPTreeFeatures.ORANGE_AUTUMN_TREE.getHolder().orElseThrow());
   }
}