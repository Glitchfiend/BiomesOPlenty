package biomesoplenty.integration;

import biomesoplenty.api.Biomes;
import biomesoplenty.integration.ATG.*;
import net.minecraft.world.biome.BiomeGenBase;
import ttftcuts.atg.api.ATGBiomes;
import ttftcuts.atg.api.ATGBiomes.BiomeType;
import ttftcuts.atg.api.IGenMod;

public class ATGIntegration {
	
	static double nb = 0.1; // Modifier used for biomes that have their own group
	static double[] tiers = { 1.0, 0.5, 0.3, 0.2, 0.1, 0.04 }; 
	
	static private BiomeType land = BiomeType.LAND;
	static private BiomeType coast = BiomeType.COAST;
	static private BiomeType sea = BiomeType.SEA;
	
	protected static void init() 
	{
		addSubBiomes();
		addLandBiomesGroup();
		addBeachBiomesGroup();
		addOceanBiomesGroup();
	}
	
	private static void addSubBiomes()
	{
		// ########################
		// sub-biomes
		// ########################
		
		ATGBiomes.addSubBiome(Biomes.meadow.get(), Biomes.meadowForest.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.canyon.get(), Biomes.canyonRavine.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.shrubland.get(), Biomes.shrublandForest.get(), 0.5);
		ATGBiomes.addSubBiome(Biomes.ominousWoods.get(), Biomes.ominousWoodsThick.get(), 0.5);
		ATGBiomes.addSubBiome(Biomes.pasture.get(), Biomes.pastureMeadow.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.pasture.get(), Biomes.pastureThin.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.timber.get(), Biomes.timberThin.get(), 0.5);
		ATGBiomes.addSubBiome(Biomes.alps.get(), Biomes.alpsForest.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.alps.get(), Biomes.alpsBase.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.seasonalForest.get(), Biomes.seasonalSpruceForest.get(), 1.0);
		ATGBiomes.addSubBiome(Biomes.field.get(), Biomes.fieldForest.get(), 1.0);
	}
	
	private static void addLandBiomesGroup()
	{
		addForestBiomesGroup();
		addJungleBiomesGroup();
		addPlainsBiomesGroup();
		addIcePlainsBiomesGroup();
		addTaigaBiomesGroup();
		addDesertBiomesGroup();
		addShrublandBiomesGroup();
		addBorealForestBiomesGroup();
		addTundraBiomesGroup();
		addSteppeBiomesGroup();
		addSavannahBiomesGroup();
		addTropicalShrublandBiomesGroup();
		addWoodlandBiomesGroup();
		addMesaBiomesGroup();
		addSwamplandBiomesGroup();
	}	
		
	private static void addForestBiomesGroup()
	{
		// ########################
		// forest
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Forest", BiomeGenBase.forest, Biomes.forestNew.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(land, "Forest", Biomes.birchForest.get(), tiers[1]);
		ATGBiomes.addBiome(land, "Forest", Biomes.woodland.get(), tiers[1] * nb); // out of base group
		ATGBiomes.addBiome(land, "Forest", Biomes.spruceWoods.get(), tiers[1] * nb); // out of base group
		
		// tier 3
		ATGBiomes.addBiome(land, "Forest", Biomes.coniferousForest.get(), tiers[2] * nb); // out of base group
		ATGBiomes.addBiome(land, "Forest", Biomes.temperateRainforest.get(), tiers[2] );
		ATGBiomes.addBiome(land, "Forest", Biomes.redwoodForest.get(), tiers[2]);
		ATGBiomes.addBiome(land, "Forest", Biomes.mountain.get(), tiers[2]);
		
		// tier 4
		ATGBiomes.addBiome(land, "Forest", Biomes.mapleWoods.get(), tiers[3]);
		ATGBiomes.addBiome(land, "Forest", Biomes.seasonalForest.get(), tiers[3]);
		ATGBiomes.addBiome(land, "Forest", Biomes.borealForest.get(), tiers[3] * nb); // out of base group
		ATGBiomes.addBiome(land, "Forest", Biomes.deciduousForest.get(), tiers[3]);
		ATGBiomes.addBiome(land, "Forest", Biomes.highland.get(), tiers[3] * nb); // out of base group
		
		// tier 5
		ATGBiomes.addBiome(land, "Forest", Biomes.deadForest.get(), tiers[4]);
		ATGBiomes.addBiome(land, "Forest", Biomes.grove.get(), tiers[4]);
		ATGBiomes.addBiome(land, "Forest", Biomes.timber.get(), tiers[4]);
		ATGBiomes.addBiome(land, "Forest", Biomes.thicket.get(), tiers[4]);
		ATGBiomes.addBiome(land, "Forest", Biomes.shield.get(), tiers[4]);
		
		// tier 6
		ATGBiomes.addBiome(land, "Forest", Biomes.fungiForest.get(), tiers[5]);
		ATGBiomes.addBiome(land, "Forest", Biomes.cherryBlossomGrove.get(), tiers[5]);
		ATGBiomes.addBiome(land, "Forest", Biomes.mysticGrove.get(), tiers[5]);
		ATGBiomes.addBiome(land, "Forest", Biomes.hotSprings.get(), tiers[5]);
		ATGBiomes.addBiome(land, "Forest", Biomes.originValley.get(), tiers[5] * 0.5); // better at lowland?
	}
	
