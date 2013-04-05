package tdwp_ftw.biomesop.items.projectiles;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMudball extends EntityThrowable 
{
	public EntityMudball(World par1World)
	{
		super(par1World);
	}

	public EntityMudball(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntityMudball(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null)
		{
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 1);
			((EntityLiving)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300));
		}

		for (int i = 0; i < 16; ++i)
		{
			this.worldObj.spawnParticle("splash", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
} 