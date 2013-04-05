package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga3;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga4;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga9;

public class BiomeGenArctic extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenArctic(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.treesPerChunk = 3;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {	
		return (WorldGenerator)(par1Random.nextInt(5) == 0 ? new WorldGenTaiga3(false) : (par1Random.nextInt(3) == 0 ? new WorldGenTaiga4(false) : new WorldGenTaiga9(false)));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1);
    }
}
