package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.Random;

public class MapleTree extends AgnosticTree
{
   @Override
   protected Feature<?> getFeature(Random random)
   {
      return (random.nextInt(10) == 0 ? BOPBiomeFeatures.BIG_MAPLE_TREE : BOPBiomeFeatures.MAPLE_TREE);
   }
}