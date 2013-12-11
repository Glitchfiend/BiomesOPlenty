package forestry.api.core;

import java.util.ArrayList;

import net.minecraft.util.Icon;
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

	/**
	 * Populated by Forestry with vanilla biomes. Add additional icy/snow biomes here. (ex. snow plains)
	 */
	public static ArrayList<Integer> icyBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional cold biomes here. (ex. taiga)
	 */
	public static ArrayList<Integer> coldBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional normal biomes here. (ex. forest, plains)
	 */
	public static ArrayList<Integer> normalBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional warm biomes here. (ex. jungle)
	 */
	public static ArrayList<Integer> warmBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional hot biomes here. (ex. desert)
	 */
	public static ArrayList<Integer> hotBiomeIds = new ArrayList<Integer>();
	/**
	 * Populated by Forestry with vanilla biomes. Add additional hellish biomes here. (ex. nether)
	 */
	public static ArrayList<Integer> hellishBiomeIds = new ArrayList<Integer>();

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
	public Icon getIcon() {
		return ForestryAPI.textureManager.getDefault(iconIndex);
	}

	public static ArrayList<Integer> getBiomeIds(EnumTemperature temperature) {

		switch (temperature) {
		case ICY:
			return icyBiomeIds;
		case COLD:
			return coldBiomeIds;
		case WARM:
			return warmBiomeIds;
		case HOT:
			return hotBiomeIds;
		case HELLISH:
			return hellishBiomeIds;
		case NORMAL:
		default:
			return normalBiomeIds;
		}

	}
}
