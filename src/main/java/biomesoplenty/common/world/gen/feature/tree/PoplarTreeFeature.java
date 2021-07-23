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
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.Material;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class PoplarTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<PoplarTreeFeature.Builder, PoplarTreeFeature>
    {
        public Builder()
        {
            this.minHeight = 12;
            this.maxHeight = 15;
        }

        @Override
        public PoplarTreeFeature create()
        {
            return new PoplarTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }

    }

    protected PoplarTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
    }

    @Override
    protected boolean place(LevelAccessor world, Random random, BlockPos startPos, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves)
    {
        // Move down until we reach the ground
    	while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.below();}
        
        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights and width
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 4) {return false;}
        int baseHeight = height / 3;
        int leavesHeight = height - baseHeight;
        
        // Move up to space above ground
        BlockPos pos = startPos.above();
        
        if (!this.checkSpace(world, pos, baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Generate bottom of tree (trunk only)
        for(int i = 0; i < baseHeight; i++)
        {
            this.placeLog(world, pos, logs);
            pos = pos.above();
        }

        for (int i = 1; i < leavesHeight - 3; i++)
        {
            for (int xx = -1; xx < 2; xx++)
            {
                for (int zz = -1; zz < 2; zz++)
                {
                    this.placeLeaves(world, pos.offset(xx, i, zz), leaves);
                }
            }
        }
        
        // Generate middle of the tree
        for(int i = 0; i < leavesHeight; i++)
        {
            int radius = radius(i, leavesHeight);
            this.generateLeafLayer(world, pos, radius, leaves);
            if (leavesHeight - i > 2) {this.placeLog(world, pos, logs);}
            pos = pos.above();
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
    
    public boolean checkSpace(LevelAccessor world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            // require 3x3 for the leaves, 1x1 for the trunk
            int radius = (y <= baseHeight ? 0 : 1);
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
    
    // generates a layer of leafs with the given radius
    public void generateLeafLayer(LevelAccessor world, BlockPos pos, int radius, BiConsumer<BlockPos, BlockState> leaves)
    {
        for(int x = -radius; x <= radius; x++)
        {
            for(int z = -radius; z <= radius; z++)
            {
                if (radius < 2)
                {
                    if (x*x + z*z <= radius*radius)
                    {
                        this.placeLeaves(world, pos.offset(x, 0, z), leaves);
                    }
                }
                else
                {
                    if ((x == -radius || x == radius) && (z == -radius || z == radius)) { continue; }

                    if (x == -radius || x == radius || z == -radius || z == radius)
                    {
                        if (world.getRandom().nextInt(3) != 0) {
                            this.placeLeaves(world, pos.offset(x, 0, z), leaves);
                        }
                    }
                    else
                    {
                        this.placeLeaves(world, pos.offset(x, 0, z), leaves);
                    }
                }
            }
        }
    }
}
