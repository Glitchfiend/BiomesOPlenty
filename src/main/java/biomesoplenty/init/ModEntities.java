/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.entity.BOPEntities.*;

import java.util.function.Function;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import biomesoplenty.common.entity.item.EntityBoatBOP;
import biomesoplenty.common.entity.projectile.EntityMudball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities
{
    public static void init()
    {
        mudball = registerEntity(FixedEntityTypeBuilder.create(EntityMudball.class, EntityMudball::new), "mudball");
        boat_bop = registerEntity(FixedEntityTypeBuilder.create(EntityBoatBOP.class, EntityBoatBOP::new), "boat_bop");
    }

    public static <T extends Entity> EntityType<T> registerEntity(FixedEntityTypeBuilder<T> typeBuilder, String name)
    {
        EntityType<T> type = typeBuilder.build("biomesoplenty:" + name);
        type.setRegistryName(name);
        ForgeRegistries.ENTITIES.register(type);
        return type;
    }

    // TODO: Remove this once Forge has fixed the bugs with calling build in
    // EntityType.Builder, causing an exception when registering entities
    public static class FixedEntityTypeBuilder<T extends Entity>
    {
        private final Class<? extends T> entityClass;
        private final Function<? super World, ? extends T> factory;
        private boolean serializable = true;
        private boolean summonable = true;

        private FixedEntityTypeBuilder(Class<? extends T> entityClassIn, Function<? super World, ? extends T> factoryIn)
        {
            this.entityClass = entityClassIn;
            this.factory = factoryIn;
        }

        public static <T extends Entity> FixedEntityTypeBuilder<T> create(Class<? extends T> entityClassIn, Function<? super World, ? extends T> factoryIn)
        {
            return new FixedEntityTypeBuilder<T>(entityClassIn, factoryIn);
        }

        public static <T extends Entity> FixedEntityTypeBuilder<T> createNothing(Class<? extends T> entityClassIn)
        {
            return new FixedEntityTypeBuilder<T>(entityClassIn, (p_200708_0_) -> {
                return null;
            });
        }

        public FixedEntityTypeBuilder<T> disableSummoning()
        {
            this.summonable = false;
            return this;
        }

        public FixedEntityTypeBuilder<T> disableSerialization()
        {
            this.serializable = false;
            return this;
        }

        public EntityType<T> build(String id) {
            Type<?> type = null;
            if (this.serializable) {
                try {
                    type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(1519)).getChoiceType(TypeReferences.ENTITY_TYPE, id);
                } catch (IllegalArgumentException e) {
                    // Ignore, we don't care
                }
            }

            return new EntityType<T>(this.entityClass, this.factory, this.serializable, this.summonable, type);
        }
    }
}
