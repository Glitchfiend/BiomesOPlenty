package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class JungleForcedDecorator extends ForcedDecorator
{
	public JungleForcedDecorator()
	{
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("generatePumpkins", 10);
        this.bopWorldFeatures.setFeature("generatePumpkins", 15);
        this.bopWorldFeatures.setFeature("generatePumpkins", 1);

        this.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 5), 12);

        this.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 1D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}
}
