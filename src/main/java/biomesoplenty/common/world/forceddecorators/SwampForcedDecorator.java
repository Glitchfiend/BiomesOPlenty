package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class SwampForcedDecorator extends ForcedDecorator
{
	public SwampForcedDecorator()
	{
		this.bopWorldFeatures.setFeature("mudPerChunk", 3);
		this.bopWorldFeatures.setFeature("seaweedPerChunk", 10);
		this.bopWorldFeatures.setFeature("cattailsPerChunk", 10);
		this.bopWorldFeatures.setFeature("highCattailsPerChunk", 5);
		this.bopWorldFeatures.setFeature("koruPerChunk", 25);
		this.bopWorldFeatures.setFeature("waterReedsPerChunk", 5);
		this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 1);
		this.bopWorldFeatures.setFeature("blueMilksPerChunk", 1);

        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);
	}
}
