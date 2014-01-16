package biomesoplenty.common.core;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeListEntry;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeTemperatureType;
import biomesoplenty.common.biomes.BiomeGenAlps;
import biomesoplenty.common.biomes.BiomeGenArctic;
import biomesoplenty.common.biomes.BiomeGenBambooForest;
import biomesoplenty.common.biomes.BiomeGenBayou;
import biomesoplenty.common.biomes.BiomeGenBog;
import biomesoplenty.common.biomes.BiomeGenBorealForest;
import biomesoplenty.common.biomes.BiomeGenBrushland;
import biomesoplenty.common.biomes.BiomeGenCanyon;
import biomesoplenty.common.biomes.BiomeGenChaparral;
import biomesoplenty.common.biomes.BiomeGenCherryBlossomGrove;
import biomesoplenty.common.biomes.BiomeGenConiferousForest;
import biomesoplenty.common.biomes.BiomeGenConiferousForestSnow;
import biomesoplenty.common.biomes.BiomeGenCrag;
import biomesoplenty.common.biomes.BiomeGenDeadForest;
import biomesoplenty.common.biomes.BiomeGenDeadSwamp;
import biomesoplenty.common.biomes.BiomeGenDeciduousForest;
import biomesoplenty.common.biomes.BiomeGenDunes;
import biomesoplenty.common.biomes.BiomeGenFen;
import biomesoplenty.common.biomes.BiomeGenFlowerField;
import biomesoplenty.common.biomes.BiomeGenFrostForest;
import biomesoplenty.common.biomes.BiomeGenGrassland;
import biomesoplenty.common.biomes.BiomeGenGrove;
import biomesoplenty.common.biomes.BiomeGenHeathland;
import biomesoplenty.common.biomes.BiomeGenHighland;
import biomesoplenty.common.biomes.BiomeGenJadeCliffs;
import biomesoplenty.common.biomes.BiomeGenLavenderFields;
import biomesoplenty.common.biomes.BiomeGenLushDesert;
import biomesoplenty.common.biomes.BiomeGenLushSwamp;
import biomesoplenty.common.biomes.BiomeGenMapleWoods;
import biomesoplenty.common.biomes.BiomeGenMarsh;
import biomesoplenty.common.biomes.BiomeGenMeadow;
import biomesoplenty.common.biomes.BiomeGenMoor;
import biomesoplenty.common.biomes.BiomeGenMountain;
import biomesoplenty.common.biomes.BiomeGenMysticGrove;
import biomesoplenty.common.biomes.BiomeGenOminousWoods;
import biomesoplenty.common.biomes.BiomeGenOriginValley;
import biomesoplenty.common.biomes.BiomeGenOutback;
import biomesoplenty.common.biomes.BiomeGenPasture;
import biomesoplenty.common.biomes.BiomeGenPrairie;
import biomesoplenty.common.biomes.BiomeGenQuagmire;
import biomesoplenty.common.biomes.BiomeGenRainforest;
import biomesoplenty.common.biomes.BiomeGenRedwoodForest;
import biomesoplenty.common.biomes.BiomeGenSacredSprings;
import biomesoplenty.common.biomes.BiomeGenSeasonalForest;
import biomesoplenty.common.biomes.BiomeGenShield;
import biomesoplenty.common.biomes.BiomeGenShrubland;
import biomesoplenty.common.biomes.BiomeGenSilkglades;
import biomesoplenty.common.biomes.BiomeGenSludgepit;
import biomesoplenty.common.biomes.BiomeGenSpruceWoods;
import biomesoplenty.common.biomes.BiomeGenTemperateRainforest;
import biomesoplenty.common.biomes.BiomeGenThicket;
import biomesoplenty.common.biomes.BiomeGenTimber;
import biomesoplenty.common.biomes.BiomeGenTropicalRainforest;
import biomesoplenty.common.biomes.BiomeGenTropics;
import biomesoplenty.common.biomes.BiomeGenTundra;
import biomesoplenty.common.biomes.BiomeGenVolcano;
import biomesoplenty.common.biomes.BiomeGenWasteland;
import biomesoplenty.common.biomes.BiomeGenWetland;
import biomesoplenty.common.biomes.BiomeGenWoodland;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
import biomesoplenty.common.world.WorldTypeBOP;

public class BOPBiomes 
{
	public static WorldTypeBOP worldTypeBOP;
	
	private static BiomeGenBase onlyBiome;
	
	public static void init()
	{
		registerBiomes();
		useOnlyBiome();
		addSpawnBiomes();
		//addStrongholdBiomes();
	}
	
