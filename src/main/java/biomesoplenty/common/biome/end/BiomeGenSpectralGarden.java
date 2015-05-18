package biomesoplenty.common.biome.end;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPEndBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import net.minecraft.init.Blocks;

public class BiomeGenSpectralGarden extends BOPEndBiome {
    public BiomeGenSpectralGarden(int id) {
        super(id);

        this.setColor(15657658);

        this.topBlock = BOPCBlocks.bopGrass;
        this.fillerBlock = Blocks.end_stone;

        this.theBiomeDecorator.treesPerChunk = -999;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 10;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.plants, 4), 1D);
    }
}
