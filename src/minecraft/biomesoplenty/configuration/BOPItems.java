package biomesoplenty.configuration;

import java.util.Map;
import java.util.logging.Level;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import biomesoplenty.armor.ArmorAmethyst;
import biomesoplenty.armor.ArmorFlowerBand;
import biomesoplenty.armor.ArmorMuddy;
import biomesoplenty.items.ItemBOP;
import biomesoplenty.items.ItemBOPAncientStaff;
import biomesoplenty.items.ItemBOPAxe;
import biomesoplenty.items.ItemBOPHoe;
import biomesoplenty.items.ItemBOPMudball;
import biomesoplenty.items.ItemBOPPickaxe;
import biomesoplenty.items.ItemBOPRecord;
import biomesoplenty.items.ItemBOPRecordMud;
import biomesoplenty.items.ItemBOPScythe;
import biomesoplenty.items.ItemBOPSpade;
import biomesoplenty.items.ItemBOPSword;
import biomesoplenty.items.ItemBOPFood;
import biomesoplenty.items.ItemDart;
import biomesoplenty.items.ItemDartBlower;
import biomesoplenty.items.ItemEnderporter;
import biomesoplenty.items.ItemSoulManipulator;

import com.google.common.base.Optional;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class BOPItems {
	public static Item shears;

	// Material declaration
	public static EnumArmorMaterial EnumArmorMaterialMud;
	public static EnumToolMaterial EnumToolMaterialMud;
	public static EnumArmorMaterial EnumArmorMaterialAmethyst;
	public static EnumToolMaterial EnumToolMaterialAmethyst;
	public static EnumArmorMaterial EnumArmorMaterialFlowerBand;

	public static int clearItem(Item var1)
	{
		return clearItem(var1.itemID);
	}

	public static int clearItem(Item var1, boolean var2)
	{
		return clearItem(var1.itemID, var2);
	}

	public static int clearItem(int var1)
	{
		return clearItem(var1, true);
	}

	public static int clearItem(int var1, boolean var2)
	{
		if (var1 < 0 || var1 >= Item.itemsList.length)
		{
			FMLLog.log(Level.SEVERE, "BiomesOPlenty has an invalid item ID (%i)", new Object[] {Integer.valueOf(var1)});
		}

		if (var2 && Item.itemsList[var1] == null)
		{
			FMLLog.log(Level.WARNING, "BiomesOPlenty tried clearing an already cleared item (%i)", new Object[] {Integer.valueOf(var1)});
		}

		Item.itemsList[var1] = null;
		Map var3 = (Map)ReflectionHelper.getPrivateValue(GameData.class, null, new String[] {"idMap"});
		var3.remove(Integer.valueOf(var1));
		return var1 - 256;
	}

	public static void init()
	{
		// Material declaration
		EnumArmorMaterialMud = EnumHelper.addArmorMaterial("MUD", 2, new int[]{1, 1, 1, 1}, 5);
		EnumToolMaterialMud = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0, 1);
		EnumArmorMaterialAmethyst = EnumHelper.addArmorMaterial("AMETHYST", 40, new int[]{6, 12, 10, 6}, 20);
		EnumToolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5, 16);
		EnumArmorMaterialFlowerBand = EnumHelper.addArmorMaterial("FLOWERBAND", -1, new int[]{0, 0, 0, 0}, 0);

		initializeItems();

		MinecraftForge.setToolClass(Items.shovelAmethyst.get(), "shovel", 4);
		MinecraftForge.setToolClass(Items.pickaxeAmethyst.get(), "pickaxe", 4);
		MinecraftForge.setToolClass(Items.axeAmethyst.get(), "axe", 4);
	}

	private static void initializeItems()
	{
		// Item declaration
		Items.food = Optional.of(new ItemBOPFood(BOPConfiguration.foodID).setUnlocalizedName("bop.food"));
		Items.miscItems = Optional.of(new ItemBOP(BOPConfiguration.miscItemsID).setUnlocalizedName("bop.miscItems"));
		Items.mudball = Optional.of(new ItemBOPMudball(BOPConfiguration.mudballID).setUnlocalizedName("bop.mudball"));
		Items.dartBlower = Optional.of(new ItemDartBlower(BOPConfiguration.dartBlowerID).setUnlocalizedName("bop.dartblower"));
		Items.dart = Optional.of(new ItemDart(BOPConfiguration.dartID).setUnlocalizedName("bop.dart"));
		Items.soulManipulator = Optional.of(new ItemSoulManipulator(BOPConfiguration.soulManipulatorID).setUnlocalizedName("bop.soulManipulator"));

		Items.ancientStaff = Optional.of(new ItemBOPAncientStaff(BOPConfiguration.ancientStaffID).setUnlocalizedName("bop.ancientStaff"));

		Items.enderporter = Optional.of(new ItemEnderporter(BOPConfiguration.enderporterID).setUnlocalizedName("bop.enderporter"));

		Items.bopDisc = Optional.of(new ItemBOPRecord(BOPConfiguration.bopDiscID, "bopdisc").setUnlocalizedName("bop.bopDisc"));
		Items.bopDiscMud = Optional.of(new ItemBOPRecordMud(BOPConfiguration.bopDiscMudID, "bopdiscmud").setUnlocalizedName("bop.bopDiscMud"));

		Items.swordMud = Optional.of((new ItemBOPSword(BOPConfiguration.swordMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.swordMud"));
		Items.shovelMud = Optional.of((new ItemBOPSpade(BOPConfiguration.shovelMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.shovelMud"));
		Items.pickaxeMud = Optional.of((new ItemBOPPickaxe(BOPConfiguration.pickaxeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.pickaxeMud"));
		Items.axeMud = Optional.of((new ItemBOPAxe(BOPConfiguration.axeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.hatchetMud"));
		Items.hoeMud = Optional.of((new ItemBOPHoe(BOPConfiguration.hoeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.hoeMud"));
		Items.helmetMud = Optional.of((new ArmorMuddy(BOPConfiguration.helmetMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 0)).setUnlocalizedName("bop.helmetMud"));
		Items.chestplateMud = Optional.of((new ArmorMuddy(BOPConfiguration.chestplateMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 1)).setUnlocalizedName("bop.chestplateMud"));
		Items.leggingsMud = Optional.of((new ArmorMuddy(BOPConfiguration.leggingsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 2)).setUnlocalizedName("bop.leggingsMud"));
		Items.bootsMud = Optional.of((new ArmorMuddy(BOPConfiguration.bootsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 3)).setUnlocalizedName("bop.bootsMud"));

		Items.swordAmethyst = Optional.of((new ItemBOPSword(BOPConfiguration.swordAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.swordAmethyst"));
		Items.shovelAmethyst = Optional.of((new ItemBOPSpade(BOPConfiguration.shovelAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.shovelAmethyst"));
		Items.pickaxeAmethyst = Optional.of((new ItemBOPPickaxe(BOPConfiguration.pickaxeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.pickaxeAmethyst"));
		Items.axeAmethyst = Optional.of((new ItemBOPAxe(BOPConfiguration.axeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.hatchetAmethyst"));
		Items.hoeAmethyst = Optional.of((new ItemBOPHoe(BOPConfiguration.hoeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("bop.hoeAmethyst"));
		Items.helmetAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.helmetAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 0)).setUnlocalizedName("bop.helmetAmethyst"));
		Items.chestplateAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.chestplateAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 1)).setUnlocalizedName("bop.chestplateAmethyst"));
		Items.leggingsAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.leggingsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 2)).setUnlocalizedName("bop.leggingsAmethyst"));
		Items.bootsAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.bootsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 3)).setUnlocalizedName("bop.bootsAmethyst"));
		
		Items.scytheWood = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheWoodID, 2, EnumToolMaterial.WOOD, 0).setUnlocalizedName("bop.scytheWood")));
		Items.scytheStone = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheStoneID, 2, EnumToolMaterial.STONE, 1).setUnlocalizedName("bop.scytheStone")));
		Items.scytheIron = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheIronID, 2, EnumToolMaterial.IRON, 2).setUnlocalizedName("bop.scytheIron")));
		Items.scytheGold = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheGoldID, 2, EnumToolMaterial.GOLD, 3).setUnlocalizedName("bop.scytheGold")));
		Items.scytheDiamond = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheDiamondID, 2, EnumToolMaterial.EMERALD, 4).setUnlocalizedName("bop.scytheDiamond")));
		Items.scytheMud = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheMudID, 2, EnumToolMaterialMud, 5).setUnlocalizedName("bop.scytheMud")));
		Items.scytheAmethyst = Optional.of((new ItemBOPScythe(BOPConfiguration.scytheAmethystID, 2, EnumToolMaterialAmethyst, 6).setUnlocalizedName("bop.scytheAmethyst")));

		Items.flowerBand = Optional.of((new ArmorFlowerBand(BOPConfiguration.flowerBandID, EnumArmorMaterialFlowerBand, BiomesOPlenty.proxy.addArmor("flowerBand"), 0)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("bop.flowerBand"));
	}
}
