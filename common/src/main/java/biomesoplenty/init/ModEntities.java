/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.entity.BoatBOP;
import biomesoplenty.entity.ChestBoatBOP;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.BiConsumer;

import static biomesoplenty.api.entity.BOPEntities.*;

public class ModEntities
{
    public static void registerEntities(BiConsumer<ResourceLocation, EntityType<?>> func)
    {
        BOAT = register(func, EntityType.Builder.<BoatBOP>of(BoatBOP::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(BiomesOPlenty.MOD_ID + ":boat"), "boat");
        CHEST_BOAT = register(func, EntityType.Builder.<ChestBoatBOP>of(ChestBoatBOP::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(BiomesOPlenty.MOD_ID + ":chest_boat"), "chest_boat");
    }

    private static EntityType<?> register(BiConsumer<ResourceLocation, EntityType<?>> func, EntityType<?> entityType, String name)
    {
        func.accept(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), entityType);
        return entityType;
    }
}
