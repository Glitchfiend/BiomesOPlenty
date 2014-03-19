package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.common.world.decoration.ForcedDecorator;

public class DesertForcedDecorator extends ForcedDecorator
{
	public DesertForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("tinyCactiPerChunk", 10);
        this.bopWorldFeatures.setFeature("generateQuicksand", true);
	}
}
