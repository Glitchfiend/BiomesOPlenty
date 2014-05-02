package biomesoplenty.common.configuration;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

public class BOPConfigurationIDs
{
	public static Configuration config;

	//Biome IDs
	public static int alpsID;
	public static int alpsForestID;
	public static int alpsBaseID;
	public static int arcticID;
	public static int autumnHillsID;
	public static int badlandsID;
	public static int bambooForestID;
	public static int bayouID;

	public static int beachGravelID;
	public static int beachOvergrownID;

	public static int birchForestID;
	public static int bogID;
	public static int borealForestID;
	public static int brushlandID;
	public static int canyonID;
	public static int canyonRavineID;
	public static int chaparralID;
	public static int cherryBlossomGroveID;
	public static int coniferousForestID;
	public static int coniferousForestSnowID;
	public static int cragID;
	public static int deadForestID;
	public static int deadForestSnowID;
	public static int deadSwampID;
	public static int deadlandsID;
	public static int deciduousForestID;
	public static int dunesID;
	public static int fenID;
	public static int flowerFieldID;
	public static int frostForestID;
	public static int fungiForestID;
	public static int gardenID;
	public static int glacierID;
	public static int grasslandID;
	public static int groveID;
	public static int heathlandID;
	public static int highlandID;
	public static int hotSpringsID;
	public static int icyHillsID;
	public static int jadeCliffsID;
	public static int lavenderFieldsID;
	public static int lushDesertID;
	public static int lushSwampID;
	public static int mangroveID;
	public static int mapleWoodsID;
	public static int marshID;
	public static int meadowID;
	public static int meadowForestID;
	public static int mesaID;
	public static int moorID;
	public static int mountainID;
	public static int mysticGroveID;
	public static int mysticGroveThinID;

	public static int netherUndergardenID;
	public static int netherCorruptedSandsID;
	public static int netherPhantasmagoricInfernoID;
	public static int netherBoneyardID;
	public static int netherVisceralHeapID;

	public static int oasisID;

	public static int oceanAbyssID;
	public static int oceanCoralID;
	public static int oceanKelpID;

	public static int ominousWoodsID;
	public static int ominousWoodsThickID;
	public static int orchardID;
	public static int originValleyID;
	public static int outbackID;
	public static int overgrownGreensID;
	public static int polarID;
	public static int prairieID;

	public static int quagmireID;
	public static int rainforestID;
	public static int redwoodForestID;
	public static int reefID;
	public static int sacredSpringsID;
	public static int savannaID;
	public static int savannaPlateauID;
	public static int scrublandID;
	public static int seasonalForestID;
	public static int seasonalSpruceForestID;
	public static int shieldID;
	public static int shoreID;
	public static int shrublandID;
	public static int shrublandForestID;
	public static int silkgladesID;
	public static int sludgepitID;
	public static int spruceWoodsID;
	public static int steppeID;
	public static int temperateRainforestID;
	public static int thicketID;
	public static int timberID;
	public static int timberThinID;
	public static int tropicalRainforestID;
	public static int tropicsID;
	public static int tropicsMountainID;
	public static int tundraID;
	public static int volcanoID;
	public static int wastelandID;
	public static int wetlandID;
	public static int woodlandID;

	public static int entityMudballID;
	public static int entityDartID;
	public static int entityPoisonDartID;

	public static int jungleSpiderID;
	public static int rosesterID;
	public static int globID;
	public static int phantomID;
	public static int waspID;
	public static int birdID;
	public static int pixieID;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();
			
			//Mob IDs
			jungleSpiderID = config.get("Mob IDs", "Jungle Spider ID", 101, null).getInt();
			rosesterID = config.get("Mob IDs", "Rosester ID", 102, null).getInt();
			globID = config.get("Mob IDs", "Glob ID", 106, null).getInt();
			phantomID = config.get("Mob IDs", "Phantom ID", 107, null).getInt();
	        waspID = config.get("Mob IDs", "Wasp ID", 108, null).getInt();
	        birdID = config.get("Mob IDs", "Bird ID", 109, null).getInt();
	        pixieID = config.get("Mob IDs", "Pixie ID", 110, null).getInt();

