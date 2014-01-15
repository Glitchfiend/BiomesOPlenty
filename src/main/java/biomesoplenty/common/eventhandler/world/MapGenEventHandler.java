package biomesoplenty.common.eventhandler.world;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MapGenEventHandler 
{
	@SubscribeEvent
	public void onMapGenInit(InitMapGenEvent event)
	{
		if (event.type == EventType.VILLAGE)
		{
			event.newGen = new BOPMapGenVillage();
		}
	}
}
