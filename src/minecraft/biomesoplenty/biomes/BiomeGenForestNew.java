package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenMoss;

public class BiomeGenForestNew extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenForestNew(int par1)
	{
		super(par1);
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 10;
		customBiomeDecorator.grassPerChunk = 2;
		customBiomeDecorator.hydrangeasPerChunk = 2;
		customBiomeDecorator.whiteFlowersPerChunk = 1;
		customBiomeDecorator.reedsBOPPerChunk = 5;
		customBiomeDecorator.poisonIvyPerChunk = 2;
		customBiomeDecorator.sunflowersPerChunk = 1;
		customBiomeDecorator.berryBushesPerChunk = 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		//return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenWhiteOak1() : (par1Random.nextInt(5) == 0 ? new WorldGenAlder2() : (par1Random.nextInt(8) == 0 ? new WorldGenAlder1() : (par1Random.nextInt(4) == 0 ? new WorldGenPaperBirch2() : (par1Random.nextInt(7) == 0 ? new WorldGenPaperBirch1() : new WorldGenWhiteOak2())))));
		return par1Random.nextInt(5) == 0 ? worldGeneratorForest : (par1Random.nextInt(10) == 0 ? worldGeneratorBigTree : worldGeneratorTrees);
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		WorldGenMoss var5 = new WorldGenMoss();

		for (int var6 = 0; var6 < 20; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16) + 8;
			byte var8 = 58;
			int var9 = par4 + par2Random.nextInt(16) + 8;
			var5.generate(par1World, par2Random, var7, var8, var9);
		}
	}
}
