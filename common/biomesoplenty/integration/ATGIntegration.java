package biomesoplenty.integration;

import static biomesoplenty.configuration.configfile.BOPConfigurationBiomeGen.*;
import biomesoplenty.api.Biomes;
import biomesoplenty.integration.atg.*;
import net.minecraft.world.biome.BiomeGenBase;
import ttftcuts.atg.api.ATGBiomes;
import ttftcuts.atg.api.ATGBiomes.BiomeType;
import ttftcuts.atg.api.IGenMod;

public class ATGIntegration 
{	
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
		
		if (meadowGen) ATGBiomes.addSubBiome(Biomes.meadow.get(), Biomes.meadowForest.get(), 1.0);
		if (canyonGen) ATGBiomes.addSubBiome(Biomes.canyon.get(), Biomes.canyonRavine.get(), 1.0);
		if (shrublandGen) ATGBiomes.addSubBiome(Biomes.shrubland.get(), Biomes.shrublandForest.get(), 0.5);
		if (ominousWoodsGen) ATGBiomes.addSubBiome(Biomes.ominousWoods.get(), Biomes.ominousWoodsThick.get(), 0.5);
		if (pastureGen) ATGBiomes.addSubBiome(Biomes.pasture.get(), Biomes.pastureMeadow.get(), 1.0);
		if (pastureGen) ATGBiomes.addSubBiome(Biomes.pasture.get(), Biomes.pastureThin.get(), 1.0);
		if (timberGen) ATGBiomes.addSubBiome(Biomes.timber.get(), Biomes.timberThin.get(), 0.5);
		if (alpsGen) ATGBiomes.addSubBiome(Biomes.alps.get(), Biomes.alpsForest.get(), 1.0);
		if (alpsGen) ATGBiomes.addSubBiome(Biomes.alps.get(), Biomes.alpsBase.get(), 1.0);
		if (seasonalForestGen) ATGBiomes.addSubBiome(Biomes.seasonalForest.get(), Biomes.seasonalSpruceForest.get(), 1.0);
		if (fieldGen) ATGBiomes.addSubBiome(Biomes.field.get(), Biomes.fieldForest.get(), 1.0);
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
	    if (forestGen) ATGBiomes.replaceBiome(land, "Forest", BiomeGenBase.forest, Biomes.forestNew.get(), tiers[0]);
		
		// tier 2
	    if (birchForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.birchForest.get(), tiers[1]);
	    if (woodlandGen) ATGBiomes.addBiome(land, "Forest", Biomes.woodland.get(), tiers[1] * nb); // out of base group
	    if (spruceWoodsGen) ATGBiomes.addBiome(land, "Forest", Biomes.spruceWoods.get(), tiers[1] * nb); // out of base group
		
		// tier 3
	    if (coniferousForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.coniferousForest.get(), tiers[2] * nb); // out of base group
	    if (temperateRainforestGen) ATGBiomes.addBiome(land, "Forest", Biomes.temperateRainforest.get(), tiers[2] );
	    if (redwoodForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.redwoodForest.get(), tiers[2]);
	    if (mountainGen) ATGBiomes.addBiome(land, "Forest", Biomes.mountain.get(), tiers[2]);
		
		// tier 4
	    if (mapleWoodsGen) ATGBiomes.addBiome(land, "Forest", Biomes.mapleWoods.get(), tiers[3]);
	    if (seasonalForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.seasonalForest.get(), tiers[3]);
	    if (borealForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.borealForest.get(), tiers[3] * nb); // out of base group
	    if (deciduousForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.deciduousForest.get(), tiers[3]);
	    if (highlandGen) ATGBiomes.addBiome(land, "Forest", Biomes.highland.get(), tiers[3] * nb); // out of base group
		
		// tier 5
	    if (deadForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.deadForest.get(), tiers[4]);
	    if (groveGen) ATGBiomes.addBiome(land, "Forest", Biomes.grove.get(), tiers[4]);
	    if (timberGen) ATGBiomes.addBiome(land, "Forest", Biomes.timber.get(), tiers[4]);
	    if (thicketGen) ATGBiomes.addBiome(land, "Forest", Biomes.thicket.get(), tiers[4]);
	    if (shieldGen) ATGBiomes.addBiome(land, "Forest", Biomes.shield.get(), tiers[4]);
		
