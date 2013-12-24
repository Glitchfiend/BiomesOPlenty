package biomesoplenty.core;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.configuration.BOPConfigurationIDs;
import biomesoplenty.entities.projectiles.EntityDart;
import cpw.mods.fml.common.registry.EntityRegistry;

public class BOPEntities 
{
	public static void init()
	{
		registerEntities();
	}
	
	private static void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityDart.class, "dart", BOPConfigurationIDs.entityDartID, BiomesOPlenty.instance, 80, 3, true);
	}
}
