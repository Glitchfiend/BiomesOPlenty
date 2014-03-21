package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenWetland extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.2F);

    public BiomeGenWetland(int id)
    {
        super(id);

        this.setHeight(biomeHeight);
        this.setColor(5215831);
        this.setTemperatureRainfall(0.8F, 0.9F);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

        this.waterColorMultiplier = 6512772;

        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.reedsPerChunk = 15;
        this.theBiomeDecorator.clayPerChunk = 2;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        this.theBiomeDecorator.waterlilyPerChunk = 4;

        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 1);
        this.bopWorldFeatures.setFeature("riverCanePerChunk", 15);
        this.bopWorldFeatures.setFeature("mudPerChunk", 5);
        this.bopWorldFeatures.setFeature("cattailsPerChunk", 20);
        this.bopWorldFeatures.setFeature("highCattailsPerChunk", 10);
        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 6);
        this.bopWorldFeatures.setFeature("blueMilksPerChunk", 1);
        this.bopWorldFeatures.setFeature("portobellosPerChunk", 1);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 10);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 8);
        this.bopWorldFeatures.setFeature("koruPerChunk", 1);
        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 15);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 15);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 5);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 10);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 1), 10);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPDoubleFlora(3), 0.75D);
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(2) == 0 ? new WorldGenBOPTaiga2(Blocks.log2, Blocks.leaves2, 1, 1, false, 9, 9, 6) : 
        new WorldGenBOPSwampTree(BOPBlockHelper.get("logs3"), BOPBlockHelper.get("colorizedLeaves2"), 1, 0, 6, 9, BOPBlockHelper.get("colorizedLeaves2"), 0);
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
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
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
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return 5935967;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 5215831;
    }

    /*@Override
	public int getFogColour()
	{
		return 6189472;
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 0.8F;
    }
     */
}
