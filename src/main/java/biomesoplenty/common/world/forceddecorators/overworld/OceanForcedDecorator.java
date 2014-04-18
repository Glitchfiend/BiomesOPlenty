package biomesoplenty.common.world.forceddecorators.overworld;

import biomesoplenty.common.world.decoration.ForcedDecorator;

public class OceanForcedDecorator extends ForcedDecorator
{
	public OceanForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("seaweedPerChunk", 20);
	}
}
