package biomesoplenty.common.biome.overridden;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;

public class BiomeGenBOPRiver extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPRiver(int biomeID, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, inheritedBiome);
        
		this.theBiomeDecorator.bopFeatures.seaweedPerChunk = 5;
		this.theBiomeDecorator.bopFeatures.riverCanePerChunk = 10;
		this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 4;
	}
}
