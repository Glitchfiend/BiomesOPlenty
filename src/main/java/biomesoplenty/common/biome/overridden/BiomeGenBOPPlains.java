package biomesoplenty.common.biome.overridden;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;

public class BiomeGenBOPPlains extends BOPInheritedOverworldBiome
{
	public BiomeGenBOPPlains(int biomeID, BiomeGenBase inheritedBiome) 
	{
		super(biomeID, inheritedBiome);
		
        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 8;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;
        this.theBiomeDecorator.bopFeatures.flaxPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 0), 10);
        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 9), 5);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 1), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
	}
}