		// tier 6
	    if (fungiForestGen) ATGBiomes.addBiome(land, "Forest", Biomes.fungiForest.get(), tiers[5]);
	    if (cherryBlossomGroveGen) ATGBiomes.addBiome(land, "Forest", Biomes.cherryBlossomGrove.get(), tiers[5]);
	    if (mysticGroveGen) ATGBiomes.addBiome(land, "Forest", Biomes.mysticGrove.get(), tiers[5]);
	    if (hotSpringsGen) ATGBiomes.addBiome(land, "Forest", Biomes.hotSprings.get(), tiers[5]);
	    if (originValleyGen) ATGBiomes.addBiome(land, "Forest", Biomes.originValley.get(), tiers[5] * 0.5); // better at lowland?
	}
	
	private static void addJungleBiomesGroup()
	{
		// ########################
		// Jungle
		// ########################
		
		// tier 1
	    if (jungleGen) ATGBiomes.replaceBiome(land, "Jungle", BiomeGenBase.jungle, Biomes.jungleNew.get(), tiers[0]);
		
		// tier 2
	    if (tropicalRainforestGen) ATGBiomes.addBiome(land, "Jungle", Biomes.tropicalRainforest.get(), tiers[1]);
	    if (rainforestGen) ATGBiomes.addBiome(land, "Jungle", Biomes.rainforest.get(), tiers[1]);
		
		// tier 3
	    if (tropicsGen) ATGBiomes.addBiome(land, "Jungle", Biomes.tropics.get(), tiers[2]);
		
		// tier 4
	    if (bambooForestGen) ATGBiomes.addBiome(land, "Jungle", Biomes.bambooForest.get(), tiers[3]);
	    if (jadeCliffsGen) ATGBiomes.addBiome(land, "Jungle", Biomes.jadeCliffs.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
	    if (sacredSpringsGen) ATGBiomes.addBiome(land, "Jungle", Biomes.sacredSprings.get(), tiers[5]);
	}
	
	private static void addPlainsBiomesGroup()
	{
		// ########################
		// Plains
		// ########################
		
		// tier 1
	    if (plainsGen) ATGBiomes.replaceBiome(land, "Plains", BiomeGenBase.plains, Biomes.plainsNew.get(), tiers[0]);
		
		// tier 2
	    if (shrublandGen) ATGBiomes.addBiome(land, "Plains", Biomes.shrubland.get(), tiers[1] * nb); // out of base group
	    if (chaparralGen) ATGBiomes.addBiome(land, "Plains", Biomes.chaparral.get(), tiers[1]);
	    if (prairieGen) ATGBiomes.addBiome(land, "Plains", Biomes.prairie.get(), tiers[1]);
		
		// tier 3
	    if (fieldGen) ATGBiomes.addBiome(land, "Plains", Biomes.field.get(), tiers[2]);
	    if (grasslandGen) ATGBiomes.addBiome(land, "Plains", Biomes.grassland.get(), tiers[2]);
		
		// tier 4
	    if (pastureGen) ATGBiomes.addBiome(land, "Plains", Biomes.pasture.get(), tiers[3]);
	    if (meadowGen) ATGBiomes.addBiome(land, "Plains", Biomes.meadow.get(), tiers[3]);
		
		// tier 5
	    if (orchardGen) ATGBiomes.addBiome(land, "Plains", Biomes.orchard.get(), tiers[4]);
	    if (overgrownGreensGen) ATGBiomes.addBiome(land, "Plains", Biomes.overgrownGreens.get(), tiers[4]);
	    if (lavenderFieldsGen) ATGBiomes.addBiome(land, "Plains", Biomes.lavenderFields.get(), tiers[4]);
		
		// tier 6
	    if (gardenGen) ATGBiomes.addBiome(land, "Plains", Biomes.garden.get(), tiers[5]);
	}
	
	private static void addIcePlainsBiomesGroup()
	{
		// ########################
		// Ice Plains
		// ########################
		
		// tier 1
	    if (alpsGen) ATGBiomes.addBiome(land, "Ice Plains", Biomes.alps.get(), tiers[0]);
		
		// tier 2
		
		// tier 3
	    if (polarGen) ATGBiomes.addBiome(land, "Ice Plains", Biomes.polar.get(), tiers[2]);
	    if (glacierGen) ATGBiomes.addBiome(land, "Ice Plains", Biomes.glacier.get(), tiers[2]);
	    if (glacierGen) ATGBiomes.addGenMod(Biomes.glacier.get(), new GenModGlacier());
	    if (arcticGen) ATGBiomes.addBiome(land, "Ice Plains", Biomes.arctic.get(), tiers[2]);
		
		// tier 4
		
		// tier 5
		
		// tier 6
	    if (icyHillsGen) ATGBiomes.addBiome(land, "Ice Plains", Biomes.icyHills.get(), tiers[5]);
	}
	
	private static void addTaigaBiomesGroup()
	{
		// ########################
		// Taiga
		// ########################
				
		// tier 1
	    if (taigaGen) ATGBiomes.replaceBiome(land, "Taiga", BiomeGenBase.taiga, Biomes.taigaNew.get(), tiers[0]);
		
		// tier 2
	    if (alpsGen) ATGBiomes.addBiome(land, "Taiga", Biomes.alpsForest.get(), tiers[1]);
	    if (coniferousForestSnowGen) ATGBiomes.addBiome(land, "Taiga", Biomes.coniferousForestSnow.get(), tiers[1]);
	    if (frostForestGen) ATGBiomes.addBiome(land, "Taiga", Biomes.frostForest.get(), tiers[1]);
		
		// tier 3
		
		// tier 4
		
		// tier 5
	    if (deadForestSnowGen) ATGBiomes.addBiome(land, "Taiga", Biomes.deadForestSnow.get(), tiers[4]);
		
		// tier 6
	    if (icyHillsGen) ATGBiomes.addBiome(land, "Taiga", Biomes.icyHills.get(), tiers[5] * nb);
	}
	
	private static void addDesertBiomesGroup()
	{
		// ########################
		// Desert
		// ########################
		
		// tier 1
	    if (desertGen) ATGBiomes.replaceBiome(land, "Desert", BiomeGenBase.desert, Biomes.desertNew.get(), tiers[0]);
		
		// tier 2
	    if (dunesGen) ATGBiomes.addBiome(land, "Desert", Biomes.dunes.get(), tiers[1]);
		
		// tier 3
	    if (outbackGen) ATGBiomes.addBiome(land, "Desert", Biomes.outback.get(), tiers[2]);
		
		// tier 4
	    if (lushDesertGen) ATGBiomes.addBiome(land, "Desert", Biomes.lushDesert.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
	    if (oasisGen) ATGBiomes.addBiome(land, "Desert", Biomes.oasis.get(), tiers[5]);
	}
	
	private static void addShrublandBiomesGroup()
	{
		// ########################
		// Shrubland
		// ########################
		
		// tier 1
	    if (shrublandGen) ATGBiomes.replaceBiome(land, "Shrubland", ATGBiomes.getBiome("Shrubland"), Biomes.shrubland.get(), tiers[0]);
		
		// tier 2
		
		// tier 3
	    if (heathlandGen) ATGBiomes.addBiome(land, "Shrubland", Biomes.heathland.get(), tiers[2]);
		
		// tier 4
		
		// tier 5
	    if (thicketGen) ATGBiomes.addBiome(land, "Shrubland", Biomes.thicket.get(), tiers[4]);
		
		// tier 6
		
	}
	
	private static void addBorealForestBiomesGroup()
	{
		// ########################
		// Boreal Forest
		// ########################
		
		// tier 1
	    if (borealForestGen) ATGBiomes.replaceBiome(land, "Boreal Forest", ATGBiomes.getBiome("BorealForest"), Biomes.borealForest.get(), tiers[0]);
		
		// tier 2
	    if (coniferousForestGen) ATGBiomes.addBiome(land, "Boreal Forest", Biomes.coniferousForest.get(), tiers[1]);
	    if (spruceWoodsGen) ATGBiomes.addBiome(land, "Boreal Forest", Biomes.spruceWoods.get(), tiers[1]);
		
		// tier 3
	    if (forestGen) ATGBiomes.addBiome(land, "Boreal Forest", Biomes.forestNew.get(), tiers[2] * nb); // out of base group
		
		// tier 4
	    if (shieldGen) ATGBiomes.addBiome(land, "Boreal Forest", Biomes.shield.get(), tiers[3]);
	    if (timberGen) ATGBiomes.addBiome(land, "Boreal Forest", Biomes.timber.get(), tiers[3]);
		
		// tier 5
	    if (deadForestGen) ATGBiomes.addBiome(land, "Boreal Forest", Biomes.deadForest.get(), tiers[4]);
		
		// tier 6
		
	}
		
	private static void addTundraBiomesGroup()
	{
		// ########################
		// Tundra
		// ########################
		
		// tier 1
	    if (tundraGen) ATGBiomes.replaceBiome(land, "Tundra", ATGBiomes.getBiome("Tundra"), Biomes.tundra.get(), tiers[0]);
		
		// tier 2
				
		// tier 3
	    ATGBiomes.addBiome(land, "Tundra", BiomeGenBase.icePlains, tiers[2]);
		
		// tier 4
		
		// tier 5
	    if (borealForestGen) ATGBiomes.addBiome(land, "Tundra", Biomes.borealForest.get(), tiers[4] * nb); // out of base group
		
		// tier 6
	    if (steppeGen) ATGBiomes.addBiome(land, "Tundra", Biomes.steppe.get(), tiers[5]);
	}
	
	private static void addSteppeBiomesGroup()
	{
		// ########################
		// Steppe
		// ########################
		
		// tier 1
		
		// tier 2
		
		// tier 3
	    if (cragGen) ATGBiomes.addBiome(land, "Steppe", Biomes.crag.get(), tiers[2]);
		
		// tier 4
		
		// tier 5
	    if (mountainGen) ATGBiomes.addBiome(land, "Steppe", Biomes.mountain.get(), tiers[4]);
		
		// tier 6
	    if (deadForestGen) ATGBiomes.addBiome(land, "Steppe", Biomes.deadForest.get(), tiers[5]);
	}
	
	private static void addSavannahBiomesGroup()
	{
		// ########################
		// Savannah
		// ########################
		
		// tier 1
	    if (savannaGen) ATGBiomes.replaceBiome(land, "Savanna", ATGBiomes.getBiome("Savanna"), Biomes.savanna.get(), 1.0);
		
		// tier 2
		
		// tier 3
	    if (scrublandGen) ATGBiomes.addBiome(land, "Savanna", Biomes.scrubland.get(), tiers[2]);
	    if (outbackGen) ATGBiomes.addBiome(land, "Savanna", Biomes.outback.get(), tiers[2] * nb); // out of base group
	    if (steppeGen) ATGBiomes.addBiome(land, "Savanna", Biomes.steppe.get(), tiers[2]);
		
		// tier 4
	    if (lushDesertGen) ATGBiomes.addBiome(land, "Savanna", Biomes.lushDesert.get(), tiers[3]);
		
		// tier 5
	    if (brushlandGen) ATGBiomes.addBiome(land, "Savanna", Biomes.brushland.get(), tiers[4]);
		
		// tier 6
		
	}
	
	private static void addTropicalShrublandBiomesGroup()
	{
		// ########################
		// Tropical Shrubland
		// ########################
		
		// tier 1
		
		// tier 2
	    if (tropicsGen) ATGBiomes.addBiome(land, "Tropical Shrubland", Biomes.tropics.get(), tiers[1]);
		
		// tier 3
		
		// tier 4
	    if (bambooForestGen) ATGBiomes.addBiome(land, "Tropical Shrubland", Biomes.bambooForest.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
	    if (sacredSpringsGen) ATGBiomes.addBiome(land, "Tropical Shrubland", Biomes.sacredSprings.get(), tiers[5]);
	}
		
	private static void addWoodlandBiomesGroup()
	{
		// ########################
		// Woodland
		// ########################
		
		// tier 1
		
		// tier 2
	    if (woodlandGen) ATGBiomes.addBiome(land, "Woodland", Biomes.woodland.get(), tiers[1]);
		
		// tier 3
		
		// tier 4
	    if (thicketGen) ATGBiomes.addBiome(land, "Woodland", Biomes.thicket.get(), tiers[3]);
		
		// tier 5
		
		// tier 6
	    if (birchForestGen) ATGBiomes.addBiome(land, "Woodland", Biomes.birchForest.get(), tiers[5]);
	}
		
	private static void addMesaBiomesGroup()
	{
		// ########################
		// Mesa
		// ########################
		
	    if (badlandsGen) ATGBiomes.addBiome(land, "Mesa", Biomes.badlands.get(), tiers[0]);
	    if (canyonGen) ATGBiomes.addBiome(land, "Mesa", Biomes.canyon.get(), tiers[0]);
		
	    if (canyonGen) ATGBiomes.addGenMod(Biomes.canyonRavine.get(), new GenModCanyonRavine());
	}
	
	private static void addSwamplandBiomesGroup()
	{
		// ########################
		// Swampland
		// ########################
		
		// tier 1
	    if (wetlandGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.wetland.get(), tiers[0]);
		
		// tier 2
	    if (marshGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.marsh.get(), tiers[1]);
	    if (lushSwampGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.lushSwamp.get(), tiers[1]);
	    if (bayouGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.bayou.get(), tiers[1]);
					
		// tier 3
	    if (swamplandGen) ATGBiomes.replaceBiome(coast, "Swampland", BiomeGenBase.swampland, Biomes.swamplandNew.get(), tiers[2]);
	    if (fenGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.fen.get(), tiers[2]);
	    if (bogGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.bog.get(), tiers[2]);
		
		// tier 4
	    if (moorGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.moor.get(), tiers[3]);
	    if (deadSwampGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.deadSwamp.get(), tiers[3]);
					
		// tier 5
	    if (quagmireGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.quagmire.get(), tiers[4]);
	    if (sludgepitGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.sludgepit.get(), tiers[4]);
		
		// tier 6
	    if (ominousWoodsGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.ominousWoods.get(), tiers[5]);
	    if (silkgladesGen) ATGBiomes.addBiome(coast, "Swampland", Biomes.silkglades.get(), tiers[5]);
			
		// swamp modifiers
		IGenMod swampmod = ATGBiomes.getGenMod(BiomeGenBase.swampland).get();
		if (wetlandGen) ATGBiomes.addGenMod(Biomes.wetland.get(), swampmod);
		if (lushSwampGen) ATGBiomes.addGenMod(Biomes.lushSwamp.get(), swampmod);
		if (bayouGen) ATGBiomes.addGenMod(Biomes.bayou.get(), swampmod);
		if (swamplandGen) ATGBiomes.addGenMod(Biomes.swamplandNew.get(), swampmod);
		if (fenGen) ATGBiomes.addGenMod(Biomes.fen.get(), swampmod);
		if (bogGen) ATGBiomes.addGenMod(Biomes.bog.get(), swampmod);
		if (moorGen) ATGBiomes.addGenMod(Biomes.moor.get(), swampmod);
		if (deadSwampGen) ATGBiomes.addGenMod(Biomes.deadSwamp.get(), swampmod);
		if (quagmireGen) ATGBiomes.addGenMod(Biomes.quagmire.get(), swampmod);
		if (sludgepitGen) ATGBiomes.addGenMod(Biomes.sludgepit.get(), swampmod);
		if (ominousWoodsGen) ATGBiomes.addGenMod(Biomes.ominousWoods.get(), swampmod);
		if (silkgladesGen) ATGBiomes.addGenMod(Biomes.silkglades.get(), swampmod);
	}
	
	private static void addBeachBiomesGroup()
	{
		// ########################
		// Beaches
		// ########################
		
	    if (overgrownBeachGen) ATGBiomes.addBiome(coast, "Beach", Biomes.beachOvergrown.get(), 0.2);
		
	    if (gravelBeachGen) ATGBiomes.replaceBiome(coast, "Gravel Beach", ATGBiomes.getBiome("GravelBeach"), Biomes.beachGravel.get(), 1.0);
	}
		
	private static void addOceanBiomesGroup()
	{
		// ########################
		// Oceans
		// ########################
				
	    if (coralReefGen) ATGBiomes.addBiome(sea, "Ocean", Biomes.oceanCoral.get(), 0.05);
	    if (kelpForestGen) ATGBiomes.addBiome(sea, "Ocean", Biomes.oceanKelp.get(), 0.1);
				
	    if (oceanicAbyssGen) ATGBiomes.replaceBiome(sea, "Deep Ocean", BiomeGenBase.ocean, Biomes.oceanAbyss.get(), 1.0);
	    if (oceanicAbyssGen) ATGBiomes.addGenMod(Biomes.oceanAbyss.get(), new GenModAbyss());
	}
}
