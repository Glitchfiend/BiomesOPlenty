package biomesoplenty.common.biomes.overworld;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPSwampTree;
import biomesoplenty.common.world.features.trees.WorldGenOriginalTree;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenMysticGrove extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.2F);

	public BiomeGenMysticGrove(int id)
	{
		super(id);
		
        this.setHeight(biomeHeight);
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

        this.bopWorldFeatures.setFeature("bopFlowersPerChunk", 10);
        this.bopWorldFeatures.setFeature("sproutsPerChunk", 1);
        this.bopWorldFeatures.setFeature("blueMilksPerChunk", 1);
        this.bopWorldFeatures.setFeature("poisonLakesPerChunk", 1);

        this.bopWorldFeatures.setFeature("cloverPatchesPerChunk", 10);
        this.bopWorldFeatures.setFeature("shrubsPerChunk", 4);
        this.bopWorldFeatures.setFeature("leafPilesPerChunk", 10);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 5);
        this.bopWorldFeatures.setFeature("algaePerChunk", 2);

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 15);

        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 6), 12);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 3), 10);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPBlockHelper.get("flowers"), 4), 8);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 3), 6);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(Blocks.red_flower, 2), 6);
        this.bopWorldFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(1, 5), 4);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
	}

	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(4) == 0 ? new WorldGenOriginalTree(BOPBlockHelper.get("logs2"), BOPBlockHelper.get("leaves1"), 1, 2, false, 5, 3, false) : 
		(random.nextInt(3) == 0 ? new WorldGenOriginalTree(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("leaves4"), 2, 1) : 
		((random.nextInt(3) == 0 ? this.worldGeneratorBigTree : 
		((random.nextInt(5) == 0 ? new WorldGenBOPSwampTree(Blocks.log, Blocks.leaves, 0, 0, 8, 6, BOPBlockHelper.get("flowerVine"), -1) : this.worldGeneratorTrees)))));
	}

	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 6934491;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
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
