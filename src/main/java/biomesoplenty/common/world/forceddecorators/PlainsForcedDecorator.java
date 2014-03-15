package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class PlainsForcedDecorator extends ForcedDecorator
{
	public PlainsForcedDecorator()
	{
		this.bopWorldFeatures.bopFlowersPerChunk = 8;
		this.bopWorldFeatures.wildCarrotsPerChunk = 1;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 10);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 9), 5);
        
        return flowerMap;
    }
}
