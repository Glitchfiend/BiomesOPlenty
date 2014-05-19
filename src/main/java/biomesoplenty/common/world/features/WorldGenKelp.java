package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenKelp extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{

		return false;
	}
	
	@Override
	public void setupGeneration(World world, Random random, BiomeGenBase biome, String featureName, int x, int z) 
	{
		// TODO Auto-generated method stub
		
	}

}
