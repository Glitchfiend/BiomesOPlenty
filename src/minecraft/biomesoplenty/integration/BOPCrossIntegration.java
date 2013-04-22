package biomesoplenty.integration;

import net.minecraft.item.ItemStack;
import ted80.api.DefaultBiomeList;
import thermalexpansion.api.crafting.CraftingManagers;
import thermalexpansion.api.item.ItemRegistry;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;

import cpw.mods.fml.common.Loader;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

public class BOPCrossIntegration {
	
	public static void init()
	{
		if (Loader.isModLoaded("Forestry"))
		{
			try {
				forestryInit();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Forestry with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
		
		if (Loader.isModLoaded("BWG4"))
		{
			try {
				bwg4Init();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Better World Generation 4 with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
		
		if (Loader.isModLoaded("ThermalExpansion"))
		{
			try {
				thermalExpansionInit();
			}
			catch (Exception e) {
				System.out.println("[BiomesOPlenty] There was an error while integrating Thermal Expansion with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
	}

	private static void forestryInit()
	{
		//Hot - Arid
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.badlandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.deadlandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.drylandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.dunesID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.mesaID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.steppeID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.volcanoID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.wastelandID);
		
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.badlandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.deadlandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.drylandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.dunesID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.mesaID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.steppeID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.volcanoID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.wastelandID);
		
		//Hot - Damp
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.oasisID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.promisedLandID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.rainforestID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.tropicsID);
		
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.oasisID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.promisedLandID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.rainforestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.tropicsID);
		
		//Warm - Damp
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.bambooForestID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.sacredSpringsID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.tropicalRainforestID);
		
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.bambooForestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.sacredSpringsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.tropicalRainforestID);
		
		//Warm - Arid
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.deadForestID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.savannaID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.scrublandID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.woodlandID);
		
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.deadForestID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.savannaID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.scrublandID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.woodlandID);
		
