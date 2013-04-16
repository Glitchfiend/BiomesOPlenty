package com.bopteam.biomesop.biomes;

import java.util.Random;

import com.bopteam.biomesop.configuration.BOPBlocks;
import com.bopteam.biomesop.worldgen.WorldGenDeciduous;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDeciduousForest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDeciduousForest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 15;
        this.customBiomeDecorator.grassPerChunk = 10;
		this.customBiomeDecorator.flowersPerChunk = -999;
		this.customBiomeDecorator.toadstoolsPerChunk = 1;
		this.customBiomeDecorator.bushesPerChunk = 8;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenShrub(2,2) : new WorldGenDeciduous(false));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(5) == 0 ? new WorldGenTallGrass(BOPBlocks.mediumGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1);
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 12695369;
    }
	
    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 12896570;
    }
}
