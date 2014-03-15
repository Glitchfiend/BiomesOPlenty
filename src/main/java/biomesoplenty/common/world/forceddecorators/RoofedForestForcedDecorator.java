package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class RoofedForestForcedDecorator extends ForcedDecorator
{
	public RoofedForestForcedDecorator()
	{
		this.bopWorldFeatures.toadstoolsPerChunk = 1;
		this.bopWorldFeatures.blueMilksPerChunk = 1;
		this.bopWorldFeatures.leafPilesPerChunk = 8;
		this.bopWorldFeatures.deadLeafPilesPerChunk = 4;
		this.bopWorldFeatures.waterReedsPerChunk = 2;
	}
}
