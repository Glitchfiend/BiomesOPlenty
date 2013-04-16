package com.bopteam.biomesop.biomes;

import java.util.Random;

import com.bopteam.biomesop.worldgen.WorldGenTaiga5;
import com.bopteam.biomesop.worldgen.WorldGenTaiga8;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenField extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenField(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		this.customBiomeDecorator.treesPerChunk = 1;
        this.customBiomeDecorator.flowersPerChunk = 1;
        this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenTaiga5(false) : (par1Random.nextInt(8) == 0 ? new WorldGenTaiga8(false) : (par1Random.nextInt(2) == 0 ? this.worldGeneratorTrees : new WorldGenShrub(0,0))));
    }
	
    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 11186770;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 10467150;
    }
}
