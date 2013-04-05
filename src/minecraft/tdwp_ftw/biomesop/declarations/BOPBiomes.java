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
		alps = (new BiomeGenAlps(mod_BiomesOPlenty.alpsID)).setColor(353825).setBiomeName("Alps").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(5.0F, 5.0F);
		arctic = (new BiomeGenArctic(mod_BiomesOPlenty.arcticID)).setColor(14090235).setBiomeName("Arctic").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.7F);
		badlands = (new BiomeGenBadlands(mod_BiomesOPlenty.badlandsID)).setColor(16421912).setBiomeName("Badlands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.9F);
		bambooForest = (new BiomeGenBambooForest(mod_BiomesOPlenty.bambooForestID)).setColor(112).setBiomeName("Bamboo Forest").setMinMaxHeight(0.0F, 0.3F);
		bayou = (new BiomeGenBayou(mod_BiomesOPlenty.bayouID)).setColor(522674).setBiomeName("Bayou").func_76733_a(9154376).setMinMaxHeight(-0.3F, 0.2F);
		birchForest = (new BiomeGenBirchForest(mod_BiomesOPlenty.birchForestID)).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.3F);
		bog = (new BiomeGenBog(mod_BiomesOPlenty.bogID)).setColor(522674).setBiomeName("Bog").func_76733_a(9154376).setMinMaxHeight(-0.3F, -0.1F).setTemperatureRainfall(0.8F, 0.9F);
		borealForest = (new BiomeGenBorealForest(mod_BiomesOPlenty.borealForestID)).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.0F, 1.0F).setTemperatureRainfall(0.6F, 0.7F);
		canyon = (new BiomeGenCanyon(mod_BiomesOPlenty.canyonID)).setColor(9286496).setBiomeName("Canyon").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(3.0F, 5.0F);
		chaparral = (new BiomeGenChaparral(mod_BiomesOPlenty.chaparralID)).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F);
		cherryBlossomGrove = (new BiomeGenCherryBlossomGrove(mod_BiomesOPlenty.cherryBlossomGroveID)).setColor(9286496).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.1F, 0.2F);
		coniferousForest = (new BiomeGenConiferousForest(mod_BiomesOPlenty.coniferousForestID)).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.4F).setMinMaxHeight(0.1F, 0.8F);
		crag = (new BiomeGenCrag(mod_BiomesOPlenty.cragID)).setColor(9286496).setBiomeName("Crag").setMinMaxHeight(0.0F, 9.9F);
		deadForest = (new BiomeGenDeadForest(mod_BiomesOPlenty.deadForestID)).setColor(522674).setBiomeName("Dead Forest").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.7F);
		deadSwamp = (new BiomeGenDeadSwamp(mod_BiomesOPlenty.deadSwampID)).setColor(522674).setBiomeName("Dead Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
		deadlands = (new BiomeGenDeadlands(mod_BiomesOPlenty.deadlandsID)).setColor(522674).setBiomeName("Deadlands").setDisableRain().func_76733_a(9154376).setMinMaxHeight(0.1F, 0.5F);
		deciduousForest = (new BiomeGenDeciduousForest(mod_BiomesOPlenty.deciduousForestID)).setColor(353825).setBiomeName("Deciduous Forest").func_76733_a(5159473);
		drylands = (new BiomeGenDrylands(mod_BiomesOPlenty.drylandsID)).setColor(16421912).setBiomeName("Drylands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.5F);
		dunes = (new BiomeGenDunes(mod_BiomesOPlenty.dunesID)).setColor(13786898).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.3F);
		fen = (new BiomeGenFen(mod_BiomesOPlenty.fenID)).setColor(9286496).setBiomeName("Fen").setTemperatureRainfall(0.4F, 0.0F).setMinMaxHeight(-0.2F, 0.1F);
		field = (new BiomeGenField(mod_BiomesOPlenty.fieldID)).setColor(9286496).setBiomeName("Field").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F);
		frostForest = (new BiomeGenFrostForest(mod_BiomesOPlenty.frostForestID)).setColor(14090235).setBiomeName("Frost Forest").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
		fungiForest = (new BiomeGenFungiForest(mod_BiomesOPlenty.fungiForestID)).setColor(747097).setBiomeName("Fungi Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.0F, 0.4F);
		garden = (new BiomeGenGarden(mod_BiomesOPlenty.gardenID)).setColor(9286496).setBiomeName("Garden").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.2F);
		glacier = (new BiomeGenGlacier(mod_BiomesOPlenty.glacierID)).setColor(6316128).setBiomeName("Glacier").setEnableSnow().setMinMaxHeight(0.4F, 1.0F).setTemperatureRainfall(0.0F, 0.0F);
		grassland = (new BiomeGenGrassland(mod_BiomesOPlenty.grasslandID)).setColor(9286496).setBiomeName("Grassland").setTemperatureRainfall(0.7F, 0.7F).setMinMaxHeight(0.2F, 0.2F);
		grove = (new BiomeGenGrove(mod_BiomesOPlenty.groveID)).setColor(9286496).setBiomeName("Grove").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F);
		heathland = (new BiomeGenHeathland(mod_BiomesOPlenty.heathlandID)).setColor(353825).setBiomeName("Heathland").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.1F, 0.3F);
		highland = (new BiomeGenHighland(mod_BiomesOPlenty.highlandID)).setColor(6316128).setBiomeName("Highland").setMinMaxHeight(0.9F, 1.9F).setTemperatureRainfall(0.5F, 0.5F);
		iceSheet = (new BiomeGenIceSheet(mod_BiomesOPlenty.iceSheetID)).setColor(6316128).setBiomeName("Ice Sheet").setEnableSnow().setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.0F, 0.0F);
		icyHills = (new BiomeGenIcyHills(mod_BiomesOPlenty.icyHillsID)).setColor(14090235).setBiomeName("Icy Hills").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.5F);
		jadeCliffs = (new BiomeGenJadeCliffs(mod_BiomesOPlenty.jadeCliffsID)).setColor(14090235).setBiomeName("Jade Cliffs").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.1F, 2.0F);
		lushDesert = (new BiomeGenLushDesert(mod_BiomesOPlenty.lushDesertID)).setColor(16421912).setBiomeName("Lush Desert").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.8F);
		lushSwamp = (new BiomeGenLushSwamp(mod_BiomesOPlenty.lushSwampID)).setColor(522674).setBiomeName("Lush Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.7F, 1.0F);
		mangrove = (new BiomeGenMangrove(mod_BiomesOPlenty.mangroveID)).setColor(16440917).setBiomeName("Mangrove").setMinMaxHeight(-0.4F, -0.1F);
		mapleWoods = (new BiomeGenMapleWoods(mod_BiomesOPlenty.mapleWoodsID)).setColor(747097).setBiomeName("Maple Woods").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 0.6F);
		marsh = (new BiomeGenMarsh(mod_BiomesOPlenty.marshID)).setColor(10486015).setBiomeName("Marsh").setMinMaxHeight(-0.5F, 0.0F);
		meadow = (new BiomeGenMeadow(mod_BiomesOPlenty.meadowID)).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 0.7F);
		mesa = (new BiomeGenMesa(mod_BiomesOPlenty.mesaID)).setColor(16421912).setBiomeName("Mesa").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.8F, 1.0F);
		moor = (new BiomeGenMoor(mod_BiomesOPlenty.moorID)).setColor(16421912).setBiomeName("Moor").setTemperatureRainfall(0.5F, 1.0F).setMinMaxHeight(0.7F, 0.8F);
		mountain = (new BiomeGenMountain(mod_BiomesOPlenty.mountainID)).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(1.2F, 1.2F);
		mysticGrove = (new BiomeGenMysticGrove(mod_BiomesOPlenty.mysticGroveID)).setColor(353825).setBiomeName("Mystic Grove").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F);
		oasis = (new BiomeGenOasis(mod_BiomesOPlenty.oasisID)).setColor(16421912).setBiomeName("Oasis").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.2F);
		ominousWoods = (new BiomeGenOminousWoods(mod_BiomesOPlenty.ominousWoodsID)).setColor(353825).setBiomeName("Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F);
		orchard = (new BiomeGenOrchard(mod_BiomesOPlenty.orchardID)).setColor(9286496).setBiomeName("Orchard").setTemperatureRainfall(0.8F, 0.4F);
		originValley = (new BiomeGenOriginValley(mod_BiomesOPlenty.originValleyID)).setColor(353825).setBiomeName("Origin Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(-0.1F, 0.6F);
		outback = (new BiomeGenOutback(mod_BiomesOPlenty.outbackID)).setColor(9286496).setBiomeName("Outback").setTemperatureRainfall(0.8F, 0.0F).setMinMaxHeight(0.1F, 0.1F);
		pasture = (new BiomeGenPasture(mod_BiomesOPlenty.pastureID)).setColor(9286496).setBiomeName("Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.1F, 0.2F);
		prairie = (new BiomeGenPrairie(mod_BiomesOPlenty.prairieID)).setColor(353825).setBiomeName("Prairie").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.6F).setMinMaxHeight(0.1F, 0.1F);
		promisedLand = (new BiomeGenPromisedLand(mod_BiomesOPlenty.promisedLandID)).setColor(112).setBiomeName("Promised Land").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F);
		quagmire = (new BiomeGenQuagmire(mod_BiomesOPlenty.quagmireID)).setColor(522674).setBiomeName("Quagmire").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F);
		rainforest = (new BiomeGenRainforest(mod_BiomesOPlenty.rainforestID)).setColor(5470985).setBiomeName("Rainforest").func_76733_a(5470985).setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.7F, 1.8F);
		redwoodForest = (new BiomeGenRedwoodForest(mod_BiomesOPlenty.redwoodForestID)).setColor(747097).setBiomeName("Redwood Forest").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.4F);
		sacredSprings = (new BiomeGenSacredSprings(mod_BiomesOPlenty.sacredSpringsID)).setColor(522674).setBiomeName("Sacred Springs").func_76733_a(9154376).setMinMaxHeight(0.0F, 1.2F).setTemperatureRainfall(0.8F, 0.9F);
		savanna = (new BiomeGenSavanna(mod_BiomesOPlenty.savannaID)).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(2.0F, 0.1F).setMinMaxHeight(0.1F, 0.1F);
		scrubland = (new BiomeGenScrubland(mod_BiomesOPlenty.scrublandID)).setColor(9286496).setBiomeName("Scrubland").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.3F);
		seasonalForest = (new BiomeGenSeasonalForest(mod_BiomesOPlenty.seasonalForestID)).setColor(353825).setBiomeName("Seasonal Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
		shield = (new BiomeGenShield(mod_BiomesOPlenty.shieldID)).setColor(522674).setBiomeName("Shield").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.5F, 0.8F);
		shore = (new BiomeGenShore(mod_BiomesOPlenty.shoreID)).setColor(9286496).setBiomeName("Shore").setMinMaxHeight(-1.0F, 0.4F);
		shrubland = (new BiomeGenShrubland(mod_BiomesOPlenty.shrublandID)).setColor(9286496).setBiomeName("Shrubland").setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.6F, 0.0F);
		snowyWoods = (new BiomeGenSnowyWoods(mod_BiomesOPlenty.snowyWoodsID)).setColor(522674).setBiomeName("Snowy Woods").func_76733_a(9154376).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F);
		spruceWoods = (new BiomeGenSpruceWoods(mod_BiomesOPlenty.spruceWoodsID)).setColor(353825).setBiomeName("Spruce Woods").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.7F);
		steppe = (new BiomeGenSteppe(mod_BiomesOPlenty.steppeID)).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		swampwoods = (new BiomeGenSwampwoods(mod_BiomesOPlenty.swampwoodsID)).setColor(522674).setBiomeName("Swampwoods").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.2F).setTemperatureRainfall(0.8F, 0.9F);
		temperateRainforest = (new BiomeGenTemperateRainforest(mod_BiomesOPlenty.temperateRainforestID)).setColor(353825).setBiomeName("Temperate Rainforest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 1.2F);
		thicket = (new BiomeGenThicket(mod_BiomesOPlenty.thicketID)).setColor(353825).setBiomeName("Thicket").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.2F).setMinMaxHeight(0.0F, 0.2F);
		tropicalRainforest = (new BiomeGenTropicalRainforest(mod_BiomesOPlenty.tropicalRainforestID)).setColor(9286496).setBiomeName("Tropical Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.7F);
		tropics = (new BiomeGenTropics(mod_BiomesOPlenty.tropicsID)).setColor(9286496).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.8F);
		tundra = (new BiomeGenTundra(mod_BiomesOPlenty.tundraID)).setColor(14090235).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.0F);
		volcano = (new BiomeGenVolcano(mod_BiomesOPlenty.volcanoID)).setColor(9286496).setBiomeName("Volcano").setDisableRain().setMinMaxHeight(0.6F, 0.9F);
		wasteland = (new BiomeGenWasteland(mod_BiomesOPlenty.wastelandID)).setColor(16421912).setBiomeName("Wasteland").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.0F);
		wetland = (new BiomeGenWetland(mod_BiomesOPlenty.wetlandID)).setColor(522674).setBiomeName("Wetland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.8F, 0.9F);
		woodland = (new BiomeGenWoodland(mod_BiomesOPlenty.woodlandID)).setColor(353825).setBiomeName("Woodland").func_76733_a(5159473).setTemperatureRainfall(2.0F, 0.2F).setMinMaxHeight(0.1F, 0.2F);
		plainsNew = (new BiomeGenPlainsNew(mod_BiomesOPlenty.plainsNewID)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
		desertNew = (new BiomeGenDesertNew(mod_BiomesOPlenty.desertNewID)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		extremeHillsNew = (new BiomeGenHillsNew(mod_BiomesOPlenty.extremeHillsNewID)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F);
		forestNew = (new BiomeGenForestNew(mod_BiomesOPlenty.forestNewID)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F);
		taigaNew = (new BiomeGenTaigaNew(mod_BiomesOPlenty.taigaNewID)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
		swamplandNew = (new BiomeGenSwampNew(mod_BiomesOPlenty.swamplandNewID)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
		jungleNew = (new BiomeGenJungleNew(mod_BiomesOPlenty.jungleNewID)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F);


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

		if (mod_BiomesOPlenty.addToDefault == true)
		{
			if (mod_BiomesOPlenty.alpsGen == true)
			{
				GameRegistry.addBiome(alps);
			}
			if (mod_BiomesOPlenty.arcticGen == true)
			{
				GameRegistry.addBiome(arctic);
			}
			if (mod_BiomesOPlenty.badlandsGen == true)
			{
				GameRegistry.addBiome(badlands);
			}
			if (mod_BiomesOPlenty.bambooForestGen == true)
			{
				GameRegistry.addBiome(bambooForest);
			}
			if (mod_BiomesOPlenty.bayouGen == true)
			{
				GameRegistry.addBiome(bayou);
			}
			if (mod_BiomesOPlenty.birchForestGen == true)
			{
				GameRegistry.addBiome(birchForest);
			}
			if (mod_BiomesOPlenty.bogGen == true)
			{
				GameRegistry.addBiome(bog);
			}
			if (mod_BiomesOPlenty.borealForestGen == true)
			{
				GameRegistry.addBiome(borealForest);
			}
			if (mod_BiomesOPlenty.canyonGen == true)
			{
				GameRegistry.addBiome(canyon);
			}
			if (mod_BiomesOPlenty.chaparralGen == true)
			{
				GameRegistry.addBiome(chaparral);
			}
			if (mod_BiomesOPlenty.cherryBlossomGroveGen == true)
			{
				GameRegistry.addBiome(cherryBlossomGrove);
			}
			if (mod_BiomesOPlenty.coniferousForestGen == true)
			{
				GameRegistry.addBiome(coniferousForest);
			}
			if (mod_BiomesOPlenty.cragGen == true)
			{
				GameRegistry.addBiome(crag);
			}
			if (mod_BiomesOPlenty.deadForestGen == true)
			{
				GameRegistry.addBiome(deadForest);
			}
			if (mod_BiomesOPlenty.deadSwampGen == true)
			{
				GameRegistry.addBiome(deadSwamp);
			}
			if (mod_BiomesOPlenty.deadlandsGen == true)
			{
				GameRegistry.addBiome(deadlands);
			}
			if (mod_BiomesOPlenty.deciduousForestGen == true)
			{
				GameRegistry.addBiome(deciduousForest);
			}
			if (mod_BiomesOPlenty.drylandsGen == true)
			{
				GameRegistry.addBiome(drylands);
			}
			if (mod_BiomesOPlenty.dunesGen == true)
			{
				GameRegistry.addBiome(dunes);
			}
			if (mod_BiomesOPlenty.fenGen == true)
			{
				GameRegistry.addBiome(fen);
			}
			if (mod_BiomesOPlenty.fieldGen == true)
			{
				GameRegistry.addBiome(field);
			}
			if (mod_BiomesOPlenty.frostForestGen == true)
			{
				GameRegistry.addBiome(frostForest);
			}
			if (mod_BiomesOPlenty.fungiForestGen == true)
			{
				GameRegistry.addBiome(fungiForest);
			}
			if (mod_BiomesOPlenty.gardenGen == true)
			{
				GameRegistry.addBiome(garden);
			}
			if (mod_BiomesOPlenty.glacierGen == true)
			{
				GameRegistry.addBiome(glacier);
			}
			if (mod_BiomesOPlenty.grasslandGen == true)
			{
				GameRegistry.addBiome(grassland);
			}
			if (mod_BiomesOPlenty.groveGen == true)
			{
				GameRegistry.addBiome(grove);
			}
			if (mod_BiomesOPlenty.heathlandGen == true)
			{
				GameRegistry.addBiome(heathland);
			}
			if (mod_BiomesOPlenty.highlandGen == true)
			{
				GameRegistry.addBiome(highland);
			}
			if (mod_BiomesOPlenty.iceSheetGen == true)
			{
				GameRegistry.addBiome(iceSheet);
			}
			if (mod_BiomesOPlenty.icyHillsGen == true)
			{
				GameRegistry.addBiome(icyHills);
			}
			if (mod_BiomesOPlenty.jadeCliffsGen == true)
			{
				GameRegistry.addBiome(jadeCliffs);
			}
			if (mod_BiomesOPlenty.lushDesertGen == true)
			{
				GameRegistry.addBiome(lushDesert);
			}
			if (mod_BiomesOPlenty.lushSwampGen == true)
			{
				GameRegistry.addBiome(lushSwamp);
			}
			if (mod_BiomesOPlenty.mangroveGen == true)
			{
				GameRegistry.addBiome(mangrove);
			}
			if (mod_BiomesOPlenty.mapleWoodsGen == true)
			{
				GameRegistry.addBiome(mapleWoods);
			}
			if (mod_BiomesOPlenty.marshGen == true)
			{
				GameRegistry.addBiome(marsh);
			}
			if (mod_BiomesOPlenty.meadowGen == true)
			{
				GameRegistry.addBiome(meadow);
			}
			if (mod_BiomesOPlenty.mesaGen == true)
			{
				GameRegistry.addBiome(mesa);
			}
			if (mod_BiomesOPlenty.moorGen == true)
			{
				GameRegistry.addBiome(moor);
			}
			if (mod_BiomesOPlenty.mountainGen == true)
			{
				GameRegistry.addBiome(mountain);
			}
			if (mod_BiomesOPlenty.mushroomIslandGen == true)
			{
				GameRegistry.addBiome(BiomeGenBase.mushroomIsland);
			}
			if (mod_BiomesOPlenty.mysticGroveGen == true)
			{
				GameRegistry.addBiome(mysticGrove);
			}
			if (mod_BiomesOPlenty.oasisGen == true)
			{
				GameRegistry.addBiome(oasis);
			}
			if (mod_BiomesOPlenty.ominousWoodsGen == true)
			{
				GameRegistry.addBiome(ominousWoods);
			}
			if (mod_BiomesOPlenty.orchardGen == true)
			{
				GameRegistry.addBiome(orchard);
			}
			if (mod_BiomesOPlenty.originValleyGen == true)
			{
				GameRegistry.addBiome(originValley);
			}
			if (mod_BiomesOPlenty.outbackGen == true)
			{
				GameRegistry.addBiome(outback);
			}
			if (mod_BiomesOPlenty.pastureGen == true)
			{
				GameRegistry.addBiome(pasture);
			}
			if (mod_BiomesOPlenty.prairieGen == true)
			{
				GameRegistry.addBiome(prairie);
			}
			if (mod_BiomesOPlenty.quagmireGen == true)
			{
				GameRegistry.addBiome(quagmire);
			}
			if (mod_BiomesOPlenty.rainforestGen == true)
			{
				GameRegistry.addBiome(rainforest);
			}
			if (mod_BiomesOPlenty.redwoodForestGen == true)
			{
				GameRegistry.addBiome(redwoodForest);
			}
			if (mod_BiomesOPlenty.sacredSpringsGen == true)
			{
				GameRegistry.addBiome(sacredSprings);
			}
			if (mod_BiomesOPlenty.savannaGen == true)
			{
				GameRegistry.addBiome(savanna);
			}
			if (mod_BiomesOPlenty.scrublandGen == true)
			{
				GameRegistry.addBiome(scrubland);
			}
			if (mod_BiomesOPlenty.seasonalForestGen == true)
			{
				GameRegistry.addBiome(seasonalForest);
			}
			if (mod_BiomesOPlenty.shieldGen == true)
			{
				GameRegistry.addBiome(shield);
			}
			if (mod_BiomesOPlenty.shrublandGen == true)
			{
				GameRegistry.addBiome(shrubland);
			}
			if (mod_BiomesOPlenty.snowyWoodsGen == true)
			{
				GameRegistry.addBiome(snowyWoods);
			}
			if (mod_BiomesOPlenty.spruceWoodsGen == true)
			{
				GameRegistry.addBiome(spruceWoods);
			}
			if (mod_BiomesOPlenty.steppeGen == true)
			{
				GameRegistry.addBiome(steppe);
			}
			if (mod_BiomesOPlenty.swampwoodsGen == true)
			{
				GameRegistry.addBiome(swampwoods);
			}
			if (mod_BiomesOPlenty.temperateRainforestGen == true)
			{
				GameRegistry.addBiome(temperateRainforest);
			}
			if (mod_BiomesOPlenty.thicketGen == true)
			{
				GameRegistry.addBiome(thicket);
			}
			if (mod_BiomesOPlenty.tropicalRainforestGen == true)
			{
				GameRegistry.addBiome(tropicalRainforest);
			}
			if (mod_BiomesOPlenty.tropicsGen == true)
			{
				GameRegistry.addBiome(tropics);
			}
			if (mod_BiomesOPlenty.tundraGen == true)
			{
				GameRegistry.addBiome(tundra);
			}
			if (mod_BiomesOPlenty.volcanoGen == true)
			{
				GameRegistry.addBiome(volcano);
			}
			if (mod_BiomesOPlenty.wastelandGen == true)
			{
				GameRegistry.addBiome(wasteland);
			}
			if (mod_BiomesOPlenty.wetlandGen == true)
			{
				GameRegistry.addBiome(wetland);
			}
			if (mod_BiomesOPlenty.woodlandGen == true)
			{
				GameRegistry.addBiome(woodland);
			}
			if (mod_BiomesOPlenty.plainsGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(plainsNew);
					GameRegistry.removeBiome(BiomeGenBase.plains);
				}
			}
			if (mod_BiomesOPlenty.desertGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(desertNew);
					GameRegistry.removeBiome(BiomeGenBase.desert);
				}
			}
			if (mod_BiomesOPlenty.extremeHillsGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(extremeHillsNew);
					GameRegistry.removeBiome(BiomeGenBase.extremeHills);
				}
			}
			if (mod_BiomesOPlenty.forestGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(forestNew);
					GameRegistry.removeBiome(BiomeGenBase.forest);
				}
			}
			if (mod_BiomesOPlenty.taigaGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(taigaNew);
					GameRegistry.removeBiome(BiomeGenBase.taiga);
				}
			}
			if (mod_BiomesOPlenty.swamplandGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(swamplandNew);
					GameRegistry.removeBiome(BiomeGenBase.swampland);
				}
			}
			if (mod_BiomesOPlenty.jungleGen == true)
			{
				if (mod_BiomesOPlenty.vanillaEnhanced == true)
				{
					GameRegistry.addBiome(jungleNew);
					GameRegistry.removeBiome(BiomeGenBase.jungle);
				}
			}
		}
	}
}
