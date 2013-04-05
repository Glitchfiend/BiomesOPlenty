package tdwp_ftw.biomesop.declarations;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.biomes.BiomeGenAlps;
import tdwp_ftw.biomesop.biomes.BiomeGenArctic;
import tdwp_ftw.biomesop.biomes.BiomeGenBadlands;
import tdwp_ftw.biomesop.biomes.BiomeGenBambooForest;
import tdwp_ftw.biomesop.biomes.BiomeGenBayou;
import tdwp_ftw.biomesop.biomes.BiomeGenBirchForest;
import tdwp_ftw.biomesop.biomes.BiomeGenBog;
import tdwp_ftw.biomesop.biomes.BiomeGenBorealForest;
import tdwp_ftw.biomesop.biomes.BiomeGenCanyon;
import tdwp_ftw.biomesop.biomes.BiomeGenChaparral;
import tdwp_ftw.biomesop.biomes.BiomeGenCherryBlossomGrove;
import tdwp_ftw.biomesop.biomes.BiomeGenConiferousForest;
import tdwp_ftw.biomesop.biomes.BiomeGenCrag;
import tdwp_ftw.biomesop.biomes.BiomeGenDeadForest;
import tdwp_ftw.biomesop.biomes.BiomeGenDeadSwamp;
import tdwp_ftw.biomesop.biomes.BiomeGenDeadlands;
import tdwp_ftw.biomesop.biomes.BiomeGenDeciduousForest;
import tdwp_ftw.biomesop.biomes.BiomeGenDesertNew;
import tdwp_ftw.biomesop.biomes.BiomeGenDrylands;
import tdwp_ftw.biomesop.biomes.BiomeGenDunes;
import tdwp_ftw.biomesop.biomes.BiomeGenFen;
import tdwp_ftw.biomesop.biomes.BiomeGenField;
import tdwp_ftw.biomesop.biomes.BiomeGenForestNew;
import tdwp_ftw.biomesop.biomes.BiomeGenFrostForest;
import tdwp_ftw.biomesop.biomes.BiomeGenFungiForest;
import tdwp_ftw.biomesop.biomes.BiomeGenGarden;
import tdwp_ftw.biomesop.biomes.BiomeGenGlacier;
import tdwp_ftw.biomesop.biomes.BiomeGenGrassland;
import tdwp_ftw.biomesop.biomes.BiomeGenGrove;
import tdwp_ftw.biomesop.biomes.BiomeGenHeathland;
import tdwp_ftw.biomesop.biomes.BiomeGenHighland;
import tdwp_ftw.biomesop.biomes.BiomeGenHillsNew;
import tdwp_ftw.biomesop.biomes.BiomeGenIceSheet;
import tdwp_ftw.biomesop.biomes.BiomeGenIcyHills;
import tdwp_ftw.biomesop.biomes.BiomeGenJadeCliffs;
import tdwp_ftw.biomesop.biomes.BiomeGenJungleNew;
import tdwp_ftw.biomesop.biomes.BiomeGenLushDesert;
import tdwp_ftw.biomesop.biomes.BiomeGenLushSwamp;
import tdwp_ftw.biomesop.biomes.BiomeGenMangrove;
import tdwp_ftw.biomesop.biomes.BiomeGenMapleWoods;
import tdwp_ftw.biomesop.biomes.BiomeGenMarsh;
import tdwp_ftw.biomesop.biomes.BiomeGenMeadow;
import tdwp_ftw.biomesop.biomes.BiomeGenMesa;
import tdwp_ftw.biomesop.biomes.BiomeGenMoor;
import tdwp_ftw.biomesop.biomes.BiomeGenMountain;
import tdwp_ftw.biomesop.biomes.BiomeGenMysticGrove;
import tdwp_ftw.biomesop.biomes.BiomeGenOasis;
import tdwp_ftw.biomesop.biomes.BiomeGenOminousWoods;
import tdwp_ftw.biomesop.biomes.BiomeGenOrchard;
import tdwp_ftw.biomesop.biomes.BiomeGenOriginValley;
import tdwp_ftw.biomesop.biomes.BiomeGenOutback;
import tdwp_ftw.biomesop.biomes.BiomeGenPasture;
import tdwp_ftw.biomesop.biomes.BiomeGenPlainsNew;
import tdwp_ftw.biomesop.biomes.BiomeGenPrairie;
import tdwp_ftw.biomesop.biomes.BiomeGenPromisedLand;
import tdwp_ftw.biomesop.biomes.BiomeGenQuagmire;
import tdwp_ftw.biomesop.biomes.BiomeGenRainforest;
import tdwp_ftw.biomesop.biomes.BiomeGenRedwoodForest;
import tdwp_ftw.biomesop.biomes.BiomeGenSacredSprings;
import tdwp_ftw.biomesop.biomes.BiomeGenSavanna;
import tdwp_ftw.biomesop.biomes.BiomeGenScrubland;
import tdwp_ftw.biomesop.biomes.BiomeGenSeasonalForest;
import tdwp_ftw.biomesop.biomes.BiomeGenShield;
import tdwp_ftw.biomesop.biomes.BiomeGenShore;
import tdwp_ftw.biomesop.biomes.BiomeGenShrubland;
import tdwp_ftw.biomesop.biomes.BiomeGenSnowyWoods;
import tdwp_ftw.biomesop.biomes.BiomeGenSpruceWoods;
import tdwp_ftw.biomesop.biomes.BiomeGenSteppe;
import tdwp_ftw.biomesop.biomes.BiomeGenSwampNew;
import tdwp_ftw.biomesop.biomes.BiomeGenSwampwoods;
import tdwp_ftw.biomesop.biomes.BiomeGenTaigaNew;
import tdwp_ftw.biomesop.biomes.BiomeGenTemperateRainforest;
import tdwp_ftw.biomesop.biomes.BiomeGenThicket;
import tdwp_ftw.biomesop.biomes.BiomeGenTropicalRainforest;
import tdwp_ftw.biomesop.biomes.BiomeGenTropics;
import tdwp_ftw.biomesop.biomes.BiomeGenTundra;
import tdwp_ftw.biomesop.biomes.BiomeGenVolcano;
import tdwp_ftw.biomesop.biomes.BiomeGenWasteland;
import tdwp_ftw.biomesop.biomes.BiomeGenWetland;
import tdwp_ftw.biomesop.biomes.BiomeGenWoodland;
import tdwp_ftw.biomesop.worldtype.WTBiomesOP;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBiomes {
	
	// Biome declaration
	public static BiomeGenBase alps;
	public static BiomeGenBase arctic;
	public static BiomeGenBase badlands;
	public static BiomeGenBase bambooForest;
	public static BiomeGenBase bayou;
	public static BiomeGenBase birchForest;
	public static BiomeGenBase bog;
	public static BiomeGenBase borealForest;
	public static BiomeGenBase canyon;
	public static BiomeGenBase chaparral;
	public static BiomeGenBase cherryBlossomGrove;
	public static BiomeGenBase coniferousForest;
	public static BiomeGenBase crag;
	public static BiomeGenBase deadForest;
	public static BiomeGenBase deadSwamp;
	public static BiomeGenBase deadlands;
	public static BiomeGenBase deciduousForest;
	public static BiomeGenBase drylands;
	public static BiomeGenBase dunes;
	public static BiomeGenBase fen;
	public static BiomeGenBase field;
	public static BiomeGenBase frostForest;
	public static BiomeGenBase fungiForest;
	public static BiomeGenBase garden;
	public static BiomeGenBase glacier;
	public static BiomeGenBase grassland;
	public static BiomeGenBase grove;
	public static BiomeGenBase heathland;
	public static BiomeGenBase highland;
	public static BiomeGenBase iceSheet;
	public static BiomeGenBase icyHills;
	public static BiomeGenBase jadeCliffs;
	public static BiomeGenBase lushDesert;
	public static BiomeGenBase lushSwamp;
	public static BiomeGenBase mangrove;
	public static BiomeGenBase mapleWoods;
	public static BiomeGenBase marsh;
	public static BiomeGenBase meadow;
	public static BiomeGenBase mesa;
	public static BiomeGenBase moor;
	public static BiomeGenBase mountain;
	public static BiomeGenBase mysticGrove;
	public static BiomeGenBase oasis;
	public static BiomeGenBase ominousWoods;
	public static BiomeGenBase orchard;
	public static BiomeGenBase originValley;
	public static BiomeGenBase outback;
	public static BiomeGenBase pasture;
	public static BiomeGenBase prairie;
	public static BiomeGenBase promisedLand;
	public static BiomeGenBase quagmire;
	public static BiomeGenBase rainforest;
	public static BiomeGenBase redwoodForest;
	public static BiomeGenBase sacredSprings;
	public static BiomeGenBase savanna;
	public static BiomeGenBase scrubland;
	public static BiomeGenBase seasonalForest;
	public static BiomeGenBase shield;
	public static BiomeGenBase shore;
	public static BiomeGenBase shrubland;
	public static BiomeGenBase snowyWoods;
	public static BiomeGenBase spruceWoods;
	public static BiomeGenBase steppe;
	public static BiomeGenBase swampwoods;
	public static BiomeGenBase temperateRainforest;
	public static BiomeGenBase thicket;
	public static BiomeGenBase tropicalRainforest;
	public static BiomeGenBase tropics;
	public static BiomeGenBase tundra;
	public static BiomeGenBase volcano;
	public static BiomeGenBase wasteland;
	public static BiomeGenBase wetland;
	public static BiomeGenBase woodland;
	public static BiomeGenBase plainsNew;
	public static BiomeGenBase desertNew;
	public static BiomeGenBase extremeHillsNew;
	public static BiomeGenBase forestNew;
	public static BiomeGenBase taigaNew;
	public static BiomeGenBase swamplandNew;
	public static BiomeGenBase jungleNew;
	
	public static WTBiomesOP WTBiomesOP;
	
	public static void init()
	{
		// Initialize biomes
		alps = (new BiomeGenAlps(BOPConfiguration.alpsID)).setColor(353825).setBiomeName("Alps").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(5.0F, 5.0F);
		arctic = (new BiomeGenArctic(BOPConfiguration.arcticID)).setColor(14090235).setBiomeName("Arctic").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.7F);
		badlands = (new BiomeGenBadlands(BOPConfiguration.badlandsID)).setColor(16421912).setBiomeName("Badlands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.9F);
		bambooForest = (new BiomeGenBambooForest(BOPConfiguration.bambooForestID)).setColor(112).setBiomeName("Bamboo Forest").setMinMaxHeight(0.0F, 0.3F);
		bayou = (new BiomeGenBayou(BOPConfiguration.bayouID)).setColor(522674).setBiomeName("Bayou").func_76733_a(9154376).setMinMaxHeight(-0.3F, 0.2F);
		birchForest = (new BiomeGenBirchForest(BOPConfiguration.birchForestID)).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.3F);
		bog = (new BiomeGenBog(BOPConfiguration.bogID)).setColor(522674).setBiomeName("Bog").func_76733_a(9154376).setMinMaxHeight(-0.3F, -0.1F).setTemperatureRainfall(0.8F, 0.9F);
		borealForest = (new BiomeGenBorealForest(BOPConfiguration.borealForestID)).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.0F, 1.0F).setTemperatureRainfall(0.6F, 0.7F);
		canyon = (new BiomeGenCanyon(BOPConfiguration.canyonID)).setColor(9286496).setBiomeName("Canyon").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(3.0F, 5.0F);
		chaparral = (new BiomeGenChaparral(BOPConfiguration.chaparralID)).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
		cherryBlossomGrove = (new BiomeGenCherryBlossomGrove(BOPConfiguration.cherryBlossomGroveID)).setColor(9286496).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.1F, 0.2F);
		coniferousForest = (new BiomeGenConiferousForest(BOPConfiguration.coniferousForestID)).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.4F).setMinMaxHeight(0.1F, 0.8F);
		crag = (new BiomeGenCrag(BOPConfiguration.cragID)).setColor(9286496).setBiomeName("Crag").setMinMaxHeight(0.0F, 9.9F);
		deadForest = (new BiomeGenDeadForest(BOPConfiguration.deadForestID)).setColor(522674).setBiomeName("Dead Forest").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.7F);
		deadSwamp = (new BiomeGenDeadSwamp(BOPConfiguration.deadSwampID)).setColor(522674).setBiomeName("Dead Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
		deadlands = (new BiomeGenDeadlands(BOPConfiguration.deadlandsID)).setColor(522674).setBiomeName("Deadlands").setDisableRain().func_76733_a(9154376).setMinMaxHeight(0.1F, 0.5F);
		deciduousForest = (new BiomeGenDeciduousForest(BOPConfiguration.deciduousForestID)).setColor(353825).setBiomeName("Deciduous Forest").func_76733_a(5159473);
		drylands = (new BiomeGenDrylands(BOPConfiguration.drylandsID)).setColor(16421912).setBiomeName("Drylands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.5F);
		dunes = (new BiomeGenDunes(BOPConfiguration.dunesID)).setColor(13786898).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.3F);
		fen = (new BiomeGenFen(BOPConfiguration.fenID)).setColor(9286496).setBiomeName("Fen").setTemperatureRainfall(0.4F, 0.0F).setMinMaxHeight(-0.2F, 0.1F);
		field = (new BiomeGenField(BOPConfiguration.fieldID)).setColor(9286496).setBiomeName("Field").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F);
		frostForest = (new BiomeGenFrostForest(BOPConfiguration.frostForestID)).setColor(14090235).setBiomeName("Frost Forest").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
		fungiForest = (new BiomeGenFungiForest(BOPConfiguration.fungiForestID)).setColor(747097).setBiomeName("Fungi Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.0F, 0.4F);
		garden = (new BiomeGenGarden(BOPConfiguration.gardenID)).setColor(9286496).setBiomeName("Garden").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.2F);
		glacier = (new BiomeGenGlacier(BOPConfiguration.glacierID)).setColor(6316128).setBiomeName("Glacier").setEnableSnow().setMinMaxHeight(0.4F, 1.0F).setTemperatureRainfall(0.0F, 0.0F);
		grassland = (new BiomeGenGrassland(BOPConfiguration.grasslandID)).setColor(9286496).setBiomeName("Grassland").setTemperatureRainfall(0.7F, 0.7F).setMinMaxHeight(0.2F, 0.2F);
		grove = (new BiomeGenGrove(BOPConfiguration.groveID)).setColor(9286496).setBiomeName("Grove").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F);
		heathland = (new BiomeGenHeathland(BOPConfiguration.heathlandID)).setColor(353825).setBiomeName("Heathland").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.1F, 0.3F);
		highland = (new BiomeGenHighland(BOPConfiguration.highlandID)).setColor(6316128).setBiomeName("Highland").setMinMaxHeight(0.9F, 1.9F).setTemperatureRainfall(0.5F, 0.5F);
		iceSheet = (new BiomeGenIceSheet(BOPConfiguration.iceSheetID)).setColor(6316128).setBiomeName("Ice Sheet").setEnableSnow().setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.0F, 0.0F);
		icyHills = (new BiomeGenIcyHills(BOPConfiguration.icyHillsID)).setColor(14090235).setBiomeName("Icy Hills").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.5F);
		jadeCliffs = (new BiomeGenJadeCliffs(BOPConfiguration.jadeCliffsID)).setColor(14090235).setBiomeName("Jade Cliffs").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.1F, 2.0F);
		lushDesert = (new BiomeGenLushDesert(BOPConfiguration.lushDesertID)).setColor(16421912).setBiomeName("Lush Desert").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.8F);
		lushSwamp = (new BiomeGenLushSwamp(BOPConfiguration.lushSwampID)).setColor(522674).setBiomeName("Lush Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.7F, 1.0F);
		mangrove = (new BiomeGenMangrove(BOPConfiguration.mangroveID)).setColor(16440917).setBiomeName("Mangrove").setMinMaxHeight(-0.4F, -0.1F);
		mapleWoods = (new BiomeGenMapleWoods(BOPConfiguration.mapleWoodsID)).setColor(747097).setBiomeName("Maple Woods").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 0.6F);
		marsh = (new BiomeGenMarsh(BOPConfiguration.marshID)).setColor(10486015).setBiomeName("Marsh").setMinMaxHeight(-0.5F, 0.0F);
		meadow = (new BiomeGenMeadow(BOPConfiguration.meadowID)).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 0.7F);
		mesa = (new BiomeGenMesa(BOPConfiguration.mesaID)).setColor(16421912).setBiomeName("Mesa").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.8F, 1.0F);
		moor = (new BiomeGenMoor(BOPConfiguration.moorID)).setColor(16421912).setBiomeName("Moor").setTemperatureRainfall(0.5F, 1.0F).setMinMaxHeight(0.7F, 0.8F);
		mountain = (new BiomeGenMountain(BOPConfiguration.mountainID)).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(1.2F, 1.2F);
		mysticGrove = (new BiomeGenMysticGrove(BOPConfiguration.mysticGroveID)).setColor(353825).setBiomeName("Mystic Grove").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F);
		oasis = (new BiomeGenOasis(BOPConfiguration.oasisID)).setColor(16421912).setBiomeName("Oasis").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.2F);
		ominousWoods = (new BiomeGenOminousWoods(BOPConfiguration.ominousWoodsID)).setColor(353825).setBiomeName("Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F);
		orchard = (new BiomeGenOrchard(BOPConfiguration.orchardID)).setColor(9286496).setBiomeName("Orchard").setTemperatureRainfall(0.8F, 0.4F);
		originValley = (new BiomeGenOriginValley(BOPConfiguration.originValleyID)).setColor(353825).setBiomeName("Origin Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(-0.1F, 0.6F);
		outback = (new BiomeGenOutback(BOPConfiguration.outbackID)).setColor(9286496).setBiomeName("Outback").setTemperatureRainfall(0.8F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
		pasture = (new BiomeGenPasture(BOPConfiguration.pastureID)).setColor(9286496).setBiomeName("Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.1F, 0.2F);
		prairie = (new BiomeGenPrairie(BOPConfiguration.prairieID)).setColor(353825).setBiomeName("Prairie").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.6F).setMinMaxHeight(0.1F, 0.1F);
		promisedLand = (new BiomeGenPromisedLand(BOPConfiguration.promisedLandID)).setColor(112).setBiomeName("Promised Land").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F);
		quagmire = (new BiomeGenQuagmire(BOPConfiguration.quagmireID)).setColor(522674).setBiomeName("Quagmire").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
		rainforest = (new BiomeGenRainforest(BOPConfiguration.rainforestID)).setColor(5470985).setBiomeName("Rainforest").func_76733_a(5470985).setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.7F, 1.8F);
		redwoodForest = (new BiomeGenRedwoodForest(BOPConfiguration.redwoodForestID)).setColor(747097).setBiomeName("Redwood Forest").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.4F);
		sacredSprings = (new BiomeGenSacredSprings(BOPConfiguration.sacredSpringsID)).setColor(522674).setBiomeName("Sacred Springs").func_76733_a(9154376).setMinMaxHeight(0.0F, 1.2F).setTemperatureRainfall(0.8F, 0.9F);
		savanna = (new BiomeGenSavanna(BOPConfiguration.savannaID)).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(2.0F, 0.1F).setMinMaxHeight(0.1F, 0.1F);
		scrubland = (new BiomeGenScrubland(BOPConfiguration.scrublandID)).setColor(9286496).setBiomeName("Scrubland").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.3F);
		seasonalForest = (new BiomeGenSeasonalForest(BOPConfiguration.seasonalForestID)).setColor(353825).setBiomeName("Seasonal Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
		shield = (new BiomeGenShield(BOPConfiguration.shieldID)).setColor(522674).setBiomeName("Shield").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.5F, 0.8F);
		shore = (new BiomeGenShore(BOPConfiguration.shoreID)).setColor(9286496).setBiomeName("Shore").setMinMaxHeight(-1.0F, 0.4F);
		shrubland = (new BiomeGenShrubland(BOPConfiguration.shrublandID)).setColor(9286496).setBiomeName("Shrubland").setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.6F, 0.0F);
		snowyWoods = (new BiomeGenSnowyWoods(BOPConfiguration.snowyWoodsID)).setColor(522674).setBiomeName("Snowy Woods").func_76733_a(9154376).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
		spruceWoods = (new BiomeGenSpruceWoods(BOPConfiguration.spruceWoodsID)).setColor(353825).setBiomeName("Spruce Woods").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.7F);
		steppe = (new BiomeGenSteppe(BOPConfiguration.steppeID)).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		swampwoods = (new BiomeGenSwampwoods(BOPConfiguration.swampwoodsID)).setColor(522674).setBiomeName("Swampwoods").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.2F).setTemperatureRainfall(0.8F, 0.9F);
		temperateRainforest = (new BiomeGenTemperateRainforest(BOPConfiguration.temperateRainforestID)).setColor(353825).setBiomeName("Temperate Rainforest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 1.2F);
		thicket = (new BiomeGenThicket(BOPConfiguration.thicketID)).setColor(353825).setBiomeName("Thicket").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.2F).setMinMaxHeight(0.0F, 0.2F);
		tropicalRainforest = (new BiomeGenTropicalRainforest(BOPConfiguration.tropicalRainforestID)).setColor(9286496).setBiomeName("Tropical Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.7F);
		tropics = (new BiomeGenTropics(BOPConfiguration.tropicsID)).setColor(9286496).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.8F);
		tundra = (new BiomeGenTundra(BOPConfiguration.tundraID)).setColor(14090235).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.0F);
		volcano = (new BiomeGenVolcano(BOPConfiguration.volcanoID)).setColor(9286496).setBiomeName("Volcano").setDisableRain().setMinMaxHeight(0.6F, 0.9F);
		wasteland = (new BiomeGenWasteland(BOPConfiguration.wastelandID)).setColor(16421912).setBiomeName("Wasteland").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.0F);
		wetland = (new BiomeGenWetland(BOPConfiguration.wetlandID)).setColor(522674).setBiomeName("Wetland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.8F, 0.9F);
		woodland = (new BiomeGenWoodland(BOPConfiguration.woodlandID)).setColor(353825).setBiomeName("Woodland").func_76733_a(5159473).setTemperatureRainfall(2.0F, 0.2F).setMinMaxHeight(0.1F, 0.2F);
		plainsNew = (new BiomeGenPlainsNew(BOPConfiguration.plainsNewID)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
		desertNew = (new BiomeGenDesertNew(BOPConfiguration.desertNewID)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		extremeHillsNew = (new BiomeGenHillsNew(BOPConfiguration.extremeHillsNewID)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F);
		forestNew = (new BiomeGenForestNew(BOPConfiguration.forestNewID)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
		taigaNew = (new BiomeGenTaigaNew(BOPConfiguration.taigaNewID)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
		swamplandNew = (new BiomeGenSwampNew(BOPConfiguration.swamplandNewID)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
		jungleNew = (new BiomeGenJungleNew(BOPConfiguration.jungleNewID)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);


		//Initialize new world type
		WTBiomesOP = new WTBiomesOP();


		//Spawning
		BiomeManager.addSpawnBiome(alps);
		BiomeManager.addSpawnBiome(arctic);
		BiomeManager.addSpawnBiome(badlands);
		BiomeManager.addSpawnBiome(bambooForest);
		BiomeManager.addSpawnBiome(bayou);
		BiomeManager.addSpawnBiome(birchForest);
		BiomeManager.addSpawnBiome(bog);
		BiomeManager.addSpawnBiome(borealForest);
		BiomeManager.addSpawnBiome(canyon);
		BiomeManager.addSpawnBiome(chaparral);
		BiomeManager.addSpawnBiome(cherryBlossomGrove);
		BiomeManager.addSpawnBiome(coniferousForest);
		BiomeManager.addSpawnBiome(deadForest);
		BiomeManager.addSpawnBiome(deadSwamp);
		BiomeManager.addSpawnBiome(deciduousForest);
		BiomeManager.addSpawnBiome(drylands);
		BiomeManager.addSpawnBiome(dunes);
		BiomeManager.addSpawnBiome(fen);
		BiomeManager.addSpawnBiome(field);
		BiomeManager.addSpawnBiome(frostForest);
		BiomeManager.addSpawnBiome(glacier);
		BiomeManager.addSpawnBiome(grassland);
		BiomeManager.addSpawnBiome(grove);
		BiomeManager.addSpawnBiome(heathland);
		BiomeManager.addSpawnBiome(highland);
		BiomeManager.addSpawnBiome(iceSheet);
		BiomeManager.addSpawnBiome(jadeCliffs);
		BiomeManager.addSpawnBiome(lushDesert);
		BiomeManager.addSpawnBiome(lushSwamp);
		BiomeManager.addSpawnBiome(mangrove);
		BiomeManager.addSpawnBiome(mapleWoods);
		BiomeManager.addSpawnBiome(marsh);
		BiomeManager.addSpawnBiome(meadow);
		BiomeManager.addSpawnBiome(mesa);
		BiomeManager.addSpawnBiome(moor);
		BiomeManager.addSpawnBiome(mountain);
		BiomeManager.addSpawnBiome(oasis);
		BiomeManager.addSpawnBiome(orchard);
		BiomeManager.addSpawnBiome(outback);
		BiomeManager.addSpawnBiome(pasture);
		BiomeManager.addSpawnBiome(prairie);
		BiomeManager.addSpawnBiome(quagmire);
		BiomeManager.addSpawnBiome(rainforest);
		BiomeManager.addSpawnBiome(redwoodForest);
		BiomeManager.addSpawnBiome(savanna);
		BiomeManager.addSpawnBiome(scrubland);
		BiomeManager.addSpawnBiome(seasonalForest);
		BiomeManager.addSpawnBiome(shield);
		BiomeManager.addSpawnBiome(shrubland);
		BiomeManager.addSpawnBiome(snowyWoods);
		BiomeManager.addSpawnBiome(spruceWoods);
		BiomeManager.addSpawnBiome(swampwoods);
		BiomeManager.addSpawnBiome(temperateRainforest);
		BiomeManager.addSpawnBiome(thicket);
		BiomeManager.addSpawnBiome(tropicalRainforest);
		BiomeManager.addSpawnBiome(tropics);
		BiomeManager.addSpawnBiome(tundra);
		BiomeManager.addSpawnBiome(volcano);
		BiomeManager.addSpawnBiome(wetland);
		BiomeManager.addSpawnBiome(woodland);
		BiomeManager.addSpawnBiome(plainsNew);
		BiomeManager.addSpawnBiome(desertNew);
		BiomeManager.addSpawnBiome(forestNew);
		BiomeManager.addSpawnBiome(extremeHillsNew);
		BiomeManager.addSpawnBiome(taigaNew);
		BiomeManager.addSpawnBiome(swamplandNew);
		BiomeManager.addSpawnBiome(jungleNew);

		//Village spawning 
		BiomeManager.addVillageBiome(arctic, true);				
		BiomeManager.addVillageBiome(bayou, true);
		BiomeManager.addVillageBiome(birchForest, true);
		BiomeManager.addVillageBiome(chaparral, true);
		BiomeManager.addVillageBiome(coniferousForest, true);
		BiomeManager.addVillageBiome(deadForest, true);
		BiomeManager.addVillageBiome(field, true);
		BiomeManager.addVillageBiome(frostForest, true);
		BiomeManager.addVillageBiome(grassland, true);
		BiomeManager.addVillageBiome(grove, true);
		BiomeManager.addVillageBiome(heathland, true);
		BiomeManager.addVillageBiome(lushSwamp, true);
		BiomeManager.addVillageBiome(mapleWoods, true);
		BiomeManager.addVillageBiome(orchard, true);
		BiomeManager.addVillageBiome(prairie, true);
		BiomeManager.addVillageBiome(redwoodForest, true);
		BiomeManager.addVillageBiome(savanna, true);
		BiomeManager.addVillageBiome(scrubland, true);
		BiomeManager.addVillageBiome(shield, true);
		BiomeManager.addVillageBiome(shrubland, true);
		BiomeManager.addVillageBiome(snowyWoods, true);
		BiomeManager.addVillageBiome(spruceWoods, true);
		BiomeManager.addVillageBiome(tropicalRainforest, true);
		BiomeManager.addVillageBiome(woodland, true);
		BiomeManager.addVillageBiome(plainsNew, true);
		BiomeManager.addVillageBiome(desertNew, true);
		BiomeManager.addVillageBiome(forestNew, true);
		BiomeManager.addVillageBiome(taigaNew, true);
		BiomeManager.addVillageBiome(swamplandNew, true);

		//Stronghold spawning
		BiomeManager.addStrongholdBiome(alps);
		BiomeManager.addStrongholdBiome(arctic);
		BiomeManager.addStrongholdBiome(badlands);
		BiomeManager.addStrongholdBiome(bambooForest);
		BiomeManager.addStrongholdBiome(bayou);
		BiomeManager.addStrongholdBiome(birchForest);
		BiomeManager.addStrongholdBiome(bog);
		BiomeManager.addStrongholdBiome(borealForest);
		BiomeManager.addStrongholdBiome(canyon);
		BiomeManager.addStrongholdBiome(chaparral);
		BiomeManager.addStrongholdBiome(cherryBlossomGrove);
		BiomeManager.addStrongholdBiome(coniferousForest);
		BiomeManager.addStrongholdBiome(crag);
		BiomeManager.addStrongholdBiome(deadForest);
		BiomeManager.addStrongholdBiome(deadSwamp);
		BiomeManager.addStrongholdBiome(deadlands);
		BiomeManager.addStrongholdBiome(deciduousForest);
		BiomeManager.addStrongholdBiome(drylands);
		BiomeManager.addStrongholdBiome(dunes);
		BiomeManager.addStrongholdBiome(fen);
		BiomeManager.addStrongholdBiome(field);
		BiomeManager.addStrongholdBiome(frostForest);
		BiomeManager.addStrongholdBiome(fungiForest);
		BiomeManager.addStrongholdBiome(garden);
		BiomeManager.addStrongholdBiome(glacier);
		BiomeManager.addStrongholdBiome(grassland);
		BiomeManager.addStrongholdBiome(grove);
		BiomeManager.addStrongholdBiome(heathland);
		BiomeManager.addStrongholdBiome(highland);
		BiomeManager.addStrongholdBiome(iceSheet);
		BiomeManager.addStrongholdBiome(icyHills);
		BiomeManager.addStrongholdBiome(jadeCliffs);
		BiomeManager.addStrongholdBiome(lushDesert);
		BiomeManager.addStrongholdBiome(lushSwamp);
		BiomeManager.addStrongholdBiome(mangrove);
		BiomeManager.addStrongholdBiome(mapleWoods);
		BiomeManager.addStrongholdBiome(marsh);
		BiomeManager.addStrongholdBiome(meadow);
		BiomeManager.addStrongholdBiome(mesa);
		BiomeManager.addStrongholdBiome(moor);
		BiomeManager.addStrongholdBiome(mountain);
		BiomeManager.addStrongholdBiome(mysticGrove);
		BiomeManager.addStrongholdBiome(oasis);
		BiomeManager.addStrongholdBiome(ominousWoods);
		BiomeManager.addStrongholdBiome(orchard);
		BiomeManager.addStrongholdBiome(outback);
		BiomeManager.addStrongholdBiome(pasture);
		BiomeManager.addStrongholdBiome(prairie);
		BiomeManager.addStrongholdBiome(quagmire);
		BiomeManager.addStrongholdBiome(rainforest);
		BiomeManager.addStrongholdBiome(redwoodForest);
		BiomeManager.addStrongholdBiome(sacredSprings);
		BiomeManager.addStrongholdBiome(savanna);
		BiomeManager.addStrongholdBiome(scrubland);
		BiomeManager.addStrongholdBiome(seasonalForest);
		BiomeManager.addStrongholdBiome(shield);
		BiomeManager.addStrongholdBiome(shrubland);
		BiomeManager.addStrongholdBiome(snowyWoods);
		BiomeManager.addStrongholdBiome(spruceWoods);
		BiomeManager.addStrongholdBiome(steppe);
		BiomeManager.addStrongholdBiome(swampwoods);
		BiomeManager.addStrongholdBiome(temperateRainforest);
		BiomeManager.addStrongholdBiome(thicket);
		BiomeManager.addStrongholdBiome(tropicalRainforest);
		BiomeManager.addStrongholdBiome(tropics);
		BiomeManager.addStrongholdBiome(tundra);
		BiomeManager.addStrongholdBiome(volcano);
		BiomeManager.addStrongholdBiome(wasteland);
		BiomeManager.addStrongholdBiome(wetland);
		BiomeManager.addStrongholdBiome(woodland);
		BiomeManager.addStrongholdBiome(plainsNew);
		BiomeManager.addStrongholdBiome(desertNew);
		BiomeManager.addStrongholdBiome(forestNew);
		BiomeManager.addStrongholdBiome(extremeHillsNew);
		BiomeManager.addStrongholdBiome(taigaNew);
		BiomeManager.addStrongholdBiome(swamplandNew);
		BiomeManager.addStrongholdBiome(jungleNew);

		if (BOPConfiguration.addToDefault == true)
		{
			if (BOPConfiguration.alpsGen == true)
			{
				GameRegistry.addBiome(alps);
			}
			if (BOPConfiguration.arcticGen == true)
			{
				GameRegistry.addBiome(arctic);
			}
			if (BOPConfiguration.badlandsGen == true)
			{
				GameRegistry.addBiome(badlands);
			}
			if (BOPConfiguration.bambooForestGen == true)
			{
				GameRegistry.addBiome(bambooForest);
			}
			if (BOPConfiguration.bayouGen == true)
			{
				GameRegistry.addBiome(bayou);
			}
			if (BOPConfiguration.birchForestGen == true)
			{
				GameRegistry.addBiome(birchForest);
			}
			if (BOPConfiguration.bogGen == true)
			{
				GameRegistry.addBiome(bog);
			}
			if (BOPConfiguration.borealForestGen == true)
			{
				GameRegistry.addBiome(borealForest);
			}
			if (BOPConfiguration.canyonGen == true)
			{
				GameRegistry.addBiome(canyon);
			}
			if (BOPConfiguration.chaparralGen == true)
			{
				GameRegistry.addBiome(chaparral);
			}
			if (BOPConfiguration.cherryBlossomGroveGen == true)
			{
				GameRegistry.addBiome(cherryBlossomGrove);
			}
			if (BOPConfiguration.coniferousForestGen == true)
			{
				GameRegistry.addBiome(coniferousForest);
			}
			if (BOPConfiguration.cragGen == true)
			{
				GameRegistry.addBiome(crag);
			}
			if (BOPConfiguration.deadForestGen == true)
			{
				GameRegistry.addBiome(deadForest);
			}
			if (BOPConfiguration.deadSwampGen == true)
			{
				GameRegistry.addBiome(deadSwamp);
			}
			if (BOPConfiguration.deadlandsGen == true)
			{
				GameRegistry.addBiome(deadlands);
			}
			if (BOPConfiguration.deciduousForestGen == true)
			{
				GameRegistry.addBiome(deciduousForest);
			}
			if (BOPConfiguration.drylandsGen == true)
			{
				GameRegistry.addBiome(drylands);
			}
			if (BOPConfiguration.dunesGen == true)
			{
				GameRegistry.addBiome(dunes);
			}
			if (BOPConfiguration.fenGen == true)
			{
				GameRegistry.addBiome(fen);
			}
			if (BOPConfiguration.fieldGen == true)
			{
				GameRegistry.addBiome(field);
			}
			if (BOPConfiguration.frostForestGen == true)
			{
				GameRegistry.addBiome(frostForest);
			}
			if (BOPConfiguration.fungiForestGen == true)
			{
				GameRegistry.addBiome(fungiForest);
			}
			if (BOPConfiguration.gardenGen == true)
			{
				GameRegistry.addBiome(garden);
			}
			if (BOPConfiguration.glacierGen == true)
			{
				GameRegistry.addBiome(glacier);
			}
			if (BOPConfiguration.grasslandGen == true)
			{
				GameRegistry.addBiome(grassland);
			}
			if (BOPConfiguration.groveGen == true)
			{
				GameRegistry.addBiome(grove);
			}
			if (BOPConfiguration.heathlandGen == true)
			{
				GameRegistry.addBiome(heathland);
			}
			if (BOPConfiguration.highlandGen == true)
			{
				GameRegistry.addBiome(highland);
			}
			if (BOPConfiguration.iceSheetGen == true)
			{
				GameRegistry.addBiome(iceSheet);
			}
			if (BOPConfiguration.icyHillsGen == true)
			{
				GameRegistry.addBiome(icyHills);
			}
			if (BOPConfiguration.jadeCliffsGen == true)
			{
				GameRegistry.addBiome(jadeCliffs);
			}
			if (BOPConfiguration.lushDesertGen == true)
			{
				GameRegistry.addBiome(lushDesert);
			}
			if (BOPConfiguration.lushSwampGen == true)
			{
				GameRegistry.addBiome(lushSwamp);
			}
			if (BOPConfiguration.mangroveGen == true)
			{
				GameRegistry.addBiome(mangrove);
			}
			if (BOPConfiguration.mapleWoodsGen == true)
			{
				GameRegistry.addBiome(mapleWoods);
			}
			if (BOPConfiguration.marshGen == true)
			{
				GameRegistry.addBiome(marsh);
			}
			if (BOPConfiguration.meadowGen == true)
			{
				GameRegistry.addBiome(meadow);
			}
			if (BOPConfiguration.mesaGen == true)
			{
				GameRegistry.addBiome(mesa);
			}
			if (BOPConfiguration.moorGen == true)
			{
				GameRegistry.addBiome(moor);
			}
			if (BOPConfiguration.mountainGen == true)
			{
				GameRegistry.addBiome(mountain);
			}
			if (BOPConfiguration.mushroomIslandGen == true)
			{
				GameRegistry.addBiome(BiomeGenBase.mushroomIsland);
			}
			if (BOPConfiguration.mysticGroveGen == true)
			{
				GameRegistry.addBiome(mysticGrove);
			}
			if (BOPConfiguration.oasisGen == true)
			{
				GameRegistry.addBiome(oasis);
			}
			if (BOPConfiguration.ominousWoodsGen == true)
			{
				GameRegistry.addBiome(ominousWoods);
			}
			if (BOPConfiguration.orchardGen == true)
			{
				GameRegistry.addBiome(orchard);
			}
			if (BOPConfiguration.originValleyGen == true)
			{
				GameRegistry.addBiome(originValley);
			}
			if (BOPConfiguration.outbackGen == true)
			{
				GameRegistry.addBiome(outback);
			}
			if (BOPConfiguration.pastureGen == true)
			{
				GameRegistry.addBiome(pasture);
			}
			if (BOPConfiguration.prairieGen == true)
			{
				GameRegistry.addBiome(prairie);
			}
			if (BOPConfiguration.quagmireGen == true)
			{
				GameRegistry.addBiome(quagmire);
			}
			if (BOPConfiguration.rainforestGen == true)
			{
				GameRegistry.addBiome(rainforest);
			}
			if (BOPConfiguration.redwoodForestGen == true)
			{
				GameRegistry.addBiome(redwoodForest);
			}
			if (BOPConfiguration.sacredSpringsGen == true)
			{
				GameRegistry.addBiome(sacredSprings);
			}
			if (BOPConfiguration.savannaGen == true)
			{
				GameRegistry.addBiome(savanna);
			}
			if (BOPConfiguration.scrublandGen == true)
			{
				GameRegistry.addBiome(scrubland);
			}
			if (BOPConfiguration.seasonalForestGen == true)
			{
				GameRegistry.addBiome(seasonalForest);
			}
			if (BOPConfiguration.shieldGen == true)
			{
				GameRegistry.addBiome(shield);
			}
			if (BOPConfiguration.shrublandGen == true)
			{
				GameRegistry.addBiome(shrubland);
			}
			if (BOPConfiguration.snowyWoodsGen == true)
			{
				GameRegistry.addBiome(snowyWoods);
			}
			if (BOPConfiguration.spruceWoodsGen == true)
			{
				GameRegistry.addBiome(spruceWoods);
			}
			if (BOPConfiguration.steppeGen == true)
			{
				GameRegistry.addBiome(steppe);
			}
			if (BOPConfiguration.swampwoodsGen == true)
			{
				GameRegistry.addBiome(swampwoods);
			}
			if (BOPConfiguration.temperateRainforestGen == true)
			{
				GameRegistry.addBiome(temperateRainforest);
			}
			if (BOPConfiguration.thicketGen == true)
			{
				GameRegistry.addBiome(thicket);
			}
			if (BOPConfiguration.tropicalRainforestGen == true)
			{
				GameRegistry.addBiome(tropicalRainforest);
			}
			if (BOPConfiguration.tropicsGen == true)
			{
				GameRegistry.addBiome(tropics);
			}
			if (BOPConfiguration.tundraGen == true)
			{
				GameRegistry.addBiome(tundra);
			}
			if (BOPConfiguration.volcanoGen == true)
			{
				GameRegistry.addBiome(volcano);
			}
			if (BOPConfiguration.wastelandGen == true)
			{
				GameRegistry.addBiome(wasteland);
			}
			if (BOPConfiguration.wetlandGen == true)
			{
				GameRegistry.addBiome(wetland);
			}
			if (BOPConfiguration.woodlandGen == true)
			{
				GameRegistry.addBiome(woodland);
			}
			if (BOPConfiguration.plainsGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(plainsNew);
					GameRegistry.removeBiome(BiomeGenBase.plains);
				}
			}
			if (BOPConfiguration.desertGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(desertNew);
					GameRegistry.removeBiome(BiomeGenBase.desert);
				}
			}
			if (BOPConfiguration.extremeHillsGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(extremeHillsNew);
					GameRegistry.removeBiome(BiomeGenBase.extremeHills);
				}
			}
			if (BOPConfiguration.forestGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(forestNew);
					GameRegistry.removeBiome(BiomeGenBase.forest);
				}
			}
			if (BOPConfiguration.taigaGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(taigaNew);
					GameRegistry.removeBiome(BiomeGenBase.taiga);
				}
			}
			if (BOPConfiguration.swamplandGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(swamplandNew);
					GameRegistry.removeBiome(BiomeGenBase.swampland);
				}
			}
			if (BOPConfiguration.jungleGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(jungleNew);
					GameRegistry.removeBiome(BiomeGenBase.jungle);
				}
			}
		}
	}
}
