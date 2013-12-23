package biomesoplenty;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.configuration.BOPConfigurationIDs;
import biomesoplenty.core.BOPAchievements;
import biomesoplenty.core.BOPBiomes;
import biomesoplenty.core.BOPBlocks;
import biomesoplenty.core.BOPCrafting;
import biomesoplenty.core.BOPEntities;
import biomesoplenty.core.BOPFluids;
import biomesoplenty.core.BOPItems;
import biomesoplenty.core.BOPPotions;
import biomesoplenty.core.BOPStructures;
import biomesoplenty.core.BOPVanillaCompat;
import biomesoplenty.eventhandlers.BonemealEventHandler;
import biomesoplenty.eventhandlers.BreakSpeedEventHandler;
import biomesoplenty.eventhandlers.CapeEventHandler;
import biomesoplenty.eventhandlers.EntityEventHandler;
import biomesoplenty.eventhandlers.FlipperMovementEventHandler;
import biomesoplenty.eventhandlers.FluidEventHandler;
import biomesoplenty.eventhandlers.VillageMaterialEventHandler;
import biomesoplenty.handlers.MovementHandler;
import biomesoplenty.handlers.SoundHandler;
import biomesoplenty.helpers.CreativeTabsBOP;
import biomesoplenty.helpers.Version;
import biomesoplenty.integration.BOPCrossIntegration;
import biomesoplenty.world.WorldProviderPromised;
import biomesoplenty.world.WorldTypeBOP;
import biomesoplenty.world.WorldTypeSize;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

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

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");

		//BOPPotions.init();
		BOPBlocks.init();
		BOPItems.init();
		/*BOPFluids.init();
		BOPCrafting.init();
		BOPStructures.init();*/
		//BOPBiomes.init();
		/*BOPEntities.init();
		BOPVanillaCompat.init();*/
		
		//GameRegistry.registerCraftingHandler(new BOPCraftHandler());
		//GameRegistry.registerPickupHandler(new BOPPickupHandler());

		// Achievement declaration
		/*if (BOPConfigurationMisc.achievements)
		{
			BOPAchievements.init();
		}
		*/
		if (proxy instanceof ClientProxy)
		{
			//MinecraftForge.EVENT_BUS.register(new SoundHandler());
		}
		/*
		BOPCrossIntegration.preInit();*/
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		// Add helpers for compatibility
	    /*MinecraftForge.TERRAIN_GEN_BUS.register(new WorldTypeSize());
	    MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
	    MinecraftForge.EVENT_BUS.register(new BOPAchievements());
		MinecraftForge.EVENT_BUS.register(new BonemealEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new FluidEventHandler());
		MinecraftForge.EVENT_BUS.register(new BreakSpeedEventHandler());
		MinecraftForge.EVENT_BUS.register(new MovementHandler());
		MinecraftForge.EVENT_BUS.register(new FlipperMovementEventHandler());
		if (event.getSide() == Side.CLIENT)
		{
		    MinecraftForge.EVENT_BUS.register(new CapeEventHandler());
		}

		proxy.registerRenderers();

//		if (BOPConfiguration.TerrainGen.netherOverride)
//		{
//        	DimensionManager.unregisterProviderType(-1);
//        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
//		}
		DimensionManager.registerProviderType(BOPConfigurationIDs.promisedLandDimID, WorldProviderPromised.class, false);
		DimensionManager.registerDimension(BOPConfigurationIDs.promisedLandDimID, BOPConfigurationIDs.promisedLandDimID);
		
		BOPCrossIntegration.init();*/
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//BOPCrossIntegration.postInit();

		//Initialize new world type
		BOPBiomes.WTBiomesOP = new WorldTypeBOP();
		
		//TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
		//TickRegistry.registerTickHandler(new TickHandlerServer(), Side.SERVER);
	}
}