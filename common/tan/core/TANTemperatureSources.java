package tan.core;

import net.minecraft.block.Block;
import tan.api.TemperatureRegistry;
import tan.api.TemperatureSource;

public class TANTemperatureSources
{
    public static void init()
    {
        registerTemperatureSources();
    }
    
    private static void registerTemperatureSources()
    {
        TemperatureRegistry.registerTemperatureSource(Block.fire.blockID, -1, new TemperatureSource(43F, 0.80F));
    }
}
