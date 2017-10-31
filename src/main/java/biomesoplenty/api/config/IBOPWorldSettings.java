/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.config;

public interface IBOPWorldSettings
{
    boolean isEnabled(GeneratorType type);
    
    enum GeneratorType
    {
        GEMS, SOILS, TREES, GRASSES, FOLIAGE, FLOWERS, PLANTS, WATER_PLANTS, MUSHROOMS, 
        ROCK_FORMATIONS, POISON_IVY, BERRY_BUSHES, THORNS, QUICKSAND, LIQUID_POISON, HOT_SPRINGS,
        NETHER_HIVES, END_FEATURES
    }
}
