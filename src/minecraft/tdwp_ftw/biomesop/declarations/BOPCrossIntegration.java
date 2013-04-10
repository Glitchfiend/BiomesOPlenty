package tdwp_ftw.biomesop.declarations;

import cpw.mods.fml.common.Loader;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

public class BOPCrossIntegration {

	public static void forestryInit()
	{
		if (Loader.isModLoaded("Forestry"))
		{
			try {
				//Hottest to Coldest
				EnumTemperature.hellishBiomeIds.add(BOPConfiguration.deadlandsID);
				EnumTemperature.hellishBiomeIds.add(BOPConfiguration.volcanoID);
				
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.deadlandsID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.volcanoID);
				
				//Hot - Arid
				EnumTemperature.hotBiomeIds.add(BOPConfiguration.badlandsID);
				EnumTemperature.hotBiomeIds.add(BOPConfiguration.dunesID);
				EnumTemperature.hotBiomeIds.add(BOPConfiguration.mesaID);
				EnumTemperature.hotBiomeIds.add(BOPConfiguration.wastelandID);
				
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.badlandsID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.dunesID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.mesaID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.wastelandID);
				
				//Hot - Damp
				EnumTemperature.hotBiomeIds.add(BOPConfiguration.lushDesertID);
				EnumTemperature.hotBiomeIds.add(BOPConfiguration.oasisID);
				
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.lushDesertID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.oasisID);
				
				//Warm - Damp
				EnumTemperature.warmBiomeIds.add(BOPConfiguration.bambooForestID);
				EnumTemperature.warmBiomeIds.add(BOPConfiguration.rainforestID);
				EnumTemperature.warmBiomeIds.add(BOPConfiguration.sacredSpringsID);
				EnumTemperature.warmBiomeIds.add(BOPConfiguration.temperateRainforestID);
				EnumTemperature.warmBiomeIds.add(BOPConfiguration.tropicalRainforestID);
				EnumTemperature.warmBiomeIds.add(BOPConfiguration.tropicsID);
				
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.bambooForestID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.rainforestID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.sacredSpringsID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.temperateRainforestID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.tropicalRainforestID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.tropicsID);
				
				//Normal - Damp
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.bayouID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.bogID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.deadSwampID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.fenID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.fungiForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.lushSwampID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.moorID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.ominousWoodsID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.quagmireID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.shieldID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.swampwoodsID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.wetlandID);
				
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.bayouID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.bogID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.deadSwampID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.fenID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.fungiForestID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.lushSwampID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.moorID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.ominousWoodsID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.quagmireID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.shieldID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.swampwoodsID);
				EnumHumidity.dampBiomeIds.add(BOPConfiguration.wetlandID);
				
				//Normal
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.birchForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.borealForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.chaparralID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.cherryBlossomGroveID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.coniferousForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.deciduousForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.fieldID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.gardenID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.grasslandID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.groveID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.highlandID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.jadeCliffsID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.mapleWoodsID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.meadowID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.mountainID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.mysticGroveID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.orchardID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.prairieID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.redwoodForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.seasonalForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.spruceWoodsID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.thicketID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.woodlandID);
				
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.birchForestID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.borealForestID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.chaparralID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.cherryBlossomGroveID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.coniferousForestID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.deciduousForestID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.fieldID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.gardenID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.grasslandID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.groveID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.highlandID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.jadeCliffsID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.mapleWoodsID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.meadowID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.mountainID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.mysticGroveID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.orchardID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.prairieID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.redwoodForestID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.seasonalForestID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.spruceWoodsID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.thicketID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.woodlandID);
				
				//Normal - Arid
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.canyonID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.cragID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.deadForestID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.drylandsID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.heathlandID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.outbackID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.pastureID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.savannaID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.scrublandID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.shrublandID);
				EnumTemperature.normalBiomeIds.add(BOPConfiguration.steppeID);
				
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.canyonID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.cragID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.deadForestID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.drylandsID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.heathlandID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.outbackID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.pastureID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.savannaID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.scrublandID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.shrublandID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.steppeID);
				
				//Cold
				EnumTemperature.coldBiomeIds.add(BOPConfiguration.tundraID);
				EnumTemperature.coldBiomeIds.add(BOPConfiguration.snowyWoodsID);
				
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.tundraID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.snowyWoodsID);
				
				//Icy
				EnumTemperature.icyBiomeIds.add(BOPConfiguration.alpsID);
				EnumTemperature.icyBiomeIds.add(BOPConfiguration.arcticID);
				EnumTemperature.icyBiomeIds.add(BOPConfiguration.frostForestID);
				
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.alpsID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.arcticID);
				EnumHumidity.normalBiomeIds.add(BOPConfiguration.frostForestID);
				
				//Icy - Arid
				EnumTemperature.icyBiomeIds.add(BOPConfiguration.glacierID);
				EnumTemperature.icyBiomeIds.add(BOPConfiguration.iceSheetID);
				EnumTemperature.icyBiomeIds.add(BOPConfiguration.icyHillsID);
				
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.glacierID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.iceSheetID);
				EnumHumidity.aridBiomeIds.add(BOPConfiguration.icyHillsID);
			}
			catch (Exception e) {
				System.out.println("[Biomes O' Plenty]: There was an error while integrating Forestry with Biomes O' Plenty!");
				e.printStackTrace(System.err);
			}
		}
	}
}
