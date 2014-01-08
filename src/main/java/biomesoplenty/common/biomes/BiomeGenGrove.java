package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenChaparral2;
import biomesoplenty.common.world.features.trees.WorldGenPoplar;
import biomesoplenty.common.world.features.trees.WorldGenPoplar2;

public class BiomeGenGrove extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.3F, 0.4F);

	public BiomeGenGrove(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(5341009);
        this.setTemperatureRainfall(0.4F, 0.8F);

		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.theBiomeDecorator.grassPerChunk = 8;
		
		this.bopWorldFeatures.bopFlowersPerChunk = 50;
		this.bopWorldFeatures.sproutsPerChunk = 1;
		this.bopWorldFeatures.berryBushesPerChunk = 2;
		this.bopWorldFeatures.shrubsPerChunk = 3;
		this.bopWorldFeatures.cloverPatchesPerChunk = 20;
		this.bopWorldFeatures.generatePumpkins = false;
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(2) == 0 ? new WorldGenChaparral2() : random.nextInt(3) == 0 ? new WorldGenPoplar2() : new WorldGenPoplar();
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenerator, Double> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 16D);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 9), 3D);
        
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
                world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 0, 2);
            }
        }
    }

	@Override
    //TODO:     getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 5341009;
	}

	@Override
    //TODO:     getBiomeFoliageColor()
    public int func_150571_c(int x, int y, int z)
    {
		return 6396257;
	}
}
