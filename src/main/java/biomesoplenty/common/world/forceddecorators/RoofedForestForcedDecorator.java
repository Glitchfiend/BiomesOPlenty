package biomesoplenty.common.world.forceddecorators;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class RoofedForestForcedDecorator extends ForcedDecorator
{
	public RoofedForestForcedDecorator()
	{
		this.bopWorldFeatures.toadstoolsPerChunk = 1;
		this.bopWorldFeatures.blueMilksPerChunk = 1;
		this.bopWorldFeatures.leafPilesPerChunk = 8;
		this.bopWorldFeatures.deadLeafPilesPerChunk = 4;
		this.bopWorldFeatures.waterReedsPerChunk = 2;
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.25D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.25D);

        return grassMap;
    }
}
