package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.worldgen.WorldGenAcacia;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
        this.customBiomeDecorator.treesPerChunk = 2;
        this.customBiomeDecorator.flowersPerChunk = -999;
        this.customBiomeDecorator.purpleFlowersPerChunk = 10;
        this.customBiomeDecorator.tinyFlowersPerChunk = 2;
        this.customBiomeDecorator.grassPerChunk = 25;
		this.customBiomeDecorator.bushesPerChunk = 10;
		this.customBiomeDecorator.generatePumpkins = false;
    }

/**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenAcacia(false) : new WorldGenShrub(0,0));
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
    }
}
