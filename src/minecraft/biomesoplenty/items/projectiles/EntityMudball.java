package biomesoplenty.items.projectiles;

import net.minecraft.entity.EntityLiving;
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
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) 
	{
        
		if (par1MovingObjectPosition.entityHit != null)
		{
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
			if (par1MovingObjectPosition.entityHit instanceof EntityLiving)
			    ((EntityLiving)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 2));
		}

			for (int i = 0; i < 16; ++i)
			{
				BiomesOPlenty.proxy.spawnParticle("mud", this.posX, this.posY, this.posZ);
			}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
} 