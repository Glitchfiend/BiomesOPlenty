package biomesoplenty.common.world.forceddecorators.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class TaigaForcedDecorator extends ForcedDecorator
{
	public TaigaForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 2);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 4);

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 2);
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 5);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
	}
}
