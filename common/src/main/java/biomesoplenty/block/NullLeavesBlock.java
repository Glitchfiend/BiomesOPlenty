/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.OptionalInt;

public class NullLeavesBlock extends TintedParticleLeavesBlock
{
    public NullLeavesBlock(Properties p_273704_)
    {
        super(0.01F, p_273704_);
    }

    @Override
    public void tick(BlockState $$0, ServerLevel $$1, BlockPos $$2, RandomSource $$3) {
        $$1.setBlock($$2, updateDistance($$0, $$1, $$2), 3);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        int $$6 = getDistanceAt(facingState) + 1;
        if ($$6 != 1 || (Integer)state.getValue(DISTANCE) != $$6) {
            tickAccess.scheduleTick(pos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistance(BlockState $$0, LevelAccessor $$1, BlockPos $$2) {
        int $$3 = 7;
        BlockPos.MutableBlockPos $$4 = new BlockPos.MutableBlockPos();
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction $$5 = var5[var7];
            $$4.setWithOffset($$2, $$5);
            $$3 = Math.min($$3, getDistanceAt($$1.getBlockState($$4)) + 1);
            if ($$3 == 1) {
                break;
            }
        }

        return (BlockState)$$0.setValue(DISTANCE, $$3);
    }

    private static int getDistanceAt(BlockState $$0) {
        return getOptionalDistanceAt($$0).orElse(7);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState $$0) {
        if ($$0.is(BOPBlocks.NULL_BLOCK)) {
            return OptionalInt.of(0);
        } else {
            return $$0.hasProperty(DISTANCE) ? OptionalInt.of((Integer)$$0.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext $$0) {
        FluidState $$1 = $$0.getLevel().getFluidState($$0.getClickedPos());
        BlockState $$2 = (BlockState)((BlockState)this.defaultBlockState().setValue(PERSISTENT, true)).setValue(WATERLOGGED, $$1.getType() == Fluids.WATER);
        return updateDistance($$2, $$0.getLevel(), $$0.getClickedPos());
    }

    @Override
    public void animateTick(BlockState p_221253_, Level p_221254_, BlockPos p_221255_, RandomSource p_221256_)
    {
        if (p_221256_.nextInt(20) == 0)
        {
            Direction direction = Direction.DOWN;
            Direction.Axis direction$axis = direction.getAxis();
            BlockPos blockpos = p_221255_.relative(direction);
            BlockState blockstate = p_221254_.getBlockState(blockpos);

            if (!isFaceFull(blockstate.getCollisionShape(p_221254_, blockpos), direction))
            {
                double d0 = (double)p_221255_.getX() + 0.5D;
                double d1 = (double)p_221255_.getY() + 0.5D;
                double d2 = (double)p_221255_.getZ() + 0.5D;

                double d3 = (p_221256_.nextDouble() * 0.5D) - (p_221256_.nextDouble() * 0.5D);
                double d4 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.55D : d3;
                double d5 = direction$axis == Direction.Axis.Y ? (double)direction.getStepY() * 0.55D : d3;
                double d6 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.55D : d3;

                p_221254_.addParticle(ModParticles.NULL, d0 + d4, d1 + d5, d2 + d6, 0.0D, -0.05D, 0.0D);
            }
        }
    }
}