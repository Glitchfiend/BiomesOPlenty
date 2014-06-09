package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.biome.decoration.BOPOverworldBiomeDecorator;

public class BOPOverworldBiome extends BOPBiome<BOPOverworldBiomeDecorator>
{
	public BOPOverworldBiome(int biomeID) 
	{
		super(biomeID, BOPOverworldBiomeDecorator.class);
	}
}
