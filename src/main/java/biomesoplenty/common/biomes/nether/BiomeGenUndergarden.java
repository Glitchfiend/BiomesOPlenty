package biomesoplenty.common.biomes.nether;

import net.minecraft.init.Blocks;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenUndergarden extends BOPNetherBiome
{
    public BiomeGenUndergarden(int id)
    {
        super(id);
        
        this.setColor(15657658);
        
        this.topBlock = Blocks.netherrack;
        this.fillerBlock = Blocks.netherrack;
        
        this.bopWorldFeatures.setFeature("netherVinesPerChunk", 12);
        this.bopWorldFeatures.setFeature("overgrownNetherrackPerChunk", 60);
        //customBiomeDecorator.gravesPerChunk = 1;
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        this.bopWorldFeatures.setFeature("toadstoolsPerChunk", 3);
        this.bopWorldFeatures.setFeature("glowshroomsPerChunk", 1);
        this.theBiomeDecorator.mushroomsPerChunk = 30;
		this.theBiomeDecorator.bigMushroomsPerChunk = 30;
    }
}
