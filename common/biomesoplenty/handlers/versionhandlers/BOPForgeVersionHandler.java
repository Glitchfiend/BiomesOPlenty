package biomesoplenty.handlers.versionhandlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.Property;
import biomesoplenty.configuration.configfile.BOPConfigurationMain;
import biomesoplenty.world.WorldTypeBOP;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class BOPForgeVersionHandler implements Runnable 
{
	private static BOPForgeVersionHandler instance = new BOPForgeVersionHandler();

	private static final String REMOTE_VERSION_FILE = "https://raw.github.com/Glitchfiend/BiomesOPlenty/master/forgeversion.txt";

	private static boolean hasFailed = false;

	public static String recommendedVersion = "1.42.666.42.1";

	public static void versionCheck() 
	{
		try 
		{
			String location = REMOTE_VERSION_FILE;
			HttpURLConnection conn = null;
			while (location != null && !location.isEmpty()) {
				URL url = new URL(location);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
				conn.connect();
				location = conn.getHeaderField("Location");
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			String mcVersion = Loader.instance().getMinecraftModContainer().getVersion();
			
			while ((line = reader.readLine()) != null) 
			{
				if (line.startsWith(mcVersion)) {
					if (line.contains("BiomesOPlenty")) 
					{
						String[] tokens = line.split(":");
					      
						recommendedVersion = tokens[2];
					}
				}
			}

		} 
		catch (Exception e) 
		{
			FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Unable to read from remote version authority.");
			FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, e.toString());

			hasFailed = true;
		}
	}

	@Override
	public void run() 
	{
		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Beginning Forge version check");

		versionCheck();

		if (hasFailed) 
		{
			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Version check failed");
		}
	}

	public static void check() 
	{
		new Thread(instance).start();
	}
}
