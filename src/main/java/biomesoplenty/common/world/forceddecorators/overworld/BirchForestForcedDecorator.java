package biomesoplenty.common.world.forceddecorators.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class BirchForestForcedDecorator extends ForcedDecorator
{
	public BirchForestForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 3);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 15);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 4);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 2);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 5);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 10);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 15);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.25D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.25D);
	}
}
