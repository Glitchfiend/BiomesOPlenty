package tan.api.temperature;

import java.util.ArrayList;
import java.util.HashMap;

public class TemperatureRegistry
{
    public static HashMap<String, Float> temperatureSources = new HashMap<String, Float>();
    public static ArrayList<ITemperatureModifier> temperatureModifiers = new ArrayList<ITemperatureModifier>();
    
    public static float getTemperatureSourceModifier(int id, int metadata)
    {
        float modifier = 0;
        
        try
        {
            modifier = temperatureSources.get(id + ";" + metadata);
        }
        catch (Exception e)
        {
            
        }
        return modifier;
    }
    
    public static float getTemperatureSourceModifier(int id)
    {
        return getTemperatureSourceModifier(id, 0);
    }
    
    public static void registerTemperatureModifier(ITemperatureModifier temperatureModifier)
    {
        temperatureModifiers.add(temperatureModifier);
    }

    public static void registerTemperatureSource(int id, int metadata, float modifier)
    { 
        if (metadata == -1)
        {
            for (int i = 0; i < 16; i++)
            {
                temperatureSources.put(id + ";" + i, modifier);
            }
        }
        else
        {   
            temperatureSources.put(id + ";" + metadata, modifier);
        }
    }
    
    public static void registerTemperatureSource(int id,  float modifier)
    {
        registerTemperatureSource(id, 0, modifier);
    }
}
