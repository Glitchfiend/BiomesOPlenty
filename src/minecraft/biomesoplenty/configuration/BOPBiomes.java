package biomesoplenty.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import biomesoplenty.api.Biomes;
import biomesoplenty.biomes.*;
import biomesoplenty.world.WorldTypeBOP;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBiomes {

	public static WorldTypeBOP WTBiomesOP;

	private static final Set<WorldType> worldTypes = new HashSet();

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
		initializeBiomes();

		//Initialize new world type
		WTBiomesOP = new WorldTypeBOP();

		// Adding biomes to World Types
		addBiomes();
		removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.jungleHills);
		//Biome Dictionary
		addToBiomeDictionary();

		//Spawning
		addSpawnBiomes();

		//Village spawning
		addVillageBiomes();

		//Stronghold spawning
		addStrongholdBiomes();

		registerBiomes();
	}

	private static void initializeBiomes()
	{
		Biomes.alps = Optional.of((new BiomeGenAlps(BOPConfiguration.alpsID)).setColor(353825).setBiomeName("Alps").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(2.0F, 3.0F));
		Biomes.alpsForest = Optional.of((new BiomeGenAlpsForest(BOPConfiguration.alpsForestID)).setColor(353825).setBiomeName("Alps Mountainside").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(1.0F, 2.0F));
		Biomes.alpsBase = Optional.of((new BiomeGenAlpsBase(BOPConfiguration.alpsBaseID)).setColor(353825).setBiomeName("Alps Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.3F, 1.0F));
		Biomes.arctic = Optional.of((new BiomeGenArctic(BOPConfiguration.arcticID)).setColor(14090235).setBiomeName("Arctic").setEnableSnow().setTemperatureRainfall(0.05F, 0.0F).setMinMaxHeight(0.2F, 0.2F));
		Biomes.badlands = Optional.of((new BiomeGenBadlands(BOPConfiguration.badlandsID)).setColor(16421912).setBiomeName("Badlands").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.9F));
		Biomes.bambooForest = Optional.of((new BiomeGenBambooForest(BOPConfiguration.bambooForestID)).setColor(112).setBiomeName("Bamboo Forest").setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(1.2F, 0.9F));
		Biomes.bayou = Optional.of((new BiomeGenBayou(BOPConfiguration.bayouID)).setColor(522674).setBiomeName("Bayou").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.4F).setTemperatureRainfall(0.5F, 0.9F));

		Biomes.beachGravel = Optional.of((new BiomeGenBeachGravel(BOPConfiguration.beachGravelID)).setColor(16440917).setBiomeName("Gravel Beach").setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.0F, 0.1F));
		Biomes.beachOvergrown = Optional.of((new BiomeGenBeachOvergrown(BOPConfiguration.beachOvergrownID)).setColor(16440917).setBiomeName("Overgrown Beach").setTemperatureRainfall(0.8F, 0.5F).setMinMaxHeight(0.0F, 0.1F));

		Biomes.birchForest = Optional.of((new BiomeGenBirchForest(BOPConfiguration.birchForestID)).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.3F));
		Biomes.bog = Optional.of((new BiomeGenBog(BOPConfiguration.bogID)).setColor(522674).setBiomeName("Bog").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.borealForest = Optional.of((new BiomeGenBorealForest(BOPConfiguration.borealForestID)).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.2F, 1.0F).setTemperatureRainfall(0.6F, 0.7F));
		Biomes.brushland = Optional.of((new BiomeGenBrushland(BOPConfiguration.brushlandID)).setColor(16421912).setBiomeName("Brushland").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.3F));
		Biomes.canyon = Optional.of((new BiomeGenCanyon(BOPConfiguration.canyonID)).setColor(9286496).setBiomeName("Canyon").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(1.5F, 2.0F));
		Biomes.canyonRavine = Optional.of((new BiomeGenCanyonRavine(BOPConfiguration.canyonRavineID)).setColor(9286496).setBiomeName("Canyon Ravine").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.chaparral = Optional.of((new BiomeGenChaparral(BOPConfiguration.chaparralID)).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F));
		Biomes.cherryBlossomGrove = Optional.of((new BiomeGenCherryBlossomGrove(BOPConfiguration.cherryBlossomGroveID)).setColor(9286496).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.3F, 0.4F).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.coniferousForest = Optional.of((new BiomeGenConiferousForest(BOPConfiguration.coniferousForestID)).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.2F, 0.8F));
		Biomes.coniferousForestSnow = Optional.of((new BiomeGenConiferousForestSnow(BOPConfiguration.coniferousForestSnowID)).setColor(14090235).setBiomeName("Snowy Coniferous Forest").setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.2F, 0.7F));
		Biomes.crag = Optional.of((new BiomeGenCrag(BOPConfiguration.cragID)).setColor(9286496).setBiomeName("Crag").setMinMaxHeight(0.0F, 9.9F).setTemperatureRainfall(0.4F, 0.2F));
		Biomes.deadForest = Optional.of((new BiomeGenDeadForest(BOPConfiguration.deadForestID)).setColor(522674).setBiomeName("Dead Forest").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.7F).setTemperatureRainfall(1.2F, 0.1F));
		Biomes.deadForestSnow = Optional.of((new BiomeGenDeadForestSnow(BOPConfiguration.deadForestSnowID)).setColor(522674).setBiomeName("Snowy Dead Forest").func_76733_a(9154376).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F));
		Biomes.deadSwamp = Optional.of((new BiomeGenDeadSwamp(BOPConfiguration.deadSwampID)).setColor(522674).setBiomeName("Dead Swamp").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.deadlands = Optional.of((new BiomeGenDeadlands(BOPConfiguration.deadlandsID)).setColor(522674).setBiomeName("Deadlands").setDisableRain().func_76733_a(9154376).setMinMaxHeight(0.1F, 0.5F).setTemperatureRainfall(2.0F, 0.0F));
		Biomes.deciduousForest = Optional.of((new BiomeGenDeciduousForest(BOPConfiguration.deciduousForestID)).setColor(353825).setBiomeName("Deciduous Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.dunes = Optional.of((new BiomeGenDunes(BOPConfiguration.dunesID)).setColor(13786898).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.5F, 1.3F));
		Biomes.fen = Optional.of((new BiomeGenFen(BOPConfiguration.fenID)).setColor(9286496).setBiomeName("Fen").setTemperatureRainfall(0.4F, 0.0F).setMinMaxHeight(0.2F, 0.4F));
		Biomes.field = Optional.of((new BiomeGenField(BOPConfiguration.fieldID)).setColor(9286496).setBiomeName("Field").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.frostForest = Optional.of((new BiomeGenFrostForest(BOPConfiguration.frostForestID)).setColor(14090235).setBiomeName("Frost Forest").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.fungiForest = Optional.of((new BiomeGenFungiForest(BOPConfiguration.fungiForestID)).setColor(747097).setBiomeName("Fungi Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.2F, 0.5F));
		Biomes.garden = Optional.of((new BiomeGenGarden(BOPConfiguration.gardenID)).setColor(9286496).setBiomeName("Garden").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.glacier = Optional.of((new BiomeGenGlacier(BOPConfiguration.glacierID)).setColor(6316128).setBiomeName("Glacier").setEnableSnow().setMinMaxHeight(0.4F, 1.0F).setTemperatureRainfall(0.0F, 0.0F));
		Biomes.grassland = Optional.of((new BiomeGenGrassland(BOPConfiguration.grasslandID)).setColor(9286496).setBiomeName("Grassland").setTemperatureRainfall(0.7F, 0.7F).setMinMaxHeight(0.3F, 0.3F));
		Biomes.grove = Optional.of((new BiomeGenGrove(BOPConfiguration.groveID)).setColor(9286496).setBiomeName("Grove").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.heathland = Optional.of((new BiomeGenHeathland(BOPConfiguration.heathlandID)).setColor(353825).setBiomeName("Heathland").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.highland = Optional.of((new BiomeGenHighland(BOPConfiguration.highlandID)).setColor(6316128).setBiomeName("Highland").setMinMaxHeight(0.9F, 1.9F).setTemperatureRainfall(0.5F, 0.5F));
		Biomes.hotSprings = Optional.of((new BiomeGenHotSprings(BOPConfiguration.hotSpringsID)).setColor(10486015).setBiomeName("Hot Springs").setMinMaxHeight(0.2F, 0.5F).setTemperatureRainfall(0.5F, 0.7F));
		Biomes.icyHills = Optional.of((new BiomeGenIcyHills(BOPConfiguration.icyHillsID)).setColor(14090235).setBiomeName("Icy Hills").setEnableSnow().setTemperatureRainfall(0.0F, 0.0F).setMinMaxHeight(0.3F, 0.8F));
		Biomes.jadeCliffs = Optional.of((new BiomeGenJadeCliffs(BOPConfiguration.jadeCliffsID)).setColor(14090235).setBiomeName("Jade Cliffs").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.2F, 2.0F));
		Biomes.lushDesert = Optional.of((new BiomeGenLushDesert(BOPConfiguration.lushDesertID)).setColor(16421912).setBiomeName("Lush Desert").setTemperatureRainfall(0.8F, 0.2F).setMinMaxHeight(0.2F, 0.9F));
		Biomes.lushSwamp = Optional.of((new BiomeGenLushSwamp(BOPConfiguration.lushSwampID)).setColor(522674).setBiomeName("Lush Swamp").func_76733_a(9154376).setMinMaxHeight(0.0F, 0.3F).setTemperatureRainfall(0.7F, 1.0F));
		Biomes.mangrove = Optional.of((new BiomeGenMangrove(BOPConfiguration.mangroveID)).setColor(16440917).setBiomeName("Mangrove").setMinMaxHeight(0.0F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.mapleWoods = Optional.of((new BiomeGenMapleWoods(BOPConfiguration.mapleWoodsID)).setColor(747097).setBiomeName("Maple Woods").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.3F, 0.6F));
		Biomes.marsh = Optional.of((new BiomeGenMarsh(BOPConfiguration.marshID)).setColor(10486015).setBiomeName("Marsh").setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.meadow = Optional.of((new BiomeGenMeadow(BOPConfiguration.meadowID)).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 0.7F));
		Biomes.meadowForest = Optional.of((new BiomeGenMeadowForest(BOPConfiguration.meadowForestID)).setColor(9286496).setBiomeName("Meadow Forest").setTemperatureRainfall(0.7F, 0.7F));
		Biomes.mesa = Optional.of((new BiomeGenMesa(BOPConfiguration.mesaID)).setColor(16421912).setBiomeName("Mesa").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.4F, 2.0F));
		Biomes.moor = Optional.of((new BiomeGenMoor(BOPConfiguration.moorID)).setColor(16421912).setBiomeName("Moor").setTemperatureRainfall(0.5F, 1.0F).setMinMaxHeight(0.7F, 0.8F));
		Biomes.mountain = Optional.of((new BiomeGenMountain(BOPConfiguration.mountainID)).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(1.2F, 1.2F));
		Biomes.mysticGrove = Optional.of((new BiomeGenMysticGrove(BOPConfiguration.mysticGroveID)).setColor(353825).setBiomeName("Mystic Grove").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F));

		Biomes.netherBase = Optional.of((new BiomeGenNetherBase(BOPConfiguration.netherBaseID)).setColor(16711680).setBiomeName("Nether").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherGarden = Optional.of((new BiomeGenNetherGarden(BOPConfiguration.netherGardenID)).setColor(16711680).setBiomeName("Undergarden").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherDesert = Optional.of((new BiomeGenNetherDesert(BOPConfiguration.netherDesertID)).setColor(16711680).setBiomeName("Corrupted Sands").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherLava = Optional.of((new BiomeGenNetherLava(BOPConfiguration.netherLavaID)).setColor(16711680).setBiomeName("Phantasmagoric Inferno").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherBone = Optional.of((new BiomeGenNetherBone(BOPConfiguration.netherBoneID)).setColor(16711680).setBiomeName("Boneyard").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));

		Biomes.oasis = Optional.of((new BiomeGenOasis(BOPConfiguration.oasisID)).setColor(16421912).setBiomeName("Oasis").setTemperatureRainfall(1.9F, 2.0F).setMinMaxHeight(0.3F, 0.4F));

		Biomes.oceanCoral = Optional.of((new BiomeGenOceanCoral(BOPConfiguration.oceanCoralID)).setColor(10486015).setBiomeName("Coral Reef").setMinMaxHeight(-0.4F, -0.1F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.oceanKelp = Optional.of((new BiomeGenOceanKelp(BOPConfiguration.oceanKelpID)).setColor(10486015).setBiomeName("Kelp Forest").setMinMaxHeight(-0.4F, -0.1F).setTemperatureRainfall(0.5F, 0.9F));

		Biomes.ominousWoods = Optional.of((new BiomeGenOminousWoods(BOPConfiguration.ominousWoodsID)).setColor(353825).setBiomeName("Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.ominousWoodsThick = Optional.of((new BiomeGenOminousWoodsThick(BOPConfiguration.ominousWoodsThickID)).setColor(353825).setBiomeName("Thick Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.orchard = Optional.of((new BiomeGenOrchard(BOPConfiguration.orchardID)).setColor(9286496).setBiomeName("Orchard").setTemperatureRainfall(0.8F, 0.4F));
		Biomes.originValley = Optional.of((new BiomeGenOriginValley(BOPConfiguration.originValleyID)).setColor(353825).setBiomeName("Origin Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.6F));
		Biomes.outback = Optional.of((new BiomeGenOutback(BOPConfiguration.outbackID)).setColor(9286496).setBiomeName("Outback").setTemperatureRainfall(0.8F, 0.0F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.pasture = Optional.of((new BiomeGenPasture(BOPConfiguration.pastureID)).setColor(9286496).setBiomeName("Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.pastureMeadow = Optional.of((new BiomeGenPastureMeadow(BOPConfiguration.pastureMeadowID)).setColor(9286496).setBiomeName("Pasture Meadow").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.pastureThin = Optional.of((new BiomeGenPastureThin(BOPConfiguration.pastureThinID)).setColor(9286496).setBiomeName("Thinned Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.polar = Optional.of((new BiomeGenPolar(BOPConfiguration.polarID)).setColor(6316128).setBiomeName("Polar").setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.0F));
		Biomes.prairie = Optional.of((new BiomeGenPrairie(BOPConfiguration.prairieID)).setColor(353825).setBiomeName("Prairie").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.6F).setMinMaxHeight(0.3F, 0.4F));

		Biomes.promisedLandForest = Optional.of((new BiomeGenPromisedLandForest(BOPConfiguration.promisedLandForestID)).setColor(112).setBiomeName("Wonderous Woods").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));
		Biomes.promisedLandPlains = Optional.of((new BiomeGenPromisedLandPlains(BOPConfiguration.promisedLandPlainsID)).setColor(112).setBiomeName("Majestic Meadow").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));
		Biomes.promisedLandSwamp = Optional.of((new BiomeGenPromisedLandSwamp(BOPConfiguration.promisedLandSwampID)).setColor(112).setBiomeName("Blessed Bog").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));

		Biomes.quagmire = Optional.of((new BiomeGenQuagmire(BOPConfiguration.quagmireID)).setColor(522674).setBiomeName("Quagmire").func_76733_a(9154376).setMinMaxHeight(0.0F, 0.4F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.rainforest = Optional.of((new BiomeGenRainforest(BOPConfiguration.rainforestID)).setColor(5470985).setBiomeName("Rainforest").func_76733_a(5470985).setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.7F, 1.8F));
		Biomes.redwoodForest = Optional.of((new BiomeGenRedwoodForest(BOPConfiguration.redwoodForestID)).setColor(747097).setBiomeName("Redwood Forest").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.sacredSprings = Optional.of((new BiomeGenSacredSprings(BOPConfiguration.sacredSpringsID)).setColor(522674).setBiomeName("Sacred Springs").func_76733_a(9154376).setMinMaxHeight(0.4F, 1.2F).setTemperatureRainfall(1.2F, 0.9F));
		Biomes.savanna = Optional.of((new BiomeGenSavanna(BOPConfiguration.savannaID)).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.scrubland = Optional.of((new BiomeGenScrubland(BOPConfiguration.scrublandID)).setColor(9286496).setBiomeName("Scrubland").setTemperatureRainfall(1.2F, 0.0F).setMinMaxHeight(0.3F, 0.5F));
		Biomes.seasonalForest = Optional.of((new BiomeGenSeasonalForest(BOPConfiguration.seasonalForestID)).setColor(353825).setBiomeName("Seasonal Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.seasonalSpruceForest = Optional.of((new BiomeGenSeasonalSpruceForest(BOPConfiguration.seasonalSpruceForestID)).setColor(353825).setBiomeName("Seasonal Spruce Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.shield = Optional.of((new BiomeGenShield(BOPConfiguration.shieldID)).setColor(522674).setBiomeName("Shield").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.4F).setTemperatureRainfall(0.5F, 0.8F));
		Biomes.shore = Optional.of((new BiomeGenShore(BOPConfiguration.shoreID)).setColor(9286496).setBiomeName("Shore").setMinMaxHeight(-1.0F, 0.4F).setTemperatureRainfall(0.8F, 0.4F));
		Biomes.shrubland = Optional.of((new BiomeGenShrubland(BOPConfiguration.shrublandID)).setColor(9286496).setBiomeName("Shrubland").setMinMaxHeight(0.3F, 0.4F).setTemperatureRainfall(0.6F, 0.0F));
		Biomes.shrublandForest = Optional.of((new BiomeGenShrublandForest(BOPConfiguration.shrublandForestID)).setColor(9286496).setBiomeName("Thick Shrubland").setMinMaxHeight(0.3F, 0.4F).setTemperatureRainfall(0.6F, 0.0F));
		Biomes.sludgepit = Optional.of((new BiomeGenSludgepit(BOPConfiguration.sludgepitID)).setColor(522674).setBiomeName("Sludgepit").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.spruceWoods = Optional.of((new BiomeGenSpruceWoods(BOPConfiguration.spruceWoodsID)).setColor(353825).setBiomeName("Spruce Woods").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.7F));
		Biomes.steppe = Optional.of((new BiomeGenSteppe(BOPConfiguration.steppeID)).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.temperateRainforest = Optional.of((new BiomeGenTemperateRainforest(BOPConfiguration.temperateRainforestID)).setColor(353825).setBiomeName("Temperate Rainforest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 1.2F));
		Biomes.thicket = Optional.of((new BiomeGenThicket(BOPConfiguration.thicketID)).setColor(353825).setBiomeName("Thicket").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.2F).setMinMaxHeight(0.2F, 0.2F));
		Biomes.timber = Optional.of((new BiomeGenTimber(BOPConfiguration.timberID)).setColor(353825).setBiomeName("Timber").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.timberThin = Optional.of((new BiomeGenTimberThin(BOPConfiguration.timberThinID)).setColor(353825).setBiomeName("Thinned Timber").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.tropicalRainforest = Optional.of((new BiomeGenTropicalRainforest(BOPConfiguration.tropicalRainforestID)).setColor(9286496).setBiomeName("Tropical Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.tropics = Optional.of((new BiomeGenTropics(BOPConfiguration.tropicsID)).setColor(9286496).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 1.5F));
		Biomes.tundra = Optional.of((new BiomeGenTundra(BOPConfiguration.tundraID)).setColor(14090235).setBiomeName("Tundra").setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.2F, 0.4F));
		Biomes.volcano = Optional.of((new BiomeGenVolcano(BOPConfiguration.volcanoID)).setColor(9286496).setBiomeName("Volcano").setDisableRain().setMinMaxHeight(0.6F, 0.9F).setTemperatureRainfall(2.0F, 0.0F));
		Biomes.wasteland = Optional.of((new BiomeGenWasteland(BOPConfiguration.wastelandID)).setColor(16421912).setBiomeName("Wasteland").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.wetland = Optional.of((new BiomeGenWetland(BOPConfiguration.wetlandID)).setColor(522674).setBiomeName("Wetland").func_76733_a(9154376).setMinMaxHeight(0.3F, 0.5F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.woodland = Optional.of((new BiomeGenWoodland(BOPConfiguration.woodlandID)).setColor(353825).setBiomeName("Woodland").func_76733_a(5159473).setTemperatureRainfall(1.7F, 0.2F).setMinMaxHeight(0.3F, 0.4F));

		Biomes.plainsNew = Optional.of((new BiomeGenPlainsNew(BOPConfiguration.plainsNewID)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F));
		Biomes.desertNew = Optional.of((new BiomeGenDesertNew(BOPConfiguration.desertNewID)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.2F, 0.3F));
		Biomes.extremeHillsNew = Optional.of((new BiomeGenHillsNew(BOPConfiguration.extremeHillsNewID)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(0.3F, 1.5F).setTemperatureRainfall(0.2F, 0.3F));
		Biomes.forestNew = Optional.of((new BiomeGenForestNew(BOPConfiguration.forestNewID)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.taigaNew = Optional.of((new BiomeGenTaigaNew(BOPConfiguration.taigaNewID)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.3F, 0.5F));
		Biomes.swamplandNew = Optional.of((new BiomeGenSwampNew(BOPConfiguration.swamplandNewID)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.jungleNew = Optional.of((new BiomeGenJungleNew(BOPConfiguration.jungleNewID)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.5F));
	}

	private static void addToBiomeDictionary()
	{
		BiomeDictionary.registerBiomeType(Biomes.alps.get(), Type.FROZEN, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.arctic.get(), Type.FROZEN, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.badlands.get(), Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.bambooForest.get(), Type.JUNGLE);
		BiomeDictionary.registerBiomeType(Biomes.bayou.get(), Type.SWAMP);

		BiomeDictionary.registerBiomeType(Biomes.beachGravel.get(), Type.BEACH);
		BiomeDictionary.registerBiomeType(Biomes.beachOvergrown.get(), Type.BEACH, Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.birchForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.bog.get(), Type.SWAMP, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.borealForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.brushland.get(), Type.DESERT, Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.canyon.get(), Type.DESERT, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.chaparral.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.cherryBlossomGrove.get(), Type.MAGICAL, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.coniferousForest.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.coniferousForestSnow.get(), Type.FROZEN, Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.crag.get(), Type.WASTELAND, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.deadForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.deadForestSnow.get(), Type.FOREST, Type.FROZEN);
		BiomeDictionary.registerBiomeType(Biomes.deadlands.get(), Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.deadSwamp.get(), Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.deciduousForest.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.dunes.get(), Type.BEACH, Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.fen.get(), Type.FOREST, Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.field.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.frostForest.get(), Type.FROZEN, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.fungiForest.get(), Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.garden.get(), Type.MAGICAL, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.glacier.get(), Type.FROZEN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.grassland.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.grove.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.heathland.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.highland.get(), Type.HILLS, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.hotSprings.get(), Type.HILLS, Type.FOREST, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.icyHills.get(), Type.FROZEN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.jadeCliffs.get(), Type.FOREST, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.lushDesert.get(), Type.DESERT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.lushSwamp.get(), Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.mangrove.get(), Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.mapleWoods.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.marsh.get(), Type.SWAMP, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.meadow.get(), Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.mesa.get(), Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.moor.get(), Type.PLAINS, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.mountain.get(), Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.mysticGrove.get(), Type.MAGICAL, Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.netherBase.get(), Type.NETHER);
		BiomeDictionary.registerBiomeType(Biomes.netherGarden.get(), Type.NETHER, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.netherDesert.get(), Type.NETHER, Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.netherLava.get(), Type.NETHER);
		BiomeDictionary.registerBiomeType(Biomes.netherBone.get(), Type.NETHER, Type.WASTELAND);

		BiomeDictionary.registerBiomeType(Biomes.oasis.get(), Type.DESERT, Type.PLAINS);

		BiomeDictionary.registerBiomeType(Biomes.oceanCoral.get(), Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.oceanKelp.get(), Type.WATER, Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.ominousWoods.get(), Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.orchard.get(), Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.outback.get(), Type.DESERT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.pasture.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.polar.get(), Type.FROZEN, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.prairie.get(), Type.PLAINS);

		BiomeDictionary.registerBiomeType(Biomes.promisedLandForest.get(), Type.FOREST, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.promisedLandPlains.get(), Type.PLAINS, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.promisedLandSwamp.get(), Type.SWAMP, Type.MAGICAL);

		BiomeDictionary.registerBiomeType(Biomes.quagmire.get(), Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.rainforest.get(), Type.JUNGLE, Type.HILLS, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.redwoodForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.sacredSprings.get(), Type.MOUNTAIN, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.savanna.get(), Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.scrubland.get(), Type.DESERT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.seasonalForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.shield.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.shrubland.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.sludgepit.get(), Type.SWAMP, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.spruceWoods.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.steppe.get(), Type.PLAINS, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.temperateRainforest.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.thicket.get(), Type.PLAINS, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.timber.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.tropicalRainforest.get(), Type.JUNGLE);
		BiomeDictionary.registerBiomeType(Biomes.tropics.get(), Type.JUNGLE);
		BiomeDictionary.registerBiomeType(Biomes.tundra.get(), Type.FROZEN, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.volcano.get(), Type.WASTELAND, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.wasteland.get(), Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.wetland.get(), Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.woodland.get(), Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.plainsNew.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.desertNew.get(), Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.forestNew.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.extremeHillsNew.get(), Type.HILLS, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.taigaNew.get(), Type.FROZEN);
		BiomeDictionary.registerBiomeType(Biomes.swamplandNew.get(), Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.jungleNew.get(), Type.JUNGLE);
	}

	private static void addSpawnBiomes()
	{
		addSpawnBiome(Biomes.alps);
		addSpawnBiome(Biomes.arctic);
		addSpawnBiome(Biomes.badlands);
		addSpawnBiome(Biomes.bambooForest);
		addSpawnBiome(Biomes.bayou);
		addSpawnBiome(Biomes.birchForest);
		addSpawnBiome(Biomes.bog);
		addSpawnBiome(Biomes.borealForest);
		addSpawnBiome(Biomes.brushland);
		addSpawnBiome(Biomes.canyon);
		addSpawnBiome(Biomes.chaparral);
		addSpawnBiome(Biomes.cherryBlossomGrove);
		addSpawnBiome(Biomes.coniferousForest);
		addSpawnBiome(Biomes.coniferousForestSnow);
		addSpawnBiome(Biomes.deadForest);
		addSpawnBiome(Biomes.deadForestSnow);
		addSpawnBiome(Biomes.deadSwamp);
		addSpawnBiome(Biomes.deciduousForest);
		addSpawnBiome(Biomes.dunes);
		addSpawnBiome(Biomes.fen);
		addSpawnBiome(Biomes.field);
		addSpawnBiome(Biomes.frostForest);
		addSpawnBiome(Biomes.glacier);
		addSpawnBiome(Biomes.grassland);
		addSpawnBiome(Biomes.grove);
		addSpawnBiome(Biomes.heathland);
		addSpawnBiome(Biomes.highland);
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
		addSpawnBiome(Biomes.outback);
		addSpawnBiome(Biomes.pasture);
		addSpawnBiome(Biomes.polar);
		addSpawnBiome(Biomes.prairie);
		addSpawnBiome(Biomes.quagmire);
		addSpawnBiome(Biomes.rainforest);
		addSpawnBiome(Biomes.redwoodForest);
		addSpawnBiome(Biomes.savanna);
		addSpawnBiome(Biomes.scrubland);
		addSpawnBiome(Biomes.seasonalForest);
		addSpawnBiome(Biomes.shield);
		addSpawnBiome(Biomes.shrubland);
		addSpawnBiome(Biomes.sludgepit);
		addSpawnBiome(Biomes.spruceWoods);
		addSpawnBiome(Biomes.temperateRainforest);
		addSpawnBiome(Biomes.thicket);
		addSpawnBiome(Biomes.timber);
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
	}

	private static void addVillageBiomes()
	{
		addVillageBiome(Biomes.alps, BOPConfiguration.alpsVillage);
		addVillageBiome(Biomes.arctic, BOPConfiguration.arcticVillage);
		addVillageBiome(Biomes.badlands, BOPConfiguration.badlandsVillage);
		addVillageBiome(Biomes.bambooForest, BOPConfiguration.bambooForestVillage);
		addVillageBiome(Biomes.bayou, BOPConfiguration.bayouVillage);
		addVillageBiome(Biomes.birchForest, BOPConfiguration.birchForestVillage);
		addVillageBiome(Biomes.bog, BOPConfiguration.bogVillage);
		addVillageBiome(Biomes.borealForest, BOPConfiguration.borealForestVillage);
		addVillageBiome(Biomes.brushland, BOPConfiguration.brushlandVillage);
		addVillageBiome(Biomes.canyon, BOPConfiguration.canyonVillage);
		addVillageBiome(Biomes.chaparral, BOPConfiguration.chaparralVillage);
		addVillageBiome(Biomes.cherryBlossomGrove, BOPConfiguration.cherryBlossomGroveVillage);
		addVillageBiome(Biomes.coniferousForest, BOPConfiguration.coniferousForestVillage);
		addVillageBiome(Biomes.coniferousForestSnow, BOPConfiguration.coniferousForestSnowVillage);
		addVillageBiome(Biomes.deadForest, BOPConfiguration.deadForestVillage);
		addVillageBiome(Biomes.deadForestSnow, BOPConfiguration.deadForestSnowVillage);
		addVillageBiome(Biomes.deadSwamp, BOPConfiguration.deadSwampVillage);
		addVillageBiome(Biomes.deciduousForest, BOPConfiguration.deciduousForestVillage);
		addVillageBiome(Biomes.dunes, BOPConfiguration.dunesVillage);
		addVillageBiome(Biomes.fen, BOPConfiguration.fenVillage);
		addVillageBiome(Biomes.field, BOPConfiguration.fieldVillage);
		addVillageBiome(Biomes.frostForest, BOPConfiguration.frostForestVillage);
		addVillageBiome(Biomes.glacier, BOPConfiguration.glacierVillage);
		addVillageBiome(Biomes.grassland, BOPConfiguration.grasslandVillage);
		addVillageBiome(Biomes.grove, BOPConfiguration.groveVillage);
		addVillageBiome(Biomes.heathland, BOPConfiguration.heathlandVillage);
		addVillageBiome(Biomes.highland, BOPConfiguration.highlandVillage);
		addVillageBiome(Biomes.hotSprings, BOPConfiguration.hotSpringsVillage);
		addVillageBiome(Biomes.jadeCliffs, BOPConfiguration.jadeCliffsVillage);
		addVillageBiome(Biomes.lushDesert, BOPConfiguration.lushDesertVillage);
		addVillageBiome(Biomes.lushSwamp, BOPConfiguration.lushSwampVillage);
		addVillageBiome(Biomes.mangrove, BOPConfiguration.mangroveVillage);
		addVillageBiome(Biomes.mapleWoods, BOPConfiguration.mapleWoodsVillage);
		addVillageBiome(Biomes.marsh, BOPConfiguration.marshVillage);
		addVillageBiome(Biomes.meadow, BOPConfiguration.meadowVillage);
		addVillageBiome(Biomes.mesa, BOPConfiguration.mesaVillage);
		addVillageBiome(Biomes.moor, BOPConfiguration.moorVillage);
		addVillageBiome(Biomes.mountain, BOPConfiguration.mountainVillage);
		addVillageBiome(Biomes.oasis, BOPConfiguration.oasisVillage);
		addVillageBiome(Biomes.orchard, BOPConfiguration.orchardVillage);
		addVillageBiome(Biomes.outback, BOPConfiguration.outbackVillage);
		addVillageBiome(Biomes.pasture, BOPConfiguration.pastureVillage);
		addVillageBiome(Biomes.polar, BOPConfiguration.polarVillage);
		addVillageBiome(Biomes.prairie, BOPConfiguration.prairieVillage);
		addVillageBiome(Biomes.quagmire, BOPConfiguration.quagmireVillage);
		addVillageBiome(Biomes.rainforest, BOPConfiguration.rainforestVillage);
		addVillageBiome(Biomes.redwoodForest, BOPConfiguration.redwoodForestVillage);
		addVillageBiome(Biomes.savanna, BOPConfiguration.savannaVillage);
		addVillageBiome(Biomes.scrubland, BOPConfiguration.scrublandVillage);
		addVillageBiome(Biomes.seasonalForest, BOPConfiguration.seasonalForestVillage);
		addVillageBiome(Biomes.shield, BOPConfiguration.shieldVillage);
		addVillageBiome(Biomes.shrubland, BOPConfiguration.shrublandVillage);
		addVillageBiome(Biomes.sludgepit, BOPConfiguration.sludgepitVillage);
		addVillageBiome(Biomes.spruceWoods, BOPConfiguration.spruceWoodsVillage);
		addVillageBiome(Biomes.temperateRainforest, BOPConfiguration.temperateRainforestVillage);
		addVillageBiome(Biomes.thicket, BOPConfiguration.thicketVillage);
		addVillageBiome(Biomes.timber, BOPConfiguration.timberVillage);
		addVillageBiome(Biomes.tropicalRainforest, BOPConfiguration.tropicalRainforestVillage);
		addVillageBiome(Biomes.tropics, BOPConfiguration.tropicsVillage);
		addVillageBiome(Biomes.tundra, BOPConfiguration.tundraVillage);
		addVillageBiome(Biomes.volcano, BOPConfiguration.volcanoVillage);
		addVillageBiome(Biomes.wetland, BOPConfiguration.wetlandVillage);
		addVillageBiome(Biomes.woodland, BOPConfiguration.woodlandVillage);

		addVillageBiome(Biomes.plainsNew, BOPConfiguration.plainsVillage);
		addVillageBiome(Biomes.desertNew, BOPConfiguration.desertVillage);
		addVillageBiome(Biomes.forestNew, BOPConfiguration.forestVillage);
		addVillageBiome(Biomes.extremeHillsNew, BOPConfiguration.extremeHillsVillage);
		addVillageBiome(Biomes.taigaNew, BOPConfiguration.taigaVillage);
		addVillageBiome(Biomes.swamplandNew, BOPConfiguration.swamplandVillage);
		addVillageBiome(Biomes.jungleNew, BOPConfiguration.jungleVillage);
	}

	private static void addStrongholdBiomes()
	{
		addStrongholdBiome(Biomes.alps);
		addStrongholdBiome(Biomes.arctic);
		addStrongholdBiome(Biomes.badlands);
		addStrongholdBiome(Biomes.bambooForest);
		addStrongholdBiome(Biomes.bayou);
		addStrongholdBiome(Biomes.birchForest);
		addStrongholdBiome(Biomes.bog);
		addStrongholdBiome(Biomes.borealForest);
		addStrongholdBiome(Biomes.brushland);
		addStrongholdBiome(Biomes.canyon);
		addStrongholdBiome(Biomes.chaparral);
		addStrongholdBiome(Biomes.cherryBlossomGrove);
		addStrongholdBiome(Biomes.coniferousForest);
		addStrongholdBiome(Biomes.coniferousForestSnow);
		addStrongholdBiome(Biomes.crag);
		addStrongholdBiome(Biomes.deadForest);
		addStrongholdBiome(Biomes.deadForestSnow);
		addStrongholdBiome(Biomes.deadSwamp);
		addStrongholdBiome(Biomes.deadlands);
		addStrongholdBiome(Biomes.deciduousForest);
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
		addStrongholdBiome(Biomes.hotSprings);
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
		addStrongholdBiome(Biomes.polar);
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
		addStrongholdBiome(Biomes.sludgepit);
		addStrongholdBiome(Biomes.spruceWoods);
		addStrongholdBiome(Biomes.steppe);
		addStrongholdBiome(Biomes.temperateRainforest);
		addStrongholdBiome(Biomes.thicket);
		addStrongholdBiome(Biomes.timber);
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
	}

	private static void registerBiomes()
	{
		if (BOPConfiguration.addToDefault)
		{
			if (BOPConfiguration.alpsGen) {
				registerBiome(Biomes.alps);
			}

			if (BOPConfiguration.arcticGen) {
				registerBiome(Biomes.arctic);
			}

			if (BOPConfiguration.badlandsGen) {
				registerBiome(Biomes.badlands);
			}

			if (BOPConfiguration.bambooForestGen) {
				registerBiome(Biomes.bambooForest);
			}

			if (BOPConfiguration.bayouGen) {
				registerBiome(Biomes.bayou);
			}

			if (BOPConfiguration.birchForestGen) {
				registerBiome(Biomes.birchForest);
			}

			if (BOPConfiguration.bogGen) {
				registerBiome(Biomes.bog);
			}

			if (BOPConfiguration.borealForestGen) {
				registerBiome(Biomes.borealForest);
			}

			if (BOPConfiguration.brushlandGen) {
				registerBiome(Biomes.brushland);
			}

			if (BOPConfiguration.canyonGen) {
				registerBiome(Biomes.canyon);
			}

			if (BOPConfiguration.chaparralGen) {
				registerBiome(Biomes.chaparral);
			}

			if (BOPConfiguration.cherryBlossomGroveGen) {
				registerBiome(Biomes.cherryBlossomGrove);
			}

			if (BOPConfiguration.coniferousForestGen) {
				registerBiome(Biomes.coniferousForest);
			}

			if (BOPConfiguration.coniferousForestSnowGen) {
				registerBiome(Biomes.coniferousForestSnow);
			}

			if (BOPConfiguration.cragGen) {
				registerBiome(Biomes.crag);
			}

			if (BOPConfiguration.deadForestGen) {
				registerBiome(Biomes.deadForest);
			}

			if (BOPConfiguration.deadForestSnowGen) {
				registerBiome(Biomes.deadForestSnow);
			}

			if (BOPConfiguration.deadSwampGen) {
				registerBiome(Biomes.deadSwamp);
			}

			if (BOPConfiguration.deadlandsGen) {
				registerBiome(Biomes.deadlands);
			}

			if (BOPConfiguration.deciduousForestGen) {
				registerBiome(Biomes.deciduousForest);
			}

			if (BOPConfiguration.dunesGen) {
				registerBiome(Biomes.dunes);
			}

			if (BOPConfiguration.fenGen) {
				registerBiome(Biomes.fen);
			}

			if (BOPConfiguration.fieldGen) {
				registerBiome(Biomes.field);
			}

			if (BOPConfiguration.frostForestGen) {
				registerBiome(Biomes.frostForest);
			}

			if (BOPConfiguration.fungiForestGen) {
				registerBiome(Biomes.fungiForest);
			}

			if (BOPConfiguration.gardenGen) {
				registerBiome(Biomes.garden);
			}

			if (BOPConfiguration.glacierGen) {
				registerBiome(Biomes.glacier);
			}

			if (BOPConfiguration.grasslandGen) {
				registerBiome(Biomes.grassland);
			}

			if (BOPConfiguration.groveGen) {
				registerBiome(Biomes.grove);
			}

			if (BOPConfiguration.heathlandGen) {
				registerBiome(Biomes.heathland);
			}

			if (BOPConfiguration.highlandGen) {
				registerBiome(Biomes.highland);
			}

			if (BOPConfiguration.hotSpringsGen) {
				registerBiome(Biomes.hotSprings);
			}

			if (BOPConfiguration.icyHillsGen) {
				registerBiome(Biomes.icyHills);
			}

			if (BOPConfiguration.jadeCliffsGen) {
				registerBiome(Biomes.jadeCliffs);
			}

			if (BOPConfiguration.lushDesertGen) {
				registerBiome(Biomes.lushDesert);
			}

			if (BOPConfiguration.lushSwampGen) {
				registerBiome(Biomes.lushSwamp);
			}

			if (BOPConfiguration.mangroveGen) {
				registerBiome(Biomes.mangrove);
			}

			if (BOPConfiguration.mapleWoodsGen) {
				registerBiome(Biomes.mapleWoods);
			}

			if (BOPConfiguration.marshGen) {
				registerBiome(Biomes.marsh);
			}

			if (BOPConfiguration.meadowGen) {
				registerBiome(Biomes.meadow);
			}

			if (BOPConfiguration.mesaGen) {
				registerBiome(Biomes.mesa);
			}

			if (BOPConfiguration.moorGen) {
				registerBiome(Biomes.moor);
			}

			if (BOPConfiguration.mountainGen) {
				registerBiome(Biomes.mountain);
			}

			//            if (BOPConfiguration.mushroomIslandGen)
				//                GameRegistry.addBiome(BiomeGenBase.mushroomIsland);

			if (BOPConfiguration.mysticGroveGen) {
				registerBiome(Biomes.mysticGrove);
			}

			if (BOPConfiguration.oasisGen) {
				registerBiome(Biomes.oasis);
			}

			if (BOPConfiguration.ominousWoodsGen) {
				registerBiome(Biomes.ominousWoods);
			}

			if (BOPConfiguration.orchardGen) {
				registerBiome(Biomes.orchard);
			}

			if (BOPConfiguration.originValleyGen) {
				registerBiome(Biomes.originValley);
			}

			if (BOPConfiguration.outbackGen) {
				registerBiome(Biomes.outback);
			}

			if (BOPConfiguration.pastureGen) {
				registerBiome(Biomes.pasture);
			}

			if (BOPConfiguration.polarGen) {
				registerBiome(Biomes.polar);
			}

			if (BOPConfiguration.prairieGen) {
				registerBiome(Biomes.prairie);
			}

			if (BOPConfiguration.quagmireGen) {
				registerBiome(Biomes.quagmire);
			}

			if (BOPConfiguration.rainforestGen) {
				registerBiome(Biomes.rainforest);
			}

			if (BOPConfiguration.redwoodForestGen) {
				registerBiome(Biomes.redwoodForest);
			}

			if (BOPConfiguration.sacredSpringsGen) {
				registerBiome(Biomes.sacredSprings);
			}

			if (BOPConfiguration.savannaGen) {
				registerBiome(Biomes.savanna);
			}

			if (BOPConfiguration.scrublandGen) {
				registerBiome(Biomes.scrubland);
			}

			if (BOPConfiguration.seasonalForestGen) {
				registerBiome(Biomes.seasonalForest);
			}

			if (BOPConfiguration.shieldGen) {
				registerBiome(Biomes.shield);
			}

			if (BOPConfiguration.shrublandGen) {
				registerBiome(Biomes.shrubland);
			}

			if (BOPConfiguration.sludgepitGen) {
				registerBiome(Biomes.sludgepit);
			}

			if (BOPConfiguration.spruceWoodsGen) {
				registerBiome(Biomes.spruceWoods);
			}

			if (BOPConfiguration.steppeGen) {
				registerBiome(Biomes.steppe);
			}

			if (BOPConfiguration.temperateRainforestGen) {
				registerBiome(Biomes.temperateRainforest);
			}

			if (BOPConfiguration.thicketGen) {
				registerBiome(Biomes.thicket);
			}

			if (BOPConfiguration.timberGen) {
				registerBiome(Biomes.timber);
			}

			if (BOPConfiguration.tropicalRainforestGen) {
				registerBiome(Biomes.tropicalRainforest);
			}

			if (BOPConfiguration.tropicsGen) {
				registerBiome(Biomes.tropics);
			}

			if (BOPConfiguration.tundraGen) {
				registerBiome(Biomes.tundra);
			}

			if (BOPConfiguration.volcanoGen) {
				registerBiome(Biomes.volcano);
			}

			if (BOPConfiguration.wastelandGen) {
				registerBiome(Biomes.wasteland);
			}

			if (BOPConfiguration.wetlandGen) {
				registerBiome(Biomes.wetland);
			}

			if (BOPConfiguration.woodlandGen) {
				registerBiome(Biomes.woodland);
			}

			// Vanilla biomes generation
			if (BOPConfiguration.plainsGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.plainsNew);
					GameRegistry.removeBiome(BiomeGenBase.plains);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.plains);
			}

			if (BOPConfiguration.desertGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.desertNew);
					GameRegistry.removeBiome(BiomeGenBase.desert);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.desert);
			}

			if (BOPConfiguration.extremeHillsGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.extremeHillsNew);
					GameRegistry.removeBiome(BiomeGenBase.extremeHills);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.extremeHills);
			}

			if (BOPConfiguration.forestGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.forestNew);
					GameRegistry.removeBiome(BiomeGenBase.forest);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.forest);
			}

			if (BOPConfiguration.taigaGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.taigaNew);
					GameRegistry.removeBiome(BiomeGenBase.taiga);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.taiga);
			}

			if (BOPConfiguration.swamplandGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.swamplandNew);
					GameRegistry.removeBiome(BiomeGenBase.swampland);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.swampland);
			}

			if (BOPConfiguration.jungleGen)
			{
				if (BOPConfiguration.vanillaEnhanced)
				{
					registerBiome(Biomes.jungleNew);
					GameRegistry.removeBiome(BiomeGenBase.jungle);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.jungle);
			}
		}
	}

	private static void addBiomes()
	{
		if (BOPConfiguration.alpsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.alps);
		}

		if (BOPConfiguration.arcticGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.arctic);
		}

		if (BOPConfiguration.badlandsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.badlands);
		}

		if (BOPConfiguration.bambooForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.bambooForest);
		}

		if (BOPConfiguration.bayouGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.bayou);
		}

		if (BOPConfiguration.birchForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.birchForest);
		}

		if (BOPConfiguration.bogGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.bog);
		}

		if (BOPConfiguration.borealForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.borealForest);
		}

		if (BOPConfiguration.brushlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.brushland);
		}

		if (BOPConfiguration.canyonGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.canyon);
		}

		if (BOPConfiguration.chaparralGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.chaparral);
		}

		if (BOPConfiguration.cherryBlossomGroveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.cherryBlossomGrove);
		}

		if (BOPConfiguration.coniferousForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.coniferousForest);
		}

		if (BOPConfiguration.coniferousForestSnowGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.coniferousForestSnow);
		}

		if (BOPConfiguration.cragGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.crag);
		}

		if (BOPConfiguration.deadForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadForest);
		}

		if (BOPConfiguration.deadForestSnowGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadForestSnow);
		}

		if (BOPConfiguration.deadSwampGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadSwamp);
		}

		if (BOPConfiguration.deadlandsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadlands);
		}

		if (BOPConfiguration.deciduousForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deciduousForest);
		}

		if (BOPConfiguration.dunesGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.dunes);
		}

		if (BOPConfiguration.fenGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.fen);
		}

		if (BOPConfiguration.fieldGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.field);
		}

		if (BOPConfiguration.frostForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.frostForest);
		}

		if (BOPConfiguration.fungiForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.fungiForest);
		}

		if (BOPConfiguration.gardenGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.garden);
		}

		if (BOPConfiguration.glacierGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.glacier);
		}

		if (BOPConfiguration.grasslandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.grassland);
		}

		if (BOPConfiguration.groveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.grove);
		}

		if (BOPConfiguration.heathlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.heathland);
		}

		if (BOPConfiguration.highlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.highland);
		}

		if (BOPConfiguration.hotSpringsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.hotSprings);
		}

		if (BOPConfiguration.icyHillsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.icyHills);
		}

		if (BOPConfiguration.jadeCliffsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.jadeCliffs);
		}

		if (BOPConfiguration.lushDesertGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.lushDesert);
		}

		if (BOPConfiguration.lushSwampGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.lushSwamp);
		}

		if (BOPConfiguration.mangroveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mangrove);
		}

		if (BOPConfiguration.mapleWoodsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mapleWoods);
		}

		if (BOPConfiguration.marshGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.marsh);
		}

		if (BOPConfiguration.meadowGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.meadow);
		}

		if (BOPConfiguration.mesaGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mesa);
		}

		if (BOPConfiguration.moorGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.moor);
		}

		if (BOPConfiguration.mountainGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mountain);
		}

		if (BOPConfiguration.mysticGroveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mysticGrove);
		}

		if (BOPConfiguration.oasisGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.oasis);
		}

		if (BOPConfiguration.ominousWoodsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.ominousWoods);
		}

		if (BOPConfiguration.orchardGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.orchard);
		}

		if (BOPConfiguration.originValleyGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.originValley);
		}

		if (BOPConfiguration.outbackGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.outback);
		}

		if (BOPConfiguration.pastureGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.pasture);
		}

		if (BOPConfiguration.polarGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.polar);
		}

		if (BOPConfiguration.prairieGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.prairie);
		}

		if (BOPConfiguration.quagmireGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.quagmire);
		}

		if (BOPConfiguration.rainforestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.rainforest);
		}

		if (BOPConfiguration.redwoodForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.redwoodForest);
		}

		if (BOPConfiguration.sacredSpringsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.sacredSprings);
		}

		if (BOPConfiguration.savannaGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.savanna);
		}

		if (BOPConfiguration.scrublandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.scrubland);
		}

		if (BOPConfiguration.seasonalForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.seasonalForest);
		}

		if (BOPConfiguration.shieldGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.shield);
		}

		if (BOPConfiguration.shrublandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.shrubland);
		}

		if (BOPConfiguration.sludgepitGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.sludgepit);
		}

		if (BOPConfiguration.spruceWoodsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.spruceWoods);
		}

		if (BOPConfiguration.steppeGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.steppe);
		}

		if (BOPConfiguration.temperateRainforestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.temperateRainforest);
		}

		if (BOPConfiguration.thicketGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.thicket);
		}

		if (BOPConfiguration.timberGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.timber);
		}

		if (BOPConfiguration.tropicalRainforestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.tropicalRainforest);
		}

		if (BOPConfiguration.tropicsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.tropics);
		}

		if (BOPConfiguration.tundraGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.tundra);
		}

		if (BOPConfiguration.volcanoGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.volcano);
		}

		if (BOPConfiguration.wastelandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.wasteland);
		}

		if (BOPConfiguration.wetlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.wetland);
		}

		if (BOPConfiguration.woodlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.woodland);
		}

		// Vanilla biomes generation
		if (BOPConfiguration.plainsGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.plainsNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.plains);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.plains);
		}

		if (BOPConfiguration.desertGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.desertNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.desert);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.desert);
		}

		if (BOPConfiguration.extremeHillsGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.extremeHillsNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.extremeHills);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.extremeHills);
		}

		if (BOPConfiguration.forestGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.forestNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.forest);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.forest);
		}

		if (BOPConfiguration.taigaGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.taigaNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.taiga);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.taiga);
		}

		if (BOPConfiguration.swamplandGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.swamplandNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.swampland);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.swampland);
		}

		if (BOPConfiguration.jungleGen)
		{
			if (BOPConfiguration.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.jungleNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.jungle);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.jungle);
		}
	}

	public static Set<WorldType> getWorldTypes() {
		if (worldTypes.isEmpty()) {
			if (BOPConfiguration.addToDefault)
			{
				worldTypes.add(WorldType.DEFAULT);
				worldTypes.add(WorldType.LARGE_BIOMES);
			}
			worldTypes.add(WTBiomesOP);
		}
		return ImmutableSet.copyOf(worldTypes);
	}

	private static void addBiomeToWorldTypes(Collection<WorldType> worldTypes, Optional<? extends BiomeGenBase> biome)
	{
		for (final WorldType worldType : worldTypes)
			if (biome.isPresent()) {
				worldType.addNewBiome(biome.get());
			}
	}

	private static void removeBiomeFromWorldTypes(Collection<WorldType> worldTypes, BiomeGenBase biome)
	{
		for (final WorldType worldType : worldTypes) {
			worldType.removeBiome(biome);
		}
	}

	private static void addSpawnBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent()) {
			BiomeManager.addSpawnBiome(biome.get());
		}
	}

	private static void addVillageBiome(Optional<? extends BiomeGenBase> biome, boolean flag)
	{
		if (biome.isPresent() && flag) {
			BiomeManager.addVillageBiome(biome.get(), true);
		}
	}

	private static void addStrongholdBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent()) {
			BiomeManager.addStrongholdBiome(biome.get());
		}
	}

	private static void registerBiome(Optional<? extends BiomeGenBase> biome)
	{
		if (biome.isPresent()) {
			GameRegistry.addBiome(biome.get());
		}
	}
}
