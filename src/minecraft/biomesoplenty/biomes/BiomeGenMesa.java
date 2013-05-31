package biomesoplenty.biomes;

import java.awt.Color;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeGenMesa extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	@SuppressWarnings("unchecked")
	public BiomeGenMesa(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Blocks.redRock.get().blockID;
		fillerBlock = (byte)Blocks.redRock.get().blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.deadBushPerChunk = 2;
		customBiomeDecorator.desertGrassPerChunk = 10;
		customBiomeDecorator.tinyCactiPerChunk = 2;
		spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 15, 2, 6));
	}

	/**
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfiguration.skyColors = true)
			return 15898486;
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
