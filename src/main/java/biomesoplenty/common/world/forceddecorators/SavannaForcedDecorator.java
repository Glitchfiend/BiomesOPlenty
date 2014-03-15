package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class SavannaForcedDecorator extends ForcedDecorator
{
	public SavannaForcedDecorator()
	{
		this.bopWorldFeatures.leafPilesPerChunk = 10;
		this.bopWorldFeatures.deadLeafPilesPerChunk = 5;
		this.bopWorldFeatures.bopFlowersPerChunk = 10;
		this.bopWorldFeatures.bushesPerChunk = 3;
		this.bopWorldFeatures.waterReedsPerChunk = 5;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 7), 8);
        
        return flowerMap;
    }
}
