package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenMangrove;

public class BiomeGenMangrove extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMangrove(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 6;
        this.customBiomeDecorator.deadBushPerChunk = 1;
		this.customBiomeDecorator.deadGrassPerChunk = 9;
        this.customBiomeDecorator.reedsPerChunk = -999;
        this.customBiomeDecorator.cactiPerChunk = -999;
		this.customBiomeDecorator.desertSproutsPerChunk = 1;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenMangrove(false);
    }
}
