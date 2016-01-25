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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntitySnail extends EntityLiving implements IMob {
    
    public EntitySnail(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
    }
    
    // Checks to make sure the light is not too bright where the mob is spawning
    // This is same code as for EntitySkeleton
    protected boolean isValidLightLevel()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

        if (this.worldObj.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
        {
            // TODO: not sure what's going on here...
            return false;
        }
        else
        {
            int light = this.worldObj.getLightFromNeighbors(blockpos);

            // if it's thundering, force getSkylightSubtracted to 10 before calculating getLightFromNeighbors, then restore it
            if (this.worldObj.isThundering())
            {
                int oldSkyLightSubtracted = this.worldObj.getSkylightSubtracted();
                this.worldObj.setSkylightSubtracted(10);
                light = this.worldObj.getLightFromNeighbors(blockpos);
                this.worldObj.setSkylightSubtracted(oldSkyLightSubtracted);
            }

            return light >= 3;
        }
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        return this.isValidLightLevel() && super.getCanSpawnHere();
    }
    
}
