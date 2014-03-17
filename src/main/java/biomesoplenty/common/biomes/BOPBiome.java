package biomesoplenty.common.biomes;

import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.decoration.IBOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public abstract class BOPBiome extends BiomeGenBase implements IBOPBiome
{
	protected BOPWorldFeatures bopWorldFeatures;
	
	public BOPBiome(int biomeID) 
	{
		super(biomeID);
		
		bopWorldFeatures = BOPDecorationManager.getBiomeFeatures(biomeID);
	}
	
    /*@Override
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
    }*/
    
    @Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
		if (weightedFlowerGen != null && !weightedFlowerGen.isEmpty())
		{
			return getRandomWeightedWorldGenerator(weightedFlowerGen);
		}
		else
		{
			return null;
		}
    }
    
    @Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		if (weightedGrassGen != null && !weightedGrassGen.isEmpty())
		{
			return getRandomWeightedWorldGenerator(weightedGrassGen);
		}
		else
		{
			return super.getRandomWorldGenForGrass(random);
		}
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
	public BOPWorldFeatures getBiomeFeatures()
	{
		return bopWorldFeatures;
	}
}
