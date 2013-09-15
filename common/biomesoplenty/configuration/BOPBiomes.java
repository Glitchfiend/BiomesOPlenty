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
import biomesoplenty.biomes.BiomeGenAlps;
import biomesoplenty.biomes.BiomeGenAlpsBase;
import biomesoplenty.biomes.BiomeGenAlpsForest;
import biomesoplenty.biomes.BiomeGenArctic;
import biomesoplenty.biomes.BiomeGenAutumnHills;
import biomesoplenty.biomes.BiomeGenBadlands;
import biomesoplenty.biomes.BiomeGenBambooForest;
import biomesoplenty.biomes.BiomeGenBayou;
import biomesoplenty.biomes.BiomeGenBirchForest;
import biomesoplenty.biomes.BiomeGenBog;
import biomesoplenty.biomes.BiomeGenBorealForest;
import biomesoplenty.biomes.BiomeGenBrushland;
import biomesoplenty.biomes.BiomeGenCanyon;
import biomesoplenty.biomes.BiomeGenCanyonRavine;
import biomesoplenty.biomes.BiomeGenChaparral;
import biomesoplenty.biomes.BiomeGenCherryBlossomGrove;
import biomesoplenty.biomes.BiomeGenConiferousForest;
import biomesoplenty.biomes.BiomeGenConiferousForestSnow;
import biomesoplenty.biomes.BiomeGenCrag;
import biomesoplenty.biomes.BiomeGenDeadForest;
import biomesoplenty.biomes.BiomeGenDeadForestSnow;
import biomesoplenty.biomes.BiomeGenDeadSwamp;
import biomesoplenty.biomes.BiomeGenDeadlands;
import biomesoplenty.biomes.BiomeGenDeciduousForest;
import biomesoplenty.biomes.BiomeGenDunes;
import biomesoplenty.biomes.BiomeGenFen;
import biomesoplenty.biomes.BiomeGenField;
import biomesoplenty.biomes.BiomeGenFieldForest;
import biomesoplenty.biomes.BiomeGenFrostForest;
import biomesoplenty.biomes.BiomeGenFungiForest;
import biomesoplenty.biomes.BiomeGenGarden;
import biomesoplenty.biomes.BiomeGenGlacier;
import biomesoplenty.biomes.BiomeGenGrassland;
import biomesoplenty.biomes.BiomeGenGrove;
import biomesoplenty.biomes.BiomeGenHeathland;
import biomesoplenty.biomes.BiomeGenHighland;
import biomesoplenty.biomes.BiomeGenHotSprings;
import biomesoplenty.biomes.BiomeGenIcyHills;
import biomesoplenty.biomes.BiomeGenJadeCliffs;
import biomesoplenty.biomes.BiomeGenLushDesert;
import biomesoplenty.biomes.BiomeGenLushSwamp;
import biomesoplenty.biomes.BiomeGenMangrove;
import biomesoplenty.biomes.BiomeGenMapleWoods;
import biomesoplenty.biomes.BiomeGenMarsh;
import biomesoplenty.biomes.BiomeGenMeadow;
import biomesoplenty.biomes.BiomeGenMeadowForest;
import biomesoplenty.biomes.BiomeGenMesa;
import biomesoplenty.biomes.BiomeGenMoor;
import biomesoplenty.biomes.BiomeGenMountain;
import biomesoplenty.biomes.BiomeGenMysticGrove;
import biomesoplenty.biomes.BiomeGenOasis;
import biomesoplenty.biomes.BiomeGenOminousWoods;
import biomesoplenty.biomes.BiomeGenOminousWoodsThick;
import biomesoplenty.biomes.BiomeGenOrchard;
import biomesoplenty.biomes.BiomeGenOriginValley;
import biomesoplenty.biomes.BiomeGenOutback;
import biomesoplenty.biomes.BiomeGenOvergrownGreens;
import biomesoplenty.biomes.BiomeGenPasture;
import biomesoplenty.biomes.BiomeGenPastureMeadow;
import biomesoplenty.biomes.BiomeGenPastureThin;
import biomesoplenty.biomes.BiomeGenPolar;
import biomesoplenty.biomes.BiomeGenPrairie;
import biomesoplenty.biomes.BiomeGenQuagmire;
import biomesoplenty.biomes.BiomeGenRainforest;
import biomesoplenty.biomes.BiomeGenRedwoodForest;
import biomesoplenty.biomes.BiomeGenSacredSprings;
import biomesoplenty.biomes.BiomeGenSavanna;
import biomesoplenty.biomes.BiomeGenSavannaPlateau;
import biomesoplenty.biomes.BiomeGenScrubland;
import biomesoplenty.biomes.BiomeGenSeasonalForest;
import biomesoplenty.biomes.BiomeGenSeasonalSpruceForest;
import biomesoplenty.biomes.BiomeGenShield;
import biomesoplenty.biomes.BiomeGenShore;
import biomesoplenty.biomes.BiomeGenShrubland;
import biomesoplenty.biomes.BiomeGenShrublandForest;
import biomesoplenty.biomes.BiomeGenSilkglades;
import biomesoplenty.biomes.BiomeGenSludgepit;
import biomesoplenty.biomes.BiomeGenSpruceWoods;
import biomesoplenty.biomes.BiomeGenSteppe;
import biomesoplenty.biomes.BiomeGenTemperateRainforest;
import biomesoplenty.biomes.BiomeGenThicket;
import biomesoplenty.biomes.BiomeGenTimber;
import biomesoplenty.biomes.BiomeGenTimberThin;
import biomesoplenty.biomes.BiomeGenTropicalRainforest;
import biomesoplenty.biomes.BiomeGenTropics;
import biomesoplenty.biomes.BiomeGenTundra;
import biomesoplenty.biomes.BiomeGenVolcano;
import biomesoplenty.biomes.BiomeGenWasteland;
import biomesoplenty.biomes.BiomeGenWetland;
import biomesoplenty.biomes.BiomeGenWoodland;
import biomesoplenty.biomes.beach.BiomeGenBeachGravel;
import biomesoplenty.biomes.beach.BiomeGenBeachOvergrown;
import biomesoplenty.biomes.nether.BiomeGenNetherBase;
import biomesoplenty.biomes.nether.BiomeGenNetherBone;
import biomesoplenty.biomes.nether.BiomeGenNetherDesert;
import biomesoplenty.biomes.nether.BiomeGenNetherGarden;
import biomesoplenty.biomes.nether.BiomeGenNetherLava;
import biomesoplenty.biomes.ocean.BiomeGenOceanAbyss;
import biomesoplenty.biomes.ocean.BiomeGenOceanCoral;
import biomesoplenty.biomes.ocean.BiomeGenOceanKelp;
import biomesoplenty.biomes.promisedland.BiomeGenPromisedLandForest;
import biomesoplenty.biomes.promisedland.BiomeGenPromisedLandPlains;
import biomesoplenty.biomes.promisedland.BiomeGenPromisedLandSwamp;
import biomesoplenty.biomes.vanilla.BiomeGenDesertNew;
import biomesoplenty.biomes.vanilla.BiomeGenForestNew;
import biomesoplenty.biomes.vanilla.BiomeGenHillsNew;
import biomesoplenty.biomes.vanilla.BiomeGenJungleNew;
import biomesoplenty.biomes.vanilla.BiomeGenPlainsNew;
import biomesoplenty.biomes.vanilla.BiomeGenSwampNew;
import biomesoplenty.biomes.vanilla.BiomeGenTaigaNew;
import biomesoplenty.configuration.configfile.BOPConfigurationBiomeGen;
import biomesoplenty.configuration.configfile.BOPConfigurationIDs;
import biomesoplenty.configuration.configfile.BOPConfigurationTerrainGen;
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
		for(x = 255; x >= 0; x--) 
		{
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
		Biomes.alps = Optional.of((new BiomeGenAlps(BOPConfigurationIDs.alpsID)).setColor(353825).setBiomeName("Alps").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(2.0F, 3.0F));
		Biomes.alpsForest = Optional.of((new BiomeGenAlpsForest(BOPConfigurationIDs.alpsForestID)).setColor(353825).setBiomeName("Alps Mountainside").func_76733_a(5159473).setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(1.0F, 2.0F));
		Biomes.alpsBase = Optional.of((new BiomeGenAlpsBase(BOPConfigurationIDs.alpsBaseID)).setColor(353825).setBiomeName("Alps Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.2F, 0.5F));
		Biomes.arctic = Optional.of((new BiomeGenArctic(BOPConfigurationIDs.arcticID)).setColor(14090235).setBiomeName("Arctic").setEnableSnow().setTemperatureRainfall(0.05F, 0.5F).setMinMaxHeight(0.2F, 0.2F));
		Biomes.autumnHills = Optional.of((new BiomeGenAutumnHills(BOPConfigurationIDs.autumnHillsID)).setColor(522674).setBiomeName("Autumn Hills").func_76733_a(9154376).setMinMaxHeight(0.5F, 0.8F).setTemperatureRainfall(0.5F, 0.2F));
		Biomes.badlands = Optional.of((new BiomeGenBadlands(BOPConfigurationIDs.badlandsID)).setColor(16421912).setBiomeName("Badlands").setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.3F, 0.9F));
		Biomes.bambooForest = Optional.of((new BiomeGenBambooForest(BOPConfigurationIDs.bambooForestID)).setColor(112).setBiomeName("Bamboo Forest").setMinMaxHeight(0.2F, 0.4F).setTemperatureRainfall(1.2F, 0.9F));
		Biomes.bayou = Optional.of((new BiomeGenBayou(BOPConfigurationIDs.bayouID)).setColor(522674).setBiomeName("Bayou").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.5F, 0.9F));

		Biomes.beachGravel = Optional.of((new BiomeGenBeachGravel(BOPConfigurationIDs.beachGravelID)).setColor(16440917).setBiomeName("Gravel Beach").setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.0F, 0.1F));
		Biomes.beachOvergrown = Optional.of((new BiomeGenBeachOvergrown(BOPConfigurationIDs.beachOvergrownID)).setColor(16440917).setBiomeName("Overgrown Beach").setTemperatureRainfall(0.8F, 0.5F).setMinMaxHeight(0.0F, 0.1F));

		Biomes.birchForest = Optional.of((new BiomeGenBirchForest(BOPConfigurationIDs.birchForestID)).setColor(353825).setBiomeName("Birch Forest").func_76733_a(5159473).setTemperatureRainfall(0.4F, 0.3F));
		Biomes.bog = Optional.of((new BiomeGenBog(BOPConfigurationIDs.bogID)).setColor(522674).setBiomeName("Bog").func_76733_a(9154376).setMinMaxHeight(0.3F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.borealForest = Optional.of((new BiomeGenBorealForest(BOPConfigurationIDs.borealForestID)).setColor(353825).setBiomeName("Boreal Forest").func_76733_a(5159473).setMinMaxHeight(0.2F, 1.0F).setTemperatureRainfall(0.6F, 0.7F));
		Biomes.brushland = Optional.of((new BiomeGenBrushland(BOPConfigurationIDs.brushlandID)).setColor(16421912).setBiomeName("Brushland").setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.3F, 0.3F));
		Biomes.canyon = Optional.of((new BiomeGenCanyon(BOPConfigurationIDs.canyonID)).setColor(9286496).setBiomeName("Canyon").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(1.5F, 2.0F));
		Biomes.canyonRavine = Optional.of((new BiomeGenCanyonRavine(BOPConfigurationIDs.canyonRavineID)).setColor(9286496).setBiomeName("Canyon Ravine").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.chaparral = Optional.of((new BiomeGenChaparral(BOPConfigurationIDs.chaparralID)).setColor(9286496).setBiomeName("Chaparral").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.6F));
		Biomes.cherryBlossomGrove = Optional.of((new BiomeGenCherryBlossomGrove(BOPConfigurationIDs.cherryBlossomGroveID)).setColor(9286496).setBiomeName("Cherry Blossom Grove").setMinMaxHeight(0.3F, 0.4F).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.coniferousForest = Optional.of((new BiomeGenConiferousForest(BOPConfigurationIDs.coniferousForestID)).setColor(747097).setBiomeName("Coniferous Forest").func_76733_a(5159473).setTemperatureRainfall(0.5F, 0.4F).setMinMaxHeight(0.2F, 0.8F));
		Biomes.coniferousForestSnow = Optional.of((new BiomeGenConiferousForestSnow(BOPConfigurationIDs.coniferousForestSnowID)).setColor(14090235).setBiomeName("Snowy Coniferous Forest").setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(0.2F, 0.7F));
		Biomes.crag = Optional.of((new BiomeGenCrag(BOPConfigurationIDs.cragID)).setColor(9286496).setBiomeName("Crag").setMinMaxHeight(2.0F, 3.0F).setTemperatureRainfall(0.4F, 0.2F));
		Biomes.deadForest = Optional.of((new BiomeGenDeadForest(BOPConfigurationIDs.deadForestID)).setColor(522674).setBiomeName("Dead Forest").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.7F).setTemperatureRainfall(1.2F, 0.1F));
		Biomes.deadForestSnow = Optional.of((new BiomeGenDeadForestSnow(BOPConfigurationIDs.deadForestSnowID)).setColor(522674).setBiomeName("Snowy Dead Forest").func_76733_a(9154376).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.2F, 0.7F));
		Biomes.deadSwamp = Optional.of((new BiomeGenDeadSwamp(BOPConfigurationIDs.deadSwampID)).setColor(522674).setBiomeName("Dead Swamp").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.2F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.deadlands = Optional.of((new BiomeGenDeadlands(BOPConfigurationIDs.deadlandsID)).setColor(522674).setBiomeName("Deadlands").setDisableRain().func_76733_a(9154376).setMinMaxHeight(0.1F, 0.5F).setTemperatureRainfall(2.0F, 0.05F));
		Biomes.deciduousForest = Optional.of((new BiomeGenDeciduousForest(BOPConfigurationIDs.deciduousForestID)).setColor(353825).setBiomeName("Deciduous Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.dunes = Optional.of((new BiomeGenDunes(BOPConfigurationIDs.dunesID)).setColor(13786898).setBiomeName("Dunes").setDisableRain().setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.5F, 1.3F));
		Biomes.fen = Optional.of((new BiomeGenFen(BOPConfigurationIDs.fenID)).setColor(9286496).setBiomeName("Fen").setTemperatureRainfall(0.4F, 0.05F).setMinMaxHeight(0.2F, 0.4F));
		Biomes.field = Optional.of((new BiomeGenField(BOPConfigurationIDs.fieldID)).setColor(9286496).setBiomeName("Field").setTemperatureRainfall(0.6F, 0.7F).setMinMaxHeight(0.3F, 0.3F));
		Biomes.fieldForest = Optional.of((new BiomeGenFieldForest(BOPConfigurationIDs.fieldForestID)).setColor(9286496).setBiomeName("Forested Field").setTemperatureRainfall(0.6F, 0.7F).setMinMaxHeight(0.3F, 0.3F));
		Biomes.frostForest = Optional.of((new BiomeGenFrostForest(BOPConfigurationIDs.frostForestID)).setColor(14090235).setBiomeName("Frost Forest").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.fungiForest = Optional.of((new BiomeGenFungiForest(BOPConfigurationIDs.fungiForestID)).setColor(747097).setBiomeName("Fungi Forest").func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F).setMinMaxHeight(0.2F, 0.5F));
		Biomes.garden = Optional.of((new BiomeGenGarden(BOPConfigurationIDs.gardenID)).setColor(9286496).setBiomeName("Garden").setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.glacier = Optional.of((new BiomeGenGlacier(BOPConfigurationIDs.glacierID)).setColor(6316128).setBiomeName("Glacier").setEnableSnow().setMinMaxHeight(0.4F, 0.8F).setTemperatureRainfall(0.0F, 0.5F));
		Biomes.grassland = Optional.of((new BiomeGenGrassland(BOPConfigurationIDs.grasslandID)).setColor(9286496).setBiomeName("Grassland").setTemperatureRainfall(0.7F, 0.7F).setMinMaxHeight(0.2F, 0.5F));
		Biomes.grove = Optional.of((new BiomeGenGrove(BOPConfigurationIDs.groveID)).setColor(9286496).setBiomeName("Grove").setTemperatureRainfall(0.4F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.heathland = Optional.of((new BiomeGenHeathland(BOPConfigurationIDs.heathlandID)).setColor(353825).setBiomeName("Heathland").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.1F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.highland = Optional.of((new BiomeGenHighland(BOPConfigurationIDs.highlandID)).setColor(6316128).setBiomeName("Highland").setMinMaxHeight(0.9F, 1.9F).setTemperatureRainfall(0.5F, 0.5F));
		Biomes.hotSprings = Optional.of((new BiomeGenHotSprings(BOPConfigurationIDs.hotSpringsID)).setColor(10486015).setBiomeName("Hot Springs").setMinMaxHeight(0.2F, 0.5F).setTemperatureRainfall(0.5F, 0.7F));
		Biomes.icyHills = Optional.of((new BiomeGenIcyHills(BOPConfigurationIDs.icyHillsID)).setColor(14090235).setBiomeName("Icy Hills").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setMinMaxHeight(0.3F, 0.8F));
		Biomes.jadeCliffs = Optional.of((new BiomeGenJadeCliffs(BOPConfigurationIDs.jadeCliffsID)).setColor(14090235).setBiomeName("Jade Cliffs").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(0.5F, 1.5F));
		Biomes.lushDesert = Optional.of((new BiomeGenLushDesert(BOPConfigurationIDs.lushDesertID)).setColor(16421912).setBiomeName("Lush Desert").setTemperatureRainfall(0.8F, 0.2F).setMinMaxHeight(0.2F, 0.9F));
		Biomes.lushSwamp = Optional.of((new BiomeGenLushSwamp(BOPConfigurationIDs.lushSwampID)).setColor(522674).setBiomeName("Lush Swamp").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.3F).setTemperatureRainfall(0.7F, 1.0F));
		Biomes.mangrove = Optional.of((new BiomeGenMangrove(BOPConfigurationIDs.mangroveID)).setColor(16440917).setBiomeName("Mangrove").setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.mapleWoods = Optional.of((new BiomeGenMapleWoods(BOPConfigurationIDs.mapleWoodsID)).setColor(747097).setBiomeName("Maple Woods").func_76733_a(5159473).setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.3F, 0.6F));
		Biomes.marsh = Optional.of((new BiomeGenMarsh(BOPConfigurationIDs.marshID)).setColor(10486015).setBiomeName("Marsh").setMinMaxHeight(-0.5F, 0.05F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.meadow = Optional.of((new BiomeGenMeadow(BOPConfigurationIDs.meadowID)).setColor(9286496).setBiomeName("Meadow").setTemperatureRainfall(0.7F, 0.7F));
		Biomes.meadowForest = Optional.of((new BiomeGenMeadowForest(BOPConfigurationIDs.meadowForestID)).setColor(9286496).setBiomeName("Meadow Forest").setTemperatureRainfall(0.7F, 0.7F));
		Biomes.mesa = Optional.of((new BiomeGenMesa(BOPConfigurationIDs.mesaID)).setColor(16421912).setBiomeName("Mesa").setDisableRain().setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.4F, 2.0F));
		Biomes.moor = Optional.of((new BiomeGenMoor(BOPConfigurationIDs.moorID)).setColor(16421912).setBiomeName("Moor").setTemperatureRainfall(0.5F, 1.0F).setMinMaxHeight(0.7F, 0.8F));
		Biomes.mountain = Optional.of((new BiomeGenMountain(BOPConfigurationIDs.mountainID)).setColor(14090235).setBiomeName("Mountain").setTemperatureRainfall(0.5F, 0.1F).setMinMaxHeight(1.0F, 1.5F));
		Biomes.mysticGrove = Optional.of((new BiomeGenMysticGrove(BOPConfigurationIDs.mysticGroveID)).setColor(353825).setBiomeName("Mystic Grove").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.9F, 1.0F));

		Biomes.netherBase = Optional.of((new BiomeGenNetherBase(BOPConfigurationIDs.netherBaseID)).setColor(16711680).setBiomeName("Nether").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherGarden = Optional.of((new BiomeGenNetherGarden(BOPConfigurationIDs.netherGardenID)).setColor(16711680).setBiomeName("Undergarden").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherDesert = Optional.of((new BiomeGenNetherDesert(BOPConfigurationIDs.netherDesertID)).setColor(16711680).setBiomeName("Corrupted Sands").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherLava = Optional.of((new BiomeGenNetherLava(BOPConfigurationIDs.netherLavaID)).setColor(16711680).setBiomeName("Phantasmagoric Inferno").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));
		Biomes.netherBone = Optional.of((new BiomeGenNetherBone(BOPConfigurationIDs.netherBoneID)).setColor(16711680).setBiomeName("Boneyard").setDisableRain().setTemperatureRainfall(2.0F, 0.0F));

		Biomes.oasis = Optional.of((new BiomeGenOasis(BOPConfigurationIDs.oasisID)).setColor(16421912).setBiomeName("Oasis").setTemperatureRainfall(0.9F, 0.7F).setMinMaxHeight(0.3F, 0.4F));

		Biomes.oceanAbyss = Optional.of((new BiomeGenOceanAbyss(BOPConfigurationIDs.oceanAbyssID)).setColor(10486015).setBiomeName("Oceanic Abyss").setMinMaxHeight(-1.65F, 0.0F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.oceanCoral = Optional.of((new BiomeGenOceanCoral(BOPConfigurationIDs.oceanCoralID)).setColor(10486015).setBiomeName("Coral Reef").setMinMaxHeight(-0.1F, 0.0F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.oceanKelp = Optional.of((new BiomeGenOceanKelp(BOPConfigurationIDs.oceanKelpID)).setColor(10486015).setBiomeName("Kelp Forest").setMinMaxHeight(-0.4F, -0.1F).setTemperatureRainfall(0.5F, 0.9F));

		Biomes.ominousWoods = Optional.of((new BiomeGenOminousWoods(BOPConfigurationIDs.ominousWoodsID)).setColor(353825).setBiomeName("Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.ominousWoodsThick = Optional.of((new BiomeGenOminousWoodsThick(BOPConfigurationIDs.ominousWoodsThickID)).setColor(353825).setBiomeName("Thick Ominous Woods").setDisableRain().func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.orchard = Optional.of((new BiomeGenOrchard(BOPConfigurationIDs.orchardID)).setColor(9286496).setBiomeName("Orchard").setTemperatureRainfall(0.8F, 0.4F));
		Biomes.originValley = Optional.of((new BiomeGenOriginValley(BOPConfigurationIDs.originValleyID)).setColor(353825).setBiomeName("Origin Valley").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 0.6F));
		Biomes.outback = Optional.of((new BiomeGenOutback(BOPConfigurationIDs.outbackID)).setColor(9286496).setBiomeName("Outback").setTemperatureRainfall(0.8F, 0.05F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.overgrownGreens = Optional.of((new BiomeGenOvergrownGreens(BOPConfigurationIDs.overgrownGreensID)).setColor(353825).setBiomeName("Overgrown Greens").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.pasture = Optional.of((new BiomeGenPasture(BOPConfigurationIDs.pastureID)).setColor(9286496).setBiomeName("Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.pastureMeadow = Optional.of((new BiomeGenPastureMeadow(BOPConfigurationIDs.pastureMeadowID)).setColor(9286496).setBiomeName("Pasture Meadow").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.pastureThin = Optional.of((new BiomeGenPastureThin(BOPConfigurationIDs.pastureThinID)).setColor(9286496).setBiomeName("Thinned Pasture").setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.polar = Optional.of((new BiomeGenPolar(BOPConfigurationIDs.polarID)).setColor(6316128).setBiomeName("Polar").setMinMaxHeight(-0.5F, 0.0F).setTemperatureRainfall(0.0F, 0.5F));
		Biomes.prairie = Optional.of((new BiomeGenPrairie(BOPConfigurationIDs.prairieID)).setColor(353825).setBiomeName("Prairie").func_76733_a(5159473).setTemperatureRainfall(0.9F, 0.6F).setMinMaxHeight(0.3F, 0.4F));

		Biomes.promisedLandForest = Optional.of((new BiomeGenPromisedLandForest(BOPConfigurationIDs.promisedLandForestID)).setColor(112).setBiomeName("Wonderous Woods").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));
		Biomes.promisedLandPlains = Optional.of((new BiomeGenPromisedLandPlains(BOPConfigurationIDs.promisedLandPlainsID)).setColor(112).setBiomeName("Majestic Meadow").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));
		Biomes.promisedLandSwamp = Optional.of((new BiomeGenPromisedLandSwamp(BOPConfigurationIDs.promisedLandSwampID)).setColor(112).setBiomeName("Blessed Bog").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 2.0F));

		Biomes.quagmire = Optional.of((new BiomeGenQuagmire(BOPConfigurationIDs.quagmireID)).setColor(522674).setBiomeName("Quagmire").func_76733_a(9154376).setMinMaxHeight(0.2F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.rainforest = Optional.of((new BiomeGenRainforest(BOPConfigurationIDs.rainforestID)).setColor(5470985).setBiomeName("Rainforest").func_76733_a(5470985).setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.7F, 1.8F));
		Biomes.redwoodForest = Optional.of((new BiomeGenRedwoodForest(BOPConfigurationIDs.redwoodForestID)).setColor(747097).setBiomeName("Redwood Forest").func_76733_a(5159473).setTemperatureRainfall(0.8F, 0.4F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.sacredSprings = Optional.of((new BiomeGenSacredSprings(BOPConfigurationIDs.sacredSpringsID)).setColor(522674).setBiomeName("Sacred Springs").func_76733_a(9154376).setMinMaxHeight(0.4F, 1.2F).setTemperatureRainfall(1.2F, 0.9F));
		Biomes.savanna = Optional.of((new BiomeGenSavanna(BOPConfigurationIDs.savannaID)).setColor(9286496).setBiomeName("Savanna").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.savannaPlateau = Optional.of((new BiomeGenSavannaPlateau(BOPConfigurationIDs.savannaPlateauID)).setColor(9286496).setBiomeName("Savanna Plateau").setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.9F, 0.9F));
		Biomes.scrubland = Optional.of((new BiomeGenScrubland(BOPConfigurationIDs.scrublandID)).setColor(9286496).setBiomeName("Scrubland").setTemperatureRainfall(1.2F, 0.05F).setMinMaxHeight(0.3F, 0.5F));
		Biomes.seasonalForest = Optional.of((new BiomeGenSeasonalForest(BOPConfigurationIDs.seasonalForestID)).setColor(353825).setBiomeName("Seasonal Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.seasonalSpruceForest = Optional.of((new BiomeGenSeasonalSpruceForest(BOPConfigurationIDs.seasonalSpruceForestID)).setColor(353825).setBiomeName("Seasonal Spruce Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.shield = Optional.of((new BiomeGenShield(BOPConfigurationIDs.shieldID)).setColor(522674).setBiomeName("Shield").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.5F, 0.8F));
		Biomes.shore = Optional.of((new BiomeGenShore(BOPConfigurationIDs.shoreID)).setColor(9286496).setBiomeName("Shore").setMinMaxHeight(-1.0F, 0.4F).setTemperatureRainfall(0.8F, 0.4F));
		Biomes.shrubland = Optional.of((new BiomeGenShrubland(BOPConfigurationIDs.shrublandID)).setColor(9286496).setBiomeName("Shrubland").setMinMaxHeight(0.2F, 0.2F).setTemperatureRainfall(0.6F, 0.05F));
		Biomes.shrublandForest = Optional.of((new BiomeGenShrublandForest(BOPConfigurationIDs.shrublandForestID)).setColor(9286496).setBiomeName("Thick Shrubland").setMinMaxHeight(0.2F, 0.2F).setTemperatureRainfall(0.6F, 0.05F));
		Biomes.silkglades = Optional.of((new BiomeGenSilkglades(BOPConfigurationIDs.silkgladesID)).setColor(522674).setBiomeName("Silkglades").func_76733_a(9154376).setMinMaxHeight(0.3F, 0.3F).setTemperatureRainfall(0.5F, 0.9F));
		Biomes.sludgepit = Optional.of((new BiomeGenSludgepit(BOPConfigurationIDs.sludgepitID)).setColor(522674).setBiomeName("Sludgepit").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.spruceWoods = Optional.of((new BiomeGenSpruceWoods(BOPConfigurationIDs.spruceWoodsID)).setColor(353825).setBiomeName("Spruce Woods").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.7F));
		Biomes.steppe = Optional.of((new BiomeGenSteppe(BOPConfigurationIDs.steppeID)).setColor(9286496).setBiomeName("Steppe").setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.temperateRainforest = Optional.of((new BiomeGenTemperateRainforest(BOPConfigurationIDs.temperateRainforestID)).setColor(353825).setBiomeName("Temperate Rainforest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.2F, 1.2F));
		Biomes.thicket = Optional.of((new BiomeGenThicket(BOPConfigurationIDs.thicketID)).setColor(353825).setBiomeName("Thicket").func_76733_a(5159473).setTemperatureRainfall(0.6F, 0.2F).setMinMaxHeight(0.2F, 0.2F));
		Biomes.timber = Optional.of((new BiomeGenTimber(BOPConfigurationIDs.timberID)).setColor(353825).setBiomeName("Timber").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.timberThin = Optional.of((new BiomeGenTimberThin(BOPConfigurationIDs.timberThinID)).setColor(353825).setBiomeName("Thinned Timber").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.tropicalRainforest = Optional.of((new BiomeGenTropicalRainforest(BOPConfigurationIDs.tropicalRainforestID)).setColor(9286496).setBiomeName("Tropical Rainforest").setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.3F, 0.7F));
		Biomes.tropics = Optional.of((new BiomeGenTropics(BOPConfigurationIDs.tropicsID)).setColor(9286496).setBiomeName("Tropics").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(0.1F, 1.5F));
		Biomes.tundra = Optional.of((new BiomeGenTundra(BOPConfigurationIDs.tundraID)).setColor(14090235).setBiomeName("Tundra").setTemperatureRainfall(0.2F, 0.8F).setMinMaxHeight(0.1F, 0.3F));
		Biomes.volcano = Optional.of((new BiomeGenVolcano(BOPConfigurationIDs.volcanoID)).setColor(9286496).setBiomeName("Volcano").setDisableRain().setMinMaxHeight(0.6F, 0.9F).setTemperatureRainfall(2.0F, 0.05F));
		Biomes.wasteland = Optional.of((new BiomeGenWasteland(BOPConfigurationIDs.wastelandID)).setColor(16421912).setBiomeName("Wasteland").setDisableRain().setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.3F, 0.4F));
		Biomes.wetland = Optional.of((new BiomeGenWetland(BOPConfigurationIDs.wetlandID)).setColor(522674).setBiomeName("Wetland").func_76733_a(9154376).setMinMaxHeight(0.3F, 0.5F).setTemperatureRainfall(0.8F, 0.9F));
		Biomes.woodland = Optional.of((new BiomeGenWoodland(BOPConfigurationIDs.woodlandID)).setColor(353825).setBiomeName("Woodland").func_76733_a(5159473).setTemperatureRainfall(1.7F, 0.2F).setMinMaxHeight(0.3F, 0.4F));

		Biomes.plainsNew = Optional.of((new BiomeGenPlainsNew(BOPConfigurationIDs.plainsNewID)).setColor(9286496).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F));
		
		Biomes.desertNew = Optional.of((new BiomeGenDesertNew(BOPConfigurationIDs.desertNewID)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.05F).setMinMaxHeight(0.2F, 0.3F));
	
		Biomes.extremeHillsNew = Optional.of((new BiomeGenHillsNew(BOPConfigurationIDs.extremeHillsNewID)).setColor(6316128).setBiomeName("Extreme Hills").setMinMaxHeight(1.0F, 2.0F).setTemperatureRainfall(0.2F, 0.3F));
		
		Biomes.forestNew = Optional.of((new BiomeGenForestNew(BOPConfigurationIDs.forestNewID)).setColor(353825).setBiomeName("Forest").func_76733_a(5159473).setTemperatureRainfall(0.7F, 0.8F));
		Biomes.forestHillsNew = Optional.of((new BiomeGenForestNew(BOPConfigurationIDs.forestHillsNewID)).setColor(353825).setBiomeName("ForestHills").func_76733_a(5159473).setMinMaxHeight(0.8F, 1.0F).setTemperatureRainfall(0.7F, 0.8F));
		
		Biomes.taigaNew = Optional.of((new BiomeGenTaigaNew(BOPConfigurationIDs.taigaNewID)).setColor(747097).setBiomeName("Taiga").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.3F, 0.5F));
		Biomes.taigaHillsNew = Optional.of((new BiomeGenTaigaNew(BOPConfigurationIDs.taigaHillsNewID)).setColor(747097).setBiomeName("TaigaHills").func_76733_a(5159473).setEnableSnow().setTemperatureRainfall(0.05F, 0.8F).setMinMaxHeight(0.8F, 1.0F));
		
		Biomes.swamplandNew = Optional.of((new BiomeGenSwampNew(BOPConfigurationIDs.swamplandNewID)).setColor(522674).setBiomeName("Swampland").func_76733_a(9154376).setMinMaxHeight(0.1F, 0.3F).setTemperatureRainfall(0.8F, 0.9F));
		
		Biomes.jungleNew = Optional.of((new BiomeGenJungleNew(BOPConfigurationIDs.jungleNewID)).setColor(5470985).setBiomeName("Jungle").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.4F, 0.5F));
		Biomes.jungleHillsNew = Optional.of((new BiomeGenJungleNew(BOPConfigurationIDs.jungleHillsNewID)).setColor(5470985).setBiomeName("JungleHills").func_76733_a(5470985).setTemperatureRainfall(1.2F, 0.9F).setMinMaxHeight(0.9F, 1.2F));
	}

	private static void addToBiomeDictionary()
	{
		BiomeDictionary.registerBiomeType(Biomes.alps.get(), Type.FROZEN, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.alpsBase.get(), Type.FROZEN, Type.MOUNTAIN, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.alpsForest.get(), Type.FROZEN, Type.MOUNTAIN);
		
		BiomeDictionary.registerBiomeType(Biomes.arctic.get(), Type.FROZEN, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.autumnHills.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.badlands.get(), Type.DESERT, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.bambooForest.get(), Type.JUNGLE, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.bayou.get(), Type.SWAMP, Type.WATER);

		BiomeDictionary.registerBiomeType(Biomes.beachGravel.get(), Type.BEACH);
		BiomeDictionary.registerBiomeType(Biomes.beachOvergrown.get(), Type.BEACH, Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.birchForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.bog.get(), Type.SWAMP, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.borealForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.brushland.get(), Type.DESERT, Type.FOREST, Type.PLAINS);
		
		BiomeDictionary.registerBiomeType(Biomes.canyon.get(), Type.DESERT, Type.MOUNTAIN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.canyonRavine.get(), Type.DESERT, Type.HILLS);
		
		BiomeDictionary.registerBiomeType(Biomes.chaparral.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.cherryBlossomGrove.get(), Type.MAGICAL, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.coniferousForest.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.coniferousForestSnow.get(), Type.FROZEN, Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.crag.get(), Type.WASTELAND, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.deadForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.deadForestSnow.get(), Type.FOREST, Type.FROZEN);
		BiomeDictionary.registerBiomeType(Biomes.deadlands.get(), Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.deadSwamp.get(), Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.deciduousForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.dunes.get(), Type.BEACH, Type.DESERT, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.fen.get(), Type.FOREST, Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.field.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.fieldForest.get(), Type.PLAINS, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.frostForest.get(), Type.FROZEN, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.fungiForest.get(), Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.garden.get(), Type.MAGICAL, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.glacier.get(), Type.FROZEN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.grassland.get(), Type.PLAINS, Type.SWAMP, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.grove.get(), Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.heathland.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.highland.get(), Type.HILLS, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.hotSprings.get(), Type.HILLS, Type.FOREST, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.icyHills.get(), Type.FROZEN, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.jadeCliffs.get(), Type.FOREST, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.lushDesert.get(), Type.DESERT, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.lushSwamp.get(), Type.SWAMP, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.mangrove.get(), Type.WATER, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.mapleWoods.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.marsh.get(), Type.SWAMP, Type.WATER);
		
		BiomeDictionary.registerBiomeType(Biomes.meadow.get(), Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.meadowForest.get(), Type.FOREST, Type.PLAINS);
		
		BiomeDictionary.registerBiomeType(Biomes.mesa.get(), Type.DESERT, Type.WASTELAND, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.moor.get(), Type.HILLS, Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.mountain.get(), Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.mysticGrove.get(), Type.MAGICAL, Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.netherBase.get(), Type.NETHER);
		BiomeDictionary.registerBiomeType(Biomes.netherGarden.get(), Type.NETHER, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.netherDesert.get(), Type.NETHER, Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.netherLava.get(), Type.NETHER);
		BiomeDictionary.registerBiomeType(Biomes.netherBone.get(), Type.NETHER, Type.WASTELAND);

		BiomeDictionary.registerBiomeType(Biomes.oasis.get(), Type.DESERT, Type.JUNGLE);

		BiomeDictionary.registerBiomeType(Biomes.oceanAbyss.get(), Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.oceanCoral.get(), Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.oceanKelp.get(), Type.WATER, Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.ominousWoods.get(), Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.ominousWoodsThick.get(), Type.MAGICAL);

		BiomeDictionary.registerBiomeType(Biomes.orchard.get(), Type.FOREST, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.outback.get(), Type.DESERT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.overgrownGreens.get(), Type.JUNGLE, Type.PLAINS);
		
		BiomeDictionary.registerBiomeType(Biomes.pasture.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.pastureThin.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.pastureMeadow.get(), Type.PLAINS, Type.FOREST);
		
		BiomeDictionary.registerBiomeType(Biomes.polar.get(), Type.FROZEN, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.prairie.get(), Type.PLAINS);

		BiomeDictionary.registerBiomeType(Biomes.promisedLandForest.get(), Type.FOREST, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.promisedLandPlains.get(), Type.PLAINS, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.promisedLandSwamp.get(), Type.SWAMP, Type.MAGICAL);

		BiomeDictionary.registerBiomeType(Biomes.quagmire.get(), Type.WASTELAND, Type.SWAMP);
		BiomeDictionary.registerBiomeType(Biomes.rainforest.get(), Type.JUNGLE, Type.HILLS, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.redwoodForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.sacredSprings.get(), Type.MOUNTAIN, Type.FOREST, Type.MAGICAL);
		BiomeDictionary.registerBiomeType(Biomes.savanna.get(), Type.DESERT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.savannaPlateau.get(), Type.DESERT, Type.PLAINS, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.scrubland.get(), Type.DESERT, Type.PLAINS);
		
		BiomeDictionary.registerBiomeType(Biomes.seasonalForest.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.seasonalSpruceForest.get(), Type.FOREST);
		
		BiomeDictionary.registerBiomeType(Biomes.shield.get(), Type.FOREST, Type.WATER);
		
		BiomeDictionary.registerBiomeType(Biomes.shrubland.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.shrublandForest.get(), Type.PLAINS);
		
		BiomeDictionary.registerBiomeType(Biomes.silkglades.get(), Type.SWAMP, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.sludgepit.get(), Type.SWAMP, Type.FOREST, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.spruceWoods.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.steppe.get(), Type.PLAINS, Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.temperateRainforest.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.thicket.get(), Type.PLAINS, Type.FOREST);
		
		BiomeDictionary.registerBiomeType(Biomes.timber.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.timberThin.get(), Type.FOREST);
		
		BiomeDictionary.registerBiomeType(Biomes.tropicalRainforest.get(), Type.JUNGLE);
		BiomeDictionary.registerBiomeType(Biomes.tropics.get(), Type.JUNGLE, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.tundra.get(), Type.FROZEN, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.volcano.get(), Type.WASTELAND, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.wasteland.get(), Type.WASTELAND);
		BiomeDictionary.registerBiomeType(Biomes.wetland.get(), Type.SWAMP, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.woodland.get(), Type.FOREST);

		BiomeDictionary.registerBiomeType(Biomes.plainsNew.get(), Type.PLAINS);
		BiomeDictionary.registerBiomeType(Biomes.desertNew.get(), Type.DESERT);
		BiomeDictionary.registerBiomeType(Biomes.forestNew.get(), Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.forestHillsNew.get(), Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.extremeHillsNew.get(), Type.HILLS, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(Biomes.taigaNew.get(), Type.FROZEN, Type.FOREST);
		BiomeDictionary.registerBiomeType(Biomes.taigaHillsNew.get(), Type.FROZEN, Type.FOREST, Type.HILLS);
		BiomeDictionary.registerBiomeType(Biomes.swamplandNew.get(), Type.SWAMP, Type.WATER);
		BiomeDictionary.registerBiomeType(Biomes.jungleNew.get(), Type.JUNGLE);
		BiomeDictionary.registerBiomeType(Biomes.jungleHillsNew.get(), Type.JUNGLE, Type.HILLS);
	}

	private static void addSpawnBiomes()
	{
		addSpawnBiome(Biomes.alps);
		addSpawnBiome(Biomes.arctic);
		addSpawnBiome(Biomes.autumnHills);
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
		addVillageBiome(Biomes.arctic, BOPConfigurationTerrainGen.arcticVillage);
		addVillageBiome(Biomes.birchForest, BOPConfigurationTerrainGen.birchForestVillage);
		addVillageBiome(Biomes.brushland, BOPConfigurationTerrainGen.brushlandVillage);
		addVillageBiome(Biomes.chaparral, BOPConfigurationTerrainGen.chaparralVillage);
		addVillageBiome(Biomes.coniferousForest, BOPConfigurationTerrainGen.coniferousForestVillage);
		addVillageBiome(Biomes.coniferousForestSnow, BOPConfigurationTerrainGen.coniferousForestSnowVillage);
		addVillageBiome(Biomes.deciduousForest, BOPConfigurationTerrainGen.deciduousForestVillage);
		addVillageBiome(Biomes.frostForest, BOPConfigurationTerrainGen.frostForestVillage);
		addVillageBiome(Biomes.field, BOPConfigurationTerrainGen.fieldVillage);
		addVillageBiome(Biomes.grassland, BOPConfigurationTerrainGen.grasslandVillage);
		addVillageBiome(Biomes.grove, BOPConfigurationTerrainGen.groveVillage);
		addVillageBiome(Biomes.heathland, BOPConfigurationTerrainGen.heathlandVillage);
		addVillageBiome(Biomes.lushDesert, BOPConfigurationTerrainGen.lushDesertVillage);
		addVillageBiome(Biomes.lushSwamp, BOPConfigurationTerrainGen.lushSwampVillage);
		addVillageBiome(Biomes.mapleWoods, BOPConfigurationTerrainGen.mapleWoodsVillage);
		addVillageBiome(Biomes.meadow, BOPConfigurationTerrainGen.meadowVillage);
		addVillageBiome(Biomes.meadowForest, BOPConfigurationTerrainGen.meadowVillage);
		addVillageBiome(Biomes.outback, BOPConfigurationTerrainGen.outbackVillage);
		addVillageBiome(Biomes.overgrownGreens, BOPConfigurationTerrainGen.overgrownGreensVillage);
		addVillageBiome(Biomes.prairie, BOPConfigurationTerrainGen.prairieVillage);
		addVillageBiome(Biomes.savanna, BOPConfigurationTerrainGen.savannaVillage);
		addVillageBiome(Biomes.scrubland, BOPConfigurationTerrainGen.scrublandVillage);
		addVillageBiome(Biomes.shrubland, BOPConfigurationTerrainGen.shrublandVillage);
		addVillageBiome(Biomes.spruceWoods, BOPConfigurationTerrainGen.spruceWoodsVillage);
		addVillageBiome(Biomes.steppe, BOPConfigurationTerrainGen.steppeVillage);
		addVillageBiome(Biomes.timber, BOPConfigurationTerrainGen.timberVillage);
		addVillageBiome(Biomes.tropicalRainforest, BOPConfigurationTerrainGen.tropicalRainforestVillage);
		addVillageBiome(Biomes.tundra, BOPConfigurationTerrainGen.tundraVillage);
		addVillageBiome(Biomes.wetland, BOPConfigurationTerrainGen.wetlandVillage);
		addVillageBiome(Biomes.woodland, BOPConfigurationTerrainGen.woodlandVillage);

		addVillageBiome(Biomes.plainsNew, BOPConfigurationTerrainGen.plainsVillage);
		addVillageBiome(Biomes.desertNew, BOPConfigurationTerrainGen.desertVillage);
		addVillageBiome(Biomes.forestNew, BOPConfigurationTerrainGen.forestVillage);
	}

	private static void addStrongholdBiomes()
	{
		addStrongholdBiome(Biomes.alps);
		addStrongholdBiome(Biomes.arctic);
		addStrongholdBiome(Biomes.autumnHills);
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
		addStrongholdBiome(Biomes.overgrownGreens);
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
		addStrongholdBiome(Biomes.silkglades);
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
		if (BOPConfigurationTerrainGen.addToDefault)
		{
			if (BOPConfigurationBiomeGen.alpsGen) {
				registerBiome(Biomes.alps);
			}

			if (BOPConfigurationBiomeGen.arcticGen) {
				registerBiome(Biomes.arctic);
			}
			
			if (BOPConfigurationBiomeGen.autumnHillsGen) {
				registerBiome(Biomes.autumnHills);
			}

			if (BOPConfigurationBiomeGen.badlandsGen) {
				registerBiome(Biomes.badlands);
			}

			if (BOPConfigurationBiomeGen.bambooForestGen) {
				registerBiome(Biomes.bambooForest);
			}

			if (BOPConfigurationBiomeGen.bayouGen) {
				registerBiome(Biomes.bayou);
			}

			if (BOPConfigurationBiomeGen.birchForestGen) {
				registerBiome(Biomes.birchForest);
			}

			if (BOPConfigurationBiomeGen.bogGen) {
				registerBiome(Biomes.bog);
			}

			if (BOPConfigurationBiomeGen.borealForestGen) {
				registerBiome(Biomes.borealForest);
			}

			if (BOPConfigurationBiomeGen.brushlandGen) {
				registerBiome(Biomes.brushland);
			}

			if (BOPConfigurationBiomeGen.canyonGen) {
				registerBiome(Biomes.canyon);
			}

			if (BOPConfigurationBiomeGen.chaparralGen) {
				registerBiome(Biomes.chaparral);
			}

			if (BOPConfigurationBiomeGen.cherryBlossomGroveGen) {
				registerBiome(Biomes.cherryBlossomGrove);
			}

			if (BOPConfigurationBiomeGen.coniferousForestGen) {
				registerBiome(Biomes.coniferousForest);
			}

			if (BOPConfigurationBiomeGen.coniferousForestSnowGen) {
				registerBiome(Biomes.coniferousForestSnow);
			}

			if (BOPConfigurationBiomeGen.cragGen) {
				registerBiome(Biomes.crag);
			}

			if (BOPConfigurationBiomeGen.deadForestGen) {
				registerBiome(Biomes.deadForest);
			}

			if (BOPConfigurationBiomeGen.deadForestSnowGen) {
				registerBiome(Biomes.deadForestSnow);
			}

			if (BOPConfigurationBiomeGen.deadSwampGen) {
				registerBiome(Biomes.deadSwamp);
			}

			if (BOPConfigurationBiomeGen.deadlandsGen) {
				registerBiome(Biomes.deadlands);
			}

			if (BOPConfigurationBiomeGen.deciduousForestGen) {
				registerBiome(Biomes.deciduousForest);
			}

			if (BOPConfigurationBiomeGen.dunesGen) {
				registerBiome(Biomes.dunes);
			}

			if (BOPConfigurationBiomeGen.fenGen) {
				registerBiome(Biomes.fen);
			}

			if (BOPConfigurationBiomeGen.fieldGen) {
				registerBiome(Biomes.field);
			}

			if (BOPConfigurationBiomeGen.frostForestGen) {
				registerBiome(Biomes.frostForest);
			}

			if (BOPConfigurationBiomeGen.fungiForestGen) {
				registerBiome(Biomes.fungiForest);
			}

			if (BOPConfigurationBiomeGen.gardenGen) {
				registerBiome(Biomes.garden);
			}

			if (BOPConfigurationBiomeGen.glacierGen) {
				registerBiome(Biomes.glacier);
			}

			if (BOPConfigurationBiomeGen.grasslandGen) {
				registerBiome(Biomes.grassland);
			}

			if (BOPConfigurationBiomeGen.groveGen) {
				registerBiome(Biomes.grove);
			}

			if (BOPConfigurationBiomeGen.heathlandGen) {
				registerBiome(Biomes.heathland);
			}

			if (BOPConfigurationBiomeGen.highlandGen) {
				registerBiome(Biomes.highland);
			}

			if (BOPConfigurationBiomeGen.hotSpringsGen) {
				registerBiome(Biomes.hotSprings);
			}

			if (BOPConfigurationBiomeGen.icyHillsGen) {
				registerBiome(Biomes.icyHills);
			}

			if (BOPConfigurationBiomeGen.jadeCliffsGen) {
				registerBiome(Biomes.jadeCliffs);
			}

			if (BOPConfigurationBiomeGen.lushDesertGen) {
				registerBiome(Biomes.lushDesert);
			}

			if (BOPConfigurationBiomeGen.lushSwampGen) {
				registerBiome(Biomes.lushSwamp);
			}

			if (BOPConfigurationBiomeGen.mangroveGen) {
				registerBiome(Biomes.mangrove);
			}

			if (BOPConfigurationBiomeGen.mapleWoodsGen) {
				registerBiome(Biomes.mapleWoods);
			}

			if (BOPConfigurationBiomeGen.marshGen) {
				registerBiome(Biomes.marsh);
			}

			if (BOPConfigurationBiomeGen.meadowGen) {
				registerBiome(Biomes.meadow);
			}

			if (BOPConfigurationBiomeGen.mesaGen) {
				registerBiome(Biomes.mesa);
			}

			if (BOPConfigurationBiomeGen.moorGen) {
				registerBiome(Biomes.moor);
			}

			if (BOPConfigurationBiomeGen.mountainGen) {
				registerBiome(Biomes.mountain);
			}

			//            if (BOPConfiguration.BiomeGen.mushroomIslandGen)
				//                GameRegistry.addBiome(BiomeGenBase.mushroomIsland);

			if (BOPConfigurationBiomeGen.mysticGroveGen) {
				registerBiome(Biomes.mysticGrove);
			}

			if (BOPConfigurationBiomeGen.oasisGen) {
				registerBiome(Biomes.oasis);
			}

			if (BOPConfigurationBiomeGen.ominousWoodsGen) {
				registerBiome(Biomes.ominousWoods);
			}

			if (BOPConfigurationBiomeGen.orchardGen) {
				registerBiome(Biomes.orchard);
			}

			if (BOPConfigurationBiomeGen.originValleyGen) {
				registerBiome(Biomes.originValley);
			}

			if (BOPConfigurationBiomeGen.outbackGen) {
				registerBiome(Biomes.outback);
			}
			
			if (BOPConfigurationBiomeGen.overgrownGreensGen) {
				registerBiome(Biomes.overgrownGreens);
			}

			if (BOPConfigurationBiomeGen.pastureGen) {
				registerBiome(Biomes.pasture);
			}

			if (BOPConfigurationBiomeGen.polarGen) {
				registerBiome(Biomes.polar);
			}

			if (BOPConfigurationBiomeGen.prairieGen) {
				registerBiome(Biomes.prairie);
			}

			if (BOPConfigurationBiomeGen.quagmireGen) {
				registerBiome(Biomes.quagmire);
			}

			if (BOPConfigurationBiomeGen.rainforestGen) {
				registerBiome(Biomes.rainforest);
			}

			if (BOPConfigurationBiomeGen.redwoodForestGen) {
				registerBiome(Biomes.redwoodForest);
			}

			if (BOPConfigurationBiomeGen.sacredSpringsGen) {
				registerBiome(Biomes.sacredSprings);
			}

			if (BOPConfigurationBiomeGen.savannaGen) {
				registerBiome(Biomes.savanna);
			}

			if (BOPConfigurationBiomeGen.scrublandGen) {
				registerBiome(Biomes.scrubland);
			}

			if (BOPConfigurationBiomeGen.seasonalForestGen) {
				registerBiome(Biomes.seasonalForest);
			}

			if (BOPConfigurationBiomeGen.shieldGen) {
				registerBiome(Biomes.shield);
			}

			if (BOPConfigurationBiomeGen.shrublandGen) {
				registerBiome(Biomes.shrubland);
			}
			
			if (BOPConfigurationBiomeGen.silkgladesGen) {
				registerBiome(Biomes.silkglades);
			}

			if (BOPConfigurationBiomeGen.sludgepitGen) {
				registerBiome(Biomes.sludgepit);
			}

			if (BOPConfigurationBiomeGen.spruceWoodsGen) {
				registerBiome(Biomes.spruceWoods);
			}

			if (BOPConfigurationBiomeGen.steppeGen) {
				registerBiome(Biomes.steppe);
			}

			if (BOPConfigurationBiomeGen.temperateRainforestGen) {
				registerBiome(Biomes.temperateRainforest);
			}

			if (BOPConfigurationBiomeGen.thicketGen) {
				registerBiome(Biomes.thicket);
			}

			if (BOPConfigurationBiomeGen.timberGen) {
				registerBiome(Biomes.timber);
			}

			if (BOPConfigurationBiomeGen.tropicalRainforestGen) {
				registerBiome(Biomes.tropicalRainforest);
			}

			if (BOPConfigurationBiomeGen.tropicsGen) {
				registerBiome(Biomes.tropics);
			}

			if (BOPConfigurationBiomeGen.tundraGen) {
				registerBiome(Biomes.tundra);
			}

			if (BOPConfigurationBiomeGen.volcanoGen) {
				registerBiome(Biomes.volcano);
			}

			if (BOPConfigurationBiomeGen.wastelandGen) {
				registerBiome(Biomes.wasteland);
			}

			if (BOPConfigurationBiomeGen.wetlandGen) {
				registerBiome(Biomes.wetland);
			}

			if (BOPConfigurationBiomeGen.woodlandGen) {
				registerBiome(Biomes.woodland);
			}

			// Vanilla biomes generation
			if (BOPConfigurationBiomeGen.plainsGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
				{
					registerBiome(Biomes.plainsNew);
					GameRegistry.removeBiome(BiomeGenBase.plains);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.plains);
			}

			if (BOPConfigurationBiomeGen.desertGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
				{
					registerBiome(Biomes.desertNew);
					GameRegistry.removeBiome(BiomeGenBase.desert);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.desert);
			}

			if (BOPConfigurationBiomeGen.extremeHillsGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
				{
					registerBiome(Biomes.extremeHillsNew);
					GameRegistry.removeBiome(BiomeGenBase.extremeHills);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.extremeHills);
			}

			if (BOPConfigurationBiomeGen.forestGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
				{
					registerBiome(Biomes.forestNew);
					GameRegistry.removeBiome(BiomeGenBase.forest);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.forest);
			}

			if (BOPConfigurationBiomeGen.taigaGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
				{
					registerBiome(Biomes.taigaNew);
					GameRegistry.removeBiome(BiomeGenBase.taiga);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.taiga);
			}

			if (BOPConfigurationBiomeGen.swamplandGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
				{
					registerBiome(Biomes.swamplandNew);
					GameRegistry.removeBiome(BiomeGenBase.swampland);
				}
			} else {
				GameRegistry.removeBiome(BiomeGenBase.swampland);
			}

			if (BOPConfigurationBiomeGen.jungleGen)
			{
				if (BOPConfigurationTerrainGen.vanillaEnhanced)
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
		if (BOPConfigurationBiomeGen.alpsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.alps);
		}

		if (BOPConfigurationBiomeGen.arcticGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.arctic);
		}
		
		if (BOPConfigurationBiomeGen.autumnHillsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.autumnHills);
		}

		if (BOPConfigurationBiomeGen.badlandsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.badlands);
		}

		if (BOPConfigurationBiomeGen.bambooForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.bambooForest);
		}

		if (BOPConfigurationBiomeGen.bayouGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.bayou);
		}

		if (BOPConfigurationBiomeGen.birchForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.birchForest);
		}

		if (BOPConfigurationBiomeGen.bogGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.bog);
		}

		if (BOPConfigurationBiomeGen.borealForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.borealForest);
		}

		if (BOPConfigurationBiomeGen.brushlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.brushland);
		}

		if (BOPConfigurationBiomeGen.canyonGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.canyon);
		}

		if (BOPConfigurationBiomeGen.chaparralGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.chaparral);
		}

		if (BOPConfigurationBiomeGen.cherryBlossomGroveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.cherryBlossomGrove);
		}

		if (BOPConfigurationBiomeGen.coniferousForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.coniferousForest);
		}

		if (BOPConfigurationBiomeGen.coniferousForestSnowGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.coniferousForestSnow);
		}

		if (BOPConfigurationBiomeGen.cragGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.crag);
		}

		if (BOPConfigurationBiomeGen.deadForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadForest);
		}

		if (BOPConfigurationBiomeGen.deadForestSnowGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadForestSnow);
		}

		if (BOPConfigurationBiomeGen.deadSwampGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadSwamp);
		}

		if (BOPConfigurationBiomeGen.deadlandsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deadlands);
		}

		if (BOPConfigurationBiomeGen.deciduousForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.deciduousForest);
		}

		if (BOPConfigurationBiomeGen.dunesGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.dunes);
		}

		if (BOPConfigurationBiomeGen.fenGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.fen);
		}

		if (BOPConfigurationBiomeGen.fieldGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.field);
		}

		if (BOPConfigurationBiomeGen.frostForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.frostForest);
		}

		if (BOPConfigurationBiomeGen.fungiForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.fungiForest);
		}

		if (BOPConfigurationBiomeGen.gardenGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.garden);
		}

		if (BOPConfigurationBiomeGen.glacierGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.glacier);
		}

		if (BOPConfigurationBiomeGen.grasslandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.grassland);
		}

		if (BOPConfigurationBiomeGen.groveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.grove);
		}

		if (BOPConfigurationBiomeGen.heathlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.heathland);
		}

		if (BOPConfigurationBiomeGen.highlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.highland);
		}

		if (BOPConfigurationBiomeGen.hotSpringsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.hotSprings);
		}

		if (BOPConfigurationBiomeGen.icyHillsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.icyHills);
		}

		if (BOPConfigurationBiomeGen.jadeCliffsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.jadeCliffs);
		}

		if (BOPConfigurationBiomeGen.lushDesertGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.lushDesert);
		}

		if (BOPConfigurationBiomeGen.lushSwampGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.lushSwamp);
		}

		if (BOPConfigurationBiomeGen.mangroveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mangrove);
		}

		if (BOPConfigurationBiomeGen.mapleWoodsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mapleWoods);
		}

		if (BOPConfigurationBiomeGen.marshGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.marsh);
		}

		if (BOPConfigurationBiomeGen.meadowGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.meadow);
		}

		if (BOPConfigurationBiomeGen.mesaGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mesa);
		}

		if (BOPConfigurationBiomeGen.moorGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.moor);
		}

		if (BOPConfigurationBiomeGen.mountainGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mountain);
		}

		if (BOPConfigurationBiomeGen.mysticGroveGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.mysticGrove);
		}

		if (BOPConfigurationBiomeGen.oasisGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.oasis);
		}

		if (BOPConfigurationBiomeGen.ominousWoodsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.ominousWoods);
		}

		if (BOPConfigurationBiomeGen.orchardGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.orchard);
		}

		if (BOPConfigurationBiomeGen.originValleyGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.originValley);
		}

		if (BOPConfigurationBiomeGen.outbackGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.outback);
		}
		
		if (BOPConfigurationBiomeGen.overgrownGreensGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.overgrownGreens);
		}

		if (BOPConfigurationBiomeGen.pastureGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.pasture);
		}

		if (BOPConfigurationBiomeGen.polarGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.polar);
		}

		if (BOPConfigurationBiomeGen.prairieGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.prairie);
		}

		if (BOPConfigurationBiomeGen.quagmireGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.quagmire);
		}

		if (BOPConfigurationBiomeGen.rainforestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.rainforest);
		}

		if (BOPConfigurationBiomeGen.redwoodForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.redwoodForest);
		}

		if (BOPConfigurationBiomeGen.sacredSpringsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.sacredSprings);
		}

		if (BOPConfigurationBiomeGen.savannaGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.savanna);
		}

		if (BOPConfigurationBiomeGen.scrublandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.scrubland);
		}

		if (BOPConfigurationBiomeGen.seasonalForestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.seasonalForest);
		}

		if (BOPConfigurationBiomeGen.shieldGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.shield);
		}

		if (BOPConfigurationBiomeGen.shrublandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.shrubland);
		}
		
		if (BOPConfigurationBiomeGen.silkgladesGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.silkglades);
		}

		if (BOPConfigurationBiomeGen.sludgepitGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.sludgepit);
		}

		if (BOPConfigurationBiomeGen.spruceWoodsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.spruceWoods);
		}

		if (BOPConfigurationBiomeGen.steppeGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.steppe);
		}

		if (BOPConfigurationBiomeGen.temperateRainforestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.temperateRainforest);
		}

		if (BOPConfigurationBiomeGen.thicketGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.thicket);
		}

		if (BOPConfigurationBiomeGen.timberGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.timber);
		}

		if (BOPConfigurationBiomeGen.tropicalRainforestGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.tropicalRainforest);
		}

		if (BOPConfigurationBiomeGen.tropicsGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.tropics);
		}

		if (BOPConfigurationBiomeGen.tundraGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.tundra);
		}

		if (BOPConfigurationBiomeGen.volcanoGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.volcano);
		}

		if (BOPConfigurationBiomeGen.wastelandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.wasteland);
		}

		if (BOPConfigurationBiomeGen.wetlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.wetland);
		}

		if (BOPConfigurationBiomeGen.woodlandGen) {
			addBiomeToWorldTypes(getWorldTypes(), Biomes.woodland);
		}

		// Vanilla biomes generation
		if (BOPConfigurationBiomeGen.plainsGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.plainsNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.plains);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.plains);
		}

		if (BOPConfigurationBiomeGen.desertGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.desertNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.desert);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.desert);
		}

		if (BOPConfigurationBiomeGen.extremeHillsGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.extremeHillsNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.extremeHills);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.extremeHills);
		}

		if (BOPConfigurationBiomeGen.forestGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.forestNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.forest);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.forest);
		}

		if (BOPConfigurationBiomeGen.taigaGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.taigaNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.taiga);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.taiga);
		}

		if (BOPConfigurationBiomeGen.swamplandGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
			{
				addBiomeToWorldTypes(getWorldTypes(), Biomes.swamplandNew);
				removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.swampland);
			}
		} else {
			removeBiomeFromWorldTypes(getWorldTypes(), BiomeGenBase.swampland);
		}

		if (BOPConfigurationBiomeGen.jungleGen)
		{
			if (BOPConfigurationTerrainGen.vanillaEnhanced)
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
			if (BOPConfigurationTerrainGen.addToDefault)
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
