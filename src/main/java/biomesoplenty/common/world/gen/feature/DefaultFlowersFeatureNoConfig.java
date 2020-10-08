/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public abstract class DefaultFlowersFeatureNoConfig extends FlowersFeature<NoFeatureConfig>
{
    public DefaultFlowersFeatureNoConfig()
    {
        super(NoFeatureConfig.CODEC.stable());
    }

    @Override
    public boolean isValid(IWorld world, BlockPos p_225559_2_, NoFeatureConfig config)
    {
        return true;
    }

    @Override
    public int getCount(NoFeatureConfig config)
    {
        return 64;
    }

    @Override
    public BlockPos getPos(Random random, BlockPos pos, NoFeatureConfig config)
    {
        return pos.offset(random.nextInt(7) - random.nextInt(7), random.nextInt(3) - random.nextInt(3), random.nextInt(7) - random.nextInt(7));
    }
}
