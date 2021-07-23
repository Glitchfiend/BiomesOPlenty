/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.HugeBrownMushroomFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

import java.util.Random;

public class BOPBigBrownMushroomFeature extends HugeBrownMushroomFeature
{
    public BOPBigBrownMushroomFeature(Codec<HugeMushroomFeatureConfiguration> deserializer)
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

            if (TreeFeature.isAirOrLeaves(world, mutablePos))
            {
                this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
            }
        }
    }

    @Override
    protected void makeCap(LevelAccessor world, Random random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeMushroomFeatureConfiguration config)
    {
        int radius = config.foliageRadius;

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
                if (!eastOrWestEdge || !northOrSouthEdge)
                {
                    mutablePos.set(pos).move(x, height, z);
                    // Fix Forge's inversion bug
                    if (TreeFeature.isAirOrLeaves(world, mutablePos))
                    {
                        boolean westFace = westEdge || northOrSouthEdge && x == 1 - radius;
                        boolean eastFace = eastEdge || northOrSouthEdge && x == radius - 1;
                        boolean northFace = northEdge || eastOrWestEdge && z == 1 - radius;
                        boolean southFace = southEdge || eastOrWestEdge && z == radius - 1;
                        this.setBlock(world, mutablePos, config.capProvider.getState(random, pos).setValue(HugeMushroomBlock.WEST, Boolean.valueOf(westFace)).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(eastFace)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(northFace)).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(southFace)));
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
            BlockState state = world.getBlockState(pos.below());
            Block groundBlock = state.getBlock();
            // Allow growth in the nether
            if (!isDirt(state) && groundBlock != Blocks.NETHERRACK && groundBlock != Blocks.SOUL_SAND)
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
                            if (!obstructingState.isAir() && !obstructingState.is(BlockTags.LEAVES))
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
