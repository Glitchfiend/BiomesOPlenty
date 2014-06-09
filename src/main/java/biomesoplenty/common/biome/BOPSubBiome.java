package biomesoplenty.common.biome;

public class BOPSubBiome extends BOPOverworldBiome
{
	 /**Smaller numbers zoom in the noise field (biomes are less common)*/
	public double zoom;
	/**The strength the field must reach to replace the biome. Larger numbers result in smaller patches.*/
	public double threshold;
	
	public BOPSubBiome(int biomeID) 
	{
		super(biomeID);
	}
}
