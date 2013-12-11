package biomesoplenty.biomes.beach;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBeachGravel extends BiomeGenBase
{
	public BiomeGenBeachGravel(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Block.gravel.blockID;
		fillerBlock = (byte)Block.gravel.blockID;
		theBiomeDecorator.treesPerChunk = -999;
		theBiomeDecorator.deadBushPerChunk = -999;
		theBiomeDecorator.reedsPerChunk = -999;
		theBiomeDecorator.cactiPerChunk = -999;
	}
}
