package biomesoplenty.common.block.trees;

import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FloweringOakTree extends AbstractTreeGrower
{
   @Nullable
   @Override
   protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean flowers)
   {
      return (random.nextInt(10) == 0 ? BOPTreeFeatures.BIG_FLOWERING_OAK_TREE : BOPTreeFeatures.FLOWERING_OAK_TREE);
   }
}