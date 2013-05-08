package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.worldgen.WorldGenAcacia;
import biomesoplenty.worldgen.WorldGenDeadTree3;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLushDesert extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenLushDesert(int par1)
    {
        super(par1);
        this.topBlock = (byte)Blocks.redRock.get().blockID;
        this.fillerBlock = (byte)Blocks.redRock.get().blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 12;
        this.customBiomeDecorator.grassPerChunk = 8;
        this.customBiomeDecorator.oasesPerChunk = 999;
        this.customBiomeDecorator.oasesPerChunk2 = 999;
        this.customBiomeDecorator.deadBushPerChunk = 2;
        this.customBiomeDecorator.purpleFlowersPerChunk = 5;
        this.customBiomeDecorator.desertGrassPerChunk = 10;
		this.customBiomeDecorator.desertCactiPerChunk = 10;
        this.customBiomeDecorator.cactiPerChunk = 20;
		this.customBiomeDecorator.tinyCactiPerChunk = 5;
		this.customBiomeDecorator.aloePerChunk = 3;
        this.customBiomeDecorator.generateGrass = true;
        this.customBiomeDecorator.generateSand = true;
		this.customBiomeDecorator.generatePumpkins = false;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		return (WorldGenerator)(par1Random.nextInt(4) == 0 ? new WorldGenAcacia(false) : (par1Random.nextInt(12) == 0 ? new WorldGenDeadTree3(false) : (par1Random.nextInt(2) == 0 ? this.worldGeneratorTrees : new WorldGenShrub(0,0))));
    }
}
