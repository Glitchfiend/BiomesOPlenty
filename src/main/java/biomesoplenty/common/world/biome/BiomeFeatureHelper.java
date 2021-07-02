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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.levelgen.feature.blockplacers.DoublePlantPlacer;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

public class BiomeFeatureHelper
{
    public static RandomPatchConfiguration createClusterConfiguration(BlockState state)
    {
        return (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(state), new SimpleBlockPlacer())).tries(64).noProjection().build();
    }

    public static RandomPatchConfiguration createClusterConfigurationDouble(BlockState state)
    {
        return (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(state), new DoublePlantPlacer())).tries(64).noProjection().build();
    }

    public static RandomPatchConfiguration createClusterConfigurationDoubleProjects(BlockState state)
    {
        return (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(state), new DoublePlantPlacer())).tries(64).build();
    }

    public static RandomPatchConfiguration createClusterConfigurationDoubleWater(BlockState state)
    {
        return (new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(state), new DoublePlantPlacer())).canReplace().tries(64).noProjection().build();
    }

}
