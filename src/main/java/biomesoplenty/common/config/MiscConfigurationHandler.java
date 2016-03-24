/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.config;

import java.io.File;

import biomesoplenty.common.remote.TrailManager;
import biomesoplenty.common.remote.TrailManager.TrailVisibilityMode;
import biomesoplenty.common.util.entity.PlayerUtil;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class MiscConfigurationHandler
{
    public static Configuration config;

    public static String guiSettings = "GUI Settings";
    public static String textureSettings = "Texture Settings";
    public static String trailSettings = "Trail Settings";

    public static boolean useBoPWorldTypeDefault;
    public static boolean overrideTitlePanorama;
    public static boolean overrideForgeBuckets;

    //Client-side only
    public static TrailVisibilityMode trailVisbilityMode;

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
            //TODO: Make this default to true once all biomes have been implemented
            useBoPWorldTypeDefault = config.getBoolean("Default to BoP World Type", guiSettings, false, "Use the Biomes O' Plenty World Type by default when selecting a world.");
            overrideTitlePanorama = config.getBoolean("Enable Biomes O\' Plenty Main Menu Panorama", textureSettings, true, "Override the main menu panorama and use ours instead (It\'s nicer!)");
            overrideForgeBuckets = config.getBoolean("Enable Biomes O\' Plenty Bucket Textures", textureSettings, true, "Override the Forge bucket texture and use ours instead (It\'s nicer!)");

            //Client-side only options
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
            {
                //Check if the player has a trail
                if (TrailManager.trailsMap.containsKey(PlayerUtil.getClientPlayerUUID()))
                {
                    trailVisbilityMode = TrailVisibilityMode.values()[config.getInt("Modify Trail Visibility", trailSettings, 0, 0, 1, "0 = All trails visble, 1 = Others can see your trail but you can't")];
                }
            }
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

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equalsIgnoreCase(BiomesOPlenty.MOD_ID))
        {
            loadConfiguration();
        }
    }
}