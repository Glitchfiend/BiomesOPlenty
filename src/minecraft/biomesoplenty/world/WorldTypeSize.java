package biomesoplenty.world;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import biomesoplenty.configuration.BOPConfiguration;

public class WorldTypeSize
{
	@ForgeSubscribe
	public void BiomeSize(WorldTypeEvent.BiomeSize event)
	{
	    if (event.worldType.getWorldTypeID() == 4)
	        event.newSize = (byte)BOPConfiguration.biomeSize;
	}

}
