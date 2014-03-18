package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class IcePlainsForcedDecorator extends ForcedDecorator
{
	public IcePlainsForcedDecorator()
    {
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 1);

        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
	}
}
