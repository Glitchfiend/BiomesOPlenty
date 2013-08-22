package biomesoplenty.world;

import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import biomesoplenty.configuration.BOPConfiguration;

public class WorldTypeSize
{
	@ForgeSubscribe
	public void BiomeSize(WorldTypeEvent.BiomeSize event)
	{
		if (event.worldType.getWorldTypeName() == "BIOMESOP") {
			if (BOPConfiguration.TerrainGen.fancyGen) {
				event.newSize = (byte)5;
			}
			else {
			event.newSize = (byte)BOPConfiguration.TerrainGen.biomeSize;
			}
			
			if (BOPConfiguration.TerrainGen.netherOverride)
			{
	        	DimensionManager.unregisterProviderType(-1);
	        	DimensionManager.registerProviderType(-1, WorldProviderBOPhell.class, true);
			}
		}
		else if (BOPConfiguration.TerrainGen.netherOverride && BOPConfiguration.TerrainGen.addToDefault)
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
