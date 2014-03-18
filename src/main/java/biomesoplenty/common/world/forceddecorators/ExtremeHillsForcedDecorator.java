package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class ExtremeHillsForcedDecorator extends ForcedDecorator
{
	public ExtremeHillsForcedDecorator()
	{
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 3);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 1);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 3);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 1);


        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);

        this.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}
}
