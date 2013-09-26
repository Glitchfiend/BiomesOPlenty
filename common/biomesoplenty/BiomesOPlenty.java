package biomesoplenty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static argo.jdom.JsonNodeBuilders.aStringBuilder;

import net.minecraft.crash.CallableMinecraftVersion;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.configuration.BOPBiomes;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPCrafting;
import biomesoplenty.configuration.BOPEntities;
import biomesoplenty.configuration.BOPFluids;
import biomesoplenty.configuration.BOPItems;
import biomesoplenty.configuration.BOPPotions;
import biomesoplenty.configuration.BOPStructures;
import biomesoplenty.configuration.BOPVanillaCompat;
import biomesoplenty.configuration.configfile.BOPConfiguration;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.handlers.BOPCraftHandler;
import biomesoplenty.handlers.BonemealHandler;
import biomesoplenty.handlers.BreakSpeedHandler;
import biomesoplenty.handlers.EntityEventHandler;
import biomesoplenty.handlers.FlipperMovementEventHandler;
import biomesoplenty.handlers.FluidEventHandler;
import biomesoplenty.handlers.MovementHandler;
import biomesoplenty.handlers.SoundHandler;
import biomesoplenty.handlers.TickHandlerClient;
import biomesoplenty.handlers.TickHandlerServer;
import biomesoplenty.handlers.VillageMaterialEventHandler;
import biomesoplenty.handlers.versionhandlers.BOPForgeVersionHandler;
import biomesoplenty.handlers.versionhandlers.BOPModVersionHandler;
import biomesoplenty.helpers.AchievementHelper;
import biomesoplenty.helpers.CreativeTabsBOP;
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
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionParser;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="BiomesOPlenty", name="Biomes O' Plenty", version=BOPModVersionHandler.VERSION)
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
		
		BOPForgeVersionHandler.check();
		
		String dependancies = "after:Natura;required-after:Forge@[" + BOPForgeVersionHandler.recommendedVersion + ",)";
		event.getModMetadata().dependencies.add(VersionParser.parseVersionReference(dependancies));
		
		BOPModVersionHandler.check();

		tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(),"tabBiomesOPlenty");

		BOPPotions.init();
		BOPBlocks.init();
		BOPItems.init();
		BOPFluids.init();
		BOPCrafting.init();
		if ((new CallableMinecraftVersion(null)).minecraftVersion() != "1.6.2")
			BOPStructures.init();
		BOPBiomes.init();
		BOPEntities.init();
		BOPVanillaCompat.init();

		// Achievement declaration
		if (BOPConfigurationMisc.achievements)
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
		if (ForgeVersion.buildVersion >= 891 || proxy instanceof ClientProxy)
			MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterialEventHandler());
		MinecraftForge.EVENT_BUS.register(new AchievementHelper());
		MinecraftForge.EVENT_BUS.register(new BonemealHandler());
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new FluidEventHandler());
		MinecraftForge.EVENT_BUS.register(new BreakSpeedHandler());
		MinecraftForge.EVENT_BUS.register(new MovementHandler());
		MinecraftForge.EVENT_BUS.register(new FlipperMovementEventHandler());
		//MinecraftForge.EVENT_BUS.register(new AxeChopHandler());

		proxy.registerRenderers();

//		if (BOPConfiguration.TerrainGen.netherOverride)
//		{
//        	DimensionManager.unregisterProviderType(-1);
//        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
//		}
		DimensionManager.registerProviderType(BOPConfigurationIDs.promisedLandDimID, WorldProviderPromised.class, false);
		DimensionManager.registerDimension(BOPConfigurationIDs.promisedLandDimID, BOPConfigurationIDs.promisedLandDimID);
		
		BOPCrossIntegration.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BOPCrossIntegration.postInit();
		
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
		TickRegistry.registerTickHandler(new TickHandlerServer(), Side.SERVER);
	}
	
    public <T extends Collection<ArtifactVersion>> T processReferences(Object refs, Class<? extends T> retType)
    {
        T res = null;
        try
        {
            res = retType.newInstance();
        }
        catch (Exception e)
        {
            // unpossible
        }

        if (refs == null)
        {
            return res;
        }
        for (String ref : ((List<String>)refs))
        {
            res.add(VersionParser.parseVersionReference(ref));
        }
        return res;
    }
}