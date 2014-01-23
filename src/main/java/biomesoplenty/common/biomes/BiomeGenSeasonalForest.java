package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPBigTree;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenSeasonalForest extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.4F);

    public BiomeGenSeasonalForest(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(12502092);
        this.setTemperatureRainfall(0.7F, 0.8F);

        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));

        this.theBiomeDecorator.treesPerChunk = 20;
        this.theBiomeDecorator.grassPerChunk = 8;
        this.theBiomeDecorator.flowersPerChunk = -999;

        this.bopWorldFeatures.toadstoolsPerChunk = 4;
        this.bopWorldFeatures.shrubsPerChunk = 15;
        this.bopWorldFeatures.waterReedsPerChunk = 4;
    }

    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);

        return grassMap;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(2) == 0 ?  new WorldGenOriginalTree(Blocks.log2, BOPBlockHelper.get("leaves2"), 1, 3, false, 5, 3, false)
        : (random.nextInt(3) == 0 ? new WorldGenOriginalTree(Blocks.log, BOPBlockHelper.get("leaves1"), 2, 0, false, 5, 3, false) 
        : (random.nextInt(6) == 0 ? new WorldGenBOPBigTree(Blocks.log2, BOPBlockHelper.get("leaves2"), 1, 3)
        : (random.nextInt(6) == 0 ? new WorldGenBOPBigTree(Blocks.log, BOPBlockHelper.get("leaves3"), 0, 2)
        : (random.nextInt(3) == 0 ? new WorldGenOriginalTree(Blocks.log, BOPBlockHelper.get("leaves3"), 0, 2, false, 5, 3, false)
        : (random.nextInt(5) == 0 ? new WorldGenOriginalTree(Blocks.log, BOPBlockHelper.get("leaves2"), 0, 0, false, 5, 3, false)
        : (random.nextInt(6) == 0 ? worldGeneratorBigTree
                : worldGeneratorTrees))))));
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
            Block block = world.func_147439_a(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 0, 2);
            }
        }
    }

    @Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
        return 11781186;
    }

    @Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 12502092;
        // return 12502595;
    }

    /*@Override
    public int getFogColour()
    {
        return 16764548;
    }

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
    */
}
