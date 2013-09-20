package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.worldgen.tree.WorldGenOminous1;
import biomesoplenty.worldgen.tree.WorldGenOminous3;
import biomesoplenty.worldgen.tree.WorldGenOminous4;

public class BiomeGenOminousWoodsThick extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenOminousWoodsThick(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 14;
		customBiomeDecorator.grassPerChunk = 4;
		customBiomeDecorator.wheatGrassPerChunk = 2;
		customBiomeDecorator.highGrassPerChunk = 4;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.deathbloomsPerChunk = 2;
		customBiomeDecorator.mushroomsPerChunk = 8;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.thornsPerChunk = 14;
		customBiomeDecorator.poisonIvyPerChunk = 6;
		customBiomeDecorator.poisonWaterPerChunk = 5;
		waterColorMultiplier = 1973030;
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 5, 1, 2));
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 7, 1, 2));
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
		return par1Random.nextInt(5) == 0 ? new WorldGenOminous3(false) : (par1Random.nextInt(3) == 0 ? new WorldGenOminous4(false) : new WorldGenOminous1(false));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(6) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2);
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
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors)
			return 5069168;
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
