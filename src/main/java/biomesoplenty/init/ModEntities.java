/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.entity.BOPEntities.boat_bop;
import static biomesoplenty.api.entity.BOPEntities.mudball;

import java.util.function.Function;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entity.item.EntityBoatBOP;
import biomesoplenty.common.entity.item.RenderBoatBOP;
import biomesoplenty.common.entity.projectile.EntityMudball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities
{
    public static void init()
    {
        mudball = createEntity(EntityMudball.class, EntityMudball::new, "mudball", 64, 10, true);
        boat_bop = createEntity(EntityBoatBOP.class, EntityBoatBOP::new, "boat_bop", 80, 3, true);
    }

    public static <T extends Entity> EntityType<T> createEntity(Class<T> entityClass, Function<? super World, T> entityInstance, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        ResourceLocation location = new ResourceLocation("biomesoplenty", name);
        EntityType<T> type = EntityType.Builder.<T>create(entityClass, entityInstance).tracker(trackingRange, updateFrequency, sendsVelocityUpdates).build(location.toString());
        type.setRegistryName(name);
        ForgeRegistries.ENTITIES.register(type);
        return type;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMudball.class, manager -> new RenderSprite<>(manager, BOPItems.mudball, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(EntityBoatBOP.class, manager -> new RenderBoatBOP(manager));
    }
}
