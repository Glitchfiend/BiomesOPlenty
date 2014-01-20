package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBulbTree;

public class BiomeGenDeciduousForest extends BOPBiome
{
	//private static final Height biomeHeight = new Height(); Not set?
	
	public BiomeGenDeciduousForest(int id)
	{
		super(id);
		
        //this.func_150570_a(biomeHeight); Not set?
        //TODO:	setColor()
        this.setColor(12695369);
        this.setTemperatureRainfall(0.7F, 0.8F);
		
		this.theBiomeDecorator.treesPerChunk = 15;
		this.theBiomeDecorator.grassPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = -999;
		
		this.bopWorldFeatures.toadstoolsPerChunk = 1;
		this.bopWorldFeatures.bushesPerChunk = 8;
		this.bopWorldFeatures.berryBushesPerChunk = 2;
		this.bopWorldFeatures.blueMilksPerChunk = 2;
		this.bopWorldFeatures.poisonIvyPerChunk = 1;
		this.bopWorldFeatures.shrubsPerChunk = 10;
		this.bopWorldFeatures.waterReedsPerChunk = 2;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(4) == 0 ? new WorldGenShrub(2,2) : new WorldGenBulbTree(Blocks.log, Blocks.leaves, 0, 0, false, 10, 15, false);
	}

    @Override
	public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
    	HashMap<WorldGenerator, Double> grassMap = new HashMap();
    	
    	grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    	grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 2), 1D);
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
			
			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 0, 2);
			}
		}
	}

	@Override
	//TODO:		getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 12695369;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 12896570;
	}
}
