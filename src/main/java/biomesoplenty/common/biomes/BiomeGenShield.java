package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenShield extends BiomeGenBase
{

	public BiomeGenShield(int par1)
	{
		super(par1);
		/*
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 7;
		customBiomeDecorator.grassPerChunk = 12;
		customBiomeDecorator.wheatGrassPerChunk = 6;
		customBiomeDecorator.mushroomsPerChunk = 4;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.gravelPerChunk = 6;
		customBiomeDecorator.gravelPerChunk2 = 6;
		customBiomeDecorator.shrubsPerChunk = 4;
		customBiomeDecorator.waterReedsPerChunk = 4;
		customBiomeDecorator.generateStoneInGrass2 = true;
		*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenShrub(0,0) : (par1Random.nextInt(4) == 0 ? new WorldGenPineTree() : (par1Random.nextInt(6) == 0 ? new WorldGenTaiga9(false) : new WorldGenTaiga5(false)));
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenMoss var5 = new WorldGenMoss();

		for (int var6 = 0; var6 < 20; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16) + 8;
			byte var8 = 58;
			int var9 = par4 + par2Random.nextInt(16) + 8;
			var5.generate(par1World, par2Random, var7, var8, var9);
		}
	}
	*/

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	/*
	@Override
	public int getBiomeGrassColor()
	{
		return 6586168;
	}
	*/

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	/*
	@Override
	public int getBiomeFoliageColor()
	{
		return 7902787;
	}
	*/
}
