package tan;

import net.minecraftforge.common.MinecraftForge;
import tan.core.TANPlayerStats;
import tan.core.TANTemperature;
import tan.handler.ConnectionHandler;
import tan.handler.RenderOverlayEventHandler;
import tan.handler.TickHandlerServer;
import tan.network.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "ToughAsNails", name = "Tough As Nails", version="0.0.1")
@NetworkMod(channels = { "ToughAsNails" }, packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = false)
public class ToughAsNails
{
    @Instance("ToughAsNails")
    public static ToughAsNails instance;
    
    @SidedProxy(clientSide="tan.ClientProxy", serverSide="tan.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        TANPlayerStats.init();
        TANTemperature.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        TickRegistry.registerTickHandler(new TickHandlerServer(), Side.SERVER);
        
        if (proxy instanceof ClientProxy)
        {
            MinecraftForge.EVENT_BUS.register(new RenderOverlayEventHandler());
        }

        NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
}
