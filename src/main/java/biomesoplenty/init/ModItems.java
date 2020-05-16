/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.common.entity.item.BoatEntityBOP;
import biomesoplenty.common.item.BoatItemBOP;
import biomesoplenty.common.item.MusicDiscItemBOP;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.item.BOPItems.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{
        mud_ball = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance)), "mud_ball");
        mud_brick = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance)), "mud_brick");
        
        music_disc_wanderer = registerItem(new MusicDiscItemBOP(BOPSounds.MUSIC_WANDERER_NAME), "music_disc_wanderer");

        fir_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.FIR, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "fir_boat");
        redwood_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.REDWOOD, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "redwood_boat");
        cherry_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.CHERRY, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "cherry_boat");
        mahogany_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.MAHOGANY, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "mahogany_boat");
        jacaranda_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.JACARANDA, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "jacaranda_boat");
        palm_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.PALM, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "palm_boat");
        willow_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.WILLOW, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "willow_boat");
        dead_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.DEAD, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "dead_boat");
        magic_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.MAGIC, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "magic_boat");
        umbran_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.UMBRAN, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "umbran_boat");
        hellbark_boat = registerItem(new BoatItemBOP(BoatEntityBOP.Type.HELLBARK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "hellbark_boat");

        bop_icon = registerItem(new Item(new Item.Properties()), "bop_icon");
	}

    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
