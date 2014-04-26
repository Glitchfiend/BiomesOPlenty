package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenChaparral3;
import biomesoplenty.common.world.features.trees.WorldGenCypress;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenTimber extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenTimber(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(10923366);
        this.setTemperatureRainfall(0.7F, 0.8F);

		this.theBiomeDecorator.treesPerChunk = 20;
		this.theBiomeDecorator.grassPerChunk = 8;
	    this.theBiomeDecorator.flowersPerChunk = -999;

        this.bopWorldFeatures.setFeature("thornsPerChunk", 2);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 2);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 4);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 10);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 12);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 8);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}


	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(4) == 0 ? new WorldGenChaparral3() : 
		(random.nextInt(8) == 0 ? new WorldGenMiniShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass, Blocks.sand) : 
		new WorldGenCypress(Blocks.log, Blocks.leaves, 0, 0, false, 10, 15, 2));
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
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 10923366;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 11049817;
	}
}
