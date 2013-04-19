package biomesoplenty.configuration;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

import biomesoplenty.api.Biomes;
import biomesoplenty.biomes.BiomeGenAlps;
import biomesoplenty.biomes.BiomeGenArctic;
import biomesoplenty.biomes.BiomeGenBadlands;
import biomesoplenty.biomes.BiomeGenBambooForest;
import biomesoplenty.biomes.BiomeGenBayou;
import biomesoplenty.biomes.BiomeGenBirchForest;
import biomesoplenty.biomes.BiomeGenBog;
import biomesoplenty.biomes.BiomeGenBorealForest;
import biomesoplenty.biomes.BiomeGenCanyon;
import biomesoplenty.biomes.BiomeGenChaparral;
import biomesoplenty.biomes.BiomeGenCherryBlossomGrove;
import biomesoplenty.biomes.BiomeGenConiferousForest;
import biomesoplenty.biomes.BiomeGenCrag;
import biomesoplenty.biomes.BiomeGenDeadForest;
import biomesoplenty.biomes.BiomeGenDeadSwamp;
import biomesoplenty.biomes.BiomeGenDeadlands;
import biomesoplenty.biomes.BiomeGenDeciduousForest;
import biomesoplenty.biomes.BiomeGenDesertNew;
import biomesoplenty.biomes.BiomeGenDrylands;
import biomesoplenty.biomes.BiomeGenDunes;
import biomesoplenty.biomes.BiomeGenFen;
import biomesoplenty.biomes.BiomeGenField;
import biomesoplenty.biomes.BiomeGenForestNew;
import biomesoplenty.biomes.BiomeGenFrostForest;
import biomesoplenty.biomes.BiomeGenFungiForest;
import biomesoplenty.biomes.BiomeGenGarden;
import biomesoplenty.biomes.BiomeGenGlacier;
import biomesoplenty.biomes.BiomeGenGrassland;
import biomesoplenty.biomes.BiomeGenGrove;
import biomesoplenty.biomes.BiomeGenHeathland;
import biomesoplenty.biomes.BiomeGenHighland;
import biomesoplenty.biomes.BiomeGenHillsNew;
import biomesoplenty.biomes.BiomeGenIceSheet;
import biomesoplenty.biomes.BiomeGenIcyHills;
import biomesoplenty.biomes.BiomeGenJadeCliffs;
import biomesoplenty.biomes.BiomeGenJungleNew;
import biomesoplenty.biomes.BiomeGenLushDesert;
import biomesoplenty.biomes.BiomeGenLushSwamp;
import biomesoplenty.biomes.BiomeGenMangrove;
import biomesoplenty.biomes.BiomeGenMapleWoods;
import biomesoplenty.biomes.BiomeGenMarsh;
import biomesoplenty.biomes.BiomeGenMeadow;
import biomesoplenty.biomes.BiomeGenMesa;
import biomesoplenty.biomes.BiomeGenMoor;
import biomesoplenty.biomes.BiomeGenMountain;
import biomesoplenty.biomes.BiomeGenMysticGrove;
import biomesoplenty.biomes.BiomeGenOasis;
import biomesoplenty.biomes.BiomeGenOminousWoods;
import biomesoplenty.biomes.BiomeGenOrchard;
import biomesoplenty.biomes.BiomeGenOriginValley;
import biomesoplenty.biomes.BiomeGenOutback;
import biomesoplenty.biomes.BiomeGenPasture;
import biomesoplenty.biomes.BiomeGenPlainsNew;
import biomesoplenty.biomes.BiomeGenPrairie;
import biomesoplenty.biomes.BiomeGenPromisedLand;
import biomesoplenty.biomes.BiomeGenQuagmire;
import biomesoplenty.biomes.BiomeGenRainforest;
import biomesoplenty.biomes.BiomeGenRedwoodForest;
import biomesoplenty.biomes.BiomeGenSacredSprings;
import biomesoplenty.biomes.BiomeGenSavanna;
import biomesoplenty.biomes.BiomeGenScrubland;
import biomesoplenty.biomes.BiomeGenSeasonalForest;
import biomesoplenty.biomes.BiomeGenShield;
import biomesoplenty.biomes.BiomeGenShore;
import biomesoplenty.biomes.BiomeGenShrubland;
import biomesoplenty.biomes.BiomeGenSnowyWoods;
import biomesoplenty.biomes.BiomeGenSpruceWoods;
import biomesoplenty.biomes.BiomeGenSteppe;
import biomesoplenty.biomes.BiomeGenSwampNew;
import biomesoplenty.biomes.BiomeGenSwampwoods;
import biomesoplenty.biomes.BiomeGenTaigaNew;
import biomesoplenty.biomes.BiomeGenTemperateRainforest;
import biomesoplenty.biomes.BiomeGenThicket;
import biomesoplenty.biomes.BiomeGenTropicalRainforest;
import biomesoplenty.biomes.BiomeGenTropics;
import biomesoplenty.biomes.BiomeGenTundra;
import biomesoplenty.biomes.BiomeGenVolcano;
import biomesoplenty.biomes.BiomeGenWasteland;
import biomesoplenty.biomes.BiomeGenWetland;
import biomesoplenty.biomes.BiomeGenWoodland;
import biomesoplenty.worldtype.WTBiomesOP;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBiomes {
	
	public static WTBiomesOP WTBiomesOP;
	
	public static int getLastBiomeID()
	{
		int x;
		for(x = 255; x >= 0; x--) {
			if (BiomeGenBase.biomeList[x] == null) 
			{
				break;
			}
		}
		return x;
	}
	
	public static void init()
	{
		// Initialize biomes
		Biomes.alps = Optional.of((new BiomeGenAlps(BOPConfiguration.alpsID)).setColor(353825).setBiomeName("Alps").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(5.0F, 5.0F));
		Biomes.arctic = Optional.of((new BiomeGenArctic(BOPConfiguration.arcticID)).setColor(14090235).setBiomeName("Arctic").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.7F));
		Biomes.badlands = Optional.of((new BiomeGenBadlands(BOPConfiguration.badlandsID)).setColor(16421912).setBiomeName("Badlands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.9F));
		Biomes.bambooForest = Optional.of((new BiomeGenBambooForest(BOPConfiguration.bambooForestID)).setColor(112).setBiomeName("Bamboo Forest").setMinMaxHeight(0.0F, 0.3F).setTemperatureRainfall(1.2F, 0.9F));
		Biomes.bayou = Optional.of((new BiomeGenBayou(BOPConfiguration.bayouID)).setColor(522674).setBiomeName("Bayou").func_76733_a(9154376).setMinMaxHeight(-0.3F, 0.2F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.birchForest = Optional.of((new BiomeGenBirchForest(BOPConfiguration.birchForestID)).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.3F));
		Biomes.bog = Optional.of((new BiomeGenBog(BOPConfiguration.bogID)).setColor(522674).setBiomeName("Bog").func_76733_a(9154376).setMinMaxHeight(-0.3F, -0.1F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.borealForest = Optional.of((new BiomeGenBorealForest(BOPConfiguration.borealForestID)).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.0F, 1.0F).setTemperatureRainfall(0.6F, 0.7F));
		Biomes.canyon = Optional.of((new BiomeGenCanyon(BOPConfiguration.canyonID)).setColor(9286496).setBiomeName("Canyon").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(3.0F, 5.0F));
		Biomes.chaparral = Optional.of((new BiomeGenChaparral(BOPConfiguration.chaparralID)).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F));
		Biomes.cherryBlossomGrove = Optional.of((new BiomeGenCherryBlossomGrove(BOPConfiguration.cherryBlossomGroveID)).setColor(9286496).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.coniferousForest = Optional.of((new BiomeGenConiferousForest(BOPConfiguration.coniferousForestID)).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.4F).setMinMaxHeight(0.1F, 0.8F));
		Biomes.crag = Optional.of((new BiomeGenCrag(BOPConfiguration.cragID)).setColor(9286496).setBiomeName("Crag").setMinMaxHeight(0.0F, 9.9F).setTemperatureRainfall(0.4F, 0.2F));
		Biomes.deadForest = Optional.of((new BiomeGenDeadForest(BOPConfiguration.deadForestID)).setColor(522674).setBiomeName("Dead Forest").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.7F).setTemperatureRainfall(1.2F, 0.1F));
		Biomes.deadSwamp = Optional.of((new BiomeGenDeadSwamp(BOPConfiguration.deadSwampID)).setColor(522674).setBiomeName("Dead Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.deadlands = Optional.of((new BiomeGenDeadlands(BOPConfiguration.deadlandsID)).setColor(522674).setBiomeName("Deadlands").setDisableRain().func_76733_a(9154376).setMinMaxHeight(0.1F, 0.5F).setTemperatureRainfall(2.0F, 0.0F));
		Biomes.deciduousForest = Optional.of((new BiomeGenDeciduousForest(BOPConfiguration.deciduousForestID)).setColor(353825).setBiomeName("Deciduous Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.drylands = Optional.of((new BiomeGenDrylands(BOPConfiguration.drylandsID)).setColor(16421912).setBiomeName("Drylands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.5F));
		Biomes.dunes = Optional.of((new BiomeGenDunes(BOPConfiguration.dunesID)).setColor(13786898).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.3F));
		Biomes.fen = Optional.of((new BiomeGenFen(BOPConfiguration.fenID)).setColor(9286496).setBiomeName("Fen").setTemperatureRainfall(0.4F, 0.0F).setMinMaxHeight(-0.2F, 0.1F));
		Biomes.field = Optional.of((new BiomeGenField(BOPConfiguration.fieldID)).setColor(9286496).setBiomeName("Field").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F));
		Biomes.frostForest = Optional.of((new BiomeGenFrostForest(BOPConfiguration.frostForestID)).setColor(14090235).setBiomeName("Frost Forest").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F));
		Biomes.fungiForest = Optional.of((new BiomeGenFungiForest(BOPConfiguration.fungiForestID)).setColor(747097).setBiomeName("Fungi Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.0F, 0.4F));
		Biomes.garden = Optional.of((new BiomeGenGarden(BOPConfiguration.gardenID)).setColor(9286496).setBiomeName("Garden").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.1F, 0.2F));
		Biomes.glacier = Optional.of((new BiomeGenGlacier(BOPConfiguration.glacierID)).setColor(6316128).setBiomeName("Glacier").setEnableSnow().setMinMaxHeight(0.4F, 1.0F).setTemperatureRainfall(0.0F, 0.0F));
		Biomes.grassland = Optional.of((new BiomeGenGrassland(BOPConfiguration.grasslandID)).setColor(9286496).setBiomeName("Grassland").setTemperatureRainfall(0.7F, 0.7F).setMinMaxHeight(0.2F, 0.2F));
		Biomes.grove = Optional.of((new BiomeGenGrove(BOPConfiguration.groveID)).setColor(9286496).setBiomeName("Grove").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.0F, 0.1F));
		Biomes.heathland = Optional.of((new BiomeGenHeathland(BOPConfiguration.heathlandID)).setColor(353825).setBiomeName("Heathland").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.1F, 0.3F));
		Biomes.highland = Optional.of((new BiomeGenHighland(BOPConfiguration.highlandID)).setColor(6316128).setBiomeName("Highland").setMinMaxHeight(0.9F, 1.9F).setTemperatureRainfall(0.5F, 0.5F));
		Biomes.iceSheet = Optional.of((new BiomeGenIceSheet(BOPConfiguration.iceSheetID)).setColor(6316128).setBiomeName("Ice Sheet").setEnableSnow().setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.0F, 0.0F));
		Biomes.icyHills = Optional.of((new BiomeGenIcyHills(BOPConfiguration.icyHillsID)).setColor(14090235).setBiomeName("Icy Hills").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(-0.2F, 0.5F));
		Biomes.jadeCliffs = Optional.of((new BiomeGenJadeCliffs(BOPConfiguration.jadeCliffsID)).setColor(14090235).setBiomeName("Jade Cliffs").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.1F, 2.0F));
		Biomes.lushDesert = Optional.of((new BiomeGenLushDesert(BOPConfiguration.lushDesertID)).setColor(16421912).setBiomeName("Lush Desert").setTemperatureRainfall(0.8F, 0.3F).setMinMaxHeight(0.2F, 0.8F));
		Biomes.lushSwamp = Optional.of((new BiomeGenLushSwamp(BOPConfiguration.lushSwampID)).setColor(522674).setBiomeName("Lush Swamp").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.7F, 1.0F));
		Biomes.mangrove = Optional.of((new BiomeGenMangrove(BOPConfiguration.mangroveID)).setColor(16440917).setBiomeName("Mangrove").setMinMaxHeight(-0.4F, -0.1F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.mapleWoods = Optional.of((new BiomeGenMapleWoods(BOPConfiguration.mapleWoodsID)).setColor(747097).setBiomeName("Maple Woods").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 0.6F));
		Biomes.marsh = Optional.of((new BiomeGenMarsh(BOPConfiguration.marshID)).setColor(10486015).setBiomeName("Marsh").setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.meadow = Optional.of((new BiomeGenMeadow(BOPConfiguration.meadowID)).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 0.7F));
		Biomes.mesa = Optional.of((new BiomeGenMesa(BOPConfiguration.mesaID)).setColor(16421912).setBiomeName("Mesa").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.8F, 1.0F));
		Biomes.moor = Optional.of((new BiomeGenMoor(BOPConfiguration.moorID)).setColor(16421912).setBiomeName("Moor").setTemperatureRainfall(0.5F, 1.0F).setMinMaxHeight(0.7F, 0.8F));
		Biomes.mountain = Optional.of((new BiomeGenMountain(BOPConfiguration.mountainID)).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(1.2F, 1.2F));
		Biomes.mysticGrove = Optional.of((new BiomeGenMysticGrove(BOPConfiguration.mysticGroveID)).setColor(353825).setBiomeName("Mystic Grove").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F));
		Biomes.oasis = Optional.of((new BiomeGenOasis(BOPConfiguration.oasisID)).setColor(16421912).setBiomeName("Oasis").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.2F));
		Biomes.ominousWoods = Optional.of((new BiomeGenOminousWoods(BOPConfiguration.ominousWoodsID)).setColor(353825).setBiomeName("Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.orchard = Optional.of((new BiomeGenOrchard(BOPConfiguration.orchardID)).setColor(9286496).setBiomeName("Orchard").setTemperatureRainfall(0.8F, 0.4F));
		Biomes.originValley = Optional.of((new BiomeGenOriginValley(BOPConfiguration.originValleyID)).setColor(353825).setBiomeName("Origin Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(-0.1F, 0.6F));
		Biomes.outback = Optional.of((new BiomeGenOutback(BOPConfiguration.outbackID)).setColor(9286496).setBiomeName("Outback").setTemperatureRainfall(0.8F, 0.0F).setMinMaxHeight(0.1F, 0.1F));
		Biomes.pasture = Optional.of((new BiomeGenPasture(BOPConfiguration.pastureID)).setColor(9286496).setBiomeName("Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.1F, 0.2F));
		Biomes.prairie = Optional.of((new BiomeGenPrairie(BOPConfiguration.prairieID)).setColor(353825).setBiomeName("Prairie").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.6F).setMinMaxHeight(0.1F, 0.1F));
		Biomes.promisedLand = Optional.of((new BiomeGenPromisedLand(BOPConfiguration.promisedLandID)).setColor(112).setBiomeName("Promised Land").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));
		Biomes.quagmire = Optional.of((new BiomeGenQuagmire(BOPConfiguration.quagmireID)).setColor(522674).setBiomeName("Quagmire").func_76733_a(9154376).setMinMaxHeight(-0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.rainforest = Optional.of((new BiomeGenRainforest(BOPConfiguration.rainforestID)).setColor(5470985).setBiomeName("Rainforest").func_76733_a(5470985).setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.7F, 1.8F));
		Biomes.redwoodForest = Optional.of((new BiomeGenRedwoodForest(BOPConfiguration.redwoodForestID)).setColor(747097).setBiomeName("Redwood Forest").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.0F, 0.4F));
		Biomes.sacredSprings = Optional.of((new BiomeGenSacredSprings(BOPConfiguration.sacredSpringsID)).setColor(522674).setBiomeName("Sacred Springs").func_76733_a(9154376).setMinMaxHeight(0.0F, 1.2F).setTemperatureRainfall(1.2F, 0.9F));
		Biomes.savanna = Optional.of((new BiomeGenSavanna(BOPConfiguration.savannaID)).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.1F));
		Biomes.scrubland = Optional.of((new BiomeGenScrubland(BOPConfiguration.scrublandID)).setColor(9286496).setBiomeName("Scrubland").setTemperatureRainfall(1.2F, 0.0F).setMinMaxHeight(0.1F, 0.3F));
		Biomes.seasonalForest = Optional.of((new BiomeGenSeasonalForest(BOPConfiguration.seasonalForestID)).setColor(353825).setBiomeName("Seasonal Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.7F));
		Biomes.shield = Optional.of((new BiomeGenShield(BOPConfiguration.shieldID)).setColor(522674).setBiomeName("Shield").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.5F, 0.8F));
		Biomes.shore = Optional.of((new BiomeGenShore(BOPConfiguration.shoreID)).setColor(9286496).setBiomeName("Shore").setMinMaxHeight(-1.0F, 0.4F).setTemperatureRainfall(0.8F, 0.4F));
		Biomes.shrubland = Optional.of((new BiomeGenShrubland(BOPConfiguration.shrublandID)).setColor(9286496).setBiomeName("Shrubland").setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.6F, 0.0F));
		Biomes.snowyWoods = Optional.of((new BiomeGenSnowyWoods(BOPConfiguration.snowyWoodsID)).setColor(522674).setBiomeName("Snowy Woods").func_76733_a(9154376).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F));
		Biomes.spruceWoods = Optional.of((new BiomeGenSpruceWoods(BOPConfiguration.spruceWoodsID)).setColor(353825).setBiomeName("Spruce Woods").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.7F));
		Biomes.steppe = Optional.of((new BiomeGenSteppe(BOPConfiguration.steppeID)).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F));
		Biomes.swampwoods = Optional.of((new BiomeGenSwampwoods(BOPConfiguration.swampwoodsID)).setColor(522674).setBiomeName("Swampwoods").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.2F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.temperateRainforest = Optional.of((new BiomeGenTemperateRainforest(BOPConfiguration.temperateRainforestID)).setColor(353825).setBiomeName("Temperate Rainforest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 1.2F));
		Biomes.thicket = Optional.of((new BiomeGenThicket(BOPConfiguration.thicketID)).setColor(353825).setBiomeName("Thicket").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.2F).setMinMaxHeight(0.0F, 0.2F));
		Biomes.tropicalRainforest = Optional.of((new BiomeGenTropicalRainforest(BOPConfiguration.tropicalRainforestID)).setColor(9286496).setBiomeName("Tropical Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.tropics = Optional.of((new BiomeGenTropics(BOPConfiguration.tropicsID)).setColor(9286496).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 0.8F));
		Biomes.tundra = Optional.of((new BiomeGenTundra(BOPConfiguration.tundraID)).setColor(14090235).setBiomeName("Tundra").setEnableSnow().setTemperatureRainfall(0.05F, 0.0F).setMinMaxHeight(-0.2F, 0.0F));
		Biomes.volcano = Optional.of((new BiomeGenVolcano(BOPConfiguration.volcanoID)).setColor(9286496).setBiomeName("Volcano").setDisableRain().setMinMaxHeight(0.6F, 0.9F).setTemperatureRainfall(2.0F, 0.0F));
		Biomes.wasteland = Optional.of((new BiomeGenWasteland(BOPConfiguration.wastelandID)).setColor(16421912).setBiomeName("Wasteland").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.0F, 0.0F));
		Biomes.wetland = Optional.of((new BiomeGenWetland(BOPConfiguration.wetlandID)).setColor(522674).setBiomeName("Wetland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.4F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.woodland = Optional.of((new BiomeGenWoodland(BOPConfiguration.woodlandID)).setColor(353825).setBiomeName("Woodland").func_76733_a(5159473).setTemperatureRainfall(1.7F, 0.2F).setMinMaxHeight(0.1F, 0.2F));
		Biomes.plainsNew = Optional.of((new BiomeGenPlainsNew(BOPConfiguration.plainsNewID)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F));
		Biomes.desertNew = Optional.of((new BiomeGenDesertNew(BOPConfiguration.desertNewID)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F));
		Biomes.extremeHillsNew = Optional.of((new BiomeGenHillsNew(BOPConfiguration.extremeHillsNewID)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F));
		Biomes.forestNew = Optional.of((new BiomeGenForestNew(BOPConfiguration.forestNewID)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.taigaNew = Optional.of((new BiomeGenTaigaNew(BOPConfiguration.taigaNewID)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.1F, 0.4F));
		Biomes.swamplandNew = Optional.of((new BiomeGenSwampNew(BOPConfiguration.swamplandNewID)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.jungleNew = Optional.of((new BiomeGenJungleNew(BOPConfiguration.jungleNewID)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.2F, 0.4F));

		//Initialize new world type
		WTBiomesOP = new WTBiomesOP();

		//Spawning
		addSpawnBiome(Biomes.alps);
		addSpawnBiome(Biomes.arctic);
		addSpawnBiome(Biomes.badlands);
		addSpawnBiome(Biomes.bambooForest);
		addSpawnBiome(Biomes.bayou);
		addSpawnBiome(Biomes.birchForest);
		addSpawnBiome(Biomes.bog);
		addSpawnBiome(Biomes.borealForest);
		addSpawnBiome(Biomes.canyon);
		addSpawnBiome(Biomes.chaparral);
		addSpawnBiome(Biomes.cherryBlossomGrove);
		addSpawnBiome(Biomes.coniferousForest);
		addSpawnBiome(Biomes.deadForest);
		addSpawnBiome(Biomes.deadSwamp);
		addSpawnBiome(Biomes.deciduousForest);
		addSpawnBiome(Biomes.drylands);
		addSpawnBiome(Biomes.dunes);
		addSpawnBiome(Biomes.fen);
		addSpawnBiome(Biomes.field);
		addSpawnBiome(Biomes.frostForest);
		addSpawnBiome(Biomes.glacier);
		addSpawnBiome(Biomes.grassland);
		addSpawnBiome(Biomes.grove);
		addSpawnBiome(Biomes.heathland);
		addSpawnBiome(Biomes.highland);
		addSpawnBiome(Biomes.iceSheet);
		addSpawnBiome(Biomes.jadeCliffs);
		addSpawnBiome(Biomes.lushDesert);
		addSpawnBiome(Biomes.lushSwamp);
		addSpawnBiome(Biomes.mangrove);
		addSpawnBiome(Biomes.mapleWoods);
		addSpawnBiome(Biomes.marsh);
		addSpawnBiome(Biomes.meadow);
		addSpawnBiome(Biomes.mesa);
		addSpawnBiome(Biomes.moor);
		addSpawnBiome(Biomes.mountain);
		addSpawnBiome(Biomes.oasis);
		addSpawnBiome(Biomes.orchard);
		addSpawnBiome(Biomes.outback);
		addSpawnBiome(Biomes.pasture);
		addSpawnBiome(Biomes.prairie);
		addSpawnBiome(Biomes.quagmire);
		addSpawnBiome(Biomes.rainforest);
		addSpawnBiome(Biomes.redwoodForest);
		addSpawnBiome(Biomes.savanna);
		addSpawnBiome(Biomes.scrubland);
		addSpawnBiome(Biomes.seasonalForest);
		addSpawnBiome(Biomes.shield);
		addSpawnBiome(Biomes.shrubland);
		addSpawnBiome(Biomes.snowyWoods);
		addSpawnBiome(Biomes.spruceWoods);
		addSpawnBiome(Biomes.swampwoods);
		addSpawnBiome(Biomes.temperateRainforest);
		addSpawnBiome(Biomes.thicket);
		addSpawnBiome(Biomes.tropicalRainforest);
		addSpawnBiome(Biomes.tropics);
		addSpawnBiome(Biomes.tundra);
		addSpawnBiome(Biomes.volcano);
		addSpawnBiome(Biomes.wetland);
		addSpawnBiome(Biomes.woodland);
		addSpawnBiome(Biomes.plainsNew);
		addSpawnBiome(Biomes.desertNew);
		addSpawnBiome(Biomes.forestNew);
		addSpawnBiome(Biomes.extremeHillsNew);
		addSpawnBiome(Biomes.taigaNew);
		addSpawnBiome(Biomes.swamplandNew);
		addSpawnBiome(Biomes.jungleNew);

		//Village spawning 
		addVillageBiome(Biomes.arctic);				
		addVillageBiome(Biomes.bayou);
		addVillageBiome(Biomes.birchForest);
		addVillageBiome(Biomes.chaparral);
		addVillageBiome(Biomes.coniferousForest);
		addVillageBiome(Biomes.deadForest);
		addVillageBiome(Biomes.field);
		addVillageBiome(Biomes.frostForest);
		addVillageBiome(Biomes.grassland);
		addVillageBiome(Biomes.grove);
		addVillageBiome(Biomes.heathland);
		addVillageBiome(Biomes.lushSwamp);
		addVillageBiome(Biomes.mapleWoods);
		addVillageBiome(Biomes.orchard);
		addVillageBiome(Biomes.prairie);
		addVillageBiome(Biomes.redwoodForest);
		addVillageBiome(Biomes.savanna);
		addVillageBiome(Biomes.scrubland);
		addVillageBiome(Biomes.shield);
		addVillageBiome(Biomes.shrubland);
		addVillageBiome(Biomes.snowyWoods);
		addVillageBiome(Biomes.spruceWoods);
		addVillageBiome(Biomes.tropicalRainforest);
		addVillageBiome(Biomes.woodland);
		addVillageBiome(Biomes.plainsNew);
		addVillageBiome(Biomes.desertNew);
		addVillageBiome(Biomes.forestNew);
		addVillageBiome(Biomes.taigaNew);
		addVillageBiome(Biomes.swamplandNew);

		//Stronghold spawning
		addStrongholdBiome(Biomes.alps);
		addStrongholdBiome(Biomes.arctic);
		addStrongholdBiome(Biomes.badlands);
		addStrongholdBiome(Biomes.bambooForest);
		addStrongholdBiome(Biomes.bayou);
		addStrongholdBiome(Biomes.birchForest);
		addStrongholdBiome(Biomes.bog);
		addStrongholdBiome(Biomes.borealForest);
		addStrongholdBiome(Biomes.canyon);
		addStrongholdBiome(Biomes.chaparral);
		addStrongholdBiome(Biomes.cherryBlossomGrove);
		addStrongholdBiome(Biomes.coniferousForest);
		addStrongholdBiome(Biomes.crag);
		addStrongholdBiome(Biomes.deadForest);
		addStrongholdBiome(Biomes.deadSwamp);
		addStrongholdBiome(Biomes.deadlands);
		addStrongholdBiome(Biomes.deciduousForest);
		addStrongholdBiome(Biomes.drylands);
		addStrongholdBiome(Biomes.dunes);
		addStrongholdBiome(Biomes.fen);
		addStrongholdBiome(Biomes.field);
		addStrongholdBiome(Biomes.frostForest);
		addStrongholdBiome(Biomes.fungiForest);
		addStrongholdBiome(Biomes.garden);
		addStrongholdBiome(Biomes.glacier);
		addStrongholdBiome(Biomes.grassland);
		addStrongholdBiome(Biomes.grove);
		addStrongholdBiome(Biomes.heathland);
		addStrongholdBiome(Biomes.highland);
		addStrongholdBiome(Biomes.iceSheet);
		addStrongholdBiome(Biomes.icyHills);
		addStrongholdBiome(Biomes.jadeCliffs);
		addStrongholdBiome(Biomes.lushDesert);
		addStrongholdBiome(Biomes.lushSwamp);
		addStrongholdBiome(Biomes.mangrove);
		addStrongholdBiome(Biomes.mapleWoods);
		addStrongholdBiome(Biomes.marsh);
		addStrongholdBiome(Biomes.meadow);
		addStrongholdBiome(Biomes.mesa);
		addStrongholdBiome(Biomes.moor);
		addStrongholdBiome(Biomes.mountain);
		addStrongholdBiome(Biomes.mysticGrove);
		addStrongholdBiome(Biomes.oasis);
		addStrongholdBiome(Biomes.ominousWoods);
		addStrongholdBiome(Biomes.orchard);
		addStrongholdBiome(Biomes.outback);
		addStrongholdBiome(Biomes.pasture);
		addStrongholdBiome(Biomes.prairie);
		addStrongholdBiome(Biomes.quagmire);
		addStrongholdBiome(Biomes.rainforest);
		addStrongholdBiome(Biomes.redwoodForest);
		addStrongholdBiome(Biomes.sacredSprings);
		addStrongholdBiome(Biomes.savanna);
		addStrongholdBiome(Biomes.scrubland);
		addStrongholdBiome(Biomes.seasonalForest);
		addStrongholdBiome(Biomes.shield);
		addStrongholdBiome(Biomes.shrubland);
		addStrongholdBiome(Biomes.snowyWoods);
		addStrongholdBiome(Biomes.spruceWoods);
		addStrongholdBiome(Biomes.steppe);
		addStrongholdBiome(Biomes.swampwoods);
		addStrongholdBiome(Biomes.temperateRainforest);
		addStrongholdBiome(Biomes.thicket);
		addStrongholdBiome(Biomes.tropicalRainforest);
		addStrongholdBiome(Biomes.tropics);
		addStrongholdBiome(Biomes.tundra);
		addStrongholdBiome(Biomes.volcano);
		addStrongholdBiome(Biomes.wasteland);
		addStrongholdBiome(Biomes.wetland);
		addStrongholdBiome(Biomes.woodland);
		addStrongholdBiome(Biomes.plainsNew);
		addStrongholdBiome(Biomes.desertNew);
		addStrongholdBiome(Biomes.forestNew);
		addStrongholdBiome(Biomes.extremeHillsNew);
		addStrongholdBiome(Biomes.taigaNew);
		addStrongholdBiome(Biomes.swamplandNew);
		addStrongholdBiome(Biomes.jungleNew);	
		
		if (BOPConfiguration.addToDefault == true)
		{
			if (BOPConfiguration.alpsGen == true)
			{
				registerBiome(Biomes.alps);
			}
			if (BOPConfiguration.arcticGen == true)
			{
				registerBiome(Biomes.arctic);
			}
			if (BOPConfiguration.badlandsGen == true)
			{
				registerBiome(Biomes.badlands);
			}
			if (BOPConfiguration.bambooForestGen == true)
			{
				registerBiome(Biomes.bambooForest);
			}
			if (BOPConfiguration.bayouGen == true)
			{
				registerBiome(Biomes.bayou);
			}
			if (BOPConfiguration.birchForestGen == true)
			{
				registerBiome(Biomes.birchForest);
			}
			if (BOPConfiguration.bogGen == true)
			{
				registerBiome(Biomes.bog);
			}
			if (BOPConfiguration.borealForestGen == true)
			{
				registerBiome(Biomes.borealForest);
			}
			if (BOPConfiguration.canyonGen == true)
			{
				registerBiome(Biomes.canyon);
			}
			if (BOPConfiguration.chaparralGen == true)
			{
				registerBiome(Biomes.chaparral);
			}
			if (BOPConfiguration.cherryBlossomGroveGen == true)
			{
				registerBiome(Biomes.cherryBlossomGrove);
			}
			if (BOPConfiguration.coniferousForestGen == true)
			{
				registerBiome(Biomes.coniferousForest);
			}
			if (BOPConfiguration.cragGen == true)
			{
				registerBiome(Biomes.crag);
			}
			if (BOPConfiguration.deadForestGen == true)
			{
				registerBiome(Biomes.deadForest);
			}
			if (BOPConfiguration.deadSwampGen == true)
			{
				registerBiome(Biomes.deadSwamp);
			}
			if (BOPConfiguration.deadlandsGen == true)
			{
				registerBiome(Biomes.deadlands);
			}
			if (BOPConfiguration.deciduousForestGen == true)
			{
				registerBiome(Biomes.deciduousForest);
			}
			if (BOPConfiguration.drylandsGen == true)
			{
				registerBiome(Biomes.drylands);
			}
			if (BOPConfiguration.dunesGen == true)
			{
				registerBiome(Biomes.dunes);
			}
			if (BOPConfiguration.fenGen == true)
			{
				registerBiome(Biomes.fen);
			}
			if (BOPConfiguration.fieldGen == true)
			{
				registerBiome(Biomes.field);
			}
			if (BOPConfiguration.frostForestGen == true)
			{
				registerBiome(Biomes.frostForest);
			}
			if (BOPConfiguration.fungiForestGen == true)
			{
				registerBiome(Biomes.fungiForest);
			}
			if (BOPConfiguration.gardenGen == true)
			{
				registerBiome(Biomes.garden);
			}
			if (BOPConfiguration.glacierGen == true)
			{
				registerBiome(Biomes.glacier);
			}
			if (BOPConfiguration.grasslandGen == true)
			{
				registerBiome(Biomes.grassland);
			}
			if (BOPConfiguration.groveGen == true)
			{
				registerBiome(Biomes.grove);
			}
			if (BOPConfiguration.heathlandGen == true)
			{
				registerBiome(Biomes.heathland);
			}
			if (BOPConfiguration.highlandGen == true)
			{
				registerBiome(Biomes.highland);
			}
			if (BOPConfiguration.iceSheetGen == true)
			{
				registerBiome(Biomes.iceSheet);
			}
			if (BOPConfiguration.icyHillsGen == true)
			{
				registerBiome(Biomes.icyHills);
			}
			if (BOPConfiguration.jadeCliffsGen == true)
			{
				registerBiome(Biomes.jadeCliffs);
			}
			if (BOPConfiguration.lushDesertGen == true)
			{
				registerBiome(Biomes.lushDesert);
			}
			if (BOPConfiguration.lushSwampGen == true)
			{
				registerBiome(Biomes.lushSwamp);
			}
			if (BOPConfiguration.mangroveGen == true)
			{
				registerBiome(Biomes.mangrove);
			}
			if (BOPConfiguration.mapleWoodsGen == true)
			{
				registerBiome(Biomes.mapleWoods);
			}
			if (BOPConfiguration.marshGen == true)
			{
				registerBiome(Biomes.marsh);
			}
			if (BOPConfiguration.meadowGen == true)
			{
				registerBiome(Biomes.meadow);
			}
			if (BOPConfiguration.mesaGen == true)
			{
				registerBiome(Biomes.mesa);
			}
			if (BOPConfiguration.moorGen == true)
			{
				registerBiome(Biomes.moor);
			}
			if (BOPConfiguration.mountainGen == true)
			{
				registerBiome(Biomes.mountain);
			}
			if (BOPConfiguration.mushroomIslandGen == true)
			{
				GameRegistry.addBiome(BiomeGenBase.mushroomIsland);
			}
			if (BOPConfiguration.mysticGroveGen == true)
			{
				registerBiome(Biomes.mysticGrove);
			}
			if (BOPConfiguration.oasisGen == true)
			{
				registerBiome(Biomes.oasis);
			}
			if (BOPConfiguration.ominousWoodsGen == true)
			{
				registerBiome(Biomes.ominousWoods);
			}
			if (BOPConfiguration.orchardGen == true)
			{
				registerBiome(Biomes.orchard);
			}
			if (BOPConfiguration.originValleyGen == true)
			{
				registerBiome(Biomes.originValley);
			}
			if (BOPConfiguration.outbackGen == true)
			{
				registerBiome(Biomes.outback);
			}
			if (BOPConfiguration.pastureGen == true)
			{
				registerBiome(Biomes.pasture);
			}
			if (BOPConfiguration.prairieGen == true)
			{
				registerBiome(Biomes.prairie);
			}
			if (BOPConfiguration.quagmireGen == true)
			{
				registerBiome(Biomes.quagmire);
			}
			if (BOPConfiguration.rainforestGen == true)
			{
				registerBiome(Biomes.rainforest);
			}
			if (BOPConfiguration.redwoodForestGen == true)
			{
				registerBiome(Biomes.redwoodForest);
			}
			if (BOPConfiguration.sacredSpringsGen == true)
			{
				registerBiome(Biomes.sacredSprings);
			}
			if (BOPConfiguration.savannaGen == true)
			{
				registerBiome(Biomes.savanna);
			}
			if (BOPConfiguration.scrublandGen == true)
			{
				registerBiome(Biomes.scrubland);
			}
			if (BOPConfiguration.seasonalForestGen == true)
			{
				registerBiome(Biomes.seasonalForest);
			}
			if (BOPConfiguration.shieldGen == true)
			{
				registerBiome(Biomes.shield);
			}
			if (BOPConfiguration.shrublandGen == true)
			{
				registerBiome(Biomes.shrubland);
			}
			if (BOPConfiguration.snowyWoodsGen == true)
			{
				registerBiome(Biomes.snowyWoods);
			}
			if (BOPConfiguration.spruceWoodsGen == true)
			{
				registerBiome(Biomes.spruceWoods);
			}
			if (BOPConfiguration.steppeGen == true)
			{
				registerBiome(Biomes.steppe);
			}
			if (BOPConfiguration.swampwoodsGen == true)
			{
				registerBiome(Biomes.swampwoods);
			}
			if (BOPConfiguration.temperateRainforestGen == true)
			{
				registerBiome(Biomes.temperateRainforest);
			}
			if (BOPConfiguration.thicketGen == true)
			{
				registerBiome(Biomes.thicket);
			}
			if (BOPConfiguration.tropicalRainforestGen == true)
			{
				registerBiome(Biomes.tropicalRainforest);
			}
			if (BOPConfiguration.tropicsGen == true)
			{
				registerBiome(Biomes.tropics);
			}
			if (BOPConfiguration.tundraGen == true)
			{
				registerBiome(Biomes.tundra);
			}
			if (BOPConfiguration.volcanoGen == true)
			{
				registerBiome(Biomes.volcano);
			}
			if (BOPConfiguration.wastelandGen == true)
			{
				registerBiome(Biomes.wasteland);
			}
			if (BOPConfiguration.wetlandGen == true)
			{
				registerBiome(Biomes.wetland);
			}
			if (BOPConfiguration.woodlandGen == true)
			{
				registerBiome(Biomes.woodland);
			}
			if (BOPConfiguration.plainsGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.plainsNew);
					GameRegistry.removeBiome(BiomeGenBase.plains);
				}
			}
			if (BOPConfiguration.desertGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.desertNew);
					GameRegistry.removeBiome(BiomeGenBase.desert);
				}
			}
			if (BOPConfiguration.extremeHillsGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.extremeHillsNew);
					GameRegistry.removeBiome(BiomeGenBase.extremeHills);
				}
			}
			if (BOPConfiguration.forestGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.forestNew);
					GameRegistry.removeBiome(BiomeGenBase.forest);
				}
			}
			if (BOPConfiguration.taigaGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.taigaNew);
					GameRegistry.removeBiome(BiomeGenBase.taiga);
				}
			}
			if (BOPConfiguration.swamplandGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.swamplandNew);
					GameRegistry.removeBiome(BiomeGenBase.swampland);
				}
			}
			if (BOPConfiguration.jungleGen == true)
			{
				if (BOPConfiguration.vanillaEnhanced == true)
				{
					registerBiome(Biomes.jungleNew);
					GameRegistry.removeBiome(BiomeGenBase.jungle);
				}
			}
		}
	}

	private static void addSpawnBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent())
			BiomeManager.addSpawnBiome(biome.get());
	}
	
	private static void addVillageBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent())
			BiomeManager.addVillageBiome(biome.get(), true);
	}
	
	private static void addStrongholdBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent())
			BiomeManager.addStrongholdBiome(biome.get());
	}
	
	private static void registerBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent())
			GameRegistry.addBiome(biome.get());
	}
}
