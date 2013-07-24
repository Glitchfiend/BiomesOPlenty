package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenPromisedTree;
import biomesoplenty.worldgen.WorldGenPromisedWillowTree;
import biomesoplenty.worldgen.WorldGenWaterSpring;

public class BiomeGenPromisedLandSwamp extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenPromisedLandSwamp(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Blocks.holyGrass.get().blockID;
		fillerBlock = (byte)Blocks.holyDirt.get().blockID;
		customBiomeDecorator.treesPerChunk = 12;
		customBiomeDecorator.grassPerChunk = -999;
		customBiomeDecorator.holyTallGrassPerChunk = 100;
		customBiomeDecorator.promisedWillowPerChunk = 80;
		customBiomeDecorator.pinkFlowersPerChunk = 6;
		customBiomeDecorator.rainbowflowersPerChunk = 5;
		customBiomeDecorator.blueMilksPerChunk = 15;
		customBiomeDecorator.toadstoolsPerChunk = 10;
		customBiomeDecorator.portobellosPerChunk = 5;
		customBiomeDecorator.generateLakes = false;
		customBiomeDecorator.pondsPerChunk = -100;
		customBiomeDecorator.hotSpringsPerChunk = 20;
		customBiomeDecorator.crystalsPerChunk = 25;
		customBiomeDecorator.crystals2PerChunk = 50;
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableCaveCreatureList.clear();
		customBiomeDecorator.generatePumpkins = false;
		//this.customBiomeDecorator.generateClouds = true;
		//this.customBiomeDecorator.generateLakes = false;
		theWorldGenerator = new WorldGenWaterSpring(Fluids.springWater.get().blockID, 8);
		/*this.spawnableMonsterList.add(new SpawnListEntry(EntityPig.class, 4, 1, 4));

		if (Loader.isModLoaded("TwilightForest"))
		{
			try {
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFBird"), 2, 1, 3));
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFRaven"), 1, 1, 2));
			} catch (ClassNotFoundException e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Twilight Forest with Biomes O' Plenty!");
				e.printStackTrace();
			}
		}*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(6) == 0 ? new WorldGenPromisedTree(false) : new WorldGenPromisedWillowTree();
	}
	
	@Override
	public int getBiomeGrassColor()
	{
		return 7925125;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 4583331;
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

	/**
	 * takes temperature, returns color
	 */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfiguration.skyColors)
			 return BOPConfiguration.promisedLandSkyColor;
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
}
