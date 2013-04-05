package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSteppe extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenSteppe(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
		this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.grassPerChunk = 15;
        this.customBiomeDecorator.deadBushPerChunk = 7;
		this.customBiomeDecorator.tinyCactiPerChunk = 1;
		this.customBiomeDecorator.quicksandPerChunk = 1;
		this.customBiomeDecorator.steppePerChunk = 6;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
		return (par1Random.nextInt(8) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1)));
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 13413215;
    }
}
