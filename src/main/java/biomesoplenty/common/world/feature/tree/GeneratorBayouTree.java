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
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBayouTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorBayouTree> implements IGeneratorBuilder<GeneratorBayouTree>
    {
        protected int minLeavesRadius;
        protected int leavesGradient;
        protected int vineAttempts;
        protected int maxVineLength;
        protected IBlockPosQuery rootsReplace;

        public Builder minLeavesRadius(int a) {this.minLeavesRadius = a; return this.self();}
        public Builder leavesGradient(int a) {this.leavesGradient = a; return this.self();}
        public Builder vineAttempts(int a) {this.vineAttempts = a; return this.self();}
        public Builder maxVineLength(int a) {this.maxVineLength = a; return this.self();}
        public Builder rootsReplace(IBlockPosQuery a) {this.replace = a; return this.self();}
        public Builder rootsReplace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this.self();}
        public Builder rootsReplace(Block a) {this.replace = new BlockQueryBlock(a); return this.self();}
        public Builder rootsReplace(IBlockState a) {this.replace = new BlockQueryState(a); return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.rootsReplace = BlockQueries.rootsCanDigThrough;
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = Blocks.vine.getDefaultState();
            this.hanging = null;
            this.altLeaves = null;
            this.minHeight = 8;
            this.maxHeight = 18;
            this.minLeavesRadius = 2;
            this.leavesGradient = 4;
            this.vineAttempts = 20;
            this.maxVineLength = 20;
        }

        @Override
        public GeneratorBayouTree create()
        {
            return new GeneratorBayouTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight, this.minLeavesRadius, this.leavesGradient, this.vineAttempts, this.maxVineLength, this.rootsReplace);
        }
    }
    

    private int minLeavesRadius;
    private int leavesGradient;
    private int vineAttempts;
    private int maxVineLength;
    private IBlockPosQuery rootsReplace;
    
    public GeneratorBayouTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight, int minLeavesRadius, int leavesGradient, int vineAttempts, int maxVineLength, IBlockPosQuery rootsReplace)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
        this.minLeavesRadius = minLeavesRadius;
        this.leavesGradient = leavesGradient;
        this.vineAttempts = vineAttempts;
        this.maxVineLength = maxVineLength;
        this.rootsReplace = rootsReplace;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at ground (sometimes underwater)
        return GeneratorUtils.ScatterYMethod.AT_GROUND.getBlockPos(world, random, x, z);
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
        if (this.vine != null)
        {
            int maxLeavesRadius = this.minLeavesRadius + topHeight / this.leavesGradient;
            this.addVines(world, random, startPos, height, maxLeavesRadius, this.vineAttempts);
        }

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