/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.worldgen.feature.configurations.MahoganyTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class MahoganyTreeFeature extends BOPTreeFeature<MahoganyTreeConfiguration>
{
    public MahoganyTreeFeature(Codec<MahoganyTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        MahoganyTreeConfiguration config = (MahoganyTreeConfiguration)configBase;

        int height = random.nextInt(config.maxHeight - config.minHeight) + config.minHeight;
        boolean hasSpace = true;

        //Generate only if we are above the lowest bedrock level (1) and reach less than the world height
        //There must be a gap of 1 between the top leaf block and the world height
        if (pos.getY() >= world.getMinBuildHeight()+1 && pos.getY() + height + 1 <= world.getMaxBuildHeight())
        {
            int radius;

            for (int y = pos.getY(); y <= pos.getY() + 1 + height; y++)
            {
                radius = 1;

                //Don't check for space on the first level, if we are a sapling then there will
                //already be a block here (the sapling itself)
                if (y == pos.getY())
                {
                    radius = 0;
                }

                //At and above the top log block, require a radius of 2 to be empty
                if (y >= pos.getY() + 1 + height - 2)
                {
                    radius = 2;
                }

                for (int x = pos.getX() - radius; x <= pos.getX() + radius && hasSpace; ++x)
                {
                    for (int z = pos.getZ() - radius; z <= pos.getZ() + radius && hasSpace; ++z)
                    {
                        if (y >= world.getMinBuildHeight() && y < world.getMaxBuildHeight())
                        {
                            if (!this.canReplace(world, new BlockPos(x, y, z)))
                            {
                                hasSpace = false;
                            }
                        }
                        else
                        {
                            hasSpace = false;
                        }
                    }
                }
            }

            if (!hasSpace)
            {
                return false;
            }
            else
            {
                BlockPos soilPos = pos.below();
                Block soil = world.getBlockState(soilPos).getBlock();

                if (pos.getY() < world.getMaxBuildHeight() - height - 1)
                {
                    world.setBlock(soilPos, Blocks.DIRT.defaultBlockState(), 3);
                    this.generateTrunk(logs, leaves, world, pos, height, config);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    protected void generateTrunk(BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, LevelAccessor world, BlockPos start, int height, MahoganyTreeConfiguration config)
    {
        int endHeight = height;

        for (int layer = 0; layer <= endHeight - 3; layer++)
        {
            BlockPos middlePos = start.above(layer);

            if (this.canReplace(world, middlePos))
            {
                this.placeLog(world, middlePos, logs, config);
            }
        }

        //Generate upper branches
        BlockPos branchStartPos = start.above(endHeight - 3);

        generateBranch(logs, leaves, world, branchStartPos, Direction.NORTH, config);
        generateBranch(logs, leaves, world, branchStartPos, Direction.EAST, config);
        generateBranch(logs, leaves, world, branchStartPos, Direction.SOUTH, config);
        generateBranch(logs, leaves, world, branchStartPos, Direction.WEST, config);
    }

    private void generateBranch(BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, LevelAccessor world, BlockPos middle, Direction direction, MahoganyTreeConfiguration config)
    {
        BlockPos pos = middle;
        int length = 1 + world.getRandom().nextInt(2);

        for (int i = 0; i <= length - 1; i++)
        {
            if (this.canReplace(world, pos.relative(direction, i+1)))
            {
                this.placeLog(world, pos.relative(direction, i+1), direction.getAxis(), logs, config);
            }
        }

        int height = 1 + world.getRandom().nextInt(2);
        for (int i = 0; i <= height; i++)
        {
            if (this.canReplace(world, pos.relative(direction, length+1).above(i+1)))
            {
                this.placeLog(world, pos.relative(direction, length+1).above(i+1), Direction.Axis.Y, logs, config);
            }
        }


        pos = pos.relative(direction, length+1).above(height+2);

        int radius = 2;
        for (int x = -(radius-1); x <= (radius-1); x++)
        {
            for (int z = -(radius - 1); z <= (radius - 1); z++)
            {
                this.placeLeaves(world, pos.offset(x,0,z), leaves, config);
            }
        }

        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                if ((x == -radius || x == radius) && (z == -radius || z == radius))
                {
                    continue;
                }
                else
                {
                    this.placeLeaves(world, pos.offset(x,-1,z), leaves, config);
                }
            }
        }
    }
}
