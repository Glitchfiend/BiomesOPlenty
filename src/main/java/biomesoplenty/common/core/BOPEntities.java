package biomesoplenty.common.core;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.common.entities.projectiles.EntityMudball;
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
		EntityRegistry.registerModEntity(EntityMudball.class, "mudball", BOPConfigurationIDs.entityMudballID, BiomesOPlenty.instance, 80, 3, true);
	}
}
