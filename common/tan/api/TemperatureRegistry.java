package tan.api;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class TemperatureRegistry
{
    public static HashMap<String, TemperatureSource> temperatureSources = new HashMap<String, TemperatureSource>();
    
    public static TemperatureSource getTemperatureSource(int id, int metadata)
    {
        return temperatureSources.get(id + ";" + metadata);
    }
    
    public static TemperatureSource getTemperatureSource(int id)
    {
        return getTemperatureSource(id, 0);
    }

    public static void registerTemperatureSource(int id, int metadata, TemperatureSource temperatureSource)
    { 
        if (metadata == -1)
        {
            for (int i = 0; i < 16; i++)
            {
                temperatureSources.put(id + ";" + i, temperatureSource);
            }
        }
        else
        {   
            temperatureSources.put(id + ";" + metadata, temperatureSource);
        }
    }
    
    public static void registerTemperatureSource(int id, TemperatureSource temperatureSource)
    {
        registerTemperatureSource(id, 0, temperatureSource);
    }
}
