package biomesoplenty.common.world.decoration;

public class BOPWorldFeatures 
{
	public PerChunk perChunk = new PerChunk();
	public DoGeneration doGeneration = new DoGeneration();
	
	public class PerChunk
	{
		public int mudPerChunk = 0;
		public int riverCanePerChunk = 0;
		public int shrubsPerChunk = 0;
		public int bushesPerChunk = 0;
		public int cloverPatchesPerChunk = 0;
		public int lavenderPerChunk = 0;
		public int thornsPerChunk = 0;
		public int stalagmitesPerChunk = 3;
        public int stalactitesPerChunk = 6;
		public int desertSproutsPerChunk = 0;
		public int bromeliadsPerChunk = 0;
		public int waterReedsPerChunk = 0;
		public int wildCarrotsPerChunk = 0;
		public int doubleTallGrassPerChunk = 0;

		public int bopFlowersPerChunk = 0;
	}
	
	public class DoGeneration
	{
		public boolean generatePumpkins = true;
	}
}
