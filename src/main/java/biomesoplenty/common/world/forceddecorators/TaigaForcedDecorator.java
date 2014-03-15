package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class TaigaForcedDecorator extends ForcedDecorator
{
	public TaigaForcedDecorator()
	{
		this.bopWorldFeatures.bopFlowersPerChunk = 2;
		this.bopWorldFeatures.leafPilesPerChunk = 2;
        this.bopWorldFeatures.deadLeafPilesPerChunk = 4;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
        
        return flowerMap;
    }
}
