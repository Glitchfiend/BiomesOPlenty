package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase.Height;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.configuration.BOPConfigurationMisc;

public class BiomeGenVolcano extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.6F, 1.2F);

    public BiomeGenVolcano(int id)
    {
        super(id);
        
        //TODO: setHeight()
        this.func_150570_a(biomeHeight);
        this.setDisableRain();
        //TODO: setColor()
        this.setColor(6645093);
        this.setTemperatureRainfall(2.0F, 0.05F);

        this.spawnableCreatureList.clear();

        this.topBlock = BOPBlockHelper.get("ashStone");
        this.fillerBlock = BOPBlockHelper.get("ashStone");
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;

        this.bopWorldFeatures.lavaLakesPerChunk = 50;
        this.bopWorldFeatures.lavaSpoutsPerChunk = 10;
        this.bopWorldFeatures.generateAsh = true;
    }

    @Override
    public int getSkyColorByTemp(float par1)
    {
        if (BOPConfigurationMisc.skyColors) return 8026746;
        else return super.getSkyColorByTemp(par1);
    }
}
