package tdwp_ftw.biomesop;

public class CommonProxy {
	public static String BLOCK_PNG = "/tdwp_ftw/biomesop/res/block.png";
	public static String ITEMS_PNG = "/tdwp_ftw/biomesop/res/items.png";
	public static String ARMOR_MUD1_PNG = "/tdwp_ftw/biomesop/res/armor/mud_1.png";
	public static String ARMOR_MUD2_PNG = "/tdwp_ftw/biomesop/res/armor/mud_2.png";
	public static String ARMOR_AMETHYST1_PNG = "/tdwp_ftw/biomesop/res/armor/amethyst_1.png";
	public static String ARMOR_AMETHYST2_PNG = "/tdwp_ftw/biomesop/res/armor/amethyst_2.png";
    
    // Client stuff
    public void registerRenderers() {
            // Nothing here as the server doesn't render graphics!
    }
    
    public int addArmor(String armor)
    {
        return 0;
    }
}