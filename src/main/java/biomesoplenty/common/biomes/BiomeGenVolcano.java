package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenVolcano extends BOPBiome
{

	public BiomeGenVolcano(int par1)
	{
		super(par1);
		/*
		spawnableCreatureList.clear();
		topBlock = (byte)Blocks.ashStone.get().blockID;
		fillerBlock = (byte)Blocks.ashStone.get().blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 0;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = -999;
		customBiomeDecorator.lavaLakesPerChunk = 50;
		customBiomeDecorator.generateAsh = true;
		*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return new WorldGenVolcano();
	 }
	 */

	 /**
	  * takes temperature, returns color
	  */
	/*
	 @Override
	 public int getSkyColorByTemp(float par1)
	 {
		 if (BOPConfigurationMisc.skyColors)
			 return 8026746;
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
	 */
}
