package biomesoplenty.common.world.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public final class BOPFeatureUtil {
    // Simulates 1.17 tree gen's sapling provider
    public static boolean isSoil(LevelReader level, BlockPos pos) {
        return Blocks.OAK_SAPLING.defaultBlockState().canSurvive(level, pos);
    }
}
