package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class ForestForcedDecorator extends ForcedDecorator
{
	public ForestForcedDecorator()
	{
		this.bopWorldFeatures.leafPilesPerChunk = 15;
        this.bopWorldFeatures.deadLeafPilesPerChunk = 5;
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
