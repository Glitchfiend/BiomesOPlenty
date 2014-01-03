package biomesoplenty.common.biomes;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenField extends BOPBiome
{

	public BiomeGenField(int par1)
	{
		super(par1);
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		
		this.bopWorldFeatures.perChunk.bopFlowersPerChunk = 50;
	}
	
	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);
			
			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
			}
		}
	}
	
    @Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
    	return random.nextInt(7) == 0 ? new WorldGenBOPFlora(Blocks.red_flower, 7) : (random.nextInt(5) == 0 ? new WorldGenBOPFlora(Blocks.red_flower, 6) : (random.nextInt(3) == 0 ? new WorldGenBOPFlora(Blocks.red_flower, 5) : new WorldGenBOPFlora(Blocks.red_flower, 4)));
    }
}
