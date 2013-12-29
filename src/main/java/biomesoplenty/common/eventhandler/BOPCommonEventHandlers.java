package biomesoplenty.common.eventhandler;

import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.common.eventhandler.entity.DyeEventHandler;
import biomesoplenty.common.eventhandler.entity.FlippersEventHandler;
import biomesoplenty.common.eventhandler.entity.TemptEventHandler;
import biomesoplenty.common.eventhandler.misc.CapeEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionParalysisEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionPossessionEventHandler;
import biomesoplenty.common.eventhandler.world.DecorateBiomeEventHandler;

public class BOPCommonEventHandlers 
{
	public static void init()
	{
		registerWorldEventHandlers();
		registerEntityEventHandlers();
		registerPotionEventHandlers();
		registerMiscEventHandlers();
	}
	
	private static void registerWorldEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new DecorateBiomeEventHandler());
	}
	
	private static void registerEntityEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
		MinecraftForge.EVENT_BUS.register(new FlippersEventHandler());
		MinecraftForge.EVENT_BUS.register(new TemptEventHandler());
	}
	
	private static void registerPotionEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new PotionParalysisEventHandler());
		MinecraftForge.EVENT_BUS.register(new PotionPossessionEventHandler());
	}
	
	private static void registerMiscEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CapeEventHandler());
	}
}
