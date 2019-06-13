package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class DeadTree extends Tree
{
   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
   {
      return (AbstractTreeFeature<NoFeatureConfig>)(random.nextInt(10) == 0 ? BOPBiomeFeatures.DYING_TREE : BOPBiomeFeatures.SMALL_DEAD_TREE);
   }
}