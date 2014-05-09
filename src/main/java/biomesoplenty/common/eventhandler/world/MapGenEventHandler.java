package biomesoplenty.common.eventhandler.world;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MapGenEventHandler 
{
	@SubscribeEvent
	public void onInitBiomeGens(InitMapGenEvent event)
	{
		if (event.type == EventType.VILLAGE)
		{
			event.newGen = new BOPMapGenVillageEventHandler();
		}
	}
}
