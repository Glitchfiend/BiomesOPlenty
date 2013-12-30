package biomesoplenty.common.core;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeListEntry;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeTemperatureType;
import biomesoplenty.common.biomes.BiomeGenSacredSprings;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.world.WorldGenFieldAssociation;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.common.world.decoration.ForcedDecorators;

public class BOPBiomes 
{
	public static WorldTypeBOP worldTypeBOP;
	
	public static void init()
	{
		registerBiomes();
		
		WorldGenFieldAssociation.init();
		ForcedDecorators.init();
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
        
        registerBiome(new BOPBiomeListEntry(new BiomeGenSacredSprings(BOPConfigurationIDs.sacredSpringsID).setBiomeName("Sacred Springs"), BOPBiomeTemperatureType.WARM));
	}
	
	public static void registerBiome(BOPBiomeListEntry biome)
	{
	    BOPBiomeHelper.registerBiome(biome.biome, biome.biome.biomeName);
	    addBiomeToList(biome);
	}
	
	public static void addBiomeToList(BOPBiomeListEntry biome)
	{
	    BOPBiomeHelper.getCorrespondingTemperatureTypeList(biome.temperatureType).add(biome.biome);
	}
}
