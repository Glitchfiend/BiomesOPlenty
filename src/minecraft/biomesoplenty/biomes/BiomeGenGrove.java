package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.worldgen.WorldGenPoplar;
import biomesoplenty.worldgen.WorldGenPoplar2;
import biomesoplenty.worldgen.WorldGenChaparral2;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenGrove extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenGrove(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.treesPerChunk = 3;
        this.customBiomeDecorator.flowersPerChunk = 5;
		this.customBiomeDecorator.tinyFlowersPerChunk = 80;
		this.customBiomeDecorator.whiteFlowersPerChunk = 15;
        this.customBiomeDecorator.grassPerChunk = 8;
		this.customBiomeDecorator.sproutsPerChunk = 1;
		this.customBiomeDecorator.lilyflowersPerChunk = 3;
		this.customBiomeDecorator.berryBushesPerChunk = 2;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(2) == 0 ? new WorldGenChaparral2() : par1Random.nextInt(3) == 0 ? new WorldGenPoplar2() : new WorldGenPoplar());
        //return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenNorwaySpruce1() : new WorldGenNorwaySpruce2());
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2);
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 5341009;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 6396257;
    }
}
