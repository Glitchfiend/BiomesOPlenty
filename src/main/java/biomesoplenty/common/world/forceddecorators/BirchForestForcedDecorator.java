package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BirchForestForcedDecorator extends ForcedDecorator
{
	public BirchForestForcedDecorator()
	{
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 3);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 15);

        weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 10);
        weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);
	}
}
