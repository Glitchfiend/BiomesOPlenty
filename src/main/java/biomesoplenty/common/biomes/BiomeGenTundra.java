package biomesoplenty.common.biomes;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

import java.util.Random;

public class BiomeGenTundra extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.1F);
    
    public BiomeGenTundra(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(11371606);
        this.setTemperatureRainfall(0.2F, 0.8F);

        this.spawnableCreatureList.clear();

        this.theBiomeDecorator.treesPerChunk = 5;
        this.theBiomeDecorator.grassPerChunk = 8;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = 8;

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 1);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 2);
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 2);
        this.bopWorldFeatures.setFeature("rockpilesPerChunk", 25);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 5);
        this.bopWorldFeatures.setFeature("deadLeafPilesPerChunk", 5);

        weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 4);

        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 1D);
        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
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
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 8, 2);
            }
        }
    }

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 11371606;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return 12543566;
    }
}
