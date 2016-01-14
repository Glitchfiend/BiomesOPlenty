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
import static biomesoplenty.api.content.BOPCBiomes.coralReef;
import static biomesoplenty.api.content.BOPCBiomes.corruptedSands;
import static biomesoplenty.api.content.BOPCBiomes.crag;
import static biomesoplenty.api.content.BOPCBiomes.deadForest;
import static biomesoplenty.api.content.BOPCBiomes.deadSwamp;
import static biomesoplenty.api.content.BOPCBiomes.deciduousForest;
import static biomesoplenty.api.content.BOPCBiomes.denseForest;
import static biomesoplenty.api.content.BOPCBiomes.dryRiver;
import static biomesoplenty.api.content.BOPCBiomes.eucalyptusForest;
import static biomesoplenty.api.content.BOPCBiomes.fen;
import static biomesoplenty.api.content.BOPCBiomes.flowerField;
import static biomesoplenty.api.content.BOPCBiomes.frostForest;
import static biomesoplenty.api.content.BOPCBiomes.fungiForest;
import static biomesoplenty.api.content.BOPCBiomes.garden;
import static biomesoplenty.api.content.BOPCBiomes.glacier;
import static biomesoplenty.api.content.BOPCBiomes.grassland;
import static biomesoplenty.api.content.BOPCBiomes.grove;
import static biomesoplenty.api.content.BOPCBiomes.heathland;
import static biomesoplenty.api.content.BOPCBiomes.highland;
import static biomesoplenty.api.content.BOPCBiomes.jadeCliffs;
import static biomesoplenty.api.content.BOPCBiomes.kelpForest;
import static biomesoplenty.api.content.BOPCBiomes.landOfLakes;
import static biomesoplenty.api.content.BOPCBiomes.landOfLakesMarsh;
import static biomesoplenty.api.content.BOPCBiomes.lavenderFields;
import static biomesoplenty.api.content.BOPCBiomes.lushDesert;
import static biomesoplenty.api.content.BOPCBiomes.lushRiver;
import static biomesoplenty.api.content.BOPCBiomes.lushSwamp;
import static biomesoplenty.api.content.BOPCBiomes.mangrove;
import static biomesoplenty.api.content.BOPCBiomes.mapleWoods;
import static biomesoplenty.api.content.BOPCBiomes.marsh;
import static biomesoplenty.api.content.BOPCBiomes.meadow;
import static biomesoplenty.api.content.BOPCBiomes.meadowForest;
import static biomesoplenty.api.content.BOPCBiomes.moor;
import static biomesoplenty.api.content.BOPCBiomes.mountain;
import static biomesoplenty.api.content.BOPCBiomes.mysticGrove;
import static biomesoplenty.api.content.BOPCBiomes.oasis;
import static biomesoplenty.api.content.BOPCBiomes.ominousWoods;
import static biomesoplenty.api.content.BOPCBiomes.orchard;
import static biomesoplenty.api.content.BOPCBiomes.originValley;
import static biomesoplenty.api.content.BOPCBiomes.outback;
import static biomesoplenty.api.content.BOPCBiomes.phantasmagoricInferno;
import static biomesoplenty.api.content.BOPCBiomes.polarChasm;
import static biomesoplenty.api.content.BOPCBiomes.prairie;
import static biomesoplenty.api.content.BOPCBiomes.quagmire;
import static biomesoplenty.api.content.BOPCBiomes.rainforest;
import static biomesoplenty.api.content.BOPCBiomes.redwoodForest;
import static biomesoplenty.api.content.BOPCBiomes.sacredSprings;
import static biomesoplenty.api.content.BOPCBiomes.scrubland;
import static biomesoplenty.api.content.BOPCBiomes.seasonalForest;
import static biomesoplenty.api.content.BOPCBiomes.seasonalForestClearing;
import static biomesoplenty.api.content.BOPCBiomes.shield;
import static biomesoplenty.api.content.BOPCBiomes.shrubland;
import static biomesoplenty.api.content.BOPCBiomes.silkglades;
import static biomesoplenty.api.content.BOPCBiomes.sludgepit;
import static biomesoplenty.api.content.BOPCBiomes.snowyConiferousForest;
import static biomesoplenty.api.content.BOPCBiomes.spectralGarden;
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
import static biomesoplenty.api.content.BOPCBiomes.xericShrubland;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPObfuscationHelper;
import biomesoplenty.api.biome.BOPOverriddenBiome;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.common.biome.end.BiomeGenSpectralGarden;
import biomesoplenty.common.biome.nether.BiomeGenBoneyard;
import biomesoplenty.common.biome.nether.BiomeGenCorruptedSands;
import biomesoplenty.common.biome.nether.BiomeGenPhantasmagoricInferno;
import biomesoplenty.common.biome.nether.BiomeGenPolarChasm;
import biomesoplenty.common.biome.nether.BiomeGenUndergarden;
import biomesoplenty.common.biome.nether.BiomeGenVisceralHeap;
import biomesoplenty.common.biome.overridden.BiomeGenBOPBirchForest;
import biomesoplenty.common.biome.overridden.BiomeGenBOPDesert;
import biomesoplenty.common.biome.overridden.BiomeGenBOPExtremeHills;
import biomesoplenty.common.biome.overridden.BiomeGenBOPForest;
import biomesoplenty.common.biome.overridden.BiomeGenBOPHell;
import biomesoplenty.common.biome.overridden.BiomeGenBOPIcePlains;
import biomesoplenty.common.biome.overridden.BiomeGenBOPJungle;
import biomesoplenty.common.biome.overridden.BiomeGenBOPMesa;
import biomesoplenty.common.biome.overridden.BiomeGenBOPMushroomIsland;
import biomesoplenty.common.biome.overridden.BiomeGenBOPOcean;
import biomesoplenty.common.biome.overridden.BiomeGenBOPPlains;
import biomesoplenty.common.biome.overridden.BiomeGenBOPRiver;
import biomesoplenty.common.biome.overridden.BiomeGenBOPRoofedForest;
import biomesoplenty.common.biome.overridden.BiomeGenBOPSavanna;
import biomesoplenty.common.biome.overridden.BiomeGenBOPSwamp;
import biomesoplenty.common.biome.overridden.BiomeGenBOPTaiga;
import biomesoplenty.common.biome.overworld.BiomeGenAlps;
import biomesoplenty.common.biome.overworld.BiomeGenArctic;
import biomesoplenty.common.biome.overworld.BiomeGenBambooForest;
import biomesoplenty.common.biome.overworld.BiomeGenBayou;
import biomesoplenty.common.biome.overworld.BiomeGenBog;
import biomesoplenty.common.biome.overworld.BiomeGenBorealForest;
import biomesoplenty.common.biome.overworld.BiomeGenBrushland;
import biomesoplenty.common.biome.overworld.BiomeGenCanyon;
import biomesoplenty.common.biome.overworld.BiomeGenChaparral;
import biomesoplenty.common.biome.overworld.BiomeGenCherryBlossomGrove;
import biomesoplenty.common.biome.overworld.BiomeGenConiferousForest;
import biomesoplenty.common.biome.overworld.BiomeGenConiferousForestSnow;
import biomesoplenty.common.biome.overworld.BiomeGenCrag;
import biomesoplenty.common.biome.overworld.BiomeGenDeadForest;
import biomesoplenty.common.biome.overworld.BiomeGenDeadSwamp;
import biomesoplenty.common.biome.overworld.BiomeGenDeciduousForest;
import biomesoplenty.common.biome.overworld.BiomeGenDenseForest;
import biomesoplenty.common.biome.overworld.BiomeGenEucalyptusForest;
import biomesoplenty.common.biome.overworld.BiomeGenFen;
import biomesoplenty.common.biome.overworld.BiomeGenFlowerField;
import biomesoplenty.common.biome.overworld.BiomeGenFrostForest;
import biomesoplenty.common.biome.overworld.BiomeGenFungiForest;
import biomesoplenty.common.biome.overworld.BiomeGenGarden;
import biomesoplenty.common.biome.overworld.BiomeGenGrassland;
import biomesoplenty.common.biome.overworld.BiomeGenGrove;
import biomesoplenty.common.biome.overworld.BiomeGenHeathland;
import biomesoplenty.common.biome.overworld.BiomeGenHighland;
import biomesoplenty.common.biome.overworld.BiomeGenJadeCliffs;
import biomesoplenty.common.biome.overworld.BiomeGenLandOfLakes;
import biomesoplenty.common.biome.overworld.BiomeGenLavenderFields;
import biomesoplenty.common.biome.overworld.BiomeGenLushDesert;
import biomesoplenty.common.biome.overworld.BiomeGenLushSwamp;
import biomesoplenty.common.biome.overworld.BiomeGenMapleWoods;
import biomesoplenty.common.biome.overworld.BiomeGenMarsh;
import biomesoplenty.common.biome.overworld.BiomeGenMeadow;
import biomesoplenty.common.biome.overworld.BiomeGenMoor;
import biomesoplenty.common.biome.overworld.BiomeGenMountain;
import biomesoplenty.common.biome.overworld.BiomeGenMysticGrove;
import biomesoplenty.common.biome.overworld.BiomeGenOminousWoods;
import biomesoplenty.common.biome.overworld.BiomeGenOrchard;
import biomesoplenty.common.biome.overworld.BiomeGenOriginValley;
import biomesoplenty.common.biome.overworld.BiomeGenOutback;
import biomesoplenty.common.biome.overworld.BiomeGenPrairie;
import biomesoplenty.common.biome.overworld.BiomeGenRainforest;
import biomesoplenty.common.biome.overworld.BiomeGenRedwoodForest;
import biomesoplenty.common.biome.overworld.BiomeGenSacredSprings;
import biomesoplenty.common.biome.overworld.BiomeGenSeasonalForest;
import biomesoplenty.common.biome.overworld.BiomeGenShield;
import biomesoplenty.common.biome.overworld.BiomeGenShrubland;
import biomesoplenty.common.biome.overworld.BiomeGenSludgepit;
import biomesoplenty.common.biome.overworld.BiomeGenSteppe;
import biomesoplenty.common.biome.overworld.BiomeGenTemperateRainforest;
import biomesoplenty.common.biome.overworld.BiomeGenThicket;
import biomesoplenty.common.biome.overworld.BiomeGenTropicalRainforest;
import biomesoplenty.common.biome.overworld.BiomeGenTundra;
import biomesoplenty.common.biome.overworld.BiomeGenWasteland;
import biomesoplenty.common.biome.overworld.BiomeGenWetland;
import biomesoplenty.common.biome.overworld.BiomeGenWoodland;
import biomesoplenty.common.biome.overworld.BiomeGenXericShrubland;
import biomesoplenty.common.biome.overworld.ocean.BiomeGenCoralReef;
import biomesoplenty.common.biome.overworld.ocean.BiomeGenKelpForest;
import biomesoplenty.common.biome.overworld.sub.BiomeGenAlpsForest;
import biomesoplenty.common.biome.overworld.sub.BiomeGenCanyonRavine;
import biomesoplenty.common.biome.overworld.sub.BiomeGenGlacier;
import biomesoplenty.common.biome.overworld.sub.BiomeGenLandOfLakesMarsh;
import biomesoplenty.common.biome.overworld.sub.BiomeGenMangrove;
import biomesoplenty.common.biome.overworld.sub.BiomeGenMeadowForest;
import biomesoplenty.common.biome.overworld.sub.BiomeGenOasis;
import biomesoplenty.common.biome.overworld.sub.BiomeGenQuagmire;
import biomesoplenty.common.biome.overworld.sub.BiomeGenScrubland;
import biomesoplenty.common.biome.overworld.sub.BiomeGenSeasonalForestClearing;
import biomesoplenty.common.biome.overworld.sub.BiomeGenSilkglades;
import biomesoplenty.common.biome.overworld.sub.BiomeGenSpruceWoods;
import biomesoplenty.common.biome.overworld.sub.BiomeGenTropics;
import biomesoplenty.common.biome.overworld.sub.BiomeGenVolcano;
import biomesoplenty.common.biome.overworld.tech.BiomeGenDryRiver;
import biomesoplenty.common.biome.overworld.tech.BiomeGenLushRiver;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import biomesoplenty.common.configuration.BOPConfigurationBiomeWeights;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.helpers.BOPReflectionHelper;
import biomesoplenty.common.integration.ATGIntegration;
import biomesoplenty.common.utils.BOPLogger;
import biomesoplenty.common.world.BOPBiomeManager;
import biomesoplenty.common.world.BOPBiomeManager.TemperatureType;
import biomesoplenty.common.world.WorldTypeBOP;

