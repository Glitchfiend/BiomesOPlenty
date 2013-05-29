package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenCanyonShrub;
import biomesoplenty.worldgen.WorldGenPineTree;

public class BiomeGenCanyon extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenCanyon(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)Blocks.hardDirt.get().blockID;
        this.fillerBlock = (byte)Blocks.hardDirt.get().blockID;
        this.customBiomeDecorator.treesPerChunk = 7;
		this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.canyonGrassPerChunk = 5;
		this.customBiomeDecorator.aloePerChunk = 2;
		this.customBiomeDecorator.generatePumpkins = false;
		this.customBiomeDecorator.generateCanyon = true;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(9) == 0 ? new WorldGenPineTree() : new WorldGenCanyonShrub(0,0));
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 11123300;
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 11123300;
    }
}
