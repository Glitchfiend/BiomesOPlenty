package tdwp_ftw.biomesop.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadTree;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadTree2;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga5;

public class BiomeGenDeadForest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDeadForest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 3;
		this.customBiomeDecorator.grassPerChunk = 1;
		this.customBiomeDecorator.thornsPerChunk = 2;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.reedsPerChunk = -999;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(9) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenDeadTree(false) : (par1Random.nextInt(4) == 0 ? new WorldGenTaiga5(false): new WorldGenDeadTree2(false)));
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 12362085;
    }
	
    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float par1)
    {
		if (mod_BiomesOPlenty.skyColors = true)
		{
        return 9873591;
		}	
		else
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
    }
}
