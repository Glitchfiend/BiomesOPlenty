/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class VegetationBlockBOP extends VegetationBlock implements BonemealableBlock
{
    private static final VoxelShape SHAPE = Block.column((double)16.0F, (double)0.0F, (double)13.0F);

    protected VegetationBlockBOP(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3)
    {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader $$0, BlockPos $$1, BlockState $$2)
    {
        return BonemealableBlock.hasSpreadableNeighbourPos($$0, $$1, $$2);
    }

    @Override
    public boolean isBonemealSuccess(Level $$0, RandomSource $$1, BlockPos $$2, BlockState $$3)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel $$0, RandomSource $$1, BlockPos $$2, BlockState $$3)
    {
        BonemealableBlock.findSpreadableNeighbourPos($$0, $$2, $$3).ifPresent(($$1x) -> $$0.setBlockAndUpdate($$1x, this.defaultBlockState()));
    }
}
