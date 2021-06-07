/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class SpanishMossBottomBlock extends AbstractTopPlantBlock {
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public SpanishMossBottomBlock(AbstractBlock.Properties p_i241194_1_) {
        super(p_i241194_1_, Direction.DOWN, SHAPE, false, 0.01D);
    }

    protected int getBlocksToGrowWhenBonemealed(Random p_230332_1_) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(p_230332_1_);
    }

    protected Block getBodyBlock() {
        return BOPBlocks.spanish_moss_plant;
    }

    protected boolean canGrowInto(BlockState p_230334_1_) {
        return PlantBlockHelper.isValidGrowthState(p_230334_1_);
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.relative(this.growthDirection.getOpposite());
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachToBlock(block)) {
            return false;
        } else {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.getBlock().is(BlockTags.LEAVES) || blockstate.getBlock().is(BlockTags.LOGS);
        }
    }
}
