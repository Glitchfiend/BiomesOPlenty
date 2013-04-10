package forestry.api.arboriculture;

public enum EnumGermlingType {
	SAPLING("Sapling"), BLOSSOM("Blossom"), POLLEN("Pollen"), GERMLING("Germling");

	String name;

	private EnumGermlingType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
