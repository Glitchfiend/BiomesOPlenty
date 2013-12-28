package biomesoplenty;

import net.minecraft.creativetab.CreativeTabs;
import biomesoplenty.client.eventhandlers.BOPClientEventHandlers;
import biomesoplenty.common.configuration.BOPConfiguration;
import biomesoplenty.common.core.BOPArmor;
import biomesoplenty.common.core.BOPBiomes;
import biomesoplenty.common.core.BOPBlocks;
import biomesoplenty.common.core.BOPEntities;
import biomesoplenty.common.core.BOPItems;
import biomesoplenty.common.core.BOPPotions;
import biomesoplenty.common.eventhandler.BOPCommonEventHandlers;
import biomesoplenty.common.helpers.CreativeTabsBOP;
import biomesoplenty.common.lib.BOPModInfo;
import biomesoplenty.common.world.WorldTypeBOP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=BOPModInfo.modID, name=BOPModInfo.modName, dependencies="after:Natura; required-after:Forge@[1.42.666.42.1,)")
public class BiomesOPlenty
{
	@Instance("BiomesOPlenty")
	public static BiomesOPlenty instance;

	@SidedProxy(clientSide="biomesoplenty.ClientProxy", serverSide="biomesoplenty.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabBiomesOPlenty;
	public static String configPath;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		configPath = event.getModConfigurationDirectory() + "/biomesoplenty/";
		BOPConfiguration.init(configPath);
		
		//Version.check();

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(), "tabBiomesOPlenty");

		BOPPotions.init();
		BOPBlocks.init();
		BOPItems.init();
		BOPArmor.init();
		BOPBiomes.init();
		BOPEntities.init();
		
		if (proxy instanceof ClientProxy) BOPClientEventHandlers.init();
		BOPCommonEventHandlers.init();
		
		proxy.registerRenderers();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BOPBiomes.worldTypeBOP = new WorldTypeBOP();
	}
}