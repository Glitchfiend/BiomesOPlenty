package biomesoplenty.common.entities.projectiles;

import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMudball extends EntityThrowable
{

    public EntityMudball(World worldIn)
    {
        super(worldIn);
    }

    public EntityMudball(World worldIn, EntityLivingBase thrower)
    {
        super(worldIn, thrower);
    }

    public EntityMudball(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    public static void registerFixesMudball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "mudball");
    }

    @Override
    protected void onImpact(RayTraceResult hit)
    {
        if (hit.entityHit != null)
        {
            // entity hit isn't damaged
            hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
            if (hit.entityHit instanceof EntityLivingBase)
            {
                ((EntityLivingBase)hit.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 400, 2));
            }
        }

        for (int i = 0; i < 16; ++i)
        {
            BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.MUD, this.posX, this.posY, this.posZ);
        }

        if (!this.world.isRemote)
        {
            this.setDead();
        }
    }
}