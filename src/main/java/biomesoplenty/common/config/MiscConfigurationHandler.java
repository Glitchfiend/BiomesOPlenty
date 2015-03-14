/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.config;

import java.io.File;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.common.config.Configuration;

public class MiscConfigurationHandler
{
    public static Configuration config;

    public static boolean useBoPWorldTypeDefault;

    public static void init(File configFile)
    {
        config = new Configuration(configFile);

        try
        {
            config.load();

            //TODO: Make this default to true once all biomes have been implemented
            useBoPWorldTypeDefault = config.getBoolean("Default to BoP World Type", "GUI Settings", false, "Use the Biomes O' Plenty World Type by default when selecting a world.");
        }
        catch (Exception e)
        {
            BiomesOPlenty.logger.error("Biomes O' Plenty has encountered a problem loading misc.cfg", e);
        }
        finally
        {
            if (config.hasChanged()) config.save();
        }
    }
}
