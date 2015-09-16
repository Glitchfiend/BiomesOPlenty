/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.core;

import net.minecraft.util.IIcon;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *  Many things Forestry use temperature and humidity of a biome to determine whether they can or how they can work or spawn at a given location.
 * 
 *  This enum concerns temperature.
 */
public enum EnumTemperature {
	NONE("None", "habitats/ocean"), ICY("Icy", "habitats/snow"), COLD("Cold", "habitats/taiga"),
	NORMAL("Normal", "habitats/plains"), WARM("Warm", "habitats/jungle"), HOT("Hot", "habitats/desert"), HELLISH("Hellish", "habitats/nether");

	public final String name;
	public final String iconIndex;

	private EnumTemperature(String name, String iconIndex) {
		this.name = name;
		this.iconIndex = iconIndex;
	}

	public String getName() {
		return this.name;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon() {
		return ForestryAPI.textureManager.getDefault(iconIndex);
	}

	/**
	 * Determines the EnumTemperature given a floating point representation of
	 * Minecraft temperature. Hellish biomes are handled based on their biome
	 * type - check BiomeHelper.isBiomeHellish.
	 * @param rawTemp raw temperature value
	 * @return EnumTemperature corresponding to value of rawTemp
	 */
	public static EnumTemperature getFromValue(float rawTemp) {
		if (rawTemp > 1.00f) {
			return HOT;
		}
		else if (rawTemp > 0.85f) {
			return WARM;
		}
		else if (rawTemp > 0.35f) {
			return NORMAL;
		}
		else if (rawTemp > 0.0f) {
			return COLD;
		}
		else {
			return ICY;
		}
	}

	public static EnumTemperature getFromBiome(BiomeGenBase biomeGenBase) {
		if (BiomeHelper.isBiomeHellish(biomeGenBase)) {
			return HELLISH;
		}
		return getFromValue(biomeGenBase.temperature);
	}

	public static EnumTemperature getFromBiome(BiomeGenBase biomeGenBase, int x, int y, int z) {
		if (BiomeHelper.isBiomeHellish(biomeGenBase)) {
			return HELLISH;
		}
		float temperature = biomeGenBase.getFloatTemperature(x, y, z);
		return getFromValue(temperature);
	}
}
