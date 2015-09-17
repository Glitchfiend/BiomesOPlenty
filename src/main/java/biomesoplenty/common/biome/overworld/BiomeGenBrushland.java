package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBrush1;
import biomesoplenty.common.world.features.trees.WorldGenBrush2;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenBrushland extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenBrushland(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(13222271);
        this.setTemperatureRainfall(1.2F, 0.1F);

		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 6;
		this.theBiomeDecorator.flowersPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.thornsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 30;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.generateQuicksand = true;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 6;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 5);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(2) == 0 ? new WorldGenBrush2(Blocks.log, Blocks.leaves, 3, 0, Blocks.grass) : (random.nextInt(5) == 0 ?  new WorldGenBrush1() : new WorldGenMiniShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass, Blocks.sand));
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(2))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 2, 2);
			}
		}
	}

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 13222271;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 11716223;
	}
}
