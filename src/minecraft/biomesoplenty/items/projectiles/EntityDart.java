package biomesoplenty.items.projectiles;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.ClientProxy;

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
	
	public EntityDart(World par1World)
	{
		super(par1World);
	}

	public EntityDart(World par1World, EntityLiving par2EntityLiving, float par3)
	{
		super(par1World, par2EntityLiving, par3);
	}

	public EntityDart(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}
	
	public void onUpdate()
    {
        super.onEntityUpdate();

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        }

        int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

        if (i > 0)
        {
            Block.blocksList[i].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

            if (axisalignedbb != null && axisalignedbb.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
            {
                this.setDead();
            }
        }

        ++this.ticksInAir;
        Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks_do_do(vec3, vec31, false, true);
        vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null)
        {
            vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        Entity entity = null;
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        double d0 = 0.0D;
        int l;
        float f1;

        for (l = 0; l < list.size(); ++l)
        {
            Entity entity1 = (Entity)list.get(l);

            if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
            {
                f1 = 0.3F;
                AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double)f1, (double)f1, (double)f1);
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

            if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).func_96122_a(entityplayer))
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
                f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);

                DamageSource damagesource = null;

                if (this.shootingEntity == null)
                {
                    damagesource = DamageSource.causeArrowDamage(this, this);
                }
                else
                {
                    damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
                }

                if (this.type == DartType.POISON)
                {
                    this.damage = 1;
                    if (movingobjectposition.entityHit instanceof EntityLiving)
                        ((EntityLiving)movingobjectposition.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
                }

                if (movingobjectposition.entityHit.attackEntityFrom(damagesource, this.damage))
                {
                    if (movingobjectposition.entityHit instanceof EntityLiving)
                    {
                        if (this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(6, 0));
                        }
                    }

                    this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    this.setDead();
                }
                else
                {
                    this.motionX *= -0.10000000149011612D;
                    this.motionY *= -0.10000000149011612D;
                    this.motionZ *= -0.10000000149011612D;
                    this.rotationYaw += 180.0F;
                    this.prevRotationYaw += 180.0F;
                    this.ticksInAir = 0;
                }
            }
            else
            {
                this.xTile = movingobjectposition.blockX;
                this.yTile = movingobjectposition.blockY;
                this.zTile = movingobjectposition.blockZ;
                this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
                this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
                this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
                f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
                this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
                this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
                
    			for (int p = 0; p < 16; ++p)
    			{
    				BiomesOPlenty.proxy.spawnParticle("dart", this.posX, this.posY, this.posZ);
    			}
                
                this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.setDead();
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f4 = 0.99F;
        f1 = 0.05F;

        if (this.isInWater())
        {
            for (int j1 = 0; j1 < 4; ++j1)
            {
                f3 = 0.25F;
                this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f3, this.posY - this.motionY * (double)f3, this.posZ - this.motionZ * (double)f3, this.motionX, this.motionY, this.motionZ);
            }

            f4 = 0.8F;
        }

        this.motionX *= (double)f4;
        this.motionY *= (double)f4;
        this.motionZ *= (double)f4;
        this.motionY -= (double)f1;
        this.setPosition(this.posX, this.posY, this.posZ);
        this.doBlockCollisions();
    }
	
	public void setDartType(DartType par1)
    {
        this.type = par1;
        
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1 == DartType.POISON)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public boolean isPoisonous()
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        return (b0 & 1) != 0;
    }
} 