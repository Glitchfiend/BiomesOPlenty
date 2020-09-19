package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class MagicTree extends TreeDefaultConfig
{
	@Override
	protected Feature<? extends BaseTreeFeatureConfig> getFeature(Random random)
	{
		return (random.nextInt(10) == 0 ? BOPFeatures.BIG_MAGIC_TREE : BOPFeatures.MAGIC_TREE);
	}
}