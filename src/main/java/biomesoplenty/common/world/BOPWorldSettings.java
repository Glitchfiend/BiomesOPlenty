/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

public class BOPWorldSettings
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

    public LandMassScheme landScheme = LandMassScheme.VANILLA;
    public TemperatureVariationScheme tempScheme = TemperatureVariationScheme.MEDIUM_ZONES;
    public RainfallVariationScheme rainScheme = RainfallVariationScheme.MEDIUM_ZONES;
    public BiomeSize biomeSize = BiomeSize.MEDIUM;
    public float amplitude = 1.0F;
}
