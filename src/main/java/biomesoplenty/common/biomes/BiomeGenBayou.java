package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBayou extends BiomeGenBase
{

	public BiomeGenBayou(int par1)
	{
		super(par1);
		/*
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 15;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.reedsPerChunk = 25;
		customBiomeDecorator.mudPerChunk = 1;
		customBiomeDecorator.mudPerChunk2 = 1;
		customBiomeDecorator.toadstoolsPerChunk = 2;
		customBiomeDecorator.mushroomsPerChunk = 4;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.waterlilyPerChunk = 2;
		customBiomeDecorator.cattailsPerChunk = 1;
		customBiomeDecorator.highCattailsPerChunk = 1;
		customBiomeDecorator.waterLakesPerChunk = 5;
		customBiomeDecorator.algaePerChunk = 1;
		customBiomeDecorator.shrubsPerChunk = 2;
		customBiomeDecorator.wheatGrassPerChunk = 7;
		customBiomeDecorator.waterReedsPerChunk = 4;
		customBiomeDecorator.koruPerChunk = 1;
		customBiomeDecorator.generatePumpkins = false;
		waterColorMultiplier = 16767282;
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
		*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return par1Random.nextInt(8) == 0 ? new WorldGenBayou3() : (par1Random.nextInt(2) == 0 ? new WorldGenBayou1() : new WorldGenBayou2());
	 }

	 @Override
	 public void decorate(World par1World, Random par2Random, int par3, int par4)
	 {
		 super.decorate(par1World, par2Random, par3, par4);
		 WorldGenMoss var5 = new WorldGenMoss();
		 
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
		 return 9154411;
	 }
	 */

	 /**
	  * Provides the basic foliage color based on the biome temperature and rainfall
	  */
	/*
	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 11591816;
	 }
	 
	@Override
	public int getFogColour()
	{
		return 9482133;
	}
	
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
    */

	 /**
	  * takes temperature, returns color
	  */
	/*
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors)
			 return 11322556;
		 else
		 {
			 par1 /= 3.0F;

			 if (par1 < -1.0F)
			 {
				 par1 = -1.0F;
			 }

			 if (par1 > 1.0F)
			 {
				 par1 = 1.0F;
			 }

			 return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		 }
	 }
	 */
}
