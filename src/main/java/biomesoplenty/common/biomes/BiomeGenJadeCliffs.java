package biomesoplenty.common.biomes;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.trees.WorldGenPineTree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenJadeCliffs extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.5F, 1.5F);
	
	public BiomeGenJadeCliffs(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(9096298);
        this.setTemperatureRainfall(0.5F, 0.1F);

		this.theBiomeDecorator.treesPerChunk = 12;
		this.theBiomeDecorator.grassPerChunk = 3;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 3);
        this.bopWorldFeatures.setFeature("wildCarrotsPerChunk", 1);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 4);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 2);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 3);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 6);
	}
	
	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenShrub(0, 1) : new WorldGenPineTree();
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 1) : new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10);
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
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 0, 2);
			}
		}
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 12045485;
		else return super.getSkyColorByTemp(par1);
	}

	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 8168808;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 9096298;
	}
}
