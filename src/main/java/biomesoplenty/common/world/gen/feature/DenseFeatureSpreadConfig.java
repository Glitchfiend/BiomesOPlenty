package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.UniformInt;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;

public class DenseFeatureSpreadConfig implements DecoratorConfiguration, FeatureConfiguration
{
    public static final Codec<DenseFeatureSpreadConfig> CODEC = UniformInt.codec(-10, 1024, 1024).fieldOf("count").xmap(DenseFeatureSpreadConfig::new, DenseFeatureSpreadConfig::count).codec();
    private final UniformInt count;

    public DenseFeatureSpreadConfig(int count)
    {
        this.count = UniformInt.fixed(count);
    }

    public DenseFeatureSpreadConfig(UniformInt count)
    {
        this.count = count;
    }

    public UniformInt count()
    {
        return this.count;
    }
}
