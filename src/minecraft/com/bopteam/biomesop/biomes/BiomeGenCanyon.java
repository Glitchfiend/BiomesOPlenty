package com.bopteam.biomesop.biomes;

import java.util.Random;

import com.bopteam.biomesop.configuration.BOPBlocks;
import com.bopteam.biomesop.worldgen.WorldGenCanyonShrub;
import com.bopteam.biomesop.worldgen.WorldGenCanyonTree;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCanyon extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenCanyon(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.topBlock = (byte)BOPBlocks.hardDirt.blockID;
        this.fillerBlock = (byte)BOPBlocks.hardDirt.blockID;
        this.customBiomeDecorator.treesPerChunk = 10;
		this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.tinyCactiPerChunk = 2;
		this.customBiomeDecorator.generatePumpkins = false;
		//this.customBiomeDecorator.generateCanyon = true;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenCanyonTree() : new WorldGenCanyonShrub(0,0));
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 11123300;
    }
}
