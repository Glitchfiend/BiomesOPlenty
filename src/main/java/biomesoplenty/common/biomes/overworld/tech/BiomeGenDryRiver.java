package biomesoplenty.common.biomes.overworld.tech;

import net.minecraft.init.Blocks;
import biomesoplenty.common.biomes.BOPBiome;

public class BiomeGenDryRiver extends BOPBiome
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