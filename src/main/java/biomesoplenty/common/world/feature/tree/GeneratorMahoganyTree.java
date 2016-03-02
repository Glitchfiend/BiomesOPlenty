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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
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
            this.hanging = null;
            this.altLeaves = null;
            this.minHeight = 10;
            this.maxHeight = 15;
            this.leafLayers = 4;
            this.leavesOffset = 1;
            this.maxLeavesRadius = 1;
            this.leavesLayerHeight = 1;
            this.placeVinesOn = BlockQueries.air;
            this.hangingChance = 0.0F;
        }
        
        @Override
        public GeneratorMahoganyTree create()
        {
            return new GeneratorMahoganyTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight, false, this.leafLayers, this.leavesOffset, this.maxLeavesRadius, this.leavesLayerHeight, this.placeVinesOn, this.hangingChance);
        }
    }
    
    public GeneratorMahoganyTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight, boolean updateNeighbours, int leafLayers, int leavesOffset, int minLeavesRadius, int leavesLayerHeight, IBlockPosQuery placeVinesOn, float hangingChance) 
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight, updateNeighbours, leafLayers, leavesOffset, minLeavesRadius, leavesLayerHeight, placeVinesOn, hangingChance);
    }

    @Override
    protected void generateTrunk(World world, BlockPos start, int height)
    {
        int endHeight = height - this.leafLayers;
        
        for (int layer = 0; layer <= endHeight - 3; layer++)
        {
            BlockPos middlePos = start.up(layer);
            
            if (this.replace.matches(world, middlePos))
            {
                this.setLog(world, middlePos);
            }
        }
        
        //Generate upper branches
        BlockPos branchStartPos = start.up(endHeight - 2);
        int branchHeight = (this.leafLayers - 1) + 1;
        
        generateBranch(world, branchStartPos, EnumFacing.NORTH, branchHeight);
        generateBranch(world, branchStartPos, EnumFacing.EAST, branchHeight);
        generateBranch(world, branchStartPos, EnumFacing.SOUTH, branchHeight);
        generateBranch(world, branchStartPos, EnumFacing.WEST, branchHeight);
    }
    
    private void generateBranch(World world, BlockPos middle, EnumFacing direction, int height)
    {
        BlockPos pos;

        if (replace.matches(world, pos = middle.offset(direction))) this.setLog(world, pos, direction.getAxis());
        
        for (int i = 0; i <= height - 1; i++)
        {
            if (replace.matches(world, pos = middle.offset(direction, 2).up(i + 1))) this.setLog(world, pos, Axis.Y);
        }
        
        EnumFacing logDirection = direction.rotateY();
        
        //Extend inner branches outwards to prevent decay
        for (int i = -1; i <= 1; i++)
        {
           if (replace.matches(world, pos = middle.offset(direction, 3).offset(logDirection, i).up(height - 1))) this.setLog(world, pos, logDirection.getAxis());
        }
    }
}
