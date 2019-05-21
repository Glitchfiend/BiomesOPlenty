package biomesoplenty.common.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.AbstractTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class OrangeAutumnTree extends AbstractTree
{
   @Nullable
   protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
   {
      return BOPBiomeFeatures.ORANGE_AUTUMN_TREE;
   }
}