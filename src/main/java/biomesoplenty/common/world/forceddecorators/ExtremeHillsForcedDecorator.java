package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class ExtremeHillsForcedDecorator extends ForcedDecorator
{
	public ExtremeHillsForcedDecorator()
	{
		this.bopWorldFeatures.bopFlowersPerChunk = 3;
        this.bopWorldFeatures.shrubsPerChunk = 1;
        this.bopWorldFeatures.leafPilesPerChunk = 3;
        this.bopWorldFeatures.deadLeafPilesPerChunk = 1;
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
        
        return flowerMap;
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);

        return grassMap;
    }
}
