/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.common.item.MusicDiscItemBOP;
import biomesoplenty.common.util.CreativeModeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.base.Suppliers;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

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

        ROSE_QUARTZ_SHARD = registerItem(new Item(new Item.Properties().tab(CreativeModeTabBOP.INSTANCE)), "rose_quartz_shard");

        MUSIC_DISC_WANDERER = registerItem(new MusicDiscItemBOP("music_disc.wanderer"), "music_disc_wanderer");

        Supplier<? extends FlowingFluid> BLOOD_SUPPLIER = Suppliers.memoize(() -> BOPFluids.BLOOD);
        BLOOD_BUCKET = registerItem(new BucketItem(BLOOD_SUPPLIER, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1).tab(CreativeModeTabBOP.INSTANCE)), "blood_bucket");

        FIR_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.FIR_SIGN, BOPBlocks.FIR_WALL_SIGN), "fir_sign");
        REDWOOD_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.REDWOOD_SIGN, BOPBlocks.REDWOOD_WALL_SIGN), "redwood_sign");
        CHERRY_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.CHERRY_SIGN, BOPBlocks.CHERRY_WALL_SIGN), "cherry_sign");
        MAHOGANY_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.MAHOGANY_SIGN, BOPBlocks.MAHOGANY_WALL_SIGN), "mahogany_sign");
        JACARANDA_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.JACARANDA_SIGN, BOPBlocks.JACARANDA_WALL_SIGN), "jacaranda_sign");
        PALM_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.PALM_SIGN, BOPBlocks.PALM_WALL_SIGN), "palm_sign");
        WILLOW_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.WILLOW_SIGN, BOPBlocks.WILLOW_WALL_SIGN), "willow_sign");
        DEAD_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.DEAD_SIGN, BOPBlocks.DEAD_WALL_SIGN), "dead_sign");
        MAGIC_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.MAGIC_SIGN, BOPBlocks.MAGIC_WALL_SIGN), "magic_sign");
        UMBRAN_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.UMBRAN_SIGN, BOPBlocks.UMBRAN_WALL_SIGN), "umbran_sign");
        HELLBARK_SIGN = registerItem(new SignItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTabBOP.INSTANCE), BOPBlocks.HELLBARK_SIGN, BOPBlocks.HELLBARK_WALL_SIGN), "hellbark_sign");

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
