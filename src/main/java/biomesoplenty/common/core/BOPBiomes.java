package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCBiomes.alps;
import static biomesoplenty.api.content.BOPCBiomes.alpsForest;
import static biomesoplenty.api.content.BOPCBiomes.arctic;
import static biomesoplenty.api.content.BOPCBiomes.bambooForest;
import static biomesoplenty.api.content.BOPCBiomes.bayou;
import static biomesoplenty.api.content.BOPCBiomes.bog;
import static biomesoplenty.api.content.BOPCBiomes.boneyard;
import static biomesoplenty.api.content.BOPCBiomes.borealForest;
import static biomesoplenty.api.content.BOPCBiomes.brushland;
import static biomesoplenty.api.content.BOPCBiomes.canyon;
import static biomesoplenty.api.content.BOPCBiomes.canyonRavine;
import static biomesoplenty.api.content.BOPCBiomes.chaparral;
import static biomesoplenty.api.content.BOPCBiomes.cherryBlossomGrove;
import static biomesoplenty.api.content.BOPCBiomes.coniferousForest;
import static biomesoplenty.api.content.BOPCBiomes.corruptedSands;
import static biomesoplenty.api.content.BOPCBiomes.crag;
import static biomesoplenty.api.content.BOPCBiomes.deadForest;
import static biomesoplenty.api.content.BOPCBiomes.deadSwamp;
import static biomesoplenty.api.content.BOPCBiomes.deciduousForest;
import static biomesoplenty.api.content.BOPCBiomes.dryRiver;
import static biomesoplenty.api.content.BOPCBiomes.dunes;
import static biomesoplenty.api.content.BOPCBiomes.fen;
import static biomesoplenty.api.content.BOPCBiomes.flowerField;
import static biomesoplenty.api.content.BOPCBiomes.frostForest;
import static biomesoplenty.api.content.BOPCBiomes.fungiForest;
import static biomesoplenty.api.content.BOPCBiomes.glacier;
import static biomesoplenty.api.content.BOPCBiomes.grassland;
import static biomesoplenty.api.content.BOPCBiomes.grove;
import static biomesoplenty.api.content.BOPCBiomes.heathland;
import static biomesoplenty.api.content.BOPCBiomes.highland;
import static biomesoplenty.api.content.BOPCBiomes.jadeCliffs;
import static biomesoplenty.api.content.BOPCBiomes.lavenderFields;
import static biomesoplenty.api.content.BOPCBiomes.lushDesert;
import static biomesoplenty.api.content.BOPCBiomes.lushRiver;
import static biomesoplenty.api.content.BOPCBiomes.lushSwamp;
import static biomesoplenty.api.content.BOPCBiomes.mapleWoods;
import static biomesoplenty.api.content.BOPCBiomes.marsh;
import static biomesoplenty.api.content.BOPCBiomes.meadow;
import static biomesoplenty.api.content.BOPCBiomes.meadowForest;
import static biomesoplenty.api.content.BOPCBiomes.moor;
import static biomesoplenty.api.content.BOPCBiomes.mountain;
import static biomesoplenty.api.content.BOPCBiomes.mysticGrove;
import static biomesoplenty.api.content.BOPCBiomes.oasis;
import static biomesoplenty.api.content.BOPCBiomes.ominousWoods;
import static biomesoplenty.api.content.BOPCBiomes.originValley;
import static biomesoplenty.api.content.BOPCBiomes.outback;
import static biomesoplenty.api.content.BOPCBiomes.phantasmagoricInferno;
import static biomesoplenty.api.content.BOPCBiomes.prairie;
import static biomesoplenty.api.content.BOPCBiomes.quagmire;
import static biomesoplenty.api.content.BOPCBiomes.rainforest;
import static biomesoplenty.api.content.BOPCBiomes.redwoodForest;
import static biomesoplenty.api.content.BOPCBiomes.sacredSprings;
import static biomesoplenty.api.content.BOPCBiomes.scrubland;
import static biomesoplenty.api.content.BOPCBiomes.seasonalForest;
import static biomesoplenty.api.content.BOPCBiomes.shield;
import static biomesoplenty.api.content.BOPCBiomes.shrubland;
import static biomesoplenty.api.content.BOPCBiomes.silkglades;
import static biomesoplenty.api.content.BOPCBiomes.sludgepit;
import static biomesoplenty.api.content.BOPCBiomes.snowyConiferousForest;
import static biomesoplenty.api.content.BOPCBiomes.spruceWoods;
import static biomesoplenty.api.content.BOPCBiomes.steppe;
import static biomesoplenty.api.content.BOPCBiomes.temperateRainforest;
import static biomesoplenty.api.content.BOPCBiomes.thicket;
import static biomesoplenty.api.content.BOPCBiomes.tropicalRainforest;
import static biomesoplenty.api.content.BOPCBiomes.tropics;
import static biomesoplenty.api.content.BOPCBiomes.tundra;
import static biomesoplenty.api.content.BOPCBiomes.undergarden;
import static biomesoplenty.api.content.BOPCBiomes.visceralHeap;
import static biomesoplenty.api.content.BOPCBiomes.volcano;
import static biomesoplenty.api.content.BOPCBiomes.wasteland;
import static biomesoplenty.api.content.BOPCBiomes.wetland;
import static biomesoplenty.api.content.BOPCBiomes.woodland;

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
import biomesoplenty.common.biomes.nether.BiomeGenUndergarden;
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
import biomesoplenty.common.biomes.overworld.BiomeGenFungiForest;
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
import biomesoplenty.common.biomes.overworld.BiomeGenRainforest;
import biomesoplenty.common.biomes.overworld.BiomeGenRedwoodForest;
import biomesoplenty.common.biomes.overworld.BiomeGenSacredSprings;
import biomesoplenty.common.biomes.overworld.BiomeGenSeasonalForest;
import biomesoplenty.common.biomes.overworld.BiomeGenShield;
import biomesoplenty.common.biomes.overworld.BiomeGenShrubland;
import biomesoplenty.common.biomes.overworld.BiomeGenSludgepit;
import biomesoplenty.common.biomes.overworld.BiomeGenSteppe;
import biomesoplenty.common.biomes.overworld.BiomeGenTemperateRainforest;
import biomesoplenty.common.biomes.overworld.BiomeGenThicket;
import biomesoplenty.common.biomes.overworld.BiomeGenTropicalRainforest;
import biomesoplenty.common.biomes.overworld.BiomeGenTundra;
import biomesoplenty.common.biomes.overworld.BiomeGenWasteland;
import biomesoplenty.common.biomes.overworld.BiomeGenWetland;
import biomesoplenty.common.biomes.overworld.BiomeGenWoodland;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenAlpsForest;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenCanyonRavine;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenGlacier;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenMeadowForest;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenOasis;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenQuagmire;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenScrubland;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenSilkglades;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenSpruceWoods;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenTropics;
import biomesoplenty.common.biomes.overworld.sub.BiomeGenVolcano;
import biomesoplenty.common.biomes.overworld.tech.BiomeGenDryRiver;
import biomesoplenty.common.biomes.overworld.tech.BiomeGenLushRiver;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.configuration.BOPConfigurationBiomeWeights;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.utils.BOPLogger;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBiomes
{
	public static WorldTypeBOP worldTypeBOP;
	
	public static BiomeGenBase onlyBiome = null;

	public static void init()
	{
        GameRegistry.registerWorldGenerator(new BOPDecorationManager(), 0);

        try
        {
        	BOPConfigurationIDs.config.load();
        	BOPConfigurationBiomeGen.config.load();
        	BOPConfigurationBiomeWeights.config.load();
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
        	if (BOPConfigurationBiomeWeights.config.hasChanged()) BOPConfigurationBiomeWeights.config.save();
        }

		addBiomesToDictionary();
		disableRivers();
		addSpawnBiomes();
	}
	
	private static void registerBiomes()
	{
		alps = registerOverworldBiome(BiomeGenAlps.class, "Alps", TemperatureType.ICY, 5);
		arctic = registerOverworldBiome(BiomeGenArctic.class, "Arctic", TemperatureType.ICY, 10);
        bambooForest = registerOverworldBiome(BiomeGenBambooForest.class, "Bamboo Forest", TemperatureType.HOT, 5);
        bayou = registerOverworldBiome(BiomeGenBayou.class, "Bayou", TemperatureType.WARM, 10);
        bog = registerOverworldBiome(BiomeGenBog.class, "Bog", TemperatureType.WARM, 7);
        borealForest = registerOverworldBiome(BiomeGenBorealForest.class, "Boreal Forest", TemperatureType.WARM, 10);
        brushland = registerOverworldBiome(BiomeGenBrushland.class, "Brushland", TemperatureType.HOT, 10);
        canyon = registerOverworldBiome(BiomeGenCanyon.class, "Canyon", TemperatureType.HOT, 7);
        chaparral = registerOverworldBiome(BiomeGenChaparral.class, "Chaparral", TemperatureType.WARM, 10);
        cherryBlossomGrove = registerOverworldBiome(BiomeGenCherryBlossomGrove.class, "Cherry Blossom Grove", TemperatureType.COOL, 3);
        coniferousForest = registerOverworldBiome(BiomeGenConiferousForest.class, "Coniferous Forest", TemperatureType.WARM, 10);
        snowyConiferousForest = registerOverworldBiome(BiomeGenConiferousForestSnow.class, "Snowy Coniferous Forest", TemperatureType.ICY, 10);
        crag = registerOverworldBiome(BiomeGenCrag.class, "Crag", TemperatureType.COOL, 3);
        deadForest = registerOverworldBiome(BiomeGenDeadForest.class, "Dead Forest", TemperatureType.COOL, 7);
        deadSwamp = registerOverworldBiome(BiomeGenDeadSwamp.class, "Dead Swamp", TemperatureType.WARM, 7);
        deciduousForest = registerOverworldBiome(BiomeGenDeciduousForest.class, "Deciduous Forest", TemperatureType.WARM, 10);
        dunes = registerOverworldBiome(BiomeGenDunes.class, "Dunes", TemperatureType.HOT, 10);
        fen = registerOverworldBiome(BiomeGenFen.class, "Fen", TemperatureType.WARM, 10);
        flowerField = registerOverworldBiome(BiomeGenFlowerField.class, "Flower Field", TemperatureType.WARM, 3);
        frostForest = registerOverworldBiome(BiomeGenFrostForest.class, "Frost Forest", TemperatureType.ICY, 7);
        fungiForest = registerOverworldBiome(BiomeGenFungiForest.class, "Fungi Forest", TemperatureType.COOL, 3);
        grassland = registerOverworldBiome(BiomeGenGrassland.class, "Grassland", TemperatureType.COOL, 10);
        grove = registerOverworldBiome(BiomeGenGrove.class, "Grove", TemperatureType.COOL, 5);
        heathland = registerOverworldBiome(BiomeGenHeathland.class, "Heathland", TemperatureType.WARM, 10);
        highland = registerOverworldBiome(BiomeGenHighland.class, "Highland", TemperatureType.WARM, 10);
        jadeCliffs = registerOverworldBiome(BiomeGenJadeCliffs.class, "Jade Cliffs", TemperatureType.WARM, 5);
        lavenderFields = registerOverworldBiome(BiomeGenLavenderFields.class, "Lavender Fields", TemperatureType.WARM, 3);
        lushDesert = registerOverworldBiome(BiomeGenLushDesert.class, "Lush Desert", TemperatureType.HOT, 5);
        lushSwamp = registerOverworldBiome(BiomeGenLushSwamp.class, "Lush Swamp", TemperatureType.WARM, 10);
        mapleWoods = registerOverworldBiome(BiomeGenMapleWoods.class, "Maple Woods", TemperatureType.COOL, 10);
        marsh = registerOverworldBiome(BiomeGenMarsh.class, "Marsh", TemperatureType.WARM, 7);
        meadow = registerOverworldBiome(BiomeGenMeadow.class, "Meadow", TemperatureType.COOL, 10);
        moor = registerOverworldBiome(BiomeGenMoor.class, "Moor", TemperatureType.COOL, 10);
        mountain = registerOverworldBiome(BiomeGenMountain.class, "Mountain", TemperatureType.WARM, 10);
        mysticGrove = registerOverworldBiome(BiomeGenMysticGrove.class, "Mystic Grove", TemperatureType.WARM, 3);
        ominousWoods = registerOverworldBiome(BiomeGenOminousWoods.class, "Ominous Woods", TemperatureType.COOL, 3);
        originValley = registerOverworldBiome(BiomeGenOriginValley.class, "Origin Valley", TemperatureType.WARM, 1);
        outback = registerOverworldBiome(BiomeGenOutback.class, "Outback", TemperatureType.HOT, 7);
        prairie = registerOverworldBiome(BiomeGenPrairie.class, "Prairie", TemperatureType.WARM, 10);
        rainforest = registerOverworldBiome(BiomeGenRainforest.class, "Rainforest", TemperatureType.WARM, 5);
        redwoodForest = registerOverworldBiome(BiomeGenRedwoodForest.class, "Redwood Forest", TemperatureType.WARM, 7);
        sacredSprings = registerOverworldBiome(BiomeGenSacredSprings.class, "Sacred Springs", TemperatureType.WARM, 3);
        seasonalForest = registerOverworldBiome(BiomeGenSeasonalForest.class, "Seasonal Forest", TemperatureType.COOL, 10);
        shield = registerOverworldBiome(BiomeGenShield.class, "Shield", TemperatureType.COOL, 7);
        shrubland = registerOverworldBiome(BiomeGenShrubland.class, "Shrubland", TemperatureType.COOL, 10);
        sludgepit = registerOverworldBiome(BiomeGenSludgepit.class, "Sludgepit", TemperatureType.WARM, 5);
        steppe = registerOverworldBiome(BiomeGenSteppe.class, "Steppe", TemperatureType.HOT, 7);
        temperateRainforest = registerOverworldBiome(BiomeGenTemperateRainforest.class, "Temperate Rainforest", TemperatureType.WARM, 10);
        thicket = registerOverworldBiome(BiomeGenThicket.class, "Thicket", TemperatureType.COOL, 5);
        tropicalRainforest = registerOverworldBiome(BiomeGenTropicalRainforest.class, "Tropical Rainforest", TemperatureType.HOT, 5);
        tundra = registerOverworldBiome(BiomeGenTundra.class, "Tundra", TemperatureType.ICY, 7);
        wasteland = registerOverworldBiome(BiomeGenWasteland.class, "Wasteland", TemperatureType.HOT, 3);
        wetland = registerOverworldBiome(BiomeGenWetland.class, "Wetland", TemperatureType.WARM, 7);
        woodland = registerOverworldBiome(BiomeGenWoodland.class, "Woodland", TemperatureType.WARM, 10);
        
        //Sub Biomes
		glacier = registerOverworldSubBiome(BiomeGenGlacier.class, "Glacier", 10, arctic.biomeID);
		scrubland = registerOverworldSubBiome(BiomeGenScrubland.class, "Scrubland", 10, BiomeGenBase.savanna.biomeID);
		oasis = registerOverworldSubBiome(BiomeGenOasis.class, "Oasis", 10, BiomeGenBase.desert.biomeID);
		quagmire = registerOverworldSubBiome(BiomeGenQuagmire.class, "Quagmire", 10, sludgepit.biomeID);
		silkglades = registerOverworldSubBiome(BiomeGenSilkglades.class, "Silkglades", 10, sludgepit.biomeID);
		meadowForest = registerOverworldSubBiome(BiomeGenMeadowForest.class, "Meadow Forest", 10, meadow.biomeID);
		alpsForest = registerOverworldSubBiome(BiomeGenAlpsForest.class, "Alps Forest", 10, alps.biomeID);
		canyonRavine = registerOverworldSubBiome(BiomeGenCanyonRavine.class, "Canyon Ravine", 10, canyon.biomeID);
		spruceWoods = registerOverworldSubBiome(BiomeGenSpruceWoods.class, "Spruce Woods", 10, BiomeGenBase.forest.biomeID);
		
		//Ocean Biomes
		volcano = registerOverworldSubBiome(BiomeGenVolcano.class, "Volcano", 10, BiomeGenBase.deepOcean.biomeID);
		tropics = registerOverworldSubBiome(BiomeGenTropics.class, "Tropics", 10, BiomeGenBase.deepOcean.biomeID);
        
        //Nether Biomes
        corruptedSands = registerNetherBiome(BiomeGenCorruptedSands.class, "Corrupted Sands", 10);
        phantasmagoricInferno = registerNetherBiome(BiomeGenPhantasmagoricInferno.class, "Phantasmagoric Inferno", 10);
        boneyard = registerNetherBiome(BiomeGenBoneyard.class, "Boneyard", 10);
        visceralHeap = registerNetherBiome(BiomeGenVisceralHeap.class, "Visceral Heap", 10);
        undergarden = registerNetherBiome(BiomeGenUndergarden.class, "Undergarden", 10);
        
        //River Biomes
        lushRiver = registerOverworldRiverBiome(BiomeGenLushRiver.class, "Lush River", lushSwamp.biomeID, lavenderFields.biomeID, flowerField.biomeID, bambooForest.biomeID, cherryBlossomGrove.biomeID, lushDesert.biomeID, meadow.biomeID, spruceWoods.biomeID, rainforest.biomeID, BiomeGenBase.forest.biomeID, BiomeGenBase.forestHills.biomeID, BiomeGenBase.jungle.biomeID, BiomeGenBase.jungleEdge.biomeID, BiomeGenBase.jungleHills.biomeID);
        dryRiver = registerOverworldRiverBiome(BiomeGenDryRiver.class, "Dry River", outback.biomeID, dunes.biomeID, steppe.biomeID, BiomeGenBase.desert.biomeID, BiomeGenBase.desertHills.biomeID);
	}
	
	private static void addBiomesToDictionary()
	{
        BiomeDictionary.registerBiomeType(BOPCBiomes.alps, Type.FROZEN, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.alpsForest, Type.FROZEN, Type.MOUNTAIN, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.arctic, Type.FROZEN, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bambooForest, Type.JUNGLE, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bayou, Type.SWAMP, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("beachGravel, Type.BEACH);
        BiomeDictionary.registerBiomeType(BOPCBiomes.bog, Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.boneyard, Type.NETHER, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.borealForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.brushland, Type.DESERT, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.canyon, Type.DESERT, Type.MOUNTAIN, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.canyonRavine, Type.DESERT, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.chaparral, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.cherryBlossomGrove, Type.MAGICAL, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.coniferousForest, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.snowyConiferousForest, Type.FROZEN, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.corruptedSands, Type.NETHER, Type.DESERT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.crag, Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deadForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deadSwamp, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.deciduousForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.dunes, Type.BEACH, Type.DESERT, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.fen, Type.FOREST, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.flowerField, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.frostForest, Type.FROZEN, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.fungiForest, Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("garden, Type.MAGICAL, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.glacier, Type.FROZEN, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.grassland, Type.PLAINS, Type.SWAMP, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.grove, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.heathland, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.highland, Type.HILLS, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.jadeCliffs, Type.FOREST, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lavenderFields, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lushDesert, Type.DESERT, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.lushSwamp, Type.SWAMP, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mangrove, Type.WATER, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mapleWoods, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.marsh, Type.SWAMP, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.meadow, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.meadowForest, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.moor, Type.HILLS, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mountain, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPCBiomes.mysticGrove, Type.MAGICAL, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.oasis, Type.DESERT, Type.JUNGLE);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanAbyss, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanCoral, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanKelp, Type.WATER, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.ominousWoods, Type.MAGICAL);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("orchard, Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.outback, Type.DESERT, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.phantasmagoricInferno, Type.NETHER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.prairie, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.quagmire, Type.SWAMP, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.rainforest, Type.JUNGLE, Type.HILLS, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.redwoodForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.sacredSprings, Type.MOUNTAIN, Type.FOREST, Type.MAGICAL);
        BiomeDictionary.registerBiomeType(BOPCBiomes.scrubland, Type.DESERT, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.seasonalForest, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.shield, Type.FOREST, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.shrubland, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.silkglades, Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.sludgepit, Type.SWAMP, Type.FOREST, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.spruceWoods, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.steppe, Type.PLAINS, Type.DESERT);
        BiomeDictionary.registerBiomeType(BOPCBiomes.temperateRainforest, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPCBiomes.thicket, Type.PLAINS, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.tropicalRainforest, Type.JUNGLE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.tropics, Type.JUNGLE, Type.BEACH, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.tundra, Type.FROZEN, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.undergarden, Type.NETHER, Type.JUNGLE);
        BiomeDictionary.registerBiomeType(BOPCBiomes.visceralHeap, Type.NETHER);
        BiomeDictionary.registerBiomeType(BOPCBiomes.volcano, Type.MOUNTAIN, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.wasteland, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPCBiomes.wetland, Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPCBiomes.woodland, Type.FOREST);
	}
	
	private static void disableRivers()
	{
		disableRiver(quagmire);
		disableRiver(sludgepit);
		disableRiver(silkglades);
		disableRiver(wetland);
		disableRiver(wasteland);
		disableRiver(tropicalRainforest);
		disableRiver(originValley);
		disableRiver(ominousWoods);
		disableRiver(mysticGrove);
		disableRiver(deadSwamp);
		disableRiver(crag);
		disableRiver(bayou);
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
	
	private static BiomeGenBase registerOverworldRiverBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int...parents)
	{
		BiomeGenBase biome = BOPBiomeManager.createBiome(biomeClass, biomeName);
		
		if (biome != null)
		{
			if (BOPConfigurationBiomeGen.config.get("Overworld (River) Biomes To Generate", biome.biomeName, true).getBoolean(false))
			{
				for (int parent : parents)
				{
					BOPBiomeManager.overworldRiverBiomes[parent] = biome;
				}
			}
		}
		
		return null;
	}
	
	private static void disableRiver(BiomeGenBase biome)
	{
		BOPBiomeManager.overworldRiverBiomes[biome.biomeID] = biome;
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
