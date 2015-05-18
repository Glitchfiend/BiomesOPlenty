package biomesoplenty.common.biome.overridden;

import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBOPMushroomIsland extends BOPInheritedOverworldBiome {
    public BiomeGenBOPMushroomIsland(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, inheritedBiome);

        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.portobellosPerChunk = 6;
    }
}
