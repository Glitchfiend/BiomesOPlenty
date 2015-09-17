package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.utils.BiomeUtils;
import biomesoplenty.client.fog.IBiomeFog;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;

public class BiomeGenBambooForest extends BOPOverworldBiome implements IBiomeFog
{
	private static final Height biomeHeight = new Height(0.1F, 0.3F);
	
	public BiomeGenBambooForest(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(10739795);
        this.setTemperatureRainfall(1.2F, 0.9F);

		this.theBiomeDecorator.treesPerChunk = 30;
        this.theBiomeDecorator.grassPerChunk = 5;
        this.theBiomeDecorator.flowersPerChunk = -999;

		this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 6;
		this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 6;
		this.theBiomeDecorator.bopFeatures.bushesPerChunk = 5;
		this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 15;
		this.theBiomeDecorator.bopFeatures.algaePerChunk = 2;

		this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
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

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone) && BiomeUtils.oreWithMetaEnabled(6))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 6, 2);
			}
		}
	}

    @Override
    public void genTerrainBlocks(World world, Random random, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.topBlock = Blocks.dirt;
        this.field_150604_aj = 2;

        this.genBiomeTerrain(world, random, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenShrub(0, 0) : new WorldGenBulbTree(BOPCBlocks.bamboo, BOPCBlocks.leaves1, 0, 1, false, 10, 12, false);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPCBlocks.foliage, 10) : (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPCBlocks.foliage, 11) : (random.nextInt(8) == 0 ? new WorldGenBOPDoubleFlora(3) : new WorldGenTallGrass(BOPCBlocks.foliage, 1))));
	}

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
        double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
        return d0 < -0.7D ? 13949781 : (d0 < -0.3 ? 12311892 : 10739795);
    }

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
        return d0 < -0.7D ? 13949781 : (d0 < -0.3 ? 12311892 : 10739795);
	}

	public int getFogColour(int x, int y, int z)
	{
		return 13428852;
	}

    public float getFogDensity(int x, int y, int z)
    {
        return 0.99F;
    }
}
