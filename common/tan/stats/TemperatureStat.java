package tan.stats;

import java.text.DecimalFormat;

import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import tan.api.TANStat;
import tan.api.temperature.ITemperatureModifier;
import tan.api.temperature.TemperatureRegistry;

public class TemperatureStat extends TANStat
{ 
    @Override
    public void update()
    {
        int x = MathHelper.floor_double(player.posX);
        int y = MathHelper.floor_double(player.posY);
        int z = MathHelper.floor_double(player.posZ);
        
        float originalTemperature = tanData.getFloat(getStatName());
        float temperature = originalTemperature;

        float[] temperatureRainfall = getEnvironmentTemperatureRainfall(x, y, z);
        
        float aimedTemperature = temperatureRainfall[0];
        
        for (ITemperatureModifier temperatureModifier : TemperatureRegistry.temperatureModifiers)
        {
            float modifier = temperatureModifier.modifyTemperature(world, player);
            
            aimedTemperature += modifier;
        }
        
        DecimalFormat twoDForm = new DecimalFormat("#.##");   

        try
        {
            aimedTemperature = Float.parseFloat(twoDForm.format(aimedTemperature));
        }
        catch (Exception e)
        {

        }
        
        if (world.rand.nextFloat() <= temperatureRainfall[1])
        {
            if (temperature > aimedTemperature)
            {
                temperature -= 0.01F;
            }
            else if (temperature < aimedTemperature)
            {
                temperature += 0.01F;
            }
        }
        
        try
        {
            temperature = Float.parseFloat(twoDForm.format(temperature));
        }
        catch (Exception e)
        {

        }
        
        System.out.println("Aimed Temp " + aimedTemperature);
        System.out.println("Rain/Speed " + temperatureRainfall[1]);
        System.out.println("Current Temp " + temperature);

        if (temperature != originalTemperature)
        {
            tanData.setFloat(getStatName(), MathHelper.clamp_float(temperature, 27F, 47F));

            updatePlayerData(tanData, player);
        }
    }
    
    private float[] getEnvironmentTemperatureRainfall(int x, int y, int z)
    {
        float[] temperatureRainfall = new float[2];
        
        float averageAimedEnvironmentTemperature = 0F;
        float rainfall = 0.25F;

        int environmentDivider = 0;

        for (int ix = -2; ix <= 2; ix++)
        {
            for (int iy = -1; iy <= 1; iy++)
            {
                for (int iz = -2; iz <= 2; iz++)
                {
                    int blockID = world.getBlockId(x + ix, y + iy, z + iz);
                    int metadata = world.getBlockMetadata(x + ix, y + iy, z + iz);
                    
                    BiomeGenBase biome = world.getBiomeGenForCoords(x + ix, z + iz);

                    averageAimedEnvironmentTemperature += ((biome.temperature / 2) * 20) + 27;
                    
                    if (biome.rainfall != 0F)
                    {
                        rainfall = biome.rainfall / 2;
                    }

                    environmentDivider++;
                }
            }
        }
        
        temperatureRainfall[0] = averageAimedEnvironmentTemperature / environmentDivider;
        temperatureRainfall[1] = rainfall;

        return temperatureRainfall;
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
