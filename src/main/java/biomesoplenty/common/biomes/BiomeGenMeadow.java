package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenMeadow extends BOPBiome
{
	public BiomeGenMeadow(int id)
	{
		super(id);
		
        //TODO: setColor()
        this.setColor(6533741);
        this.setTemperatureRainfall(0.7F, 0.7F);

	    this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 10;
		
		this.bopWorldFeatures.bopFlowersPerChunk = 14;
		this.bopWorldFeatures.wildCarrotsPerChunk = 1;
		this.bopWorldFeatures.sunflowersPerChunk = 1;
		this.bopWorldFeatures.shrubsPerChunk = 5;
		this.bopWorldFeatures.cloverPatchesPerChunk = 15;
		this.bopWorldFeatures.generatePumpkins = false;
	}
	
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return random.nextInt(5) == 0 ? new WorldGenBOPTaiga2(Blocks.log, Blocks.leaves, 1, 1, false, 6, 4, 0) : new WorldGenBOPShrub(Blocks.log, Blocks.leaves, 0, 1, Blocks.dirt, Blocks.grass);
    }
    
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenerator, Double> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 10D);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 8D);
        flowerMap.put(new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 5, 11, 3), 5D);
        flowerMap.put(new WorldGenBOPDoubleFlora(Blocks.double_plant, Blocks.double_plant, 1, 7, 5), 4D);
        
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
            Block block = world.func_147439_a(x, y, z);

            if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
            {
                //TODO: setBlock()
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 4, 2);
            }
        }
    }

    @Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 6533741;
    }

    @Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
        return 6533741;
    }
}
