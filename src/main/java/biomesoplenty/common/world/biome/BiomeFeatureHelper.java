/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.biome;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class BiomeFeatureHelper
{
    public static BlockClusterFeatureConfig createClusterConfiguration(BlockState state)
    {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(state), new SimpleBlockPlacer())).tries(64).noProjection().build();
    }

    public static BlockClusterFeatureConfig createClusterConfigurationDouble(BlockState state)
    {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(state), new DoublePlantBlockPlacer())).tries(64).noProjection().build();
    }

    public static BlockClusterFeatureConfig createClusterConfigurationDoubleProjects(BlockState state)
    {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(state), new DoublePlantBlockPlacer())).tries(64).build();
    }

    public static BlockClusterFeatureConfig createClusterConfigurationDoubleWater(BlockState state)
    {
        return (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(state), new DoublePlantBlockPlacer())).canReplace().tries(64).noProjection().build();
    }

}
