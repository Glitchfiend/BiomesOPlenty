package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class JungleForcedDecorator extends ForcedDecorator
{
	public JungleForcedDecorator()
	{
		this.bopWorldFeatures.leafPilesPerChunk = 10;
		this.bopWorldFeatures.bopFlowersPerChunk = 10;
		this.bopWorldFeatures.seaweedPerChunk = 15;
		this.bopWorldFeatures.poisonIvyPerChunk = 1;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 5), 12);
        
        return flowerMap;
    }
}
