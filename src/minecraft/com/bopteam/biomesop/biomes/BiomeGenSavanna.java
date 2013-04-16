package com.bopteam.biomesop.biomes;

import java.util.Random;

import com.bopteam.biomesop.configuration.BOPBlocks;
import com.bopteam.biomesop.worldgen.WorldGenAcacia;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSavanna extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

    public BiomeGenSavanna(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.purpleFlowersPerChunk = 10;
        this.customBiomeDecorator.tinyFlowersPerChunk = 2;
        this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.generatePumpkins = false;
    }

/**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenShrub(0, 0) : new WorldGenAcacia(false));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(BOPBlocks.shortGrass.blockID, 1);
    }
}