			//Projectile IDs
			entityMudballID = config.get("Entity IDs", "Mudball ID", 103, null).getInt();;
			entityDartID = config.get("Entity IDs", "Dart ID", 104, null).getInt();;
			entityPoisonDartID = config.get("Entity IDs", "Poison Dart ID", 105, null).getInt();;

			//23-79 ExtraBiomesXL
			
			//fungiForestID = config.get("Biome IDs", "Fungi Forest ID", 201).getInt();
			//gardenID = config.get("Biome IDs", "Garden ID", 202).getInt();
			//glacierID = config.get("Biome IDs", "Glacier ID", 203).getInt();
			//hotSpringsID = config.get("Biome IDs", "Hot Springs ID", 211).getInt();
			//mangroveID = config.get("Biome IDs", "Mangrove ID", 216).getInt();
			//oasisID = config.get("Biome IDs", "Oasis ID", 224).getInt();
			//orchardID = config.get("Biome IDs", "Orchard ID", 226).getInt();
			//scrublandID = config.get("Biome IDs", "Scrubland ID", 237).getInt();
			//steppeID = config.get("Biome IDs", "Steppe ID", 244).getInt();

			//oceanAbyssID = config.get("Biome IDs", "Oceanic Abyss (Ocean) ID", 72).getInt();
			//oceanCoralID = config.get("Biome IDs", "Coral Reef (Ocean) ID", 73).getInt();
			//oceanKelpID = config.get("Biome IDs", "Kelp Forest (Ocean) ID", 74).getInt();

			//beachGravelID = config.get("Biome IDs", "Gravel Beach ID", 75).getInt();
			//beachOvergrownID = config.get("Biome IDs", "Overgrown Beach ID", 76).getInt();

			//70-87 Twilight Forest < Changed on their end?

			//80-159 Better World Generation 4 < Changed on their end?
			
			//160-161 BuildCraft Oil Fields
			
			//162-169 Thaumcraft
			
			netherUndergardenID = config.get("Biome IDs", "Undergarden (Nether) ID", 185).getInt();
			netherCorruptedSandsID = config.get("Biome IDs", "Corrupted Sands (Nether) ID", 186).getInt();
			netherPhantasmagoricInfernoID = config.get("Biome IDs", "Phantasmagoric Inferno (Nether) ID", 187).getInt();
			netherBoneyardID = config.get("Biome IDs", "Boneyard (Nether) ID", 188).getInt();
            netherVisceralHeapID = config.get("Biome IDs", "Visceral Heap (Nether) ID", 189).getInt();

			alpsID = config.get("Biome IDs", "Alps ID", 194).getInt();
			arcticID = config.get("Biome IDs", "Arctic ID", 195).getInt();
			bambooForestID = config.get("Biome IDs", "Bamboo Forest ID", 196).getInt();
			bayouID = config.get("Biome IDs", "Bayou ID", 197).getInt();
			bogID = config.get("Biome IDs", "Bog ID", 198).getInt();
			borealForestID = config.get("Biome IDs", "Boreal Forest ID", 199).getInt();
			brushlandID = config.get("Biome IDs", "Brushland ID", 200).getInt();
			canyonID = config.get("Biome IDs", "Canyon ID", 201).getInt();
			chaparralID = config.get("Biome IDs", "Chaparral ID", 202).getInt();
			cherryBlossomGroveID = config.get("Biome IDs", "Cherry Blossom Grove ID", 203).getInt();
			coniferousForestID = config.get("Biome IDs", "Coniferous Forest ID", 204).getInt();
			coniferousForestSnowID = config.get("Biome IDs", "Coniferous Forest (Snow) ID", 205).getInt();
			cragID = config.get("Biome IDs", "Crag ID", 206).getInt();
			
			//207-209 left for Mo Creatures
			
