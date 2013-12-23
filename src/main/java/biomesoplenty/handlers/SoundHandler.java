package biomesoplenty.handlers;

import net.minecraftforge.client.event.sound.PlayStreamingEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SoundHandler 
{
	static String[] recordSoundFiles = { "biomesoplenty:bopdisc.ogg", "biomesoplenty:bopdiscmud.ogg" };
	static String[] soundFiles = { "biomesoplenty:mob/phantom/say.ogg", "biomesoplenty:mob/phantom/hurt.ogg", "biomesoplenty:mob/phantom/death.ogg", "biomesoplenty:mob/wasp/say.ogg", "biomesoplenty:mob/wasp/hurt.ogg", "biomesoplenty:mob/bird/say.ogg", "biomesoplenty:mob/bird/hurt.ogg", "biomesoplenty:mob/pixie/say.ogg", "biomesoplenty:mob/pixie/hurt.ogg" };
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onSoundLoad(SoundLoadEvent event) 
	{
		for (String soundFile : soundFiles) 
		{
			try 
			{
				event.manager.func_148611_c(p_148611_1_);.addSound(soundFile);
			}

			catch (Exception e) 
			{
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed loading sound file: " + soundFile);
			}
		}
		
		for (String recordSoundFile : recordSoundFiles) 
        {
            try 
            {
                event.manager.soundPoolStreaming.addSound(recordSoundFile);
            }

            catch (Exception e) 
            {
            	FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed loading sound file: " + recordSoundFile);
            }
        }
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayStreaming(PlayStreamingEvent event) 
	{
		if (event.name == "bopdisc")
		{
			FMLClientHandler.instance().getClient().sndManager.playStreaming("biomesoplenty:bopdisc", (float) event.x + 0.5F, (float) event.y + 0.5F, (float) event.z + 0.5F);
		}
		else if (event.name == "bopdiscmud")
		{
			FMLClientHandler.instance().getClient().sndManager.playStreaming("biomesoplenty:bopdiscmud", (float) event.x + 0.5F, (float) event.y + 0.5F, (float) event.z + 0.5F);
		}
	}
}
