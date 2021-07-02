/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import biomesoplenty.common.world.gen.feature.DenseFeatureSpreadConfig;
import biomesoplenty.common.world.gen.placement.BOPPlacements;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;
import net.minecraft.world.gen.placement.Placement;

public class FeatureUtil
{
    public static ConfiguredDecorator<?> denseCount(int count)
    {
        return BOPPlacements.COUNT.configured(new DenseFeatureSpreadConfig(count));
    }
}
