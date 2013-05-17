package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.worldgen.WorldGenCherry1;
import biomesoplenty.worldgen.WorldGenCherry2;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCherryBlossomGrove extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenCherryBlossomGrove(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 3;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.pinkFlowersPerChunk = 15;
        this.customBiomeDecorator.whiteFlowersPerChunk = 30;
        this.customBiomeDecorator.tinyFlowersPerChunk = 25;
        this.customBiomeDecorator.grassPerChunk = 15;
		this.customBiomeDecorator.lilyflowersPerChunk = 9;
		this.customBiomeDecorator.generatePumpkins = false;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenCherry2(false) : new WorldGenCherry1(false));
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 10747818;
    }
    
    public int getBiomeFoliageColor()
    {
        return 10747818;
    }
}
