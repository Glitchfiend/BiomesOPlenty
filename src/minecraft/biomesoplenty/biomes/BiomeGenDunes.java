package biomesoplenty.biomes;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeGenDunes extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDunes(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sand.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.deadBushPerChunk = -999;
		customBiomeDecorator.duneGrassPerChunk = 10;
		customBiomeDecorator.desertSproutsPerChunk = 5;
		customBiomeDecorator.aloePerChunk = 1;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.generateLakes = false;
	}

	/**
	 * takes temperature, returns color
	 */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfiguration.skyColors = true)
			 return 14203007;
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
