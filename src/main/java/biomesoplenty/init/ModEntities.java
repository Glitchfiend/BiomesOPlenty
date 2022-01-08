/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.common.entity.BoatBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        BOPEntities.BOAT = registerEntity(EntityType.Builder.<BoatBOP>of(BoatBOP::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(BiomesOPlenty.MOD_ID + ":boat"), "boat");
    }

    public static EntityType<?> registerEntity(EntityType<?> entity, String name)
    {
        entity.setRegistryName(name);
        ForgeRegistries.ENTITIES.register(entity);
        return entity;
    }
}
