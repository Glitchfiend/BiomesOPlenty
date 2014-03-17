package biomesoplenty.common.biomes;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeGenBorealForest extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.2F, 0.4F);
	
	public BiomeGenBorealForest(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(10467185);
        this.setTemperatureRainfall(0.6F, 0.7F);
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		
		this.theBiomeDecorator.treesPerChunk = 20;
		this.theBiomeDecorator.grassPerChunk = 50;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 5);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 10);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 10);

        weightedFlowerGen.put(new WorldGenBOPDoubleFlora(4, 5), 10);

        weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 2D);
        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, false, 8, 2, false) : (random.nextInt(5) == 0 ? new WorldGenShrub(0,0) : (random.nextInt(3) == 0 ? new WorldGenOriginalTree(Blocks.log, BOPBlockHelper.get("leaves1"), 2, 0, false, 5, 3, false) : 
		(random.nextInt(3) == 0 ? worldGeneratorTrees : new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 13, 9, 2))));
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
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 10467185;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 13225573;
	}
}
