package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import worldcore.interfaces.IWCFog;
import biomesoplenty.configuration.BOPConfigurationMisc;
import biomesoplenty.worldgen.tree.WorldGenDeadTree3;
import biomesoplenty.worldgen.tree.WorldGenOminous1;
import biomesoplenty.worldgen.tree.WorldGenOminous2;

public class BiomeGenOminousWoods extends BiomeGenBase implements IWCFog
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenOminousWoods(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 8;
		customBiomeDecorator.grassPerChunk = 1;
		customBiomeDecorator.wheatGrassPerChunk = 1;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.deathbloomsPerChunk = 1;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.thornsPerChunk = 9;
		customBiomeDecorator.poisonIvyPerChunk = 3;
		customBiomeDecorator.poisonWaterPerChunk = 15;
		waterColorMultiplier = 1973030;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 5, 1, 2));
		spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 4));
		spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		//return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenWillow2() : (par1Random.nextInt(7) == 0 ? new WorldGenDarkTree1() : (par1Random.nextInt(5) == 0 ? new WorldGenWillow1() : new WorldGenDarkTree2())));
		return par1Random.nextInt(2) == 0 ? new WorldGenOminous1(false) : (par1Random.nextInt(6) == 0 ? new WorldGenDeadTree3(false) : new WorldGenOminous2());
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(6) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 4145489;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 4145489;
	}
	
	/**
	 * Fog Color
	 */
	@Override
	public int getFogColour()
	{
		return 3420989;
	}

	/**
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors)
			return 5522002;
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
        return 0.1F;
    }
}
