package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenPineTree;

public class BiomeGenMountain extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMountain(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 2;
        this.customBiomeDecorator.grassPerChunk = 3;
		this.customBiomeDecorator.berryBushesPerChunk = 3;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(4) == 0 ? this.worldGeneratorTrees : new WorldGenPineTree());
    }
}
