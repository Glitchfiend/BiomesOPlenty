/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import biomesoplenty.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BrimstoneFumaroleBlock extends Block
{
    protected static final VoxelShape SHAPE_TOP = Block.box(4.0D, 10.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape SHAPE_BOTTOM = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D);
    protected static final VoxelShape FULL_SHAPE = Shapes.or(SHAPE_TOP, SHAPE_BOTTOM);

    public BrimstoneFumaroleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos p_153778_, BlockState p_153779_, Entity p_153780_)
    {
        if (!p_153780_.fireImmune() && p_153780_ instanceof LivingEntity) {
            p_153780_.hurt(level.damageSources().source(BOPDamageTypes.FUMAROLE), 1.0F);
        }

        super.stepOn(level, p_153778_, p_153779_, p_153780_);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (worldIn.getBlockState(pos.above()).isAir())
        {
            worldIn.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), 0.0D, 0.02D, 0.0D);

            if (rand.nextInt(6) == 0)
            {
                for (int i = 0; i < 5; i++)
                {
                    worldIn.addParticle(ParticleTypes.LAVA, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return FULL_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());
        return groundState.is(ModTags.Blocks.BRIMSTONE_DECORATION_PLACEABLE) && groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState p_51032_, Direction p_51033_, BlockState p_51034_, LevelAccessor p_51035_, BlockPos p_51036_, BlockPos p_51037_) {
        return !p_51032_.canSurvive(p_51035_, p_51036_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51032_, p_51033_, p_51034_, p_51035_, p_51036_, p_51037_);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType)
    {
        return false;
    }
}
