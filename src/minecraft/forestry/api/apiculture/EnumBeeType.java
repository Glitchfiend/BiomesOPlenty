package forestry.api.apiculture;

import java.util.Locale;

public enum EnumBeeType {
	NONE, PRINCESS, QUEEN, DRONE;

	String name;

	private EnumBeeType() {
		this.name = "bees." + this.toString().toLowerCase(Locale.ENGLISH);
	}

	public String getName() {
		return name;
	}
}
