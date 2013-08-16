package biomesoplenty;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.configuration.BOPBiomes;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.configuration.BOPCrafting;
import biomesoplenty.configuration.BOPEntities;
import biomesoplenty.configuration.BOPFluids;
import biomesoplenty.configuration.BOPItems;
import biomesoplenty.configuration.BOPPotions;
import biomesoplenty.configuration.BOPVanillaCompat;
import biomesoplenty.handlers.BOPCraftHandler;
import biomesoplenty.handlers.BonemealHandler;
import biomesoplenty.handlers.BreakSpeedHandler;
import biomesoplenty.handlers.EntityEventHandler;
import biomesoplenty.handlers.FluidEventHandler;
import biomesoplenty.handlers.MovementHandler;
import biomesoplenty.handlers.SoundHandler;
import biomesoplenty.handlers.TickHandlerClient;
import biomesoplenty.handlers.TickHandlerServer;
import biomesoplenty.helpers.AchievementHelper;
import biomesoplenty.helpers.CreativeTabsBOP;
import biomesoplenty.helpers.Localizations;
import biomesoplenty.helpers.Version;
import biomesoplenty.integration.BOPCrossIntegration;
import biomesoplenty.world.WorldProviderPromised;
import biomesoplenty.world.WorldTypeSize;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", version=Version.VERSION, dependencies="after:Natura")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
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
		
		Version.check();

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");

		BOPPotions.init();
		BOPBlocks.init();
		BOPItems.init();
		BOPFluids.init();
		BOPCrafting.init();
		BOPBiomes.init();
		BOPEntities.init();
		BOPVanillaCompat.init();
		
		if (proxy instanceof ClientProxy)
		{
			Localizations.loadLanguages();
		}

		// Achievement declaration
		if (BOPConfiguration.Misc.achievements)
		{
			AchievementHelper.init();
		}

		GameRegistry.registerCraftingHandler(new BOPCraftHandler());
		
		if (proxy instanceof ClientProxy)
		{
			MinecraftForge.EVENT_BUS.register(new SoundHandler());
		}
		
		BOPCrossIntegration.preInit();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		// Add helpers for compatibility
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldTypeSize());
		MinecraftForge.EVENT_BUS.register(new AchievementHelper());
		MinecraftForge.EVENT_BUS.register(new BonemealHandler());
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new FluidEventHandler());
		MinecraftForge.EVENT_BUS.register(new BreakSpeedHandler());
		MinecraftForge.EVENT_BUS.register(new MovementHandler());
		//MinecraftForge.EVENT_BUS.register(new AxeChopHandler());

		proxy.registerRenderers();

//		if (BOPConfiguration.TerrainGen.netherOverride)
//		{
//        	DimensionManager.unregisterProviderType(-1);
//        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
//		}
		DimensionManager.registerProviderType(BOPConfiguration.IDs.promisedLandDimID, WorldProviderPromised.class, false);
		DimensionManager.registerDimension(BOPConfiguration.IDs.promisedLandDimID, BOPConfiguration.IDs.promisedLandDimID);
		
		BOPCrossIntegration.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BOPCrossIntegration.postInit();
		
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
		TickRegistry.registerTickHandler(new TickHandlerServer(), Side.SERVER);
	}
}