/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItems.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItemHelper;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.item.ItemGem;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.item.ItemWadingBoots;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;

public class ModItems
{    
    public static void init()
    {
        fleshchunk = registerItem(new Item(), "fleshchunk");
        mudball = registerItem(new ItemMudball(), "mudball");
        turnip_seeds = registerItem(new ItemSeeds(BOPBlocks.turnip_block, Blocks.farmland), "turnip_seeds");
        turnip = registerItem(new ItemFood(3, 0.4F, false), "turnip");
        persimmon = registerItem(new ItemFood(5, 0.2F, false), "persimmon");
        peach = registerItem(new ItemFood(5, 0.5F, false), "peach");
        pear = registerItem(new ItemFood(5, 0.3F, false), "pear");
        crystal_shard = registerItem(new Item(), "crystal_shard");
        honeycomb = registerItem(new Item(), "honeycomb");
        filled_honeycomb = registerItem(new ItemFood(3, 0.4F, false), "filled_honeycomb");
        gem = registerItem(new ItemGem(), "gem");
        ash = registerItem(new Item(), "ash");
        berries = registerItem(new ItemFood(1, 0.1F, false), "berries");
        wildcarrots = registerItem(new ItemFood(3, 0.5F, false), "wildcarrots");
    
        // armor
        // TODO: do we really want durability of -1?  does that mean it lasts forever?
        BOPItemHelper.wading_boots_material = EnumHelper.addArmorMaterial("WADING_BOOTS", "biomesoplenty:wading_boots", -1, new int[]{0,0,0,0}, 0);
        wading_boots = registerItem(new ItemWadingBoots(BOPItemHelper.wading_boots_material, 0), "wading_boots");
    
        
        // tools

        // addToolMaterial arguments:
        // (String name, int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability)
        // Vanilla tool material values for comparison:
        // WOOD(0, 59, 2.0F, 0.0F, 15),
        // STONE(1, 131, 4.0F, 1.0F, 5),
        // IRON(2, 250, 6.0F, 2.0F, 14),
        // EMERALD(3, 1561, 8.0F, 3.0F, 10),
        // GOLD(0, 32, 12.0F, 0.0F, 22);
        ToolMaterial mud_tool_material = EnumHelper.addToolMaterial("MUD", 0, 32, 0.5F, 0.0F, 1);
        mud_tool_material.setRepairItem(new ItemStack(mudball));
        ToolMaterial amethyst_tool_material = EnumHelper.addToolMaterial("AMETHYST", 4, 2013, 15.0F, 5.0F, 16);
        // no repair item for amethyst tool - they can't be repaired

        // ItemAxe and ItemPickaxe have protected constructors - use reflection to construct
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
        
    }
    
    public static Item registerItem(Item item, String name)
    {    
        item.setUnlocalizedName(name).setCreativeTab(CreativeTabBOP.instance);
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