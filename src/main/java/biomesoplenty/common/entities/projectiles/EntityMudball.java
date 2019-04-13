package biomesoplenty.common.entities.projectiles;

import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    
    @SideOnly(Side.CLIENT)
    @Override
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.MUD, this.posX, this.posY, this.posZ);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult hit)
    {
        if (hit.entityHit != null)
        {
            hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}