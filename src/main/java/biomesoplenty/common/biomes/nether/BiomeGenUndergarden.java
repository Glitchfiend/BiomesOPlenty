package biomesoplenty.common.biomes.nether;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biomes.BOPNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenUndergarden extends BOPNetherBiome
{
    public BiomeGenUndergarden(int id)
    {
        super(id);
        
        this.setColor(15657658);
        
        this.topBlock = BOPCBlocks.overgrownNetherrack;
        this.fillerBlock = Blocks.netherrack;
        
        this.theBiomeDecorator.treesPerChunk = 50;
        
        this.bopWorldFeatures.setFeature("netherVinesPerChunk", 20);
        this.bopWorldFeatures.setFeature("netherrackSplatterPerChunk", 45);
        this.bopWorldFeatures.setFeature("bopBigMushroomsPerChunk", 30);
        this.bopWorldFeatures.setFeature("gravesPerChunk", 1);
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 10);
        this.bopWorldFeatures.setFeature("glowshroomsPerChunk", 5);
        
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 50);
		
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 0.25D);
		this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
    }
    
	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return new WorldGenMiniShrub(BOPCBlocks.logs4, BOPCBlocks.leaves4, 1, 0, BOPCBlocks.overgrownNetherrack);
	}
}
