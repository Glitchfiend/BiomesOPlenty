/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import java.io.File;

import biomesoplenty.common.config.BiomeConfigurationHandler;
import biomesoplenty.common.config.MiscConfigurationHandler;

public class ModConfiguration
{
    public static void init(File configDirectory)
    {
    	MiscConfigurationHandler.init(new File(configDirectory, "misc.cfg"));
    }
    
    public static void initEnd(File configDirectory)
    {
        BiomeConfigurationHandler.init(new File(configDirectory, "biomes"));
    }
}
