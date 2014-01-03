package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class SwampForcedDecorator extends ForcedDecorator
{
	public SwampForcedDecorator()
	{
		this.bopWorldFeatures.perChunk.mudPerChunk = 3;
	}
}
