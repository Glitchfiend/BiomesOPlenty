/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

public class WaterlilyBlockBOP extends BushBlock
{
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public WaterlilyBlockBOP(BlockBehaviour.Properties p_58162_)
    {
        super(p_58162_);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return SHAPE;
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
}