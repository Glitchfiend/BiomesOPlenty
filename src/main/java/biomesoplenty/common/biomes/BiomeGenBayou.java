package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenMoss;

public class BiomeGenBayou extends BOPBiome
{

	public BiomeGenBayou(int id)
	{
		super(id);

		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		waterColorMultiplier = 16767282;

		this.theBiomeDecorator.treesPerChunk = 15;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = 25;
		this.theBiomeDecorator.mushroomsPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		//this.theBiomeDecorator.waterLakesPerChunk = 5;

		/*customBiomeDecorator.mudPerChunk = 1;
		customBiomeDecorator.mudPerChunk2 = 1;
		customBiomeDecorator.toadstoolsPerChunk = 2;

		customBiomeDecorator.waterlilyPerChunk = 2;
		customBiomeDecorator.cattailsPerChunk = 1;
		customBiomeDecorator.highCattailsPerChunk = 1;
		customBiomeDecorator.algaePerChunk = 1;
		customBiomeDecorator.shrubsPerChunk = 2;
		customBiomeDecorator.wheatGrassPerChunk = 7;
		customBiomeDecorator.waterReedsPerChunk = 4;
		customBiomeDecorator.koruPerChunk = 1;*/
		//TODO: FEATURE ? customBiomeDecorator.generatePumpkins = false;
	}

	/*@Override
	public WorldGenAbstractTree getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(8) == 0 ? new WorldGenBayou3() : (par1Random.nextInt(2) == 0 ? new WorldGenBayou1() : new WorldGenBayou2());
	}*/

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
			}
		}

		for (int i = 0; i < 20; i++)
		{
			int x = chunkX + random.nextInt(16);
			int y = 58;
			int z = chunkZ + random.nextInt(16);

			new WorldGenMoss().generate(world, random, x, y, z);
		}
	}

	@Override
	//TODO:		getBiomeGrassColor()
	public int func_150558_b(int x, int y, int z)
	{
		return 9154411;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 11591816;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 11322556;
		else return super.getSkyColorByTemp(par1);
	}

	/*@Override
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
}