	private static void registerBiomes()
	{
		addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.desert, BOPBiomeTemperatureType.HOT));
		addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.desert, BOPBiomeTemperatureType.HOT)); 
		addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.desert, BOPBiomeTemperatureType.HOT)); 
		addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.field_150588_X, BOPBiomeTemperatureType.HOT));
		addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.field_150588_X, BOPBiomeTemperatureType.HOT));
		addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.plains, BOPBiomeTemperatureType.HOT));
		
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.forest, BOPBiomeTemperatureType.WARM));
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.field_150585_R, BOPBiomeTemperatureType.WARM));
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.extremeHills, BOPBiomeTemperatureType.WARM));
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.plains, BOPBiomeTemperatureType.WARM));
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.field_150583_P, BOPBiomeTemperatureType.WARM)); 
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.swampland, BOPBiomeTemperatureType.WARM));
        
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.forest, BOPBiomeTemperatureType.COOL)); 
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.extremeHills, BOPBiomeTemperatureType.COOL));  
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.taiga, BOPBiomeTemperatureType.COOL)); 
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.plains, BOPBiomeTemperatureType.COOL));

        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.icePlains, BOPBiomeTemperatureType.ICY));  
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.icePlains, BOPBiomeTemperatureType.ICY)); 
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.icePlains, BOPBiomeTemperatureType.ICY));  
        addBiomeToList(new BOPBiomeListEntry(BiomeGenBase.field_150584_S, BOPBiomeTemperatureType.ICY));
        
        registerBiome(new BOPBiomeListEntry(new BiomeGenAlps(BOPConfigurationIDs.alpsID).setBiomeName("Alps"), BOPBiomeTemperatureType.ICY)); 
        registerBiome(new BOPBiomeListEntry(new BiomeGenArctic(BOPConfigurationIDs.arcticID).setBiomeName("Arctic"), BOPBiomeTemperatureType.ICY));
        registerBiome(new BOPBiomeListEntry(new BiomeGenBambooForest(BOPConfigurationIDs.bambooForestID).setBiomeName("Bamboo Forest"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenBayou(BOPConfigurationIDs.bayouID).setBiomeName("Bayou"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenBog(BOPConfigurationIDs.bogID).setBiomeName("Bog"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenBorealForest(BOPConfigurationIDs.borealForestID).setBiomeName("Boreal Forest"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenBrushland(BOPConfigurationIDs.brushlandID).setBiomeName("Brushland"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenCanyon(BOPConfigurationIDs.canyonID).setBiomeName("Canyon"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenChaparral(BOPConfigurationIDs.chaparralID).setBiomeName("Chaparral"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenCherryBlossomGrove(BOPConfigurationIDs.cherryBlossomGroveID).setBiomeName("Cherry Blossom Grove"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenConiferousForest(BOPConfigurationIDs.coniferousForestID).setBiomeName("Coniferous Forest"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenConiferousForestSnow(BOPConfigurationIDs.coniferousForestSnowID).setBiomeName("Snowy Coniferous Forest"), BOPBiomeTemperatureType.ICY));
        registerBiome(new BOPBiomeListEntry(new BiomeGenCrag(BOPConfigurationIDs.cragID).setBiomeName("Crag"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenDeadForest(BOPConfigurationIDs.deadForestID).setBiomeName("Dead Forest"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenDeadSwamp(BOPConfigurationIDs.deadSwampID).setBiomeName("Dead Swamp"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenDeciduousForest(BOPConfigurationIDs.deciduousForestID).setBiomeName("Deciduous Forest"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenDunes(BOPConfigurationIDs.dunesID).setBiomeName("Dunes"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenFen(BOPConfigurationIDs.fenID).setBiomeName("Fen"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenFlowerField(BOPConfigurationIDs.flowerFieldID).setBiomeName("Flower Field"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenFrostForest(BOPConfigurationIDs.frostForestID).setBiomeName("Frost Forest"), BOPBiomeTemperatureType.ICY));
        registerBiome(new BOPBiomeListEntry(new BiomeGenGrassland(BOPConfigurationIDs.grasslandID).setBiomeName("Grassland"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenGrove(BOPConfigurationIDs.groveID).setBiomeName("Grove"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenHeathland(BOPConfigurationIDs.heathlandID).setBiomeName("Heathland"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenHighland(BOPConfigurationIDs.highlandID).setBiomeName("Highland"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenJadeCliffs(BOPConfigurationIDs.jadeCliffsID).setBiomeName("Jade Cliffs"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenLavenderFields(BOPConfigurationIDs.lavenderFieldsID).setBiomeName("Lavender Fields"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenLushDesert(BOPConfigurationIDs.lushDesertID).setBiomeName("Lush Desert"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenLushSwamp(BOPConfigurationIDs.lushSwampID).setBiomeName("Lush Swamp"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenMapleWoods(BOPConfigurationIDs.mapleWoodsID).setBiomeName("Maple Woods"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenMarsh(BOPConfigurationIDs.marshID).setBiomeName("Marsh"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenMeadow(BOPConfigurationIDs.meadowID).setBiomeName("Meadow"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenMoor(BOPConfigurationIDs.moorID).setBiomeName("Moor"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenMountain(BOPConfigurationIDs.mountainID).setBiomeName("Mountain"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenMysticGrove(BOPConfigurationIDs.mysticGroveID).setBiomeName("Mystic Grove"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenOminousWoods(BOPConfigurationIDs.ominousWoodsID).setBiomeName("Ominous Woods"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenOriginValley(BOPConfigurationIDs.originValleyID).setBiomeName("Origin Valley"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenOutback(BOPConfigurationIDs.outbackID).setBiomeName("Outback"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenPasture(BOPConfigurationIDs.pastureID).setBiomeName("Pasture"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenPrairie(BOPConfigurationIDs.prairieID).setBiomeName("Prairie"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenQuagmire(BOPConfigurationIDs.quagmireID).setBiomeName("Quagmire"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenRainforest(BOPConfigurationIDs.rainforestID).setBiomeName("Rainforest"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenRedwoodForest(BOPConfigurationIDs.redwoodForestID).setBiomeName("Redwood Forest"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenSacredSprings(BOPConfigurationIDs.sacredSpringsID).setBiomeName("Sacred Springs"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenSeasonalForest(BOPConfigurationIDs.seasonalForestID).setBiomeName("Seasonal Forest"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenShield(BOPConfigurationIDs.shieldID).setBiomeName("Shield"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenShrubland(BOPConfigurationIDs.shrublandID).setBiomeName("Shrubland"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenSilkglades(BOPConfigurationIDs.silkgladesID).setBiomeName("Silkglades"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenSludgepit(BOPConfigurationIDs.sludgepitID).setBiomeName("Sludgepit"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenSpruceWoods(BOPConfigurationIDs.spruceWoodsID).setBiomeName("Spruce Woods"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenTemperateRainforest(BOPConfigurationIDs.temperateRainforestID).setBiomeName("Temperate Rainforest"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenThicket(BOPConfigurationIDs.thicketID).setBiomeName("Thicket"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenTimber(BOPConfigurationIDs.timberID).setBiomeName("Timber"), BOPBiomeTemperatureType.COOL));
        registerBiome(new BOPBiomeListEntry(new BiomeGenTropicalRainforest(BOPConfigurationIDs.tropicalRainforestID).setBiomeName("Tropical Rainforest"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenTropics(BOPConfigurationIDs.tropicsID).setBiomeName("Tropics"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenTundra(BOPConfigurationIDs.tundraID).setBiomeName("Tundra"), BOPBiomeTemperatureType.ICY));
        registerBiome(new BOPBiomeListEntry(new BiomeGenVolcano(BOPConfigurationIDs.volcanoID).setBiomeName("Volcano"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenWasteland(BOPConfigurationIDs.wastelandID).setBiomeName("Wasteland"), BOPBiomeTemperatureType.HOT));
        registerBiome(new BOPBiomeListEntry(new BiomeGenWetland(BOPConfigurationIDs.wetlandID).setBiomeName("Wetland"), BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(new BiomeGenWoodland(BOPConfigurationIDs.woodlandID).setBiomeName("Woodland"), BOPBiomeTemperatureType.WARM));
	}

	private static void addSpawnBiomes()
	{
		if (BOPConfigurationMisc.onlySpawnOnBeaches)
		{
			clearAllSpawnBiomes();

			addSpawnBiome(BiomeGenBase.beach);
			//TODO:					   stoneBeach
			addSpawnBiome(BiomeGenBase.field_150576_N);
			//TODO:						coldBeach
			addSpawnBiome(BiomeGenBase.field_150577_O);
		}
		else
		{
			for (BiomeGenBase biome : BOPBiomeHelper.biomeList.values())
			{
				addSpawnBiome(biome);
			}
		}
	}
	
	private static void addVillageBiomes()
	{

	}
	
	private static void addStrongholdBiomes()
	{
        /*addStrongholdBiome(Biomes.alps);
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
        addStrongholdBiome(Biomes.lavenderFields);
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
        addStrongholdBiome(Biomes.jungleNew);*/
	}
	
	public static void registerOnlyBiome(BOPBiomeListEntry biome)
	{
		onlyBiome = biome.biome;
	}
	
	public static void registerBiome(BOPBiomeListEntry biome)
	{
	    BOPBiomeHelper.registerBiome(biome.biome, BOPBiomeHelper.convertBiomeName(biome.biome.biomeName));
	    addBiomeToList(biome);
	}
	
	public static void addBiomeToList(BOPBiomeListEntry biome)
	{ 
	    BOPBiomeHelper.getCorrespondingTemperatureTypeList(biome.temperatureType).add(biome.biome);
	}
	
	public static void addSpawnBiome(BiomeGenBase biome)
	{
	    BiomeManager.addSpawnBiome(biome);
	}
	
	public static void clearAllSpawnBiomes()
	{
	    WorldChunkManager.allowedBiomes.clear();
	}

	private static void useOnlyBiome()
	{
	    if (onlyBiome != null)
	    {
	        for (BOPBiomeTemperatureType temperatureType : BOPBiomeHelper.BOPBiomeTemperatureType.values())
	        {
	            BOPBiomeHelper.getCorrespondingTemperatureTypeList(temperatureType).clear();
	            addBiomeToList(new BOPBiomeListEntry(onlyBiome, temperatureType));
	        }
	    }
	}
}
