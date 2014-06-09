package biomesoplenty.common.biome.overridden;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenBOPRoofedForest extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPRoofedForest(int biomeID, BiomeGenBase inheritedBiome)
	{
		super(biomeID, inheritedBiome);
		
        this.theBiomeDecorator.bopFeatures.toadstoolsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.blueMilksPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 4;
        this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.25D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.25D);
	}
}