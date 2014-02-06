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
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPBigTree;

public class BiomeGenCherryBlossomGrove extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenCherryBlossomGrove(int id)
	{
		super(id);

        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO:	setColor()
        this.setColor(16289679);
        this.setTemperatureRainfall(0.7F, 0.8F);
		
		this.theBiomeDecorator.treesPerChunk = 3;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.flowersPerChunk = -999;
		
		this.bopWorldFeatures.bopFlowersPerChunk = 10;
		this.bopWorldFeatures.shrubsPerChunk = 2;
		this.bopWorldFeatures.cloverPatchesPerChunk = 15;
		this.bopWorldFeatures.generatePumpkins = false;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenBOPBigTree(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves3"), 1, 3) : new WorldGenBOPBigTree(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves3"), 1, 1);
	}
	
    @Override
	public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
    	HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
    	
    	flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 6), 12);
    	flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 9), 8);
    	flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 0), 6);
    	flowerMap.put(new WorldGenBOPDoubleFlora(1, 5), 4);
    	
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
			
			//TODO:				getBlock()
			Block block = world.getBlock(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.setBlock(x, y, z, BOPBlockHelper.get("gemOre"), 6, 2);
			}
		}
	}

	@Override
	//TODO:		getBiomeGrassColor()
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 10747818;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 10747818;
	}
}
