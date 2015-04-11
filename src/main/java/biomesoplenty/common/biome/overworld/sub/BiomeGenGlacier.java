package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPSubBiome;

public class BiomeGenGlacier extends BOPSubBiome
{
	private static final Height biomeHeight = new Height(2.0F, 1.025F);
	
	public BiomeGenGlacier(int biomeID) 
	{
		super(biomeID);

		this.zoom = 0.25D;
		this.threshold = 0.25D;
		
        this.setHeight(biomeHeight);
		this.setColor(11582425);
        this.setTemperatureRainfall(0.0F, 0.2F);
        this.setEnableSnow();
		
		this.spawnableCreatureList.clear();
		
		this.topBlock = BOPCBlocks.hardIce;
		this.fillerBlock = BOPCBlocks.hardIce;
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

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 8, 2);
			}
		}
	}
}
