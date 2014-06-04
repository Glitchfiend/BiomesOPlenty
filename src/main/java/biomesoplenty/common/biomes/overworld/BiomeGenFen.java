package biomesoplenty.common.biomes.overworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga1;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree;

public class BiomeGenFen extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);
    
	public BiomeGenFen(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(12240001);
        this.setTemperatureRainfall(0.4F, 0.4F);
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 5);
        this.bopWorldFeatures.setFeature("cattailsPerChunk", 1);
        this.bopWorldFeatures.setFeature("highCattailsPerChunk", 1);
        this.bopWorldFeatures.setFeature("waterSpringsPerChunk", 99);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 2);
        this.bopWorldFeatures.setFeature("mudPerChunk", 1);
        this.bopWorldFeatures.setFeature("riverCanePerChunk", 5);
        this.bopWorldFeatures.setFeature("algaePerChunk", 3);
        this.bopWorldFeatures.setFeature("portobellosPerChunk", 1);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 10);
        this.bopWorldFeatures.setFeature("koruPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 7);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 15);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 15);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 6);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(2), 0.25D);
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenBOPTaiga2(Blocks.log2, BOPCBlocks.leaves2, 1, 0, false, 10, 12, 3) : 
		(random.nextInt(20) == 0 ? new WorldGenDeadTree() : 
		new WorldGenBOPTaiga1(Blocks.log2, Blocks.leaves2, 1, 1, false, 7, 5, 1));
	}

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		super.decorate(world, random, chunkX, chunkZ);
		int var5 = 12 + random.nextInt(6);

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				world.setBlock(x, y, z, BOPCBlocks.gemOre, 10, 2);
			}
		}

		for (int i = 0; i < 20; i++)
		{
			int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
			int z = chunkZ + random.nextInt(16) + 8;

			new WorldGenMoss().generate(world, random, x, y, z);
		}
	}

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 12240001;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 13547897;
	}

	/*@Override
	public int getFogColour()
	{
		return 12638463;
	}
	
	@Override
	public float getFogCloseness()
	{
	    // TODO Auto-generated method stub
	    return 0.8F;
	}
	*/
}
