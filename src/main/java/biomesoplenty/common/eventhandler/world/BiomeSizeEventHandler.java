package biomesoplenty.common.eventhandler.world;

import net.minecraftforge.event.terraingen.WorldTypeEvent.BiomeSize;
import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BiomeSizeEventHandler 
{
	@SubscribeEvent
	public void onDetermineBiomeSize(BiomeSize event)
	{
		if (event.worldType.getWorldTypeName() == "BIOMESOP" || event.worldType.getWorldTypeName() == "ATG") 
		{
			event.newSize = (byte)BOPConfigurationTerrainGen.biomeSize;

			//if (BOPConfigurationTerrainGen.netherOverride)
			//{
				//DimensionManager.unregisterProviderType(-1);
				//DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
			//}
		}
		else
		{
			//DimensionManager.unregisterProviderType(-1);
			//DimensionManager.registerProviderType(-1, WorldProviderHell.class, true);
		}
	}
}
