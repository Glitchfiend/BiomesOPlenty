package biomesoplenty.common.biome.nether;

import biomesoplenty.common.biome.BOPNetherBiome;
import net.minecraft.init.Blocks;

public class BiomeGenBoneyard extends BOPNetherBiome {
    public BiomeGenBoneyard(int id) {
        super(id);

        this.setColor(15657658);

        this.topBlock = Blocks.netherrack;
        this.fillerBlock = Blocks.netherrack;

        this.theBiomeDecorator.bopFeatures.boneSpinesUpPerChunk = 9;
        this.theBiomeDecorator.bopFeatures.boneSpinesDownPerChunk = 12;
        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waspHivesPerChunk = 1;
    }
}