			deadForestID = config.get("Biome IDs", "Dead Forest ID", 210).getInt();
			deadSwampID = config.get("Biome IDs", "Dead Swamp ID", 211).getInt();
			deciduousForestID = config.get("Biome IDs", "Deciduous Forest ID", 212).getInt();
			dunesID = config.get("Biome IDs", "Dunes ID", 213).getInt();
			fenID = config.get("Biome IDs", "Fen ID", 214).getInt();
			flowerFieldID = config.get("Biome IDs", "Flower Field ID", 215).getInt();
			frostForestID = config.get("Biome IDs", "Frost Forest ID", 216).getInt();
			grasslandID = config.get("Biome IDs", "Grassland ID", 217).getInt();
			groveID = config.get("Biome IDs", "Grove ID", 218).getInt();
			heathlandID = config.get("Biome IDs", "Heathland ID", 219).getInt();
			highlandID = config.get("Biome IDs", "Highland ID", 220).getInt();
			jadeCliffsID = config.get("Biome IDs", "Jade Cliffs ID", 221).getInt();
			lavenderFieldsID = config.get("Biome IDs", "Lavender Fields ID", 222).getInt();
			lushDesertID = config.get("Biome IDs", "Lush Desert ID", 223).getInt();
			lushSwampID = config.get("Biome IDs", "Lush Swamp ID", 224).getInt();
			mapleWoodsID = config.get("Biome IDs", "Maple Woods ID", 225).getInt();
			marshID = config.get("Biome IDs", "Marsh ID", 226).getInt();
			meadowID = config.get("Biome IDs", "Meadow ID", 227).getInt();
			moorID = config.get("Biome IDs", "Moor ID", 228).getInt();
			mountainID = config.get("Biome IDs", "Mountain ID", 229).getInt();
			mysticGroveID = config.get("Biome IDs", "Mystic Grove ID", 230).getInt();
			ominousWoodsID = config.get("Biome IDs", "Ominous Woods ID", 231).getInt();
			originValleyID = config.get("Biome IDs", "Origin Valley ID", 232).getInt();
			outbackID = config.get("Biome IDs", "Outback ID", 233).getInt();
			prairieID = config.get("Biome IDs", "Prairie ID", 234).getInt();
			quagmireID = config.get("Biome IDs", "Quagmire ID", 235).getInt();
			rainforestID = config.get("Biome IDs", "Rainforest ID", 236).getInt();
			redwoodForestID = config.get("Biome IDs", "Redwood Forest ID", 237).getInt();
			sacredSpringsID = config.get("Biome IDs", "Sacred Springs ID", 238).getInt();
			seasonalForestID = config.get("Biome IDs", "Seasonal Forest ID", 239).getInt();
			shieldID = config.get("Biome IDs", "Shield ID", 240).getInt();
			shrublandID = config.get("Biome IDs", "Shrubland ID", 241).getInt();
			silkgladesID = config.get("Biome IDs", "Silkglades ID", 242).getInt();
			sludgepitID = config.get("Biome IDs", "Sludgepit ID", 243).getInt();
			spruceWoodsID = config.get("Biome IDs", "Spruce Woods ID", 244).getInt();
			temperateRainforestID = config.get("Biome IDs", "Temperate Rainforest ID", 245).getInt();
			thicketID = config.get("Biome IDs", "Thicket ID", 246).getInt();
			timberID = config.get("Biome IDs", "Timber ID", 247).getInt();
			tropicalRainforestID = config.get("Biome IDs", "Tropical Rainforest ID", 248).getInt();
			tropicsID = config.get("Biome IDs", "Tropics ID", 249).getInt();
			tundraID = config.get("Biome IDs", "Tundra ID", 250).getInt();
			volcanoID = config.get("Biome IDs", "Volcano ID", 251).getInt();
			wastelandID = config.get("Biome IDs", "Wasteland ID", 252).getInt();
			wetlandID = config.get("Biome IDs", "Wetland ID", 253).getInt();
			woodlandID = config.get("Biome IDs", "Woodland ID", 254).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "Biomes O Plenty has had a problem loading its configuration");
		}
		finally
		{
			if (config.hasChanged()) 
			{
				config.save();
			}
		}
	}
}
