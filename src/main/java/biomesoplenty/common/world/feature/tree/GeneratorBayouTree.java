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
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GeneratorBayouTree extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorBayouTree>
    {
        protected float amountPerChunk = 1.0F;
        protected int minHeight = 8;
        protected int maxHeight = 18;
        protected int minLeavesRadius = 2;
        protected int leavesGradient = 4;
        protected int vineAttempts = 10;
        protected int maxVineLength = 20;
        protected IBlockPosQuery placeOn = BlockQueries.fertile;
        protected IBlockPosQuery replace = BlockQueries.airOrLeaves;
        protected IBlockPosQuery rootsReplace = BlockQueries.rootsCanDigThrough;
        protected IBlockState log = Blocks.log.getDefaultState();
        protected IBlockState leaves = Blocks.leaves.getDefaultState();
        protected IBlockState vine = Blocks.vine.getDefaultState();
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder minHeight(int a) {this.minHeight = a; return this;}
        public Builder maxHeight(int a) {this.maxHeight = a; return this;}
        public Builder minLeavesRadius(int a) {this.minLeavesRadius = a; return this;}
        public Builder leavesGradient(int a) {this.leavesGradient = a; return this;}
        public Builder vineAttempts(int a) {this.vineAttempts = a; return this;}
        public Builder maxVineLength(int a) {this.maxVineLength = a; return this;}
        public Builder placeOn(IBlockPosQuery a) {this.placeOn = a; return this;}
        public Builder placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this;}
        public Builder placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this;}
        public Builder placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this;}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this;}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}
        public Builder rootsReplace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder rootsReplace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this;}
        public Builder rootsReplace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder rootsReplace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}
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
        public GeneratorBayouTree create()
        {
            return new GeneratorBayouTree(this.amountPerChunk, this.minHeight, this.maxHeight, this.minLeavesRadius, this.leavesGradient, this.vineAttempts, this.maxVineLength, this.placeOn, this.replace, this.rootsReplace, this.log, this.leaves, this.vine);
        }
    }
    

    private int minHeight;
    private int maxHeight;
    private int minLeavesRadius;
    private int leavesGradient;
    private int vineAttempts;
    private int maxVineLength;
    private IBlockPosQuery placeOn;
    private IBlockPosQuery replace;
    private IBlockPosQuery rootsReplace;
    private IBlockState log;
    private IBlockState leaves;
    private IBlockState vine;
    
    public GeneratorBayouTree(float amountPerChunk, int minHeight, int maxHeight, int minLeavesRadius, int leavesGradient, int vineAttempts, int maxVineLength, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockPosQuery rootsReplace, IBlockState log, IBlockState leaves, IBlockState vine)
    {
        super(amountPerChunk);
        
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minLeavesRadius = minLeavesRadius;
        this.leavesGradient = leavesGradient;
        this.vineAttempts = vineAttempts;
        this.maxVineLength = maxVineLength;
        this.placeOn = placeOn;
        this.replace = replace;
        this.rootsReplace = rootsReplace;
        this.log = log;
        this.leaves = leaves;
        this.vine = vine;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_GROUND.getBlockPos(world, random, x, z);
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
    
    public boolean setRoot(World world, BlockPos pos)
    {
        if (this.rootsReplace.matches(world, pos))
        {
            world.setBlockState(pos, this.log, 2);
            return true;
        }
        return false;
    }
    
    public boolean setVine(World world, Random rand, BlockPos pos, EnumFacing side, int length)
    {
        IBlockState vineState = this.vine.withProperty(BlockVine.NORTH, Boolean.valueOf(side == EnumFacing.NORTH)).withProperty(BlockVine.EAST, Boolean.valueOf(side == EnumFacing.EAST)).withProperty(BlockVine.SOUTH, Boolean.valueOf(side == EnumFacing.SOUTH)).withProperty(BlockVine.WEST, Boolean.valueOf(side == EnumFacing.WEST));
        boolean setOne = false;
        while (this.replace.matches(world, pos) && length > 0 && rand.nextInt(12) > 0)
        {
            world.setBlockState(pos, vineState, 2);
            setOne = true;
            length--;
            pos = pos.down();
        }
        return setOne;
    }
    
    public boolean checkRootViable(World world, BlockPos pos, int rootHeight, EnumFacing direction)
    {
        // a viable root has an path which roots can dig through from trunk to some fertile ground
        pos = pos.offset(direction).up(rootHeight - 1);
        for (int i = 0; i < rootHeight; i++)
        {
            if (!this.rootsReplace.matches(world, pos)) {return false;}
            pos = pos.down();
            if (this.placeOn.matches(world, pos)) {return true;}
        }
        return false;
    }
    
    public boolean checkSpace(World world, BlockPos pos, int rootHeight, int middleHeight, int height)
    {
        
        // we want at least 2 of the roots to be viable
        int rootsOk = 0;
        for (EnumFacing direction : EnumFacing.Plane.HORIZONTAL)
        {
            if (this.checkRootViable(world, pos, rootHeight, direction)) {rootsOk++;}
        }
        if (rootsOk < 2) {return false;}
   
        // check there's some space for the trunk and leaves too
        for (int y = rootHeight; y <= height; y++)
        {
            // require 1x1 for the trunk, 3x3 for the leaves
            int radius = (y <= (rootHeight + middleHeight) ? 0 : 1);                        
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
    
    public void generateTop(World world, Random random, BlockPos pos, int topHeight)
    {           
        
        for (int y = 0; y < topHeight; y++)
        {
            int radius = Math.min(3, this.minLeavesRadius + (topHeight - y) / this.leavesGradient);
            
            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    // too far away and the leaves will decay
                    int dist = Math.abs(x) + Math.abs(z);
                    if (dist < 4 || (dist == 4 && random.nextInt(2) == 0))
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
    
    public void generateRoots(World world, Random random, BlockPos pos, int rootHeight)
    {
        for (int i = 0; i < rootHeight; i++)
        {
            this.setRoot(world, pos.north());
            this.setRoot(world, pos.east());
            this.setRoot(world, pos.south());
            this.setRoot(world, pos.west());
            pos = pos.up();
        }
        this.setRoot(world, pos.down());
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos startPos)
    {
                
        if (!this.placeOn.matches(world, startPos.down()))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        
        int topHeight = Math.min(6, GeneratorUtils.nextIntBetween(random, height / 5, height / 3));
        int rootHeight = Math.min(5, GeneratorUtils.nextIntBetween(random, height / 4, height / 2));
        int middleHeight = height - topHeight - rootHeight;
        if (middleHeight < 1) {return false;}
        
        // Start in the ground block
        BlockPos pos = startPos.down();
        
        if (!this.checkSpace(world, pos, rootHeight, middleHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }
        
        // Generate roots
        this.generateRoots(world, random, pos, rootHeight);
        pos = pos.up(rootHeight);
        
        // Generate middle of tree (trunk only)
        for(int i = 0; i < middleHeight; i++)
        {
            this.setLog(world, pos);
            pos = pos.up();
        }
        
        // Generate the top of the tree
        this.generateTop(world, random, pos, topHeight);
        
        // Add vines
        int maxLeavesRadius = this.minLeavesRadius + topHeight / this.leavesGradient;
        this.addVines(world, random, startPos, height, maxLeavesRadius, this.vineAttempts);

        return true;
    }

    protected void addVines(World world, Random rand, BlockPos startPos, int height, int leavesRadius, int generationAttempts)
    {
        for (int i = 0; i < generationAttempts; i++)
        {
            // choose a random direction
            EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);
            EnumFacing back = direction.getOpposite();
            EnumFacing sideways = direction.rotateY();
            
            // choose a random starting point somewhere just outside the boundary of the tree
            BlockPos pos = startPos.up(GeneratorUtils.nextIntBetween(rand, 2, height)).offset(direction, leavesRadius + 1).offset(sideways, GeneratorUtils.nextIntBetween(rand, -leavesRadius, leavesRadius));
            
            // move back towards the center until we meet a leaf or log, then stick a vine on it
            IBlockState state;
            for (int l = 0; l < leavesRadius; l++)
            {
                state = world.getBlockState(pos.offset(back, 1 + l));
                if (state == this.leaves || state == this.log) {
                    this.setVine(world, rand, pos.offset(back, l), back, this.maxVineLength);
                    break;
                }
            }
        }
    }
    
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.minLeavesRadius = conf.getInt("minLeavesRadius", this.minLeavesRadius);
        this.leavesGradient = conf.getInt("leavesGradient", this.leavesGradient);
        this.vineAttempts = conf.getInt("vineAttempts", this.vineAttempts);
        this.maxVineLength = conf.getInt("maxVineLength", this.maxVineLength);        
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.rootsReplace = conf.getBlockPosQuery("rootsReplace", this.rootsReplace);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
        this.vine = conf.getBlockState("vinesState", this.vine);
    }
}