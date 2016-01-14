package thaumcraft.api.golems.seals;

public interface ISealGui {
	final int CAT_PRIORITY = 0;
	final int CAT_FILTER = 1;	
	final int CAT_AREA = 2;
	final int CAT_TOGGLES = 3;
	final int CAT_TAGS = 4;
	
	public int[] getGuiCategories();
	
}
