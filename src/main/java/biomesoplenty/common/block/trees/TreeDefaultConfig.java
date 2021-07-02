/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public abstract class TreeDefaultConfig extends AbstractTreeGrower
{
    @Override
    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean hasFlowers)
    {
        return null;
    }

    protected abstract Feature<? extends TreeConfiguration> getFeature(Random random);

    @Override
    public boolean growTree(ServerLevel world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random)
    {
        Feature<TreeConfiguration> feature = (Feature<TreeConfiguration>)this.getFeature(random);
        if (feature == null)
        {
            return false;
        }
        else
        {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
            if (feature.place(world, generator, random, pos, Features.OAK.config()))
            {
                return true;
            }
            else
            {
                world.setBlock(pos, state, 4);
                return false;
            }
        }
    }
}
