package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.eventhandler.world.DecorateBiomeEventHandler;
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
			return (WorldGenBOPFlora)getRandomWeightedWorldGenerator(getWeightedWorldGenForBOPFlowers());
		}
		else
		{
			return null;
		}
    }
    
    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		if (getWeightedWorldGenForBOPFlowers() != null && !getWeightedWorldGenForGrass().isEmpty())
		{
			return getRandomWeightedWorldGenerator(getWeightedWorldGenForGrass());
		}
		else
		{
			return super.getRandomWorldGenForGrass(random);
		}
	}
    
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
    	return null;
    }
    
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
    	return null;
    }
    
    public static WorldGenerator getRandomWeightedWorldGenerator(HashMap<WorldGenerator, Double> worldGeneratorMap)
    {
    	double completeWeight = 0D;
    	
    	for (Double weight : worldGeneratorMap.values())
    	{
    		completeWeight += weight;
    	}
    	
    	double random = Math.random() * completeWeight;
    	double countWeight = 0D;
    	
    	for (Entry<WorldGenerator, Double> entry : worldGeneratorMap.entrySet())
    	{
    		countWeight += entry.getValue();
    		
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
