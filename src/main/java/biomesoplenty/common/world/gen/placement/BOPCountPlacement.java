package biomesoplenty.common.world.gen.placement;

import biomesoplenty.common.world.gen.feature.DenseFeatureSpreadConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.placement.RepeatingDecorator;

import java.util.Random;

public class BOPCountPlacement extends RepeatingDecorator<DenseFeatureSpreadConfig>
{
    public BOPCountPlacement(Codec<DenseFeatureSpreadConfig> codec)
    {
        super(codec);
    }

    @Override
    protected int count(Random random, DenseFeatureSpreadConfig config, BlockPos blockPos) {
        return config.count().sample(random);
    }
}