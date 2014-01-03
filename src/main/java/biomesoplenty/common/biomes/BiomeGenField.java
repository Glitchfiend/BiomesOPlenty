package biomesoplenty.common.biomes;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenField extends BOPBiome
{
	public BiomeGenField(int id)
	{
		super(id);
		
		/*customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.rosesPerChunk = 75;
		customBiomeDecorator.grassPerChunk = 8;
		
		customBiomeDecorator.bushesPerChunk = 8;
		customBiomeDecorator.berryBushesPerChunk = 5;
		customBiomeDecorator.wheatGrassPerChunk = 4;
		customBiomeDecorator.waterReedsPerChunk = 4;
		customBiomeDecorator.generatePumpkins = true;
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		*/
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
}
