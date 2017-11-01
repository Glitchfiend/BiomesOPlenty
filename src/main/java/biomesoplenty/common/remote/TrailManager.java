/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;

public class TrailManager 
{
    private static final String REMOTE_TRAILS_FILE = "https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/trails.txt";
    
    public static HashMap<UUID, String> trailsMap = new HashMap<UUID, String>();
    public static HashMap<String, ResourceLocation> trailTextures = new HashMap<String, ResourceLocation>();
    
    public static void retrieveTrails()
    {
        try
        {
            URL url = new URL(REMOTE_TRAILS_FILE);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
            connection.connect();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            
            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith("//") || line.isEmpty()) continue;
                
                String[] split = line.split(":");
                
                trailsMap.put(UUID.fromString(split[0]), split[1]);
                if (!trailTextures.containsKey(split[1])) trailTextures.put(split[1], null);
            }
            
            reader.close();
        } 
        catch (Exception e)
        {
            BiomesOPlenty.logger.warn("There was an issue retrieving trail info from remote!");
        } 
    }
    
    public static enum TrailVisibilityMode
    {
        ALL, OTHERS, NOBODY; //TODO: Implement NOBODY mode
    }
}
