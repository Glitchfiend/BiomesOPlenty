package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenPrairie extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
    
	public BiomeGenPrairie(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(13165952);
        this.setTemperatureRainfall(0.9F, 0.6F);

		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));

		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.grassPerChunk = 999;

		this.bopWorldFeatures.bopFlowersPerChunk = 30;
		this.bopWorldFeatures.portobellosPerChunk = 2;
		this.bopWorldFeatures.berryBushesPerChunk = 2;
		this.bopWorldFeatures.shrubsPerChunk = 3;
		this.bopWorldFeatures.waterReedsPerChunk = 4;
		this.bopWorldFeatures.leafPilesPerChunk = 15;
	}
	
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 0, 0, false, 6, 1, 7);
    }
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers2"), 4), 12);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 9), 6);
        
        return flowerMap;
    }
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
        HashMap<WorldGenerator, Double> grassMap = new HashMap();

        grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);

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
                world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
            }
        }
    }
	
	@Override
    //TODO:     getBiomeGrassColor()
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 13165952;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 11395195;
	}
}
