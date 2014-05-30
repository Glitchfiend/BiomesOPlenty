package biomesoplenty.common.biomes.overworld.tech;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.world.features.WorldGenBOPTallGrass;
import biomesoplenty.common.world.features.trees.WorldGenBOPShrub;
import biomesoplenty.common.world.features.trees.WorldGenMiniShrub;

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