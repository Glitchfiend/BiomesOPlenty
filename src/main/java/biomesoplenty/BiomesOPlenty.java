package biomesoplenty;

import net.minecraft.creativetab.CreativeTabs;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.core.BOPBlocks;
import biomesoplenty.core.BOPEntities;
import biomesoplenty.core.BOPItems;
import biomesoplenty.helpers.CreativeTabsBOP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", dependencies="after:Natura; required-after:Forge@[1.42.666.42.1,)")
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

		BOPBlocks.init();
		BOPItems.init();
		BOPEntities.init();
		
		proxy.registerEventHandlers();
		proxy.registerRenderers();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}