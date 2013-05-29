package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenScrubland;

public class BiomeGenScrubland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenScrubland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 7;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.highGrassPerChunk = 2;
        this.customBiomeDecorator.grassPerChunk = 30;
		this.customBiomeDecorator.generatePumpkins = false;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenShrub(0, 0) : new WorldGenScrubland(false));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(5) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}
