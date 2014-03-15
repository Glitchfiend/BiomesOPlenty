package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BirchForestForcedDecorator extends ForcedDecorator
{
	public BirchForestForcedDecorator()
	{
		this.bopWorldFeatures.poisonIvyPerChunk = 3;
		this.bopWorldFeatures.cloverPatchesPerChunk = 15;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 10);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);
        
        return flowerMap;
    }
}
