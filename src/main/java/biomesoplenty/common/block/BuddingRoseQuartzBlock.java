package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;

public class BuddingRoseQuartzBlock extends BuddingAmethystBlock
{
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingRoseQuartzBlock(BlockBehaviour.Properties p_152726_)
    {
        super(p_152726_);
    }

    @Override
    public void randomTick(BlockState p_152728_, ServerLevel p_152729_, BlockPos p_152730_, Random p_152731_)
    {
        if (p_152731_.nextInt(5) == 0)
        {
            Direction direction = DIRECTIONS[p_152731_.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = p_152730_.relative(direction);
            BlockState blockstate = p_152729_.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate))
            {
                block = BOPBlocks.SMALL_ROSE_QUARTZ_BUD;
            }
            else if (blockstate.is(BOPBlocks.SMALL_ROSE_QUARTZ_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction)
            {
                block = BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD;
            }
            else if (blockstate.is(BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction)
            {
                block = BOPBlocks.LARGE_ROSE_QUARTZ_BUD;
            }
            else if (blockstate.is(BOPBlocks.LARGE_ROSE_QUARTZ_BUD) && blockstate.getValue(AmethystClusterBlock.FACING) == direction)
            {
                block = BOPBlocks.ROSE_QUARTZ_CLUSTER;
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                p_152729_.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }
}