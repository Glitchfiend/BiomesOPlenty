package biomesoplenty.common.biomes.overworld.techbiomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenLushRiver extends BOPBiome
{
	private static final Height biomeHeight = new Height(-0.5F, 0.0F);

    public BiomeGenLushRiver(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.setTemperatureRainfall(0.6F, 0.7F);
        
        this.setHeight(biomeHeight);
        
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.waterlilyPerChunk = 8;
        
        this.bopWorldFeatures.setFeature("waterReedsPerChunk", 8);
        this.bopWorldFeatures.setFeature("riverCanePerChunk", 10);
        this.bopWorldFeatures.setFeature("seaweedPerChunk", 15);
        this.bopWorldFeatures.setFeature("algaePerChunk", 10);
        
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 30);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
    }
    
    @Override
    //TODO:                     getRandomWorldGenForTrees()
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return new WorldGenBOPShrub(Blocks.log, Blocks.leaves, 0, 0, Blocks.grass);
    }
}