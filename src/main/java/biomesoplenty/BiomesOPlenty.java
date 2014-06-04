package biomesoplenty;

import biomesoplenty.common.configuration.BOPConfiguration;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.configuration.structures.BOPConfigurationStrongholds;
import biomesoplenty.common.configuration.structures.BOPConfigurationVillages;
import biomesoplenty.common.core.*;
import biomesoplenty.common.eventhandler.BOPEventHandlers;
import biomesoplenty.common.helpers.CreativeTabsBOP;
import biomesoplenty.common.integration.TreecapitatorIntegration;
import biomesoplenty.common.network.PacketPipeline;
import biomesoplenty.common.utils.BOPModInfo;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.common.world.decoration.BiomeTweaker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid = BOPModInfo.modID, name = BOPModInfo.modName, dependencies = "after:Natura; required-after:Forge@[1.42.666.42.1,)")
public class BiomesOPlenty
{
    @Instance("BiomesOPlenty")
    public static BiomesOPlenty instance;
    
    @SidedProxy(clientSide = "biomesoplenty.ClientProxy", serverSide = "biomesoplenty.CommonProxy")
    public static CommonProxy proxy;
    
    public static final PacketPipeline packetPipeline = new PacketPipeline();
    
    public static CreativeTabs tabBiomesOPlenty;
    public static String configPath;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        configPath = event.getModConfigurationDirectory() + "/biomesoplenty/";
        BOPConfiguration.init(configPath);
        
        //Version.check();
        
        tabBiomesOPlenty = new CreativeTabsBOP(CreativeTabs.getNextID(), "tabBiomesOPlenty");
        
        BOPPackets.init();
        BOPPotions.init();
        BOPFluids.preInit();
        BOPBlocks.init();
        BOPItems.init();
        BOPFluids.init();
        BOPCrafting.init();
        BOPBiomes.init();
        BOPConfigurationVillages.init(BOPConfiguration.villagesConfigFile);
        BOPConfigurationStrongholds.init(BOPConfiguration.strongholdsConfigFile);
        BiomeTweaker.init();
        BOPEntities.init();
        BOPVanillaCompat.init();
        
        BOPEventHandlers.init();
        
        proxy.registerRenderers();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        packetPipeline.initialize();
        
        TreecapitatorIntegration.init();

        BOPDimensions.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        packetPipeline.postInitialize();
        
        BOPBiomes.worldTypeBOP = new WorldTypeBOP();
    }
}