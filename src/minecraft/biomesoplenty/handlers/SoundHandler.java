package biomesoplenty.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.ClientProxy;
import cpw.mods.fml.common.FMLCommonHandler;

public class SoundHandler 
{
	static String[] soundFiles = { "bopdisc.ogg", "bopdiscmud.ogg"};
	
	public void installRecordTracks()
	{
		boolean isClient = BiomesOPlenty.proxy instanceof ClientProxy;

		if (isClient)
		{
			for (String soundFile : soundFiles) 
			{
				try
				{
					File file = new File(Minecraft.getMinecraftDir().getAbsolutePath() + "/resources/mod/streaming/" + soundFile);
					if (!file.exists()) {
						System.out.println("[BiomesOPlenty] " + soundFile + " doesn't exist, creating...");
						file.getParentFile().mkdirs();
						file.createNewFile();
						InputStream istream = getClass().getResourceAsStream("/mods/BiomesOPlenty/audio/" + soundFile);
						OutputStream out = new FileOutputStream(file);
						byte[] buf = new byte[1024];
						int size = 0;
						int len;
						while ((len = istream.read(buf)) > 0) {
							out.write(buf, 0, len);
							size += len;
						}
						out.close();
						istream.close();
						if (size == 0) {
							file.delete();
						}
					}
				}
				catch (Exception e)
				{
					FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed to load sound file: " + soundFile);
					e.printStackTrace();
				}
			}
		}
	}
}
