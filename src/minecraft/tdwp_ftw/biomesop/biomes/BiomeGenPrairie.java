package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.configuration.BOPBlocks;
import tdwp_ftw.biomesop.worldgen.WorldGenPrairie;

public class BiomeGenPrairie extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenPrairie(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1;
        this.customBiomeDecorator.grassPerChunk = 999;
        this.customBiomeDecorator.whiteFlowersPerChunk = 45;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenPrairie(false);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		return (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1)));
    }
}
