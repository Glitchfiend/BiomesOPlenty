package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenRainforest extends BOPBiome
{

	@SuppressWarnings("unchecked")
	public BiomeGenRainforest(int par1)
	{
		super(par1);
		/*
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 14;
		customBiomeDecorator.grassPerChunk = 25;
		customBiomeDecorator.pinkFlowersPerChunk = 2;
		customBiomeDecorator.flowersPerChunk = 25;
		customBiomeDecorator.rosesPerChunk = 10;
		customBiomeDecorator.mushroomsPerChunk = 25;
		customBiomeDecorator.orangeFlowersPerChunk = 6;
		customBiomeDecorator.wheatGrassPerChunk = 10;
		customBiomeDecorator.shrubsPerChunk = 5;
		customBiomeDecorator.generatePumpkins = false;
		customBiomeDecorator.cloverPatchesPerChunk = 20;
		spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
		*/
	}
	
	/*
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 12 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 6, 2);
			}
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
		return par1Random.nextInt(15) == 0 ? worldGeneratorForest : (par1Random.nextInt(5) == 0 ? worldGeneratorBigTree : new WorldGenRainforestTree1(false));
	}
	*/

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}
	*/

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	/*
	@Override
	public int getBiomeGrassColor()
	{
		return 1759340;
	}
	*/

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	/*
	@Override
	public int getBiomeFoliageColor()
	{
		return 1368687;
	}
	*/
}
