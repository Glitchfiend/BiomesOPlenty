package forestry.api.core;

import java.util.ArrayList;

/**
 *  Many things Forestry use temperature and humidity of a biome to determine whether they can or how they can work or spawn at a given location.
 * 
 *  This enum concerns humidity. 
 */
public enum EnumHumidity {
	ARID("Arid"), NORMAL("Normal"), DAMP("Damp");

	/**
	 * Populated by Forestry with vanilla biomes. Add additional arid biomes here. (ex. desert)
	 */
	public static ArrayList<Integer> aridBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional damp biomes here. (ex. jungle)
	 */
	public static ArrayList<Integer> dampBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional normal biomes here.
	 */
	public static ArrayList<Integer> normalBiomeIds = new ArrayList<Integer>();

	public final String name;

	private EnumHumidity(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static ArrayList<Integer> getBiomeIds(EnumHumidity humidity) {
		switch (humidity) {
		case ARID:
			return aridBiomeIds;
		case DAMP:
			return dampBiomeIds;
		case NORMAL:
		default:
			return normalBiomeIds;
		}
	}
}
