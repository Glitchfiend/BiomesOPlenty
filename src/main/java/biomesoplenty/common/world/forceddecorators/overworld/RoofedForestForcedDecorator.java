package biomesoplenty.common.world.forceddecorators.overworld;

import net.minecraft.init.Blocks;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class RoofedForestForcedDecorator extends ForcedDecorator
{
	public RoofedForestForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 1);
        this.bopWorldFeatures.setFeature("blueMilksPerChunk", 1);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 8);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 4);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 5);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.25D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.25D);
	}
}
