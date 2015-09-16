/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.arboriculture;

public enum EnumGermlingType {
	SAPLING("sapling"), BLOSSOM("blossom"), POLLEN("pollen"), GERMLING("germling"), NONE("none");

	public static final EnumGermlingType[] VALUES = values();
	
	private final String name;

	private EnumGermlingType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
