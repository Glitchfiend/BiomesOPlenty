/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.core;

/**
 *  Many things Forestry use temperature and humidity of a biome to determine whether they can or how they can work or spawn at a given location.
 * 
 *  This enum concerns humidity. 
 */
public enum EnumHumidity {
	ARID("Arid"), NORMAL("Normal"), DAMP("Damp");

	public final String name;

	private EnumHumidity(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Determines the EnumHumidity given a floating point representation of Minecraft Rainfall.
	 * To check if rainfall is possible in a biome, use BiomeHelper.canRainOrSnow().
	 * @param rawHumidity raw rainfall value
	 * @return EnumHumidity corresponding to rainfall value
	 */
	public static EnumHumidity getFromValue(float rawHumidity) {
		if (rawHumidity > 0.85f) { // matches BiomeGenBase.isHighHumidity()
			return DAMP;
		}
		else if (rawHumidity >= 0.3f) {
			return NORMAL;
		}
		else {
			return ARID;
		}
	}
}
