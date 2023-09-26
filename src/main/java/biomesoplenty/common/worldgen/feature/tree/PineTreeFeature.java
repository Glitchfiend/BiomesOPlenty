/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.worldgen.feature.configurations.PineTreeConfiguration;
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

public class PineTreeFeature extends BOPTreeFeature<PineTreeConfiguration>
{
    public PineTreeFeature(Codec<PineTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        PineTreeConfiguration config = (PineTreeConfiguration)configBase;

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

    protected void generateTrunk(BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, LevelAccessor world, BlockPos start, int height, PineTreeConfiguration config)
    {
        int endHeight = height;

        for (int layer = 0; layer <= endHeight; layer++)
        {
            BlockPos middlePos = start.above(layer);

            if (this.canReplace(world, middlePos))
            {
                this.placeLog(world, middlePos, logs, config);
            }
        }

        //Generate lower branches
        BlockPos lowerBranchStartPos = start.above(endHeight - 9);
        Direction lowerBranchDir = Direction.Plane.HORIZONTAL.getRandomDirection(world.getRandom());

        if (world.getRandom().nextInt(6) != 0)
        {
            generateBranch(logs, leaves, world, lowerBranchStartPos, lowerBranchDir, 2, config);

            if (world.getRandom().nextInt(4) == 0)
            {
                generateBranch(logs, leaves, world, lowerBranchStartPos, lowerBranchDir.getOpposite(), 2, config);

                if (world.getRandom().nextInt(6) == 0)
                {
                    generateBranch(logs, leaves, world, lowerBranchStartPos, lowerBranchDir.getClockWise(), 2, config);
                    generateBranch(logs, leaves, world, lowerBranchStartPos, lowerBranchDir.getCounterClockWise(), 2, config);
                }
            }
        }

        //Generate upper branches
        BlockPos upperBranchStartPos = start.above(endHeight - 6);

        generateBranch(logs, leaves, world, upperBranchStartPos, Direction.NORTH, 1, config);
        generateBranch(logs, leaves, world, upperBranchStartPos, Direction.EAST, 1, config);
        generateBranch(logs, leaves, world, upperBranchStartPos, Direction.SOUTH, 1, config);
        generateBranch(logs, leaves, world, upperBranchStartPos, Direction.WEST, 1, config);

        //Generate top leaves
        BlockPos topStartPos = start.above(endHeight - 3);

        this.placeLeaves(world, topStartPos.offset(0,0,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,0,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,0,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,0,0), leaves, config);

        this.placeLeaves(world, topStartPos.offset(0,1,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,1,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,1,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,1,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,1,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,1,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,1,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,1,-1), leaves, config);

        this.placeLeaves(world, topStartPos.offset(1,1,2), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,1,-2), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,1,2), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,1,-2), leaves, config);
        this.placeLeaves(world, topStartPos.offset(2,1,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(2,1,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-2,1,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-2,1,-1), leaves, config);

        this.placeLeaves(world, topStartPos.offset(0,3,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,3,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,3,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,3,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,3,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,3,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,3,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,3,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,3,2), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,3,-2), leaves, config);
        this.placeLeaves(world, topStartPos.offset(2,3,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-2,3,0), leaves, config);

        this.placeLeaves(world, topStartPos.offset(0,4,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,4,1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(0,4,-1), leaves, config);
        this.placeLeaves(world, topStartPos.offset(1,4,0), leaves, config);
        this.placeLeaves(world, topStartPos.offset(-1,4,0), leaves, config);
    }

    private void generateBranch(BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, LevelAccessor world, BlockPos middle, Direction direction, int length, PineTreeConfiguration config)
    {
        BlockPos pos = middle;

        for (int i = 0; i <= length - 1; i++)
        {
            if (this.canReplace(world, pos.relative(direction, i+1)))
            {
                this.placeLog(world, pos.relative(direction, i+1), direction.getAxis(), logs, config);
            }
        }

        if (this.canReplace(world, pos.relative(direction, length + 1).above()))
        {
            this.placeLog(world, pos.relative(direction, length + 1).above(), direction.getAxis(), logs, config);
        }

        pos = pos.relative(direction, length+1);
        this.placeLeaves(world, pos, leaves, config);

        this.placeLeaves(world, pos.offset(0,1,1), leaves, config);
        this.placeLeaves(world, pos.offset(0,1,-1), leaves, config);
        this.placeLeaves(world, pos.offset(1,1,0), leaves, config);
        this.placeLeaves(world, pos.offset(-1,1,0), leaves, config);
        this.placeLeaves(world, pos.offset(1,1,1), leaves, config);
        this.placeLeaves(world, pos.offset(-1,1,1), leaves, config);
        this.placeLeaves(world, pos.offset(1,1,-1), leaves, config);
        this.placeLeaves(world, pos.offset(-1,1,-1), leaves, config);
        this.placeLeaves(world, pos.offset(0,1,2), leaves, config);
        this.placeLeaves(world, pos.offset(0,1,-2), leaves, config);
        this.placeLeaves(world, pos.offset(2,1,0), leaves, config);
        this.placeLeaves(world, pos.offset(-2,1,0), leaves, config);

        this.placeLeaves(world, pos.offset(0,2,0), leaves, config);
        this.placeLeaves(world, pos.offset(1,2,1), leaves, config);
        this.placeLeaves(world, pos.offset(-1,2,1), leaves, config);
        this.placeLeaves(world, pos.offset(1,2,-1), leaves, config);
        this.placeLeaves(world, pos.offset(-1,2,-1), leaves, config);
    }
}
