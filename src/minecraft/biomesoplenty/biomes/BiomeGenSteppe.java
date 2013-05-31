package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class BiomeGenSteppe extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenSteppe(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.deadBushPerChunk = 7;
		customBiomeDecorator.tinyCactiPerChunk = 1;
		customBiomeDecorator.generateQuicksand = true;
		customBiomeDecorator.steppePerChunk = 6;
		customBiomeDecorator.aloePerChunk = 2;
		customBiomeDecorator.generatePumpkins = false;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 13413215;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 13413215;
	}
}
