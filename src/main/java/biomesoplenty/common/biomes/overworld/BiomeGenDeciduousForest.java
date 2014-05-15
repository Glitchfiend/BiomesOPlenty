package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeGenDeciduousForest extends BOPBiome
{
	//private static final Height biomeHeight = new Height(); Not set?
	
	public BiomeGenDeciduousForest(int id)
	{
		super(id);
		
        //this.setHeight(biomeHeight); Not set?
        //TODO:	setColor()
        this.setColor(12695369);
        this.setTemperatureRainfall(0.7F, 0.8F);
		
		this.theBiomeDecorator.treesPerChunk = 18;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = -999;

        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 1);
        this.bopWorldFeatures.setFeature("bushesPerChunk", 8);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 2);
        this.bopWorldFeatures.setFeature("blueMilksPerChunk", 2);
        this.bopWorldFeatures.setFeature("poisonIvyPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 10);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("algaePerChunk", 3);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 10);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(6) == 0 ? new WorldGenShrub(2,2) : (random.nextInt(3) == 0 ? new WorldGenShrub(0,0) : new WorldGenBulbTree(Blocks.log, Blocks.leaves, 0, 0, false, 10, 15, false));
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
				world.setBlock(x, y, z, Blocks.emerald_ore, 0, 2);
			}
		}
	}

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 12695369 : 11374145;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? 12896570 : 11510344;
	}
}
