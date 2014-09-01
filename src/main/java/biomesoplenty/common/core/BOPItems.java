package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCItems.*;
import static biomesoplenty.common.core.BOPItems.registerItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.armor.ItemAmethystArmor;
import biomesoplenty.common.armor.ItemFlippers;
import biomesoplenty.common.armor.ItemFlowerBand;
import biomesoplenty.common.armor.ItemMuddyArmor;
import biomesoplenty.common.armor.ItemWadingBoots;
import biomesoplenty.common.items.ItemBOPAncientStaff;
import biomesoplenty.common.items.ItemBOPAxe;
import biomesoplenty.common.items.ItemBOPBiomeEssence;
import biomesoplenty.common.items.ItemBOPBucket;
import biomesoplenty.common.items.ItemBOPFood;
import biomesoplenty.common.items.ItemBOPHoe;
import biomesoplenty.common.items.ItemBOPMisc;
import biomesoplenty.common.items.ItemBOPMudball;
import biomesoplenty.common.items.ItemBOPPickaxe;
import biomesoplenty.common.items.ItemBOPRecord;
import biomesoplenty.common.items.ItemBOPScythe;
import biomesoplenty.common.items.ItemBOPSeeds;
import biomesoplenty.common.items.ItemBOPSpade;
import biomesoplenty.common.items.ItemBOPSword;
import biomesoplenty.common.items.ItemBiomeFinder;
import biomesoplenty.common.items.ItemDart;
import biomesoplenty.common.items.ItemDartBlower;
import biomesoplenty.common.items.ItemEnderporter;
import biomesoplenty.common.items.ItemGems;
import biomesoplenty.common.items.ItemJarEmpty;
import biomesoplenty.common.items.ItemJarFilled;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPItems 
{
	public static void init()
	{
		initializeMaterials();
		registerItems();
	}
	
	private static void initializeMaterials()
	{
		BOPItemHelper.toolMaterialMud = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0, 1);
		BOPItemHelper.armorMaterialMud = EnumHelper.addArmorMaterial("MUD", 2, new int[] {1, 1, 1, 1}, 5);
		
		BOPItemHelper.toolMaterialAmethyst = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5, 16);
		BOPItemHelper.armorMaterialAmethyst = EnumHelper.addArmorMaterial("AMETHYST", 40, new int[] {6, 12, 10, 6}, 20);
		
		BOPItemHelper.armorMaterialUnprotective = EnumHelper.addArmorMaterial("UNPROTECTIVE", -1, new int[] {0, 0, 0, 0}, 0);
		
		registerCraftingMaterials();
	}

	private static void registerItems()
	{
		food = registerItem(new ItemBOPFood(0).setUnlocalizedName("food"));
		turnipSeeds = registerItem(new ItemBOPSeeds(BOPCBlocks.turnip, Blocks.farmland).setUnlocalizedName("turnipSeeds"));
		misc = registerItem(new ItemBOPMisc().setUnlocalizedName("misc"));
		gems = registerItem(new ItemGems().setUnlocalizedName("gems"));
		mudball = registerItem(new ItemBOPMudball().setUnlocalizedName("mudball"));
		
		jarEmpty = registerItem(new ItemJarEmpty().setUnlocalizedName("jarEmpty"));
		jarFilled = registerItem(new ItemJarFilled().setUnlocalizedName("jarFilled"));
		
		dartBlower = registerItem(new ItemDartBlower().setUnlocalizedName("dartBlower"));
		dart = registerItem(new ItemDart().setUnlocalizedName("dart"));
		
		ancientStaff = registerItem(new ItemBOPAncientStaff().setUnlocalizedName("ancientStaff"));
		enderporter = registerItem(new ItemEnderporter().setUnlocalizedName("enderporter"));
		
		bopBucket = registerItem(new ItemBOPBucket().setUnlocalizedName("bopBucket"));
		
		record_wanderer = registerItem(new ItemBOPRecord("wanderer").setUnlocalizedName("record_wanderer"));
		record_corruption = registerItem(new ItemBOPRecord("corruption").setUnlocalizedName("record_corruption"));
		
		//registerItem(new ItemBiomeBook().setUnlocalizedName("biomeBook"));
		biomeFinder = registerItem(new ItemBiomeFinder().setUnlocalizedName("biomeFinder"));
		biomeEssence = registerItem(new ItemBOPBiomeEssence().setUnlocalizedName("biomeEssence"));
		
		swordMud = registerItem(new ItemBOPSword(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("swordMud"));
		shovelMud = registerItem(new ItemBOPSpade(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("shovelMud"));
		pickaxeMud = registerItem(new ItemBOPPickaxe(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("pickaxeMud"));
		axeMud = registerItem(new ItemBOPAxe(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("axeMud"));
		hoeMud = registerItem(new ItemBOPHoe(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("hoeMud"));
		
		swordAmethyst = registerItem(new ItemBOPSword(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("swordAmethyst"));
		shovelAmethyst = registerItem(new ItemBOPSpade(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("shovelAmethyst"));
		pickaxeAmethyst = registerItem(new ItemBOPPickaxe(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("pickaxeAmethyst"));
		axeAmethyst = registerItem(new ItemBOPAxe(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("axeAmethyst"));
		hoeAmethyst = registerItem(new ItemBOPHoe(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("hoeAmethyst"));
		
		scytheWood = registerItem(new ItemBOPScythe(ToolMaterial.WOOD, 0).setUnlocalizedName("scytheWood"));
		scytheStone = registerItem(new ItemBOPScythe(ToolMaterial.STONE, 1).setUnlocalizedName("scytheStone"));
		scytheIron = registerItem(new ItemBOPScythe(ToolMaterial.IRON, 2).setUnlocalizedName("scytheIron"));
		scytheGold = registerItem(new ItemBOPScythe(ToolMaterial.GOLD, 3).setUnlocalizedName("scytheGold"));
		scytheDiamond = registerItem(new ItemBOPScythe(ToolMaterial.EMERALD, 4).setUnlocalizedName("scytheDiamond"));
		scytheMud = registerItem(new ItemBOPScythe(BOPItemHelper.toolMaterialMud, 5).setUnlocalizedName("scytheMud"));
		scytheAmethyst = registerItem(new ItemBOPScythe(BOPItemHelper.toolMaterialAmethyst, 6).setUnlocalizedName("scytheAmethyst"));
		
		helmetMud = registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 0).setUnlocalizedName("helmetMud"));
		chestplateMud = registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 1).setUnlocalizedName("chestplateMud"));
		leggingsMud = registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 2).setUnlocalizedName("leggingsMud"));
		bootsMud = registerItem(new ItemMuddyArmor(BOPItemHelper.armorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 3).setUnlocalizedName("bootsMud"));
		
		helmetAmethyst = registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 0).setUnlocalizedName("helmetAmethyst"));
		chestplateAmethyst = registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 1).setUnlocalizedName("chestplateAmethyst"));
		leggingsAmethyst = registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 2).setUnlocalizedName("leggingsAmethyst"));
		bootsAmethyst = registerItem(new ItemAmethystArmor(BOPItemHelper.armorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 3).setUnlocalizedName("bootsAmethyst"));
		
		flowerBand = registerItem(new ItemFlowerBand(BOPItemHelper.armorMaterialUnprotective, BiomesOPlenty.proxy.addArmor("flowerBand"), 0).setUnlocalizedName("flowerBand"));
		flippers = registerItem(new ItemFlippers(BOPItemHelper.armorMaterialUnprotective, BiomesOPlenty.proxy.addArmor("flippers"), 3).setUnlocalizedName("flippers"));
		wadingBoots = registerItem(new ItemWadingBoots(BOPItemHelper.armorMaterialUnprotective, BiomesOPlenty.proxy.addArmor("wadingBoots"), 3).setUnlocalizedName("wadingBoots"));
	}
	
	private static void registerCraftingMaterials()
	{
		BOPItemHelper.toolMaterialMud.customCraftingMaterial = mudball;
		BOPItemHelper.armorMaterialMud.customCraftingMaterial = mudball;
	}

	public static Item registerItem(Item item)
	{
	    GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	    
	    return item;
	}
}
