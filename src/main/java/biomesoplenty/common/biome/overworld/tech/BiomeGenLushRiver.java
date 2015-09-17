package biomesoplenty.common.biome.overworld.tech;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;

public class BiomeGenLushRiver extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(-0.5F, 0.0F);

    public BiomeGenLushRiver(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.setTemperatureRainfall(0.7F, 0.8F);
        
        this.setHeight(biomeHeight);
        
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.waterlilyPerChunk = 8;
        
        this.theBiomeDecorator.bopFeatures.bopLilyPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 10;
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 15;
        this.theBiomeDecorator.bopFeatures.algaePerChunk = 10;
        
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 30;
        
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }
    
    @Override
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass);
    }
}