package biomesoplenty.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.Property;
import biomesoplenty.configuration.configfile.BOPConfigurationMain;
import biomesoplenty.world.WorldTypeBOP;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;

public class Version implements Runnable {
	private static Version instance = new Version();

	public enum EnumUpdateState 
	{
		CURRENT, OUTDATED, CONNECTION_ERROR
	}

	public static final String VERSION = "@VERSION@";
	private static final String REMOTE_VERSION_FILE = "https://raw.github.com/BiomesOPlenty/BiomesOPlenty/master/version.txt";
	private static final String REMOTE_CHANGELOG_ROOT = "https://raw.github.com/BiomesOPlenty/BiomesOPlenty/master/changelog/";

	public static EnumUpdateState currentVersion = EnumUpdateState.CURRENT;

	private static String recommendedVersion;
	private static String[] cachedChangelog;

	public static String getVersion() 
	{
		return VERSION;
	}

	public static boolean isOutdated() 
	{
		return currentVersion == EnumUpdateState.OUTDATED;
	}

	public static boolean needsUpdateNoticeAndMarkAsSeen() 
	{
		if (!isOutdated())
			return false;

		//Property property = BOPConfiguration.Main.config.get("Vars", "Seen Version", VERSION);
		//String seenVersion = property.getString();

		//if (recommendedVersion == null || recommendedVersion.equals(seenVersion))
			//return false;

		/*property.set(recommendedVersion);
		
		BOPConfiguration.Main.config.save();*/
		
		return true;
	}
	
	public static boolean needsBOPWorldtypeAndMarkAsSeen(World world) 
	{
		WorldType terrainType = world.getWorldInfo().getTerrainType();
		WorldTypeBOP bopType = new WorldTypeBOP();
		
		Property property = BOPConfigurationMain.config.get("Vars", "Seen WorldType Msg", false);
		String worldTypeProp = property.getString();
		
		if (terrainType == null || property.getBoolean(false) == true)
			return false;
		
		if (terrainType.getWorldTypeName().equals(bopType.getWorldTypeName()) || terrainType.getWorldTypeName().equals("ATG"))
		{
			property.set(true);
			
			BOPConfigurationMain.config.save();
			return false;
		}
		
		property.set(true);
		
		BOPConfigurationMain.config.save();
		return true;
	}

	public static String getRecommendedVersion() 
	{
		return recommendedVersion;
	}

	public static void versionCheck() 
	{
		try {

			if ("0.0.0".equals(VERSION))
				return;

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

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String line = null;
			String mcVersion = Loader.instance().getMinecraftModContainer().getVersion();
			
			while ((line = reader.readLine()) != null) 
			{
				if (line.startsWith(mcVersion)) {
					if (line.contains("BiomesOPlenty")) {
						String[] tokens = line.split(":");
						recommendedVersion = tokens[2];

						if (line.endsWith(VERSION)) 
						{
							FMLCommonHandler.instance().getFMLLogger().log(Level.FINER, "[BiomesOPlenty] Using the latest version ["+ getVersion() + "] for Minecraft " + mcVersion);
							
							currentVersion = EnumUpdateState.CURRENT;
							return;
						}
					}
				}
			}

			FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING,"[BiomesOPlenty] Using outdated version [" + VERSION + "] for Minecraft " + mcVersion + ". Consider updating.");
			
			currentVersion = EnumUpdateState.OUTDATED;

		} catch (Exception e) 
		{
			FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Unable to read from remote version authority.");
			FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, e.toString());

			currentVersion = EnumUpdateState.CONNECTION_ERROR;
		}
	}

	@Override
	public void run() 
	{
		int count = 0;
		currentVersion = null;

		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Beginning version check");

		try {
			while ((count < 3)
					&& ((currentVersion == null) || (currentVersion == EnumUpdateState.CONNECTION_ERROR))) {
				versionCheck();
				count++;

				if (currentVersion == EnumUpdateState.CONNECTION_ERROR) {
					FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Version check attempt " + count + " failed, trying again in 10 seconds");
					Thread.sleep(10000);
				}
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (currentVersion == EnumUpdateState.CONNECTION_ERROR) {
			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Version check failed");
		}

	}

	public static void check() 
	{
		new Thread(instance).start();
	}
}
