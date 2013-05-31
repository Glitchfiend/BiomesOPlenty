package biomesoplenty.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.Blocks;

public class BiomeGenGlacier extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenGlacier(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Blocks.hardIce.get().blockID;
		fillerBlock = (byte)Blocks.hardIce.get().blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = -999;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
	}
}
