package tdwp_ftw.biomesop.helpers;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import tdwp_ftw.biomesop.configuration.BOPConfiguration;

public class WorldTypeSize
{
	@ForgeSubscribe
	public void BiomeSize(WorldTypeEvent.BiomeSize event)
	{
		event.newSize = (byte)BOPConfiguration.biomeSize;
	}

}
