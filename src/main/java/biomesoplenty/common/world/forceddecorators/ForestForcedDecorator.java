package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class ForestForcedDecorator extends ForcedDecorator
{
	public ForestForcedDecorator()
	{
		this.bopWorldFeatures.leafPilesPerChunk = 15;
		this.bopWorldFeatures.bopFlowersPerChunk = 5;
		this.bopWorldFeatures.cloverPatchesPerChunk = 5;
		this.bopWorldFeatures.riverCanePerChunk = 5;
		this.bopWorldFeatures.shrubsPerChunk = 2;
		this.bopWorldFeatures.waterReedsPerChunk = 6;
		this.bopWorldFeatures.poisonIvyPerChunk = 1;
		this.bopWorldFeatures.bushesPerChunk = 2;
		this.bopWorldFeatures.berryBushesPerChunk = 1;
		this.bopWorldFeatures.toadstoolsPerChunk = 2;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 8);
        
        return flowerMap;
    }
}
