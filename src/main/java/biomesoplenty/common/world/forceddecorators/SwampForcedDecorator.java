package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

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
		this.bopWorldFeatures.leafPilesPerChunk = 2;
		this.bopWorldFeatures.deadLeafPilesPerChunk = 4;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);
        
        return flowerMap;
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);

        return grassMap;
    }
}
