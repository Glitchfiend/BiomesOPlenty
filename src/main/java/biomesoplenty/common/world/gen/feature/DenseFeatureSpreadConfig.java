package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class DenseFeatureSpreadConfig implements DecoratorConfiguration, FeatureConfiguration
{
    public static final Codec<DenseFeatureSpreadConfig> CODEC = IntProvider.codec(-1024, 1024).fieldOf("count").xmap(DenseFeatureSpreadConfig::new, DenseFeatureSpreadConfig::count).codec();
    private final IntProvider count;

    public DenseFeatureSpreadConfig(int count)
    {
        this.count = ConstantInt.of(count);
    }

    public DenseFeatureSpreadConfig(IntProvider count)
    {
        this.count = count;
    }

    public IntProvider count()
    {
        return this.count;
    }
}
