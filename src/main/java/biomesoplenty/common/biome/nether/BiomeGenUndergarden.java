package biomesoplenty.common.biome.nether;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;
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
        
        this.theBiomeDecorator.bopFeatures.netherVinesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.netherrackSplatterPerChunk = 45;
        this.theBiomeDecorator.bopFeatures.bopBigMushroomsPerChunk = 30;
        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waspHivesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.glowshroomsPerChunk = 5;
                               
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 50;
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 3;
		
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 0), 0.25D);
		this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(BOPCBlocks.flowers, BOPCBlocks.flowers, 13, 14), 6);
    }
    
	@Override
	public WorldGenAbstractTree func_150567_a(Random random)
	{
		return new WorldGenMiniShrub(BOPCBlocks.logs4, BOPCBlocks.leaves4, 1, 0, BOPCBlocks.overgrownNetherrack);
	}
}
