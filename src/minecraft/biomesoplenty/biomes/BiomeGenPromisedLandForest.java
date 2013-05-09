package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenPromisedShrub;
import biomesoplenty.worldgen.WorldGenPromisedTree;
import biomesoplenty.worldgen.WorldGenPromisedTree2;
import biomesoplenty.worldgen.WorldGenPromisedTree3;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

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
        this.fillerBlock = (byte)Blocks.holyStone.get().blockID;
        this.customBiomeDecorator.treesPerChunk = 8;
        this.customBiomeDecorator.grassPerChunk = -999;
		this.customBiomeDecorator.holyTallGrassPerChunk = 50;
		this.customBiomeDecorator.promisedWillowPerChunk = 40;
		this.customBiomeDecorator.pinkFlowersPerChunk = 6;
        this.customBiomeDecorator.glowFlowersPerChunk = 3;
		this.customBiomeDecorator.blueMilksPerChunk = 5;
		this.customBiomeDecorator.generateLakes = false;
		this.customBiomeDecorator.pondsPerChunk = -100;
		this.customBiomeDecorator.waterLakesPerChunk = 5;
		this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.customBiomeDecorator.generatePumpkins = false;
		//this.customBiomeDecorator.generateLakes = false;
		this.theWorldGenerator = new WorldGenMinable(Block.waterMoving.blockID, 8);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenPromisedTree3(false) : (par1Random.nextInt(8) == 0 ? new WorldGenPromisedTree2(false) : new WorldGenPromisedTree(false)));
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

        for (var5 = 0; var5 < 5; ++var5)
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
        return 50175;
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
