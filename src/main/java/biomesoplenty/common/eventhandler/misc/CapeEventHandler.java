package biomesoplenty.common.eventhandler.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;

import org.apache.logging.log4j.Level;

import biomesoplenty.common.utils.BOPLogger;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CapeEventHandler
{
    private final String serverLocation = "https://raw.githubusercontent.com/Glitchfiend/BiomesOPlenty/master/capes.txt";
    private final int timeout = 1000;

    private HashMap<String, String> cloaks = new HashMap<String, String>();
    private ArrayList<AbstractClientPlayer> checkedPlayers = new ArrayList<AbstractClientPlayer>();

    public static CapeEventHandler instance;

    public CapeEventHandler()
    {
        buildCloakURLDatabase();
        instance = this;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPreRenderSpecials(RenderPlayerEvent.Specials.Pre event)
    {
        if (Loader.isModLoaded("shadersmod"))
        {
            return;
        }
        if (event.entityPlayer instanceof AbstractClientPlayer)
        {
            AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer) event.entityPlayer;
            
            if (!checkedPlayers.contains(abstractClientPlayer))
            {
            	checkedPlayers.add(abstractClientPlayer);
            	
            	try
            	{
            		Class SkinManager = Class.forName("net.minecraft.client.resources.SkinManager");
            		Class SkinAvailableCallback = Class.forName("net.minecraft.client.resources.SkinManager$SkinAvailableCallback");
            		Class UUIDTypeAdapter = Class.forName("com.mojang.util.UUIDTypeAdapter");

            		//						 						   															getSkinManager()?
            		Object skinManager = ReflectionHelper.findMethod(Minecraft.class, Minecraft.getMinecraft(), new String[] { "func_152342_ad" }).invoke(Minecraft.getMinecraft());
            		String uuid = (String)ReflectionHelper.findMethod(UUIDTypeAdapter, null, new String[] { "fromUUID" }, UUID.class).invoke(null, abstractClientPlayer.getUniqueID());

            		if (cloaks.containsKey(uuid))
            		{
            			Class MinecraftProfileTexture = Class.forName("com.mojang.authlib.minecraft.MinecraftProfileTexture");
            			Class Type = Class.forName("com.mojang.authlib.minecraft.MinecraftProfileTexture$Type");

            			Object profileTexture = MinecraftProfileTexture.getConstructor(String.class).newInstance(cloaks.get(uuid));

            			ReflectionHelper.findMethod(SkinManager, skinManager, new String[] { "func_152789_a" }, MinecraftProfileTexture, Type, SkinAvailableCallback).invoke(skinManager, profileTexture, Type.getField("CAPE").get(null), abstractClientPlayer);

            			event.renderCape = true;
            		}
            	}
            	catch (Exception e)
            	{
            		e.printStackTrace();
            	}
            }
        }
    }

    public void buildCloakURLDatabase()
    {
        URL url;
        try
        {
            url = new URL(serverLocation);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(timeout);
            con.setReadTimeout(timeout);
            InputStream io = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(io));

            String str;
            int linetracker = 1;
            while ((str = br.readLine()) != null)
            {
                if ((!str.startsWith("--") || str.contains("--*--")) && !str.isEmpty())
                {
                	str = str.replace("--*--", "");

                	if (str.contains(":"))
                	{
                		String uuid = str.substring(0, str.indexOf(":"));
                		String link = str.substring(str.indexOf(":") + 1);
                		cloaks.put(uuid, link);
                	}
                	else
                	{
                		BOPLogger.log(Level.WARN, "[capes.txt] Syntax error on line " + linetracker + ": " + str);
                	}
                }
                linetracker++;
            }

            br.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}