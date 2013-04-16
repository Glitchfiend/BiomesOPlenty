package biomesoplenty.biomes;

import java.awt.Color;

import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenMesa extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenMesa(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)BOPBlocks.redRock.blockID;
        this.fillerBlock = (byte)BOPBlocks.redRock.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 2;
        this.customBiomeDecorator.desertGrassPerChunk = 10;
		this.customBiomeDecorator.tinyCactiPerChunk = 2;
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 15, 2, 6));
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (BOPConfiguration.skyColors = true)
		{
        return 15898486;
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
