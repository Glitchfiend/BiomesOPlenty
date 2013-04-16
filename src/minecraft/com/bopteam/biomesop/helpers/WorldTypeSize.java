package com.bopteam.biomesop.helpers;

import com.bopteam.biomesop.configuration.BOPConfiguration;

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