public class BOPBiomes
{
	public static WorldTypeBOP worldTypeBOP;
	
	public static BiomeGenBase onlyBiome = null;

	public static void init()
	{
        try
        {
        	BOPConfigurationIDs.config.load();
        	BOPConfigurationBiomeGen.config.load();
        	BOPConfigurationBiomeWeights.config.load();
        	registerBiomes();
        	registerOverriddenBiomes();
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
        bog = registerOverworldBiome(BiomeGenBog.class, "Bog", TemperatureType.COOL, 7);
        borealForest = registerOverworldBiome(BiomeGenBorealForest.class, "Boreal Forest", TemperatureType.COOL, 10);
        brushland = registerOverworldBiome(BiomeGenBrushland.class, "Brushland", TemperatureType.HOT, 10);
        canyon = registerOverworldBiome(BiomeGenCanyon.class, "Canyon", TemperatureType.HOT, 5);
        chaparral = registerOverworldBiome(BiomeGenChaparral.class, "Chaparral", TemperatureType.WARM, 10);
        cherryBlossomGrove = registerOverworldBiome(BiomeGenCherryBlossomGrove.class, "Cherry Blossom Grove", TemperatureType.COOL, 3);
        coniferousForest = registerOverworldBiome(BiomeGenConiferousForest.class, "Coniferous Forest", TemperatureType.COOL, 10);
        snowyConiferousForest = registerOverworldBiome(BiomeGenConiferousForestSnow.class, "Snowy Coniferous Forest", TemperatureType.ICY, 10);
        crag = registerOverworldBiome(BiomeGenCrag.class, "Crag", TemperatureType.COOL, 3);
        deadForest = registerOverworldBiome(BiomeGenDeadForest.class, "Dead Forest", TemperatureType.COOL, 5);
        deadSwamp = registerOverworldBiome(BiomeGenDeadSwamp.class, "Dead Swamp", TemperatureType.COOL, 5);
        deciduousForest = registerOverworldBiome(BiomeGenDeciduousForest.class, "Deciduous Forest", TemperatureType.WARM, 10);
        denseForest = registerOverworldBiome(BiomeGenDenseForest.class, "Dense Forest", TemperatureType.WARM, 7);
        eucalyptusForest = registerOverworldBiome(BiomeGenEucalyptusForest.class, "Eucalyptus Forest", TemperatureType.HOT, 7);
        fen = registerOverworldBiome(BiomeGenFen.class, "Fen", TemperatureType.COOL, 10);
        flowerField = registerOverworldBiome(BiomeGenFlowerField.class, "Flower Field", TemperatureType.WARM, 3);
        frostForest = registerOverworldBiome(BiomeGenFrostForest.class, "Frost Forest", TemperatureType.ICY, 7);
        fungiForest = registerOverworldBiome(BiomeGenFungiForest.class, "Fungi Forest", TemperatureType.WARM, 3);
        garden = registerOverworldBiome(BiomeGenGarden.class, "Garden", TemperatureType.COOL, 3);
        grassland = registerOverworldBiome(BiomeGenGrassland.class, "Grassland", TemperatureType.COOL, 10);
        grove = registerOverworldBiome(BiomeGenGrove.class, "Grove", TemperatureType.COOL, 7);
        heathland = registerOverworldBiome(BiomeGenHeathland.class, "Heathland", TemperatureType.WARM, 10);
        highland = registerOverworldBiome(BiomeGenHighland.class, "Highland", TemperatureType.WARM, 7);
        jadeCliffs = registerOverworldBiome(BiomeGenJadeCliffs.class, "Jade Cliffs", TemperatureType.WARM, 3);
        landOfLakes = registerOverworldBiome(BiomeGenLandOfLakes.class, "Land of Lakes", TemperatureType.COOL, 5);
        lavenderFields = registerOverworldBiome(BiomeGenLavenderFields.class, "Lavender Fields", TemperatureType.WARM, 3);
        lushDesert = registerOverworldBiome(BiomeGenLushDesert.class, "Lush Desert", TemperatureType.HOT, 5);
        lushSwamp = registerOverworldBiome(BiomeGenLushSwamp.class, "Lush Swamp", TemperatureType.WARM, 10);
        mapleWoods = registerOverworldBiome(BiomeGenMapleWoods.class, "Maple Woods", TemperatureType.COOL, 10);
        marsh = registerOverworldBiome(BiomeGenMarsh.class, "Marsh", TemperatureType.WARM, 7);
        meadow = registerOverworldBiome(BiomeGenMeadow.class, "Meadow", TemperatureType.COOL, 10);
        moor = registerOverworldBiome(BiomeGenMoor.class, "Moor", TemperatureType.COOL, 7);
        mountain = registerOverworldBiome(BiomeGenMountain.class, "Mountain", TemperatureType.WARM, 10);
        mysticGrove = registerOverworldBiome(BiomeGenMysticGrove.class, "Mystic Grove", TemperatureType.COOL, 2);
        ominousWoods = registerOverworldBiome(BiomeGenOminousWoods.class, "Ominous Woods", TemperatureType.COOL, 2);
        orchard = registerOverworldBiome(BiomeGenOrchard.class, "Orchard", TemperatureType.WARM, 3);
        originValley = registerOverworldBiome(BiomeGenOriginValley.class, "Origin Valley", TemperatureType.WARM, 1);
        outback = registerOverworldBiome(BiomeGenOutback.class, "Outback", TemperatureType.HOT, 7);
        prairie = registerOverworldBiome(BiomeGenPrairie.class, "Prairie", TemperatureType.WARM, 10);
        rainforest = registerOverworldBiome(BiomeGenRainforest.class, "Rainforest", TemperatureType.WARM, 7);
        redwoodForest = registerOverworldBiome(BiomeGenRedwoodForest.class, "Redwood Forest", TemperatureType.WARM, 5);
        sacredSprings = registerOverworldBiome(BiomeGenSacredSprings.class, "Sacred Springs", TemperatureType.WARM, 3);
        seasonalForest = registerOverworldBiome(BiomeGenSeasonalForest.class, "Seasonal Forest", TemperatureType.COOL, 10);
        shield = registerOverworldBiome(BiomeGenShield.class, "Shield", TemperatureType.COOL, 7);
        shrubland = registerOverworldBiome(BiomeGenShrubland.class, "Shrubland", TemperatureType.COOL, 10);
        sludgepit = registerOverworldBiome(BiomeGenSludgepit.class, "Sludgepit", TemperatureType.WARM, 5);
        steppe = registerOverworldBiome(BiomeGenSteppe.class, "Steppe", TemperatureType.HOT, 5);
        temperateRainforest = registerOverworldBiome(BiomeGenTemperateRainforest.class, "Temperate Rainforest", TemperatureType.COOL, 10);
        thicket = registerOverworldBiome(BiomeGenThicket.class, "Thicket", TemperatureType.COOL, 5);
        tropicalRainforest = registerOverworldBiome(BiomeGenTropicalRainforest.class, "Tropical Rainforest", TemperatureType.HOT, 5);
        tundra = registerOverworldBiome(BiomeGenTundra.class, "Tundra", TemperatureType.ICY, 7);
        wasteland = registerOverworldBiome(BiomeGenWasteland.class, "Wasteland", TemperatureType.HOT, 3);
        wetland = registerOverworldBiome(BiomeGenWetland.class, "Wetland", TemperatureType.WARM, 7);
        woodland = registerOverworldBiome(BiomeGenWoodland.class, "Woodland", TemperatureType.COOL, 10);
        xericShrubland = registerOverworldBiome(BiomeGenXericShrubland.class, "Xeric Shrubland", TemperatureType.HOT, 5);
		
		//Ocean Biomes
		coralReef = registerOverworldSubBiome(BiomeGenCoralReef.class, "Coral Reef", 10, BiomeGenBase.ocean);
		kelpForest = registerOverworldSubBiome(BiomeGenKelpForest.class, "Kelp Forest", 10, BiomeGenBase.ocean);
		tropics = registerOverworldSubBiome(BiomeGenTropics.class, "Tropics", 10, BiomeGenBase.deepOcean);
		volcano = registerOverworldSubBiome(BiomeGenVolcano.class, "Volcano", 10, BiomeGenBase.deepOcean);
		mangrove = registerOverworldSubBiome(BiomeGenMangrove.class, "Mangrove", 10, BiomeGenBase.deepOcean);
		
        //Sub Biomes
		alpsForest = registerOverworldSubBiome(BiomeGenAlpsForest.class, "Alps Forest", 10, alps);
		canyonRavine = registerOverworldSubBiome(BiomeGenCanyonRavine.class, "Canyon Ravine", 10, canyon);
		glacier = registerOverworldSubBiome(BiomeGenGlacier.class, "Glacier", 10, arctic);
		landOfLakesMarsh = registerOverworldSubBiome(BiomeGenLandOfLakesMarsh.class, "Land of Lakes Marsh", 10, landOfLakes);
		meadowForest = registerOverworldSubBiome(BiomeGenMeadowForest.class, "Meadow Forest", 10, meadow);
		oasis = registerOverworldSubBiome(BiomeGenOasis.class, "Oasis", 10, BiomeGenBase.desert);
		quagmire = registerOverworldSubBiome(BiomeGenQuagmire.class, "Quagmire", 10, sludgepit);
		scrubland = registerOverworldSubBiome(BiomeGenScrubland.class, "Scrubland", 10, BiomeGenBase.savanna);
		seasonalForestClearing = registerOverworldSubBiome(BiomeGenSeasonalForestClearing.class, "Seasonal Forest Clearing", 10, seasonalForest);
		silkglades = registerOverworldSubBiome(BiomeGenSilkglades.class, "Silkglades", 10, sludgepit);
		spruceWoods = registerOverworldSubBiome(BiomeGenSpruceWoods.class, "Spruce Woods", 10, BiomeGenBase.forest);
        
        //Nether Biomes
        corruptedSands = registerNetherBiome(BiomeGenCorruptedSands.class, "Corrupted Sands", 10);
        phantasmagoricInferno = registerNetherBiome(BiomeGenPhantasmagoricInferno.class, "Phantasmagoric Inferno", 10);
        boneyard = registerNetherBiome(BiomeGenBoneyard.class, "Boneyard", 10);
        visceralHeap = registerNetherBiome(BiomeGenVisceralHeap.class, "Visceral Heap", 10);
        undergarden = registerNetherBiome(BiomeGenUndergarden.class, "Undergarden", 10);
        polarChasm = registerNetherBiome(BiomeGenPolarChasm.class, "Polar Chasm", 1);
        
        //End Biomes
        spectralGarden = registerEndBiome(BiomeGenSpectralGarden.class, "Spectral Garden", 10);
        
        //River Biomes
        lushRiver = registerOverworldRiverBiome(BiomeGenLushRiver.class, "Lush River", lushSwamp, lavenderFields, flowerField, bambooForest, cherryBlossomGrove, lushDesert, meadow, spruceWoods, rainforest, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleEdge, BiomeGenBase.jungleHills);
        dryRiver = registerOverworldRiverBiome(BiomeGenDryRiver.class, "Dry River", outback, steppe, BiomeGenBase.desert, BiomeGenBase.desertHills);
	}
	
	private static void registerOverriddenBiomes()
	{
		registerOverriddenBiome(BiomeGenBOPBirchForest.class, BOPObfuscationHelper.birchForest, BOPObfuscationHelper.birchForestHills);

		registerOverriddenBiome(BiomeGenBOPDesert.class, BOPObfuscationHelper.desert, BOPObfuscationHelper.desertHills);

		registerOverriddenBiome(BiomeGenBOPExtremeHills.class, BOPObfuscationHelper.extremeHills, BOPObfuscationHelper.extremeHillsEdge);

		registerOverriddenBiome(BiomeGenBOPForest.class, BOPObfuscationHelper.forest, BOPObfuscationHelper.forestHills);

		registerOverriddenBiome(BiomeGenBOPIcePlains.class, BOPObfuscationHelper.icePlains);

		registerOverriddenBiome(BiomeGenBOPJungle.class, BOPObfuscationHelper.jungle, BOPObfuscationHelper.jungleEdge, BOPObfuscationHelper.jungleHills);

		registerOverriddenBiome(BiomeGenBOPMesa.class, BOPObfuscationHelper.mesa, BOPObfuscationHelper.mesaPlateau, BOPObfuscationHelper.mesaPlateau_F);

		registerOverriddenBiome(BiomeGenBOPMushroomIsland.class, BOPObfuscationHelper.mushroomIsland, BOPObfuscationHelper.mushroomIslandShore);
		
		registerOverriddenBiome(BiomeGenBOPOcean.class, BOPObfuscationHelper.ocean);
		registerOverriddenBiome(BiomeGenBOPPlains.class, BOPObfuscationHelper.plains);
		registerOverriddenBiome(BiomeGenBOPRiver.class, BOPObfuscationHelper.river);
		registerOverriddenBiome(BiomeGenBOPRoofedForest.class, BOPObfuscationHelper.roofedForest);

		registerOverriddenBiome(BiomeGenBOPSavanna.class, BOPObfuscationHelper.savanna, BOPObfuscationHelper.savannaPlateau);

		registerOverriddenBiome(BiomeGenBOPSwamp.class, BOPObfuscationHelper.swampland);

		registerOverriddenBiome(BiomeGenBOPTaiga.class, BOPObfuscationHelper.taiga, BOPObfuscationHelper.taigaHills, BOPObfuscationHelper.coldTaiga, BOPObfuscationHelper.coldTaigaHills);

		registerOverriddenBiome(BiomeGenBOPHell.class, BOPObfuscationHelper.hell);
	}
	
	private static void addBiomesToDictionary()
	{
        registerBiomeToDictionary(BOPCBiomes.alps, Type.SNOWY, Type.MOUNTAIN, Type.COLD);
        registerBiomeToDictionary(BOPCBiomes.alpsForest, Type.SNOWY, Type.MOUNTAIN, Type.FOREST, Type.COLD);
        registerBiomeToDictionary(BOPCBiomes.arctic, Type.SNOWY, Type.WASTELAND, Type.COLD, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.bambooForest, Type.JUNGLE, Type.FOREST, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.bayou, Type.SWAMP, Type.WATER, Type.LUSH, Type.WET);
        //registerBiomeToDictionary(BOPBiomeHelper.getBOPBiome("beachGravel, Type.BEACH);
        registerBiomeToDictionary(BOPCBiomes.bog, Type.SWAMP, Type.FOREST, Type.WET, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.boneyard, Type.NETHER, Type.WASTELAND, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.borealForest, Type.FOREST, Type.DENSE, Type.CONIFEROUS, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.brushland, Type.PLAINS, Type.DRY, Type.HOT, Type.SAVANNA);
        registerBiomeToDictionary(BOPCBiomes.canyon, Type.SANDY, Type.MOUNTAIN, Type.HILLS, Type.SPARSE, Type.DRY, Type.HOT);
        registerBiomeToDictionary(BOPCBiomes.canyonRavine, Type.SANDY, Type.HILLS, Type.DRY, Type.HOT);
        registerBiomeToDictionary(BOPCBiomes.chaparral, Type.PLAINS, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.cherryBlossomGrove, Type.MAGICAL, Type.FOREST, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.coniferousForest, Type.FOREST, Type.HILLS, Type.CONIFEROUS, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.snowyConiferousForest, Type.SNOWY, Type.FOREST, Type.HILLS, Type.COLD, Type.CONIFEROUS, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.corruptedSands, Type.NETHER, Type.SANDY, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.crag, Type.WASTELAND, Type.MOUNTAIN, Type.SPOOKY, Type.DEAD, Type.DRY);
        registerBiomeToDictionary(BOPCBiomes.deadForest, Type.FOREST, Type.DEAD, Type.SPARSE, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.deadSwamp, Type.SWAMP, Type.DEAD, Type.SPARSE, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.deciduousForest, Type.FOREST, Type.DENSE, Type.DRY);
        registerBiomeToDictionary(BOPCBiomes.fen, Type.FOREST, Type.SWAMP, Type.DEAD, Type.WET);
        registerBiomeToDictionary(BOPCBiomes.flowerField, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.frostForest, Type.SNOWY, Type.FOREST, Type.COLD, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.fungiForest, Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP, Type.LUSH, Type.WET);
        registerBiomeToDictionary(BOPCBiomes.garden, Type.MAGICAL, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.glacier, Type.SNOWY, Type.HILLS, Type.COLD, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.grassland, Type.PLAINS, Type.SWAMP, Type.HILLS, Type.SPARSE, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.grove, Type.FOREST, Type.PLAINS, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.heathland, Type.PLAINS, Type.DRY, Type.SAVANNA);
        registerBiomeToDictionary(BOPCBiomes.highland, Type.HILLS, Type.MOUNTAIN, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.jadeCliffs, Type.FOREST, Type.MOUNTAIN, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.lavenderFields, Type.PLAINS, Type.LUSH, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.lushDesert, Type.SANDY, Type.SAVANNA, Type.DRY, Type.LUSH, Type.HOT);
        registerBiomeToDictionary(BOPCBiomes.lushSwamp, Type.SWAMP, Type.WATER, Type.LUSH, Type.WET);
        registerBiomeToDictionary(BOPCBiomes.mangrove, Type.WATER, Type.FOREST, Type.WET, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.mapleWoods, Type.FOREST, Type.COLD);
        registerBiomeToDictionary(BOPCBiomes.marsh, Type.SWAMP, Type.WATER, Type.WET, Type.SPARSE, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.meadow, Type.FOREST, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.meadowForest, Type.FOREST, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.moor, Type.HILLS, Type.SWAMP, Type.SPARSE, Type.WET);
        registerBiomeToDictionary(BOPCBiomes.mountain, Type.MOUNTAIN, Type.FOREST, Type.DRY);
        registerBiomeToDictionary(BOPCBiomes.mysticGrove, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.WET);
        registerBiomeToDictionary(BOPCBiomes.oasis, Type.SANDY, Type.JUNGLE, Type.LUSH, Type.DRY, Type.HOT);
        registerBiomeToDictionary(BOPCBiomes.coralReef, Type.WATER);
        registerBiomeToDictionary(BOPCBiomes.kelpForest, Type.WATER, Type.FOREST);
        registerBiomeToDictionary(BOPCBiomes.ominousWoods, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.SWAMP, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.orchard, Type.FOREST, Type.PLAINS, Type.LUSH, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.outback, Type.SANDY, Type.PLAINS, Type.SAVANNA, Type.DRY, Type.HOT);
        registerBiomeToDictionary(BOPCBiomes.phantasmagoricInferno, Type.NETHER, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.prairie, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.quagmire, Type.SWAMP, Type.WASTELAND, Type.SPOOKY, Type.WET, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.rainforest, Type.JUNGLE, Type.HILLS, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.redwoodForest, Type.FOREST, Type.CONIFEROUS, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.sacredSprings, Type.MOUNTAIN, Type.FOREST, Type.MAGICAL, Type.WET, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.scrubland, Type.SAVANNA, Type.PLAINS, Type.DRY, Type.HOT, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.seasonalForest, Type.FOREST, Type.LUSH, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.shield, Type.FOREST, Type.WATER, Type.CONIFEROUS, Type.WET);
        registerBiomeToDictionary(BOPCBiomes.shrubland, Type.PLAINS, Type.SPARSE, Type.DRY);
        registerBiomeToDictionary(BOPCBiomes.silkglades, Type.SWAMP, Type.FOREST, Type.SPOOKY, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.sludgepit, Type.SWAMP, Type.FOREST, Type.WASTELAND, Type.WET, Type.DEAD, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.spruceWoods, Type.FOREST, Type.CONIFEROUS, Type.LUSH, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.steppe, Type.PLAINS, Type.SANDY, Type.DRY, Type.HOT, Type.SAVANNA, Type.SPARSE, Type.DEAD);
        registerBiomeToDictionary(BOPCBiomes.temperateRainforest, Type.FOREST, Type.HILLS, Type.WET, Type.CONIFEROUS, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.thicket, Type.PLAINS, Type.FOREST, Type.DRY, Type.DEAD, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.tropicalRainforest, Type.JUNGLE, Type.HOT, Type.WET, Type.LUSH, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.tropics, Type.JUNGLE, Type.BEACH, Type.WATER, Type.WET, Type.LUSH);
        registerBiomeToDictionary(BOPCBiomes.tundra, Type.COLD, Type.WASTELAND, Type.DRY, Type.DEAD, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.undergarden, Type.NETHER, Type.JUNGLE, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.visceralHeap, Type.NETHER, Type.SPOOKY);
        registerBiomeToDictionary(BOPCBiomes.volcano, Type.MOUNTAIN, Type.WASTELAND, Type.HOT, Type.DRY);
        registerBiomeToDictionary(BOPCBiomes.wasteland, Type.WASTELAND, Type.SPOOKY, Type.DEAD, Type.SPARSE);
        registerBiomeToDictionary(BOPCBiomes.wetland, Type.SWAMP, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPCBiomes.woodland, Type.FOREST, Type.DRY, Type.DENSE);
	}
	
	private static void registerBiomeToDictionary(BiomeGenBase biome, Type... types) {
		if (biome != null) {
			BiomeDictionary.registerBiomeType(biome, types);
		}
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
	
	private static BiomeGenBase registerOverworldSubBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int weight, BiomeGenBase...parents)
	{
		BiomeGenBase biome = BOPBiomeManager.createBiome(biomeClass, biomeName);
		
		if (biome != null)
		{
			BiomeEntry entry = new BiomeEntry(biome, weight);

			if (BOPConfigurationBiomeGen.config.get("Overworld (Sub) Biomes To Generate", biome.biomeName, true).getBoolean(false))
			{
				for (BiomeGenBase parent : parents)
				{
					if (parent != null)
					{
						if (BOPBiomeManager.overworldSubBiomes[parent.biomeID] == null) BOPBiomeManager.overworldSubBiomes[parent.biomeID] = new ArrayList();

						BOPBiomeManager.overworldSubBiomes[parent.biomeID].add(entry);
						
						ATGIntegration.registerATGSubBiome(parent, biome);
					}
				}
			}

			return biome;
		}
		
		return null;
	}
	
	private static BiomeGenBase registerOverworldRiverBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, BiomeGenBase...parents)
	{
		BiomeGenBase biome = BOPBiomeManager.createBiome(biomeClass, biomeName);
		
		if (biome != null)
		{
			if (BOPConfigurationBiomeGen.config.get("Overworld (River) Biomes To Generate", biome.biomeName, true).getBoolean(false))
			{
				for (BiomeGenBase parent : parents)
				{
					if (parent != null)
					{
						BOPBiomeManager.overworldRiverBiomes[parent.biomeID] = biome;
					}
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
	
	private static BiomeGenBase registerEndBiome(Class<? extends BiomeGenBase> biomeClass, String biomeName, int weight)
	{
		return BOPBiomeManager.createAndRegisterBiome(biomeClass, "End", biomeName, BOPBiomeManager.endBiomes, weight);
	}
	
	private static void registerOverriddenBiome(Class<? extends BOPOverriddenBiome> biomeClass, String[]...overriddenBiomeNames)
	{
		for (String[] overriddenBiomeName : overriddenBiomeNames)
		{
			Field field = BOPReflectionHelper.removeFinal(BiomeGenBase.class, null, overriddenBiomeName);
			
			try
			{
				BiomeGenBase biomeToOverride = (BiomeGenBase)field.get(null);

				if (biomeToOverride != null)
				{
					BiomeGenBase newBiome = BOPBiomeManager.createBiome(biomeClass, biomeToOverride.biomeName, biomeToOverride.biomeID);

					if (BOPConfigurationBiomeGen.config.get("Vanilla Biomes To Override", biomeToOverride.biomeName, true).getBoolean(false))
					{
						field.set(null, newBiome);
						BiomeGenBase.getBiomeGenArray()[biomeToOverride.biomeID] = newBiome;
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
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
