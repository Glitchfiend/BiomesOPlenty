package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;

public class BiomeGenLushSwamp extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.0F, 0.1F);

    public BiomeGenLushSwamp(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(5746228);
        this.setTemperatureRainfall(0.7F, 1.0F);
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
        
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 4;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 16;
		
		this.bopWorldFeatures.bopFlowersPerChunk = 1;
		this.bopWorldFeatures.cattailsPerChunk = 10;
		this.bopWorldFeatures.highCattailsPerChunk = 5;
		this.bopWorldFeatures.riverCanePerChunk = 5;
		//TODO: FEATURE this.bopWorldFeatures.poisonWaterPerChunk = 2;
		this.bopWorldFeatures.wildCarrotsPerChunk = 1;
		this.bopWorldFeatures.shrubsPerChunk = 5;
		this.bopWorldFeatures.koruPerChunk = 1;
		this.bopWorldFeatures.waterReedsPerChunk = 6;
		this.bopWorldFeatures.cloverPatchesPerChunk = 10;
		this.bopWorldFeatures.seaweedPerChunk = 10;
    }

    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPSwampTree(Blocks.log, Blocks.leaves, 0, 0, 8, 6, BOPBlockHelper.get("ivy"), -1);
    }
    
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 8);
        flowerMap.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 4);
        flowerMap.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);
        
        return flowerMap;
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();
        
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
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
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
            }
        }
    }
}
