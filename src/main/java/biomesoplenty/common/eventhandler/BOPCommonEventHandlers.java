package biomesoplenty.common.eventhandler;

import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.common.eventhandler.potions.PotionParalysisEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionPossessionEventHandler;

public class BOPCommonEventHandlers 
{
	public static void init()
	{
		registerPotionEventHandlers();
	}
	
	private static void registerPotionEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new PotionParalysisEventHandler());
		MinecraftForge.EVENT_BUS.register(new PotionPossessionEventHandler());
	}
}
