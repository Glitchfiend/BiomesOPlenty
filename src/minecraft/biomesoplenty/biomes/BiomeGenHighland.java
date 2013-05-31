package biomesoplenty.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenHighland extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenHighland(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.highGrassPerChunk = 25;
		customBiomeDecorator.grassPerChunk = 25;
		customBiomeDecorator.potatoesPerChunk = -999;
		customBiomeDecorator.generateBoulders = true;
	}
}
