package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenFir1;
import biomesoplenty.worldgen.WorldGenFir2;
import biomesoplenty.worldgen.WorldGenFir3;
import biomesoplenty.worldgen.WorldGenTaiga3;
import biomesoplenty.worldgen.WorldGenTaiga4;
import biomesoplenty.worldgen.WorldGenTaiga9;

public class BiomeGenConiferousForest extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenConiferousForest(int par1)
	{
		super(par1);
		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 6;
		customBiomeDecorator.grassPerChunk = 10;
		customBiomeDecorator.toadstoolsPerChunk = 3;
		customBiomeDecorator.blueMilksPerChunk = 1;
		customBiomeDecorator.poisonIvyPerChunk = 1;
		customBiomeDecorator.berryBushesPerChunk = 1;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		if (BOPConfiguration.Main.realisticTrees)
		{
			return par1Random.nextInt(5) == 0 ? new WorldGenFir1() : (par1Random.nextInt(3) == 0 ? new WorldGenFir2() : new WorldGenFir3());
		}
		
		return par1Random.nextInt(5) == 0 ? new WorldGenTaiga3(false) : (par1Random.nextInt(3) == 0 ? new WorldGenTaiga4(false) : new WorldGenTaiga9(false));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2);
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			if (var10 == Block.stone.blockID)
			{
				par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
			}
		}
	}
}
