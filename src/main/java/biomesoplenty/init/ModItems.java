/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.item.BOPItems.ash;
import static biomesoplenty.api.item.BOPItems.boat_cherry;
import static biomesoplenty.api.item.BOPItems.boat_ethereal;
import static biomesoplenty.api.item.BOPItems.boat_fir;
import static biomesoplenty.api.item.BOPItems.boat_hellbark;
import static biomesoplenty.api.item.BOPItems.boat_jacaranda;
import static biomesoplenty.api.item.BOPItems.boat_magic;
import static biomesoplenty.api.item.BOPItems.boat_mahogany;
import static biomesoplenty.api.item.BOPItems.boat_palm;
import static biomesoplenty.api.item.BOPItems.boat_redwood;
import static biomesoplenty.api.item.BOPItems.boat_umbran;
import static biomesoplenty.api.item.BOPItems.boat_willow;
import static biomesoplenty.api.item.BOPItems.bop_icon;
import static biomesoplenty.api.item.BOPItems.fleshchunk;
import static biomesoplenty.api.item.BOPItems.mud_brick;
import static biomesoplenty.api.item.BOPItems.mudball;
import static biomesoplenty.api.item.BOPItems.record_wanderer;

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
        boat_fir = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_fir");
        boat_redwood = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_redwood");
        boat_cherry = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_cherry");
        boat_mahogany = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_mahogany");
        boat_jacaranda = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_jacaranda");
        boat_palm = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_palm");
        boat_willow = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_willow");
        boat_magic = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_magic");
        boat_umbran = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_umbran");
        boat_hellbark = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_hellbark");
        boat_ethereal = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "boat_ethereal");

        bop_icon = registerItem(new Item(new Item.Builder()), "bop_icon");
	}

    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
