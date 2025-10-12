/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.block.properties.QuarterProperty;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class HugeLilyPadBlock extends VegetationBlockBOP
{
    public static final MapCodec<HugeLilyPadBlock> CODEC = simpleCodec(HugeLilyPadBlock::new);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<QuarterProperty> QUARTER = EnumProperty.create("quarter", QuarterProperty.class);

    protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.5D, 16.0D);

    public HugeLilyPadBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(QUARTER, QuarterProperty.SOUTH_WEST));
    }

    @Override
    public MapCodec<HugeLilyPadBlock> codec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
    {
        return AABB;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING).add(QUARTER);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction direction, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        boolean lilypadSurvive = true;
        Direction facing = state.getValue(FACING);
        BlockPos sw = pos;
        BlockPos nw = pos.relative(facing);
        BlockPos ne = nw.relative(facing.getClockWise());
        BlockPos se = pos.relative(facing.getClockWise());

        if (state.getValue(QUARTER) == QuarterProperty.SOUTH_WEST)
        {
            sw = pos;
            nw = pos.relative(facing);
            ne = nw.relative(facing.getClockWise());
            se = pos.relative(facing.getClockWise());
        }
        if (state.getValue(QUARTER) == QuarterProperty.NORTH_WEST)
        {
            sw = pos.relative(facing.getOpposite());
            nw = pos;
            ne = pos.relative(facing.getClockWise());
            se = sw.relative(facing.getClockWise());
        }
        if (state.getValue(QUARTER) == QuarterProperty.NORTH_EAST)
        {
            nw = pos.relative(facing.getCounterClockWise());
            ne = pos;
            se = pos.relative(facing.getOpposite());
            sw = se.relative(facing);
        }
        if (state.getValue(QUARTER) == QuarterProperty.SOUTH_EAST)
        {
            sw = pos.relative(facing.getCounterClockWise());
            ne = pos.relative(facing);
            se = pos;
            nw = ne.relative(facing.getCounterClockWise());
        }

        if (!level.getBlockState(sw).is(this) || !level.getBlockState(nw).is(this) || !level.getBlockState(ne).is(this) || !level.getBlockState(se).is(this))
        {
            lilypadSurvive = false;
        }

        if (level.getBlockState(sw).is(this) && level.getBlockState(sw).getValue(FACING) != facing && level.getBlockState(sw).getValue(QUARTER) != QuarterProperty.SOUTH_WEST)
        {
            lilypadSurvive = false;
        }
        if (level.getBlockState(nw).is(this) && level.getBlockState(nw).getValue(FACING) != facing && level.getBlockState(nw).getValue(QUARTER) != QuarterProperty.NORTH_WEST)
        {
            lilypadSurvive = false;
        }
        if (level.getBlockState(ne).is(this) && level.getBlockState(ne).getValue(FACING) != facing && level.getBlockState(ne).getValue(QUARTER) != QuarterProperty.NORTH_EAST)
        {
            lilypadSurvive = false;
        }
        if (level.getBlockState(se).is(this) && level.getBlockState(se).getValue(FACING) != facing && level.getBlockState(se).getValue(QUARTER) != QuarterProperty.SOUTH_EAST)
        {
            lilypadSurvive = false;
        }

        if (lilypadSurvive == false)
        {
            return Blocks.AIR.defaultBlockState();
        }
        else
        {
            return super.updateShape(state, level, tickAccess, pos, direction, facingPos, facingState, random);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49479_)
    {
        Direction direction = p_49479_.getHorizontalDirection();
        BlockPos blockpos = p_49479_.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction);
        BlockPos blockpos2 = blockpos1.relative(direction.getClockWise());
        BlockPos blockpos3 = blockpos.relative(direction.getClockWise());

        Level level = p_49479_.getLevel();
        return mayPlaceOn(level.getBlockState(blockpos1.below()), level, blockpos1.below()) && level.getBlockState(blockpos1).canBeReplaced(p_49479_) && level.getWorldBorder().isWithinBounds(blockpos1) &&
               mayPlaceOn(level.getBlockState(blockpos2.below()), level, blockpos2.below()) && level.getBlockState(blockpos2).canBeReplaced(p_49479_) && level.getWorldBorder().isWithinBounds(blockpos2) &&
               mayPlaceOn(level.getBlockState(blockpos3.below()), level, blockpos3.below()) && level.getBlockState(blockpos3).canBeReplaced(p_49479_) && level.getWorldBorder().isWithinBounds(blockpos3) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    @Override
    public void setPlacedBy(Level p_49499_, BlockPos p_49500_, BlockState p_49501_, @Nullable LivingEntity p_49502_, ItemStack p_49503_)
    {
        super.setPlacedBy(p_49499_, p_49500_, p_49501_, p_49502_, p_49503_);
        if (!p_49499_.isClientSide()) {
            BlockPos blockpos = p_49500_.relative(p_49501_.getValue(FACING));
            BlockPos blockpos1 = blockpos.relative(p_49501_.getValue(FACING).getClockWise());
            BlockPos blockpos2 = p_49500_.relative(p_49501_.getValue(FACING).getClockWise());
            p_49499_.setBlock(blockpos, p_49501_.setValue(QUARTER, QuarterProperty.NORTH_WEST), 26);
            p_49499_.setBlock(blockpos1, p_49501_.setValue(QUARTER, QuarterProperty.NORTH_EAST), 26);
            p_49499_.setBlock(blockpos2, p_49501_.setValue(QUARTER, QuarterProperty.SOUTH_EAST), 26);
            p_49499_.updateNeighborsAt(p_49500_, Blocks.AIR);
            p_49499_.updateNeighborsAt(blockpos, Blocks.AIR);
            p_49499_.updateNeighborsAt(blockpos1, Blocks.AIR);
            p_49499_.updateNeighborsAt(blockpos2, Blocks.AIR);
            p_49501_.updateNeighbourShapes(p_49499_, p_49500_, 26);
            p_49501_.updateNeighbourShapes(p_49499_, blockpos, 26);
            p_49501_.updateNeighbourShapes(p_49499_, blockpos1, 26);
            p_49501_.updateNeighbourShapes(p_49499_, blockpos2, 26);
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean b)
    {
        if (level instanceof ServerLevel && entity instanceof Boat)
        {
            level.destroyBlock(new BlockPos(pos), true, entity);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level p_52878_, BlockPos p_52879_, BlockState p_52880_, Player p_52881_)
    {
        if (!p_52878_.isClientSide())
        {
            if (p_52881_.preventsBlockDrops())
            {
                return super.playerWillDestroy(p_52878_, p_52879_, p_52880_, p_52881_);
            }
            else
            {
                dropResources(p_52880_, p_52878_, p_52879_, null, p_52881_, p_52881_.getMainHandItem());
            }
        }

        return super.playerWillDestroy(p_52878_, p_52879_, p_52880_, p_52881_);
    }

    @Override
    public void playerDestroy(Level p_52865_, Player p_52866_, BlockPos p_52867_, BlockState p_52868_, @Nullable BlockEntity p_52869_, ItemStack p_52870_)
    {
        super.playerDestroy(p_52865_, p_52866_, p_52867_, Blocks.AIR.defaultBlockState(), p_52869_, p_52870_);
    }

    @Override
    public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_)
    {
        BlockPos blockpos = p_51030_.below();
        return this.mayPlaceOn(p_51029_.getBlockState(blockpos), p_51029_, blockpos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_58174_, BlockGetter p_58175_, BlockPos p_58176_)
    {
        FluidState fluidstate = p_58175_.getFluidState(p_58176_);
        FluidState fluidstate1 = p_58175_.getFluidState(p_58176_.above());
        return (fluidstate.getType() == Fluids.WATER || p_58174_.getBlock() instanceof IceBlock) && fluidstate1.getType() == Fluids.EMPTY;
    }

    @Override
    public BlockState rotate(BlockState p_54125_, Rotation p_54126_)
    {
        return p_54125_.setValue(FACING, p_54126_.rotate(p_54125_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_54122_, Mirror p_54123_)
    {
        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
    }
}
