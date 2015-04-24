package biomesoplenty.common.network;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class BOPPacketHandler
{
    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(BiomesOPlenty.MOD_ID);
    
    public static void init()
    {
        // register messages here
        // instance.registerMessage(messageHandler, requestMessageType, discriminator, side);
    }
    
    
}
