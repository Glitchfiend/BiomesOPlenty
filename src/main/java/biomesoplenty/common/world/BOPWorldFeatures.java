package biomesoplenty.common.world;

public class BOPWorldFeatures 
{
	public PerChunk perChunk = new PerChunk();
	public DoGeneration doGeneration = new DoGeneration();
	
	public class PerChunk
	{
		public int mudPerChunk;
		public int riverCanePerChunk;
		public int shrubsPerChunk;
		public int bushesPerChunk;
		public int cloverPatchesPerChunk;
		
		public int bopFlowersPerChunk;
	}
	
	public class DoGeneration
	{
		public boolean generatePumpkins = true;
	}
}
