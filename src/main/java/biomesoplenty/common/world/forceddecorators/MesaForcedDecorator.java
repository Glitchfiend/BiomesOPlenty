package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.ForcedDecorator;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class MesaForcedDecorator extends ForcedDecorator
{
	public MesaForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("tinyCactiPerChunk", 10);
        this.bopWorldFeatures.setFeature("bromeliadsPerChunk", 10);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 5);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}
}
