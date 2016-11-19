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

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.api.particle.BOPParticleTypes;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityPixie extends EntityFlying implements IMob {
    
    public EntityPixie(World worldIn) {
        super(worldIn);
        this.setSize(0.7F, 0.7F);
        
        this.moveHelper = new EntityPixie.PixieMoveHelper();
        this.tasks.addTask(3, new EntityPixie.AIPixieRandomFly());
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        // TODO: get right value here   this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D); 
    }
    
    @Override
    protected Item getDropItem()
    {
        return BOPItems.pixie_dust;
    }
    
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (this.world.isRemote)
        {
            for (int i = 0; i < 7; i++)
            {
                if (this.rand.nextInt(2)==0)
                {
                    BiomesOPlenty.proxy.spawnParticle(BOPParticleTypes.PIXIETRAIL, this.posX + (this.rand.nextDouble()) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble()) * (double)this.width);
                }
            }
        }
    }
    
    
    // Checks to make sure the light is not too bright where the mob is spawning
    // This is same code as for EntitySkeleton
    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            // TODO: not sure what's going on here...
            return false;
        }
        else
        {
            int light = this.world.getLightFromNeighbors(blockpos);

            // if it's thundering, force getSkylightSubtracted to 10 before calculating getLightFromNeighbors, then restore it
            if (this.world.isThundering())
            {
                int oldSkyLightSubtracted = this.world.getSkylightSubtracted();
                this.world.setSkylightSubtracted(10);
                light = this.world.getLightFromNeighbors(blockpos);
                this.world.setSkylightSubtracted(oldSkyLightSubtracted);
            }

            return light <= this.rand.nextInt(8);
        }
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        return this.isValidLightLevel() && super.getCanSpawnHere();
    }
        
    
    // TODO - move PixieMoveTargetPos and AIPixieRandomFly outside and implement in a more generic way, to be reused for pixie and wasp 

    
    
    // Helper class representing a point in space that the pixie is targeting for some reason
    class PixieMoveTargetPos
    {
        private EntityPixie pixie = EntityPixie.this;

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
        
        public PixieMoveTargetPos()
        {
            this(0, 0, 0);
        }
        
        public PixieMoveTargetPos(double posX, double posY, double posZ)
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
            this.distX = this.posX - this.pixie.posX;
            this.distY = this.posY - this.pixie.posY;
            this.distZ = this.posZ - this.pixie.posZ;
            
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
            return !this.pixie.world.getCollisionBoxes(this.pixie, box).isEmpty();
        }
        
        // check nothing will collide with the pixie in the direction of aim, for howFar units (or until the destination - whichever is closer)
        public boolean isPathClear(double howFar)
        {
            howFar = Math.min(howFar, this.dist);
            AxisAlignedBB box = this.pixie.getEntityBoundingBox();
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
            
    class PixieMoveHelper extends EntityMoveHelper
    {
        // EntityMoveHelper has the boolean 'update' which is set to true when the target is changed, and set to false when a bearing is set
        // So it means 'the target has changed but we're not yet heading for it'
        // We'll re-use it here with a slightly different interpretation
        // Here it will mean 'has a target and not yet arrived'
        
        private EntityPixie pixie = EntityPixie.this;
        private int courseChangeCooldown = 0;
        private double closeEnough = 0.3D;
        private PixieMoveTargetPos targetPos = new PixieMoveTargetPos();

        public PixieMoveHelper()
        {
            super(EntityPixie.this);
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
            this.courseChangeCooldown += this.pixie.getRNG().nextInt(2) + 2;
            
            // update the target position
            this.targetPos.refresh();
            
            // accelerate the pixie towards the target
            double acceleration = 0.1D;
            this.pixie.motionX += this.targetPos.aimX * acceleration;
            this.pixie.motionY += this.targetPos.aimY * acceleration;
            this.pixie.motionZ += this.targetPos.aimZ * acceleration;
           
            // rotate to point at target
            this.pixie.renderYawOffset = this.pixie.rotationYaw = -((float)Math.atan2(this.targetPos.distX, this.targetPos.distZ)) * 180.0F / (float)Math.PI;            

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
    class AIPixieRandomFly extends EntityAIBase
    {
        private EntityPixie pixie = EntityPixie.this;
        private PixieMoveTargetPos targetPos = new PixieMoveTargetPos();
        
        public AIPixieRandomFly()
        {
            this.setMutexBits(1);
        }

        // should we choose a new random destination for the pixie to fly to?
        // yes, if the pixie doesn't already have a destination
        @Override
        public boolean shouldExecute()
        {
            return !this.pixie.getMoveHelper().isUpdating();
        }
        
        @Override
        public boolean continueExecuting() {return false;}     
        
        // choose a a new random destination for the pixie to fly to
        @Override
        public void startExecuting()
        {            
            Random rand = this.pixie.getRNG();
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
            this.targetPos.setTarget(this.pixie.posX + dirX, this.pixie.posY + dirY, this.pixie.posZ + dirZ);
            //System.out.println("Testing random move target distance:"+this.targetPos.dist+" direction:("+this.targetPos.aimX+","+this.targetPos.aimY+","+this.targetPos.aimZ+")");
            boolean result = this.targetPos.isPathClear(5.0F);
            if (result)
            {
                this.pixie.getMoveHelper().setMoveTo(this.targetPos.posX, this.targetPos.posY, this.targetPos.posZ, 1.0D);
            }
            return result;
        }
    }

    
}
