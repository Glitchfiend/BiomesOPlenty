package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.worldgen.WorldGenOutbackShrub;
import tdwp_ftw.biomesop.worldgen.WorldGenOutbackTree;

public class BiomeGenOutback extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOutback(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)BOPBlocks.hardSand.blockID;
        this.fillerBlock = (byte)BOPBlocks.hardSand.blockID;
        this.customBiomeDecorator.treesPerChunk = 3;
		this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.outbackPerChunk = 10;
        this.customBiomeDecorator.deadBushPerChunk = 7;
		this.customBiomeDecorator.tinyCactiPerChunk = 2;
		this.customBiomeDecorator.bushesPerChunk = 5;
		this.customBiomeDecorator.quicksandPerChunk = 1;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenOutbackShrub(0,0) : new WorldGenOutbackTree());
    }
}
