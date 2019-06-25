/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.item.BOPItems.bop_icon;
import static biomesoplenty.api.item.BOPItems.cherry_boat;
import static biomesoplenty.api.item.BOPItems.dead_boat;
import static biomesoplenty.api.item.BOPItems.ethereal_boat;
import static biomesoplenty.api.item.BOPItems.fir_boat;
import static biomesoplenty.api.item.BOPItems.hellbark_boat;
import static biomesoplenty.api.item.BOPItems.jacaranda_boat;
import static biomesoplenty.api.item.BOPItems.magic_boat;
import static biomesoplenty.api.item.BOPItems.mahogany_boat;
import static biomesoplenty.api.item.BOPItems.mud_brick;
import static biomesoplenty.api.item.BOPItems.mudball;
import static biomesoplenty.api.item.BOPItems.palm_boat;
import static biomesoplenty.api.item.BOPItems.record_wanderer;
import static biomesoplenty.api.item.BOPItems.redwood_boat;
import static biomesoplenty.api.item.BOPItems.umbran_boat;
import static biomesoplenty.api.item.BOPItems.willow_boat;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.entity.item.EntityBoatBOP;
import biomesoplenty.common.item.ItemMudball;
import biomesoplenty.common.item.ItemRecordBOP;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
	public static void init()
	{
        mudball = registerItem(new ItemMudball(), "mudball");
        mud_brick = registerItem(new Item(new Item.Properties().group(ItemGroupBOP.instance)), "mud_brick");
        
        record_wanderer = registerItem(new ItemRecordBOP(BOPSounds.records_wanderer), "record_wanderer");

        fir_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.FIR, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "fir_boat");
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
        ethereal_boat = registerItem(new ItemBoatBOP(EntityBoatBOP.Type.ETHEREAL, (new Item.Properties()).maxStackSize(1).group(ItemGroupBOP.instance)), "ethereal_boat");

        bop_icon = registerItem(new Item(new Item.Properties()), "bop_icon");
	}

    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
