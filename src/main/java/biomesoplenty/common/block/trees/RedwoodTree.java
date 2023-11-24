/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class RedwoodTree extends AbstractMegaTreeGrower
{
   @Nullable
   @Override
   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random)
   {
      return (random.nextInt(15) == 0 ? BOPTreeFeatures.REDWOOD_TREE_LARGE : BOPTreeFeatures.REDWOOD_TREE_MEDIUM);
   }

   @Nullable
   @Override
   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      return BOPTreeFeatures.REDWOOD_TREE;
   }
}