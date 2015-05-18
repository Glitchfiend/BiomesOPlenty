package biomesoplenty.common.biome.overridden;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPInheritedOverworldBiome;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBOPExtremeHills extends BOPInheritedOverworldBiome {
    public BiomeGenBOPExtremeHills(int biomeID, BiomeGenBase inheritedBiome) {
        super(biomeID, inheritedBiome);

        this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 1;

        this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 3;
        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 5;

        this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPFlora(BOPCBlocks.flowers, 8), 8);

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10), 0.5D);
        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11), 0.5D);
    }
}
