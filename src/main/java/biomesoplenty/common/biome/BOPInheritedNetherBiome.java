package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPInheritedBiome;
import biomesoplenty.common.biome.decoration.BOPNetherBiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class BOPInheritedNetherBiome extends BOPInheritedBiome<BOPNetherBiomeDecorator> {
    public BOPInheritedNetherBiome(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, BOPNetherBiomeDecorator.class, inheritedBiome);
    }
}
