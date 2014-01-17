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
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.WorldGenMoss;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga1;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;

public class BiomeGenFen extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);
    
	public BiomeGenFen(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO: setColor()
        this.setColor(12240001);
        this.setTemperatureRainfall(0.4F, 0.4F);
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));

		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
		this.bopWorldFeatures.doubleTallGrassPerChunk = 1;
		this.bopWorldFeatures.cattailsPerChunk = 1;
		this.bopWorldFeatures.highCattailsPerChunk = 1;
		this.bopWorldFeatures.waterPoolsPerChunk = 99;
		this.bopWorldFeatures.toadstoolsPerChunk = 2;
		this.bopWorldFeatures.mudPerChunk = 1;
		this.bopWorldFeatures.riverCanePerChunk = 5;
		this.bopWorldFeatures.algaePerChunk = 1;
		this.bopWorldFeatures.portobellosPerChunk = 1;
		this.bopWorldFeatures.waterReedsPerChunk = 10;
		this.bopWorldFeatures.koruPerChunk = 1;
		this.bopWorldFeatures.shrubsPerChunk = 7;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(3) == 0 ? new WorldGenBOPTaiga2(Blocks.log, BOPBlockHelper.get("leaves2"), 0, 0, false, 10, 12, 3) : 
		(random.nextInt(20) == 0 ? new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("mud")) : 
		new WorldGenBOPTaiga1(Blocks.log, Blocks.leaves, 0, 0, false, 7, 5, 1));
	}
	
    @Override
	public HashMap<WorldGenerator, Double> getWeightedWorldGenForGrass()
    {
    	HashMap<WorldGenerator, Double> grassMap = new HashMap();
    	
    	grassMap.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
    	grassMap.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 1D);
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

		for (int i = 0; i < var5; ++i)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			//TODO:				getBlock()
			Block block = world.func_147439_a(x, y, z);

			if (block != null && block.isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				//TODO:	setBlock()
				world.func_147465_d(x, y, z, BOPBlockHelper.get("gemOre"), 10, 2);
			}
		}

		for (int i = 0; i < 20; i++)
		{
			int x = chunkX + random.nextInt(16) + 8;
            short y = 58;
			int z = chunkZ + random.nextInt(16) + 8;

			new WorldGenMoss().generate(world, random, x, y, z);
		}
	}

	@Override
	//TODO:		getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 12240001;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 13547897;
	}

	/*@Override
	public int getFogColour()
	{
		return 12638463;
	}
	
	@Override
	public float getFogCloseness()
	{
	    // TODO Auto-generated method stub
	    return 0.8F;
	}
	*/
}
