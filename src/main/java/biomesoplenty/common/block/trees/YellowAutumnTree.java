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

public class YellowAutumnTree extends AbstractTreeGrower
{
   @Override
   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers)
   {
      return (random.nextInt(10) == 0 ? (random.nextInt(5) == 0 ? BOPTreeFeatures.ASPEN_TREE : BOPTreeFeatures.BIG_YELLOW_AUTUMN_TREE) : BOPTreeFeatures.YELLOW_AUTUMN_TREE);
   }
}