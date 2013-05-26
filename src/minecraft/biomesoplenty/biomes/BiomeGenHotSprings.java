package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.api.Liquids;
import biomesoplenty.worldgen.WorldGenPineTree;
import biomesoplenty.worldgen.WorldGenTaiga6;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHotSprings extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenHotSprings(int par1)
    {
        super(par1);
		this.topBlock = (byte)Block.stone.blockID;
        this.fillerBlock = (byte)Block.stone.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 3;
        this.customBiomeDecorator.grassPerChunk = -999;
		this.customBiomeDecorator.outbackPerChunk = 5;
		this.customBiomeDecorator.hotSpringsPerChunk = 25;
		this.customBiomeDecorator.lavaLakesPerChunk = 5;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenPineTree() : new WorldGenTaiga6(false));
    }
}
