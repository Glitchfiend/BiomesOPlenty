package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenGrassland extends BOPBiome
{

	@SuppressWarnings("unchecked")
	public BiomeGenGrassland(int par1)
	{
		super(par1);
		/*
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = 2;
		customBiomeDecorator.wheatGrassPerChunk = 1;
		customBiomeDecorator.reedsPerChunk = 35;
		customBiomeDecorator.mushroomsPerChunk = 20;
		customBiomeDecorator.waterLakesPerChunk = 15;
		customBiomeDecorator.portobellosPerChunk = 3;
		customBiomeDecorator.reedsBOPPerChunk = 5;
		customBiomeDecorator.waterReedsPerChunk = 2;
		customBiomeDecorator.generatePumpkins = false;
		spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 14, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 12, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 12, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 10, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
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
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 4, 2);
			}
		}
	}
	*/

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
	}
	*/

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	/*
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 8379261;
	 }

	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 8379261;
	 }
	 */
}
