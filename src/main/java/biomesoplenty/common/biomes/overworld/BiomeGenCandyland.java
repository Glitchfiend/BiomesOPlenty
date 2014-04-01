package biomesoplenty.common.biomes.overworld;

import java.util.Random;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.features.WorldGenCookie;

public class BiomeGenCandyland extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.2F);

	public BiomeGenCandyland(int id)
	{
		super(id);
		
        //TODO: setHeight()
        this.setHeight(biomeHeight);
        //TODO: setColor()
        this.setColor(10341485);
        this.setTemperatureRainfall(0.7F, 0.8F);
        
        this.waterColorMultiplier = 11485208;
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
		
		this.topBlock = BOPBlockHelper.get("frostedCake");
		this.fillerBlock = BOPBlockHelper.get("cakeBlock");
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.generateLakes = false;
		this.theBiomeDecorator.clayPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;

        this.bopWorldFeatures.setFeature("rootsPerChunk", -999);
        this.bopWorldFeatures.setFeature("stalagmitesPerChunk", -999);
        this.bopWorldFeatures.setFeature("stalactitesPerChunk", -999);
        this.bopWorldFeatures.setFeature("minersDelightPerChunk", -999);
        this.bopWorldFeatures.setFeature("cookiesPerChunk", 4);
        //TODO: FEATURE this.theBiomeDecorator.generateUndergroundLakes = false;
        this.bopWorldFeatures.setFeature("generatePumpkins", false);
	}
	
	@Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
		return 16760265;
	}

	@Override
    public int getBiomeFoliageColor(int x, int y, int z)
    {
		return 16760265;
	}

	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors) return 16760265;
		else return super.getSkyColorByTemp(par1);
	}
}
