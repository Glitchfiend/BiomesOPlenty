package biomesoplenty.common.biomes.nether;

import net.minecraft.init.Blocks;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenCorruptedSands extends BOPNetherBiome
{
    public BiomeGenCorruptedSands(int id)
    {
        super(id);
        
        this.setColor(7691854);
        
        this.topBlock = Blocks.soul_sand;
        this.fillerBlock = Blocks.soul_sand;
        
        this.bopWorldFeatures.setFeature("thornsPerChunk", 10);
        this.bopWorldFeatures.setFeature("gravesPerChunk", 1);
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
    }
}
