package biomesoplenty.common.biomes.nether;

import net.minecraft.world.gen.feature.WorldGenFire;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BiomeGenPhantasmagoricInferno extends BOPNetherBiome
{
    public BiomeGenPhantasmagoricInferno(int id)
    {
        super(id);

        this.setColor(14247446);

        this.topBlock = BOPBlockHelper.get("ash");
        this.fillerBlock = BOPBlockHelper.get("ash");

        this.bopWorldFeatures.setFeature("netherLavaLakesPerChunk", 20);
        this.bopWorldFeatures.setFeature("smolderingGrassPerChunk", 8);
        this.bopWorldFeatures.setFeature("gravesPerChunk", 1);
        this.bopWorldFeatures.setFeature("waspHivesPerChunk", 1);
        this.bopWorldFeatures.setFeature("generateAsh", true);
        
        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 8);
        
        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenFire(), 1D);
    }
}
