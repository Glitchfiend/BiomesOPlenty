package biomesoplenty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.configuration.BOPBiomes;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.configuration.BOPCrafting;
import biomesoplenty.configuration.BOPEntities;
import biomesoplenty.configuration.BOPItems;
import biomesoplenty.configuration.BOPLiquids;
import biomesoplenty.configuration.BOPPotions;
import biomesoplenty.configuration.BOPVanillaCompat;
import biomesoplenty.handlers.BOPCraftHandler;
import biomesoplenty.handlers.BonemealHandler;
import biomesoplenty.handlers.EntityEventHandler;
import biomesoplenty.handlers.SoundHandler;
import biomesoplenty.handlers.TickHandlerClient;
import biomesoplenty.helpers.AchievementHelper;
import biomesoplenty.helpers.BOPLiquidHelper;
import biomesoplenty.helpers.CreativeTabsBOP;
import biomesoplenty.helpers.Localizations;
import biomesoplenty.helpers.Version;
import biomesoplenty.integration.BOPCrossIntegration;
import biomesoplenty.integration.TConstructIntegration;
import biomesoplenty.world.WorldProviderBOPhell;
import biomesoplenty.world.WorldProviderPromised;
import biomesoplenty.world.WorldTypeSize;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
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

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		BOPConfiguration.init(event.getSuggestedConfigurationFile());
		
		Version.check();

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");

		BOPPotions.init();
		BOPBlocks.init();
		BOPItems.init();
		BOPLiquids.init();
		BOPCrafting.init();
		BOPBiomes.init();
		BOPEntities.init();
		BOPVanillaCompat.init();
		
        Localizations.loadLanguages();

		// Achievement declaration
		if (BOPConfiguration.achievements)
		{
			AchievementHelper.init();
		}

		GameRegistry.registerCraftingHandler(new BOPCraftHandler());
		
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
		
		BOPCrossIntegration.preInit();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabBiomesOPlenty", "en_US", "Biomes O\' Plenty");
		LanguageRegistry.instance().addStringLocalization("generator.BIOMESOP", "en_US", "Biomes O\' Plenty");

		// Add helpers for compatibility
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldTypeSize());
		MinecraftForge.EVENT_BUS.register(new AchievementHelper());
		MinecraftForge.EVENT_BUS.register(new BonemealHandler());
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new BOPLiquidHelper());

		proxy.registerRenderers();

		if (BOPConfiguration.netherOverride)
		{
        	DimensionManager.unregisterProviderType(-1);
        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
		}
		DimensionManager.registerProviderType(BOPConfiguration.promisedLandDimID, WorldProviderPromised.class, false);
		DimensionManager.registerDimension(BOPConfiguration.promisedLandDimID, BOPConfiguration.promisedLandDimID);
		
		BOPCrossIntegration.init();
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		BOPCrossIntegration.postInit();
		
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
	}
}