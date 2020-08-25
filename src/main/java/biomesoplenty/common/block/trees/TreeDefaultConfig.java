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
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class TreeDefaultConfig extends Tree
{
    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean hasFlowers)
    {
        return null;
    }

    protected abstract Feature<? extends BaseTreeFeatureConfig> getFeature(Random random);

    @Override
    public boolean growTree(ServerWorld world, ChunkGenerator generator, BlockPos pos, BlockState state, Random random)
    {
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
}
