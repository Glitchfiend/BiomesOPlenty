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
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class PoplarTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<PoplarTreeFeature.Builder, PoplarTreeFeature>
    {
        public Builder()
        {
            this.minHeight = 9;
            this.maxHeight = 17;

            this.replace = (world, pos) ->
            {
                Material mat = world.getBlockState(pos).getMaterial();
                return mat == Material.AIR || mat == Material.LEAVES;
            };
        }

        @Override
        public PoplarTreeFeature create()
        {
            return new PoplarTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }

    }

    protected PoplarTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState altLeaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(notify, placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorld world, Random random, BlockPos startPos)
    {
        
        // Move down until we reach the ground
    	while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.down();}
        
        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights and width
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 4) {return false;}
        int baseHeight = height / (2 + random.nextInt(3));
        int leavesHeight = height - baseHeight;
        
        // Move up to space above ground
        BlockPos pos = startPos.up();
        
        if (!this.checkSpace(world, pos, baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Generate bottom of tree (trunk only)
        for(int i = 0; i < baseHeight; i++)
        {
            this.setLog(changedBlocks, world, pos);
            pos = pos.up();
        }
        
        // Generate middle of the tree
        for(int i = 0; i < leavesHeight; i++)
        {
            int radius = radius(i, leavesHeight);
            this.generateLeafLayer(world, pos, radius);
            if (leavesHeight - i > 2) {this.setLog(changedBlocks, world, pos);}
            pos = pos.up();
        }
        
        return true;
    }
    
    public int radius(int height, int maxHeight)
    {
	    float x = (float)height / (float)maxHeight;
	    float maxRadius = 1.0F + maxHeight * 0.10F;
	    // this function creates a curved profile which has its widest point 1/4 from the bottom and a pointy top
	    float r = maxRadius * 0.6667F * x * (1/(x*x + 0.08173F) - 0.9244F);
	    return (int)(r + 0.5F);
    }
    
    public boolean checkSpace(IWorld world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            // require 3x3 for the leaves, 1x1 for the trunk
            int radius = (y <= baseHeight ? 0 : 1);
            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    BlockPos pos1 = pos.add(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;       
    }
    
    // generates a layer of leafs with the given radius
    public void generateLeafLayer(IWorld world, BlockPos pos, int radius)
    {
        for(int x = -radius; x <= radius; x++)
        {
            for(int z = -radius; z <= radius; z++)
            {
                if (x*x + z*z <= radius*radius)
                {
                    this.setLeaves(world, pos.add(x, 0, z));
                }
            }
        }
    }
}
