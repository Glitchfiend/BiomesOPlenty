package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class UmbranTree extends AbstractMegaTreeGrower
{
   @Override
   protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean flowers)
   {
      return BOPTreeFeatures.UMBRAN_TREE;
   }

   @Nullable
   @Override
   protected ConfiguredFeature<?, ?> getConfiguredMegaFeature(Random p_60004_)
   {
      return BOPTreeFeatures.TALL_UMBRAN_TREE;
   }
}