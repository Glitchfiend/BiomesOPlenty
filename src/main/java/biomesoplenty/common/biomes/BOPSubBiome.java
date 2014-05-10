package biomesoplenty.common.biomes;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.common.world.layer.GenLayerSubBiome;

public abstract class BOPSubBiome extends BOPBiome
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
