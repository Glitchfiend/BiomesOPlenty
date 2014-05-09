package biomesoplenty.common.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.entities.EntityBird;
import biomesoplenty.common.entities.EntityGlob;
import biomesoplenty.common.entities.EntityJungleSpider;
import biomesoplenty.common.entities.EntityPhantom;
import biomesoplenty.common.entities.EntityPixie;
import biomesoplenty.common.entities.EntityRosester;
import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.entities.projectiles.EntityDart;
import biomesoplenty.common.entities.projectiles.EntityMudball;
import cpw.mods.fml.common.registry.EntityRegistry;

public class BOPEntities 
{
	public static int eggIdCounter = 300;
	
	public static void init()
	{
		registerEntities();
	}
	
	private static void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityDart.class, "dart", BOPConfigurationIDs.entityDartID, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityMudball.class, "mudball", BOPConfigurationIDs.entityMudballID, BiomesOPlenty.instance, 80, 3, true);

		if (BOPConfigurationIDs.jungleSpiderID > 0)
		{
			EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", BOPConfigurationIDs.jungleSpiderID, BiomesOPlenty.instance, 80, 3, true);

			registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);

				EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, BOPCBiomes.bambooForest, BiomeGenBase.jungle, BOPCBiomes.tropicalRainforest/*, Biomes.oasis.get()*/, BOPCBiomes.tropics);
		}

		if (BOPConfigurationIDs.rosesterID > 0)
		{
			EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", BOPConfigurationIDs.rosesterID, BiomesOPlenty.instance, 80, 3, true);

			registerEntityEgg(EntityRosester.class, 14831439, 16756224);

			/*TODO: FEATURE if (Biomes.garden.isPresent())
			{
				EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, Biomes.garden.get());
			}*/
		}

		if (BOPConfigurationIDs.globID > 0)
		{
			EntityRegistry.registerModEntity(EntityGlob.class, "Glob", BOPConfigurationIDs.globID, BiomesOPlenty.instance, 80, 3, true);

			registerEntityEgg(EntityGlob.class, 6836276, 8414787);

			EntityRegistry.addSpawn(EntityGlob.class, 1, 1, 1, EnumCreatureType.creature, BOPCBiomes.bog, BOPCBiomes.deadSwamp, BOPCBiomes.fen, BOPCBiomes.moor, BOPCBiomes.quagmire, BOPCBiomes.sludgepit, BiomeGenBase.swampland);
		}

		if (BOPConfigurationIDs.phantomID > 0)
		{
			EntityRegistry.registerModEntity(EntityPhantom.class, "Phantom", BOPConfigurationIDs.phantomID, BiomesOPlenty.instance, 80, 3, true);

			registerEntityEgg(EntityPhantom.class, 4472140, 2499368);

			/*TODO: FEATURE if (Biomes.netherBone.isPresent() && Biomes.netherDesert.isPresent())
			{
				EntityRegistry.addSpawn(EntityPhantom.class, 8, 1, 1, EnumCreatureType.monster, Biomes.netherBone.get(), Biomes.netherDesert.get());
			}*/
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

			/*TODO: FEATURE if (Biomes.promisedLandForest.isPresent() && Biomes.promisedLandSwamp.isPresent() && Biomes.promisedLandPlains.isPresent() && Biomes.promisedLandShrub.isPresent())
			{
				EntityRegistry.addSpawn(EntityBird.class, 10, 3, 5, EnumCreatureType.ambient, Biomes.promisedLandForest.get(), Biomes.promisedLandSwamp.get(), Biomes.promisedLandPlains.get(), Biomes.promisedLandShrub.get());
			}*/
		}

		if (BOPConfigurationIDs.pixieID > 0)
		{
			EntityRegistry.registerModEntity(EntityPixie.class, "Pixie", BOPConfigurationIDs.pixieID, BiomesOPlenty.instance, 80, 3, true);

			registerEntityEgg(EntityPixie.class, 16742365, 16645116);

			EntityRegistry.addSpawn(EntityPixie.class, 8, 1, 3, EnumCreatureType.ambient, BOPCBiomes.mysticGrove);
		}
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	{
		int id = getUniqueEntityEggId();
		
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}
	

	public static int getUniqueEntityEggId() 
	{
		do 
		{
			eggIdCounter++;
		} while (EntityList.getStringFromID(eggIdCounter) != null);

		return eggIdCounter;
	}
}
