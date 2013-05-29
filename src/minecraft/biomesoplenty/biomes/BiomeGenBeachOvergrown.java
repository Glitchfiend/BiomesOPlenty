package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenBogBush;

public class BiomeGenBeachOvergrown extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenBeachOvergrown(int par1)
    {
        super(par1);
		this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.theBiomeDecorator.treesPerChunk = 15;
        this.theBiomeDecorator.deadBushPerChunk = 1;
		this.customBiomeDecorator.duneGrassPerChunk = 25;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.cactiPerChunk = 1;
		this.customBiomeDecorator.outbackPerChunk = 5;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenBogBush();
    }
}
