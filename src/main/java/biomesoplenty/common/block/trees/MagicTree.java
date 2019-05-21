package biomesoplenty.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.AbstractTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MagicTree extends AbstractTree
{
	   @Nullable
	   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
	   {
	      return (AbstractTreeFeature<NoFeatureConfig>)(random.nextInt(10) == 0 ? BOPBiomeFeatures.BIG_MAGIC_TREE : BOPBiomeFeatures.MAGIC_TREE);
	   }
}