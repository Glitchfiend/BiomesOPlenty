package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga7;

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
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(8) == 0 ? new WorldGenTaiga2(false) : (par1Random.nextInt(4) == 0 ? new WorldGenTaiga7(false) : this.worldGeneratorTrees));
    }
}
