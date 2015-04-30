/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItems.*;
import static biomesoplenty.api.item.BOPItemHelper.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.item.*;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;

public class ModItems
{    
    public static void init()
    {
        // food
        ambrosia =          registerItem(new ItemAmbrosia(), "ambrosia");
        berries =           registerItem(new ItemBOPFood(1, 0.1F, 8), "berries"); 
        shroompowder =      registerItem(new ItemFood(1, 0.1F, false), "shroompowder");
        ((ItemFood)shroompowder).setAlwaysEdible();
        ((ItemFood)shroompowder).setPotionEffect(Potion.confusion.id, 225, 0, 0.6F);
        wildcarrots =       registerItem(new ItemFood(3, 0.5F, false), "wildcarrots");
        peach =             registerItem(new ItemFood(5, 0.2F, false), "peach");
        persimmon =         registerItem(new ItemFood(5, 0.2F, false), "persimmon");
        filled_honeycomb =  registerItem(new ItemBOPFood(3, 0.4F, 16), "filled_honeycomb");
        turnip =            registerItem(new ItemFood(3, 0.4F, false), "turnip");
        pear =              registerItem(new ItemFood(5, 0.3F, false), "pear");
        saladfruit =        registerItem(new ItemSoup(6), "saladfruit");
        ((ItemFood)saladfruit).setPotionEffect(Potion.digSpeed.id, 775, 1, 0.05F);
        saladveggie =       registerItem(new ItemSoup(6), "saladveggie");
        ((ItemFood)saladveggie).setPotionEffect(Potion.nightVision.id, 1100, 1, 0.05F); // TODO: Is this the right potion effect for veggie salad?
        saladshroom =       registerItem(new ItemSoup(6), "saladshroom");
        ((ItemFood)saladshroom).setPotionEffect(Potion.jump.id, 550, 1, 0.05F);
        ricebowl =          registerItem(new ItemSoup(2), "ricebowl");
        // TODO: eating earth is supposed to kill you isn't it?
        earth =             registerItem(new ItemFood(0, 0.0F, false), "earth", null); // no creative tab

        
        fleshchunk = registerItem(new Item(), "fleshchunk");
        mudball = registerItem(new ItemMudball(), "mudball");
        turnip_seeds = registerItem(new ItemSeeds(BOPBlocks.turnip_block, Blocks.farmland), "turnip_seeds");
        crystal_shard = registerItem(new Item(), "crystal_shard");
        honeycomb = registerItem(new Item(), "honeycomb");
        gem = registerItem(new ItemGem(), "gem");
        ash = registerItem(new Item(), "ash");
        mud_brick = registerItem(new Item(), "mud_brick");
        // TODO: move dyes to their own class?
        blue_dye = registerItem(new Item(), "blue_dye");
        brown_dye = registerItem(new Item(), "brown_dye");
        green_dye = registerItem(new Item(), "green_dye");
        white_dye = registerItem(new Item(), "white_dye");
        black_dye = registerItem(new Item(), "black_dye");
        ghastly_soul = registerItem(new Item(), "ghastly_soul");
        ghastly_soul.setMaxStackSize(1);
        pixie_dust = registerItem(new Item(), "pixie_dust");
        ichor = registerItem(new Item(), "ichor");
        pinecone = registerItem(new Item(), "pinecone");
        
        
        dart = registerItem(new ItemDart(), "dart");
        dart_blower = registerItem(new ItemDartBlower(), "dart_blower");
    
        // armor
        
        // addArmorMaterial arguments:
        // (String name, String textureName, int durability, int[] reductionAmounts, int enchantability)
        // Vanilla armor material values for comparison:
        // LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15),
        // CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
        // IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
        // GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
        // DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10);
        
        // TODO: do we really want durability of -1 for wading boots?  does that mean it lasts forever?
        wading_boots_material = EnumHelper.addArmorMaterial("WADING_BOOTS", "biomesoplenty:wading_boots", -1, new int[]{0,0,0,0}, 0);
        mud_armor_material = EnumHelper.addArmorMaterial("MUD", "biomesoplenty:mud_armor", 2, new int[]{1,1,1,1}, 5);
        mud_armor_material.customCraftingMaterial = mudball;
        amethyst_armor_material = EnumHelper.addArmorMaterial("AMETHYST", "biomesoplenty:amethyst_armor", 40, new int[]{6,12,10,6}, 20);
        
        
        wading_boots = registerItem(new ItemWadingBoots(wading_boots_material, 0), "wading_boots");
        mud_helmet = registerItem(new ItemArmor(mud_armor_material, 0, 0), "mud_helmet");
        mud_chestplate = registerItem(new ItemArmor(mud_armor_material, 0, 1), "mud_chestplate");
        mud_leggings = registerItem(new ItemArmor(mud_armor_material, 0, 2), "mud_leggings");
        mud_boots = registerItem(new ItemArmor(mud_armor_material, 0, 3), "mud_boots");
        amethyst_helmet = registerItem(new ItemArmor(amethyst_armor_material, 0, 0), "amethyst_helmet");
        amethyst_chestplate = registerItem(new ItemArmor(amethyst_armor_material, 0, 1), "amethyst_chestplate");
        amethyst_leggings = registerItem(new ItemArmor(amethyst_armor_material, 0, 2), "amethyst_leggings");
        amethyst_boots = registerItem(new ItemArmor(amethyst_armor_material, 0, 3), "amethyst_boots");
    
        
        
        // tools

        // addToolMaterial arguments:
        // (String name, int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability)
        // Vanilla tool material values for comparison:
        // WOOD(0, 59, 2.0F, 0.0F, 15),
        // STONE(1, 131, 4.0F, 1.0F, 5),
        // IRON(2, 250, 6.0F, 2.0F, 14),
        // EMERALD(3, 1561, 8.0F, 3.0F, 10),
        // GOLD(0, 32, 12.0F, 0.0F, 22);
        mud_tool_material = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0.0F, 1);
        mud_tool_material.setRepairItem(new ItemStack(mudball));
        amethyst_tool_material = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5.0F, 16);
        // no repair item for amethyst tool - they can't be repaired

        // ItemAxe and ItemPickaxe have protected constructors - use reflection to construct
        // TODO use utility functions for this
        Constructor<ItemAxe> axeConstructor;
        try
        {
            axeConstructor = ItemAxe.class.getDeclaredConstructor(ToolMaterial.class);
            axeConstructor.setAccessible(true);
            mud_axe = registerItem(axeConstructor.newInstance(mud_tool_material), "mud_axe");
            amethyst_axe = registerItem(axeConstructor.newInstance(amethyst_tool_material), "amethyst_axe");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            Constructor<ItemPickaxe> pickaxeConstructor = ItemPickaxe.class.getDeclaredConstructor(ToolMaterial.class);
            pickaxeConstructor.setAccessible(true);  
            mud_pickaxe = registerItem(pickaxeConstructor.newInstance(mud_tool_material), "mud_pickaxe");
            amethyst_pickaxe = registerItem(pickaxeConstructor.newInstance(amethyst_tool_material), "amethyst_pickaxe");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
                
        // the other tools have public constructors, so we create instances in the normal way
        mud_hoe = registerItem(new ItemHoe(mud_tool_material), "mud_hoe");
        mud_shovel = registerItem(new ItemSpade(mud_tool_material), "mud_shovel");
        mud_sword = registerItem(new ItemSword(mud_tool_material), "mud_sword");
        amethyst_hoe = registerItem(new ItemHoe(amethyst_tool_material), "amethyst_hoe");
        amethyst_shovel = registerItem(new ItemSpade(amethyst_tool_material), "amethyst_shovel");
        amethyst_sword = registerItem(new ItemSword(amethyst_tool_material), "amethyst_sword");
        
        
        mud_scythe = registerItem(new ItemBOPScythe(mud_tool_material), "mud_scythe");
        wood_scythe = registerItem(new ItemBOPScythe(ToolMaterial.WOOD), "wood_scythe");
        stone_scythe = registerItem(new ItemBOPScythe(ToolMaterial.STONE), "stone_scythe");
        iron_scythe = registerItem(new ItemBOPScythe(ToolMaterial.IRON), "iron_scythe");
        gold_scythe = registerItem(new ItemBOPScythe(ToolMaterial.GOLD), "gold_scythe");
        diamond_scythe = registerItem(new ItemBOPScythe(ToolMaterial.EMERALD), "diamond_scythe");
        amethyst_scythe = registerItem(new ItemBOPScythe(amethyst_tool_material), "amethyst_scythe");

        
        biome_finder = registerItem(new ItemBiomeFinder(), "biome_finder");
        enderporter = registerItem(new ItemEnderporter(), "enderporter");
        jar_empty = registerItem(new ItemJarEmpty(), "jar_empty");
        
    }
    
    public static Item registerItem(Item item, String name)
    {
        return registerItem(item, name, CreativeTabBOP.instance);
    }
    
    public static Item registerItem(Item item, String name, CreativeTabs tab)
    {    
        item.setUnlocalizedName(name);
        if (tab != null)
        {
            item.setCreativeTab(CreativeTabBOP.instance);
        }
        GameRegistry.registerItem(item,name);
        BOPCommand.itemCount++;
        
        // register sub types if there are any
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            if (item.getHasSubtypes())
            {
                List<ItemStack> subItems = new ArrayList<ItemStack>();
                item.getSubItems(item, CreativeTabBOP.instance, subItems);
                for (ItemStack subItem : subItems)
                {
                    String subItemName = item.getUnlocalizedName(subItem);
                    subItemName =  subItemName.substring(subItemName.indexOf(".") + 1); // remove 'item.' from the front

                    ModelBakery.addVariantName(item, BiomesOPlenty.MOD_ID + ":" + subItemName);
                    ModelLoader.setCustomModelResourceLocation(item, subItem.getMetadata(), new ModelResourceLocation(BiomesOPlenty.MOD_ID + ":" + subItemName, "inventory"));
                }
            }
            else
            {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(BiomesOPlenty.MOD_ID + ":" + name, "inventory"));
            }
        }
        
        return item;   
    }

}