package biomesoplenty.common.biome.overridden;

import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBOPOcean extends BOPInheritedOverworldBiome {
    public BiomeGenBOPOcean(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, inheritedBiome);

        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 20;
    }
}
