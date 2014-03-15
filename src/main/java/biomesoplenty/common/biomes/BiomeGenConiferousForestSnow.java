package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga3;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenConiferousForestSnow extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.4F);
    
    public BiomeGenConiferousForestSnow(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(16777215);
        this.setTemperatureRainfall(0.0F, 0.5F);

        this.spawnableCreatureList.clear();

        this.theBiomeDecorator.treesPerChunk = 2;
        this.theBiomeDecorator.mushroomsPerChunk = 4;
        this.theBiomeDecorator.flowersPerChunk = -999;
        //                     gravelPerChunk
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;

        this.bopWorldFeatures.bopFlowersPerChunk = 3;
        this.bopWorldFeatures.shrubsPerChunk = 4;
        this.bopWorldFeatures.deadLeafPilesPerChunk = 8;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenBOPTaiga3(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 35, 10, 0) : 
        (random.nextInt(3) == 0 ? new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 20, 15, 4) : 
        new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves2"), 3, 1, false, 10, 10, 5));
    }
    
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 8), 8);
        
        return flowerMap;
    }

    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 0.25D);
        grassMap.put(new WorldGenBOPDoubleFlora(3, 64), 0.25D);

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
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 8, 2);
            }
        }
    }
}
