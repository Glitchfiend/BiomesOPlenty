package biomesoplenty.common.world.forceddecorators;

import biomesoplenty.common.world.decoration.ForcedDecorator;

public class MushroomIslandForcedDecorator extends ForcedDecorator
{
	public MushroomIslandForcedDecorator(int id)
	{
        super(id);

        this.bopWorldFeatures.setFeature("blueMilksPerChunk", 2);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 8);
        this.bopWorldFeatures.setFeature("portobellosPerChunk", 6);
	}
}
