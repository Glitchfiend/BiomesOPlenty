/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
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
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class MiscConfigurationHandler
{
    public static Configuration config;
    
    public static boolean useBoPWorldTypeDefault;
    public static boolean overrideTitlePanorama;
    
    //Client-side only
    public static TrailVisibilityMode trailVisbilityMode;

    public static void init(File configFile)
    {
        config = new Configuration(configFile);

        try
        {
            config.load();
            
            //TODO: Make this default to true once all biomes have been implemented
            useBoPWorldTypeDefault = config.getBoolean("Default to BoP World Type", "GUI Settings", false, "Use the Biomes O' Plenty World Type by default when selecting a world.");
            overrideTitlePanorama = config.getBoolean("Enable Biomes O\' Plenty Main Menu Panorama", "GUI Settings", true, "Override the main menu panorama and use ours instead (It\'s nicer!)");
            
            //Client-side only options
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
            {
                //Check if the player has a trail
                if (TrailManager.trailsMap.containsKey(PlayerUtil.getClientPlayerUUID()))
                {
                    trailVisbilityMode = TrailVisibilityMode.values()[config.getInt("Modify Trail Visibility", "Trail Settings", 0, 0, 1, "0 = All trails visble, 1 = Others can see your trail but you can't")];
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
}
