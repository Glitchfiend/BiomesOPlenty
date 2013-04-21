package biomesoplenty.configuration;

import java.util.Map;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.armor.ArmorAmethyst;
import biomesoplenty.armor.ArmorMuddy;
import biomesoplenty.items.ItemBOP;
import biomesoplenty.items.ItemBOPAncientStaff;
import biomesoplenty.items.ItemBOPAxe;
import biomesoplenty.items.ItemBOPHoe;
import biomesoplenty.items.ItemBOPPickaxe;
import biomesoplenty.items.ItemBOPRecord;
import biomesoplenty.items.ItemBOPRecordMud;
import biomesoplenty.items.ItemBOPSpade;
import biomesoplenty.items.ItemBOPSword;
import biomesoplenty.items.ItemEnderporter;
import biomesoplenty.items.ItemShroomPowder;
import biomesoplenty.items.overrides.ItemShears;

import com.google.common.base.Optional;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class BOPItems {
	public static Item shears;
	
	// Material declaration
	public static EnumArmorMaterial EnumArmorMaterialMud;
	public static EnumToolMaterial EnumToolMaterialMud;
	public static EnumArmorMaterial EnumArmorMaterialAmethyst;
	public static EnumToolMaterial EnumToolMaterialAmethyst;
	
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
		
		//Override Items
        shears = (new ItemShears(clearItem(Item.shears))).setUnlocalizedName("shears").setCreativeTab(CreativeTabs.tabTools);
		
		// Item declaration
		Items.shroomPowder = Optional.of(new ItemShroomPowder(BOPConfiguration.shroomPowderID, 1, 0.5F, false));
		Items.miscItems = Optional.of(new ItemBOP(BOPConfiguration.amethystID));
		
		Items.ancientStaff = Optional.of(new ItemBOPAncientStaff(BOPConfiguration.ancientStaffID));
		
		Items.enderporter = Optional.of(new ItemEnderporter(BOPConfiguration.enderporterID));
		
		Items.bopDisc = Optional.of(new ItemBOPRecord(BOPConfiguration.bopDiscID, "bopdisc"));
		Items.bopDiscMud = Optional.of(new ItemBOPRecordMud(BOPConfiguration.bopDiscMudID, "bopdiscmud"));

		Items.swordMud = Optional.of((new ItemBOPSword(BOPConfiguration.swordMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("swordMud"));
		Items.shovelMud = Optional.of((new ItemBOPSpade(BOPConfiguration.shovelMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("shovelMud"));
		Items.pickaxeMud = Optional.of((new ItemBOPPickaxe(BOPConfiguration.pickaxeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("pickaxeMud"));
		Items.axeMud = Optional.of((new ItemBOPAxe(BOPConfiguration.axeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("hatchetMud"));
		Items.hoeMud = Optional.of((new ItemBOPHoe(BOPConfiguration.hoeMudID, EnumToolMaterialMud, 0)).setUnlocalizedName("hoeMud"));
		Items.helmetMud = Optional.of((new ArmorMuddy(BOPConfiguration.helmetMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 0)).setUnlocalizedName("helmetMud"));
		Items.chestplateMud = Optional.of((new ArmorMuddy(BOPConfiguration.chestplateMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 1)).setUnlocalizedName("chestplateMud"));
		Items.leggingsMud = Optional.of((new ArmorMuddy(BOPConfiguration.leggingsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 2)).setUnlocalizedName("leggingsMud"));
		Items.bootsMud = Optional.of((new ArmorMuddy(BOPConfiguration.bootsMudID, EnumArmorMaterialMud, BiomesOPlenty.proxy.addArmor("mud"), 3)).setUnlocalizedName("bootsMud"));

		Items.swordAmethyst = Optional.of((new ItemBOPSword(BOPConfiguration.swordAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("swordAmethyst"));
		Items.shovelAmethyst = Optional.of((new ItemBOPSpade(BOPConfiguration.shovelAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("shovelAmethyst"));
		Items.pickaxeAmethyst = Optional.of((new ItemBOPPickaxe(BOPConfiguration.pickaxeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("pickaxeAmethyst"));
		Items.axeAmethyst = Optional.of((new ItemBOPAxe(BOPConfiguration.axeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("hatchetAmethyst"));
		Items.hoeAmethyst = Optional.of((new ItemBOPHoe(BOPConfiguration.hoeAmethystID, EnumToolMaterialAmethyst, 1)).setUnlocalizedName("hoeAmethyst"));
		Items.helmetAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.helmetAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 0)).setUnlocalizedName("helmetAmethyst"));
		Items.chestplateAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.chestplateAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 1)).setUnlocalizedName("chestplateAmethyst"));
		Items.leggingsAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.leggingsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 2)).setUnlocalizedName("leggingsAmethyst"));
		Items.bootsAmethyst = Optional.of((new ArmorAmethyst(BOPConfiguration.bootsAmethystID, EnumArmorMaterialAmethyst, BiomesOPlenty.proxy.addArmor("amethyst"), 3)).setUnlocalizedName("bootsAmethyst"));

		MinecraftForge.setToolClass(Items.shovelAmethyst.get(), "shovel", 4);
		MinecraftForge.setToolClass(Items.pickaxeAmethyst.get(), "pickaxe", 4);
		MinecraftForge.setToolClass(Items.axeAmethyst.get(), "axe", 4);
		
		//Mud Tools and Armor
		GameRegistry.addRecipe(new ItemStack(Items.pickaxeMud.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0), Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(Items.shovelMud.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0), Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(Items.swordMud.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0), Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(Items.axeMud.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0), Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(Items.hoeMud.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0), Character.valueOf('X'), Item.stick});
		GameRegistry.addRecipe(new ItemStack(Items.helmetMud.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Items.chestplateMud.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Items.leggingsMud.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Items.bootsMud.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 0)});

		//Amethyst Tools and Armor
		GameRegistry.addRecipe(new ItemStack(Items.pickaxeAmethyst.get(), 1), new Object [] {"###", " X ", " X ", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(Items.shovelAmethyst.get(), 1), new Object [] {"#", "X", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(Items.swordAmethyst.get(), 1), new Object [] {"#", "#", "X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(Items.axeAmethyst.get(), 1), new Object [] {"##", "#X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(Items.hoeAmethyst.get(), 1), new Object [] {"##", " X", " X", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2), Character.valueOf('X'), Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(Items.helmetAmethyst.get(), 1), new Object [] {"###", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Items.chestplateAmethyst.get(), 1), new Object [] {"# #", "###", "###", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Items.leggingsAmethyst.get(), 1), new Object [] {"###", "# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Items.bootsAmethyst.get(), 1), new Object [] {"# #", "# #", Character.valueOf('#'), new ItemStack(Items.miscItems.get(), 1, 2)});
		
		//Other
		GameRegistry.addRecipe(new ItemStack(Item.wheat, 1), new Object[] {"###", '#', new ItemStack(Blocks.plants.get(),1,6)});
		GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 0), new Object[] {"T", "P", "H", 'T', new ItemStack(Items.ancientStaff.get(), 1, 3), 'P', new ItemStack(Items.ancientStaff.get(), 1, 2), 'H', new ItemStack(Items.ancientStaff.get(), 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 1), new Object[] {"ISI", "ISI", " E ", 'I', Item.ingotIron, 'S', Block.whiteStone, 'E', Item.emerald});
		GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 2), new Object[] {"ISI", "IRI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'R', Item.redstone});
		GameRegistry.addRecipe(new ItemStack(Items.ancientStaff.get(), 1, 3), new Object[] {" N ", "IDI", "ISI", 'I', Item.ingotIron, 'S', Block.whiteStone, 'D', Item.diamond, 'N', Item.netherStar});
		GameRegistry.addRecipe(new ItemStack(Items.enderporter.get(), 1, 0), new Object[] {"IOI", "OAO", "IOI", 'I', Item.eyeOfEnder, 'O', Block.obsidian, 'A', Blocks.amethystBlock.get()});
		GameRegistry.addRecipe(new ItemStack(Items.bopDiscMud.get(), 1), new Object[] {" M ", "MDM", " M ", 'M', new ItemStack(Items.miscItems.get(), 1, 0), 'D', Items.bopDisc.get()});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.bamboo.get(), 9), new Object[] {Blocks.bambooThatching.get()});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.miscItems.get(), 9, 2), new Object[] {Blocks.amethystBlock.get()});
		
		//Plants
		GameRegistry.addShapelessRecipe(new ItemStack(Items.shroomPowder.get(), 2), new Object[] {new ItemStack(Blocks.flowers.get(),1,10)});
		
		FurnaceRecipes.smelting().addSmelting(Items.miscItems.get().itemID, 2, new ItemStack(Items.miscItems.get(), 1, 3), 0F);
		
		LanguageRegistry.addName(Items.shroomPowder.get(), "Shroom Powder");
		LanguageRegistry.addName(new ItemStack(Items.miscItems.get(), 1, 0), "Mud Ball");
		LanguageRegistry.addName(new ItemStack(Items.miscItems.get(), 1, 3), "Mud Brick");
		LanguageRegistry.addName(new ItemStack(Items.miscItems.get(), 1, 2), "Amethyst");
		LanguageRegistry.addName(new ItemStack(Items.miscItems.get(), 1, 1), "Pile of Ashes");
		
		LanguageRegistry.addName(Items.pickaxeMud.get(), "Muddy Pickaxe");
		LanguageRegistry.addName(Items.axeMud.get(), "Muddy Axe");
		LanguageRegistry.addName(Items.shovelMud.get(), "Muddy Shovel");
		LanguageRegistry.addName(Items.swordMud.get(), "Muddy Sword");
		LanguageRegistry.addName(Items.hoeMud.get(), "Muddy Hoe");
		LanguageRegistry.addName(Items.helmetMud.get(), "Muddy Helmet");
		LanguageRegistry.addName(Items.chestplateMud.get(), "Muddy Chestplate");
		LanguageRegistry.addName(Items.leggingsMud.get(), "Muddy Leggings");
		LanguageRegistry.addName(Items.bootsMud.get(), "Muddy Boots");

		LanguageRegistry.addName(new ItemStack(Items.ancientStaff.get(), 1, 0), "Ancient Staff");
		LanguageRegistry.addName(new ItemStack(Items.ancientStaff.get(), 1, 1), "Ancient Staff Handle");
		LanguageRegistry.addName(new ItemStack(Items.ancientStaff.get(), 1, 2), "Ancient Staff Pole");
		LanguageRegistry.addName(new ItemStack(Items.ancientStaff.get(), 1, 3), "Ancient Staff Topper");
		
		LanguageRegistry.addName(Items.enderporter.get(), "Enderporter");
		
		LanguageRegistry.addName(Items.bopDisc.get(), "Music Disc");
		LanguageRegistry.addName(Items.bopDiscMud.get(), "Music Disc");

		LanguageRegistry.addName(Items.pickaxeAmethyst.get(), "Amethyst Pickaxe");
		LanguageRegistry.addName(Items.axeAmethyst.get(), "Amethyst Axe");
		LanguageRegistry.addName(Items.shovelAmethyst.get(), "Amethyst Shovel");
		LanguageRegistry.addName(Items.swordAmethyst.get(), "Amethyst Sword");
		LanguageRegistry.addName(Items.hoeAmethyst.get(), "Amethyst Hoe");
		LanguageRegistry.addName(Items.helmetAmethyst.get(), "Amethyst Helmet");
		LanguageRegistry.addName(Items.chestplateAmethyst.get(), "Amethyst Chestplate");
		LanguageRegistry.addName(Items.leggingsAmethyst.get(), "Amethyst Leggings");
		LanguageRegistry.addName(Items.bootsAmethyst.get(), "Amethyst Boots");	
	}
}
