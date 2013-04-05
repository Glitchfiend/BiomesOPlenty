package tdwp_ftw.biomesop.biomes;

import java.util.Random;
import java.awt.Color;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.worldgen.WorldGenSpire;

public class BiomeGenWasteland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenWasteland(int par1)
    {
        super(par1);
        this.topBlock = (byte)BOPBlocks.driedDirt.blockID;
        this.fillerBlock = (byte)BOPBlocks.driedDirt.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadGrassPerChunk = 14;
        this.waterColorMultiplier = 15073024;
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
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
		if (mod_BiomesOPlenty.skyColors = true)
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
