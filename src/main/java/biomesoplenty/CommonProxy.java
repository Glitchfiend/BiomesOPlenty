package biomesoplenty;

import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.common.eventhandler.entity.DyeEventHandler;
import biomesoplenty.common.eventhandler.entity.FlippersEventHandler;
import biomesoplenty.common.eventhandler.entity.TemptEventHandler;
import biomesoplenty.common.eventhandler.gui.MainMenuEventHandler;
import biomesoplenty.common.eventhandler.gui.StartupWarningEventHandler;
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


public class CommonProxy 
{
	//Client Only
	public void registerRenderers() 
	{
	}
	
	public void registerEventHandlers()
	{
	    FMLCommonHandler.instance().bus().register(new ConnectionEventHandler());

		MinecraftForge.TERRAIN_GEN_BUS.register(new DecorationModificationEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new BiomeSizeEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new MapGenEventHandler());
		MinecraftForge.EVENT_BUS.register(new DyeEventHandler());
		MinecraftForge.EVENT_BUS.register(new FlippersEventHandler());
		MinecraftForge.EVENT_BUS.register(new TemptEventHandler());
		MinecraftForge.EVENT_BUS.register(new PotionParalysisEventHandler());
		MinecraftForge.EVENT_BUS.register(new PotionPossessionEventHandler());
	    MinecraftForge.EVENT_BUS.register(new MainMenuEventHandler());
		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		MinecraftForge.EVENT_BUS.register(new BucketEventHandler());
	}
	
	public void spawnParticle(String string, double x, double y, double z)
	{
	}
	
	public int addArmor(String armor)
	{
		return 0;
	}
}