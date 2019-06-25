/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.item.ItemRecordBOP;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.item.BOPItems.*;

public class ModItems
{
	public static void init()
	{
        mudball = registerItem(new ItemMudball(), "mudball");
        mud_brick = registerItem(new Item(new Item.Properties().group(ItemGroupBOP.instance)), "mud_brick");
        
        record_wanderer = registerItem(new ItemRecordBOP(BOPSounds.records_wanderer), "record_wanderer");

        /*fir_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.FIR, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "fir_boat");
        redwood_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.REDWOOD, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "redwood_boat");
        cherry_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.CHERRY, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "cherry_boat");
        mahogany_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.MAHOGANY, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "mahogany_boat");
        jacaranda_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.JACARANDA, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "jacaranda_boat");
        palm_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.PALM, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "palm_boat");
        willow_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.WILLOW, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "willow_boat");
        dead_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.DEAD, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "dead_boat");
        magic_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.MAGIC, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "magic_boat");
        umbran_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.UMBRAN, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "umbran_boat");
        hellbark_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.HELLBARK, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "hellbark_boat");
        ethereal_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.ETHEREAL, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "ethereal_boat");*/

        bop_icon = registerItem(new Item(new Item.Properties()), "bop_icon");
	}

    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
