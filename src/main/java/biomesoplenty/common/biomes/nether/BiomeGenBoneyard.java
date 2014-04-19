package biomesoplenty.common.biomes.nether;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenBoneyard extends BOPNetherBiome
{
    public BiomeGenBoneyard(int id)
    {
        super(id);
        
        this.setColor(15657658);
        
        this.topBlock = Blocks.netherrack;
        this.fillerBlock = Blocks.netherrack;
        
        this.bopWorldFeatures.setFeature("boneSpinesUpPerChunk", 9);
        this.bopWorldFeatures.setFeature("boneSpinesDownPerChunk", 12);
        //customBiomeDecorator.gravesPerChunk = 1;
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
    }
}
