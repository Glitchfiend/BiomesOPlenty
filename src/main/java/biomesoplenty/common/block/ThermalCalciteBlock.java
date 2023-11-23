/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;

import java.util.OptionalInt;

public class ThermalCalciteBlock extends Block
{
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 5);

    public ThermalCalciteBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(5)));
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (worldIn.getBlockState(pos.above()).getFluidState().getType() == Fluids.WATER && worldIn.getBlockState(pos.above()).getFluidState().getAmount() == 8)
        {
            worldIn.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void tick(BlockState p_221369_, ServerLevel p_221370_, BlockPos p_221371_, RandomSource p_221372_) {
        p_221370_.setBlock(p_221371_, updateDistance(p_221369_, p_221370_, p_221371_), 3);
    }

    @Override
    public BlockState updateShape(BlockState p_54440_, Direction p_54441_, BlockState p_54442_, LevelAccessor p_54443_, BlockPos p_54444_, BlockPos p_54445_) {
        int i = getDistanceAt(p_54442_) + 1;
        if (i != 1 || p_54440_.getValue(DISTANCE) != i) {
            p_54443_.scheduleTick(p_54444_, this, 1);
        }

        return p_54440_;
    }

    private static BlockState updateDistance(BlockState p_54436_, LevelAccessor p_54437_, BlockPos p_54438_) {
        int i = 5;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(p_54438_, direction);
            i = Math.min(i, getDistanceAt(p_54437_.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return p_54436_.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistanceAt(BlockState p_54464_) {
        return getOptionalDistanceAt(p_54464_).orElse(5);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState p_277868_) {
        if (p_277868_.getFluidState().is(Fluids.WATER) || p_277868_.getBlock() instanceof IceBlock) {
            return OptionalInt.of(0);
        } else {
            return p_277868_.hasProperty(DISTANCE) ? OptionalInt.of(p_277868_.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_54447_) {
        p_54447_.add(DISTANCE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_54424_) {
        BlockState blockstate = this.defaultBlockState();
        return updateDistance(blockstate, p_54424_.getLevel(), p_54424_.getClickedPos());
    }
}
