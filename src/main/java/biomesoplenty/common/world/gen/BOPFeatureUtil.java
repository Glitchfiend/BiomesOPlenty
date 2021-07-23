package biomesoplenty.common.world.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

public final class BOPFeatureUtil {
    // Simulates 1.17 tree gen's sapling provider
    public static boolean isSoil(LevelReader level, BlockPos pos) {
        return level.getBlockState(pos).canSustainPlant(level, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING);
    }
}
