package biomesoplenty.common.biomes;

import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.decoration.IBOPBiome;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public abstract class BOPBiome extends BiomeGenBase implements IBOPBiome
{
	protected BOPWorldFeatures bopWorldFeatures;
	
	public BOPBiome(int biomeID) 
	{
		super(biomeID);
		
		bopWorldFeatures = BOPDecorationManager.getOrCreateBiomeFeatures(biomeID);
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
	public BOPWorldFeatures getBiomeFeatures()
	{
		return bopWorldFeatures;
	}
}
