package biomesoplenty.common.biomes.promisedland;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.decoration.BOPBiomeDecorator;
import biomesoplenty.common.world.features.WorldGenWaterSpring;
import biomesoplenty.common.world.features.trees.WorldGenPromisedTree;
import biomesoplenty.common.world.features.trees.WorldGenPromisedTree2;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

//import worldcore.interfaces.IWCFog;

public class BiomeGenPromisedLandForest extends BOPBiome //implements IWCFog
{
	private WorldGenerator theWorldGenerator;
	private BOPBiomeDecorator customBiomeDecorator;

	public BiomeGenPromisedLandForest(int par1)
	{
		super(par1);
		theBiomeDecorator = new BOPBiomeDecorator();
		customBiomeDecorator = (BOPBiomeDecorator)theBiomeDecorator;
		topBlock = BOPBlockHelper.get("grass");
		fillerBlock = BOPBlockHelper.get("holyDirt");
		customBiomeDecorator.treesPerChunk = 10;
		customBiomeDecorator.grassPerChunk = 0;
		//this.bopWorldFeatures.holyTallGrassPerChunk = 100;
		//this.bopWorldFeatures.promisedWillowPerChunk = 40;
		//this.bopWorldFeatures.pinkFlowersPerChunk = 12;
		//this.bopWorldFeatures.rainbowflowersPerChunk = 10;
		this.bopWorldFeatures.blueMilksPerChunk = 5;
		customBiomeDecorator.generateLakes = false;
		this.bopWorldFeatures.waterPoolsPerChunk = 0;
		//this.bopWorldFeatures.hotSpringsPerChunk = 5;
		this.bopWorldFeatures.waterLakesPerChunk = 10;
		//this.bopWorldFeatures.crystalsPerChunk = 25;
		//this.bopWorldFeatures.crystals2PerChunk = 50;
		//this.bopWorldFeatures.cloudsPerChunk = 1;
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableCaveCreatureList.clear();
		this.bopWorldFeatures.generatePumpkins = false;
		//this.bopWorldFeatures.generateMossySkystone = true;
		//this.bopWorldFeatures.generateUndergroundLakes = false;
		theWorldGenerator = new WorldGenWaterSpring(Blocks.water, 8);
		/*this.spawnableMonsterList.add(new SpawnListEntry(EntityCow.class, 6, 1, 4));*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
		return random.nextInt(8) == 0 ? new WorldGenPromisedTree2(false) : new WorldGenPromisedTree(false);
    }
	
	@Override
    public int getBiomeGrassColor(int x, int y, int z)
    {
		return 7925125;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	//@Override
	public int getBiomeFoliageColor()
	{
		return 7925125;
	}
	
	//@Override
	public int getFogColour()
	{
		return 16754234;
	}
	
    //@Override
    public float getFogCloseness()
    {
        return 1.0F;
    }

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 100;
		int var6;
		int var7;
		int var8;

		for (var5 = 0; var5 < 10; ++var5)
		{
			var6 = par3 + par2Random.nextInt(16);
			var7 = par2Random.nextInt(60);
			var8 = par4 + par2Random.nextInt(16);
			theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
		}
	}

	 @Override
	 public int getSkyColorByTemp(float temperature)
	 {
		 if (BOPConfigurationMisc.skyColors) return BOPConfigurationMisc.promisedLandSkyColor;
		 else return super.getSkyColorByTemp(temperature);
	 }
}
