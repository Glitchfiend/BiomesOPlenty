package biomesoplenty.common.biomes;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;

public class BiomeGenMysticGrove extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.3F, 0.8F);

	public BiomeGenMysticGrove(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        this.setEnableSnow();
        //TODO: setColor()
        this.setColor(6934491);
        this.setTemperatureRainfall(0.9F, 1.0F);
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 4, 4));
		
		this.waterColorMultiplier = 16715898;
		
		this.theBiomeDecorator.treesPerChunk = 9;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.flowersPerChunk = 8;
		
		this.bopWorldFeatures.bopFlowersPerChunk = 10;
		this.bopWorldFeatures.sproutsPerChunk = 1;
		this.bopWorldFeatures.blueMilksPerChunk = 1;
		//TODO: FEATURE customBiomeDecorator.poisonWaterPerChunk = 1;
		
		this.bopWorldFeatures.cloverPatchesPerChunk = 10;
		this.bopWorldFeatures.shrubsPerChunk = 4;

	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(4) == 0 ? new WorldGenOriginalTree(BOPBlockHelper.get("logs2"), BOPBlockHelper.get("leaves1"), 1, 2, false, 5, 3, false) : 
		(random.nextInt(3) == 0 ? new WorldGenOriginalTree(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("leaves4"), 2, 1) : 
		((random.nextInt(3) == 0 ? this.worldGeneratorBigTree : 
		((random.nextInt(8) == 0 ? new WorldGenBOPSwampTree(Blocks.log, Blocks.leaves, 0, 0, 6, 8) : this.worldGeneratorTrees)))));
	}
	
    @Override
    public HashMap<WorldGenerator, Double> getWeightedWorldGenForBOPFlowers()
    {
        HashMap<WorldGenerator, Double> flowerMap = new HashMap();
        
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 6), 3D);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 3), 4D);
        flowerMap.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 1D);
        
        return flowerMap;
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
	//TODO:		getBiomeGrassColor()
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6934491;
	}

	@Override
	//TODO:		getBiomeFoliageColor()
	public int func_150571_c(int x, int y, int z)
	{
		return 7397529;
	}
	
	/**
	 * Fog Color
	 */
	/*
	@Override
	public int getFogColour()
	{
		return 16755401;
	}
	*/

	/**
	 * takes temperature, returns color
	 */
	/*
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors)
			return 8972496;
		else
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
	}

    @Override
    public float getFogCloseness()
    {
        // TODO Auto-generated method stub
        return 1.0F;
    }
    */
}
