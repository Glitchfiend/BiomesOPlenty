package biomesoplenty.common.integration;

import net.minecraft.world.biome.BiomeGenBase;
import ttftcuts.atg.api.ATGBiomes;
import ttftcuts.atg.api.ATGBiomes.BiomeType;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.common.configuration.BOPConfigurationBiomeGen;
import cpw.mods.fml.common.Loader;

public class ATGIntegration 
{
	private enum BiomeGroup
	{
		FOREST(BiomeType.LAND), JUNGLE(BiomeType.LAND), PLAINS(BiomeType.LAND), ICE_PLAINS(BiomeType.LAND), TAIGA(BiomeType.LAND), DESERT(BiomeType.LAND), SHRUBLAND(BiomeType.LAND), BOREAL_FOREST(BiomeType.LAND), 
		TUNDRA(BiomeType.LAND), STEPPE(BiomeType.LAND), SAVANNA(BiomeType.LAND), TROPICAL_SHRUBLAND(BiomeType.LAND), WOODLAND(BiomeType.LAND), MESA(BiomeType.LAND), SWAMPLAND(BiomeType.LAND), OCEAN(BiomeType.SEA);
		
		public final BiomeType biomeType;
		
		private BiomeGroup(BiomeType biomeType)
		{
			this.biomeType = biomeType;
		}
	}
	
	private static double nb = 0.1; // Modifier used for biomes that have their own group
	private static double[] tiers = { 1.0, 0.5, 0.3, 0.2, 0.1, 0.04 }; 
	
	protected static void init()
	{
		addLandBiomes();
		addOceanBiomes();
	}
	
	private static void addLandBiomes()
	{
		//Forest
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.woodland, tiers[1] * nb); //Out of base group
		
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.coniferousForest, tiers[2] * nb);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.temperateRainforest, tiers[2]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.redwoodForest, tiers[2]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.mountain, tiers[2]);
		
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.mapleWoods, tiers[3]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.seasonalForest, tiers[3]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.borealForest, tiers[3] * nb); //Out of base group
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.deciduousForest, tiers[3]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.highland, tiers[3] * nb); //Out of base group
		
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.deadForest, tiers[4]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.grove, tiers[4]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.thicket, tiers[4]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.shield, tiers[4]);
		
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.fungiForest, tiers[5]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.cherryBlossomGrove, tiers[5]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.mysticGrove, tiers[5]);
		registerATGBiome(BiomeGroup.FOREST, BOPCBiomes.originValley, tiers[5] * 0.5F); //Better at low land?
		
		//Jungle
		registerATGBiome(BiomeGroup.JUNGLE, BOPCBiomes.tropicalRainforest, tiers[1]);
		registerATGBiome(BiomeGroup.JUNGLE, BOPCBiomes.rainforest, tiers[1]);
		
		registerATGBiome(BiomeGroup.JUNGLE, BOPCBiomes.tropics, tiers[2]);
		
		registerATGBiome(BiomeGroup.JUNGLE, BOPCBiomes.bambooForest, tiers[3]);
		registerATGBiome(BiomeGroup.JUNGLE, BOPCBiomes.jadeCliffs, tiers[3]);
		
		registerATGBiome(BiomeGroup.JUNGLE, BOPCBiomes.sacredSprings, tiers[5]);
		
		//Plains
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.shrubland, tiers[1] * nb); //Out of base group
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.chaparral, tiers[1]);
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.prairie, tiers[1]);
		
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.grassland, tiers[2]);
		
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.meadow, tiers[3]);
		
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.lavenderFields, tiers[4]);
		
		registerATGBiome(BiomeGroup.PLAINS, BOPCBiomes.garden, tiers[5]);
		
		//Ice Plains
		registerATGBiome(BiomeGroup.ICE_PLAINS, BOPCBiomes.alps, tiers[0]);
		
		registerATGBiome(BiomeGroup.ICE_PLAINS, BOPCBiomes.glacier, tiers[2]);
		registerATGBiome(BiomeGroup.ICE_PLAINS, BOPCBiomes.arctic, tiers[2]);
	}
	
	private static void addOceanBiomes()
	{
		registerATGBiome(BiomeGroup.OCEAN, BOPCBiomes.coralReef, 0.05F);
		registerATGBiome(BiomeGroup.OCEAN, BOPCBiomes.kelpForest, 0.1F);
	}
	
	private static void addModifiers()
	{
		
	}
	
	private static void registerATGBiome(BiomeGroup group, BiomeGenBase biome, double weight)
	{
		if (BOPConfigurationBiomeGen.config.get("Overworld Biomes To Generate", biome.biomeName, true).getBoolean(false))
		{
			ATGBiomes.addBiome(group.biomeType, group.toString(), biome, weight);
		}
	}
	
	public static void registerATGSubBiome(BiomeGenBase parent, BiomeGenBase subBiome)
	{
		/*if (Loader.isModLoaded("ATG"))
		{
			ATGBiomes.addSubBiome(parent, subBiome, 1.0F);
		}*/
	}
}
