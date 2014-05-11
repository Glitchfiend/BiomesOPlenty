package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCBiomes.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPBiomeManager;
import biomesoplenty.api.BOPBiomeManager.BiomeEntry;
import biomesoplenty.api.BOPBiomeManager.TemperatureType;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.common.biomes.nether.BiomeGenBoneyard;
import biomesoplenty.common.biomes.nether.BiomeGenCorruptedSands;
import biomesoplenty.common.biomes.nether.BiomeGenPhantasmagoricInferno;
import biomesoplenty.common.biomes.nether.BiomeGenVisceralHeap;
import biomesoplenty.common.biomes.overworld.BiomeGenAlps;
import biomesoplenty.common.biomes.overworld.BiomeGenArctic;
import biomesoplenty.common.biomes.overworld.BiomeGenBambooForest;
import biomesoplenty.common.biomes.overworld.BiomeGenBayou;
import biomesoplenty.common.biomes.overworld.BiomeGenBog;
import biomesoplenty.common.biomes.overworld.BiomeGenBorealForest;
import biomesoplenty.common.biomes.overworld.BiomeGenBrushland;
import biomesoplenty.common.biomes.overworld.BiomeGenCanyon;
import biomesoplenty.common.biomes.overworld.BiomeGenChaparral;
import biomesoplenty.common.biomes.overworld.BiomeGenCherryBlossomGrove;
import biomesoplenty.common.biomes.overworld.BiomeGenConiferousForest;
import biomesoplenty.common.biomes.overworld.BiomeGenConiferousForestSnow;
import biomesoplenty.common.biomes.overworld.BiomeGenCrag;
import biomesoplenty.common.biomes.overworld.BiomeGenDeadForest;
import biomesoplenty.common.biomes.overworld.BiomeGenDeadSwamp;
import biomesoplenty.common.biomes.overworld.BiomeGenDeciduousForest;
import biomesoplenty.common.biomes.overworld.BiomeGenDunes;
import biomesoplenty.common.biomes.overworld.BiomeGenFen;
import biomesoplenty.common.biomes.overworld.BiomeGenFlowerField;
import biomesoplenty.common.biomes.overworld.BiomeGenFrostForest;
import biomesoplenty.common.biomes.overworld.BiomeGenGrassland;
import biomesoplenty.common.biomes.overworld.BiomeGenGrove;
import biomesoplenty.common.biomes.overworld.BiomeGenHeathland;
import biomesoplenty.common.biomes.overworld.BiomeGenHighland;
import biomesoplenty.common.biomes.overworld.BiomeGenJadeCliffs;
import biomesoplenty.common.biomes.overworld.BiomeGenLavenderFields;
import biomesoplenty.common.biomes.overworld.BiomeGenLushDesert;
import biomesoplenty.common.biomes.overworld.BiomeGenLushSwamp;
import biomesoplenty.common.biomes.overworld.BiomeGenMapleWoods;
import biomesoplenty.common.biomes.overworld.BiomeGenMarsh;
import biomesoplenty.common.biomes.overworld.BiomeGenMeadow;
import biomesoplenty.common.biomes.overworld.BiomeGenMoor;
import biomesoplenty.common.biomes.overworld.BiomeGenMountain;
import biomesoplenty.common.biomes.overworld.BiomeGenMysticGrove;
import biomesoplenty.common.biomes.overworld.BiomeGenOminousWoods;
import biomesoplenty.common.biomes.overworld.BiomeGenOriginValley;
import biomesoplenty.common.biomes.overworld.BiomeGenOutback;
import biomesoplenty.common.biomes.overworld.BiomeGenPrairie;
import biomesoplenty.common.biomes.overworld.BiomeGenQuagmire;
import biomesoplenty.common.biomes.overworld.BiomeGenRainforest;
import biomesoplenty.common.biomes.overworld.BiomeGenRedwoodForest;
import biomesoplenty.common.biomes.overworld.BiomeGenSacredSprings;
import biomesoplenty.common.biomes.overworld.BiomeGenSeasonalForest;
import biomesoplenty.common.biomes.overworld.BiomeGenShield;
import biomesoplenty.common.biomes.overworld.BiomeGenShrubland;
import biomesoplenty.common.biomes.overworld.BiomeGenSilkglades;
import biomesoplenty.common.biomes.overworld.BiomeGenSludgepit;
import biomesoplenty.common.biomes.overworld.BiomeGenSpruceWoods;
import biomesoplenty.common.biomes.overworld.BiomeGenSteppe;
import biomesoplenty.common.biomes.overworld.BiomeGenTemperateRainforest;
import biomesoplenty.common.biomes.overworld.BiomeGenThicket;
import biomesoplenty.common.biomes.overworld.BiomeGenTimber;
import biomesoplenty.common.biomes.overworld.BiomeGenTropicalRainforest;
import biomesoplenty.common.biomes.overworld.BiomeGenTropics;
import biomesoplenty.common.biomes.overworld.BiomeGenTundra;
import biomesoplenty.common.biomes.overworld.BiomeGenVolcano;
import biomesoplenty.common.biomes.overworld.BiomeGenWasteland;
import biomesoplenty.common.biomes.overworld.BiomeGenWetland;
import biomesoplenty.common.biomes.overworld.BiomeGenWoodland;
import biomesoplenty.common.biomes.overworld.subbiomes.BiomeGenGlacier;
import biomesoplenty.common.biomes.overworld.subbiomes.BiomeGenOasis;
import biomesoplenty.common.biomes.overworld.subbiomes.BiomeGenScrubland;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.utils.BOPLogger;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBiomes
{
	public static WorldTypeBOP worldTypeBOP;

	public static void init()
	{
        GameRegistry.registerWorldGenerator(new BOPDecorationManager(), 0);

        try
        {
        	BOPConfigurationIDs.config.load();
        	BOPConfigurationBiomeGen.config.load();
        	registerBiomes();
        }
        catch (Exception e)
        {
        	BOPLogger.log(Level.ERROR, "Biomes O Plenty has had a problem loading its configuration", e);
        }
        finally
        {
        	if (BOPConfigurationIDs.config.hasChanged()) BOPConfigurationIDs.config.save();
        	if (BOPConfigurationBiomeGen.config.hasChanged()) BOPConfigurationBiomeGen.config.save();
        }

		addBiomesToDictionary();
		addSpawnBiomes();
	}
	
	private static void registerBiomes()
	{
		alps = registerOverworldBiome(BiomeGenAlps.class, "Alps", TemperatureType.ICY, 5);
		arctic = registerOverworldBiome(BiomeGenArctic.class, "Arctic", TemperatureType.ICY, 10);
        bambooForest = registerOverworldBiome(BiomeGenBambooForest.class, "Bamboo Forest", TemperatureType.HOT, 5);
        bayou = registerOverworldBiome(BiomeGenBayou.class, "Bayou", TemperatureType.WARM, 10);
        bog = registerOverworldBiome(BiomeGenBog.class, "Bog", TemperatureType.WARM, 10);
        borealForest = registerOverworldBiome(BiomeGenBorealForest.class, "Boreal Forest", TemperatureType.WARM, 10);
        brushland = registerOverworldBiome(BiomeGenBrushland.class, "Brushland", TemperatureType.HOT, 10);
        canyon = registerOverworldBiome(BiomeGenCanyon.class, "Canyon", TemperatureType.HOT, 10);
        chaparral = registerOverworldBiome(BiomeGenChaparral.class, "Chaparral", TemperatureType.WARM, 10);
        cherryBlossomGrove = registerOverworldBiome(BiomeGenCherryBlossomGrove.class, "Cherry Blossom Grove", TemperatureType.COOL, 3);
        coniferousForest = registerOverworldBiome(BiomeGenConiferousForest.class, "Coniferous Forest", TemperatureType.WARM, 10);
        snowyConiferousForest = registerOverworldBiome(BiomeGenConiferousForestSnow.class, "Snowy Coniferous Forest", TemperatureType.ICY, 10);
        crag = registerOverworldBiome(BiomeGenCrag.class, "Crag", TemperatureType.COOL, 3);
        deadForest = registerOverworldBiome(BiomeGenDeadForest.class, "Dead Forest", TemperatureType.COOL, 10);
        deadSwamp = registerOverworldBiome(BiomeGenDeadSwamp.class, "Dead Swamp", TemperatureType.WARM, 10);
        deciduousForest = registerOverworldBiome(BiomeGenDeciduousForest.class, "Deciduous Forest", TemperatureType.WARM, 10);
        dunes = registerOverworldBiome(BiomeGenDunes.class, "Dunes", TemperatureType.HOT, 10);
        fen = registerOverworldBiome(BiomeGenFen.class, "Fen", TemperatureType.WARM, 10);
        flowerField = registerOverworldBiome(BiomeGenFlowerField.class, "Flower Field", TemperatureType.WARM, 3);
        frostForest = registerOverworldBiome(BiomeGenFrostForest.class, "Frost Forest", TemperatureType.ICY, 10);
        grassland = registerOverworldBiome(BiomeGenGrassland.class, "Grassland", TemperatureType.COOL, 10);
        grove = registerOverworldBiome(BiomeGenGrove.class, "Grove", TemperatureType.COOL, 5);
        heathland = registerOverworldBiome(BiomeGenHeathland.class, "Heathland", TemperatureType.WARM, 10);
        highland = registerOverworldBiome(BiomeGenHighland.class, "Highland", TemperatureType.WARM, 10);
        jadeCliffs = registerOverworldBiome(BiomeGenJadeCliffs.class, "Jade Cliffs", TemperatureType.WARM, 5);
        lavenderFields = registerOverworldBiome(BiomeGenLavenderFields.class, "Lavender Fields", TemperatureType.WARM, 3);
        lushDesert = registerOverworldBiome(BiomeGenLushDesert.class, "Lush Desert", TemperatureType.HOT, 5);
        lushSwamp = registerOverworldBiome(BiomeGenLushSwamp.class, "Lush Swamp", TemperatureType.WARM, 10);
        mapleWoods = registerOverworldBiome(BiomeGenMapleWoods.class, "Maple Woods", TemperatureType.COOL, 10);
        marsh = registerOverworldBiome(BiomeGenMarsh.class, "Marsh", TemperatureType.WARM, 10);
        meadow = registerOverworldBiome(BiomeGenMeadow.class, "Meadow", TemperatureType.COOL, 10);
        moor = registerOverworldBiome(BiomeGenMoor.class, "Moor", TemperatureType.COOL, 10);
        mountain = registerOverworldBiome(BiomeGenMountain.class, "Mountain", TemperatureType.WARM, 10);
        mysticGrove = registerOverworldBiome(BiomeGenMysticGrove.class, "Mystic Grove", TemperatureType.WARM, 3);
        ominousWoods = registerOverworldBiome(BiomeGenOminousWoods.class, "Ominous Woods", TemperatureType.COOL, 3);
        originValley = registerOverworldBiome(BiomeGenOriginValley.class, "Origin Valley", TemperatureType.WARM, 1);
        outback = registerOverworldBiome(BiomeGenOutback.class, "Outback", TemperatureType.HOT, 10);
        prairie = registerOverworldBiome(BiomeGenPrairie.class, "Prairie", TemperatureType.WARM, 10);
        quagmire = registerOverworldBiome(BiomeGenQuagmire.class, "Quagmire", TemperatureType.WARM, 10);
        rainforest = registerOverworldBiome(BiomeGenRainforest.class, "Rainforest", TemperatureType.WARM, 5);
        redwoodForest = registerOverworldBiome(BiomeGenRedwoodForest.class, "Redwood Forest", TemperatureType.WARM, 10);
        sacredSprings = registerOverworldBiome(BiomeGenSacredSprings.class, "Sacred Springs", TemperatureType.WARM, 3);
        seasonalForest = registerOverworldBiome(BiomeGenSeasonalForest.class, "Seasonal Forest", TemperatureType.COOL, 10);
        shield = registerOverworldBiome(BiomeGenShield.class, "Shield", TemperatureType.COOL, 10);
        shrubland = registerOverworldBiome(BiomeGenShrubland.class, "Shrubland", TemperatureType.COOL, 10);
        silkglades = registerOverworldBiome(BiomeGenSilkglades.class, "Silkglades", TemperatureType.COOL, 5);
        sludgepit = registerOverworldBiome(BiomeGenSludgepit.class, "Sludgepit", TemperatureType.WARM, 10);
        spruceWoods = registerOverworldBiome(BiomeGenSpruceWoods.class, "Spruce Woods", TemperatureType.WARM, 10);
        steppe = registerOverworldBiome(BiomeGenSteppe.class, "Steppe", TemperatureType.HOT, 10);
        temperateRainforest = registerOverworldBiome(BiomeGenTemperateRainforest.class, "Temperate Rainforest", TemperatureType.WARM, 10);
        thicket = registerOverworldBiome(BiomeGenThicket.class, "Thicket", TemperatureType.COOL, 5);
        timber = registerOverworldBiome(BiomeGenTimber.class, "Timber", TemperatureType.COOL, 5);
        tropicalRainforest = registerOverworldBiome(BiomeGenTropicalRainforest.class, "Tropical Rainforest", TemperatureType.HOT, 5);
        tropics = registerOverworldBiome(BiomeGenTropics.class, "Tropics", TemperatureType.HOT, 3);
        tundra = registerOverworldBiome(BiomeGenTundra.class, "Tundra", TemperatureType.ICY, 10);
        volcano = registerOverworldBiome(BiomeGenVolcano.class, "Volcano", TemperatureType.HOT, 5);
        wasteland = registerOverworldBiome(BiomeGenWasteland.class, "Wasteland", TemperatureType.HOT, 3);
        wetland = registerOverworldBiome(BiomeGenWetland.class, "Wetland", TemperatureType.WARM, 10);
        woodland = registerOverworldBiome(BiomeGenWoodland.class, "Woodland", TemperatureType.WARM, 10);
        
        //Sub Biomes
		glacier = registerOverworldSubBiome(BiomeGenGlacier.class, "Glacier", 10, arctic.biomeID);
		scrubland = registerOverworldSubBiome(BiomeGenScrubland.class, "Scrubland", 10, BiomeGenBase.savanna.biomeID);
		oasis = registerOverworldSubBiome(BiomeGenOasis.class, "Oasis", 10, BiomeGenBase.desert.biomeID);
        
        //Nether Biomes
        corruptedSands = registerNetherBiome(BiomeGenCorruptedSands.class, "Corrupted Sands", 10);
        //registerBiome(new BiomeEntry(new BiomeGenUndergarden(BOPConfigurationIDs.netherUndergardenID).setBiomeName("Undergarden"), 10), -1);
        phantasmagoricInferno = registerNetherBiome(BiomeGenPhantasmagoricInferno.class, "Phantasmagoric Inferno", 10);
        boneyard = registerNetherBiome(BiomeGenBoneyard.class, "Boneyard", 10);
        visceralHeap = registerNetherBiome(BiomeGenVisceralHeap.class, "Visceral Heap", 10);
	}

	private static void addSpawnBiomes()
	{
		if (BOPConfigurationMisc.onlySpawnOnBeaches)
		{
			clearAllSpawnBiomes();

			addSpawnBiome(BiomeGenBase.beach);
			addSpawnBiome(BiomeGenBase.stoneBeach);
			addSpawnBiome(BiomeGenBase.coldBeach);
		}
		else
		{
			for (List<BiomeEntry> biomeList : BOPBiomeManager.overworldBiomes)
			{
				for (BiomeEntry entry : biomeList)
				{
					addSpawnBiome(entry.biome);
				}
			}
		}
	}
	
	private static void addBiomesToDictionary()
	{
        BiomeDictionary.registerBiomeType(BOPCBiomes.alps, Type.FROZEN, Type.MOUNTAIN);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("alpsBase, Type.FROZEN, Type.MOUNTAIN, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("alpsForest, Type.FROZEN, Type.MOUNTAIN);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.arctic, Type.FROZEN, Type.WASTELAND);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("autumnHills, Type.FOREST, Type.HILLS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("badlands, Type.DESERT, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bambooForest, Type.JUNGLE, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bayou, Type.SWAMP, Type.WATER);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("beachGravel, Type.BEACH);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("beachOvergrown, Type.BEACH, Type.FOREST);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("birchForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bog, Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.borealForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.brushland, Type.DESERT, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.canyon, Type.DESERT, Type.MOUNTAIN, Type.HILLS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("canyonRavine, Type.DESERT, Type.HILLS);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.chaparral, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.cherryBlossomGrove, Type.MAGICAL, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.coniferousForest, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.snowyConiferousForest, Type.FROZEN, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.crag, Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deadForest, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("deadForestSnow, Type.FOREST, Type.FROZEN);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("deadlands, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deadSwamp, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deciduousForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.dunes, Type.BEACH, Type.DESERT, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.fen, Type.FOREST, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.flowerField, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("fieldForest, Type.PLAINS, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.frostForest, Type.FROZEN, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("fungiForest, Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("garden, Type.MAGICAL, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("glacier, Type.FROZEN, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.grassland, Type.PLAINS, Type.SWAMP, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.grove, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.heathland, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.highland, Type.HILLS, Type.MOUNTAIN);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("hotSprings, Type.HILLS, Type.FOREST, Type.WATER);
       // BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("icyHills, Type.FROZEN, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.jadeCliffs, Type.FOREST, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lavenderFields, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lushDesert, Type.DESERT, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lushSwamp, Type.SWAMP, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mangrove, Type.WATER, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mapleWoods, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.marsh, Type.SWAMP, Type.WATER);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.meadow, Type.FOREST, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("meadowForest, Type.FOREST, Type.PLAINS);
        
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mesa, Type.DESERT, Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.moor, Type.HILLS, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mountain, Type.MOUNTAIN);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.mysticGrove, Type.MAGICAL, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mysticGroveThin, Type.MAGICAL, Type.FOREST);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherBase, Type.NETHER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherGarden, Type.NETHER, Type.JUNGLE);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherDesert, Type.NETHER, Type.DESERT);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherLava, Type.NETHER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherBone, Type.NETHER, Type.WASTELAND);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherBlood, Type.NETHER);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oasis, Type.DESERT, Type.JUNGLE);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanAbyss, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanCoral, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanKelp, Type.WATER, Type.FOREST);

        BiomeDictionary.registerBiomeType(BOPCBiomes.ominousWoods, Type.MAGICAL);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("ominousWoodsThick, Type.MAGICAL);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("orchard, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.outback, Type.DESERT, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("overgrownGreens, Type.JUNGLE, Type.PLAINS);
        
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("polar, Type.FROZEN, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.prairie, Type.PLAINS);

        BiomeDictionary.registerBiomeType(BOPCBiomes.quagmire, Type.WASTELAND, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.rainforest, Type.JUNGLE, Type.HILLS, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.redwoodForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.sacredSprings, Type.MOUNTAIN, Type.FOREST, Type.MAGICAL);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("savanna, Type.DESERT, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("savannaPlateau, Type.DESERT, Type.PLAINS, Type.HILLS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("scrubland, Type.DESERT, Type.PLAINS);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.seasonalForest, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("seasonalSpruceForest, Type.FOREST);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.shield, Type.FOREST, Type.WATER);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.shrubland, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("shrublandForest, Type.PLAINS);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.silkglades, Type.SWAMP, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.sludgepit, Type.SWAMP, Type.FOREST, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.spruceWoods, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("steppe, Type.PLAINS, Type.DESERT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.temperateRainforest, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.thicket, Type.PLAINS, Type.FOREST);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.timber, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("timberThin, Type.FOREST);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.tropicalRainforest, Type.JUNGLE);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.tropics, Type.JUNGLE, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("tropicsMountain, Type.JUNGLE, Type.WATER);
        
        BiomeDictionary.registerBiomeType(BOPCBiomes.tundra, Type.FROZEN, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.volcano, Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.wasteland, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.wetland, Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.woodland, Type.FOREST);
	}
	
	private static BiomeGenBase registerOverworldBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int temperatureType, int weight)
	{
		if (BOPBiomeManager.overworldBiomes[temperatureType] == null) BOPBiomeManager.overworldBiomes[temperatureType] = new ArrayList();
		
		return BOPBiomeManager.createAndRegisterBiome(biomeClass, "Overworld", biomeName, BOPBiomeManager.overworldBiomes[temperatureType], weight);
	}
	
	private static BiomeGenBase registerOverworldSubBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int weight, int...parents)
	{
		BiomeGenBase biome = BOPBiomeManager.createBiome(biomeClass, biomeName);
		
		if (biome != null)
		{
			BiomeEntry entry = new BiomeEntry(biome, weight);

			if (BOPConfigurationBiomeGen.config.get("Overworld (Sub) Biomes To Generate", biome.biomeName, true).getBoolean(false))
			{
				for (int parent : parents)
				{
					if (BOPBiomeManager.overworldSubBiomes[parent] == null) BOPBiomeManager.overworldSubBiomes[parent] = new ArrayList();
					
					BOPBiomeManager.overworldSubBiomes[parent].add(entry);
				}
			}

			return biome;
		}
		
		return null;
	}
	
	private static BiomeGenBase registerNetherBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int weight)
	{
		return BOPBiomeManager.createAndRegisterBiome(biomeClass, "Nether", biomeName, BOPBiomeManager.netherBiomes, weight);
	}
	
	public static void addSpawnBiome(BiomeGenBase biome)
	{
	    BiomeManager.addSpawnBiome(biome);
	}
	
	public static void clearAllSpawnBiomes()
	{
	    WorldChunkManager.allowedBiomes.clear();
	}
}
