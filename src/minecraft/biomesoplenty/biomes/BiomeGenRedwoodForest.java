package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.worldgen.WorldGenRedwoodTree;
import biomesoplenty.worldgen.WorldGenRedwoodTree2;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRedwoodForest extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenRedwoodForest(int par1)
    {
        super(par1);
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 6;
        this.customBiomeDecorator.grassPerChunk = 16;
		this.customBiomeDecorator.bushesPerChunk = 4;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenRedwoodTree(false) : (par1Random.nextInt(2) == 0 ? new WorldGenShrub(0,0) : new WorldGenRedwoodTree2(false)));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}
