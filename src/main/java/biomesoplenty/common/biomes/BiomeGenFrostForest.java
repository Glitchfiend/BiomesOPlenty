package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BiomeGenFrostForest extends BOPBiome
{

	public BiomeGenFrostForest(int id)
	{
		super(id);
		
		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		
		this.bopWorldFeatures.perChunk.shrubsPerChunk = 1;
		this.bopWorldFeatures.perChunk.bopFlowersPerChunk = 3;
		/*customBiomeDecorator.icyIrisPerChunk = 3;
		customBiomeDecorator.wheatGrassPerChunk = 1;
		customBiomeDecorator.violetsPerChunk = 1;*/
		this.bopWorldFeatures.doGeneration.generatePumpkins = false;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return worldGeneratorTrees;
	}
	
    @Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
    	return null;
    }
	
	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);
			
			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 8, 2);
			}
		}
	}

	@Override
	//TODO:		getBiomeGrassColor()
	public int func_150558_b(int x, int y, int z)
	{
		return 11261628;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 11261628;
	}
	
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 13557994;
		else return super.getSkyColorByTemp(par1);
	}
	
	/*@Override
	public int getFogColour()
	{
		return 12239814;
	}
	
    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
    */
}
