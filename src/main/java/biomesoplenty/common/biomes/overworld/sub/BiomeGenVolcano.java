package biomesoplenty.common.biomes.overworld.sub;

import net.minecraft.world.biome.BiomeGenBase.Height;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.biomes.BOPSubBiome;
import biomesoplenty.common.configuration.BOPConfigurationMisc;

public class BiomeGenVolcano extends BOPSubBiome
{
    private static final Height biomeHeight = new Height(5.0F, 0.025F);

    public BiomeGenVolcano(int id)
    {
        super(id);
        
        this.zoom = 0.01D;
		this.threshold = 0.5D;
        
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(6645093);
        this.setTemperatureRainfall(2.0F, 0.05F);

        this.spawnableCreatureList.clear();

        this.topBlock = BOPBlockHelper.get("ashStone");
        this.fillerBlock = BOPBlockHelper.get("ashStone");
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;

        this.bopWorldFeatures.setFeature("lavaLakesPerChunk", 20);
        this.bopWorldFeatures.setFeature("lavaSpoutsPerChunk", 1);
        this.bopWorldFeatures.setFeature("generateAsh", true);
    }

    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 8026746;
        else return super.getSkyColorByTemp(par1);
    }
}
