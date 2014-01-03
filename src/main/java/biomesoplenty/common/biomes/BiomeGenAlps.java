package biomesoplenty.common.biomes;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;

public class BiomeGenAlps extends BOPBiome
{
	private static final Height biomeHeight = new Height(2.0F, 3.0F);
	
	public BiomeGenAlps(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO:	setColor()
        this.setColor(13421772);
        this.setTemperatureRainfall(0.0F, 0.5F);
		
		this.topBlock = Blocks.stone;
		this.fillerBlock = Blocks.stone;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
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
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 8, 2);
			}
		}
	}
}
