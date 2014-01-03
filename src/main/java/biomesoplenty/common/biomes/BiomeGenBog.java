package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBog extends BOPBiome
{
	public BiomeGenBog(int par1)
	{
		super(par1);
		/*
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 12;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.grassPerChunk = 5;
		customBiomeDecorator.bushesPerChunk = 6;
		customBiomeDecorator.mudPerChunk = 2;
		customBiomeDecorator.mudPerChunk2 = 2;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.algaePerChunk = 2;
		customBiomeDecorator.waterlilyPerChunk = 4;
		customBiomeDecorator.reedsBOPPerChunk = 8;
		customBiomeDecorator.blueMilksPerChunk = 1;
		customBiomeDecorator.waterLakesPerChunk = 6;
		customBiomeDecorator.wheatGrassPerChunk = 3;
		customBiomeDecorator.poisonWaterPerChunk = 2;
		customBiomeDecorator.waterReedsPerChunk = 8;
		customBiomeDecorator.koruPerChunk = 1;
		customBiomeDecorator.shrubsPerChunk = 10;
		customBiomeDecorator.generatePumpkins = false;
		*/
	}

	/*
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenMarsh var5 = new WorldGenMarsh();
		
		 int var55 = 12 + par2Random.nextInt(6);

		for (int var66 = 0; var66 < var55; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16);
			int var88 = par2Random.nextInt(28) + 4;
			int var99 = par4 + par2Random.nextInt(16);
			int var100 = par1World.getBlockId(var77, var88, var99);

			if (var100 == Block.stone.blockID)
			{
				par1World.setBlock(var77, var88, var99, Blocks.amethystOre.get().blockID, 10, 2);
			}
		}

		for (int var6 = 0; var6 < 10; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16) + 8;
			byte var8 = 62;
			int var9 = par4 + par2Random.nextInt(16) + 8;
			var5.generate(par1World, par2Random, var7, var8, var9);
		}
	}
	*/

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		 return par1Random.nextInt(3) == 0 ? new WorldGenCypress1(false) : (par1Random.nextInt(6) == 0 ? new WorldGenCypress2(false) : new WorldGenBogBush());
	}
	*/

	 /**
	  * Gets a WorldGen appropriate for this biome.
	  */
	/*
	 @Override
	 public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	 {
		 return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2);
	 }
	 */

	 /**
	  * Provides the basic grass color based on the biome temperature and rainfall
	  */
	/*
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 14193503;
	 }
	 */

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	/*
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 14345593;
	 }
	 */
}
