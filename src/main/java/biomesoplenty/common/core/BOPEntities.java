package biomesoplenty.common.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMain;
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
	public static int EntID = 0;
	
	public static void init()
	{
		registerEntities();
	}
	
	private static void registerEntities()
	{
		boolean mobSpawns = BOPConfigurationMain.mobSpawns;
		
		EntityRegistry.registerModEntity(EntityDart.class, "dart", EntID++, BiomesOPlenty.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityMudball.class, "mudball", EntID++, BiomesOPlenty.instance, 80, 3, true);

		EntityRegistry.registerModEntity(EntityJungleSpider.class, "JungleSpider", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityJungleSpider.class, 5147192, 11013646);
		if(mobSpawns && BOPConfigurationMain.spawnJungleSpider)
			EntityRegistry.addSpawn(EntityJungleSpider.class, 8, 1, 3, EnumCreatureType.monster, BOPCBiomes.bambooForest, BiomeGenBase.jungle, BOPCBiomes.tropicalRainforest, BOPCBiomes.oasis, BOPCBiomes.tropics, BOPCBiomes.mangrove, BOPCBiomes.sacredSprings, BOPCBiomes.rainforest);

		EntityRegistry.registerModEntity(EntityRosester.class, "Rosester", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityRosester.class, 14831439, 16756224);
		if(mobSpawns && BOPConfigurationMain.spawnRosester)
			EntityRegistry.addSpawn(EntityRosester.class, 10, 2, 4, EnumCreatureType.creature, BOPCBiomes.garden);

		EntityRegistry.registerModEntity(EntityGlob.class, "Glob", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityGlob.class, 6836276, 8414787);
		if(mobSpawns && BOPConfigurationMain.spawnGlob)
			EntityRegistry.addSpawn(EntityGlob.class, 1, 1, 1, EnumCreatureType.creature, BOPCBiomes.deadSwamp, BOPCBiomes.fen, BOPCBiomes.moor, BOPCBiomes.quagmire, BOPCBiomes.sludgepit, BiomeGenBase.swampland);

		EntityRegistry.registerModEntity(EntityPhantom.class, "Phantom", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityPhantom.class, 4472140, 2499368);
		if (BOPCBiomes.boneyard != null && BOPCBiomes.corruptedSands != null && mobSpawns && BOPConfigurationMain.spawnPhantom)
		{
			EntityRegistry.addSpawn(EntityPhantom.class, 8, 1, 1, EnumCreatureType.monster, BOPCBiomes.boneyard, BOPCBiomes.corruptedSands);
		}

		EntityRegistry.registerModEntity(EntityWasp.class, "Wasp", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityWasp.class, 16434729, 2500135);

		EntityRegistry.registerModEntity(EntityBird.class, "Bird", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityBird.class, 5277691, 16772788);
		/*TODO: FEATURE if (Biomes.promisedLandForest.isPresent() && Biomes.promisedLandSwamp.isPresent() && Biomes.promisedLandPlains.isPresent() && Biomes.promisedLandShrub.isPresent())
		{
			EntityRegistry.addSpawn(EntityBird.class, 10, 3, 5, EnumCreatureType.ambient, Biomes.promisedLandForest.get(), Biomes.promisedLandSwamp.get(), Biomes.promisedLandPlains.get(), Biomes.promisedLandShrub.get());
		}*/

		EntityRegistry.registerModEntity(EntityPixie.class, "Pixie", EntID++, BiomesOPlenty.instance, 80, 3, true);
		registerEntityEgg(EntityPixie.class, 16742365, 16645116);
		if(mobSpawns && BOPConfigurationMain.spawnPixie)
			EntityRegistry.addSpawn(EntityPixie.class, 8, 1, 3, EnumCreatureType.ambient, BOPCBiomes.mysticGrove);
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
