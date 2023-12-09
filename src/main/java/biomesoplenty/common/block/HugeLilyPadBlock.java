/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.common.block.state.properties.QuarterProperty;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;

public class HugeLilyPadBlock extends BushBlock
{
    public static final MapCodec<HugeLilyPadBlock> CODEC = simpleCodec(HugeLilyPadBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
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
    public BlockState updateShape(BlockState p_51028_, Direction p_49526_, BlockState p_49527_, LevelAccessor p_51029_, BlockPos p_51030_, BlockPos p_49530_)
    {
        boolean lilypadSurvive = true;
        Direction facing = p_51028_.getValue(FACING);
        BlockPos sw = p_51030_;
        BlockPos nw = p_51030_.relative(facing);
        BlockPos ne = nw.relative(facing.getClockWise());
        BlockPos se = p_51030_.relative(facing.getClockWise());

        if (p_51028_.getValue(QUARTER) == QuarterProperty.SOUTH_WEST)
        {
            sw = p_51030_;
            nw = p_51030_.relative(facing);
            ne = nw.relative(facing.getClockWise());
            se = p_51030_.relative(facing.getClockWise());
        }
        if (p_51028_.getValue(QUARTER) == QuarterProperty.NORTH_WEST)
        {
            sw = p_51030_.relative(facing.getOpposite());
            nw = p_51030_;
            ne = p_51030_.relative(facing.getClockWise());
            se = sw.relative(facing.getClockWise());
        }
        if (p_51028_.getValue(QUARTER) == QuarterProperty.NORTH_EAST)
        {
            nw = p_51030_.relative(facing.getCounterClockWise());
            ne = p_51030_;
            se = p_51030_.relative(facing.getOpposite());
            sw = se.relative(facing);
        }
        if (p_51028_.getValue(QUARTER) == QuarterProperty.SOUTH_EAST)
        {
            sw = p_51030_.relative(facing.getCounterClockWise());
            ne = p_51030_.relative(facing);
            se = p_51030_;
            nw = ne.relative(facing.getCounterClockWise());
        }

        if (!p_51029_.getBlockState(sw).is(this) || !p_51029_.getBlockState(nw).is(this) || !p_51029_.getBlockState(ne).is(this) || !p_51029_.getBlockState(se).is(this))
        {
            lilypadSurvive = false;
        }

        if (p_51029_.getBlockState(sw).is(this) && p_51029_.getBlockState(sw).getValue(FACING) != facing && p_51029_.getBlockState(sw).getValue(QUARTER) != QuarterProperty.SOUTH_WEST)
        {
            lilypadSurvive = false;
        }
        if (p_51029_.getBlockState(nw).is(this) && p_51029_.getBlockState(nw).getValue(FACING) != facing && p_51029_.getBlockState(nw).getValue(QUARTER) != QuarterProperty.NORTH_WEST)
        {
            lilypadSurvive = false;
        }
        if (p_51029_.getBlockState(ne).is(this) && p_51029_.getBlockState(ne).getValue(FACING) != facing && p_51029_.getBlockState(ne).getValue(QUARTER) != QuarterProperty.NORTH_EAST)
        {
            lilypadSurvive = false;
        }
        if (p_51029_.getBlockState(se).is(this) && p_51029_.getBlockState(se).getValue(FACING) != facing && p_51029_.getBlockState(se).getValue(QUARTER) != QuarterProperty.SOUTH_EAST)
        {
            lilypadSurvive = false;
        }

        if (lilypadSurvive == false)
        {
            return Blocks.AIR.defaultBlockState();
        }
        else
        {
            return super.updateShape(p_51028_, p_49526_, p_49527_, p_51029_, p_51030_, p_49530_);
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
        if (!p_49499_.isClientSide) {
            BlockPos blockpos = p_49500_.relative(p_49501_.getValue(FACING));
            BlockPos blockpos1 = blockpos.relative(p_49501_.getValue(FACING).getClockWise());
            BlockPos blockpos2 = p_49500_.relative(p_49501_.getValue(FACING).getClockWise());
            p_49499_.setBlock(blockpos, p_49501_.setValue(QUARTER, QuarterProperty.NORTH_WEST), 26);
            p_49499_.setBlock(blockpos1, p_49501_.setValue(QUARTER, QuarterProperty.NORTH_EAST), 26);
            p_49499_.setBlock(blockpos2, p_49501_.setValue(QUARTER, QuarterProperty.SOUTH_EAST), 26);
            p_49499_.blockUpdated(p_49500_, Blocks.AIR);
            p_49499_.blockUpdated(blockpos, Blocks.AIR);
            p_49499_.blockUpdated(blockpos1, Blocks.AIR);
            p_49499_.blockUpdated(blockpos2, Blocks.AIR);
            p_49501_.updateNeighbourShapes(p_49499_, p_49500_, 26);
            p_49501_.updateNeighbourShapes(p_49499_, blockpos, 26);
            p_49501_.updateNeighbourShapes(p_49499_, blockpos1, 26);
            p_49501_.updateNeighbourShapes(p_49499_, blockpos2, 26);
        }
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return PlantType.WATER;
    }

    @Override
    public void entityInside(BlockState p_58164_, Level p_58165_, BlockPos p_58166_, Entity p_58167_)
    {
        super.entityInside(p_58164_, p_58165_, p_58166_, p_58167_);
        if (p_58165_ instanceof ServerLevel && p_58167_ instanceof Boat)
        {
            p_58165_.destroyBlock(new BlockPos(p_58166_), true, p_58167_);
        }

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
