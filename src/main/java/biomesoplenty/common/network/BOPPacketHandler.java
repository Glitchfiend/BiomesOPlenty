package biomesoplenty.common.network;

import org.apache.logging.log4j.Level;

import biomesoplenty.common.network.message.MessageBiomePosition;
import biomesoplenty.common.utils.BOPLogger;
import biomesoplenty.common.utils.BOPModInfo;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class BOPPacketHandler 
{
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(BOPModInfo.modID);
	private static int nextId = 0;
	
	public static void init()
	{
		registerMessage(MessageBiomePosition.class, Side.CLIENT);
	}
	
	private static void registerMessage(Class messageHandler, Side side)
	{
		instance.registerMessage(messageHandler, messageHandler, getNextId(), side);
	}
	
	private static int getNextId()
	{
		if (nextId == 255)
		{
			BOPLogger.log(Level.ERROR, "There are no more message discriminators avaliable");
		
			return -1;
		}
		else
		{
			return ++nextId;
		}
	}
}
