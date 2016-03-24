/*******************************************************************************
 * Copyright 2016-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.config;

import java.io.File;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GameplayConfigurationHandler
{
    public static Configuration config;

    public static String convenienceSettings = "Convenience Settings";

    public static boolean flowerDropsNeedShears;

    public static void init(File configFile)
    {
        if (config == null)
        {
            config = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        try
        {
            flowerDropsNeedShears = config.getBoolean("Flower Drops Need Shears", convenienceSettings, false, "Require shears to be used to collect flower drops.");
        }
        catch (Exception e)
        {
            BiomesOPlenty.logger.error("Biomes O' Plenty has encountered a problem loading gameplay.cfg", e);
        }
        finally
        {
            if (config.hasChanged()) config.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equalsIgnoreCase(BiomesOPlenty.MOD_ID))
        {
            loadConfiguration();
        }
    }
}