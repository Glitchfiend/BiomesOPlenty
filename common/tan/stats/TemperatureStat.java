package tan.stats;

import java.text.DecimalFormat;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import tan.api.TANStat;
import tan.api.TemperatureRegistry;
import tan.api.TemperatureSource;

public class TemperatureStat extends TANStat
{ 
    private ArrayList<Float> averageAimedTemperatures = new ArrayList<Float>();
    private ArrayList<Float> averageRates = new ArrayList<Float>();
    
    @Override
    public void update()
    {
        int x = MathHelper.floor_double(player.posX);
        int y = MathHelper.floor_double(player.posY);
        int z = MathHelper.floor_double(player.posZ);
        
        float originalTemperature = tanData.getFloat(getStatName());
        float temperature = originalTemperature;

        float aimedTemperature = 0F;
        float rate = 0F;
        
        calculateSourceAndEnvironment(x, y, z);
        
        for (float averageAimedTemperature : averageAimedTemperatures)
        {
            aimedTemperature += averageAimedTemperature;
        }
        
        aimedTemperature /= averageAimedTemperatures.size();
        
        DecimalFormat twoDForm = new DecimalFormat("#.#");   

        try
        {
            aimedTemperature = Float.parseFloat(twoDForm.format(aimedTemperature));
        }
        catch (Exception e)
        {

        }
        
        for (float averageRate : averageRates)
        {
            rate += averageRate;
        }
        
        rate = (rate / averageRates.size()) / 10;

        if (world.rand.nextFloat() <= rate)
        {
            if (temperature > aimedTemperature)
            {
                temperature -= 0.1F;
            }
            else if (temperature < aimedTemperature)
            {
                temperature += 0.1F;
            }
        }
        
        averageAimedTemperatures.clear();
        averageRates.clear();

        if (temperature != originalTemperature)
        {
            tanData.setFloat(getStatName(), MathHelper.clamp_float(temperature, 27F, 47F));

            updatePlayerData(tanData, player);
        }
    }
    
    private void calculateSourceAndEnvironment(int x, int y, int z)
    {
        float averageAimedSourceTemperature = 0F;
        float averageSourceRate = 0F;
        
        int sourceDivider = 0;
        
        float averageAimedEnvironmentTemperature = 0F;
        float averageEnvironmentRate = 0F;
        
        int environmentDivider = 0;
        
        for (int ix = -2; ix <= 2; ix++)
        {
            for (int iy = -1; iy <= 1; iy++)
            {
                for (int iz = -2; iz <= 2; iz++)
                {
                    int blockID = world.getBlockId(x + ix, y + iy, z + iz);
                    int metadata = world.getBlockMetadata(x + ix, y + iy, z + iz);
                    
                    TemperatureSource temperatureSource = TemperatureRegistry.getTemperatureSource(blockID, metadata);
                    
                    if (temperatureSource != null)
                    {
                        averageAimedSourceTemperature += temperatureSource.temperature;
                        averageSourceRate += temperatureSource.rate;
                        
                        sourceDivider++;
                    }
                    else
                    {
                        averageAimedEnvironmentTemperature += ((world.getBiomeGenForCoords(x + ix, z + iz).temperature / 2) * 20) + 27;
                        averageEnvironmentRate += 0.25F;

                        environmentDivider++;
                    }
                }
            }
        }
        
        if (sourceDivider != 0) 
        {
            float aimedSourceTemperature = averageAimedSourceTemperature /= sourceDivider;
            averageAimedTemperatures.add(aimedSourceTemperature);
        }
        if (environmentDivider != 0) 
        {
           float aimedEnvironmentTemperature = averageAimedEnvironmentTemperature /= environmentDivider;
           averageAimedTemperatures.add(aimedEnvironmentTemperature);
        }
        
        if (sourceDivider != 0) averageRates.add(averageSourceRate /= sourceDivider);
        if (environmentDivider != 0) averageRates.add(averageEnvironmentRate /= environmentDivider);
    }

    @Override
    public void setDefaults()
    {
        setDefaultFloat(getStatName(), 37F);
    }

    @Override
    public String getStatName()
    {
        return "Temp";
    }
}
