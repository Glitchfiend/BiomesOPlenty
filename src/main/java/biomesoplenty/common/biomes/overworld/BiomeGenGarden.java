package biomesoplenty.common.biomes.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.entities.EntityRosester;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenGiantFlower;

public class BiomeGenGarden extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.3F, 0.4F);
	
	public BiomeGenGarden(int biomeID) 
	{
		super(biomeID);

        this.setHeight(biomeHeight);
        this.setColor(7656308);
        this.setTemperatureRainfall(0.7F, 0.8F);

        this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityRosester.class, 10, 4, 4));
		
		this.topBlock = BOPCBlocks.longGrass;
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
        this.bopWorldFeatures.setFeature("sproutsPerChunk", 2);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 10);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
        this.bopWorldFeatures.setFeature("generateMelons", true);
		
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 20);
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 25);
        
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 0), 15);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 20);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 4), 8);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(0, 3), 2);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 1), 0.25D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 2), 0.25D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.75D);
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(6) == 0 ? new WorldGenGiantFlower(0) : (random.nextInt(6) == 0 ? new WorldGenGiantFlower(1) : new WorldGenShrub(0, 0));
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
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 4, 2);
			}
		}
	}

	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 7656308;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 6742630;
	}
}
