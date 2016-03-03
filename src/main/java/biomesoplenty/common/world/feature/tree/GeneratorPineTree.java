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
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorPineTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorPineTree> implements IGeneratorBuilder<GeneratorPineTree>
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
            this.hanging = hanging;
            this.altLeaves = null;
        }

        @Override
        public GeneratorPineTree create() {
            return new GeneratorPineTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight);
        }
        
    }
    
    public GeneratorPineTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
    }
    

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        
        // Move down until we reach the ground
        while (pos.getY() > 1 && world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isLeaves(world.getBlockState(pos), world, pos)) {pos = pos.down();}
        
        if (!this.placeOn.matches(world, pos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 6) {return false;}
        int heightMinusTop = height - 3;
        int numBranches = heightMinusTop / 3;
        int baseHeight = heightMinusTop - (numBranches * 2);
        
        // Move up to space above ground
        pos = pos.up();
        
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
        for(int i = 0; i < numBranches; i++)
        {
            this.generateLeafLayer(world, random, pos, i);
            pos = pos.up(2);
        }
        
        // Generate top of tree
        generateTop(world, pos);
        
        return true;
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

    // generate the top of the tree (3 blocks)
    public void generateTop(World world, BlockPos pos)
    {
        for(int x = -1; x <= 1; x++)
        {
            for(int z = -1; z <= 1; z++)
            {
                this.setLeaves(world, pos.add(x, 0, z));
            }
        }
        this.setLog(world, pos);
        
        pos = pos.up();
        this.setLeaves(world, pos);
        this.setLeaves(world, pos.north());
        this.setLeaves(world, pos.east());
        this.setLeaves(world, pos.south());
        this.setLeaves(world, pos.west());
        this.setLeaves(world, pos.up());
    }
    
    // generates a 'branch' of a leaf layer - extra leaves in the corner in the specified direction
    public void generateBranch(World world, BlockPos pos, EnumFacing direction, boolean dropY, boolean dropCorner)
    {
        EnumFacing sideways = direction.rotateY();
        if (dropY) {pos = pos.down();}
        this.setLeaves(world, pos.offset(direction, 1).offset(sideways, 1));
        this.setLeaves(world, pos.offset(direction, 2).offset(sideways, 1));
        this.setLeaves(world, pos.offset(direction, 1).offset(sideways, 2));
        this.setLeaves(world, pos.offset(direction, 2).offset(sideways, 2));
        if (dropCorner)
        {
            this.setLeaves(world, pos.offset(direction, 2).offset(sideways, 2).down());
        }
    }
    
    // generates a layer of leafs (2 blocks high)
    public void generateLeafLayer(World world, Random rand, BlockPos pos, int leafLayerNum)
    {
        // middle 3x3 are always leaves
        for(int x = -1; x <= 1; x++)
        {
            for(int z = -1; z <= 1; z++)
            {
                this.setLeaves(world, pos.add(x, 0, z));
            }
        }
        
        // sometimes add leaves further out - alternate layers at right angles to each other
        EnumFacing direction = (leafLayerNum % 2 == 0) ? EnumFacing.NORTH : EnumFacing.EAST;
        boolean dropY = (rand.nextInt(2) == 0);
        boolean dropCorner = (rand.nextInt(2) == 0);
        
        if (rand.nextInt(2) == 0)
        {
            this.generateBranch(world, pos, direction, dropY, dropCorner);
        }
        if (rand.nextInt(2) == 0)
        {
            this.generateBranch(world, pos, direction.getOpposite(), dropY, dropCorner);
        }
        
        // add the trunk in the middle
        this.setLog(world, pos);
        this.setLog(world, pos.up());
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
    }
    
}