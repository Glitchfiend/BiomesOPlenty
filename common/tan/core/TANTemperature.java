package tan.core;

import net.minecraft.block.Block;
import tan.api.temperature.TemperatureRegistry;
import tan.temperaturemodifiers.TemperatureSourceModifier;

public class TANTemperature
{
    public static void init()
    {
        registerTemperatureModifiers();
        registerTemperatureSources();
    }
    
    private static void registerTemperatureModifiers()
    {
        TemperatureRegistry.registerTemperatureModifier(new TemperatureSourceModifier());
    }
    
    private static void registerTemperatureSources()
    {
        TemperatureRegistry.registerTemperatureSource(Block.fire.blockID, -1, 4F);
    }
}
