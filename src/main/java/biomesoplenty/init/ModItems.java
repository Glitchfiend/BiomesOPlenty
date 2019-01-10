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
import biomesoplenty.common.item.ItemBOPFood;
import biomesoplenty.common.item.ItemBOPRecord;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemRecord;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
	public static void init()
	{
		berries = registerItem(new ItemBOPFood(1, 0.1F, 8), "berries");
		pear = registerItem(new ItemFood(5, 0.3F, false, new Item.Builder().group(ItemGroupBOP.instance)), "pear");
        peach = registerItem(new ItemFood(5, 0.2F, false, new Item.Builder().group(ItemGroupBOP.instance)), "peach");
        persimmon = registerItem(new ItemFood(5, 0.2F, false, new Item.Builder().group(ItemGroupBOP.instance)), "persimmon");

        // TODO: This should be ItemMudball
        mudball = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "mudball");
        mud_brick = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "mud_brick");
        ash = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "ash");
        fleshchunk = registerItem(new Item(new Item.Builder().group(ItemGroupBOP.instance)), "fleshchunk");

        // TODO: This needs to be ItemBOPRecord and/or registered with its sounds
        //record_wanderer = registerItem(new ItemBOPRecord(BOPSounds.records_wanderer), "record_wanderer");

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
