package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenBayou1;
import biomesoplenty.worldgen.WorldGenBayou2;
import biomesoplenty.worldgen.WorldGenBayou3;
import biomesoplenty.worldgen.WorldGenMoss;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenBayou extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenBayou(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 15;
        this.customBiomeDecorator.grassPerChunk = 15;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.reedsPerChunk = 25;
        this.customBiomeDecorator.mudPerChunk = 1;
        this.customBiomeDecorator.mudPerChunk2 = 1;
		this.customBiomeDecorator.toadstoolsPerChunk = 2;
		this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
        this.customBiomeDecorator.waterlilyPerChunk = 2;
        this.customBiomeDecorator.cattailsPerChunk = 1;
		this.customBiomeDecorator.highCattailsPerChunk = 1;
		this.customBiomeDecorator.waterLakesPerChunk = 2;
		this.customBiomeDecorator.algaePerChunk = 1;
		this.customBiomeDecorator.generatePumpkins = false;
		this.waterColorMultiplier = 16767282;
        this.spawnableWaterCreatureList.clear();
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(8) == 0 ? new WorldGenBayou3() : (par1Random.nextInt(2) == 0 ? new WorldGenBayou1() : new WorldGenBayou2()));
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenMoss var5 = new WorldGenMoss();

        for (int var6 = 0; var6 < 20; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 58;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 9154411;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 11591816;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 11322556;
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
