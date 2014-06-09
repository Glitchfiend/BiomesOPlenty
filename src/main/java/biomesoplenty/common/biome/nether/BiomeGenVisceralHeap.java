package biomesoplenty.common.biome.nether;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPNetherBiome;

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
    }
}
