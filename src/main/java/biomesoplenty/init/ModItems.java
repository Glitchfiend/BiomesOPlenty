/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.item.BOPItems.*;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.item.ItemBOPRecord;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
	public static void init()
	{
        // TODO: This should be ItemMudball
        mudball = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "mudball");
        mud_brick = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "mud_brick");
        ash = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "ash");
        fleshchunk = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "fleshchunk");
        
        record_wanderer = registerItem(new ItemBOPRecord(BOPSounds.records_wanderer), "record_wanderer");

        // TODO: These all need to be associated with their entities
        fir_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "fir_boat");
        redwood_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "redwood_boat");
        cherry_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "cherry_boat");
        mahogany_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "mahogany_boat");
        jacaranda_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "jacaranda_boat");
        palm_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "palm_boat");
        willow_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "willow_boat");
        magic_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "magic_boat");
        umbran_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "umbran_boat");
        hellbark_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "hellbark_boat");
        ethereal_boat = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "ethereal_boat");

        bop_icon = registerItem(new Item(new Item.Builder()), "bop_icon");
	}

    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
