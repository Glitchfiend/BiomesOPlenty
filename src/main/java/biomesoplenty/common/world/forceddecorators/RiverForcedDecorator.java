package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class RiverForcedDecorator extends ForcedDecorator
{
	public RiverForcedDecorator()
	{
		this.bopWorldFeatures.seaweedPerChunk = 5;
		this.bopWorldFeatures.riverCanePerChunk = 10;
		this.bopWorldFeatures.waterReedsPerChunk = 4;
	}
}
