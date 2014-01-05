package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenMysticGrove extends BOPBiome
{

	public BiomeGenMysticGrove(int id)
	{
		super(id);
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 4, 4));
		
		this.waterColorMultiplier = 16715898;
		
		this.theBiomeDecorator.treesPerChunk = 9;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
		/*customBiomeDecorator.rosesPerChunk = 8;
		customBiomeDecorator.flowersPerChunk = 8;
		customBiomeDecorator.pinkFlowersPerChunk = 9;
		customBiomeDecorator.glowFlowersPerChunk = 10;
		customBiomeDecorator.sproutsPerChunk = 1;
		customBiomeDecorator.hydrangeasPerChunk = 6;
		customBiomeDecorator.blueMilksPerChunk = 1;
		customBiomeDecorator.lilyflowersPerChunk = 3;
		customBiomeDecorator.poisonWaterPerChunk = 1;*/
		
		this.bopWorldFeatures.perChunk.cloverPatchesPerChunk = 10;
		this.bopWorldFeatures.perChunk.shrubsPerChunk = 4;

	}

	/*@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? new WorldGenMystic2(false) : (par1Random.nextInt(3) == 0 ? new WorldGenOriginalTree(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("leaves4"), 2, 1) : ((par1Random.nextInt(3) == 0 ? this.worldGeneratorBigTree : ((par1Random.nextInt(8) == 0 ? new WorldGenSwampTree(Blocks.log, Blocks.leaves, 0, 0, 6, 8) : this.worldGeneratorTrees)))));
	}*/


	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return (random.nextInt(5) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 1) : (random.nextInt(3) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 2) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11) : new WorldGenTallGrass(Blocks.tallgrass, 1)))));
	}

	@Override
	//TODO:		getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6934491;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 7397529;
	}
	
	/**
	 * Fog Color
	 */
	/*
	@Override
	public int getFogColour()
	{
		return 16755401;
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
			return 8972496;
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

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
    */
}
