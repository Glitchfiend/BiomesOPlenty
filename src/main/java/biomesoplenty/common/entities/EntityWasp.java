/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import biomesoplenty.api.sound.BOPSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityWasp extends EntityFlying implements IMob {
    
    public EntityWasp(World worldIn) {
        super(worldIn);
        this.setSize(0.7F, 0.7F);
        
        this.moveHelper = new EntityWasp.WaspMoveHelper();
        this.tasks.addTask(3, new EntityWasp.AIWaspRandomFly());
        this.tasks.addTask(4, new EntityWasp.AIWaspAttackTarget());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.5D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity target)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        return target.attackEntityFrom(DamageSource.causeMobDamage(this), f);
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
        return BOPSounds.wasp_ambient;
    }
    
    @Override
    protected SoundEvent getHurtSound()
    {
        return BOPSounds.wasp_hurt;
    }
    
    // Helper class representing a point in space that the wasp is targeting for some reason
    class WaspMoveTargetPos
    {
        private EntityWasp wasp = EntityWasp.this;

        public double posX;
        public double posY;
        public double posZ;
        public double distX;
        public double distY;
        public double distZ;
        public double dist;
        public double aimX;
        public double aimY;
        public double aimZ;
        
        public WaspMoveTargetPos()
        {
            this(0, 0, 0);
        }
        
        public WaspMoveTargetPos(double posX, double posY, double posZ)
        {
            this.setTarget(posX, posY, posZ);
        }
        
        public void setTarget(double posX, double posY, double posZ)
        {
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.refresh();
        }
        
        public void refresh()
        {
            this.distX = this.posX - this.wasp.posX;
            this.distY = this.posY - this.wasp.posY;
            this.distZ = this.posZ - this.wasp.posZ;
            
            this.dist = (double)MathHelper.sqrt(this.distX * this.distX + this.distY * this.distY + this.distZ * this.distZ);
            
            // (aimX,aimY,aimZ) is a unit vector in the direction we want to go
            if (this.dist == 0.0D)
            {
                this.aimX = 0.0D;
                this.aimY = 0.0D;
                this.aimZ = 0.0D;
            }
            else
            {
                this.aimX = this.distX / this.dist;
                this.aimY = this.distY / this.dist;
                this.aimZ = this.distZ / this.dist;                
            }
         }
        
        public boolean isBoxBlocked(AxisAlignedBB box)
        {
            //Im assuming this does what getCubes did. If not, im terribly sorry - Topisani
            return !this.wasp.world.getCollisionBoxes(this.wasp, box).isEmpty();
        }
        
        // check nothing will collide with the wasp in the direction of aim, for howFar units (or until the destination - whichever is closer)
        public boolean isPathClear(double howFar)
        {
            howFar = Math.min(howFar, this.dist);
            AxisAlignedBB box = this.wasp.getEntityBoundingBox();
            for (double i = 0.5D; i < howFar; ++i)
            {
                // check there's nothing in the way
                if (this.isBoxBlocked(box.offset(this.aimX * i, this.aimY * i, this.aimZ * i)))
                {
                    return false;
                }
            }
            if (this.isBoxBlocked(box.offset(this.aimX * howFar, this.aimY * howFar, this.aimZ * howFar)))
            {
                return false;
            }
            return true;
        }
        
    }
        
    
    class WaspMoveHelper extends EntityMoveHelper
    {
        // EntityMoveHelper has the boolean 'update' which is set to true when the target is changed, and set to false when a bearing is set
        // So it means 'the target has changed but we're not yet heading for it'
        // We'll re-use it here with a slightly different interpretation
        // Here it will mean 'has a target and not yet arrived'
        
        private EntityWasp wasp = EntityWasp.this;
        private int courseChangeCooldown = 0;
        private double closeEnough = 0.3D;
        private WaspMoveTargetPos targetPos = new WaspMoveTargetPos();

        public WaspMoveHelper()
        {
            super(EntityWasp.this);
        }
                        
        @Override
        public void setMoveTo(double x, double y, double z, double speedIn)
        {
            super.setMoveTo(x,y,z,speedIn);
            this.targetPos.setTarget(x, y, z);
        }

        @Override
        public void onUpdateMoveHelper()
        {
            // if we have arrived at the previous target, or we have no target to aim for, do nothing
            if (this.action != Action.MOVE_TO) {return;}
            
            if (this.courseChangeCooldown-- > 0) {
                // limit the rate at which we change course
                return;
            }
            this.courseChangeCooldown += this.wasp.getRNG().nextInt(5) + 2;
            
            // update the target position
            this.targetPos.refresh();
            
            // accelerate the wasp towards the target
            double acceleration = 0.1D;
            this.wasp.motionX += this.targetPos.aimX * acceleration;
            this.wasp.motionY += this.targetPos.aimY * acceleration;
            this.wasp.motionZ += this.targetPos.aimZ * acceleration;
           
            // rotate to point at target
            this.wasp.renderYawOffset = this.wasp.rotationYaw = -((float)Math.atan2(this.targetPos.distX, this.targetPos.distZ)) * 180.0F / (float)Math.PI;            
            
            // occasionally jerk to the side - makes them more difficult to hit
            if (this.wasp.getRNG().nextInt(5)==0)
            {
                float strafeAmount = (this.wasp.getRNG().nextFloat() * 0.4F) - 0.2F; 
                this.wasp.motionX += (double)(strafeAmount * MathHelper.cos(this.wasp.rotationYaw * (float)Math.PI / 180.0F));
                this.wasp.motionZ += (double)(strafeAmount * MathHelper.sin(this.wasp.rotationYaw * (float)Math.PI / 180.0F));
            }

            // abandon this movement if we have reached the target or there is no longer a clear path to the target
            if (!this.targetPos.isPathClear(5.0D))
            {
                //System.out.println("Abandoning move target - way is blocked" );
                this.action = Action.WAIT;
            } else if (this.targetPos.dist < this.closeEnough) {
                //System.out.println("Arrived (close enough) dist:"+this.targetPos.dist);
                this.action = Action.WAIT;
            }
        }        

    }
    
    // AI class for implementing the random flying behaviour
    class AIWaspRandomFly extends EntityAIBase
    {
        private EntityWasp wasp = EntityWasp.this;
        private WaspMoveTargetPos targetPos = new WaspMoveTargetPos();
        
        public AIWaspRandomFly()
        {
            this.setMutexBits(1);
        }

        // should we choose a new random destination for the wasp to fly to?
        // yes, if the wasp doesn't already have a destination
        @Override
        public boolean shouldExecute()
        {
            //System.out.println(this.wasp.getMoveHelper().isUpdating()?"has a move target":"no move target");
            return !this.wasp.getMoveHelper().isUpdating();
        }
        
        @Override
        public boolean continueExecuting() {return false;}     
        
        // choose a a new random destination for the wasp to fly to
        @Override
        public void startExecuting()
        {            
            Random rand = this.wasp.getRNG();
            // pick a random nearby point and see if we can fly to it
            if (this.tryGoingRandomDirection(rand, 6.0D)) {return;}
            // pick a random closer point to fly to instead
            if (this.tryGoingRandomDirection(rand, 2.0D)) {return;}
            // try going straight along axes (try all 6 directions in random order)
            List<EnumFacing> directions = Arrays.asList(EnumFacing.values());
            Collections.shuffle(directions);
            for (EnumFacing facing : directions)
            {
                if (this.tryGoingAlongAxis(rand, facing, 1.0D)) {return;}
            }
        }
        
        
        // note y direction has a slight downward bias to stop them flying too high
        public boolean tryGoingRandomDirection(Random rand, double maxDistance)
        {
            double dirX = ((rand.nextDouble() * 2.0D - 1.0D) * maxDistance);
            double dirY = ((rand.nextDouble() * 2.0D - 1.1D) * maxDistance);
            double dirZ = ((rand.nextDouble() * 2.0D - 1.0D) * maxDistance);
            return this.tryGoing(dirX, dirY, dirZ);
        }
        
        public boolean tryGoingAlongAxis(Random rand, EnumFacing facing, double maxDistance)
        {
            double dirX = 0.0D;
            double dirY = 0.0D;
            double dirZ = 0.0D;
            switch (facing.getAxis())
            {
                case X:
                    dirX = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                    break;
                case Y:
                    dirY = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                    break;
                case Z: default:
                    dirZ = rand.nextDouble() * facing.getAxisDirection().getOffset() * maxDistance;
                    break;
            }
            return this.tryGoing(dirX, dirY, dirZ);
        }
        
        public boolean tryGoing(double dirX, double dirY, double dirZ)
        {
            //System.out.println("("+dirX+","+dirY+","+dirZ+")");
            this.targetPos.setTarget(this.wasp.posX + dirX, this.wasp.posY + dirY, this.wasp.posZ + dirZ);
            //System.out.println("Testing random move target distance:"+this.targetPos.dist+" direction:("+this.targetPos.aimX+","+this.targetPos.aimY+","+this.targetPos.aimZ+")");
            boolean result = this.targetPos.isPathClear(5.0F);
            if (result)
            {
                this.wasp.getMoveHelper().setMoveTo(this.targetPos.posX, this.targetPos.posY, this.targetPos.posZ, 1.0D);
            }
            return result;
        }
    }

    
    // AI class for implementing the behaviour to target and attack players
    class AIWaspAttackTarget extends EntityAIBase
    {
        private EntityWasp wasp = EntityWasp.this;
        private int attackTick = 0;
        private WaspMoveTargetPos targetPos = new WaspMoveTargetPos();
        
        public AIWaspAttackTarget()
        {
            this.setMutexBits(2);
        }
        
        public boolean attackTargetExists()
        {
            // see if there's actually a living attack target to aim for
            EntityLivingBase attackTarget = this.wasp.getAttackTarget();
            return (attackTarget != null && attackTarget.isEntityAlive());           
        }
        
        @Override
        public boolean shouldExecute()
        {
            // decrement time since last attack
            if (this.attackTick > 0) {--this.attackTick;}
            
            return this.attackTargetExists();
        }

        @Override
        public boolean continueExecuting()
        {
            // decrement time since last attack
            if (this.attackTick > 0) {--this.attackTick;}
            
            if (!this.attackTargetExists()) {return false;}
            
            // focus attack on target position
            EntityLivingBase attackTarget = this.wasp.getAttackTarget();
            this.targetPos.setTarget(attackTarget.posX, attackTarget.posY, attackTarget.posZ);
                      
             // damage the target if it's in range, and it has been long enough since the last attack
            double damageRange = (double)(this.wasp.width + attackTarget.width);
            if (this.attackTick <= 0 && this.targetPos.dist < damageRange)
            {
                this.wasp.attackEntityAsMob(attackTarget);
                this.attackTick = 16; // 16 ticks before next attack
            }
            
            // see if there's a straight path to the target, if there is, aim for it
            if (this.targetPos.isPathClear(5.0D))
            {
                //System.out.println("Setting attack target");
                this.wasp.getMoveHelper().setMoveTo(attackTarget.posX, attackTarget.posY, attackTarget.posZ, 1.0D);
            }
            //System.out.println("dist:"+this.targetPos.dist+" damageRange:"+damageRange+" attackTick:"+attackTick); 
            return true;
        }
    }

    
}
