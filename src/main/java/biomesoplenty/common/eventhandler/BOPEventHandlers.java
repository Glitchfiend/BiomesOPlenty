package biomesoplenty.common.eventhandler;

import biomesoplenty.client.fog.FogHandler;
import biomesoplenty.common.eventhandler.client.FlowerScatterEventHandler;
import biomesoplenty.common.eventhandler.client.gui.MainMenuEventHandler;
import biomesoplenty.common.eventhandler.client.gui.WorldTypeMessageEventHandler;
import biomesoplenty.common.eventhandler.entity.DyeEventHandler;
import biomesoplenty.common.eventhandler.entity.FlippersEventHandler;
import biomesoplenty.common.eventhandler.entity.SlimeSpawnEventHandler;
import biomesoplenty.common.eventhandler.entity.TemptEventHandler;
import biomesoplenty.common.eventhandler.loading.LabelHandler;
import biomesoplenty.common.eventhandler.misc.BonemealEventHandler;
import biomesoplenty.common.eventhandler.misc.BucketEventHandler;
import biomesoplenty.common.eventhandler.misc.AchievementEventHandler;
import biomesoplenty.common.eventhandler.misc.FurnaceBurnTimeEventHandler;
import biomesoplenty.common.eventhandler.misc.OreDictionaryEventHandler;
import biomesoplenty.common.eventhandler.misc.UseHoeEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionParalysisEventHandler;
import biomesoplenty.common.eventhandler.potions.PotionPossessionEventHandler;
import biomesoplenty.common.eventhandler.world.BiomeSizeEventHandler;
import biomesoplenty.common.eventhandler.world.DecorationModificationEventHandler;
import biomesoplenty.common.eventhandler.world.MapGenEventHandler;
import biomesoplenty.common.eventhandler.world.VillageMaterialEventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

public class BOPEventHandlers 
{
	public static void init()
	{
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
	
	private static void registerWorldEventHandlers()
	{
		MinecraftForge.TERRAIN_GEN_BUS.register(new DecorationModificationEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new BiomeSizeEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new MapGenEventHandler());
		MinecraftForge.EVENT_BUS.register(new LabelHandler());
	}
	
	private static void registerEntityEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new SlimeSpawnEventHandler());
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
	    MinecraftForge.EVENT_BUS.register(WorldTypeMessageEventHandler.instance);
	    MinecraftForge.EVENT_BUS.register(new MainMenuEventHandler());
	}
	
	private static void registerMiscEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		MinecraftForge.EVENT_BUS.register(new BucketEventHandler());
		MinecraftForge.EVENT_BUS.register(new OreDictionaryEventHandler());
		MinecraftForge.EVENT_BUS.register(new FurnaceBurnTimeEventHandler());
		MinecraftForge.EVENT_BUS.register(new UseHoeEventHandler());
		FMLCommonHandler.instance().bus().register(new AchievementEventHandler());
	}
	
	private static void registerClientEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new FogHandler());
		FMLCommonHandler.instance().bus().register(new FlowerScatterEventHandler());
	}
}