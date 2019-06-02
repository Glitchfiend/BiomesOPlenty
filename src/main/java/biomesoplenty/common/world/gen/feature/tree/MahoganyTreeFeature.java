/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import java.util.Set;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class MahoganyTreeFeature extends BasicTreeFeature
{
    public static class Builder extends BasicTreeFeature.InnerBuilder<MahoganyTreeFeature.Builder, MahoganyTreeFeature>
    {
        public Builder()
        {
            this.log = BOPBlocks.mahogany_log.getDefaultState();
            this.leaves = BOPBlocks.mahogany_leaves.getDefaultState();
            this.minHeight = 10;
            this.maxHeight = 15;
            this.leavesLayerHeight = 1;
        }

        @Override
        public MahoganyTreeFeature create()
        {
            return new MahoganyTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.leafLayers, this.leavesOffset, this.maxLeavesRadius, this.leavesLayerHeight, this.placeVinesOn, this.hangingChance);
        }
    }

    protected MahoganyTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log,
                               IBlockState leaves, IBlockState altLeaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit,
                               int minHeight, int maxHeight, int leafLayers, int leavesOffset, int maxLeavesRadius, int leavesLayerHeight,
                               IBlockPosQuery placeVinesOn, float hangingChance)
    {
        super(notify, placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight, leafLayers, leavesOffset, maxLeavesRadius, leavesLayerHeight, placeVinesOn, hangingChance);
    }

    @Override
    protected void generateTrunk(Set<BlockPos> changedBlocks, IWorld world, BlockPos start, int height)
    {
        int endHeight = height - this.leafLayers;

        for (int layer = 0; layer <= endHeight - 3; layer++)
        {
            BlockPos middlePos = start.up(layer);

            if (this.replace.matches(world, middlePos))
            {
                this.setLog(changedBlocks, world, middlePos);
            }
        }

        //Generate upper branches
        BlockPos branchStartPos = start.up(endHeight - 2);
        int branchHeight = (this.leafLayers - 1) + 1;

        generateBranch(changedBlocks, world, branchStartPos, EnumFacing.NORTH, branchHeight);
        generateBranch(changedBlocks, world, branchStartPos, EnumFacing.EAST, branchHeight);
        generateBranch(changedBlocks, world, branchStartPos, EnumFacing.SOUTH, branchHeight);
        generateBranch(changedBlocks, world, branchStartPos, EnumFacing.WEST, branchHeight);
    }

    private void generateBranch(Set<BlockPos> changedBlocks, IWorld world, BlockPos middle, EnumFacing direction, int height)
    {
        BlockPos pos;

        if (replace.matches(world, pos = middle.offset(direction))) this.setLog(changedBlocks, world, pos, direction.getAxis());

        for (int i = 0; i <= height - 1; i++)
        {
            if (replace.matches(world, pos = middle.offset(direction, 2).up(i + 1))) this.setLog(changedBlocks, world, pos, EnumFacing.Axis.Y);
        }

        EnumFacing logDirection = direction.rotateY();

        //Extend inner branches outwards to prevent decay
        for (int i = -1; i <= 1; i++)
        {
            if (replace.matches(world, pos = middle.offset(direction, 3).offset(logDirection, i).up(height - 1))) this.setLog(changedBlocks, world, pos, logDirection.getAxis());
        }
    }
}
