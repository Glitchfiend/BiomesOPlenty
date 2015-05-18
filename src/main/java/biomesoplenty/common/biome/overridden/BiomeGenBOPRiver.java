package biomesoplenty.common.biome.overridden;

import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBOPRiver extends BOPInheritedOverworldBiome {
    public BiomeGenBOPRiver(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, inheritedBiome);

        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 10;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
    }
}
