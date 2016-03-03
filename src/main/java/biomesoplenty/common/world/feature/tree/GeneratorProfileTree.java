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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorProfileTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorProfileTree> implements IGeneratorBuilder<GeneratorProfileTree>
    {
        protected TreeProfile profile;
        
        public Builder profile(TreeProfile a) {this.profile = a; return this.self();}
        
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
            this.profile = TreeProfile.POPLAR;
        }

        @Override
        public GeneratorProfileTree create() {
            return new GeneratorProfileTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight, this.profile);
        }
        
    }
    
    protected TreeProfile profile;
    
    public GeneratorProfileTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight, TreeProfile profile)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
        this.profile = profile;
    }
    
    public static enum TreeProfile
    {
        POPLAR;
        
        public int radius(int height, int maxHeight)
        {
            switch (this)
            {
                case POPLAR: default:
                    float x = (float)height / (float)maxHeight;
                    float maxRadius = 1.0F + maxHeight * 0.10F;
                    // this function creates a curved profile which has its widest point 1/4 from the bottom and a pointy top
                    float r = maxRadius * 0.6667F * x * (1/(x*x + 0.08173F) - 0.9244F);
                    return (int)(r + 0.5F);
            }
        }
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
        
        // Choose heights and width
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 4) {return false;}
        int baseHeight = height / (2 + random.nextInt(3));
        int leavesHeight = height - baseHeight;
        
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
        
        // Generate middle of the tree
        for(int i = 0; i < leavesHeight; i++)
        {
            int radius = this.profile.radius(i, leavesHeight);
            this.generateLeafLayer(world, pos, radius);
            if (leavesHeight - i > 2) {this.setLog(world, pos);}
            pos = pos.up();
        }
        
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
    
    // generates a layer of leafs with the given radius
    public void generateLeafLayer(World world, BlockPos pos, int radius)
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
        this.profile = conf.getEnum("profile", this.profile, TreeProfile.class);
    }   
}