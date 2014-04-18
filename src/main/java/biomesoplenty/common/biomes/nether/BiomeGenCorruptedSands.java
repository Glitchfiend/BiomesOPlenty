package biomesoplenty.common.biomes.nether;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenCorruptedSands extends BOPNetherBiome
{
    public BiomeGenCorruptedSands(int id)
    {
        super(id);
        
        this.topBlock = Blocks.soul_sand;
        this.fillerBlock = Blocks.soul_sand;
        
        this.bopWorldFeatures.setFeature("thornsPerChunk", 10);
        //customBiomeDecorator.gravesPerChunk = 1;
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
    }
}
