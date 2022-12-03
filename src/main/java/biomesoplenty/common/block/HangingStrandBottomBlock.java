/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingStrandBottomBlock extends GrowingPlantHeadBlock
{
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public HangingStrandBottomBlock(Properties p_i241194_1_) {
        super(p_i241194_1_, Direction.DOWN, SHAPE, false, 0.01D);
    }

    @Override
    protected Block getBodyBlock() {
        return null;
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.relative(this.growthDirection.getOpposite());
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.getMaterial() == Material.STONE;
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_149653_1_)
    {
        return false;
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, RandomSource p_225542_4_)
    {
    }

    @Override
    protected boolean canGrowInto(BlockState p_230334_1_)
    {
        return false;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource p_230332_1_)
    {
        return 0;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_)
    {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level p_180670_1_, RandomSource p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_)
    {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel p_225535_1_, RandomSource p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_)
    {
    }
}
