/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.item.MusicDiscItemBOP;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.item.BOPItems.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    public static final Food VENISON = (new Food.Builder()).nutrition(3).saturationMod(0.3F).meat().build();
    public static final Food COOKED_VENISON = (new Food.Builder()).nutrition(8).saturationMod(0.8F).meat().build();
    public static final Food TURKEY_LEG = (new Food.Builder()).nutrition(1).saturationMod(0.15F).effect(new EffectInstance(Effects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final Food COOKED_TURKEY_LEG = (new Food.Builder()).nutrition(3).saturationMod(0.3F).meat().build();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{
        mud_ball = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance)), "mud_ball");
        mud_brick = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance)), "mud_brick");
        
        music_disc_wanderer = registerItem(new MusicDiscItemBOP("music_disc.wanderer"), "music_disc_wanderer");

        fir_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "fir_boat");
        redwood_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "redwood_boat");
        cherry_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "cherry_boat");
        mahogany_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "mahogany_boat");
        jacaranda_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "jacaranda_boat");
        palm_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "palm_boat");
        willow_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "willow_boat");
        dead_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "dead_boat");
        magic_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "magic_boat");
        umbran_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "umbran_boat");
        hellbark_boat = registerItem(new BoatItem(BoatEntity.Type.OAK, (new Item.Properties()).stacksTo(1).tab(ItemGroupBOP.instance)), "hellbark_boat");

        venison = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance).food(VENISON)), "venison");
        cooked_venison = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance).food(COOKED_VENISON)), "cooked_venison");
        turkey_leg = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance).food(TURKEY_LEG)), "turkey_leg");
        cooked_turkey_leg = registerItem(new Item(new Item.Properties().tab(ItemGroupBOP.instance).food(COOKED_TURKEY_LEG)), "cooked_turkey_leg");

        bop_icon = registerItem(new Item(new Item.Properties()), "bop_icon");
	}

    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
