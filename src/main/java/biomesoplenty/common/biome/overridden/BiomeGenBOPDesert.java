package biomesoplenty.common.biome.overridden;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;

public class BiomeGenBOPDesert extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPDesert(int biomeID, BiomeGenBase inheritedBiome)
	{
		super(biomeID, inheritedBiome);
		
        this.theBiomeDecorator.bopFeatures.tinyCactiPerChunk = 10;
        if (BOPConfigurationTerrainGen.generateQuicksand)
        	this.theBiomeDecorator.bopFeatures.generateQuicksand = true;
	}
}
