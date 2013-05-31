package biomesoplenty.biomes;

import java.awt.Color;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeGenCrag extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenCrag(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		topBlock = (byte)Blocks.cragRock.get().blockID;
		fillerBlock = (byte)Blocks.cragRock.get().blockID;
		waterColorMultiplier = 944693;
	}

	/**
	 * takes temperature, returns color
	 */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfiguration.skyColors = true)
			 return 4944498;
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
