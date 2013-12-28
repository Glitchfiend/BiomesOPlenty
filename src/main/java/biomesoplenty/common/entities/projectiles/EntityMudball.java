package biomesoplenty.common.entities.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.ClientProxy;

public class EntityMudball extends EntityThrowable
{
	boolean isClient = BiomesOPlenty.proxy instanceof ClientProxy;

	public EntityMudball(World world)
	{
		super(world);
	}

	public EntityMudball(World world, EntityLivingBase entityLivingBase)
	{
		super(world, entityLivingBase);
	}

	public EntityMudball(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition)
	{
		if (movingObjectPosition.entityHit != null)
		{
			movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
			
			if (movingObjectPosition.entityHit instanceof EntityLivingBase) 
			{
				((EntityLivingBase)movingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 2));
			}
		}

		for (int i = 0; i < 16; ++i)
		{
			BiomesOPlenty.proxy.spawnParticle("mud", posX, posY, posZ);
		}

		if (!worldObj.isRemote)
		{
			this.setDead();
		}
	}
}