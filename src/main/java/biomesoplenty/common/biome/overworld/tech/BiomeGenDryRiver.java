package biomesoplenty.common.biome.overworld.tech;

import biomesoplenty.common.biome.BOPOverworldBiome;
import net.minecraft.init.Blocks;

public class BiomeGenDryRiver extends BOPOverworldBiome {
    private static final Height biomeHeight = new Height(-0.2F, 0.0F);

    public BiomeGenDryRiver(int biomeId) {
        super(biomeId);

        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setTemperatureRainfall(1.0F, 0.0F);

        this.spawnableCreatureList.clear();

        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;

        //this.bopWorldFeatures.setFeature("sandstoneSpikesPerChunk", 5);
    }
}