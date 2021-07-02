/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.LevelAccessor;

import java.util.Random;
import java.util.Set;

public class BushTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<BushTreeFeature.Builder, BushTreeFeature>
    {
        public Builder()
        {
            this.minHeight = 2;
            this.maxHeight = 2;
        }

        @Override
        public BushTreeFeature create()
        {
            // Bushes shouldn't check for decay
            if (this.leaves != Blocks.AIR.defaultBlockState())
                this.leaves = this.leaves;

            if (this.altLeaves != Blocks.AIR.defaultBlockState())
                this.altLeaves = this.altLeaves;

            return new BushTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }
    }

    protected BushTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
    }

    @Override
    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, LevelAccessor world, Random random, BlockPos startPos, BoundingBox boundingBox)
    {
        // Move down until we reach the ground
        while (startPos.getY() > 1 && (world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.AIR)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        // choose a random height
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);

        // start from the block above the ground block
        BlockPos pos = startPos.above();

        //Generate a bush 3 blocks tall, with the bottom block already set to a log
        for (int y = 0; y < height; ++y)
        {
            // log in the center
            if (height - y > 1)
            {
                this.placeLog(world, pos.offset(0, y, 0), changedLogs, boundingBox);
            }

            //Reduces the radius closer to the top of the bush
            int leavesRadius = (height - y > 1 ? 2 : 1);

            for (int x = -leavesRadius; x <= leavesRadius; ++x)
            {
                for (int z = -leavesRadius; z <= leavesRadius; ++z)
                {
                    //Randomly prevent the generation of leaves on the corners of each layer
                    if (Math.abs(x) < leavesRadius || Math.abs(z) < leavesRadius || random.nextInt(2) != 0)
                    {
                        if (this.altLeaves != Blocks.AIR.defaultBlockState())
                        {
                            if (random.nextInt(4) == 0)
                            {
                                this.setAltLeaves(world, pos.offset(x, y, z), changedLeaves, boundingBox);
                            }
                            else
                            {
                                this.placeLeaves(world, pos.offset(x, y, z), changedLeaves, boundingBox);
                            }
                        }
                        else
                        {
                            this.placeLeaves(world, pos.offset(x, y, z), changedLeaves, boundingBox);
                        }
                    }
                }
            }
        }

        return true;
    }
}
