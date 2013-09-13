package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenChaparral1;
import biomesoplenty.worldgen.WorldGenChaparral2;
import biomesoplenty.worldgen.WorldGenChaparral3;

public class BiomeGenChaparral extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenChaparral(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 8;
		customBiomeDecorator.grassPerChunk = 20;
		customBiomeDecorator.bushesPerChunk = 10;
		customBiomeDecorator.berryBushesPerChunk = 2;
		customBiomeDecorator.generateStoneInGrass = true;
		customBiomeDecorator.generatePumpkins = false;
		customBiomeDecorator.carrotsPerChunk = 1;
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 12 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			if (var10 == Block.stone.blockID)
			{
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 4, 2);
			}
		}
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenChaparral2() : (par1Random.nextInt(5) == 0 ?  new WorldGenChaparral1(0, 0) : new WorldGenChaparral3());
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 12638301;
	 }
}
