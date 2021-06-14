package biomesoplenty.common.world.gen.placement;

import biomesoplenty.common.world.gen.feature.DenseFeatureSpreadConfig;
import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.placement.SimplePlacement;

public class BOPCountPlacement extends SimplePlacement<DenseFeatureSpreadConfig>
{
    public BOPCountPlacement(Codec<DenseFeatureSpreadConfig> codec)
    {
        super(codec);
    }

    public Stream<BlockPos> place(Random random, DenseFeatureSpreadConfig config, BlockPos pos)
    {
        return IntStream.range(0, config.count().sample(random)).mapToObj((p_242878_1_) -> {
            return pos;
        });
    }
}