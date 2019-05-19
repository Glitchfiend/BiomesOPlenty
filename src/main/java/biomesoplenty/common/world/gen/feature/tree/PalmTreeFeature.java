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
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class PalmTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<PalmTreeFeature.Builder, PalmTreeFeature>
    {
        public Builder()
        {
            this.minHeight = 10;
            this.maxHeight = 14;
            this.log = BOPBlocks.palm_log.getDefaultState();
            this.leaves = BOPBlocks.palm_leaves.getDefaultState().with(BlockLeaves.PERSISTENT, true);

            this.replace = (world, pos) ->
            {
                Material mat = world.getBlockState(pos).getMaterial();
                return mat == Material.AIR || mat == Material.LEAVES;
            };
        }

        @Override
        public PalmTreeFeature create()
        {
            return new PalmTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }

    }

    protected PalmTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState altLeaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit, int minHeight, int maxHeight)
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
        boolean slant = false;
        EnumFacing direction = EnumFacing.random(random); //The direction the palm tree curves towards
        if (direction == EnumFacing.DOWN || direction == EnumFacing.UP)
        {
        	slant = false;
        }
        double baseSlant = random.nextInt(35) / 100D; 
        double slantMultiplier = 1.3D;
        
        if (height < 8) {return false;} //Prevent trees from being too small 
        
        // Move up to space above ground
        BlockPos pos = startPos.up();
        
        if (!this.checkSpace(world, pos, height, 1))
        {
            // Abandon if there isn't enough room
            return false;
        }

        double slantOffset = baseSlant;
        
        // Generate trunk of tree (trunk only)
        for(int step = 0; step <= heightMinusTop; step++)
        {
        	BlockPos offsetPos = pos.up(step);
        	
        	if (slant == true)
        	{
        		offsetPos = pos.up(step).offset(direction, (int)Math.floor(slantOffset));
        	}
            
            if (step == heightMinusTop)
            {
                // Generate top of tree
                this.setLog(changedBlocks, world, offsetPos);
                generateLeavesTop(world, offsetPos, leavesRadius);
                break;
            }
            
            this.setLog(changedBlocks, world, offsetPos);
            
            //As the height increases, slant more drastically
            slantOffset *= slantMultiplier;
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

    // generate the top of the tree (3 blocks)
    public void generateLeavesTop(IWorld world, BlockPos pos, int maxRadius)
    {
        setLeaves(world, pos.add(2, -1, 0));
        setLeaves(world, pos.add(-2, -1, 0));
        setLeaves(world, pos.add(0, -1, 2));
        setLeaves(world, pos.add(0, -1, -2));

        setLeaves(world, pos.add(1, 0, 0));
        setLeaves(world, pos.add(-1, 0, 0));
        setLeaves(world, pos.add(0, 0, 1));
        setLeaves(world, pos.add(0, 0, -1));
        setLeaves(world, pos.add(2, 0, 2));
        setLeaves(world, pos.add(-2, 0, -2));
        setLeaves(world, pos.add(2, 0, -2));
        setLeaves(world, pos.add(-2, 0, 2));

        setLeaves(world, pos.add(1, 1, -1));
        setLeaves(world, pos.add(-1, 1, 1));
        setLeaves(world, pos.add(1, 1, 1));
        setLeaves(world, pos.add(-1, 1, -1));
        setLeaves(world, pos.add(0, 1, 0));

        setLeaves(world, pos.add(2, 2, 0));
        setLeaves(world, pos.add(-2, 2, 0));
        setLeaves(world, pos.add(0, 2, 2));
        setLeaves(world, pos.add(0, 2, -2));
    }
}
