package biomesoplenty.common.biome.nether;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPDoubleFlora;

public class BiomeGenVisceralHeap extends BOPNetherBiome
{
    public BiomeGenVisceralHeap(int id)
    {
        super(id);

        this.setColor(11091006);

        this.topBlock = BOPCBlocks.flesh;
        this.fillerBlock = BOPCBlocks.flesh;

        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waspHivesPerChunk = 1;
        
        this.theBiomeDecorator.bopFeatures.bloodLakesPerChunk = 15;
        //this.theBiomeDecorator.bopFeatures.bloodSpringsPerChunk = 10;
        
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 3;
        
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(BOPCBlocks.flowers, BOPCBlocks.flowers, 13, 14), 6);
    }
}
