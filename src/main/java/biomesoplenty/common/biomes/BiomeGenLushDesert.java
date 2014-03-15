package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenCypress;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;

public class BiomeGenLushDesert extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.5F);
    
    public BiomeGenLushDesert(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(9087277);
        this.setTemperatureRainfall(1.0F, 0.3F);

        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));

        this.topBlock = Blocks.hardened_clay;
        this.fillerBlock = Blocks.hardened_clay;

        this.theBiomeDecorator.treesPerChunk = 6;
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
        this.bopWorldFeatures.leafPilesPerChunk = 2;
        this.bopWorldFeatures.deadLeafPilesPerChunk = 4;
        this.bopWorldFeatures.generateSand = true;
        this.bopWorldFeatures.generatePumpkins = false;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(4) == 0 ? new WorldGenCypress(Blocks.log2, Blocks.leaves2, 0, 0, false, 7, 10, 2) : 
        (random.nextInt(18) == 0 ? new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("grass"), BOPBlockHelper.get("driedDirt"), Blocks.hardened_clay) : 
        (random.nextInt(2) == 0 ? worldGeneratorTrees : new WorldGenShrub(0,0)));
    }
    
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPDoubleFlora(4, 5), 4);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 7), 8);
        
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
            Block block = world.getBlock(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 2, 2);
            }
        }

        for (int var6 = 0; var6 < var5; ++var6)
        {
            int x = chunkX + random.nextInt(16);
            int y = random.nextInt(53) + 75;
            int z = chunkZ + random.nextInt(16);

            Block block= world.getBlock(x, y, z);

            if (block != null && (block.isReplaceableOreGen(world, x, y, z, Blocks.stone) || block == BOPBlockHelper.get("redRock")))
            {
                //TODO: setBlock()
                world.setBlock(x, y, z, Blocks.flowing_water, 0, 2);
            }
        }
    }
    
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {

        if (p_150573_7_ > 1.75D)
        {
        	this.topBlock = Blocks.grass;
            this.fillerBlock = Blocks.dirt;
            this.field_150604_aj = 0;
        }
        else if (p_150573_7_ > -0.5D)
        {
            this.topBlock = Blocks.hardened_clay;
            this.fillerBlock = Blocks.hardened_clay;
            this.field_150604_aj = 0;
        }

        this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }
}
