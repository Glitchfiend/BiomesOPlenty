package biomesoplenty.biomes;

import java.util.Random;

import biomesoplenty.worldgen.WorldGenPalmTree3;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenOasis extends BiomeGenBase
{
    private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOasis(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.sand.blockID;
        this.fillerBlock = (byte)Block.sand.blockID;
        this.theBiomeDecorator = new BiomeDecoratorBOP(this);
        this.customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 3;
        this.customBiomeDecorator.grassPerChunk = 15;
        this.customBiomeDecorator.reedsPerChunk = 100;
        this.customBiomeDecorator.oasesPerChunk = 999;
        this.customBiomeDecorator.oasesPerChunk2 = 999;
		this.customBiomeDecorator.cactiPerChunk = 7;
		this.customBiomeDecorator.desertSproutsPerChunk = 3;
		this.customBiomeDecorator.tinyCactiPerChunk = 2;
		this.customBiomeDecorator.generatePumpkins = false;
		this.customBiomeDecorator.generateMelons = true;
		this.customBiomeDecorator.generateQuicksand = true;
		this.customBiomeDecorator.waterLakesPerChunk = 10;
		this.customBiomeDecorator.aloePerChunk = 4;
		this.customBiomeDecorator.hotSpringsPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenPalmTree3();
    }
}
