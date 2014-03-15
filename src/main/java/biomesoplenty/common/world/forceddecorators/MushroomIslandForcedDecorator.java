package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class MushroomIslandForcedDecorator extends ForcedDecorator
{
	public MushroomIslandForcedDecorator()
	{
		this.bopWorldFeatures.blueMilksPerChunk = 2;
		this.bopWorldFeatures.toadstoolsPerChunk = 8;
		this.bopWorldFeatures.portobellosPerChunk = 6;
	}
}
