package biomesoplenty.common.core;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.common.items.ItemBOPAncientStaff;
import biomesoplenty.common.items.ItemBOPAxe;
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
	}

	private static void registerItems()
	{
		registerItem(new ItemBOPFood(0).setUnlocalizedName("food"));
		registerItem(new ItemBOPSeeds(BOPBlockHelper.get("turnip"), Blocks.farmland).setUnlocalizedName("turnipSeeds"));
		registerItem(new ItemBOPMisc().setUnlocalizedName("misc"));
		registerItem(new ItemGems().setUnlocalizedName("gems"));
		registerItem(new ItemBOPMudball().setUnlocalizedName("mudball"));
		
		registerItem(new ItemJarEmpty().setUnlocalizedName("jarEmpty"));
		registerItem(new ItemJarFilled().setUnlocalizedName("jarFilled"));
		
		registerItem(new ItemDartBlower().setUnlocalizedName("dartBlower"));
		registerItem(new ItemDart().setUnlocalizedName("dart"));
		
		registerItem(new ItemBOPAncientStaff().setUnlocalizedName("ancientStaff"));
		registerItem(new ItemEnderporter().setUnlocalizedName("enderporter"));
		
		registerItem(new ItemBOPRecord("bopdisc").setUnlocalizedName("bopDisc"));
		registerItem(new ItemBOPRecord("bopdiscmud").setUnlocalizedName("bopDiscMud"));
		
		//registerItem(new ItemBiomeBook().setUnlocalizedName("biomeBook"));
		registerItem(new ItemBiomeFinder().setUnlocalizedName("biomeFinder"));
		
		registerItem(new ItemBOPSword(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("swordMud"));
		registerItem(new ItemBOPSpade(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("shovelMud"));
		registerItem(new ItemBOPPickaxe(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("pickaxeMud"));
		registerItem(new ItemBOPAxe(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("axeMud"));
		registerItem(new ItemBOPHoe(BOPItemHelper.toolMaterialMud, 0).setUnlocalizedName("hoeMud"));
		
		registerItem(new ItemBOPSword(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("swordAmethyst"));
		registerItem(new ItemBOPSpade(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("shovelAmethyst"));
		registerItem(new ItemBOPPickaxe(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("pickaxeAmethyst"));
		registerItem(new ItemBOPAxe(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("axeAmethyst"));
		registerItem(new ItemBOPHoe(BOPItemHelper.toolMaterialAmethyst, 1).setUnlocalizedName("hoeAmethyst"));
		
		registerItem(new ItemBOPScythe(ToolMaterial.WOOD, 0).setUnlocalizedName("scytheWood"));
		registerItem(new ItemBOPScythe(ToolMaterial.STONE, 1).setUnlocalizedName("scytheStone"));
		registerItem(new ItemBOPScythe(ToolMaterial.IRON, 2).setUnlocalizedName("scytheIron"));
		registerItem(new ItemBOPScythe(ToolMaterial.GOLD, 3).setUnlocalizedName("scytheGold"));
		registerItem(new ItemBOPScythe(ToolMaterial.EMERALD, 4).setUnlocalizedName("scytheDiamond"));
		registerItem(new ItemBOPScythe(BOPItemHelper.toolMaterialMud, 5).setUnlocalizedName("scytheMud"));
		registerItem(new ItemBOPScythe(BOPItemHelper.toolMaterialAmethyst, 6).setUnlocalizedName("scytheAmethyst"));
	}

	public static void registerItem(Item item)
	{
	    GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}
