package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingRoseQuartzBlock extends AmethystBlock {
    public static final MapCodec<BuddingRoseQuartzBlock> CODEC = simpleCodec(BuddingRoseQuartzBlock::new);
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public MapCodec<BuddingRoseQuartzBlock> codec() {
        return CODEC;
    }

    public BuddingRoseQuartzBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(GROWTH_CHANCE) == 0) {
            Direction direction = DIRECTIONS[randomSource.nextInt(DIRECTIONS.length)];
            BlockPos blockPos2 = blockPos.relative(direction);
            BlockState blockState2 = serverLevel.getBlockState(blockPos2);
            Block block = null;
            if (canClusterGrowAtState(blockState2)) {
                block = BOPBlocks.SMALL_ROSE_QUARTZ_BUD;
            } else if (blockState2.is(BOPBlocks.SMALL_ROSE_QUARTZ_BUD) && blockState2.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD;
            } else if (blockState2.is(BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD) && blockState2.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BOPBlocks.LARGE_ROSE_QUARTZ_BUD;
            } else if (blockState2.is(BOPBlocks.LARGE_ROSE_QUARTZ_BUD) && blockState2.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BOPBlocks.ROSE_QUARTZ_CLUSTER;
            }

            if (block != null) {
                BlockState blockState3 = block.defaultBlockState()
                        .setValue(AmethystClusterBlock.FACING, direction)
                        .setValue(AmethystClusterBlock.WATERLOGGED, blockState2.getFluidState().getType() == Fluids.WATER);
                serverLevel.setBlockAndUpdate(blockPos2, blockState3);
            }

        }
    }

    public static boolean canClusterGrowAtState(BlockState blockState) {
        return blockState.isAir() || blockState.is(Blocks.WATER) && blockState.getFluidState().getAmount() == 8;
    }
}
