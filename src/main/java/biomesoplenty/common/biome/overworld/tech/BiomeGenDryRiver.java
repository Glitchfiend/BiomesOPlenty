package biomesoplenty.common.biome.overworld.tech;

import net.minecraft.init.Blocks;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.biome.BOPOverworldBiome;

public class BiomeGenDryRiver extends BOPOverworldBiome
{
	private static final Height biomeHeight = new Height(-0.2F, 0.0F);

    public BiomeGenDryRiver(int biomeId)
    {
        super(biomeId);

        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setTemperatureRainfall(1.2F, 0.5F);
        
        this.spawnableCreatureList.clear();

        this.topBlock = Blocks.sand;
		this.fillerBlock = Blocks.sand;
		
		//this.bopWorldFeatures.setFeature("sandstoneSpikesPerChunk", 5);
    }
}