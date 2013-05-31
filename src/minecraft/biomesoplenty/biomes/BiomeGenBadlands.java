package biomesoplenty.biomes;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeGenBadlands extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenBadlands(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Blocks.hardSand.get().blockID;
		fillerBlock = (byte)Block.blockClay.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.deadBushPerChunk = 4;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.cactiPerChunk = 2;
		customBiomeDecorator.clayPerChunk = 3;
		customBiomeDecorator.generateClayInStone = true;
		customBiomeDecorator.generateSandInStone = true;
	}

	/**
	 * takes temperature, returns color
	 */
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfiguration.skyColors = true)
			 return 9814727;
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
