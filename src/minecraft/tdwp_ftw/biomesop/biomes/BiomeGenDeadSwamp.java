package tdwp_ftw.biomesop.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.configuration.BOPBlocks;
import tdwp_ftw.biomesop.configuration.BOPConfiguration;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadTree;

public class BiomeGenDeadSwamp extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDeadSwamp(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 2;
		this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.highGrassPerChunk = 1;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.reedsPerChunk = -999;
		this.customBiomeDecorator.mudPerChunk = 3;
        this.customBiomeDecorator.mudPerChunk2 = 3;
		this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.waterColorMultiplier = 10661201;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(9) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenDeadTree(false);
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 6713420;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 6451816;
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
