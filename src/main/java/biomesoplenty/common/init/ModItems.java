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
import biomesoplenty.common.item.ItemGem;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
    }
    
    private static Item registerItem(Item item, String name)
    {    
        item.setUnlocalizedName(name).setCreativeTab(CreativeTabBOP.instance);
        GameRegistry.registerItem(item,name);
    
        
        
        
        
        // register sub types if there are any
        if (item.getHasSubtypes())
        {
            List<ItemStack> subItems = new ArrayList<ItemStack>();
            item.getSubItems(item, CreativeTabBOP.instance, subItems);
            for (ItemStack subItem : subItems)
            {
                String subItemName = item.getUnlocalizedName(subItem);
                subItemName =  subItemName.substring(subItemName.indexOf(".") + 1); // remove 'item.' from the front
                System.out.println("adding variant " + subItemName);
                BiomesOPlenty.proxy.addVariantName(item, BiomesOPlenty.MOD_ID + ":" + subItemName);
                BiomesOPlenty.proxy.registerItemForMeshing(item, subItem.getMetadata(), subItemName);
            }
        }
        else
        {
            BiomesOPlenty.proxy.registerItemForMeshing(item,name);
        }
        
        return item;   
    }

}