/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.entity.projectile;

import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityMudball extends ProjectileItemEntity
{
    public EntityMudball(EntityType<? extends EntityMudball> type, World world)
    {
        super(type, world);
    }

    public EntityMudball(World world, double x, double y, double z)
    {
        super(BOPEntities.mudball, x, y, z, world);
    }

    public EntityMudball(World world, LivingEntity thrower)
    {
        super(BOPEntities.mudball, thrower, world);
    }

    @Override
    protected Item func_213885_i()
    {
        return BOPItems.mudball;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.MUD, this.world, this.posX, this.posY, this.posZ);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult hit)
    {
        if (hit.getType() == RayTraceResult.Type.ENTITY)
        {
            Entity entity = ((EntityRayTraceResult)hit).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }
}
