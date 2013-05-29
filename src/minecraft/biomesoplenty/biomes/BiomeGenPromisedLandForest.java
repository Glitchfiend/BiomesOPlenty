package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Liquids;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenPromisedShrub;
import biomesoplenty.worldgen.WorldGenPromisedTree;
import biomesoplenty.worldgen.WorldGenPromisedTree2;
import biomesoplenty.worldgen.WorldGenPromisedTree3;
import biomesoplenty.worldgen.WorldGenWaterSpring;

public class BiomeGenPromisedLandForest extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenPromisedLandForest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)Blocks.holyGrass.get().blockID;
        this.fillerBlock = (byte)Blocks.holyDirt.get().blockID;
        this.customBiomeDecorator.treesPerChunk = 20;
        this.customBiomeDecorator.grassPerChunk = -999;
		this.customBiomeDecorator.holyTallGrassPerChunk = 50;
		this.customBiomeDecorator.promisedWillowPerChunk = 80;
		this.customBiomeDecorator.pinkFlowersPerChunk = 12;
		this.customBiomeDecorator.blueMilksPerChunk = 5;
		this.customBiomeDecorator.generateLakes = false;
		this.customBiomeDecorator.pondsPerChunk = -100;
		this.customBiomeDecorator.hotSpringsPerChunk = 10;
		this.customBiomeDecorator.crystalsPerChunk = 25;
		this.customBiomeDecorator.crystals2PerChunk = 50;
		this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.customBiomeDecorator.generatePumpkins = false;
		//this.customBiomeDecorator.generateClouds = true;
		//this.customBiomeDecorator.generateLakes = false;
		this.theWorldGenerator = new WorldGenWaterSpring(Liquids.springWater.get().blockID, 8);
		/*this.spawnableMonsterList.add(new SpawnListEntry(EntityCow.class, 6, 1, 4));
		
		if (Loader.isModLoaded("TwilightForest"))
		{
			try {
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFBighorn"), 2, 1, 3));
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFDeer"), 4, 1, 3));
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFBird"), 2, 1, 3));
			} catch (ClassNotFoundException e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Twilight Forest with Biomes O' Plenty!");
				e.printStackTrace();
			}
		}*/
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenPromisedTree3(false) : (par1Random.nextInt(2) == 0 ? new WorldGenPromisedShrub(0,0) : (par1Random.nextInt(8) == 0 ? new WorldGenPromisedTree2(false) : new WorldGenPromisedTree(false))));
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 7925125;
    }
	
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
            this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
        }
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return BOPConfiguration.promisedLandSkyColor;
		}	
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
