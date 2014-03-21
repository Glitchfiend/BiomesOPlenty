package biomesoplenty.common.biomes.nether;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPNetherBiome;
import net.minecraft.world.gen.feature.WorldGenFire;

public class BiomeGenPhantasmagoricInferno extends BOPNetherBiome
{
    public BiomeGenPhantasmagoricInferno(int id)
    {
        super(id);

        this.setColor(14247446);

        this.topBlock = BOPBlockHelper.get("ash");
        this.fillerBlock = BOPBlockHelper.get("ash");

        this.bopWorldFeatures.setFeature("bopGrassPerChunk", 8);

        this.bopWorldFeatures.weightedGrassGen.put(new WorldGenFire(), 1D);
    }
}
