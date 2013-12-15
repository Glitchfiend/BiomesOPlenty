package biomesoplenty.core;

import java.util.Map;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.armor.ArmorAmethyst;
import biomesoplenty.armor.ArmorFlippers;
import biomesoplenty.armor.ArmorFlowerBand;
import biomesoplenty.armor.ArmorMuddy;
import biomesoplenty.armor.ArmorWadingBoots;
import biomesoplenty.configuration.BOPConfigurationIDs;
import biomesoplenty.items.ItemBOP;
import biomesoplenty.items.ItemBOPAncientStaff;
import biomesoplenty.items.ItemBOPAxe;
import biomesoplenty.items.ItemBOPFood;
import biomesoplenty.items.ItemBOPHoe;
import biomesoplenty.items.ItemBOPMudball;
import biomesoplenty.items.ItemBOPPickaxe;
import biomesoplenty.items.ItemBOPRecord;
import biomesoplenty.items.ItemBOPRecordMud;
import biomesoplenty.items.ItemBOPScythe;
import biomesoplenty.items.ItemBOPSeeds;
import biomesoplenty.items.ItemBOPSpade;
import biomesoplenty.items.ItemBOPSword;
import biomesoplenty.items.ItemDart;
import biomesoplenty.items.ItemDartBlower;
import biomesoplenty.items.ItemEnderporter;
import biomesoplenty.items.ItemGems;
import biomesoplenty.items.ItemJarEmpty;
import biomesoplenty.items.ItemJarFilled;

import com.google.common.base.Optional;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class BOPItems {
	public static Item shears;

	// Material declaration
	public static EnumArmorMaterial EnumArmorMaterialMud;
	public static EnumToolMaterial EnumToolMaterialMud;
	public static EnumArmorMaterial EnumArmorMaterialAmethyst;
	public static EnumToolMaterial EnumToolMaterialAmethyst;
	public static EnumArmorMaterial EnumArmorMaterialFlowerBand;
	public static EnumArmorMaterial EnumArmorMaterialWadingBoots;
	public static EnumArmorMaterial EnumArmorMaterialFlippers;

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
		EnumArmorMaterialWadingBoots = EnumHelper.addArmorMaterial("WADINGBOOTS", -1, new int[]{0, 0, 0, 0}, 0);
		EnumArmorMaterialFlippers = EnumHelper.addArmorMaterial("FLIPPERS", -1, new int[]{0, 0, 0, 0}, 0);

		initializeItems();
		registerItems();

		MinecraftForge.setToolClass(Items.shovelAmethyst.get(), "shovel", 4);
		MinecraftForge.setToolClass(Items.pickaxeAmethyst.get(), "pickaxe", 4);
		MinecraftForge.setToolClass(Items.axeAmethyst.get(), "axe", 4);
	}

	private static void initializeItems()
	{
		// Item declaration
		Items.food = Optional.of(new ItemBOPFood(BOPConfigurationIDs.foodID).setUnlocalizedName("bop.food"));
		Items.turnipseeds = Optional.of(new ItemBOPSeeds(BOPConfigurationIDs.turnipseedsID, Blocks.turnip.get().blockID, Block.tilledField.blockID).setUnlocalizedName("bop.turnipseeds"));
		Items.miscItems = Optional.of(new ItemBOP(BOPConfigurationIDs.miscItemsID).setUnlocalizedName("bop.miscItems"));
		Items.jarEmpty = Optional.of(new ItemJarEmpty(BOPConfigurationIDs.jarEmptyID).setUnlocalizedName("bop.jarEmpty"));
		Items.jarFilled = Optional.of(new ItemJarFilled(BOPConfigurationIDs.jarFilledID).setUnlocalizedName("bop.jarFilled").setContainerItem(Items.jarEmpty.get()));
		Items.gems = Optional.of(new ItemGems(BOPConfigurationIDs.gemsID).setUnlocalizedName("bop.gems"));
		Items.mudball = Optional.of(new ItemBOPMudball(BOPConfigurationIDs.mudballID).setUnlocalizedName("bop.mudball"));
		Items.dartBlower = Optional.of(new ItemDartBlower(BOPConfigurationIDs.dartBlowerID).setUnlocalizedName("bop.dartblower"));
		Items.dart = Optional.of(new ItemDart(BOPConfigurationIDs.dartID).setUnlocalizedName("bop.dart"));

		Items.ancientStaff = Optional.of(new ItemBOPAncientStaff(BOPConfigurationIDs.ancientStaffID).setUnlocalizedName("bop.ancientStaff"));

		Items.enderporter = Optional.of(new ItemEnderporter(BOPConfigurationIDs.enderporterID).setUnlocalizedName("bop.enderporter"));

		Items.bopDisc = Optional.of(new ItemBOPRecord(BOPConfigurationIDs.bopDiscID, "bopdisc").setUnlocalizedName("bop.bopDisc"));
		Items.bopDiscMud = Optional.of(new ItemBOPRecordMud(BOPConfigurationIDs.bopDiscMudID, "bopdiscmud").setUnlocalizedName("bop.bopDiscMud"));

		Items.swordMud = Optional.of((new ItemBOPSword(BOPConfigurationIDs.swordMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("bop.swordMud"));
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
		Items.flippers = Optional.of((new ArmorFlippers(BOPConfigurationIDs.flippersID, EnumArmorMaterialFlippers, BiomesOPlenty.proxy.addArmor("flippers"), 3)).setCreativeTab(BiomesOPlenty.tabBiomesOPlenty).setUnlocalizedName("bop.flippers"));
	}
	
	private static void registerItems()
	{
        registerItem(Items.food.get());
        registerItem(Items.turnipseeds.get());
        registerItem(Items.miscItems.get());
        registerItem(Items.jarEmpty.get()); 
        registerItem(Items.jarFilled.get()); 
        registerItem(Items.gems.get());
        registerItem(Items.mudball.get());
        registerItem(Items.dartBlower.get());
        registerItem(Items.dart.get());

        registerItem(Items.ancientStaff.get());

        registerItem(Items.enderporter.get());

        registerItem(Items.bopDisc.get()); 
        registerItem(Items.bopDiscMud.get()); 

        registerItem(Items.swordMud.get());
        registerItem(Items.shovelMud.get()); 
        registerItem(Items.pickaxeMud.get());
        registerItem(Items.axeMud.get());
        registerItem(Items.hoeMud.get());
        registerItem(Items.helmetMud.get());
        registerItem(Items.chestplateMud.get());
        registerItem(Items.leggingsMud.get());
        registerItem(Items.bootsMud.get());

        registerItem(Items.swordAmethyst.get()); 
        registerItem(Items.shovelAmethyst.get()); 
        registerItem(Items.pickaxeAmethyst.get());
        registerItem(Items.axeAmethyst.get()); 
        registerItem(Items.hoeAmethyst.get()); 
        registerItem(Items.helmetAmethyst.get()); 
        registerItem(Items.chestplateAmethyst.get());
        registerItem(Items.leggingsAmethyst.get());
        registerItem(Items.bootsAmethyst.get());

        registerItem(Items.scytheWood.get()); 
        registerItem(Items.scytheStone.get());
        registerItem(Items.scytheIron.get()); 
        registerItem(Items.scytheGold.get()); 
        registerItem(Items.scytheDiamond.get());
        registerItem(Items.scytheMud.get());
        registerItem(Items.scytheAmethyst.get());

        registerItem(Items.flowerBand.get()); 
        registerItem(Items.wadingBoots.get());
        registerItem(Items.flippers.get());
	}

	public static void registerItem(Item item)
	{
	    GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}
