package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class ForestForcedDecorator extends ForcedDecorator
{
	public ForestForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 15);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 5);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 5);
        this.bopWorldFeatures.setFeature("riverCanePerChunk", 5);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 2);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 6);
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 1);
        this.bopWorldFeatures.setFeature("bushesPerChunk", 2);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 1);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 2);

        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 8);

        this.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}
}
