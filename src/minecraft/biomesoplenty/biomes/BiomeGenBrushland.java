package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenBrush1;
import biomesoplenty.worldgen.WorldGenBrush2;
import biomesoplenty.worldgen.WorldGenChaparral2;

public class BiomeGenBrushland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenBrushland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.treesPerChunk = 10;
		this.customBiomeDecorator.grassPerChunk = 6;
		this.customBiomeDecorator.thornsPerChunk = 4;
		this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.generateQuicksand = true;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenBrush2() : (par1Random.nextInt(5) == 0 ?  new WorldGenBrush1() : new WorldGenChaparral2()));
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 13222271;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 11716223;
    }
}
