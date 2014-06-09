package biomesoplenty.common.biome;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.BOPBiomeManager;

public class BOPOceanBiome extends BOPSubBiome
{
	public BOPOceanBiome(int biomeID) 
	{
		super(biomeID);
		
		BOPBiomeManager.overworldOceanBiomes.add(this.biomeID);

        this.spawnableCreatureList.clear();
	}
}
