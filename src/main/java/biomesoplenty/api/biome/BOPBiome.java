package biomesoplenty.api.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BOPBiome<T extends BOPBiomeDecorator> extends BiomeGenBase
{
	public final T theBiomeDecorator;
	
	public BOPBiome(int biomeID, Class<T> clazz, boolean register) 
	{
		super(biomeID, register);
		
		try 
		{
			this.theBiomeDecorator = clazz.newInstance();
		} 
		catch (Exception e) 
		{
			throw new RuntimeException();
		} 
		
		this.flowers.clear();
		this.addDefaultFlowers();

		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 0;
	}
	
	public BOPBiome(int biomeID, Class<T> clazz)
	{
		this(biomeID, clazz, true);
	}
	
	//TODO: Wrap tree method
	
    @Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
    {
        this.theBiomeDecorator.decorateChunk(world, random, this, chunkX, chunkZ);
    }
}
