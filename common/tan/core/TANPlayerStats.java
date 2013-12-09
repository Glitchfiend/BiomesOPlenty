package tan.core;

import tan.api.PlayerStatRegistry;
import tan.stats.TemperatureStat;

public class TANPlayerStats
{
    public static void init()
    {
        registerStats();
    }
    
    private static void registerStats()
    {
        PlayerStatRegistry.registerStat(new TemperatureStat());
    }
}
