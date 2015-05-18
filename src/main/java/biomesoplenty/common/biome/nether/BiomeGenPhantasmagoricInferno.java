package biomesoplenty.common.biome.nether;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.biome.BOPNetherBiome;
import net.minecraft.world.gen.feature.WorldGenFire;

public class BiomeGenPhantasmagoricInferno extends BOPNetherBiome {
    public BiomeGenPhantasmagoricInferno(int id) {
        super(id);

        this.setColor(14247446);

        this.topBlock = BOPCBlocks.ash;
        this.fillerBlock = BOPCBlocks.ash;

        this.theBiomeDecorator.bopFeatures.netherLavaLakesPerChunk = 20;
        this.theBiomeDecorator.bopFeatures.smolderingGrassPerChunk = 30;
        this.theBiomeDecorator.bopFeatures.gravesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.waspHivesPerChunk = 1;
        this.theBiomeDecorator.bopFeatures.generateAsh = true;

        this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 8;

        this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenFire(), 1D);
    }
}
