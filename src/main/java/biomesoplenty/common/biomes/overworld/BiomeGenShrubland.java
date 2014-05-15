package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeGenShrubland extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.1F);
	
	public BiomeGenShrubland(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(8168286);
        this.setTemperatureRainfall(0.6F, 0.05F);
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));

		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 5;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 5);
        this.bopWorldFeatures.setFeature("bushesPerChunk", 7);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 5);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 3);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);
        
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 1);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 4);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return new WorldGenShrub(0, 0);
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
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
			}
		}
	}
}
