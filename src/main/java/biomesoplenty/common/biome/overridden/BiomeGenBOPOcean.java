package biomesoplenty.common.biome.overridden;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;

public class BiomeGenBOPOcean extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPOcean(int biomeID, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, inheritedBiome);
		
        this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 20;
	}
}
