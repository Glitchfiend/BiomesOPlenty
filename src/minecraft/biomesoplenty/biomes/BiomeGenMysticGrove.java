package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenMystic1;
import biomesoplenty.worldgen.WorldGenMystic2;
import biomesoplenty.worldgen.WorldGenSwampTall;

public class BiomeGenMysticGrove extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenMysticGrove(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 8;
		customBiomeDecorator.grassPerChunk = 7;
		customBiomeDecorator.flowersPerChunk = 8;
		customBiomeDecorator.pinkFlowersPerChunk = 6;
		customBiomeDecorator.glowFlowersPerChunk = 15;
		customBiomeDecorator.rosesPerChunk = 8;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.sproutsPerChunk = 3;
		customBiomeDecorator.hydrangeasPerChunk = 3;
		customBiomeDecorator.blueMilksPerChunk = 4;
		customBiomeDecorator.glowshroomsPerChunk = 2;
		customBiomeDecorator.lilyflowersPerChunk = 3;
		customBiomeDecorator.hotSpringsPerChunk = 2;
		customBiomeDecorator.poisonWaterPerChunk = 1;
		waterColorMultiplier = 15349914;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 4, 4));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(5) == 0 ? new WorldGenMystic2(false) : (par1Random.nextInt(7) == 0 ? new WorldGenSwampTall() : new WorldGenMystic1(false));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 6934491;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 7332553;
	}

	/**
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfiguration.skyColors = true)
			return 16751558;
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
}
