/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.biome;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.blockplacers.DoublePlantPlacer;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

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
