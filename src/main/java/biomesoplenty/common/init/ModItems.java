/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.item.BOPItems.*;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.util.inventory.CreativeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{    
    public static void init()
    {
        fleshchunk = registerItem(new Item(),"fleshchunk");
        mudball = registerItem(new ItemMudball(),"mudball");
        turnip_seeds = registerItem(new ItemSeeds(BOPBlocks.turnip_block, Blocks.farmland),"turnip_seeds");
        turnip = registerItem(new ItemFood(3, 0.4F, false),"turnip");
    }
    
    private static Item registerItem(Item item, String name)
    {    
        item.setUnlocalizedName(name).setCreativeTab(CreativeTabBOP.instance);
        GameRegistry.registerItem(item,name);
        BiomesOPlenty.proxy.registerItemForMeshing(item,name);
        return item;   
    }

}