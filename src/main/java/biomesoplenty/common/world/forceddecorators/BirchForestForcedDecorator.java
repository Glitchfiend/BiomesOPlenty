package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class BirchForestForcedDecorator extends ForcedDecorator
{
	public BirchForestForcedDecorator()
	{
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 3);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 15);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 4);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 2);

        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 10);
        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);

        this.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.25D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.25D);
	}
}
