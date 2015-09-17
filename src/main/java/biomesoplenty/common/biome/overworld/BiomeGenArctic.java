package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;

public class BiomeGenArctic extends BOPOverworldBiome implements IBiomeFog
{
	private static final Height biomeHeight = new Height(0F, 0F);
	
	public BiomeGenArctic(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
        this.setEnableSnow();
        this.setColor(14540253);
        this.setTemperatureRainfall(0.05F, 0.5F);
		
		this.spawnableCreatureList.clear();
		
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
			
			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(8))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 8, 2);
			}
		}
	}

	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 11176526;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 11903827;
	}
	
	
	@Override
	public int getFogColour(int x, int y, int z)
	{
		return 12638463;
	}
	
	
	
    @Override
    public float getFogDensity(int x, int y, int z)
    {
        return 0.4F;
    }
    
}
