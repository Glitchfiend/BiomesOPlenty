package biomesoplenty.common.biome.overworld.sub;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.common.biome.BOPSubBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;

public class BiomeGenCanyonRavine extends BOPSubBiome
{
	private static final Height biomeHeight = new Height(-0.1F, 0.4F);

	public BiomeGenCanyonRavine(int id)
	{
		super(id);
		
		this.zoom = 0.25D;
		this.threshold = 0.25D;
		
        this.setHeight(biomeHeight);
        this.setColor(9337689);
        this.setTemperatureRainfall(1.0F, 0.3F);

		this.spawnableCreatureList.clear();

		this.topBlock = BOPCBlocks.hardDirt;
		this.fillerBlock = BOPCBlocks.hardDirt;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.bromeliadsPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.grassSplatterPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 1D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(5) == 0 ? new WorldGenPineTree() : new WorldGenBOPShrub(Blocks.log2, Blocks.leaves2, 0, 0, 64, 256, BOPCBlocks.hardDirt);
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
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 11123300;
	}

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		
		return 11123300;
	}
}
