/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItems.*;

import java.util.ArrayList;
import java.util.List;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItemHelper;
import biomesoplenty.common.handler.GuiEventHandler;
import biomesoplenty.common.item.ItemGem;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.item.ItemWadingBoots;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

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
    
        // armor
        // TODO: do we really want durability of -1?  does that mean it lasts forever?
        BOPItemHelper.wading_boots_material = EnumHelper.addArmorMaterial("WADING_BOOTS", "biomesoplenty:wading_boots", -1, new int[]{0,0,0,0}, 0);
        wading_boots = registerItem(new ItemWadingBoots(BOPItemHelper.wading_boots_material, 0), "wading_boots");
    
    }
    
    public static Item registerItem(Item item, String name)
    {    
        item.setUnlocalizedName(name).setCreativeTab(CreativeTabBOP.instance);
        GameRegistry.registerItem(item,name);
        GuiEventHandler.itemCount++;
        
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