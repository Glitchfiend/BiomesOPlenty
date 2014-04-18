package biomesoplenty.common.world.forceddecorators.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class IcePlainsForcedDecorator extends ForcedDecorator
{
	public IcePlainsForcedDecorator(int id)
    {
        super(id);

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 1);
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 5);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
	}
}
