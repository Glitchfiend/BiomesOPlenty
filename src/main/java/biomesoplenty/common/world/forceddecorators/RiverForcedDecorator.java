package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.common.world.decoration.ForcedDecorator;

public class RiverForcedDecorator extends ForcedDecorator
{
	public RiverForcedDecorator()
	{
		this.bopWorldFeatures.setFeature("seaweedPerChunk", 5);
		this.bopWorldFeatures.setFeature("riverCanePerChunk", 10);
		this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
	}
}
