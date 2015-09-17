package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenLavenderFields extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.125F, 0.05F);
	
	public BiomeGenLavenderFields(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(11035852);
        this.setTemperatureRainfall(0.6F, 0.7F);

		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 20;

        this.theBiomeDecorator.bopFeatures.lavenderPerChunk = 999;
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? this.worldGeneratorBigTree : new WorldGenOriginalTree(BOPCBlocks.logs4, BOPCBlocks.leaves4, 2, 1);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(BOPCBlocks.foliage, 10);
	}
	
	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 10601325;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 10601325;
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(4))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 4, 2);
			}
		}
	}
}