	private static void addJungleBiomesGroup()
	{
		// ########################
		// Jungle
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Jungle", BiomeGenBase.jungle, Biomes.jungleNew.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(land, "Jungle", Biomes.tropicalRainforest.get(), tiers[1]);
		ATGBiomes.addBiome(land, "Jungle", Biomes.rainforest.get(), tiers[1]);
		
		// tier 3
		ATGBiomes.addBiome(land, "Jungle", Biomes.tropics.get(), tiers[2]);
		
		// tier 4
		ATGBiomes.addBiome(land, "Jungle", Biomes.bambooForest.get(), tiers[3]);
		ATGBiomes.addBiome(land, "Jungle", Biomes.jadeCliffs.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
		ATGBiomes.addBiome(land, "Jungle", Biomes.sacredSprings.get(), tiers[5]);
	}
	
	private static void addPlainsBiomesGroup()
	{
		// ########################
		// Plains
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Plains", BiomeGenBase.plains, Biomes.plainsNew.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(land, "Plains", Biomes.shrubland.get(), tiers[1] * nb); // out of base group
		ATGBiomes.addBiome(land, "Plains", Biomes.chaparral.get(), tiers[1]);
		ATGBiomes.addBiome(land, "Plains", Biomes.prairie.get(), tiers[1]);
		
		// tier 3
		ATGBiomes.addBiome(land, "Plains", Biomes.field.get(), tiers[2]);
		ATGBiomes.addBiome(land, "Plains", Biomes.grassland.get(), tiers[2]);
		
		// tier 4
		ATGBiomes.addBiome(land, "Plains", Biomes.pasture.get(), tiers[3]);
		ATGBiomes.addBiome(land, "Plains", Biomes.meadow.get(), tiers[3]);
		
		// tier 5
		ATGBiomes.addBiome(land, "Plains", Biomes.orchard.get(), tiers[4]);
		ATGBiomes.addBiome(land, "Plains", Biomes.overgrownGreens.get(), tiers[4]);
		ATGBiomes.addBiome(land, "Plains", Biomes.lavenderFields.get(), tiers[4]);
		
		// tier 6
		ATGBiomes.addBiome(land, "Plains", Biomes.garden.get(), tiers[5]);
	}
	
	private static void addIcePlainsBiomesGroup()
	{
		// ########################
		// Ice Plains
		// ########################
		
		// tier 1
		ATGBiomes.addBiome(land, "Ice Plains", Biomes.alps.get(), tiers[0]);
		
		// tier 2
		
		// tier 3
		ATGBiomes.addBiome(land, "Ice Plains", Biomes.polar.get(), tiers[2]);
		ATGBiomes.addBiome(land, "Ice Plains", Biomes.glacier.get(), tiers[2]);
		ATGBiomes.addGenMod(Biomes.glacier.get(), new GenModGlacier());
		ATGBiomes.addBiome(land, "Ice Plains", Biomes.arctic.get(), tiers[2]);
		
		// tier 4
		
		// tier 5
		
		// tier 6
		ATGBiomes.addBiome(land, "Ice Plains", Biomes.icyHills.get(), tiers[5]);
	}
	
	private static void addTaigaBiomesGroup()
	{
		// ########################
		// Taiga
		// ########################
				
		// tier 1
		ATGBiomes.replaceBiome(land, "Taiga", BiomeGenBase.taiga, Biomes.taigaNew.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(land, "Taiga", Biomes.alpsForest.get(), tiers[1]);
		ATGBiomes.addBiome(land, "Taiga", Biomes.coniferousForestSnow.get(), tiers[1]);
		ATGBiomes.addBiome(land, "Taiga", Biomes.frostForest.get(), tiers[1]);
		
		// tier 3
		
		// tier 4
		
		// tier 5
		ATGBiomes.addBiome(land, "Taiga", Biomes.deadForestSnow.get(), tiers[4]);
		
		// tier 6
		ATGBiomes.addBiome(land, "Taiga", Biomes.icyHills.get(), tiers[5] * nb);
	}
	
	private static void addDesertBiomesGroup()
	{
		// ########################
		// Desert
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Desert", BiomeGenBase.desert, Biomes.desertNew.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(land, "Desert", Biomes.dunes.get(), tiers[1]);
		
		// tier 3
		ATGBiomes.addBiome(land, "Desert", Biomes.outback.get(), tiers[2]);
		
		// tier 4
		ATGBiomes.addBiome(land, "Desert", Biomes.lushDesert.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
		ATGBiomes.addBiome(land, "Desert", Biomes.oasis.get(), tiers[5]);
	}
	
	private static void addShrublandBiomesGroup()
	{
		// ########################
		// Shrubland
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Shrubland", ATGBiomes.getBiome("Shrubland"), Biomes.shrubland.get(), tiers[0]);
		
		// tier 2
		
		// tier 3
		ATGBiomes.addBiome(land, "Shrubland", Biomes.heathland.get(), tiers[2]);
		
		// tier 4
		
		// tier 5
		ATGBiomes.addBiome(land, "Shrubland", Biomes.thicket.get(), tiers[4]);
		
		// tier 6
		
	}
	
	private static void addBorealForestBiomesGroup()
	{
		// ########################
		// Boreal Forest
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Boreal Forest", ATGBiomes.getBiome("BorealForest"), Biomes.borealForest.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(land, "Boreal Forest", Biomes.coniferousForest.get(), tiers[1]);
		ATGBiomes.addBiome(land, "Boreal Forest", Biomes.spruceWoods.get(), tiers[1]);
		
		// tier 3
		ATGBiomes.addBiome(land, "Boreal Forest", Biomes.forestNew.get(), tiers[2] * nb); // out of base group
		
		// tier 4
		ATGBiomes.addBiome(land, "Boreal Forest", Biomes.shield.get(), tiers[3]);
		ATGBiomes.addBiome(land, "Boreal Forest", Biomes.timber.get(), tiers[3]);
		
		// tier 5
		ATGBiomes.addBiome(land, "Boreal Forest", Biomes.deadForest.get(), tiers[4]);
		
		// tier 6
		
	}
		
	private static void addTundraBiomesGroup()
	{
		// ########################
		// Tundra
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Tundra", ATGBiomes.getBiome("Tundra"), Biomes.tundra.get(), tiers[0]);
		
		// tier 2
				
		// tier 3
		ATGBiomes.addBiome(land, "Tundra", BiomeGenBase.icePlains, tiers[2]);
		
		// tier 4
		
		// tier 5
		ATGBiomes.addBiome(land, "Tundra", Biomes.borealForest.get(), tiers[4] * nb); // out of base group
		
		// tier 6
		ATGBiomes.addBiome(land, "Tundra", Biomes.steppe.get(), tiers[5]);
	}
	
	private static void addSteppeBiomesGroup()
	{
		// ########################
		// Steppe
		// ########################
		
		// tier 1
		
		// tier 2
		
		// tier 3
		ATGBiomes.addBiome(land, "Steppe", Biomes.crag.get(), tiers[2]);
		
		// tier 4
		
		// tier 5
		ATGBiomes.addBiome(land, "Steppe", Biomes.mountain.get(), tiers[4]);
		
		// tier 6
		ATGBiomes.addBiome(land, "Steppe", Biomes.deadForest.get(), tiers[5]);
	}
	
	private static void addSavannahBiomesGroup()
	{
		// ########################
		// Savannah
		// ########################
		
		// tier 1
		ATGBiomes.replaceBiome(land, "Savanna", ATGBiomes.getBiome("Savanna"), Biomes.savanna.get(), 1.0);
		
		// tier 2
		
		// tier 3
		ATGBiomes.addBiome(land, "Savanna", Biomes.scrubland.get(), tiers[2]);
		ATGBiomes.addBiome(land, "Savanna", Biomes.outback.get(), tiers[2] * nb); // out of base group
		ATGBiomes.addBiome(land, "Savanna", Biomes.steppe.get(), tiers[2]);
		
		// tier 4
		ATGBiomes.addBiome(land, "Savanna", Biomes.lushDesert.get(), tiers[3]);
		
		// tier 5
		ATGBiomes.addBiome(land, "Savanna", Biomes.brushland.get(), tiers[4]);
		
		// tier 6
		
	}
	
	private static void addTropicalShrublandBiomesGroup()
	{
		// ########################
		// Tropical Shrubland
		// ########################
		
		// tier 1
		
		// tier 2
		ATGBiomes.addBiome(land, "Tropical Shrubland", Biomes.tropics.get(), tiers[1]);
		
		// tier 3
		
		// tier 4
		ATGBiomes.addBiome(land, "Tropical Shrubland", Biomes.bambooForest.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
		ATGBiomes.addBiome(land, "Tropical Shrubland", Biomes.sacredSprings.get(), tiers[5]);
	}
		
	private static void addWoodlandBiomesGroup()
	{
		// ########################
		// Woodland
		// ########################
		
		// tier 1
		
		// tier 2
		ATGBiomes.addBiome(land, "Woodland", Biomes.woodland.get(), tiers[1]);
		
		// tier 3
		
		// tier 4
		ATGBiomes.addBiome(land, "Woodland", Biomes.thicket.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
		ATGBiomes.addBiome(land, "Woodland", Biomes.birchForest.get(), tiers[5]);
	}
		
	private static void addMesaBiomesGroup()
	{
		// ########################
		// Mesa
		// ########################
		
		ATGBiomes.addBiome(land, "Mesa", Biomes.badlands.get(), tiers[0]);
		ATGBiomes.addBiome(land, "Mesa", Biomes.canyon.get(), tiers[0]);
		
		ATGBiomes.addGenMod(Biomes.canyonRavine.get(), new GenModCanyonRavine());
	}
	
	private static void addSwamplandBiomesGroup()
	{
		// ########################
		// Swampland
		// ########################
		
		// tier 1
		ATGBiomes.addBiome(coast, "Swampland", Biomes.wetland.get(), tiers[0]);
		
		// tier 2
		ATGBiomes.addBiome(coast, "Swampland", Biomes.marsh.get(), tiers[1]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.lushSwamp.get(), tiers[1]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.bayou.get(), tiers[1]);
					
		// tier 3
		ATGBiomes.replaceBiome(coast, "Swampland", BiomeGenBase.swampland, Biomes.swamplandNew.get(), tiers[2]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.fen.get(), tiers[2]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.bog.get(), tiers[2]);
		
		// tier 4
		ATGBiomes.addBiome(coast, "Swampland", Biomes.moor.get(), tiers[3]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.deadSwamp.get(), tiers[3]);
					
		// tier 5
		ATGBiomes.addBiome(coast, "Swampland", Biomes.quagmire.get(), tiers[4]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.sludgepit.get(), tiers[4]);
		
		// tier 6
		ATGBiomes.addBiome(coast, "Swampland", Biomes.ominousWoods.get(), tiers[5]);
		ATGBiomes.addBiome(coast, "Swampland", Biomes.silkglades.get(), tiers[5]);
			
		// swamp modifiers
		IGenMod swampmod = ATGBiomes.getGenMod(BiomeGenBase.swampland).get();
		ATGBiomes.addGenMod(Biomes.wetland.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.lushSwamp.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.bayou.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.swamplandNew.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.fen.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.bog.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.moor.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.deadSwamp.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.quagmire.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.sludgepit.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.ominousWoods.get(), swampmod);
		ATGBiomes.addGenMod(Biomes.silkglades.get(), swampmod);
	}
	
	private static void addBeachBiomesGroup()
	{
		// ########################
		// Beaches
		// ########################
		
		ATGBiomes.addBiome(coast, "Beach", Biomes.beachOvergrown.get(), 0.2);
		
		ATGBiomes.replaceBiome(coast, "Gravel Beach", ATGBiomes.getBiome("GravelBeach"), Biomes.beachGravel.get(), 1.0);
	}
		
	private static void addOceanBiomesGroup()
	{
		// ########################
		// Oceans
		// ########################
				
		ATGBiomes.addBiome(sea, "Ocean", Biomes.oceanCoral.get(), 0.05);
		ATGBiomes.addBiome(sea, "Ocean", Biomes.oceanKelp.get(), 0.1);
				
		ATGBiomes.replaceBiome(sea, "Deep Ocean", BiomeGenBase.ocean, Biomes.oceanAbyss.get(), 1.0);
		ATGBiomes.addGenMod(Biomes.oceanAbyss.get(), new GenModAbyss());
	}
}
