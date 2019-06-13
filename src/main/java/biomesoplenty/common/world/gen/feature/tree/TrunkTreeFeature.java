/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import java.util.Random;
import java.util.Set;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class TrunkTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<TrunkTreeFeature.Builder, TrunkTreeFeature>
    {
        public Builder()
        {
            this.minHeight = 6;
            this.maxHeight = 9;
        }

        @Override
        public TrunkTreeFeature create()
        {
            return new TrunkTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }

    }

    protected TrunkTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
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
        
        // Generation settings
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);
        int leavesRadius = 2;
        int heightMinusTop = height - leavesRadius - 1;
        
        // Move up to space above ground
        BlockPos pos = startPos.up();
        
        if (!this.checkSpace(world, pos, height, 1))
        {
            // Abandon if there isn't enough room
            return false;
        }
        
        // Generate trunk of tree (trunk only)
        for(int step = 0; step <= heightMinusTop; step++)
        {
        	BlockPos offsetPos = pos.up(step);
            
            if (step == heightMinusTop)
            {
                // Generate top of tree
                this.setLog(changedBlocks, world, offsetPos);
                break;
            }
            
            this.setLog(changedBlocks, world, offsetPos);
        }
        
        return true;
    }
    
    public boolean checkSpace(IWorld world, BlockPos pos, int height, int radius)
    {
        for (int y = 0; y <= height; y++)
        {
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
}
