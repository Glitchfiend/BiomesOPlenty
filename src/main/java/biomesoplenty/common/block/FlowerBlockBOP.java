/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class FlowerBlockBOP extends FlowerBlock
{
	protected static final VoxelShape NORMAL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
	protected static final VoxelShape LARGE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    private final Effect stewEffect;
    private final int stewEffectDuration;
	
    public FlowerBlockBOP(Effect p_i49984_1_, int effectDuration, Block.Properties properties)
    {
        super(p_i49984_1_, 0, properties);
        this.stewEffect = p_i49984_1_;
        if (p_i49984_1_.isInstantenous()) {
            this.stewEffectDuration = effectDuration;
        } else {
            this.stewEffectDuration = effectDuration * 20;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
    {
    	Block block = state.getBlock();
        
        if (block == BOPBlocks.lavender || block == BOPBlocks.pink_hibiscus)
        {
        	return LARGE;
        }
        
        return NORMAL;
    }
    
    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block ground = worldIn.getBlockState(pos.below()).getBlock();

        if (this == BOPBlocks.wildflower)
        {
            return ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.white_sand || super.canSurvive(state, worldIn, pos);
        }
        if (this == BOPBlocks.burning_blossom)
        {
            return ground == Blocks.NETHERRACK || ground == Blocks.SOUL_SAND ||  ground == Blocks.SOUL_SOIL ||  ground == Blocks.CRIMSON_NYLIUM ||  ground == Blocks.WARPED_NYLIUM || super.canSurvive(state, worldIn, pos);
        }

        return super.canSurvive(state, worldIn, pos);
    }
    
    @Override
    public void entityInside(BlockState stateIn, World worldIn, BlockPos pos, Entity entityIn)
    {
    	Block block = stateIn.getBlock();
    	
    	if (entityIn instanceof LivingEntity)
    	{
	    	if (block == BOPBlocks.burning_blossom)
	    	{
	    		(entityIn).setSecondsOnFire(1);
	    	}
    	}
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
       super.animateTick(stateIn, worldIn, pos, rand);
       Block block = stateIn.getBlock();
       
       if (block == BOPBlocks.burning_blossom)
       {
	       if (rand.nextInt(8) == 0)
	       {
	    	   worldIn.addParticle(ParticleTypes.FLAME, (double)(pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D)), (double)(pos.getY() + 0.75D), (double)(pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D)), 0.0D, 0.0D, 0.0D);
	       }
	       if (rand.nextInt(4) == 0)
	       {
	    	   worldIn.addParticle(ParticleTypes.SMOKE, (double)(pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D)), (double)(pos.getY() + 0.75D), (double)(pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D)), 0.0D, 0.0D, 0.0D);
	       }
	   }
    }

    @Override
    public Effect getSuspiciousStewEffect() {
        return this.stewEffect;
    }

    @Override
    public int getEffectDuration() {
        return this.stewEffectDuration;
    }
}
