package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;
import biomesoplenty.common.world.features.trees.WorldGenPoplar;
import biomesoplenty.common.world.features.trees.WorldGenPoplar2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenGrove extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenGrove(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(5341009);
        this.setTemperatureRainfall(0.4F, 0.8F);

		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.theBiomeDecorator.grassPerChunk = 8;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 50);
        this.bopWorldFeatures.setFeature("sproutsPerChunk", 1);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 2);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 3);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 20);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 8);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 16);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 9), 6);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(5, 3), 4);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(2) == 0 ? new WorldGenMiniShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass, Blocks.sand) : random.nextInt(3) == 0 ? new WorldGenPoplar2() : new WorldGenPoplar();
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
            
            //TODO:             getBlock()
            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 0, 2);
            }
        }
    }

	@Override
    //TODO:     getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 5341009;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 6396257;
	}
}
