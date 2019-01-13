/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.world.WorldTypeBOP;

public class ModBiomes
{
    public static WorldTypeBOP worldType;

    public static void init()
    {
        worldType = new WorldTypeBOP();
    }
}
