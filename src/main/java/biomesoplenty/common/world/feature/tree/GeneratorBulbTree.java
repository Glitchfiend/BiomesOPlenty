/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;
import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBulbTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorBulbTree> implements IGeneratorBuilder<GeneratorBulbTree>
    {
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 6;
            this.maxHeight = 12;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = null;
            this.hanging = null;
            this.altLeaves = null;
        }

        @Override
        public GeneratorBulbTree create() {
            return new GeneratorBulbTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight);
        }
        
    }
    
    
    public GeneratorBulbTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
    }
    
    public boolean setCocoa(World world, BlockPos pos, EnumFacing side)
    {
        IBlockState cocoaState = Blocks.cocoa.getDefaultState().withProperty(BlockDirectional.FACING, side);
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, cocoaState);
            return true;
        }
        return false;
    }
    
    public boolean checkSpace(World world, BlockPos pos, int baseHeight, int height)
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
    
    // generates a 'branch' of a leaf layer
    public void generateBranch(World world, Random random, BlockPos pos, EnumFacing direction)
    {
        EnumFacing sideways = direction.rotateY();
        this.setLeaves(world, pos.offset(direction, 1));
        this.setLeaves(world, pos.up().offset(direction, 1));
        if (random.nextInt(3) > 0)
        {
            this.setLeaves(world, pos.up().offset(direction, 1).offset(sideways, 1));
        }
    }
    
    // generates a layer of leafs (2 blocks high)
    public void generateLeafLayer(World world, Random random, BlockPos pos)
    {
        for (EnumFacing direction : EnumFacing.Plane.HORIZONTAL)
        {
            this.generateBranch(world, random, pos, direction);
        }
        
        // add the trunk in the middle
        this.setLog(world, pos);
        this.setLog(world, pos.up());
    }
    
    public void generateTop(World world, Random random, BlockPos pos, int topHeight)
    {
        for (int y = 0; y < topHeight; y++)
        {
            int radius = topHeight - 1 - y;
            
            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    if (Math.abs(x) < radius || Math.abs(z) < radius || random.nextInt(2) == 0)
                    {
                        this.setLeaves(world, pos.add(x, y, z));
                    }
                }
            }
            if (y < topHeight - 1)
            {
                // add the trunk in the middle
                this.setLog(world, pos.add(0, y, 0));
            } else {
                // add leaves on top for certain
                this.setLeaves(world, pos.add(0, y, 0));
            }
        }
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos startPos)
    {
     
        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getBlock().isLeaves(world.getBlockState(startPos), world, startPos)) {startPos = startPos.down();}
        
        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 6) {return false;}
        int topHeight = 3;
        int heightMinusTop = height - topHeight;
        int numBranches = heightMinusTop / 5;
        int baseHeight = heightMinusTop - (numBranches * 2);
        
        // Start on the space above ground
        BlockPos pos = startPos.up();
        
        if (!this.checkSpace(world, pos, baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }
        
        // Generate bottom of tree (trunk only)
        for(int i = 0; i < baseHeight; i++)
        {
            this.setLog(world, pos);
            pos = pos.up();
        }
        
        // Generate middle of the tree - 2 steps at a time (trunk and leaves)
        for (int i = 0; i < numBranches; i++)
        {
            this.generateLeafLayer(world, random, pos);
            pos = pos.up(2);
        }
        
        // Generate the top of the tree
        this.generateTop(world, random, pos, topHeight);
        
        // Add vines
        this.addVines(world, random, startPos, baseHeight, height, 3, 10);

        // Add cocoa
        // this.addCocoa(world, random, startPos, baseHeight, 3);

        return true;
    }

    protected void addVines(World world, Random rand, BlockPos startPos, int baseHeight, int height, int leavesRadius, int generationAttempts)
    {
        if (this.vine == null) {return;}
        for (int i = 0; i < generationAttempts; i++)
        {
            // choose a random direction
            EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);
            EnumFacing back = direction.getOpposite();
            EnumFacing sideways = direction.rotateY();
            
            // choose a random starting point somewhere just outside the boundary of the tree leaves
            BlockPos pos = startPos.up(GeneratorUtils.nextIntBetween(rand, baseHeight + 1, height)).offset(direction, leavesRadius + 1).offset(sideways, GeneratorUtils.nextIntBetween(rand, -leavesRadius, leavesRadius));
            
            // move back towards the center until we meet a leaf, then stick a vine on it
            for (int l = 0; l < leavesRadius; l++)
            {
                if (world.getBlockState(pos.offset(back, 1 + l)) == this.leaves) {
                    this.setVine(world, rand, pos.offset(back, l), back, 4);
                    break;
                }
            }
        }
    }
    
    protected void addCocoa(World world, Random rand, BlockPos startPos, int baseHeight, int generationAttempts)
    {
        for (int i = 0; i < generationAttempts; i++)
        {
            // choose a random direction
            EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);
            EnumFacing back = direction.getOpposite();
            
            // choose a random point next to the trunk
            BlockPos pos = startPos.up(GeneratorUtils.nextIntBetween(rand, 1, baseHeight)).offset(direction, 1);
            
            // stick a cocoa pod on it
            this.setCocoa(world, pos, back);
        }
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("minHeight", this.maxHeight);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
        this.vine = conf.getBlockState("vinesState", this.vine);
    }
}