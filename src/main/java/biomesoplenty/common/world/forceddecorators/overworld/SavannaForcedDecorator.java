package biomesoplenty.common.world.forceddecorators.overworld;

import net.minecraft.init.Blocks;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class SavannaForcedDecorator extends ForcedDecorator
{
	public SavannaForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("bushesPerChunk", 3);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 5);

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 10);
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 20);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 7), 8);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}
}
