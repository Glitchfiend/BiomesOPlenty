/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block.trees;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.BigTree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class BigTreeDefaultConfig extends BigTree
{
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean hasFlowers)
    {
        return null;
    }

    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random random)
    {
        return null;
    }

    protected abstract Feature<? extends BaseTreeFeatureConfig> getFeature(Random random);
    protected abstract Feature<? extends BaseTreeFeatureConfig> getBigFeature(Random random);

    @Override
    public boolean growTree(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random)
    {
        for (int i = 0; i >= -1; --i)
        {
            for (int j = 0; j >= -1; --j)
            {
                if (isTwoByTwoSapling(state, world, pos, i, j))
                {
                    return this.placeMega(world, generator, pos, state, random, i, j);
                }
            }
        }

        Feature<BaseTreeFeatureConfig> feature = (Feature<BaseTreeFeatureConfig>)this.getFeature(random);

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

    @Override
    public boolean placeMega(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random, int x, int z)
    {
        Feature<BaseTreeFeatureConfig> feature = (Feature<BaseTreeFeatureConfig>)this.getBigFeature(random);
        if (feature == null)
        {
            return false;
        }
        else
        {
            BlockState blockstate = Blocks.AIR.defaultBlockState();
            world.setBlock(pos.offset(x, 0, z), blockstate, 4);
            world.setBlock(pos.offset(x + 1, 0, z), blockstate, 4);
            world.setBlock(pos.offset(x, 0, z + 1), blockstate, 4);
            world.setBlock(pos.offset(x + 1, 0, z + 1), blockstate, 4);
            if (feature.place(world, generator, random, pos.offset(x, 0, z), Features.OAK.config()))
            {
                return true;
            }
            else
            {
                world.setBlock(pos.offset(x, 0, z), state, 4);
                world.setBlock(pos.offset(x + 1, 0, z), state, 4);
                world.setBlock(pos.offset(x, 0, z + 1), state, 4);
                world.setBlock(pos.offset(x + 1, 0, z + 1), state, 4);
                return false;
            }
        }
    }

}
