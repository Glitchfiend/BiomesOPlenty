/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.worldgen.feature.configurations.CypressTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class CypressTreeFeature  extends BOPTreeFeature<CypressTreeConfiguration>
{
    public CypressTreeFeature(Codec<CypressTreeConfiguration> codec)
    {
        super(codec);
    }

    public boolean checkSpace(LevelAccessor world, BlockPos pos, int baseHeight, int height, CypressTreeConfiguration config)
    {
        for (int y = 0; y <= height; y++)
        {
            int radius = config.trunkWidth - 1;

            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.canReplace(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }

        BlockPos pos2 = pos.offset(0, height - 2,0);
        if (!TreeFeature.isAirOrLeaves(world, pos2))
        {
            return false;
        }

        return true;
    }

    // generates a layer of leaves
    public void generateLeafLayer(LevelAccessor world, RandomSource rand, BlockPos pos, int leavesRadius, FoliagePlacer.FoliageSetter leaves, CypressTreeConfiguration config)
    {
        int start = -leavesRadius;
        int end = leavesRadius;

        for (int x = start; x <= end; x++)
        {
            for (int z = start; z <= end; z++)
            {
                // skip corners
                if ((leavesRadius > 0) && (x == start || x == end) && (z == start || z == end))
                {
                    continue;
                }

                // Make ends more scraggly
                if ((leavesRadius > 0) && ((x == start || x == end) || (z == start || z == end)) && rand.nextDouble() < 0.2)
                {
                    continue;
                }

                this.placeLeaves(world, pos.offset(x, 0, z), leaves, config);
            }
        }
    }

    public void generateBranch(LevelAccessor world, RandomSource rand, BlockPos pos, Direction direction, int length, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, CypressTreeConfiguration config)
    {
        Direction.Axis axis = direction.getAxis();
        Direction sideways = direction.getClockWise();
        for (int i = 1; i <= length; i++)
        {
            BlockPos pos1 = pos.relative(direction, i);
            int r = (i == 1 || i == length) ? 1 : 2;
            for (int j = -r; j <= r; j++)
            {
                if (i < length || rand.nextInt(2) == 0)
                {
                    this.placeLeaves(world, pos1.relative(sideways, j), leaves, config);
                }
            }
            if (length - i > 2)
            {
                this.placeLeaves(world, pos1.above(), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, -1), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, 1), leaves, config);
                this.placeLog(world, pos1, axis, logs, config);
            }
        }
    }


    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        CypressTreeConfiguration config = (CypressTreeConfiguration)configBase;

        // Move down until we reach the ground
        while (startPos.getY() > 1 && this.canReplace(world, startPos) || world.getBlockState(startPos).is(BlockTags.LEAVES)) {startPos = startPos.below();}

        // Choose heights
        int height = GeneratorUtil.nextIntBetween(random, config.minHeight, config.maxHeight);
        int baseHeight = GeneratorUtil.nextIntBetween(random, (int)(height * 0.6F), (int)(height * 0.4F));
        int leavesHeight = height - baseHeight;
        int baseLeavesHeight = leavesHeight;
        if (leavesHeight < 3) {return false;}

        leavesHeight = Mth.clamp(leavesHeight, 3, 5);
        leavesHeight = Mth.clamp(leavesHeight + random.nextInt(3), 0, baseLeavesHeight);

        if (!this.checkSpace(world, startPos.above(), baseHeight, height, config))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.above(height);

        // Leaves at the top
        this.placeLeaves(world, pos, leaves, config);
        pos.below();

        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++)
        {
            int radius = 3;
            if (i == 0)
            {
                radius = 1;
            }
            else if (i <= 2)
            {
                radius = 2;
            }

            this.generateLeafLayer(world, random, pos, radius, leaves, config);

            pos = pos.below();
        }

        this.placeSpanishMoss(world, random, pos);

        // We make the radius to check 1 less than the width
        int trunkRadius = config.trunkWidth - 1;

        // Generate the trunk
        for (int x = -trunkRadius; x <= trunkRadius; x++)
        {
            for (int z = -trunkRadius; z <= trunkRadius; z++)
            {
                int dist = Math.abs(x) + Math.abs(z);

                if (dist > trunkRadius)
                {
                    continue;
                }

                int heightHere = height - 1;
                if (dist == 1)
                {
                    heightHere = (int) (height * (0.2 + random.nextDouble() * 0.15));
                }

                heightHere += random.nextInt(2);

                for (int y = 0; y < heightHere; y++)
                {
                    BlockPos local = startPos.offset(x, y, z);
                    boolean air = world.getBlockState(local).getFluidState().isEmpty();

                    this.placeLog(world, local, logs, config);

                    if (x == 0 && z == 0 && air && y < heightHere - leavesHeight + 1)
                    {
                        if (y >= baseHeight && random.nextInt(3) == 0)
                        {
                            // Big branch
                            double theta = Math.PI * random.nextDouble() * 2;

                            int length = 2 + random.nextInt(3);

                            BlockPos branchPos = null;
                            for (int i = 0; i < length; i++)
                            {
                                branchPos = local.offset(Mth.floor(Math.cos(theta) * i), i / 2, Mth.floor(Math.sin(theta) * i));

                                this.placeLog(world, branchPos, logs, config);
                            }

                            generateLeafLayer(world, random, branchPos, 2, leaves, config);
                            generateLeafLayer(world, random, branchPos.above(), 1, leaves, config);
                            if (random.nextBoolean())
                            {
                                generateLeafLayer(world, random, branchPos.above(2), 0, leaves, config);
                            }

                            this.placeSpanishMoss(world, random, branchPos);

                        }
                        else if (y >= baseHeight && random.nextInt(3) == 0)
                        {
                            // Small branch
                            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                            BlockPos offset = local.relative(direction);

                            this.placeLog(world, offset, logs, config);

                            for (Direction dir : Direction.values())
                            {
                                if (random.nextDouble() > 0.2)
                                {
                                    this.placeLeaves(world, offset.relative(dir), leaves, config);
                                }
                            }

                            this.placeSpanishMoss(world, random, offset);
                        }
                    }
                }
            }
        }

        return true;
    }

    private void placeSpanishMoss(LevelAccessor p_236429_1_, RandomSource p_236429_2_, BlockPos p_236429_3_)
    {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 50; ++i)
        {
            blockpos$mutable.setWithOffset(p_236429_3_, p_236429_2_.nextInt(5) - p_236429_2_.nextInt(5), p_236429_2_.nextInt(3) - p_236429_2_.nextInt(3), p_236429_2_.nextInt(5) - p_236429_2_.nextInt(5));
            if (p_236429_1_.isEmptyBlock(blockpos$mutable))
            {
                BlockState blockstate = p_236429_1_.getBlockState(blockpos$mutable.above());
                if (blockstate.getBlock() == BOPBlocks.WILLOW_LEAVES.get())
                {
                    int j = Mth.nextInt(p_236429_2_, 1, 3);

                    if (p_236429_2_.nextInt(5) == 0)
                    {
                        j = 1;
                    }

                    placeSpanishMossColumn(p_236429_1_, p_236429_2_, blockpos$mutable, j, 17, 25);
                }
            }
        }
    }

    public static void placeSpanishMossColumn(LevelAccessor p_236427_0_, RandomSource p_236427_1_, BlockPos.MutableBlockPos p_236427_2_, int p_236427_3_, int p_236427_4_, int p_236427_5_)
    {
        for(int i = 0; i <= p_236427_3_; ++i)
        {
            if (p_236427_0_.isEmptyBlock(p_236427_2_))
            {
                if (i == p_236427_3_ || !p_236427_0_.isEmptyBlock(p_236427_2_.below()))
                {
                    p_236427_0_.setBlock(p_236427_2_, BOPBlocks.SPANISH_MOSS.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Integer.valueOf(Mth.nextInt(p_236427_1_, p_236427_4_, p_236427_5_))), 2);
                    break;
                }

                p_236427_0_.setBlock(p_236427_2_, BOPBlocks.SPANISH_MOSS_PLANT.get().defaultBlockState(), 2);
            }

            p_236427_2_.move(Direction.DOWN);
        }

    }

    @Override
    public boolean placeLeaves(LevelAccessor level, BlockPos pos, FoliagePlacer.FoliageSetter leaves, CypressTreeConfiguration config)
    {
        if (TreeFeature.isAirOrLeaves(level, pos))
        {
            leaves.set(pos, config.foliageProvider.getState(level.getRandom(), pos));
            return true;
        }
        return false;
    }

    @Override
    protected boolean canReplace(LevelAccessor level, BlockPos pos)
    {
        return super.canReplace(level, pos) || level.isStateAtPosition(pos, (state) -> {
            return state.liquid();
        });
    }
}
