/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities.projectiles;

import java.util.List;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.potion.BOPPotions;
import biomesoplenty.common.item.ItemDart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityDart extends EntityArrow
{
    private static final DataParameter<Byte> TYPE = EntityDataManager.<Byte>createKey(EntityDart.class, DataSerializers.BYTE);
    
    private int ticksInAir = 0;
    
    public EntityDart(World world)
    {
        super(world);
    }
    
    public EntityDart(World world, EntityLivingBase shootingEntity)
    {
        super(world, shootingEntity);
    }
    
    public EntityDart(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }
 
    @Override
    protected void entityInit()
    {
        this.dataManager.register(TYPE, Byte.valueOf((byte)0));
    }
    
    public void setDartType(ItemDart.DartType dartType)
    {
        dataManager.set(TYPE, (byte)dartType.ordinal());
    }
    
    public ItemDart.DartType getDartType()
    {
        return ItemDart.DartType.values()[dataManager.get(TYPE)];
    }
    
    // TODO: read/write to NBT?
    
    
    // Called from onUpdate when it is detected that the dart has hit a solid block
    public void onHitSolidBlock()
    {
        this.playSound(SoundEvents.entity_arrow_hit, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        int itemId = Item.getIdFromItem(BOPItems.dart);
        int itemMeta = this.getDartType().ordinal();
        for (int i = 0; i < 16; ++i)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {itemId, itemMeta});                
        }
        this.setDead();
    }
    
    // Called from onUpdate when it is detected that the dart has hit an entity
    public void onHitEntity(Entity entityHit)
    {
        ItemDart.DartType dartType = this.getDartType();
        DamageSource damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity == null ? this : this.shootingEntity);

        if (dartType == ItemDart.DartType.POISONDART)
        {
            if (entityHit instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityHit).addPotionEffect(new PotionEffect(BOPPotions.paralysis, 100));
            }
        }

        // attempt to damage the entity by the amount in the dartType
        boolean entitySufferedHit = entityHit.attackEntityFrom(damagesource, dartType.getDamageInflicted());
        
        if (entitySufferedHit)
        {
            if (entityHit instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)entityHit;

                // TODO: this bit is from the EntityArrow code - what does it do?  do we need it?
                /*
                if (this.shootingEntity instanceof EntityLivingBase)
                {
                    EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
                    EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, entitylivingbase);
                }
                */

                // TODO: what is all this?  Something about informing the other player in a multiplayer game that he got hit or something?
                if (this.shootingEntity != null && entityHit != this.shootingEntity && entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                {
                    ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new SPacketChangeGameState(6, 0.0F));
                }
            }

            this.playSound(SoundEvents.entity_arrow_hit, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            this.setDead();
        }
        else
        {
            // if the entity didn't suffer a hit, the dart bounces off
            this.bounceOff();
        }
    }
    
    // Reverse the dart's direction and (almost) stop it moving
    public void bounceOff()
    {
        this.motionX *= -0.10000000149011612D;
        this.motionY *= -0.10000000149011612D;
        this.motionZ *= -0.10000000149011612D;
        this.rotationYaw += 180.0F;
        this.prevRotationYaw += 180.0F;
        this.ticksInAir = 0;        
    }
    
    
    
    @Override
    public void onUpdate()
    {
        super.onEntityUpdate();

        // Set the initial pitch and yaw according to the direction the dart is fired
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
        }

        ++this.ticksInAir;
        Vec3d currentPosision = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d futurePosition = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        
        // see if there's a block between the current and future positions
        RayTraceResult blockCollision = this.worldObj.rayTraceBlocks(currentPosision, futurePosition, false, true, false);

        // if there's a block, then correct futurePosition to be the hit surface of the block
        if (blockCollision != null)
        {
            futurePosition = new Vec3d(blockCollision.hitVec.xCoord, blockCollision.hitVec.yCoord, blockCollision.hitVec.zCoord);
        }

        // get a list of all the entities which are anywhere near getting hit
        List<Entity> nearbyEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
        
        // loop through the list of nearby entities, and find the closest one who is in line to be hit by the dart on this tick
        Entity closestOnTargetEntity = null;
        double distanceToClosestOnTargetEntity = Double.POSITIVE_INFINITY;
        float entityCollisionTolerance = 0.3F;
        for (Entity entity : nearbyEntities)
        {
            if (entity.canBeCollidedWith() && (entity != this.shootingEntity || this.ticksInAir >= 5))
            {
                AxisAlignedBB axisalignedbb1 = entity.getEntityBoundingBox().expand((double)entityCollisionTolerance, (double)entityCollisionTolerance, (double)entityCollisionTolerance);
                RayTraceResult entityCollision = axisalignedbb1.calculateIntercept(currentPosision, futurePosition);
                if (entityCollision != null)
                {
                    // skip players who cannot be hit
                    if (entity instanceof EntityPlayer)
                    {
                        EntityPlayer entityplayer = (EntityPlayer)entity;
                        if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
                        {
                            continue;
                        }
                    }
                    double distanceToEntity = currentPosision.distanceTo(entityCollision.hitVec);
                    if (distanceToEntity < distanceToClosestOnTargetEntity)
                    {
                        closestOnTargetEntity = entity;
                        distanceToClosestOnTargetEntity = distanceToEntity;
                    }
                }
            }
        }

        if (closestOnTargetEntity != null)
        {
            // we hit an entity
            this.onHitEntity(closestOnTargetEntity);
        }
        else if (blockCollision != null)
        {
            // we hit a block
            this.onHitSolidBlock();
        }


        // continue the movement (taken unaltered from EntityArrow)
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float dartSpeed = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)dartSpeed) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
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
        
        float dragFactor = 0.99F;
        float accelerationDueToGravity = 0.05F;

        if (this.isInWater())
        {
            for (int l = 0; l < 4; ++l)
            {
                float f4 = 0.25F;
                this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ, new int[0]);
            }
            dragFactor = 0.6F;
        }

        this.motionX *= (double)dragFactor;
        this.motionY *= (double)dragFactor;
        this.motionZ *= (double)dragFactor;
        this.motionY -= (double)accelerationDueToGravity;
        
        this.setPosition(this.posX, this.posY, this.posZ);
        this.doBlockCollisions();
    }

    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(BOPItems.dart);
    }
}