package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPInheritedBiome;
import biomesoplenty.common.biome.decoration.BOPOverworldBiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class BOPInheritedOverworldBiome extends BOPInheritedBiome<BOPOverworldBiomeDecorator> {
    public BOPInheritedOverworldBiome(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, BOPOverworldBiomeDecorator.class, inheritedBiome);
    }
}
