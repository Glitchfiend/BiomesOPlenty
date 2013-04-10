package tdwp_ftw.biomesop.biomes;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import tdwp_ftw.biomesop.configuration.BOPBlocks;
import tdwp_ftw.biomesop.configuration.BOPConfiguration;

public class BiomeGenBadlands extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenBadlands(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sandStone.blockID;
        this.fillerBlock = (byte)BOPBlocks.hardSand.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 4;
        this.customBiomeDecorator.reedsPerChunk = -999;
		this.customBiomeDecorator.cactiPerChunk = 2;
		this.customBiomeDecorator.clayPerChunk = 3;
		this.customBiomeDecorator.generateClayInStone = true;
		this.customBiomeDecorator.generateSandInStone = true;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 13421723;
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
