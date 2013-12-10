package tan.core;

import net.minecraft.block.Block;
import tan.api.temperature.TemperatureRegistry;
import tan.temperaturemodifiers.TemperaturePlayerStateModifier;
import tan.temperaturemodifiers.TemperatureSourceModifier;
import tan.temperaturemodifiers.TemperatureTimeModifier;

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
        TemperatureRegistry.registerTemperatureModifier(new TemperatureTimeModifier());
        TemperatureRegistry.registerTemperatureModifier(new TemperaturePlayerStateModifier());
    }
    
    private static void registerTemperatureSources()
    {
        TemperatureRegistry.registerTemperatureSource(Block.fire.blockID, -1, 4F);
    }
}
