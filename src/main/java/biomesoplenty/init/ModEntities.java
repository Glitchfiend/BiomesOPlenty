/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.entity.item.BoatEntityBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final EntityType<BoatEntityBOP> boat = EntityType.Builder.<BoatEntityBOP>of(BoatEntityBOP::new, MobCategory.MISC).sized(1.375f, 0.5625f).setCustomClientFactory(BoatEntityBOP::new).clientTrackingRange(10).build(BiomesOPlenty.MOD_ID + ":boat");

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().register(boat.setRegistryName("boat"));
    }
}
