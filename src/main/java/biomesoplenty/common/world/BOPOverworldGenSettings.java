/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.init.ModConfig;

public class BOPOverworldGenSettings
{
    public enum LandMassScheme
    {
        VANILLA,
        CONTINENTS,
        ARCHIPELAGO
    }

    public enum TemperatureVariationScheme
    {
        LATITUDE,
        SMALL_ZONES,
        MEDIUM_ZONES,
        LARGE_ZONES,
        RANDOM
    }

    public enum RainfallVariationScheme
    {
        SMALL_ZONES,
        MEDIUM_ZONES,
        LARGE_ZONES,
        RANDOM
    }

    public enum BiomeSize
    {
        TINY (2),
        SMALL (3),
        MEDIUM (4),
        LARGE (5),
        HUGE (6);

        private final int value;

        BiomeSize(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return this.value;
        }
    }

    public enum RiverSize
    {
        TINY (2),
        SMALL (3),
        MEDIUM (4),
        LARGE (5),
        HUGE (6);

        private final int value;

        RiverSize(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return this.value;
        }
    }

    public int getBiomeSize()
    {
        return ModConfig.GenerationConfig.biomeSize.get().value;
    }
    public int getRiverSize()
    {
        return ModConfig.GenerationConfig.riverSize.get().value;
    }

    public TemperatureVariationScheme getTempScheme()
    {
        return ModConfig.GenerationConfig.temperatureVariationScheme.get();
    }

    public RainfallVariationScheme getRainScheme()
    {
        return ModConfig.GenerationConfig.rainfallVariationScheme.get();
    }
}
