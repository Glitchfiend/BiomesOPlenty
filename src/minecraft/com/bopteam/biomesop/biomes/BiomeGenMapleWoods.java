package com.bopteam.biomesop.biomes;

import java.util.Random;

import com.bopteam.biomesop.worldgen.WorldGenMaple;
import com.bopteam.biomesop.worldgen.WorldGenTaiga5;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenMapleWoods extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMapleWoods(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 9;
        this.customBiomeDecorator.grassPerChunk = 1;
		this.customBiomeDecorator.violetsPerChunk = 1;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(6) == 0 ? new WorldGenTaiga5(false) : new WorldGenMaple(false));
    }
}
