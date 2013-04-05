package tdwp_ftw.biomesop.helpers;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPConfiguration;
import net.minecraft.world.WorldType;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public class WorldTypeSize
{
	@ForgeSubscribe
	public void BiomeSize(WorldTypeEvent.BiomeSize event)
	{
		event.newSize = (byte)BOPConfiguration.biomeSize;
	}

}
