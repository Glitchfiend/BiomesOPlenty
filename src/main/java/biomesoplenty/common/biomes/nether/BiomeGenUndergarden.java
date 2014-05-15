package biomesoplenty.common.biomes.nether;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBrush1;
import biomesoplenty.common.world.features.trees.WorldGenBrush2;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

public class BiomeGenUndergarden extends BOPNetherBiome
{
    public BiomeGenUndergarden(int id)
    {
        super(id);
        
        this.setColor(15657658);
        
        this.topBlock = BOPBlockHelper.get("overgrownNetherrack");
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
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 1), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 2), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 10), 0.5D);
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPBlockHelper.get("foliage"), 11), 0.5D);
    }
    
	@Override
	//TODO:						getRandomWorldGenForTrees()
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return new WorldGenMiniShrub(BOPBlockHelper.get("logs4"), BOPBlockHelper.get("leaves4"), 1, 0, BOPBlockHelper.get("overgrownNetherrack"));
	}
}
