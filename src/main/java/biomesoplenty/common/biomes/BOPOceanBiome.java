package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.BOPBiomeManager;

public abstract class BOPOceanBiome extends BOPSubBiome
{
	public BOPOceanBiome(int biomeID) 
	{
		super(biomeID);
		
		BOPBiomeManager.overworldOceanBiomes.add(this.biomeID);

        this.spawnableCreatureList.clear();
	}
}
