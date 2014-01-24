package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public abstract class BOPBiome extends BiomeGenBase implements IBOPDecoration
{
	protected BOPWorldFeatures bopWorldFeatures;
	
	public BOPBiome(int biomeID) 
	{
		super(biomeID);
		
		bopWorldFeatures = new BOPWorldFeatures();
	}
	
    @Override
	public void decorate(World world, Random random, int x, int z)
    {
        try
        {
            super.decorate(world, random, x, z);
        }
        catch (Exception e)
        {
            Throwable cause = e.getCause();
            
            if (e.getMessage() != null && e.getMessage().equals("Already decorating!!") || (cause != null && cause.getMessage() != null && cause.getMessage().equals("Already decorating!!")))
            {
            }
            else
            {
                e.printStackTrace();
            }
        }
    }
    
    @Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
		if (getWeightedWorldGenForBOPFlowers() != null && !getWeightedWorldGenForBOPFlowers().isEmpty())
		{
			return getRandomWeightedWorldGenerator(getWeightedWorldGenForBOPFlowers());
		}
		else
		{
			return null;
		}
    }
    
    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		if (getWeightedWorldGenForGrass() != null && !getWeightedWorldGenForGrass().isEmpty())
		{
			return getRandomWeightedWorldGenerator(getWeightedWorldGenForGrass());
		}
		else
		{
			return super.getRandomWorldGenForGrass(random);
		}
	}
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
    	return null;
    }
    
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
    	return null;
    }
    
    public static <T extends WorldGenerator> T getRandomWeightedWorldGenerator(HashMap<T, ? extends Number> worldGeneratorMap)
    {
    	double completeWeight = 0D;
    	
    	for (Number weight : worldGeneratorMap.values())
    	{
    		completeWeight += Double.parseDouble(weight.toString());
    	}
    	
    	double random = Math.random() * completeWeight;
    	double countWeight = 0D;
    	
    	for (Entry<T, ? extends Number> entry : worldGeneratorMap.entrySet())
    	{
    		countWeight += Double.parseDouble(entry.getValue().toString());
    		
    		if (countWeight >= random) return entry.getKey();
    	}
    	
    	return null;
    }
    
	@Override
	public BOPWorldFeatures getWorldFeatures() 
	{
		return bopWorldFeatures;
	}
}
