package biomesoplenty.configuration;

import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.armor.ArmorAmethyst;
import biomesoplenty.armor.ArmorMuddy;
import biomesoplenty.items.ItemAncientStaff;
import biomesoplenty.items.ItemBOP;
import biomesoplenty.items.ItemBOPAxe;
import biomesoplenty.items.ItemBOPHoe;
import biomesoplenty.items.ItemBOPPickaxe;
import biomesoplenty.items.ItemBOPRecord;
import biomesoplenty.items.ItemBOPRecordMud;
import biomesoplenty.items.ItemBOPSpade;
import biomesoplenty.items.ItemBOPSword;
import biomesoplenty.items.ItemBamboo;
import biomesoplenty.items.ItemBarley;
import biomesoplenty.items.ItemBush;
import biomesoplenty.items.ItemCattail;
import biomesoplenty.items.ItemEnderporter;
import biomesoplenty.items.ItemMediumGrass;
import biomesoplenty.items.ItemShortGrass;
import biomesoplenty.items.ItemShroomPowder;
import biomesoplenty.items.ItemSprout;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BOPItems {
	
	public static Item shroomPowder;
	public static Item mudBall;
	public static Item mudBrick;
//	public static Item cattailItem;
//	public static Item barleyItem;
//	public static Item shortGrassItem;
//	public static Item mediumGrassItem;
//	public static Item bushItem;
//	public static Item sproutItem;
	public static Item mossItem;
	public static Item ashes;
	public static Item bambooItem;
	public static Item ancientStaff;
	public static Item ancientStaffHandle;
	public static Item ancientStaffPole;
	public static Item ancientStaffTopper;
	public static Item enderporter;
	public static Item amethyst;
	public static Item bopDisc;
	public static Item bopDiscMud;

	public static Item swordMud;
	public static Item shovelMud;
	public static Item pickaxeMud;
	public static Item axeMud;
	public static Item hoeMud;
	public static Item helmetMud;
	public static Item chestplateMud;
	public static Item leggingsMud;
	public static Item bootsMud;

	public static Item swordAmethyst;
	public static Item shovelAmethyst;
	public static Item pickaxeAmethyst;
	public static Item axeAmethyst;
	public static Item hoeAmethyst;
	public static Item helmetAmethyst;
	public static Item chestplateAmethyst;
	public static Item leggingsAmethyst;
	public static Item bootsAmethyst;
	
	// Material declaration
	public static EnumArmorMaterial EnumArmorMaterialMud;
	public static EnumToolMaterial EnumToolMaterialMud;
	public static EnumArmorMaterial EnumArmorMaterialAmethyst;
	public static EnumToolMaterial EnumToolMaterialAmethyst;
	
	public static void init()
	{
		// Material declaration
		EnumArmorMaterialMud = EnumHelper.addArmorMaterial("MUD", 2, new int[]{1, 1, 1, 1}, 5);
		EnumToolMaterialMud = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0, 1);
		EnumArmorMaterialAmethyst = EnumHelper.addArmorMaterial("AMETHYST", 40, new int[]{6, 12, 10, 6}, 20);
		EnumToolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5, 16);
		
		// Item declaration
		shroomPowder = (new ItemShroomPowder(BOPConfiguration.shroomPowderID, 1, 0.5F, false)).setPotionEffect(Potion.confusion.id, 30, 0, 0.6F).setAlwaysEdible().setUnlocalizedName("shroomPowder").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		mudBall = (new ItemBOP(BOPConfiguration.mudBallID, 0)).setUnlocalizedName("mudBall").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		mudBrick = (new ItemBOP(BOPConfiguration.mudBrickID, 1)).setUnlocalizedName("mudBrick").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		bambooItem = (new ItemBamboo(BOPConfiguration.bambooItemID, Blocks.bamboo.get())).setUnlocalizedName("bambooItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
//		cattailItem = (new ItemCattail(BOPConfiguration.cattailItemID, BOPBlocks.cattail)).setUnlocalizedName("cattailItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
//		barleyItem = (new ItemBarley(BOPConfiguration.barleyItemID, BOPBlocks.barley)).setUnlocalizedName("barleyItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
//		shortGrassItem = (new ItemShortGrass(BOPConfiguration.shortGrassItemID, BOPBlocks.shortGrass)).setUnlocalizedName("shortGrassItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
//		mediumGrassItem = (new ItemMediumGrass(BOPConfiguration.mediumGrassItemID, BOPBlocks.mediumGrass)).setUnlocalizedName("mediumGrassItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
//		bushItem = (new ItemBush(BOPConfiguration.bushItemID, BOPBlocks.bush)).setUnlocalizedName("bushItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
//		sproutItem = (new ItemSprout(BOPConfiguration.sproutItemID, BOPBlocks.sprout)).setUnlocalizedName("sproutItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		mossItem = (new ItemBOP(BOPConfiguration.mossItemID, 2)).setUnlocalizedName("mossItem").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		ancientStaff = new ItemAncientStaff(BOPConfiguration.ancientStaffID).setUnlocalizedName("ancientStaff").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		enderporter = new ItemEnderporter(BOPConfiguration.enderporterID).setUnlocalizedName("enderporter").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		ashes = (new ItemBOP(BOPConfiguration.ashesID, 3)).setUnlocalizedName("ashes").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		amethyst = (new ItemBOP(BOPConfiguration.amethystID, 4)).setUnlocalizedName("amethyst").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		ancientStaffHandle = (new ItemBOP(BOPConfiguration.ancientStaffHandleID, 5)).setUnlocalizedName("ancientStaffHandle").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		ancientStaffPole = (new ItemBOP(BOPConfiguration.ancientStaffPoleID, 6)).setUnlocalizedName("ancientStaffPole").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		ancientStaffTopper = (new ItemBOP(BOPConfiguration.ancientStaffTopperID, 7)).setUnlocalizedName("ancientStaffTopper").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		bopDisc = (new ItemBOPRecord(BOPConfiguration.bopDiscID, "bopdisc")).setUnlocalizedName("bopDisc").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		bopDiscMud = (new ItemBOPRecordMud(BOPConfiguration.bopDiscMudID, "bopdiscmud")).setUnlocalizedName("bopDiscMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		swordMud = (new ItemBOPSword(BOPConfiguration.swordMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("swordMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		shovelMud = (new ItemBOPSpade(BOPConfiguration.shovelMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("shovelMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		pickaxeMud = (new ItemBOPPickaxe(BOPConfiguration.pickaxeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("pickaxeMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		axeMud = (new ItemBOPAxe(BOPConfiguration.axeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("hatchetMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		hoeMud = (new ItemBOPHoe(BOPConfiguration.hoeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("hoeMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		helmetMud = (new ArmorMuddy(BOPConfiguration.helmetMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 0)).setUnlocalizedName("helmetMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		chestplateMud = (new ArmorMuddy(BOPConfiguration.chestplateMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 1)).setUnlocalizedName("chestplateMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		leggingsMud = (new ArmorMuddy(BOPConfiguration.leggingsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 2)).setUnlocalizedName("leggingsMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		bootsMud = (new ArmorMuddy(BOPConfiguration.bootsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 3)).setUnlocalizedName("bootsMud").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);

		swordAmethyst = (new ItemBOPSword(BOPConfiguration.swordAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("swordAmethyst").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		shovelAmethyst = (new ItemBOPSpade(BOPConfiguration.shovelAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("shovelAmethyst").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		pickaxeAmethyst = (new ItemBOPPickaxe(BOPConfiguration.pickaxeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("pickaxeAmethyst").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		axeAmethyst = (new ItemBOPAxe(BOPConfiguration.axeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("hatchetAmethyst").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		hoeAmethyst = (new ItemBOPHoe(BOPConfiguration.hoeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("hoeAmethyst").setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		helmetAmethyst = (new ArmorAmethyst(BOPConfiguration.helmetAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 0)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("helmetAmethyst");
		chestplateAmethyst = (new ArmorAmethyst(BOPConfiguration.chestplateAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 1)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("chestplateAmethyst");
		leggingsAmethyst = (new ArmorAmethyst(BOPConfiguration.leggingsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 2)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("leggingsAmethyst");
		bootsAmethyst = (new ArmorAmethyst(BOPConfiguration.bootsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 3)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("bootsAmethyst");

		MinecraftForge.setToolClass(shovelAmethyst, "shovel", 3);
		MinecraftForge.setToolClass(pickaxeAmethyst, "pickaxe", 3);
		MinecraftForge.setToolClass(axeAmethyst, "axe", 3);
		
		//Mud Tools and Armor
		GameRegistry.addRecipe(new ItemStack(pickaxeMud, 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(shovelMud, 1), new Object [] {"#", "X", "X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(swordMud, 1), new Object [] {"#", "#", "X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(axeMud, 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(hoeMud, 1), new Object [] {"##", " X", " X", Character.valueOf('#'), mudBall, Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(helmetMud, 1), new Object [] {"###", "# #", Character.valueOf('#'), mudBall});
		GameRegistry.addRecipe(new ItemStack(chestplateMud, 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), mudBall});
		GameRegistry.addRecipe(new ItemStack(leggingsMud, 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), mudBall});
		GameRegistry.addRecipe(new ItemStack(bootsMud, 1), new Object [] {"# #", "# #", Character.valueOf('#'), mudBall});

		//Amethyst Tools and Armor
		GameRegistry.addRecipe(new ItemStack(pickaxeAmethyst, 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(shovelAmethyst, 1), new Object [] {"#", "X", "X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(swordAmethyst, 1), new Object [] {"#", "#", "X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(axeAmethyst, 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(hoeAmethyst, 1), new Object [] {"##", " X", " X", Character.valueOf('#'), amethyst, Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(helmetAmethyst, 1), new Object [] {"###", "# #", Character.valueOf('#'), amethyst});
		GameRegistry.addRecipe(new ItemStack(chestplateAmethyst, 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), amethyst});
		GameRegistry.addRecipe(new ItemStack(leggingsAmethyst, 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), amethyst});
		GameRegistry.addRecipe(new ItemStack(bootsAmethyst, 1), new Object [] {"# #", "# #", Character.valueOf('#'), amethyst});
		
		//Other
//		GameRegistry.addRecipe(new ItemStack(Item.wheat, 1), new Object[] {"###", '#', BOPItems.barleyItem});
		GameRegistry.addRecipe(new ItemStack(ancientStaff, 1, 0), new Object[] {"T", "P", "H", 'T', ancientStaffTopper, 'P', ancientStaffPole, 'H', ancientStaffHandle});
		GameRegistry.addRecipe(new ItemStack(ancientStaffHandle, 1, 0), new Object[] {"ISI", "ISI", " E ", 'I', Item.ingotIron, 'S', Block.whiteStone, 'E', Item.emerald});
		GameRegistry.addRecipe(new ItemStack(ancientStaffPole, 1, 0), new Object[] {"ISI", "IRI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'R', Item.redstone});
		GameRegistry.addRecipe(new ItemStack(ancientStaffTopper, 1, 0), new Object[] {" N ", "IDI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'D', Item.diamond, 'N', Item.netherStar});
		GameRegistry.addRecipe(new ItemStack(enderporter, 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Item.eyeOfEnder, 'O', Block.obsidian, 'A', Blocks.amethystBlock.get()});
		GameRegistry.addRecipe(new ItemStack(bopDiscMud, 1), new Object[] {" M ", "MDM", " M ", 'M', mudBall, 'D', bopDisc});
		
		GameRegistry.addShapelessRecipe(new ItemStack(bambooItem, 9), new Object[] {Blocks.bambooThatching.get()});
		GameRegistry.addShapelessRecipe(new ItemStack(amethyst, 9), new Object[] {Blocks.amethystBlock.get()});
		
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(shroomPowder, 2), new Object[] {new ItemStack(Blocks.flowers.get(),1,10)});
		
		GameRegistry.addSmelting(mudBall.itemID, new ItemStack(mudBrick, 1), 0F);
		
		LanguageRegistry.addName(shroomPowder, "Shroom Powder");
		LanguageRegistry.addName(mudBall, "Mud Ball");
		LanguageRegistry.addName(mudBrick, "Mud Brick");
		LanguageRegistry.addName(bambooItem, "Bamboo");
//		LanguageRegistry.addName(cattailItem, "Cattail");
//		LanguageRegistry.addName(shortGrassItem, "Short Grass");
//		LanguageRegistry.addName(mediumGrassItem, "Medium Grass");
//		LanguageRegistry.addName(bushItem, "Bush");
//		LanguageRegistry.addName(sproutItem, "Sprout");
		LanguageRegistry.addName(mossItem, "Moss");
//		LanguageRegistry.addName(barleyItem, "Barley");
		LanguageRegistry.addName(ashes, "Pile of Ashes");
		LanguageRegistry.addName(pickaxeMud, "Muddy Pickaxe");
		LanguageRegistry.addName(axeMud, "Muddy Axe");
		LanguageRegistry.addName(shovelMud, "Muddy Shovel");
		LanguageRegistry.addName(swordMud, "Muddy Sword");
		LanguageRegistry.addName(hoeMud, "Muddy Hoe");
		LanguageRegistry.addName(helmetMud, "Muddy Helmet");
		LanguageRegistry.addName(chestplateMud, "Muddy Chestplate");
		LanguageRegistry.addName(leggingsMud, "Muddy Leggings");
		LanguageRegistry.addName(bootsMud, "Muddy Boots");
		LanguageRegistry.addName(ancientStaff, "Ancient Staff");
		LanguageRegistry.addName(ancientStaffHandle, "Ancient Staff Handle");
		LanguageRegistry.addName(ancientStaffPole, "Ancient Staff Pole");
		LanguageRegistry.addName(ancientStaffTopper, "Ancient Staff Topper");
		LanguageRegistry.addName(enderporter, "Enderporter");
		LanguageRegistry.addName(amethyst, "Amethyst");
		LanguageRegistry.addName(bopDisc, "Music Disc");
		LanguageRegistry.addName(bopDiscMud, "Music Disc");
		LanguageRegistry.addName(pickaxeAmethyst, "Amethyst Pickaxe");
		LanguageRegistry.addName(axeAmethyst, "Amethyst Axe");
		LanguageRegistry.addName(shovelAmethyst, "Amethyst Shovel");
		LanguageRegistry.addName(swordAmethyst, "Amethyst Sword");
		LanguageRegistry.addName(hoeAmethyst, "Amethyst Hoe");
		LanguageRegistry.addName(helmetAmethyst, "Amethyst Helmet");
		LanguageRegistry.addName(chestplateAmethyst, "Amethyst Chestplate");
		LanguageRegistry.addName(leggingsAmethyst, "Amethyst Leggings");
		LanguageRegistry.addName(bootsAmethyst, "Amethyst Boots");	
	}
}
