package biomesoplenty.common.eventhandler;

import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.client.utils.ParticleRegistry;
import biomesoplenty.common.eventhandler.client.FlowerScatterEventHandler;
import biomesoplenty.common.eventhandler.client.gui.MainMenuEventHandler;
import biomesoplenty.common.eventhandler.client.gui.StartupWarningEventHandler;
import biomesoplenty.common.eventhandler.entity.DyeEventHandler;
import biomesoplenty.common.eventhandler.entity.FlippersEventHandler;
import biomesoplenty.common.eventhandler.entity.TemptEventHandler;
import biomesoplenty.common.eventhandler.misc.BonemealEventHandler;
import biomesoplenty.common.eventhandler.misc.BucketEventHandler;
import biomesoplenty.common.eventhandler.network.ConnectionEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionParalysisEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionPossessionEventHandler;
import biomesoplenty.common.eventhandler.world.BiomeSizeEventHandler;
import biomesoplenty.common.eventhandler.world.DecorationModificationEventHandler;
import biomesoplenty.common.eventhandler.world.MapGenEventHandler;
import biomesoplenty.common.eventhandler.world.VillageMaterialEventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class BOPEventHandlers 
{
	public static void init()
	{
	    registerNetworkEventHandlers();
		registerWorldEventHandlers();
		registerEntityEventHandlers();
		registerPotionEventHandlers();
		registerMiscEventHandlers();

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			registerGUIEventHandlers();
			registerClientEventHandlers();
		}
	}
	
	private static void registerNetworkEventHandlers()
	{
	    FMLCommonHandler.instance().bus().register(new ConnectionEventHandler());
	}
	
	private static void registerWorldEventHandlers()
	{
		MinecraftForge.TERRAIN_GEN_BUS.register(new DecorationModificationEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new BiomeSizeEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new MapGenEventHandler());
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
	
	private static void registerGUIEventHandlers()
	{
	    MinecraftForge.EVENT_BUS.register(StartupWarningEventHandler.instance);
	    MinecraftForge.EVENT_BUS.register(new MainMenuEventHandler());
	}
	
	private static void registerMiscEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		MinecraftForge.EVENT_BUS.register(new BucketEventHandler());
	}
	
	private static void registerClientEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new ParticleRegistry());
		FMLCommonHandler.instance().bus().register(new FlowerScatterEventHandler());
	}
}