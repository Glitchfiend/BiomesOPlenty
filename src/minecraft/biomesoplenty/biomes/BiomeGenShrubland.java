package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class BiomeGenShrubland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenShrubland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 0;
        this.customBiomeDecorator.flowersPerChunk = 0;
        this.customBiomeDecorator.grassPerChunk = 5;
		this.customBiomeDecorator.bushesPerChunk = 7;
		this.customBiomeDecorator.generatePumpkins = false;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenShrub(0, 0);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
    }
}
