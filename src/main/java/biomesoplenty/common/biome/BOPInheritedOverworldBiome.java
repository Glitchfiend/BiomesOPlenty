package biomesoplenty.common.biome;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.biome.BOPInheritedBiome;
import biomesoplenty.common.biome.decoration.BOPOverworldBiomeDecorator;

public class BOPInheritedOverworldBiome extends BOPInheritedBiome<BOPOverworldBiomeDecorator>
{
	public BOPInheritedOverworldBiome(int biomeID, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, BOPOverworldBiomeDecorator.class, inheritedBiome);
	}
}
