package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenOceanKelp extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenOceanKelp(int par1)
    {
        super(par1);
		this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.grassPerChunk = 50;
        this.spawnableCreatureList.clear();
    }
	

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Blocks.coral.get().blockID, 0);
    }
}
