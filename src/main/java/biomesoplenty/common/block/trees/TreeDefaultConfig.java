/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Features;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

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
            if (feature.place(new FeaturePlaceContext<>(world, generator, random, pos, Features.OAK.config())))
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