		//Normal - Damp
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.bayouID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.bogID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.deadSwampID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.fungiForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.lushSwampID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.mangroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.marshID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.moorID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.mysticGroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.ominousWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.quagmireID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.swampwoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.wetlandID);
		
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.bayouID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.bogID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.deadSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.fungiForestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.lushSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.mangroveID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.marshID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.moorID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.mysticGroveID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.ominousWoodsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.quagmireID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.swampwoodsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.wetlandID);
		
		//Normal
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.birchForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.borealForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.canyonID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.chaparralID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.cherryBlossomGroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.coniferousForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.deciduousForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.fieldID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.gardenID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.grasslandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.groveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.highlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.lushDesertID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.mapleWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.meadowID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.orchardID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.originValleyID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.pastureID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.prairieID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.redwoodForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.seasonalForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.shieldID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.shoreID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.spruceWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.temperateRainforestID);

		EnumHumidity.normalBiomeIds.add(BOPConfiguration.birchForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.borealForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.canyonID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.chaparralID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.cherryBlossomGroveID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.coniferousForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.deciduousForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.fieldID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.gardenID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.grasslandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.groveID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.highlandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.lushDesertID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.mapleWoodsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.meadowID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.orchardID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.originValleyID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.pastureID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.prairieID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.redwoodForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.seasonalForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.shieldID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.shoreID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.spruceWoodsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.temperateRainforestID);
		
		//Normal - Arid
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.cragID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.fenID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.heathlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.jadeCliffsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.mountainID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.outbackID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.shrublandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.thicketID);
		
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.cragID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.fenID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.heathlandID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.jadeCliffsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.mountainID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.outbackID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.shrublandID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.thicketID);
		
		//Cold
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.tundraID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.snowyWoodsID);
		
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.tundraID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.snowyWoodsID);
		
		//Icy - Arid
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.alpsID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.arcticID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.frostForestID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.glacierID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.iceSheetID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.icyHillsID);
		
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.alpsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.arcticID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.frostForestID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.glacierID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.iceSheetID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.icyHillsID);
		
		//New vanilla biomes
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.plainsNewID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.desertNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.extremeHillsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.forestNewID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.taigaNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.swamplandNewID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.jungleNewID);
		
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.plainsNewID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.desertNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.extremeHillsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.forestNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.taigaNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.swamplandNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.jungleNewID);
	}
	
	public static void bwg4Init()
	{
		if(Biomes.alps.isPresent()) { DefaultBiomeList.addBiome("BoP: Alps", Biomes.alps.get(), 1); }
		if(Biomes.arctic.isPresent()) { DefaultBiomeList.addBiome("BoP: Arctic", Biomes.arctic.get(), 1); }
		if(Biomes.badlands.isPresent()) { DefaultBiomeList.addBiome("BoP: Badlands", Biomes.badlands.get(), 4); }
		if(Biomes.bambooForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Bamboo Forest", Biomes.bambooForest.get(), 3); }
		if(Biomes.bayou.isPresent()) { DefaultBiomeList.addBiome("BoP: Bayou", Biomes.bayou.get(), 3); }
		if(Biomes.birchForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Birch Forest", Biomes.birchForest.get(), 2); }
		if(Biomes.bog.isPresent()) { DefaultBiomeList.addBiome("BoP: Bog", Biomes.bog.get(), 3); }
		if(Biomes.borealForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Boreal Forest", Biomes.borealForest.get(), 2); }
		if(Biomes.canyon.isPresent()) { DefaultBiomeList.addBiome("BoP: Canyon", Biomes.canyon.get(), 4); }
		if(Biomes.chaparral.isPresent()) { DefaultBiomeList.addBiome("BoP: Chaparral", Biomes.chaparral.get(), 2); }
		if(Biomes.cherryBlossomGrove.isPresent()) { DefaultBiomeList.addBiome("BoP: Cherry Blossom Grove", Biomes.cherryBlossomGrove.get(), 2); }
		if(Biomes.coniferousForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Coniferous Forest", Biomes.coniferousForest.get(), 2); }
		if(Biomes.crag.isPresent()) { DefaultBiomeList.addBiome("BoP: Crag", Biomes.crag.get(), 2); }
		if(Biomes.deadForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Dead Forest", Biomes.deadForest.get(), 2); }
		if(Biomes.deadSwamp.isPresent()) { DefaultBiomeList.addBiome("BoP: Dead Swamp", Biomes.deadSwamp.get(), 2); }
		if(Biomes.deadlands.isPresent()) { DefaultBiomeList.addBiome("BoP: Deadlands", Biomes.deadlands.get(), 4); }
		if(Biomes.deciduousForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Deciduous Forest", Biomes.deciduousForest.get(), 2); }
		if(Biomes.drylands.isPresent()) { DefaultBiomeList.addBiome("BoP: Drylands", Biomes.drylands.get(), 4); }
		if(Biomes.dunes.isPresent()) { DefaultBiomeList.addBiome("BoP: Dunes", Biomes.dunes.get(), 4); }
		if(Biomes.fen.isPresent()) { DefaultBiomeList.addBiome("BoP: Fen", Biomes.fen.get(), 2); }
		if(Biomes.field.isPresent()) { DefaultBiomeList.addBiome("BoP: Field", Biomes.field.get(), 2); }
		if(Biomes.frostForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Frost Forest", Biomes.frostForest.get(), 1); }
		if(Biomes.fungiForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Fungi Forest", Biomes.fungiForest.get(), 3); }
		if(Biomes.garden.isPresent()) { DefaultBiomeList.addBiome("BoP: Garden", Biomes.garden.get(), 2); }
		if(Biomes.glacier.isPresent()) { DefaultBiomeList.addBiome("BoP: Glacier", Biomes.glacier.get(), 1); }
		if(Biomes.grassland.isPresent()) { DefaultBiomeList.addBiome("BoP: Grassland", Biomes.grassland.get(), 2); }
		if(Biomes.grove.isPresent()) { DefaultBiomeList.addBiome("BoP: Grove", Biomes.grove.get(), 2); }
		if(Biomes.heathland.isPresent()) { DefaultBiomeList.addBiome("BoP: Heathland", Biomes.heathland.get(), 4); }
		if(Biomes.highland.isPresent()) { DefaultBiomeList.addBiome("BoP: Highland", Biomes.highland.get(), 2); }
		if(Biomes.iceSheet.isPresent()) { DefaultBiomeList.addBiome("BoP: Ice Sheet", Biomes.iceSheet.get(), 1); }
		if(Biomes.icyHills.isPresent()) { DefaultBiomeList.addBiome("BoP: Icy Hills", Biomes.icyHills.get(), 1); }
		if(Biomes.jadeCliffs.isPresent()) { DefaultBiomeList.addBiome("BoP: Jade Cliffs", Biomes.jadeCliffs.get(), 2); }
		if(Biomes.lushDesert.isPresent()) { DefaultBiomeList.addBiome("BoP: Lush Desert", Biomes.lushDesert.get(), 4); }
		if(Biomes.lushSwamp.isPresent()) { DefaultBiomeList.addBiome("BoP: Lush Swamp", Biomes.lushSwamp.get(), 3); }
		if(Biomes.mangrove.isPresent()) { DefaultBiomeList.addBiome("BoP: Mangrove", Biomes.mangrove.get(), 2); }
		if(Biomes.mapleWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Maple Woods", Biomes.mapleWoods.get(), 2); }
		if(Biomes.marsh.isPresent()) { DefaultBiomeList.addBiome("BoP: Marsh", Biomes.marsh.get(), 2); }
		if(Biomes.meadow.isPresent()) { DefaultBiomeList.addBiome("BoP: Meadow", Biomes.meadow.get(), 2); }
		if(Biomes.mesa.isPresent()) { DefaultBiomeList.addBiome("BoP: Mesa", Biomes.mesa.get(), 4); }
		if(Biomes.moor.isPresent()) { DefaultBiomeList.addBiome("BoP: Moor", Biomes.moor.get(), 2); }
		if(Biomes.mountain.isPresent()) { DefaultBiomeList.addBiome("BoP: Mountain", Biomes.mountain.get(), 2); }
		if(Biomes.mysticGrove.isPresent()) { DefaultBiomeList.addBiome("BoP: Mystic Grove", Biomes.mysticGrove.get(), 3); }
		if(Biomes.oasis.isPresent()) { DefaultBiomeList.addBiome("BoP: Oasis", Biomes.oasis.get(), 4); }
		if(Biomes.ominousWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Ominous Woods", Biomes.ominousWoods.get(), 2); }
		if(Biomes.orchard.isPresent()) { DefaultBiomeList.addBiome("BoP: Orchard", Biomes.orchard.get(), 2); }
		if(Biomes.originValley.isPresent()) { DefaultBiomeList.addBiome("BoP: Origin Valley", Biomes.originValley.get(), 2); }
		if(Biomes.outback.isPresent()) { DefaultBiomeList.addBiome("BoP: Outback", Biomes.outback.get(), 4); }
		if(Biomes.pasture.isPresent()) { DefaultBiomeList.addBiome("BoP: Pasture", Biomes.pasture.get(), 2); }
		if(Biomes.prairie.isPresent()) { DefaultBiomeList.addBiome("BoP: Prairie", Biomes.prairie.get(), 2); }
		if(Biomes.quagmire.isPresent()) { DefaultBiomeList.addBiome("BoP: Quagmire", Biomes.quagmire.get(), 4); }
		if(Biomes.rainforest.isPresent()) { DefaultBiomeList.addBiome("BoP: Rainforest", Biomes.rainforest.get(), 3); }
		if(Biomes.redwoodForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Redwood Forest", Biomes.redwoodForest.get(), 2); }
		if(Biomes.sacredSprings.isPresent()) { DefaultBiomeList.addBiome("BoP: Sacred Springs", Biomes.sacredSprings.get(), 3); }
		if(Biomes.savanna.isPresent()) { DefaultBiomeList.addBiome("BoP: Savanna", Biomes.savanna.get(), 4); }
		if(Biomes.scrubland.isPresent()) { DefaultBiomeList.addBiome("BoP: Scrubland", Biomes.scrubland.get(), 4); }
		if(Biomes.seasonalForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Seasonal Forest", Biomes.seasonalForest.get(), 2); }
		if(Biomes.shield.isPresent()) { DefaultBiomeList.addBiome("BoP: Shield", Biomes.shield.get(), 2); }
		if(Biomes.shrubland.isPresent()) { DefaultBiomeList.addBiome("BoP: Shrubland", Biomes.shrubland.get(), 2); }
		if(Biomes.snowyWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Snowy Woods", Biomes.snowyWoods.get(), 1); }
		if(Biomes.spruceWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Spruce Woods", Biomes.spruceWoods.get(), 2); }
		if(Biomes.steppe.isPresent()) { DefaultBiomeList.addBiome("BoP: Steppe", Biomes.steppe.get(), 4); }
		if(Biomes.swampwoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Swampwoods", Biomes.swampwoods.get(), 3); }
		if(Biomes.temperateRainforest.isPresent()) { DefaultBiomeList.addBiome("BoP: Temperate Rainforest", Biomes.temperateRainforest.get(), 3); }
		if(Biomes.thicket.isPresent()) { DefaultBiomeList.addBiome("BoP: Thicket", Biomes.thicket.get(), 2); }
		if(Biomes.tropicalRainforest.isPresent()) { DefaultBiomeList.addBiome("BoP: Tropical Rainforest", Biomes.tropicalRainforest.get(), 3); }
		if(Biomes.tropics.isPresent()) { DefaultBiomeList.addBiome("BoP: Tropics", Biomes.tropics.get(), 3); }
		if(Biomes.tundra.isPresent()) { DefaultBiomeList.addBiome("BoP: Tundra", Biomes.tundra.get(), 1); }
		if(Biomes.volcano.isPresent()) { DefaultBiomeList.addBiome("BoP: Volcano", Biomes.volcano.get(), 4); }
		if(Biomes.wasteland.isPresent()) { DefaultBiomeList.addBiome("BoP: Wasteland", Biomes.wasteland.get(), 4); }
		if(Biomes.wetland.isPresent()) { DefaultBiomeList.addBiome("BoP: Wetland", Biomes.wetland.get(), 3); }
		if(Biomes.woodland.isPresent()) { DefaultBiomeList.addBiome("BoP: Woodland", Biomes.woodland.get(), 2); }
	}
	
	public static void thermalExpansionInit()
	{
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 0), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 1), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 2), new ItemStack(Blocks.planks.get(), 6, 2), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs1.get(), 1, 3), new ItemStack(Blocks.planks.get(), 6, 3), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 4), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 5), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 2), new ItemStack(Blocks.planks.get(), 6, 6), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs2.get(), 1, 3), new ItemStack(Blocks.planks.get(), 6, 7), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs3.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 8), ItemRegistry.getItem("sawdust", 1), 100);
		CraftingManagers.sawmillManager.addRecipe(80, new ItemStack(Blocks.logs3.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 9), ItemRegistry.getItem("sawdust", 1), 100);
	}
}
