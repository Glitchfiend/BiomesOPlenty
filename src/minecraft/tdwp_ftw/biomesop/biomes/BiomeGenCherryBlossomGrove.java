package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.worldgen.WorldGenCherry1;
import tdwp_ftw.biomesop.worldgen.WorldGenCherry2;

public class BiomeGenCherryBlossomGrove extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenCherryBlossomGrove(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 5;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.pinkFlowersPerChunk = 15;
        this.customBiomeDecorator.whiteFlowersPerChunk = 30;
        this.customBiomeDecorator.tinyFlowersPerChunk = 25;
        this.customBiomeDecorator.grassPerChunk = 15;
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
}
