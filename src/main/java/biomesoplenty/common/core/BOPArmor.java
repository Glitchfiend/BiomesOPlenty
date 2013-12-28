package biomesoplenty.common.core;

import static biomesoplenty.common.core.BOPItems.registerItem;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.armor.ItemAmethystArmor;
import biomesoplenty.common.armor.ItemFlippers;
import biomesoplenty.common.armor.ItemFlowerBand;
import biomesoplenty.common.armor.ItemMuddyArmor;
import biomesoplenty.common.armor.ItemWadingBoots;

public class BOPArmor 
{
	public static void init()
	{
		initializeArmor();
	}
	
	private static void initializeArmor()
	{
		registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 0).setUnlocalizedName("helmetMud"));
		registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 1).setUnlocalizedName("chestplateMud"));
		registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 2).setUnlocalizedName("leggingsMud"));
		registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 3).setUnlocalizedName("bootsMud"));
		
		registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 0).setUnlocalizedName("helmetAmethyst"));
		registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 1).setUnlocalizedName("chestplateAmethyst"));
		registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 2).setUnlocalizedName("leggingsAmethyst"));
		registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 3).setUnlocalizedName("bootsAmethyst"));
		
		registerItem(new ItemFlowerBand(BOPItemHelper.armorMaterialUnprotective, BiomesOPlenty.proxy.addArmor("flowerBand"), 0).setUnlocalizedName("flowerBand"));
		
		registerItem(new ItemFlippers(BOPItemHelper.armorMaterialUnprotective, BiomesOPlenty.proxy.addArmor("flippers"), 3).setUnlocalizedName("flippers"));
		
		registerItem(new ItemWadingBoots(BOPItemHelper.armorMaterialUnprotective, BiomesOPlenty.proxy.addArmor("wadingBoots"), 3).setUnlocalizedName("wadingBoots"));
	}
}
