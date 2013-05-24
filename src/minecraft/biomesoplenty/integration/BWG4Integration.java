package biomesoplenty.integration;

import ted80.api.DefaultBiomeList;
import biomesoplenty.api.Biomes;

public class BWG4Integration
{
    protected static void init()
    {
        bwg4Init();
    }
    
    private static void bwg4Init()
    {        
    	if(Biomes.woodland.isPresent()) { DefaultBiomeList.addBiome("BoP: Woodland", Biomes.woodland.get(), 2); }
	    if(Biomes.wetland.isPresent()) { DefaultBiomeList.addBiome("BoP: Wetland", Biomes.wetland.get(), 3); }
	    if(Biomes.wasteland.isPresent()) { DefaultBiomeList.addBiome("BoP: Wasteland", Biomes.wasteland.get(), 4); }
	    if(Biomes.volcano.isPresent()) { DefaultBiomeList.addBiome("BoP: Volcano", Biomes.volcano.get(), 4); }
	    if(Biomes.tundra.isPresent()) { DefaultBiomeList.addBiome("BoP: Tundra", Biomes.tundra.get(), 1); }
	    if(Biomes.tropics.isPresent()) { DefaultBiomeList.addBiome("BoP: Tropics", Biomes.tropics.get(), 3); }
	    if(Biomes.tropicalRainforest.isPresent()) { DefaultBiomeList.addBiome("BoP: Tropical Rainforest", Biomes.tropicalRainforest.get(), 3); }
		if(Biomes.timber.isPresent()) { DefaultBiomeList.addBiome("BoP: Timber", Biomes.timber.get(), 2); }
	    if(Biomes.thicket.isPresent()) { DefaultBiomeList.addBiome("BoP: Thicket", Biomes.thicket.get(), 2); }
	    if(Biomes.temperateRainforest.isPresent()) { DefaultBiomeList.addBiome("BoP: Temperate Rainforest", Biomes.temperateRainforest.get(), 3); }
	    if(Biomes.steppe.isPresent()) { DefaultBiomeList.addBiome("BoP: Steppe", Biomes.steppe.get(), 4); }
	    if(Biomes.spruceWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Spruce Woods", Biomes.spruceWoods.get(), 2); }
		if(Biomes.sludgepit.isPresent()) { DefaultBiomeList.addBiome("BoP: Sludgepit", Biomes.sludgepit.get(), 3); }
	    if(Biomes.shrubland.isPresent()) { DefaultBiomeList.addBiome("BoP: Shrubland", Biomes.shrubland.get(), 2); }
	    if(Biomes.shield.isPresent()) { DefaultBiomeList.addBiome("BoP: Shield", Biomes.shield.get(), 2); }
	    if(Biomes.seasonalForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Seasonal Forest", Biomes.seasonalForest.get(), 2); }
	    if(Biomes.scrubland.isPresent()) { DefaultBiomeList.addBiome("BoP: Scrubland", Biomes.scrubland.get(), 4); }
	    if(Biomes.savanna.isPresent()) { DefaultBiomeList.addBiome("BoP: Savanna", Biomes.savanna.get(), 4); }
	    if(Biomes.sacredSprings.isPresent()) { DefaultBiomeList.addBiome("BoP: Sacred Springs", Biomes.sacredSprings.get(), 3); }
	    if(Biomes.redwoodForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Redwood Forest", Biomes.redwoodForest.get(), 2); }
	    if(Biomes.rainforest.isPresent()) { DefaultBiomeList.addBiome("BoP: Rainforest", Biomes.rainforest.get(), 3); }
	    if(Biomes.quagmire.isPresent()) { DefaultBiomeList.addBiome("BoP: Quagmire", Biomes.quagmire.get(), 4); }
	    if(Biomes.prairie.isPresent()) { DefaultBiomeList.addBiome("BoP: Prairie", Biomes.prairie.get(), 2); }
		if(Biomes.polar.isPresent()) { DefaultBiomeList.addBiome("BoP: Polar", Biomes.polar.get(), 1); }
	    if(Biomes.pasture.isPresent()) { DefaultBiomeList.addBiome("BoP: Pasture", Biomes.pasture.get(), 2); }
	    if(Biomes.outback.isPresent()) { DefaultBiomeList.addBiome("BoP: Outback", Biomes.outback.get(), 4); }
	    if(Biomes.originValley.isPresent()) { DefaultBiomeList.addBiome("BoP: Origin Valley", Biomes.originValley.get(), 2); }
	    if(Biomes.orchard.isPresent()) { DefaultBiomeList.addBiome("BoP: Orchard", Biomes.orchard.get(), 2); }
	    if(Biomes.ominousWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Ominous Woods", Biomes.ominousWoods.get(), 2); }
	    if(Biomes.oasis.isPresent()) { DefaultBiomeList.addBiome("BoP: Oasis", Biomes.oasis.get(), 4); }
	    if(Biomes.mysticGrove.isPresent()) { DefaultBiomeList.addBiome("BoP: Mystic Grove", Biomes.mysticGrove.get(), 3); }
	    if(Biomes.mountain.isPresent()) { DefaultBiomeList.addBiome("BoP: Mountain", Biomes.mountain.get(), 2); }
	    if(Biomes.moor.isPresent()) { DefaultBiomeList.addBiome("BoP: Moor", Biomes.moor.get(), 2); }
	    if(Biomes.mesa.isPresent()) { DefaultBiomeList.addBiome("BoP: Mesa", Biomes.mesa.get(), 4); }
        if(Biomes.meadow.isPresent()) { DefaultBiomeList.addBiome("BoP: Meadow", Biomes.meadow.get(), 2); }
        if(Biomes.marsh.isPresent()) { DefaultBiomeList.addBiome("BoP: Marsh", Biomes.marsh.get(), 2); }
        if(Biomes.mapleWoods.isPresent()) { DefaultBiomeList.addBiome("BoP: Maple Woods", Biomes.mapleWoods.get(), 2); }
        if(Biomes.mangrove.isPresent()) { DefaultBiomeList.addBiome("BoP: Mangrove", Biomes.mangrove.get(), 2); }
        if(Biomes.lushSwamp.isPresent()) { DefaultBiomeList.addBiome("BoP: Lush Swamp", Biomes.lushSwamp.get(), 3); }
        if(Biomes.lushDesert.isPresent()) { DefaultBiomeList.addBiome("BoP: Lush Desert", Biomes.lushDesert.get(), 4); }
        if(Biomes.jadeCliffs.isPresent()) { DefaultBiomeList.addBiome("BoP: Jade Cliffs", Biomes.jadeCliffs.get(), 2); }
        if(Biomes.icyHills.isPresent()) { DefaultBiomeList.addBiome("BoP: Icy Hills", Biomes.icyHills.get(), 1); }
        if(Biomes.highland.isPresent()) { DefaultBiomeList.addBiome("BoP: Highland", Biomes.highland.get(), 2); }
        if(Biomes.heathland.isPresent()) { DefaultBiomeList.addBiome("BoP: Heathland", Biomes.heathland.get(), 4); }
        if(Biomes.grove.isPresent()) { DefaultBiomeList.addBiome("BoP: Grove", Biomes.grove.get(), 2); }
        if(Biomes.grassland.isPresent()) { DefaultBiomeList.addBiome("BoP: Grassland", Biomes.grassland.get(), 2); }
        if(Biomes.glacier.isPresent()) { DefaultBiomeList.addBiome("BoP: Glacier", Biomes.glacier.get(), 1); }
        if(Biomes.garden.isPresent()) { DefaultBiomeList.addBiome("BoP: Garden", Biomes.garden.get(), 2); }
        if(Biomes.frostForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Frost Forest", Biomes.frostForest.get(), 1); }
        if(Biomes.fungiForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Fungi Forest", Biomes.fungiForest.get(), 3); }
        if(Biomes.field.isPresent()) { DefaultBiomeList.addBiome("BoP: Field", Biomes.field.get(), 2); }
        if(Biomes.fen.isPresent()) { DefaultBiomeList.addBiome("BoP: Fen", Biomes.fen.get(), 2); }
        if(Biomes.dunes.isPresent()) { DefaultBiomeList.addBiome("BoP: Dunes", Biomes.dunes.get(), 4); }
        if(Biomes.deciduousForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Deciduous Forest", Biomes.deciduousForest.get(), 2); }
        if(Biomes.deadlands.isPresent()) { DefaultBiomeList.addBiome("BoP: Deadlands", Biomes.deadlands.get(), 4); }
        if(Biomes.deadSwamp.isPresent()) { DefaultBiomeList.addBiome("BoP: Dead Swamp", Biomes.deadSwamp.get(), 2); }
		if(Biomes.deadForestSnow.isPresent()) { DefaultBiomeList.addBiome("BoP: Dead Forest (Snow)", Biomes.deadForestSnow.get(), 1); }
        if(Biomes.deadForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Dead Forest", Biomes.deadForest.get(), 2); }
        if(Biomes.crag.isPresent()) { DefaultBiomeList.addBiome("BoP: Crag", Biomes.crag.get(), 2); }
		if(Biomes.coniferousForestSnow.isPresent()) { DefaultBiomeList.addBiome("BoP: Coniferous Forest (Snow)", Biomes.coniferousForestSnow.get(), 1); }
        if(Biomes.coniferousForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Coniferous Forest", Biomes.coniferousForest.get(), 2); }
        if(Biomes.cherryBlossomGrove.isPresent()) { DefaultBiomeList.addBiome("BoP: Cherry Blossom Grove", Biomes.cherryBlossomGrove.get(), 2); }
        if(Biomes.chaparral.isPresent()) { DefaultBiomeList.addBiome("BoP: Chaparral", Biomes.chaparral.get(), 2); }
        if(Biomes.canyon.isPresent()) { DefaultBiomeList.addBiome("BoP: Canyon", Biomes.canyon.get(), 4); }
		if(Biomes.brushland.isPresent()) { DefaultBiomeList.addBiome("BoP: Brushland", Biomes.brushland.get(), 4); }
        if(Biomes.borealForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Boreal Forest", Biomes.borealForest.get(), 2); }
        if(Biomes.bog.isPresent()) { DefaultBiomeList.addBiome("BoP: Bog", Biomes.bog.get(), 3); }
        if(Biomes.birchForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Birch Forest", Biomes.birchForest.get(), 2); }
        if(Biomes.bayou.isPresent()) { DefaultBiomeList.addBiome("BoP: Bayou", Biomes.bayou.get(), 3); }
        if(Biomes.bambooForest.isPresent()) { DefaultBiomeList.addBiome("BoP: Bamboo Forest", Biomes.bambooForest.get(), 3); }
        if(Biomes.badlands.isPresent()) { DefaultBiomeList.addBiome("BoP: Badlands", Biomes.badlands.get(), 4); }
        if(Biomes.arctic.isPresent()) { DefaultBiomeList.addBiome("BoP: Arctic", Biomes.arctic.get(), 1); }
        if(Biomes.alps.isPresent()) { DefaultBiomeList.addBiome("BoP: Alps", Biomes.alps.get(), 1); }
        
        if(Biomes.jungleNew.isPresent()) { DefaultBiomeList.addBiome("BoP: Jungle", Biomes.jungleNew.get(), 1); }
        if(Biomes.swamplandNew.isPresent()) { DefaultBiomeList.addBiome("BoP: Swampland", Biomes.swamplandNew.get(), 1); }
        if(Biomes.taigaNew.isPresent()) { DefaultBiomeList.addBiome("BoP: Taiga", Biomes.taigaNew.get(), 1); }
        if(Biomes.forestNew.isPresent()) { DefaultBiomeList.addBiome("BoP: Forest", Biomes.forestNew.get(), 1); }
        if(Biomes.extremeHillsNew.isPresent()) { DefaultBiomeList.addBiome("BoP: ExtemeHills", Biomes.extremeHillsNew.get(), 1); }
        if(Biomes.desertNew.isPresent()) { DefaultBiomeList.addBiome("BoP: Desert", Biomes.desertNew.get(), 1); }
        if(Biomes.plainsNew.isPresent()) { DefaultBiomeList.addBiome("BoP: Plains", Biomes.plainsNew.get(), 1); }
    }
}
