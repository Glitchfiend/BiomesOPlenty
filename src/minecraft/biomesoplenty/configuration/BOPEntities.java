package biomesoplenty.configuration;

import com.google.common.base.Optional;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Entities;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityMudball;
import biomesoplenty.mobs.EntityJungleSpider;
import biomesoplenty.mobs.EntityRosester;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;

public class BOPEntities {
	
	//Eggs
	public static int eggIdCounter = 300;
	
	//Find the first available egg ID after our egg ID counter
	public static int getUniqueEntityEggId() {
		do {
			eggIdCounter++;
		} while (EntityList.getStringFromID(eggIdCounter) != null);

		return eggIdCounter;
	}
	
	@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
		int id = getUniqueEntityEggId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}
	
	public static void init()
	{	
		EntityRegistry.registerModEntity(EntityMudball.class, "MudBall", EntityRegistry.findGlobalUniqueEntityId(), BiomesOPlenty.instance, 80, 3, true); 	
		EntityRegistry.registerModEntity(EntityDart.class, "Dart", EntityRegistry.findGlobalUniqueEntityId(), BiomesOPlenty.instance, 80, 3, true); 	
		EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", BOPConfiguration.jungleSpiderID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", BOPConfiguration.rosesterID, BiomesOPlenty.instance, 80, 3, true);	
		
		registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);
		registerEntityEgg(EntityRosester.class, 14831439, 16756224);
		
		if (Biomes.jungleNew.isPresent() && Biomes.tropicalRainforest.isPresent() && Biomes.oasis.isPresent() && Biomes.tropics.isPresent())
		{
			EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, Biomes.jungleNew.get(), Biomes.tropicalRainforest.get(), Biomes.oasis.get(), Biomes.tropics.get());
		}

		if (Biomes.garden.isPresent())
		{
			EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, Biomes.garden.get());
		}
		
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.JungleSpider.name", "en_US", "Jungle Spider");
		LanguageRegistry.instance().addStringLocalization("entity.BiomesOPlenty.Rosester.name", "en_US", "Rosester");
	}
}
