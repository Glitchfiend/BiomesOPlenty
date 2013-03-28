package tdwp_ftw.biomesop.helpers;

import java.util.Random;

import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import cpw.mods.fml.common.IWorldGenerator;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.worldgen.WorldGenPromisedLandPortal;

public class WorldGeneratorPromisedLandPortal implements IWorldGenerator 
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.dimensionId)
		{
			case 20: generatePromisedLand(world, random, chunkX*16, chunkZ*16);
			case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}



	private void generateSurface(World world, Random random, int blockX, int blockZ) 
	{
		(new WorldGenPromisedLandPortal()).generate(world, random, 0, 64, 0);
	}

	private void generatePromisedLand(World world, Random random, int blockX, int blockZ) 
	{
		(new WorldGenPromisedLandPortal()).generate(world, random, 0, 64, 0);
	}

}