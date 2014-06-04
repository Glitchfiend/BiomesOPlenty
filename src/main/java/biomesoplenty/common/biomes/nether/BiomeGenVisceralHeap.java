package biomesoplenty.common.biomes.nether;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenVisceralHeap extends BOPNetherBiome
{
    public BiomeGenVisceralHeap(int id)
    {
        super(id);

        this.setColor(11091006);

        this.topBlock = BOPCBlocks.flesh;
        this.fillerBlock = BOPCBlocks.flesh;

        this.bopWorldFeatures.setFeature("gravesPerChunk", 1);
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
    }
}
