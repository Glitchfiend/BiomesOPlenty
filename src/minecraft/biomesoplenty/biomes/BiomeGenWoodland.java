package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenLog;

public class BiomeGenWoodland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenWoodland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 9;
        this.customBiomeDecorator.grassPerChunk = 7;
		this.customBiomeDecorator.toadstoolsPerChunk = 3;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new WorldGenLog() : (par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }
}
