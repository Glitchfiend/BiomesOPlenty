package biomesoplenty.common.biomes.nether;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenVisceralHeap extends BOPNetherBiome
{
    public BiomeGenVisceralHeap(int id)
    {
        super(id);

        this.setColor(11091006);

        this.topBlock = BOPBlockHelper.get("flesh");
        this.fillerBlock = BOPBlockHelper.get("flesh");

        //customBiomeDecorator.gravesPerChunk = 1;
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
    }
}
