/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class FirTree extends AbstractMegaTreeGrower
{
   @Nullable
   @Override
   protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random)
   {
      return BOPTreeFeatures.FIR_TREE_LARGE.getHolder().orElseThrow();
   }

   @Nullable
   @Override
   protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      return BOPTreeFeatures.FIR_TREE.getHolder().orElseThrow();
   }
}