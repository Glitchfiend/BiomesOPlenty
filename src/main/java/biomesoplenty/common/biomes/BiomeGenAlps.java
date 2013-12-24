package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenAlps extends BiomeGenBase
{
	//private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenAlps(int id)
	{
		super(id);
		
		this.topBlock = Blocks.stone;
		this.fillerBlock = Blocks.stone;
		/*theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = -999;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;*/
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

			/*TODO: FEATURE if (block != null && block.isGenMineableReplaceable(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, Blocks.amethystOre.get().blockID, 8, 2);
			}*/
		}
	}
}
