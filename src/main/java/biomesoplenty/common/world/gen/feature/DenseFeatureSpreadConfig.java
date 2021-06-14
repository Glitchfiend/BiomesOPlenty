package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class DenseFeatureSpreadConfig implements IPlacementConfig, IFeatureConfig
{
    public static final Codec<DenseFeatureSpreadConfig> CODEC = FeatureSpread.codec(-10, 1024, 1024).fieldOf("count").xmap(DenseFeatureSpreadConfig::new, DenseFeatureSpreadConfig::count).codec();
    private final FeatureSpread count;

    public DenseFeatureSpreadConfig(int count)
    {
        this.count = FeatureSpread.fixed(count);
    }

    public DenseFeatureSpreadConfig(FeatureSpread count)
    {
        this.count = count;
    }

    public FeatureSpread count()
    {
        return this.count;
    }
}
