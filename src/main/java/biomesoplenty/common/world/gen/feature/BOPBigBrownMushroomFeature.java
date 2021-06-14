/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BigBrownMushroomFeature;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;

import java.util.Random;

public class BOPBigBrownMushroomFeature extends BigBrownMushroomFeature
{
    public BOPBigBrownMushroomFeature(Codec<BigMushroomFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    protected void placeTrunk(IWorld world, Random random, BlockPos pos, BigMushroomFeatureConfig config, int height, BlockPos.Mutable mutablePos)
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
    protected void makeCap(IWorld world, Random random, BlockPos pos, int height, BlockPos.Mutable mutablePos, BigMushroomFeatureConfig config)
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
                    if (world.getBlockState(mutablePos).canBeReplacedByLeaves(world, mutablePos))
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
    protected boolean isValidPosition(IWorld world, BlockPos pos, int height, BlockPos.Mutable mutablePos, BigMushroomFeatureConfig config)
    {
        int i = pos.getY();
        if (i >= 1 && i + height + 1 < world.getMaxBuildHeight())
        {
            Block groundBlock = world.getBlockState(pos.below()).getBlock();
            // Allow growth in the nether
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
