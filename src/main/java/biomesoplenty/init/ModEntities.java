/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.common.entity.BoatBOP;
import biomesoplenty.common.entity.ChestBoatBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities
{
    public static void setup()
    {
        registerEntities();
    }

    public static void registerEntities()
    {
        BOPEntities.BOAT = registerEntity(() -> EntityType.Builder.<BoatBOP>of(BoatBOP::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(BiomesOPlenty.MOD_ID + ":boat"), "boat");
        BOPEntities.CHEST_BOAT = registerEntity(() -> EntityType.Builder.<ChestBoatBOP>of(ChestBoatBOP::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(BiomesOPlenty.MOD_ID + ":chest_boat"), "chest_boat");
    }

    public static RegistryObject<EntityType<?>> registerEntity(Supplier<EntityType<?>> typeSupplier, String name)
    {
        return BiomesOPlenty.ENTITY_TYPE_REGISTER.register(name, typeSupplier);
    }
}
