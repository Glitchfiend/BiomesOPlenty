/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.feature.tree;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.world.World;

public class GeneratorMahoganyTree extends GeneratorBasicTree
{
    public static class Builder extends GeneratorBasicTree.InnerBuilder<Builder, GeneratorMahoganyTree> implements IGeneratorBuilder<GeneratorMahoganyTree>
    {
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = new BlockQueryMaterial(Material.air, Material.leaves);
            this.log = BlockBOPLog.paging.getVariantState(BOPWoods.MAHOGANY);
            this.leaves = BlockBOPLeaves.paging.getVariantState(BOPTrees.MAHOGANY);
            this.vine = null;
            this.minHeight = 10;
            this.maxHeight = 15;
            this.leafLayers = 4;
            this.leavesOffset = 1;
            this.minLeavesRadius = 2;
            this.leavesLayerHeight = 1;
            this.placeVinesOn = BlockQueries.air;
        }
        
        @Override
        public GeneratorMahoganyTree create()
        {
            return new GeneratorMahoganyTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.minHeight, this.maxHeight, false, this.leafLayers, this.leavesOffset, this.minLeavesRadius, this.leavesLayerHeight, this.placeVinesOn);
        }
    }
    
    public GeneratorMahoganyTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, int minHeight, int maxHeight, boolean updateNeighbours, int leafLayers, int leavesOffset, int minLeavesRadius, int leavesLayerHeight, IBlockPosQuery placeVinesOn) 
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, minHeight, maxHeight, updateNeighbours, leafLayers, leavesOffset, minLeavesRadius, leavesLayerHeight, placeVinesOn);
    }

    @Override
    protected void generateTrunk(World world, BlockPos start, int height)
    {
        int endHeight = height - this.leafLayers;
        
        for (int layer = 0; layer <= height; ++layer)
        {
            BlockPos middlePos = start.up(layer);
            
            if (layer == endHeight - 2) 
            {
                int branchHeight = height - endHeight - 1 + 2;
                
                //Generate the upper branches and stop
                generateBranch(world, middlePos, EnumFacing.NORTH, branchHeight);
                generateBranch(world, middlePos, EnumFacing.EAST, branchHeight);
                generateBranch(world, middlePos, EnumFacing.SOUTH, branchHeight);
                generateBranch(world, middlePos, EnumFacing.WEST, branchHeight);
                break;
            }
            else if (this.replace.matches(world, middlePos))
            {
                this.setLog(world, middlePos);
            }
        }
    }
    
    private void generateBranch(World world, BlockPos middle, EnumFacing direction, int height)
    {
        BlockPos pos;

        if (replace.matches(world, pos = middle.offset(direction))) this.setLog(world, pos, direction.getAxis());
        
        for (int i = 1; i <= height; ++i)
        {
            if (replace.matches(world, pos = middle.offset(direction, 2).up(i))) this.setLog(world, pos, Axis.Y);
        }
    }
}
