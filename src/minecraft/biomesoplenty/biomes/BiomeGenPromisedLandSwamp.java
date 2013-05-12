package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import cpw.mods.fml.common.Loader;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.integration.ThaumcraftIntegration;
import biomesoplenty.worldgen.WorldGenPromisedShrub;
import biomesoplenty.worldgen.WorldGenPromisedTree;
import biomesoplenty.worldgen.WorldGenPromisedWillowTree;
import biomesoplenty.worldgen.WorldGenWaterSpring;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenPromisedLandSwamp extends BiomeGenBase
{
	private WorldGenerator theWorldGenerator;
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenPromisedLandSwamp(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)Blocks.holyGrass.get().blockID;
        this.fillerBlock = (byte)Blocks.holyDirt.get().blockID;
        this.customBiomeDecorator.treesPerChunk = 12;
        this.customBiomeDecorator.grassPerChunk = -999;
		this.customBiomeDecorator.holyTallGrassPerChunk = 100;
		this.customBiomeDecorator.promisedWillowPerChunk = 80;
		this.customBiomeDecorator.pinkFlowersPerChunk = 6;
		this.customBiomeDecorator.blueMilksPerChunk = 15;
		this.customBiomeDecorator.toadstoolsPerChunk = 10;
		this.customBiomeDecorator.portobellosPerChunk = 5;
		this.customBiomeDecorator.generateLakes = false;
		this.customBiomeDecorator.pondsPerChunk = -100;
		this.customBiomeDecorator.waterLakesPerChunk = 20;
		this.customBiomeDecorator.crystalsPerChunk = 25;
		this.customBiomeDecorator.crystals2PerChunk = 50;
		this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.customBiomeDecorator.generatePumpkins = false;
		this.customBiomeDecorator.generateClouds = true;
		//this.customBiomeDecorator.generateLakes = false;
		this.theWorldGenerator = new WorldGenWaterSpring(Block.waterMoving.blockID, 8);
		this.spawnableMonsterList.add(new SpawnListEntry(EntityPig.class, 4, 1, 4));
		
		if (Loader.isModLoaded("TwilightForest"))
		{
			try {
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFBird"), 2, 1, 3));
				this.spawnableMonsterList.add(new SpawnListEntry(Class.forName("twilightforest.entity.passive.EntityTFRaven"), 1, 1, 2));
			} catch (ClassNotFoundException e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Twilight Forest with Biomes O' Plenty!");
				e.printStackTrace();
			}
		}
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(6) == 0 ? new WorldGenPromisedTree(false) : new WorldGenPromisedWillowTree());
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 4583331;
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
