/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FlowerBlockBOP extends FlowerBlock
{
    protected static final VoxelShape SHORT = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
	protected static final VoxelShape NORMAL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    protected static final VoxelShape MEDIUM = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);
	protected static final VoxelShape LARGE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	
    public FlowerBlockBOP(MobEffect effect, int effectDuration, Block.Properties properties)
    {
        super(effect, 0, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
    	Block block = state.getBlock();
        VoxelShape shape = NORMAL;
        
        if (block == BOPBlocks.LAVENDER || block == BOPBlocks.WHITE_LAVENDER || block == BOPBlocks.PINK_HIBISCUS)
        {
        	shape = LARGE;
        }
        if (block == BOPBlocks.PINK_DAFFODIL || block == BOPBlocks.GLOWFLOWER || block == BOPBlocks.WILTED_LILY)
        {
            shape = MEDIUM;
        }
        if (block == BOPBlocks.VIOLET || block == BOPBlocks.ENDBLOOM)
        {
            shape = SHORT;
        }

        Vec3 vec3 = state.getOffset(worldIn, pos);
        return shape.move(vec3.x, vec3.y, vec3.z);
    }
    
    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        Block ground = worldIn.getBlockState(pos.below()).getBlock();

        if (this == BOPBlocks.BURNING_BLOSSOM)
        {
            return ground == Blocks.NETHERRACK || ground == Blocks.SOUL_SAND ||  ground == Blocks.SOUL_SOIL ||  ground == Blocks.CRIMSON_NYLIUM ||  ground == Blocks.WARPED_NYLIUM || super.canSurvive(state, worldIn, pos);
        }
        else if (this == BOPBlocks.WILTED_LILY)
        {
            return ground == BOPBlocks.DRIED_SALT || super.canSurvive(state, worldIn, pos);
        }
        else if (this == BOPBlocks.ENDBLOOM)
        {
            return ground == BOPBlocks.ALGAL_END_STONE || ground == BOPBlocks.UNMAPPED_END_STONE || ground == BOPBlocks.NULL_END_STONE || ground == Blocks.END_STONE || super.canSurvive(state, worldIn, pos);
        }

        return super.canSurvive(state, worldIn, pos);
    }
    
    @Override
    public void entityInside(BlockState stateIn, Level worldIn, BlockPos pos, Entity entityIn)
    {
    	Block block = stateIn.getBlock();

        if (block == BOPBlocks.BURNING_BLOSSOM && entityIn.getType() != EntityType.HOGLIN && entityIn.getType() != EntityType.PIGLIN && entityIn.getType() != EntityType.PIGLIN_BRUTE)
        {
            if (!entityIn.fireImmune())
            {
                entityIn.setRemainingFireTicks(entityIn.getRemainingFireTicks() + 1);
                if (entityIn.getRemainingFireTicks() == 0)
                {
                    entityIn.setSecondsOnFire(1);
                }
            }

            entityIn.hurt(worldIn.damageSources().inFire(), 1.0F);
        }

        super.entityInside(stateIn, worldIn, pos, entityIn);
    }
    
    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
       super.animateTick(stateIn, worldIn, pos, rand);
       Block block = stateIn.getBlock();
       
       if (block == BOPBlocks.BURNING_BLOSSOM)
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
}
