/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class FlowerBlockBOP extends FlowerBlock
{
	protected static final VoxelShape NORMAL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
	protected static final VoxelShape LARGE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    private final MobEffect stewEffect;
    private final int stewEffectDuration;
	
    public FlowerBlockBOP(Supplier<MobEffect> effectSupplier, int effectDuration, Block.Properties properties)
    {
        super(effectSupplier, 0, properties);
        this.stewEffect = effectSupplier.get();
        if (effectSupplier.get().isInstantenous()) {
            this.stewEffectDuration = effectDuration;
        } else {
            this.stewEffectDuration = effectDuration * 20;
        }
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext selectionContext)
    {
    	Block block = state.getBlock();
        
        if (block == BOPBlocks.LAVENDER.get() || block == BOPBlocks.PINK_HIBISCUS.get())
        {
        	return LARGE;
        }
        
        return NORMAL;
    }
    
    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader worldIn, BlockPos pos)
    {
        Block ground = worldIn.getBlockState(pos.below()).getBlock();

        if (this == BOPBlocks.WILDFLOWER.get())
        {
            return ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.WHITE_SAND.get() || ground == BOPBlocks.ORANGE_SAND.get() || ground == BOPBlocks.BLACK_SAND.get() || super.canSurvive(state, worldIn, pos);
        }
        if (this == BOPBlocks.BURNING_BLOSSOM.get())
        {
            return ground == Blocks.NETHERRACK || ground == Blocks.SOUL_SAND ||  ground == Blocks.SOUL_SOIL ||  ground == Blocks.CRIMSON_NYLIUM ||  ground == Blocks.WARPED_NYLIUM || super.canSurvive(state, worldIn, pos);
        }

        return super.canSurvive(state, worldIn, pos);
    }
    
    @Override
    public void entityInside(BlockState stateIn, @NotNull Level worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn)
    {
    	Block block = stateIn.getBlock();
    	
    	if (entityIn instanceof LivingEntity)
    	{
	    	if (block == BOPBlocks.BURNING_BLOSSOM.get())
	    	{
	    		(entityIn).setSecondsOnFire(1);
	    	}
    	}
    }
    
    @Override
    public void animateTick(@NotNull BlockState stateIn, @NotNull Level worldIn, @NotNull BlockPos pos, @NotNull RandomSource rand)
    {
       super.animateTick(stateIn, worldIn, pos, rand);
       Block block = stateIn.getBlock();
       
       if (block == BOPBlocks.BURNING_BLOSSOM.get())
       {
	       if (rand.nextInt(8) == 0)
	       {
	    	   worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D), pos.getY() + 0.75D, pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D), 0.0D, 0.0D, 0.0D);
	       }
	       if (rand.nextInt(4) == 0)
	       {
	    	   worldIn.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D), pos.getY() + 0.75D, pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 4.0D), 0.0D, 0.0D, 0.0D);
	       }
	   }
    }

    @Override
    public @NotNull MobEffect getSuspiciousEffect() {
        return this.stewEffect;
    }

    @Override
    public int getEffectDuration() {
        return this.stewEffectDuration;
    }
}
