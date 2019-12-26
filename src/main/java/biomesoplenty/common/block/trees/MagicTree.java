package biomesoplenty.common.block.trees;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class MagicTree extends TreeNoConfig
{
	@Override
	protected Feature<?> getFeature(Random random)
	{
		return (random.nextInt(10) == 0 ? BOPBiomeFeatures.BIG_MAGIC_TREE : BOPBiomeFeatures.MAGIC_TREE);
	}
}