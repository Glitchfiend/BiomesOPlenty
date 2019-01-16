/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.Particles;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockFlowerBOP extends BlockFlower
{
    public BlockFlowerBOP(Block.Builder properties)
    {
        super(properties);
    }
    
    @Override
    public void onEntityCollision(IBlockState stateIn, World worldIn, BlockPos pos, Entity entityIn)
    {
    	Block block = stateIn.getBlock();
    	
    	if (entityIn instanceof EntityLivingBase)
    	{
	    	if (block == BOPBlocks.deathbloom)
	    	{
	    		((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 200));
	    	}
	    	if (block == BOPBlocks.burning_blossom)
	    	{
	    		((EntityLivingBase) entityIn).setFire(1);
	    	}
    	}
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
       super.animateTick(stateIn, worldIn, pos, rand);
       Block block = stateIn.getBlock();
       
       if (block == BOPBlocks.deathbloom)
       {
	       if (rand.nextInt(4) == 0)
	       {
	    	   worldIn.addParticle(Particles.MYCELIUM, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
	       }
	       if (rand.nextInt(4) == 0)
	       {
	    	   worldIn.addParticle(Particles.SMOKE, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
	       }
	   }
       
       if (block == BOPBlocks.burning_blossom)
       {
	       if (rand.nextInt(4) == 0)
	       {
	    	   worldIn.addParticle(Particles.FLAME, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
	       }
	       if (rand.nextInt(2) == 0)
	       {
	    	   worldIn.addParticle(Particles.SMOKE, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
	       }
	   }
    }
}
