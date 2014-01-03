package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenHighland extends BOPBiome
{

	public BiomeGenHighland(int par1)
	{
		super(par1);
		/*
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.highGrassPerChunk = 25;
		customBiomeDecorator.grassPerChunk = 25;
		customBiomeDecorator.wheatGrassPerChunk = 10;
		customBiomeDecorator.potatoesPerChunk = -999;
		customBiomeDecorator.generateBoulders = false;
		customBiomeDecorator.carrotsPerChunk = 1;
		*/
	}
	
	/*
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

			Block block = Block.blocksList[var10]; 
			if (block != null && block.isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
			}
		}
	}
	*/
}
