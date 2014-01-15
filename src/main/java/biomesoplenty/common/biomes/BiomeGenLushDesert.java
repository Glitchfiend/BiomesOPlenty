package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLushDesert extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.9F);
    
    public BiomeGenLushDesert(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(9087277);
        this.setTemperatureRainfall(0.8F, 0.2F);

        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));

        this.topBlock = BOPBlockHelper.get("redRock");
        this.fillerBlock = BOPBlockHelper.get("redRock");

        this.theBiomeDecorator.treesPerChunk = 12;
        this.theBiomeDecorator.grassPerChunk = 8;
        this.theBiomeDecorator.cactiPerChunk = 20;
        this.theBiomeDecorator.deadBushPerChunk = 2;

        this.bopWorldFeatures.shrubsPerChunk = 10;
        this.bopWorldFeatures.oasesPerChunk = 999;
        this.bopWorldFeatures.bopFlowersPerChunk = 5;
        this.bopWorldFeatures.tinyCactiPerChunk = 5;
        this.bopWorldFeatures.waterLakesPerChunk = 5;
        this.bopWorldFeatures.waterReedsPerChunk = 4;
        this.bopWorldFeatures.bromeliadsPerChunk = 3;
        this.bopWorldFeatures.generateGrass = true;
        this.bopWorldFeatures.generateSand = true;
        this.bopWorldFeatures.generatePumpkins = false;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(4) == 0 ? new WorldGenSavannaTree(false) : 
        (random.nextInt(24) == 0 ? new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("grass"), BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("redRock")) : 
        (random.nextInt(2) == 0 ? worldGeneratorTrees : new WorldGenShrub(0,0)));
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenerator, Double> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 7), 1D);
        
        return flowerMap;
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("plants"), 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        
        return grassMap;
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
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 2, 2);
            }
        }

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(53) + 75;
            int z = chunkZ + random.nextInt(16);

            Block block= world.func_147439_a(x, y, z);

            if (block != null && (block.isReplaceableOreGen(world, x, y, z, Blocks.stone) || block == BOPBlockHelper.get("redRock")))
            {
                //TODO: setBlock()
                world.func_147465_d(x, y, z, Blocks.flowing_water, 0, 2);
            }
        }
    }
}
