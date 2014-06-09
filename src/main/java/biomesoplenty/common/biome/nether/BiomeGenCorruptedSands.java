package biomesoplenty.common.biome.nether;

import net.minecraft.init.Blocks;
import biomesoplenty.common.biome.BOPNetherBiome;

public class BiomeGenCorruptedSands extends BOPNetherBiome
{
    public BiomeGenCorruptedSands(int id)
    {
        super(id);
        
        this.setColor(7691854);
        
        this.topBlock = Blocks.soul_sand;
        this.fillerBlock = Blocks.soul_sand;
        
        this.theBiomeDecorator.bopFeatures.thornsPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waspHivesPerChunk = 1;
    }
}
