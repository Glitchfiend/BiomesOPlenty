package biomesoplenty.world;

import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import biomesoplenty.configuration.configfile.BOPConfigurationTerrainGen;

public class WorldTypeSize
{
	@ForgeSubscribe
	public void BiomeSize(WorldTypeEvent.BiomeSize event)
	{
		if (event.worldType.getWorldTypeName() == "BIOMESOP") {
			event.newSize = (byte)BOPConfigurationTerrainGen.biomeSize;
			
			if (BOPConfigurationTerrainGen.netherOverride)
			{
	        	DimensionManager.unregisterProviderType(-1);
	        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
			}
		}
		else if (BOPConfigurationTerrainGen.netherOverride && BOPConfigurationTerrainGen.addToDefault)
		{
        	DimensionManager.unregisterProviderType(-1);
        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
		}
		else
		{
			DimensionManager.unregisterProviderType(-1);
			DimensionManager.registerProviderType(-1, WorldProviderHell.class, true);
		}
	}

}
