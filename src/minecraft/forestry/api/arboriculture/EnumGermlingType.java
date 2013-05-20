package forestry.api.arboriculture;

public enum EnumGermlingType {
	NONE("None"), SAPLING("Sapling"), BLOSSOM("Blossom"), POLLEN("Pollen"), GERMLING("Germling");

	public static final EnumGermlingType[] VALUES = values();
	
	String name;

	private EnumGermlingType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
