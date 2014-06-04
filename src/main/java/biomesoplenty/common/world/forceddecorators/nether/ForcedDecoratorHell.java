package biomesoplenty.common.world.forceddecorators.nether;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class ForcedDecoratorHell extends ForcedDecorator
{
	public ForcedDecoratorHell(int id)
	{
		super(id);
		
        this.bopWorldFeatures.setFeature("gravesPerChunk", 1);
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 4);
        
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers2, 2), 10);
	}
}
