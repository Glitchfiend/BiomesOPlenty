/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import biomesoplenty.common.world.gen.feature.DenseFeatureSpreadConfig;
import biomesoplenty.common.world.gen.placement.BOPPlacements;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;

public class FeatureUtil
{
    public static ConfiguredDecorator<?> denseCount(int count)
    {
        return BOPPlacements.COUNT.configured(new DenseFeatureSpreadConfig(count));
    }
}
