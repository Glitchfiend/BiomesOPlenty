package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.tree.WorldGenPalmTree1;

public class BiomeGenOasis extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOasis(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sand.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.grassPerChunk = 8;
		customBiomeDecorator.wheatGrassPerChunk = 4;
		customBiomeDecorator.reedsPerChunk = 100;
		customBiomeDecorator.oasesPerChunk = 15;
		customBiomeDecorator.oasesPerChunk2 = 15;
		customBiomeDecorator.cactiPerChunk = 7;
		customBiomeDecorator.desertSproutsPerChunk = 3;
		customBiomeDecorator.tinyCactiPerChunk = 2;
		customBiomeDecorator.generatePumpkins = false;
		customBiomeDecorator.generateMelons = true;
		customBiomeDecorator.generateQuicksand = true;
		customBiomeDecorator.waterLakesPerChunk = 10;
		customBiomeDecorator.aloePerChunk = 4;
		customBiomeDecorator.hotSpringsPerChunk = 1;
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

			Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 6, 2);
			}
		}
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return new WorldGenPalmTree1();
	 }
}
