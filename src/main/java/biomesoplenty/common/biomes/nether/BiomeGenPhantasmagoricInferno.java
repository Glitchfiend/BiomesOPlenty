package biomesoplenty.common.biomes.nether;

import net.minecraft.world.gen.feature.WorldGenFire;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biomes.BOPNetherBiome;

public class BiomeGenPhantasmagoricInferno extends BOPNetherBiome
{
    public BiomeGenPhantasmagoricInferno(int id)
    {
        super(id);

        this.setColor(14247446);

        this.topBlock = BOPCBlocks.ash;
        this.fillerBlock = BOPCBlocks.ash;

        this.bopWorldFeatures.setFeature("netherLavaLakesPerChunk", 20);
        this.bopWorldFeatures.setFeature("smolderingGrassPerChunk", 30);
        this.bopWorldFeatures.setFeature("gravesPerChunk", 1);
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        this.bopWorldFeatures.setFeature("generateAsh", true);
        
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 8);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenFire(), 1D);
    }
}
