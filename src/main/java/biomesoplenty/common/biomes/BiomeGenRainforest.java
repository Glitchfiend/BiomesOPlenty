package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenRainforest extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.2F, 0.9F);

	public BiomeGenRainforest(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO:	setColor()
        this.setColor(1368687);
        this.setTemperatureRainfall(2.0F, 2.0F);
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityJungleSpider.class, 12, 6, 6));
		
		this.theBiomeDecorator.treesPerChunk = 14;
		this.theBiomeDecorator.grassPerChunk = 25;

		this.theBiomeDecorator.mushroomsPerChunk = 25;

		this.bopWorldFeatures.bopFlowersPerChunk = 25;
		this.bopWorldFeatures.shrubsPerChunk = 5;
		this.bopWorldFeatures.cloverPatchesPerChunk = 20;
		this.bopWorldFeatures.leafPilesPerChunk = 10;
		this.bopWorldFeatures.generatePumpkins = false;
		
		/*TODO: FEATURE customBiomeDecorator.pinkFlowersPerChunk = 2;
		customBiomeDecorator.rosesPerChunk = 10;
		customBiomeDecorator.orangeFlowersPerChunk = 6;*/
	}
	
    @Override
    public HashMap<WorldGenBOPFlora, Integer> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenBOPFlora, Integer> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 6), 12);
        flowerMap.put(new WorldGenBOPFlora(Blocks.red_flower, 1), 6);
        flowerMap.put(new WorldGenBOPDoubleFlora(4, 5), 4);
        flowerMap.put(new WorldGenBOPDoubleFlora(1, 5), 6);
        
        return flowerMap;
    }
	
	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(15) == 0 ? this.worldGeneratorTrees : (random.nextInt(5) == 0 ? worldGeneratorBigTree : new WorldGenOriginalTree(Blocks.log, Blocks.leaves, 0, 0, false, 8, 2, false));
	}
	
    @Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
    	return null;
    }

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10) : new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11)) : (random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : new WorldGenTallGrass(Blocks.tallgrass, 1));
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
		return 1759340;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 1368687;
	}
}
