/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class SpanishMossBottomBlock extends GrowingPlantHeadBlock
{
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public SpanishMossBottomBlock(Properties p_i241194_1_) {
        super(p_i241194_1_, Direction.DOWN, SHAPE, false, 0.01D);
    }

    protected int getBlocksToGrowWhenBonemealed(Random p_230332_1_) {
        return NetherVines.getBlocksToGrowWhenBonemealed(p_230332_1_);
    }

    @Override
    protected Block getBodyBlock() {
        return BOPBlocks.SPANISH_MOSS_PLANT;
    }

    protected boolean canGrowInto(BlockState p_230334_1_) {
        return NetherVines.isValidGrowthState(p_230334_1_);
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.relative(this.growthDirection.getOpposite());
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.is(BlockTags.LEAVES) || blockstate.is(BlockTags.LOGS);
        }
    }
}
