package biomesoplenty.common.biomes;

import java.util.Random;

import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenDeadTree1;
import biomesoplenty.common.world.features.trees.WorldGenBOPTaiga2;

public class BiomeGenOminousWoods extends BOPBiome
{
	private static final Height biomeHeight = new Height(0.1F, 0.2F);
	
	public BiomeGenOminousWoods(int id)
	{
		super(id);

        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        //TODO:	setColor()
        this.setColor(4145489);
        this.setTemperatureRainfall(0.8F, 0.9F);
		
		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 5, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 4));
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
		
		this.waterColorMultiplier = 1973030;
		
		this.theBiomeDecorator.treesPerChunk = 8;
		this.theBiomeDecorator.grassPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		
		this.bopWorldFeatures.bopFlowersPerChunk = 1;
		this.bopWorldFeatures.thornsPerChunk = 9;
		this.bopWorldFeatures.poisonIvyPerChunk = 3;
		//TODO: FEATURE customBiomeDecorator.poisonWaterPerChunk = 15;
	}

	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return random.nextInt(2) == 0 ? new WorldGenBOPTaiga2(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves1"), 2, 3, false, 14, 6, 0) : (random.nextInt(6) == 0 ? new WorldGenDeadTree1(false, Blocks.dirt, Blocks.grass, BOPBlockHelper.get("grass"), BOPBlockHelper.get("driedDirt"), BOPBlockHelper.get("redRock")) : new WorldGenBOPSwampTree(BOPBlockHelper.get("logs1"), BOPBlockHelper.get("leaves1"), 2, 3, 5, 4));
	}
	
    @Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random)
    {
    	return new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 2);
    }
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random random)
	{
		return random.nextInt(4) == 0 ? (random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 10) : new WorldGenTallGrass(BOPBlockHelper.get("foliage"), 11)) : (random.nextInt(6) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 0) : new WorldGenTallGrass(Blocks.tallgrass, 1));
	}

	@Override
	//TODO:		getBiomeGrassColor()
	public int func_150558_b(int x, int y, int z)
	{
		return 4145489;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 4145489;
	}
	
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 5522002;
		else return super.getSkyColorByTemp(par1);
	}
	
	/**
	 * Fog Color
	 */
	/*
	@Override
	public int getFogColour()
	{
		return 3420989;
	}
	*/

    /*@Override
    public float getFogCloseness()
    {
        return 0.1F;
    }
    */
}
