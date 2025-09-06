/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static biomesoplenty.api.entity.BOPEntities.*;

public class ModEntities
{
    public static void registerEntities(BiConsumer<ResourceLocation, EntityType<?>> func)
    {
        ORIGIN_OAK_BOAT = register(
                func, "origin_oak_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.ORIGIN_OAK_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        ORIGIN_OAK_CHEST_BOAT = register(
                func, "origin_oak_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.ORIGIN_OAK_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        FIR_BOAT = register(
                func, "fir_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.FIR_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        FIR_CHEST_BOAT = register(
                func, "fir_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.FIR_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        PINE_BOAT = register(
                func, "pine_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.PINE_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        PINE_CHEST_BOAT = register(
                func, "pine_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.PINE_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        MAPLE_BOAT = register(
                func, "maple_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.MAPLE_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        MAPLE_CHEST_BOAT = register(
                func, "maple_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.MAPLE_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        REDWOOD_BOAT = register(
                func, "redwood_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.REDWOOD_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        REDWOOD_CHEST_BOAT = register(
                func, "redwood_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.REDWOOD_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        MAHOGANY_BOAT = register(
                func, "mahogany_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.MAHOGANY_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        MAHOGANY_CHEST_BOAT = register(
                func, "mahogany_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.MAHOGANY_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        JACARANDA_BOAT = register(
                func, "jacaranda_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.JACARANDA_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        JACARANDA_CHEST_BOAT = register(
                func, "jacaranda_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.JACARANDA_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        PALM_BOAT = register(
                func, "palm_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.PALM_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        PALM_CHEST_BOAT = register(
                func, "palm_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.PALM_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        WILLOW_BOAT = register(
                func, "willow_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.WILLOW_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        WILLOW_CHEST_BOAT = register(
                func, "willow_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.WILLOW_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        DEAD_BOAT = register(
                func, "dead_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.DEAD_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        DEAD_CHEST_BOAT = register(
                func, "dead_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.DEAD_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        MAGIC_BOAT = register(
                func, "magic_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.MAGIC_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        MAGIC_CHEST_BOAT = register(
                func, "magic_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.MAGIC_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        UMBRAN_BOAT = register(
                func, "umbran_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.UMBRAN_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        UMBRAN_CHEST_BOAT = register(
                func, "umbran_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.UMBRAN_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        HELLBARK_BOAT = register(
                func, "hellbark_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.HELLBARK_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        HELLBARK_CHEST_BOAT = register(
                func, "hellbark_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.HELLBARK_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        EMPYREAL_BOAT = register(
                func, "empyreal_boat", EntityType.Builder.of(boatFactory(() -> BOPItems.EMPYREAL_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
        EMPYREAL_CHEST_BOAT = register(
                func, "empyreal_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> BOPItems.EMPYREAL_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
        );
    }

    private static <T extends Entity> EntityType<T> register(BiConsumer<ResourceLocation, EntityType<?>> func, ResourceKey<EntityType<?>> key, EntityType.Builder<T> builder)
    {
        var type = builder.build(key);
        func.accept(key.location(), type);
        return type;
    }

    private static <T extends Entity> EntityType<T> register(BiConsumer<ResourceLocation, EntityType<?>> func, String name, EntityType.Builder<T> builder)
    {
        return register(func, entityId(name), builder);
    }

    private static ResourceKey<EntityType<?>> entityId(String name)
    {
        return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name));
    }

    private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> dropItem)
    {
        return (type, level) -> new Boat(type, level, dropItem);
    }

    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> dropItem)
    {
        return (type, level) -> new ChestBoat(type, level, dropItem);
    }
}
