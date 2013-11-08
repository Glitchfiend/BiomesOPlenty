package biomesoplenty.configuration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Biomes;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.entities.EntityBird;
import biomesoplenty.entities.EntityGlob;
import biomesoplenty.entities.EntityJungleSpider;
import biomesoplenty.entities.EntityPhantom;
import biomesoplenty.entities.EntityRosester;
import biomesoplenty.entities.EntityWasp;
import biomesoplenty.entities.projectiles.EntityDart;
import biomesoplenty.entities.projectiles.EntityMudball;
import biomesoplenty.entities.projectiles.EntityPoisonDart;
import cpw.mods.fml.common.registry.EntityRegistry;

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
		EntityRegistry.registerModEntity(EntityMudball.class, "MudBall", BOPConfigurationIDs.entityMudballID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDart.class, "Dart", BOPConfigurationIDs.entityDartID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityPoisonDart.class, "PoisonDart", BOPConfigurationIDs.entityPoisonDartID, BiomesOPlenty.instance, 80, 3, true);
		
		if (BOPConfigurationIDs.jungleSpiderID > 0)
		{
			EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", BOPConfigurationIDs.jungleSpiderID, BiomesOPlenty.instance, 80, 3, true);
			
			registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);
			
			if (Biomes.bambooForest.isPresent() && Biomes.jungleNew.isPresent() && Biomes.tropicalRainforest.isPresent() && Biomes.oasis.isPresent() && Biomes.tropics.isPresent())
			{
				EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, Biomes.bambooForest.get(), Biomes.jungleNew.get(), Biomes.tropicalRainforest.get(), Biomes.oasis.get(), Biomes.tropics.get());
			}
		}
		
		if (BOPConfigurationIDs.rosesterID > 0)
		{
			EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", BOPConfigurationIDs.rosesterID, BiomesOPlenty.instance, 80, 3, true);
			
			registerEntityEgg(EntityRosester.class, 14831439, 16756224);
			
			if (Biomes.garden.isPresent())
			{
				EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, Biomes.garden.get());
			}
		}
		
		if (BOPConfigurationIDs.globID > 0)
		{
			EntityRegistry.registerModEntity(EntityGlob.class, "Glob", BOPConfigurationIDs.globID, BiomesOPlenty.instance, 80, 3, true);
			
			registerEntityEgg(EntityGlob.class, 6836276, 8414787);
			
			if (Biomes.bog.isPresent() && Biomes.deadSwamp.isPresent() && Biomes.fen.isPresent() && Biomes.moor.isPresent() && Biomes.quagmire.isPresent() && Biomes.sludgepit.isPresent() && Biomes.swamplandNew.isPresent())
			{
				EntityRegistry.addSpawn(EntityGlob.class, 1, 1, 1, EnumCreatureType.creature, Biomes.bog.get(), Biomes.deadSwamp.get(), Biomes.fen.get(), Biomes.moor.get(), Biomes.quagmire.get(), Biomes.sludgepit.get(), Biomes.swamplandNew.get());
			}
		}
		
		if (BOPConfigurationIDs.phantomID > 0)
		{
			EntityRegistry.registerModEntity(EntityPhantom.class, "Phantom", BOPConfigurationIDs.phantomID, BiomesOPlenty.instance, 80, 3, true);
			
			registerEntityEgg(EntityPhantom.class, 4472140, 2499368);
			
			if (Biomes.netherBone.isPresent() && Biomes.netherDesert.isPresent())
			{
				EntityRegistry.addSpawn(EntityPhantom.class, 8, 1, 1, EnumCreatureType.monster, Biomes.netherBone.get(), Biomes.netherDesert.get());
			}
		}
		
		if (BOPConfigurationIDs.waspID > 0)
		{
		    EntityRegistry.registerModEntity(EntityWasp.class, "Wasp", BOPConfigurationIDs.waspID, BiomesOPlenty.instance, 80, 3, true);

		    registerEntityEgg(EntityWasp.class, 16434729, 2500135);
		}
		
		if (BOPConfigurationIDs.birdID > 0)
		{
		    EntityRegistry.registerModEntity(EntityBird.class, "Bird", BOPConfigurationIDs.birdID, BiomesOPlenty.instance, 80, 3, true);

		    registerEntityEgg(EntityBird.class, 5277691, 16772788);
		    
		    if (Biomes.promisedLandForest.isPresent() && Biomes.promisedLandSwamp.isPresent() && Biomes.promisedLandPlains.isPresent())
			{
				EntityRegistry.addSpawn(EntityBird.class, 1, 1, 3, EnumCreatureType.monster, Biomes.promisedLandForest.get(), Biomes.promisedLandSwamp.get(), Biomes.promisedLandPlains.get());
			}
		}
	}
}
