package tdwp_ftw.biomesop.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.worldgen.WorldGenAutumn;
import tdwp_ftw.biomesop.worldgen.WorldGenAutumn2;
import tdwp_ftw.biomesop.worldgen.WorldGenDeadTree2;
import tdwp_ftw.biomesop.worldgen.WorldGenMaple;
import tdwp_ftw.biomesop.worldgen.WorldGenTaiga5;

public class BiomeGenSeasonalForest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenSeasonalForest(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 20;
        this.customBiomeDecorator.grassPerChunk = 8;
        this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.toadstoolsPerChunk = 4;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(mod_BiomesOPlenty.mediumGrass.blockID, 1) : new WorldGenTallGrass(mod_BiomesOPlenty.shortGrass.blockID, 1);
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenAutumn2(false) : (par1Random.nextInt(3) == 0 ? new WorldGenAutumn(false) : (par1Random.nextInt(3) == 0 ? new WorldGenMaple(false) : (par1Random.nextInt(5) == 0 ? new WorldGenDeadTree2(false) : this.worldGeneratorTrees))));
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 11781186;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
		return 12502092;
		//return 12502595;
    }
}
