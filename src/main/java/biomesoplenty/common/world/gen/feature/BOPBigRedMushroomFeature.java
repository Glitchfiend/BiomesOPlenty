/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.HugeRedMushroomFeature;

import java.util.Random;

public class BOPBigRedMushroomFeature extends HugeRedMushroomFeature
{
    public BOPBigRedMushroomFeature(Codec<HugeMushroomFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    protected void placeTrunk(LevelAccessor world, Random random, BlockPos pos, HugeMushroomFeatureConfiguration config, int height, BlockPos.MutableBlockPos mutablePos)
    {
        for (int i = 0; i < height; ++i)
        {
            mutablePos.set(pos).move(Direction.UP, i);
            // Fix Forge's inversion bug
            if (world.getBlockState(mutablePos).canBeReplacedByLeaves(world, mutablePos))
            {
                this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
            }
        }
    }

    @Override
    protected void makeCap(LevelAccessor world, Random random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config)
    {
        for (int y = height - 3; y <= height; ++y)
        {
            int radius = y < height ? config.foliageRadius : config.foliageRadius - 1;
            int innerRadius = config.foliageRadius - 2;

            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    boolean westEdge = x == -radius;
                    boolean eastEdge = x == radius;
                    boolean northEdge = z == -radius;
                    boolean southEdge = z == radius;
                    boolean eastOrWestEdge = westEdge || eastEdge;
                    boolean northOrSouthEdge = northEdge || southEdge;
                    if (y >= height || eastOrWestEdge != northOrSouthEdge)
                    {
                        mutablePos.set(pos).move(x, y, z);
                        // Fix Forge's inversion bug
                        if (world.getBlockState(mutablePos).canBeReplacedByLeaves(world, mutablePos))
                        {
                            this.setBlock(world, mutablePos, config.capProvider.getState(random, pos).setValue(HugeMushroomBlock.UP, Boolean.valueOf(y >= height - 1)).setValue(HugeMushroomBlock.WEST, Boolean.valueOf(x < -innerRadius)).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(x > innerRadius)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(z < -innerRadius)).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(z > innerRadius)));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean isValidPosition(LevelAccessor world, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config)
    {
        int i = pos.getY();
        if (i >= 1 && i + height + 1 < world.getMaxBuildHeight())
        {
            Block groundBlock = world.getBlockState(pos.below()).getBlock();
            // Allow growth in the Nether
            if (!isDirt(groundBlock) && groundBlock != Blocks.NETHERRACK && groundBlock != Blocks.SOUL_SAND)
            {
                return false;
            }
            else
            {
                for (int y = 0; y <= height; ++y)
                {
                    int radius = this.getTreeRadiusForHeight(-1, -1, config.foliageRadius, y);

                    for (int x = -radius; x <= radius; ++x)
                    {
                        for (int z = -radius; z <= radius; ++z)
                        {
                            BlockState obstructingState = world.getBlockState(mutablePos.set(pos).move(x, y, z));
                            if (!obstructingState.isAir(world, mutablePos) && !obstructingState.is(BlockTags.LEAVES))
                            {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
