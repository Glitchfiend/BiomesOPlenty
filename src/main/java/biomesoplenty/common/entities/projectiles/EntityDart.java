package biomesoplenty.common.entities.projectiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.ClientProxy;
import biomesoplenty.api.content.BOPCPotions;

public class EntityDart extends EntityArrow
{
	public static enum DartType
	{
		NORMAL, POISON;
	}

	boolean isClient = BiomesOPlenty.proxy instanceof ClientProxy;

	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int ticksInAir = 0;
	private int damage = 2;
	private DartType type = DartType.NORMAL;

	public EntityDart(World world)
	{
		super(world);
	}

	public EntityDart(World world, EntityLivingBase shootingEntity, float par3)
	{
		super(world, shootingEntity, par3);
	}

	public EntityDart(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	public void onUpdate()
	{
		super.onEntityUpdate();

		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
			prevRotationPitch = rotationPitch = (float)(Math.atan2(motionY, f) * 180.0D / Math.PI);
		}

		Block block = worldObj.getBlock(xTile, yTile, zTile);

		if (block.getMaterial() != Material.air)
		{
			block.setBlockBoundsBasedOnState(worldObj, xTile, yTile, zTile);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(worldObj, xTile, yTile, zTile);

			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(posX, posY, posZ)))
			{
				this.setDead();
			}
		}

		++ticksInAir;
		Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.func_147447_a(vec3, vec31, false, true, false);
		vec3 = Vec3.createVectorHelper(posX, posY, posZ);
		vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);

		if (movingobjectposition != null)
		{
			vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}

		Entity entity = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d0 = 0.0D;
		int l;
		float f1;

		for (l = 0; l < list.size(); ++l)
		{
			Entity entity1 = (Entity)list.get(l);

			if (entity1.canBeCollidedWith() && (entity1 != shootingEntity || ticksInAir >= 5))
			{
				f1 = 0.3F;
				AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f1, f1, f1);
				MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

				if (movingobjectposition1 != null)
				{
					double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

					if (d1 < d0 || d0 == 0.0D)
					{
						entity = entity1;
						d0 = d1;
					}
				}
			}
		}

		if (entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}

		if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

			if (entityplayer.capabilities.disableDamage || shootingEntity instanceof EntityPlayer && !((EntityPlayer)shootingEntity).canAttackPlayer(entityplayer))
			{
				movingobjectposition = null;
			}
		}

		float f2;
		float f3;

		if (movingobjectposition != null)
		{
			if (movingobjectposition.entityHit != null)
			{
				f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);

				DamageSource damagesource = null;

				if (shootingEntity == null)
				{
					damagesource = DamageSource.causeArrowDamage(this, this);
				}
				else
				{
					damagesource = DamageSource.causeArrowDamage(this, shootingEntity);
				}

				if (type == DartType.POISON)
				{
					damage = 1;
					
					if (movingobjectposition.entityHit instanceof EntityLivingBase) 
					{
						((EntityLivingBase)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(BOPCPotions.paralysis.id, 100));
					}
				}

				if (movingobjectposition.entityHit.attackEntityFrom(damagesource, damage))
				{
					if (movingobjectposition.entityHit instanceof EntityLivingBase)
					{
						if (shootingEntity != null && movingobjectposition.entityHit != shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && shootingEntity instanceof EntityPlayerMP)
						{
							((EntityPlayerMP)shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
						}
					}

					this.playSound("random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
					this.setDead();
				}
				else
				{
					motionX *= -0.10000000149011612D;
					motionY *= -0.10000000149011612D;
					motionZ *= -0.10000000149011612D;
					rotationYaw += 180.0F;
					prevRotationYaw += 180.0F;
					ticksInAir = 0;
				}
			}
			else
			{
				xTile = movingobjectposition.blockX;
				yTile = movingobjectposition.blockY;
				zTile = movingobjectposition.blockZ;
				motionX = ((float)(movingobjectposition.hitVec.xCoord - posX));
				motionY = ((float)(movingobjectposition.hitVec.yCoord - posY));
				motionZ = ((float)(movingobjectposition.hitVec.zCoord - posZ));
				f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
				posX -= motionX / f2 * 0.05000000074505806D;
				posY -= motionY / f2 * 0.05000000074505806D;
				posZ -= motionZ / f2 * 0.05000000074505806D;

				for (int p = 0; p < 16; ++p)
				{
					if (isPoisonous())
					{
						BiomesOPlenty.proxy.spawnParticle("poisondart", posX, posY, posZ);
					}
					else
					{
						BiomesOPlenty.proxy.spawnParticle("dart", posX, posY, posZ);
					}
				}

				this.playSound("random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
				this.setDead();
			}
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		f2 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);

		for (rotationPitch = (float)(Math.atan2(motionY, f2) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
		{
			;
		}

		while (rotationPitch - prevRotationPitch >= 180.0F)
		{
			prevRotationPitch += 360.0F;
		}

		while (rotationYaw - prevRotationYaw < -180.0F)
		{
			prevRotationYaw -= 360.0F;
		}

		while (rotationYaw - prevRotationYaw >= 180.0F)
		{
			prevRotationYaw += 360.0F;
		}

		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f4 = 0.99F;
		f1 = 0.05F;

		if (this.isInWater())
		{
			for (int j1 = 0; j1 < 4; ++j1)
			{
				f3 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * f3, posY - motionY * f3, posZ - motionZ * f3, motionX, motionY, motionZ);
			}

			f4 = 0.8F;
		}

		motionX *= f4;
		motionY *= f4;
		motionZ *= f4;
		motionY -= f1;
		this.setPosition(posX, posY, posZ);
		this.func_145775_I();
	}

	public void setDartType(DartType dartType)
	{
		type = dartType;

		byte b0 = dataWatcher.getWatchableObjectByte(16);

		if (dartType == DartType.POISON)
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
		}
	}

	public boolean isPoisonous()
	{
		byte b0 = dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}
}
