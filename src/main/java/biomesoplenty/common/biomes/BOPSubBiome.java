package biomesoplenty.common.biomes;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.common.world.layer.GenLayerSubBiome;

public abstract class BOPSubBiome extends BOPBiome
{
	public int minRadius;
	public int maxRadius;
	
	public List parents;
	
	public BOPSubBiome(int biomeID) 
	{
		super(biomeID);
		
		this.minRadius = 2;
		this.maxRadius = 5;
		this.parents = new ArrayList();
	}
}
