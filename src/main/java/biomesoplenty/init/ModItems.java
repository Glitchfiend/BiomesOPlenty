/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.item.MusicDiscItemBOP;
import biomesoplenty.common.util.CreativeModeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
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
        BOP_ICON = registerItem(new Item(new Item.Properties()), "bop_icon");

        MUD_BALL = registerItem(new Item(new Item.Properties().tab(CreativeModeTabBOP.INSTANCE)), "mud_ball");
        MUD_BRICK = registerItem(new Item(new Item.Properties().tab(CreativeModeTabBOP.INSTANCE)), "mud_brick");

        MUSIC_DISC_WANDERER = registerItem(new MusicDiscItemBOP("music_disc.wanderer"), "music_disc_wanderer");

        FIR_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "fir_boat");
        REDWOOD_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "redwood_boat");
        CHERRY_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "cherry_boat");
        MAHOGANY_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "mahogany_boat");
        JACARANDA_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "jacaranda_boat");
        PALM_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "palm_boat");
        WILLOW_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "willow_boat");
        DEAD_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "dead_boat");
        MAGIC_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "magic_boat");
        UMBRAN_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "umbran_boat");
        HELLBARK_BOAT = registerItem(new BoatItem(Boat.Type.OAK, (new Item.Properties()).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "hellbark_boat");
    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
