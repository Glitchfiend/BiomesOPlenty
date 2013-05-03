package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.worldgen.WorldGenMoss;
import biomesoplenty.worldgen.WorldGenTaiga5;
import biomesoplenty.worldgen.WorldGenWillow;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenWetland extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenWetland(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 10;
        this.customBiomeDecorator.grassPerChunk = 10;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.mushroomsPerChunk = 5;
		this.customBiomeDecorator.toadstoolsPerChunk = 1;
        this.customBiomeDecorator.reedsPerChunk = 15;
        this.customBiomeDecorator.clayPerChunk = 2;
        this.customBiomeDecorator.sandPerChunk = -999;
        this.customBiomeDecorator.sandPerChunk2 = -999;
        this.customBiomeDecorator.mudPerChunk = 5;
        this.customBiomeDecorator.mudPerChunk2 = 5;
        this.customBiomeDecorator.waterlilyPerChunk = 6;
        this.customBiomeDecorator.cattailsPerChunk = 20;
        this.customBiomeDecorator.blueFlowersPerChunk = 6;
		this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		this.waterColorMultiplier = 6512772;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		//return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenWillow2() : (par1Random.nextInt(4) == 0 ? new WorldGenLarch1() : (par1Random.nextInt(2) == 0 ? new WorldGenLarch2() : new WorldGenWillow1())));
        return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenTaiga5(false) : new WorldGenWillow());
    }
	
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenMoss var5 = new WorldGenMoss();

        for (int var6 = 0; var6 < 20; ++var6)
        {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 58;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(6) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 5935967;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 5215831;
    }
}
