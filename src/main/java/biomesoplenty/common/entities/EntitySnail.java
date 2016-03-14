/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.entities;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor_double(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);
        if (this.worldObj.getBlockState(blockpos.down()).getBlock() != Blocks.grass && this.worldObj.getBlockState(blockpos.down()).getBlock() != BOPBlocks.grass)
        {
        	return false;
        }
        else
        {
        	if (blockpos.getY() <= this.worldObj.getSeaLevel())
        	{
        		return false;
        	}
        	else
        	{
        	return this.worldObj.getLight(blockpos) > 6 && super.getCanSpawnHere();
        	}
        }
    }
    
}
