package biomesoplenty.common.world.forceddecorators.nether;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class ForcedDecoratorHell extends ForcedDecorator
{
	public ForcedDecoratorHell(int id)
	{
		super(id);
		
        //customBiomeDecorator.gravesPerChunk = 1;
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 4);
        
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers2"), 2), 10);
	}
}
