package biomesoplenty.common.core;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeListEntry;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeTemperatureType;
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
		registerBiome(new BOPBiomeListEntry(BiomeGenBase.desert, BOPBiomeTemperatureType.HOT));
		registerBiome(new BOPBiomeListEntry(BiomeGenBase.desert, BOPBiomeTemperatureType.HOT)); 
		registerBiome(new BOPBiomeListEntry(BiomeGenBase.desert, BOPBiomeTemperatureType.HOT)); 
		registerBiome(new BOPBiomeListEntry(BiomeGenBase.field_150588_X, BOPBiomeTemperatureType.HOT));
		registerBiome(new BOPBiomeListEntry(BiomeGenBase.field_150588_X, BOPBiomeTemperatureType.HOT));
		registerBiome(new BOPBiomeListEntry(BiomeGenBase.plains, BOPBiomeTemperatureType.HOT));
		
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.forest, BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.field_150585_R, BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.extremeHills, BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.plains, BOPBiomeTemperatureType.WARM));
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.field_150583_P, BOPBiomeTemperatureType.WARM)); 
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.swampland, BOPBiomeTemperatureType.WARM));
        
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.forest, BOPBiomeTemperatureType.COOL)); 
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.extremeHills, BOPBiomeTemperatureType.COOL));  
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.taiga, BOPBiomeTemperatureType.COOL)); 
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.plains, BOPBiomeTemperatureType.COOL));

        registerBiome(new BOPBiomeListEntry(BiomeGenBase.icePlains, BOPBiomeTemperatureType.ICY));  
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.icePlains, BOPBiomeTemperatureType.ICY)); 
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.icePlains, BOPBiomeTemperatureType.ICY));  
        registerBiome(new BOPBiomeListEntry(BiomeGenBase.field_150584_S, BOPBiomeTemperatureType.ICY));
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
