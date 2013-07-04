package biomesoplenty.handlers;

import java.util.logging.Level;

import net.minecraft.client.resources.ResourceLocation;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler 
{
	static ResourceLocation[] recordSoundFiles = { new ResourceLocation("bopdisc.ogg"), new ResourceLocation("bopdiscmud.ogg") };
	static String[] soundFiles = { };
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) 
	{
		for (String soundFile : soundFiles) 
		{
			try 
			{
				event.manager.soundPoolSounds.addSound(soundFile);
			}

			catch (Exception e) 
			{
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed loading sound file: " + soundFile);
			}
		}
		
		for (ResourceLocation recordSoundFile : recordSoundFiles) 
        {
            try 
            {
                event.manager.soundPoolStreaming.addSound(recordSoundFile.func_110623_a());
            }

            catch (Exception e) 
            {
            	FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed loading sound file: " + recordSoundFile);
            }
        }
	}
}
