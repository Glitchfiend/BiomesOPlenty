package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeGenHeathland extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenHeathland(int id)
	{
		super(id);

        this.setHeight(biomeHeight);
        this.setColor(13550967);
        this.setTemperatureRainfall(0.8F, 0.1F);

		this.spawnableCreatureList.clear();
		
	    this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.grassPerChunk = 10;
	    this.theBiomeDecorator.deadBushPerChunk = 2;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 20);
        this.bopWorldFeatures.setFeature("berryBushesPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 5);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("generatePumpkins", false);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 10);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 7), 8);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 6);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 4);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
	}
	
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(3) == 0 ? new WorldGenBOPShrub(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("leaves4"), 2, 1, Blocks.grass) : (random.nextInt(2) == 0 ? new WorldGenShrub(0, 0) : worldGeneratorTrees);
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

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 13550967;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 11454081;
	}
}
