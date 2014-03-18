package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.common.world.decoration.ForcedDecorator;

public class DesertForcedDecorator extends ForcedDecorator
{
	public DesertForcedDecorator()
	{
        this.bopWorldFeatures.setFeature("tinyCactiPerChunk", 10);
        this.bopWorldFeatures.setFeature("generateQuicksand", true);
	}
}
