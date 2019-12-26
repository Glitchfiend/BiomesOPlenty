package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.Random;

public class UmbranTree extends AgnosticBigTree
{
   @Override
   protected Feature<?> getFeature(Random random)
   {
      return BOPBiomeFeatures.UMBRAN_TREE;
   }

   @Override
   protected Feature<?> getBigFeature(Random random)
   {
      return BOPBiomeFeatures.TALL_UMBRAN_TREE;
   }
}