package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenConiferousForest extends BiomeGenBase
{

	@SuppressWarnings("unchecked")
	public BiomeGenConiferousForest(int par1)
	{
		super(par1);
		/*
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 8;
		customBiomeDecorator.grassPerChunk = 10;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.toadstoolsPerChunk = 3;
		customBiomeDecorator.blueMilksPerChunk = 1;
		customBiomeDecorator.poisonIvyPerChunk = 1;
		customBiomeDecorator.berryBushesPerChunk = 1;
		customBiomeDecorator.wheatGrassPerChunk = 5;
		customBiomeDecorator.shrubsPerChunk = 8;
		customBiomeDecorator.waterReedsPerChunk = 2;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.gravelPerChunk = 1;
		customBiomeDecorator.gravelPerChunk2 = 1;
		customBiomeDecorator.cloverPatchesPerChunk = 10;
		*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenTaiga3(false) : (par1Random.nextInt(5) == 0 ? new WorldGenTaiga4(false) : new WorldGenTaiga9(false));
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
