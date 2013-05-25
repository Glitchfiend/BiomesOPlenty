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
import biomesoplenty.helpers.AchievementHelper;
import biomesoplenty.helpers.BOPCraft;
import biomesoplenty.helpers.BOPLiquidHelper;
import biomesoplenty.helpers.BonemealUse;
import biomesoplenty.helpers.CreativeTabsBOP;
import biomesoplenty.helpers.EntitiesHelper;
import biomesoplenty.integration.BOPCrossIntegration;
import biomesoplenty.world.WorldProviderBOPhell;
import biomesoplenty.world.WorldProviderPromised;
import biomesoplenty.world.WorldTypeSize;
import cpw.mods.fml.common.FMLCommonHandler;
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

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", version="0.5.4")
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
		boolean isClient = proxy instanceof ClientProxy;

		String[] soundFiles = { "bopdisc.ogg", "bopdiscmud.ogg"};

		if (isClient)
		{
			for (String soundFile : soundFiles) try
			{
				File file = new File(Minecraft.getMinecraftDir().getAbsolutePath() + "/resources/mod/streaming/" + soundFile);
				if (!file.exists()) {
					System.out.println("[BiomesOPlenty] " + soundFile + " doesn't exist, creating...");
					file.getParentFile().mkdirs();
					file.createNewFile();
					InputStream istream = getClass().getResourceAsStream("/mods/BiomesOPlenty/audio/" + soundFile);
					OutputStream out = new FileOutputStream(file);
					byte[] buf = new byte[1024];
					int size = 0;
					int len;
					while ((len = istream.read(buf)) > 0) {
						out.write(buf, 0, len);
						size += len;
					}
					out.close();
					istream.close();
					if (size == 0) file.delete();
				}
			}
			catch (Exception e)
			{
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "[BiomesOPlenty] Failed to load sound file: " + soundFile);
				e.printStackTrace();
			}
		}

		BOPConfiguration.init(event.getSuggestedConfigurationFile());
			
		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");
		
		BOPPotions.init();

		BOPBlocks.init();

		BOPItems.init();
		
		BOPLiquids.init();

		BOPCrafting.init();

		BOPBiomes.init();

		BOPEntities.init();

		BOPVanillaCompat.init();

		// Achievement declaration
		if (BOPConfiguration.achievements == true)
		{
			AchievementHelper.init();
		}
		
		GameRegistry.registerCraftingHandler(new BOPCraft());
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabBiomesOPlenty", "en_US", "Biomes O\' Plenty");
		LanguageRegistry.instance().addStringLocalization("generator.BIOMESOP", "en_US", "Biomes O\' Plenty");

		// Add helpers for compatibility
		MinecraftForge.TERRAIN_GEN_BUS.register(new WorldTypeSize());
		MinecraftForge.EVENT_BUS.register(new AchievementHelper());
		MinecraftForge.EVENT_BUS.register(new BonemealUse());
		MinecraftForge.EVENT_BUS.register(new EntitiesHelper());
		MinecraftForge.EVENT_BUS.register(new BOPLiquidHelper());

		proxy.registerRenderers();

		DimensionManager.unregisterProviderType(-1);
		DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
		DimensionManager.registerProviderType(BOPConfiguration.promisedLandDimID, WorldProviderPromised.class, false);
		DimensionManager.registerDimension(BOPConfiguration.promisedLandDimID, BOPConfiguration.promisedLandDimID);
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		BOPCrossIntegration.init();
	}
}