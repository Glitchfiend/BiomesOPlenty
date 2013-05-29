package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenTaiga6;

public class BiomeGenAlps extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenAlps(int par1)
    {
        super(par1);
        this.topBlock = (byte)Block.stone.blockID;
        this.fillerBlock = (byte)Block.stone.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
		this.customBiomeDecorator.hotSpringsPerChunk = 4;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenTaiga6(false);
    }
}
