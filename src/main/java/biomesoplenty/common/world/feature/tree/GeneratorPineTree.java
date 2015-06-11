/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;


import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.*;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorPineTree extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorPineTree>
    {
        protected float amountPerChunk = 1.0F;
        protected int minHeight = 6;
        protected int maxHeight = 12;
        protected IBlockPosQuery placeOn = new BlockPosQueryOr(new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass));
        protected IBlockPosQuery replace = new BlockPosQueryOr(new BlockQueryMaterial(Material.air), new BlockQueryMaterial(Material.leaves), new BlockQueryMaterial(Material.plants));
        protected IBlockState log = BlockBOPLog.paging.getVariantState(BOPWoods.PINE);
        protected IBlockState leaves = BlockBOPLeaves.paging.getVariantState(BOPTrees.PINE);
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder minHeight(int a) {this.minHeight = a; return this;}
        public Builder maxHeight(int a) {this.maxHeight = a; return this;}
        public Builder placeOn(IBlockPosQuery a) {this.placeOn = a; return this;}
        public Builder placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this;}
        public Builder placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this;}
        public Builder placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this;}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this;}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}
        public Builder log(IBlockState a) {this.log = a; return this;}
        public Builder log(BOPWoods a) {this.log = BlockBOPLog.paging.getVariantState(a); return this;}
        public Builder log(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.log = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, a);
            } else {
                this.log = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, a);
            }
            return this;
        }
        public Builder leaves(IBlockState a) {this.leaves = a; return this;}
        public Builder leaves(BOPTrees a) {this.leaves = BlockBOPLeaves.paging.getVariantState(a); return this;}
        public Builder leaves(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.leaves = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                this.leaves = Blocks.leaves2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, a);
            }
            return this;
        }

        @Override
        public GeneratorPineTree create()
        {
            return new GeneratorPineTree(this.amountPerChunk, this.minHeight, this.maxHeight, this.placeOn, this.replace, this.log, this.leaves);
        }
    }
    
    

    private int minHeight;
    private int maxHeight;
    private IBlockPosQuery placeOn;
    private IBlockPosQuery replace;
    private IBlockState log;
    private IBlockState leaves;
    
    public GeneratorPineTree(float amountPerChunk, int minHeight, int maxHeight, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves)
    {
        super(amountPerChunk);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.placeOn = placeOn;
        this.replace = replace;
        this.log = log;
        this.leaves = leaves;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        
        // Move down until we reach the ground
        while (pos.getY() > 1 && world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isLeaves(world, pos)) {pos = pos.down();}
        
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
    
    public boolean setLeaves(World world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.leaves, 2);
            return true;
        }
        return false;
    }
    
    public boolean setLog(World world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.log, 2);
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