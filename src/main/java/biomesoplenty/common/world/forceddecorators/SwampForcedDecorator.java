package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class SwampForcedDecorator extends ForcedDecorator
{
	public SwampForcedDecorator()
	{
		this.bopWorldFeatures.mudPerChunk = 3;
		this.bopWorldFeatures.seaweedPerChunk = 10;
		this.bopWorldFeatures.cattailsPerChunk = 10;
		this.bopWorldFeatures.highCattailsPerChunk = 5;
		this.bopWorldFeatures.koruPerChunk = 25;
		this.bopWorldFeatures.waterReedsPerChunk = 5;
		this.bopWorldFeatures.toadstoolsPerChunk = 1;
		this.bopWorldFeatures.blueMilksPerChunk = 1;
		this.bopWorldFeatures.leafPilesPerChunk = 5;
		this.bopWorldFeatures.deadLeafPilesPerChunk = 2;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);
        
        return flowerMap;
    }
}
