package biomesoplenty.biomes.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.biomes.BiomeDecoratorBOP;
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
		customBiomeDecorator.carrotsPerChunk = 1;
		customBiomeDecorator.wheatGrassPerChunk = 1;
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
		int var5 = 3 + par2Random.nextInt(6);
		WorldGenMoss var999 = new WorldGenMoss();

		for (int var66 = 0; var66 < 20; ++var66)
		{
			int var77 = par3 + par2Random.nextInt(16) + 8;
			byte var88 = 58;
			int var99 = par4 + par2Random.nextInt(16) + 8;
			var999.generate(par1World, par2Random, var77, var88, var99);
		}

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
			}
		}
	}
}
