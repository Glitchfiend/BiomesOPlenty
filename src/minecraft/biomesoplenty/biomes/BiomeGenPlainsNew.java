package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class BiomeGenPlainsNew extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenPlainsNew(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.flowersPerChunk = 4;
        this.customBiomeDecorator.grassPerChunk = 10;
		this.customBiomeDecorator.tinyFlowersPerChunk = 1;
		this.customBiomeDecorator.portobellosPerChunk = 1;
		this.customBiomeDecorator.sunflowersPerChunk = 2;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}
