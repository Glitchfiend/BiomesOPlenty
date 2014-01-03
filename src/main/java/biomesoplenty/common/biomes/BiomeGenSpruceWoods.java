package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenSpruceWoods extends BOPBiome
{

	@SuppressWarnings("unchecked")
	public BiomeGenSpruceWoods(int par1)
	{
		super(par1);
		/*
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 10;
		customBiomeDecorator.grassPerChunk = 6;
		customBiomeDecorator.sproutsPerChunk = 3;
		customBiomeDecorator.mushroomsPerChunk = 4;
		customBiomeDecorator.poisonIvyPerChunk = 1;
		customBiomeDecorator.berryBushesPerChunk = 3;
		customBiomeDecorator.wheatGrassPerChunk = 10;
		customBiomeDecorator.carrotsPerChunk = 1;
		customBiomeDecorator.bluebellsPerChunk = 100;
		customBiomeDecorator.shrubsPerChunk = 5;
		customBiomeDecorator.waterReedsPerChunk = 2;
		*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenTaiga5(false) : new WorldGenTaiga2(false);
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
			}
		}
	}
	*/
}
