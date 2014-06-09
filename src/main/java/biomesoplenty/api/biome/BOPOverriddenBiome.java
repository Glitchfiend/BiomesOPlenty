package biomesoplenty.api.biome;

public class BOPOverriddenBiome<T extends BOPBiomeDecorator> extends BOPBiome<T> 
{
	public BOPOverriddenBiome(int biomeID, Class<T> clazz) 
	{
		super(biomeID, clazz, false);
	}
}
