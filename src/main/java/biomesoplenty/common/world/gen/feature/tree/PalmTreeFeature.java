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
import biomesoplenty.common.world.gen.BOPFeatureUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.Material;

import java.util.Random;
import java.util.Set;

public class PalmTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<PalmTreeFeature.Builder, PalmTreeFeature>
    {
        public Builder()
        {
        	this.placeOn = (world, pos) ->
        	{
        		Block ground = world.getBlockState(pos).getBlock();
        		return BOPFeatureUtil.isSoil(world, pos) || (ground == BOPBlocks.WHITE_SAND || ground == Blocks.RED_SAND || ground == Blocks.SAND);
        	};
            this.minHeight = 10;
            this.maxHeight = 14;
            this.log = BOPBlocks.PALM_LOG.defaultBlockState();
            this.leaves = BOPBlocks.PALM_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);
        }

        @Override
        public PalmTreeFeature create()
        {
            return new PalmTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }

    }

    protected PalmTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
    }

    @Override
    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, LevelAccessor world, Random random, BlockPos startPos, BoundingBox boundingBox)
    {
        // Move down until we reach the ground
    	while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.below();}
        
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
        Direction direction = Direction.getRandom(random); //The direction the palm tree curves towards
        if (direction == Direction.DOWN || direction == Direction.UP)
        {
        	slant = false;
        }
        double baseSlant = random.nextInt(35) / 100D; 
        double slantMultiplier = 1.3D;
        
        if (height < 8) {return false;} //Prevent trees from being too small 
        
        // Move up to space above ground
        BlockPos pos = startPos.above();
        
        if (!this.checkSpace(world, pos, height, 1))
        {
            // Abandon if there isn't enough room
            return false;
        }

        double slantOffset = baseSlant;
        
        // Generate trunk of tree (trunk only)
        for(int step = 0; step <= heightMinusTop; step++)
        {
        	BlockPos offsetPos = pos.above(step);
        	
        	if (slant == true)
        	{
        		offsetPos = pos.above(step).relative(direction, (int)Math.floor(slantOffset));
        	}
            
            if (step == heightMinusTop)
            {
                // Generate top of tree
                this.placeLog(world, offsetPos, changedLogs, boundingBox);
                generateLeavesTop(world, offsetPos, leavesRadius, changedLeaves, boundingBox);
                break;
            }
            
            this.placeLog(world, offsetPos, changedLogs, boundingBox);
            
            //As the height increases, slant more drastically
            slantOffset *= slantMultiplier;
        }
        
        return true;
    }
    
    public boolean checkSpace(LevelAccessor world, BlockPos pos, int height, int radius)
    {
        for (int y = 0; y <= height; y++)
        {
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
        return true;       
    }

    // generate the top of the tree (3 blocks)
    public void generateLeavesTop(LevelAccessor world, BlockPos pos, int maxRadius, Set<BlockPos> changedLeaves, BoundingBox boundingBox)
    {
        placeLeaves(world, pos.offset(2, -1, 0), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-2, -1, 0), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, -1, 2), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, -1, -2), changedLeaves, boundingBox);

        placeLeaves(world, pos.offset(1, 0, 0), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-1, 0, 0), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, 0, 1), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, 0, -1), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(2, 0, 2), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-2, 0, -2), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(2, 0, -2), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-2, 0, 2), changedLeaves, boundingBox);

        placeLeaves(world, pos.offset(1, 1, -1), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-1, 1, 1), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(1, 1, 1), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-1, 1, -1), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, 1, 0), changedLeaves, boundingBox);

        placeLeaves(world, pos.offset(2, 2, 0), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(-2, 2, 0), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, 2, 2), changedLeaves, boundingBox);
        placeLeaves(world, pos.offset(0, 2, -2), changedLeaves, boundingBox);
    }
}
