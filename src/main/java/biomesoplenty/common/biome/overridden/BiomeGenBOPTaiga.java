package biomesoplenty.common.biome.overridden;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class BiomeGenBOPTaiga extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPTaiga(int biomeID, BiomeGenBase inheritedBiome)
	{
		super(biomeID, inheritedBiome);
		
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 4;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 2;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 8), 8);
	}
}
