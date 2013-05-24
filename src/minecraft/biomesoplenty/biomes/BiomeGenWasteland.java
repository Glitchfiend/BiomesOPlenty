package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import biomesoplenty.worldgen.WorldGenDeadTree3;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenWasteland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenWasteland(int par1)
    {
        super(par1);
        this.topBlock = (byte)Blocks.driedDirt.get().blockID;
        this.fillerBlock = (byte)Blocks.driedDirt.get().blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.treesPerChunk = 0;
        this.customBiomeDecorator.deadGrassPerChunk = 14;
        this.waterColorMultiplier = 15073024;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenDeadTree3(false);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 10330232;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 10067541;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 10465942;
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
