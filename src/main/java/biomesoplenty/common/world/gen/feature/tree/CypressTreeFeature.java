/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class CypressTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<Builder, CypressTreeFeature>
    {
        protected int trunkWidth;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}

        public Builder()
        {
            this.minHeight = 6;
            this.maxHeight = 15;
            this.placeOn = (world, pos) -> world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING);
            this.replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getMaterial() == Material.WATER || world.getBlockState(pos).getBlock().is(BlockTags.SAPLINGS) || world.getBlockState(pos).getBlock() == Blocks.VINE || world.getBlockState(pos).getBlock() == BOPBlocks.willow_vine || world.getBlockState(pos).getBlock() == BOPBlocks.spanish_moss_plant || world.getBlockState(pos).getBlock() == BOPBlocks.spanish_moss || world.getBlockState(pos).getBlock() instanceof BushBlock;
            this.log = BOPBlocks.willow_log.defaultBlockState();
            this.leaves = BOPBlocks.willow_leaves.defaultBlockState();
            this.vine = BOPBlocks.willow_vine.defaultBlockState();
            this.trunkWidth = 1;
        }

        @Override
        public CypressTreeFeature create()
        {
            return new CypressTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.trunkWidth);
        }

    }

    private int trunkWidth = 1;

    protected CypressTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight, int trunkWidth)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
        this.trunkWidth = trunkWidth;
    }

    public boolean checkSpace(IWorld world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            int radius = this.trunkWidth - 1;

            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }

        BlockPos pos2 = pos.offset(0, height - 2,0);
        if (!world.getBlockState(pos2).canBeReplacedByLeaves(world, pos2))
        {
            return false;
        }

        return true;
    }

    // generates a layer of leaves
    public void generateLeafLayer(IWorld world, Random rand, BlockPos pos, int leavesRadius, Set<BlockPos> changedLeaves, MutableBoundingBox boundingBox)
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

                this.placeLeaves(world, pos.offset(x, 0, z), changedLeaves, boundingBox);
            }
        }
    }

    public void generateBranch(IWorld world, Random rand, BlockPos pos, Direction direction, int length, Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, MutableBoundingBox boundingBox)
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
                    this.placeLeaves(world, pos1.relative(sideways, j), changedLeaves, boundingBox);
                }
            }
            if (length - i > 2)
            {
                this.placeLeaves(world, pos1.above(), changedLeaves, boundingBox);
                this.placeLeaves(world, pos1.above().relative(sideways, -1), changedLeaves, boundingBox);
                this.placeLeaves(world, pos1.above().relative(sideways, 1), changedLeaves, boundingBox);
                this.placeLog(world, pos1, axis, changedLogs, boundingBox);
            }
        }
    }


    @Override
    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, IWorld world, Random random, BlockPos startPos, MutableBoundingBox boundingBox)
    {
        // Move down until we reach the ground
        while (startPos.getY() > 1 && this.replace.matches(world, startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.below();}

        for (int x = 0; x <= this.trunkWidth - 1; x++)
        {
            for (int z = 0; z <= this.trunkWidth - 1; z++)
            {
                if (!this.placeOn.matches(world, startPos.offset(x, 0, z)))
                {
                    // Abandon if we can't place the tree on this block
                    return false;
                }
            }
        }

        // Choose heights
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);
        int baseHeight = GeneratorUtil.nextIntBetween(random, (int)(height * 0.6F), (int)(height * 0.4F));
        int leavesHeight = height - baseHeight;
        int baseLeavesHeight = leavesHeight;
        if (leavesHeight < 3) {return false;}

        leavesHeight = MathHelper.clamp(leavesHeight, 3, 5);
        leavesHeight = MathHelper.clamp(leavesHeight + random.nextInt(3), 0, baseLeavesHeight);

        if (!this.checkSpace(world, startPos.above(), baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.above(height);

        // Leaves at the top
        this.placeLeaves(world, pos, changedLeaves, boundingBox);
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

            this.generateLeafLayer(world, random, pos, radius,  changedLeaves, boundingBox);

            pos = pos.below();
        }

        this.placeSpanishMoss(world, random, pos);

        // We make the radius to check 1 less than the width
        int trunkRadius = this.trunkWidth - 1;

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

                    this.placeLog(world, local, changedLogs, boundingBox);

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
                                branchPos = local.offset(Math.cos(theta) * i, i / 2, Math.sin(theta) * i);

                                this.placeLog(world, branchPos, changedLogs, boundingBox);
                            }

                            generateLeafLayer(world, random, branchPos, 2, changedLeaves, boundingBox);
                            generateLeafLayer(world, random, branchPos.above(), 1, changedLeaves, boundingBox);
                            if (random.nextBoolean())
                            {
                                generateLeafLayer(world, random, branchPos.above(2), 0, changedLeaves, boundingBox);
                            }

                            this.placeSpanishMoss(world, random, branchPos);

                        }
                        else if (y >= baseHeight && random.nextInt(3) == 0)
                        {
                            // Small branch
                            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                            BlockPos offset = local.relative(direction);

                            this.placeLog(world, offset, changedLogs, boundingBox);

                            for (Direction dir : Direction.values())
                            {
                                if (random.nextDouble() > 0.2)
                                {
                                    this.placeLeaves(world, offset.relative(dir), changedLeaves, boundingBox);
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

    @Override
    public boolean placeLeaves(IWorld world, BlockPos pos, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox)
    {
        if (world.getBlockState(pos).canBeReplacedByLeaves(world, pos))
        {
            this.setBlock(world, pos, this.leaves);
            this.placeBlock(world, pos, this.leaves, changedBlocks, boundingBox);
            return true;
        }
        return false;
    }

    private void placeSpanishMoss(IWorld p_236429_1_, Random p_236429_2_, BlockPos p_236429_3_)
    {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = 0; i < 50; ++i)
        {
            blockpos$mutable.setWithOffset(p_236429_3_, p_236429_2_.nextInt(5) - p_236429_2_.nextInt(5), p_236429_2_.nextInt(3) - p_236429_2_.nextInt(3), p_236429_2_.nextInt(5) - p_236429_2_.nextInt(5));
            if (p_236429_1_.isEmptyBlock(blockpos$mutable))
            {
                BlockState blockstate = p_236429_1_.getBlockState(blockpos$mutable.above());
                if (blockstate.getBlock() == BOPBlocks.willow_leaves)
                {
                    int j = MathHelper.nextInt(p_236429_2_, 1, 3);

                    if (p_236429_2_.nextInt(5) == 0)
                    {
                        j = 1;
                    }

                    placeSpanishMossColumn(p_236429_1_, p_236429_2_, blockpos$mutable, j, 17, 25);
                }
            }
        }
    }

    public static void placeSpanishMossColumn(IWorld p_236427_0_, Random p_236427_1_, BlockPos.Mutable p_236427_2_, int p_236427_3_, int p_236427_4_, int p_236427_5_)
    {
        for(int i = 0; i <= p_236427_3_; ++i)
        {
            if (p_236427_0_.isEmptyBlock(p_236427_2_))
            {
                if (i == p_236427_3_ || !p_236427_0_.isEmptyBlock(p_236427_2_.below()))
                {
                    p_236427_0_.setBlock(p_236427_2_, BOPBlocks.spanish_moss.defaultBlockState().setValue(AbstractTopPlantBlock.AGE, Integer.valueOf(MathHelper.nextInt(p_236427_1_, p_236427_4_, p_236427_5_))), 2);
                    break;
                }

                p_236427_0_.setBlock(p_236427_2_, BOPBlocks.spanish_moss_plant.defaultBlockState(), 2);
            }

            p_236427_2_.move(Direction.DOWN);
        }

    }
}
