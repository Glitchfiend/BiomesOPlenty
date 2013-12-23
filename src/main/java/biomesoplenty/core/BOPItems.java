package biomesoplenty.core;

import net.minecraft.item.Item;
import biomesoplenty.configuration.BOPConfigurationIDs;
import biomesoplenty.items.ItemBOPFood;
import biomesoplenty.items.ItemBOPRecord;
import biomesoplenty.items.ItemBOPRecordMud;
import biomesoplenty.items.ItemDart;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPItems 
{
	// Material declaration
	/*public static EnumArmorMaterial EnumArmorMaterialMud;
	public static EnumToolMaterial EnumToolMaterialMud;
	public static EnumArmorMaterial EnumArmorMaterialAmethyst;
	public static EnumToolMaterial EnumToolMaterialAmethyst;
	public static EnumArmorMaterial EnumArmorMaterialFlowerBand;
	public static EnumArmorMaterial EnumArmorMaterialWadingBoots;
	public static EnumArmorMaterial EnumArmorMaterialFlippers;*/

	public static void init()
	{
		// Material declaration
		/*EnumArmorMaterialMud = EnumHelper.addArmorMaterial("MUD", 2, new int[]{1, 1, 1, 1}, 5);
		EnumToolMaterialMud = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0, 1);
		EnumArmorMaterialAmethyst = EnumHelper.addArmorMaterial("AMETHYST", 40, new int[]{6, 12, 10, 6}, 20);
		EnumToolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5, 16);
		EnumArmorMaterialFlowerBand = EnumHelper.addArmorMaterial("FLOWERBAND", -1, new int[]{0, 0, 0, 0}, 0);
		EnumArmorMaterialWadingBoots = EnumHelper.addArmorMaterial("WADINGBOOTS", -1, new int[]{0, 0, 0, 0}, 0);
		EnumArmorMaterialFlippers = EnumHelper.addArmorMaterial("FLIPPERS", -1, new int[]{0, 0, 0, 0}, 0);*/

		initializeItems();

		/*MinecraftForge.setToolClass(Items.shovelAmethyst.get(), "shovel", 4);
		MinecraftForge.setToolClass(Items.pickaxeAmethyst.get(), "pickaxe", 4);
		MinecraftForge.setToolClass(Items.axeAmethyst.get(), "axe", 4);*/
	}

	private static void initializeItems()
	{
		// Item declaration
		registerItem(new ItemBOPFood(0).setUnlocalizedName("food"));
		/*Items.turnipseeds = Optional.of(new ItemBOPSeeds(BOPConfigurationIDs.turnipseedsID, Blocks.turnip.get().blockID, Block.tilledField.blockID).setUnlocalizedName("bop.turnipseeds"));
		Items.miscItems = Optional.of(new ItemBOP(BOPConfigurationIDs.miscItemsID).setUnlocalizedName("bop.miscItems"));
		Items.jarEmpty = Optional.of(new ItemJarEmpty(BOPConfigurationIDs.jarEmptyID).setUnlocalizedName("bop.jarEmpty"));
		Items.jarFilled = Optional.of(new ItemJarFilled(BOPConfigurationIDs.jarFilledID).setUnlocalizedName("bop.jarFilled").setContainerItem(Items.jarEmpty.get()));
		Items.gems = Optional.of(new ItemGems(BOPConfigurationIDs.gemsID).setUnlocalizedName("bop.gems"));
		Items.mudball = Optional.of(new ItemBOPMudball(BOPConfigurationIDs.mudballID).setUnlocalizedName("bop.mudball"));
		Items.dartBlower = Optional.of(new ItemDartBlower(BOPConfigurationIDs.dartBlowerID).setUnlocalizedName("bop.dartblower"));*/
		registerItem(new ItemDart().setUnlocalizedName("dart"));

		/*Items.ancientStaff = Optional.of(new ItemBOPAncientStaff(BOPConfigurationIDs.ancientStaffID).setUnlocalizedName("bop.ancientStaff"));

		Items.enderporter = Optional.of(new ItemEnderporter(BOPConfigurationIDs.enderporterID).setUnlocalizedName("bop.enderporter"));*/

		registerItem(new ItemBOPRecord("bopdisc").setUnlocalizedName("bopDisc"));
		//registerItem(new ItemBOPRecordMud("bopdiscmud").setUnlocalizedName("bopDiscMud"));

		/*Items.swordMud = Optional.of((new ItemBOPSword(BOPConfigurationIDs.swordMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.swordMud"));
		Items.shovelMud = Optional.of((new ItemBOPSpade(BOPConfigurationIDs.shovelMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.shovelMud"));
		Items.pickaxeMud = Optional.of((new ItemBOPPickaxe(BOPConfigurationIDs.pickaxeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.pickaxeMud"));
		Items.axeMud = Optional.of((new ItemBOPAxe(BOPConfigurationIDs.axeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.hatchetMud"));
		Items.hoeMud = Optional.of((new ItemBOPHoe(BOPConfigurationIDs.hoeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.hoeMud"));
		Items.helmetMud = Optional.of((new ArmorMuddy(BOPConfigurationIDs.helmetMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 0)).setUnlocalizedName("bop.helmetMud"));
		Items.chestplateMud = Optional.of((new ArmorMuddy(BOPConfigurationIDs.chestplateMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 1)).setUnlocalizedName("bop.chestplateMud"));
		Items.leggingsMud = Optional.of((new ArmorMuddy(BOPConfigurationIDs.leggingsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 2)).setUnlocalizedName("bop.leggingsMud"));
		Items.bootsMud = Optional.of((new ArmorMuddy(BOPConfigurationIDs.bootsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 3)).setUnlocalizedName("bop.bootsMud"));

		Items.swordAmethyst = Optional.of((new ItemBOPSword(BOPConfigurationIDs.swordAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.swordAmethyst"));
		Items.shovelAmethyst = Optional.of((new ItemBOPSpade(BOPConfigurationIDs.shovelAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.shovelAmethyst"));
		Items.pickaxeAmethyst = Optional.of((new ItemBOPPickaxe(BOPConfigurationIDs.pickaxeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.pickaxeAmethyst"));
		Items.axeAmethyst = Optional.of((new ItemBOPAxe(BOPConfigurationIDs.axeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.hatchetAmethyst"));
		Items.hoeAmethyst = Optional.of((new ItemBOPHoe(BOPConfigurationIDs.hoeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.hoeAmethyst"));
		Items.helmetAmethyst = Optional.of((new ArmorAmethyst(BOPConfigurationIDs.helmetAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 0)).setUnlocalizedName("bop.helmetAmethyst"));
		Items.chestplateAmethyst = Optional.of((new ArmorAmethyst(BOPConfigurationIDs.chestplateAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 1)).setUnlocalizedName("bop.chestplateAmethyst"));
		Items.leggingsAmethyst = Optional.of((new ArmorAmethyst(BOPConfigurationIDs.leggingsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 2)).setUnlocalizedName("bop.leggingsAmethyst"));
		Items.bootsAmethyst = Optional.of((new ArmorAmethyst(BOPConfigurationIDs.bootsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 3)).setUnlocalizedName("bop.bootsAmethyst"));
		
		Items.scytheWood = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheWoodID, 2, EnumToolMaterial.WOOD, 0).setUnlocalizedName("bop.scytheWood")));
		Items.scytheStone = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheStoneID, 2, EnumToolMaterial.STONE, 1).setUnlocalizedName("bop.scytheStone")));
		Items.scytheIron = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheIronID, 2, EnumToolMaterial.IRON, 2).setUnlocalizedName("bop.scytheIron")));
		Items.scytheGold = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheGoldID, 2, EnumToolMaterial.GOLD, 3).setUnlocalizedName("bop.scytheGold")));
		Items.scytheDiamond = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheDiamondID, 2, EnumToolMaterial.EMERALD, 4).setUnlocalizedName("bop.scytheDiamond")));
		Items.scytheMud = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheMudID, 2, EnumToolMaterialMud, 5).setUnlocalizedName("bop.scytheMud")));
		Items.scytheAmethyst = Optional.of((new ItemBOPScythe(BOPConfigurationIDs.scytheAmethystID, 2, EnumToolMaterialAmethyst, 6).setUnlocalizedName("bop.scytheAmethyst")));

		Items.flowerBand = Optional.of((new ArmorFlowerBand(BOPConfigurationIDs.flowerBandID, EnumArmorMaterialFlowerBand, BiomesOPlenty.proxy.addArmor("flowerBand"), 0)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("bop.flowerBand"));
		Items.wadingBoots = Optional.of((new ArmorWadingBoots(BOPConfigurationIDs.wadingBootsID, EnumArmorMaterialWadingBoots, BiomesOPlenty.proxy.addArmor("wadingBoots"), 3)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("bop.wadingBoots"));
		Items.flippers = Optional.of((new ArmorFlippers(BOPConfigurationIDs.flippersID, EnumArmorMaterialFlippers, BiomesOPlenty.proxy.addArmor("flippers"), 3)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("bop.flippers"));*/
	}

	public static void registerItem(Item item)
	{
	    GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}
