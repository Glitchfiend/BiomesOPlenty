package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class IcePlainsForcedDecorator extends ForcedDecorator
{
	public IcePlainsForcedDecorator()
	{
		this.bopWorldFeatures.bopFlowersPerChunk = 1;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
        
        return flowerMap;
    }
}
