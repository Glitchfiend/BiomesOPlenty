/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;
import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockPosQueryOr;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GeneratorBulbTree extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorBulbTree>
    {
        protected float amountPerChunk = 1.0F;
        protected int minHeight = 6;
        protected int maxHeight = 12;
        protected IBlockPosQuery placeOn = new BlockPosQueryOr(new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass));
        protected IBlockPosQuery replace = new BlockPosQueryOr(new BlockQueryMaterial(Material.air), new BlockQueryMaterial(Material.leaves), new BlockQueryMaterial(Material.plants));
        protected IBlockState log = Blocks.log.getDefaultState();
        protected IBlockState leaves = Blocks.leaves.getDefaultState();
        protected IBlockState vine = Blocks.vine.getDefaultState();
        
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
        public Builder vine(IBlockState a)
        {
            if (a.getBlock() instanceof BlockVine)
            {
                this.vine = a;
            } else {
                throw new IllegalArgumentException("vine must use a BlockVine block");
            }
            return this;
        }


        @Override
        public GeneratorBulbTree create()
        {
            return new GeneratorBulbTree(this.amountPerChunk, this.minHeight, this.maxHeight, this.placeOn, this.replace, this.log, this.leaves, this.vine);
        }
    }
    

    private int minHeight;
    private int maxHeight;
    private IBlockPosQuery placeOn;
    private IBlockPosQuery replace;
    private IBlockState log;
    private IBlockState leaves;
    private IBlockState vine;
    
    public GeneratorBulbTree(float amountPerChunk, int minHeight, int maxHeight, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine)
    {
        super(amountPerChunk);
        
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.placeOn = placeOn;
        this.replace = replace;
        this.log = log;
        this.leaves = leaves;
        this.vine = vine;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
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
    
    public boolean setVine(World world, BlockPos pos, EnumFacing side, int length)
    {
        IBlockState vineState = this.vine.withProperty(BlockVine.NORTH, Boolean.valueOf(side == EnumFacing.NORTH)).withProperty(BlockVine.EAST, Boolean.valueOf(side == EnumFacing.EAST)).withProperty(BlockVine.SOUTH, Boolean.valueOf(side == EnumFacing.SOUTH)).withProperty(BlockVine.WEST, Boolean.valueOf(side == EnumFacing.WEST));
        boolean setOne = false;
        while (this.replace.matches(world, pos) && length > 0)
        {
            world.setBlockState(pos, vineState, 2);
            setOne = true;
            length--;
            pos = pos.down();
        }
        return setOne;
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
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getBlock().isLeaves(world, startPos)) {startPos = startPos.down();}
        
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
        int numBranches = heightMinusTop / 3;
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
                    this.setVine(world, pos.offset(back, l), back, 4);
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