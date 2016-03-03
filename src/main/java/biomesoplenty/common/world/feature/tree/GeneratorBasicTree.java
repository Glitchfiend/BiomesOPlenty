/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBasicTree extends GeneratorTreeBase
{
    // TODO: update neighbours in builder?
    public static class Builder extends GeneratorBasicTree.InnerBuilder<Builder, GeneratorBasicTree> implements IGeneratorBuilder<GeneratorBasicTree>
    {        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = new BlockQueryMaterial(Material.air, Material.leaves);
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = null;
            this.hanging = null;
            this.altLeaves = null;
            this.minHeight = 4;
            this.maxHeight = 7;
            this.leafLayers = 4;
            this.leavesOffset = 1;
            this.maxLeavesRadius = 1;
            this.leavesLayerHeight = 2;
            this.placeVinesOn = BlockQueries.air;
            this.hangingChance = 0.0F;
        }

        @Override
        public GeneratorBasicTree create()
        {
            return new GeneratorBasicTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight, false, this.leafLayers, this.leavesOffset, this.maxLeavesRadius, this.leavesLayerHeight, this.placeVinesOn, this.hangingChance);
        }
    }
    
    protected static abstract class InnerBuilder<T extends InnerBuilder<T, G>, G extends GeneratorBasicTree> extends GeneratorTreeBase.InnerBuilder<T, G>
    {
        protected int leafLayers;
        protected int leavesOffset;
        protected int maxLeavesRadius;
        protected int leavesLayerHeight;
        protected IBlockPosQuery placeVinesOn;
        protected float hangingChance;
        
        public T leafLayers(int a) {this.leafLayers = a; return this.self();}
        public T leavesOffset(int a) {this.leavesOffset = a; return this.self();}
        public T leavesLayerHeight(int a) {this.leavesLayerHeight = a; return this.self();}
        public T maxLeavesRadius(int a) {this.maxLeavesRadius = a; return this.self();}
        
        public T placeVinesOn(IBlockPosQuery a) {this.placeVinesOn = a; return this.self();}
        public T placeVinesOn(String a) throws BlockQueryParseException {this.placeVinesOn = BlockQuery.parseQueryString(a); return this.self();}
        public T placeVinesOn(Block a) {this.placeVinesOn = new BlockQueryBlock(a); return this.self();}
        public T placeVinesOn(IBlockState a) {this.placeVinesOn = new BlockQueryState(a); return this.self();}
        public T placeVinesOn(Material... a) {this.placeVinesOn = new BlockQueryMaterial(a); return this.self();}
        
        public T hangingChance(float a) {this.hangingChance = a; return this.self();}
    }
    
    protected boolean updateNeighbours;
    protected int leafLayers;
    protected int leavesOffset;
    protected int maxLeavesRadius;
    protected int leavesLayerHeight;
    protected IBlockPosQuery placeVinesOn;
    protected float hangingChance;
    
    public GeneratorBasicTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight, boolean updateNeighbours, int leafLayers, int leavesOffset, int maxLeavesRadius, int leavesLayerHeight, IBlockPosQuery placeVinesOn, float hangingChance)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
        this.updateNeighbours = updateNeighbours;
        this.leavesOffset = leavesOffset;
        this.leafLayers = leafLayers;
        this.maxLeavesRadius = maxLeavesRadius;
        this.leavesLayerHeight = leavesLayerHeight;
        this.placeVinesOn = placeVinesOn;
        this.hangingChance = hangingChance;
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        int height = random.nextInt(this.maxHeight - this.minHeight) + this.minHeight;
        boolean hasSpace = true;

        //Generate only if we are above the lowest bedrock level (1) and reach less than the world height
        //There must be a gap of 1 between the top leaf block and the world height
        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256)
        {
            int radius;

            for (int y = pos.getY(); y <= pos.getY() + 1 + height; y++)
            {
                radius = 1;

                //Don't check for space on the first level, if we are a sapling then there will
                //already be a block here (the sapling itself)
                if (y == pos.getY())
                {
                    radius = 0;
                }

                //At and above the top log block, require a radius of 2 to be empty
                if (y >= pos.getY() + 1 + height - 2)
                {
                    radius = 2;
                }

                for (int x = pos.getX() - radius; x <= pos.getX() + radius && hasSpace; ++x)
                {
                    for (int z = pos.getZ() - radius; z <= pos.getZ() + radius && hasSpace; ++z)
                    {
                        if (y >= 0 && y < 256)
                        {
                            if (!this.replace.matches(world, new BlockPos(x, y, z)))
                            {
                                hasSpace = false;
                            }
                        }
                        else
                        {
                            hasSpace = false;
                        }
                    }
                }
            }

            if (!hasSpace)
            {
                return false;
            }
            else
            {
                BlockPos soilPos = pos.down();
                Block soil = world.getBlockState(soilPos).getBlock();
                boolean isSoil = soil.canSustainPlant(world.getBlockState(soilPos), world, soilPos, EnumFacing.UP, (BlockSapling)Blocks.sapling);

                if (this.placeOn.matches(world, soilPos) && isSoil && pos.getY() < 256 - height - 1)
                {
                    soil.onPlantGrow(world.getBlockState(soilPos), world, soilPos, pos);
                    int leavesLayers = (this.leafLayers - 1);
                    
                    //Generates leaves at the top of the tree, going one block above the top log (<= rather than <)
                    for (int y = pos.getY() + height - leavesLayers; y <= pos.getY() + height; y++)
                    {
                        //Determines the distance from the top of the tree as a negative number
                        int currentLayer = y - (pos.getY() + height);
                        //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                        //the radius closer to the top of the tree. (2, 2, 1, 1)
                        int leavesRadius =  this.maxLeavesRadius - currentLayer / this.leavesLayerHeight;

                        for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                        {
                            int xDiff = x - pos.getX();

                            for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; ++z)
                            {
                                int zDiff = z - pos.getZ();

                                //Randomly prevent the generation of leaves on the corners of each layer
                                //If the layer is the top layer, never generate the corners
                                if (Math.abs(xDiff) != leavesRadius || Math.abs(zDiff) != leavesRadius || random.nextInt(2) != 0 && currentLayer != 0)
                                {
                                    BlockPos leavesPos = new BlockPos(x, y, z);
                                    if (this.replace.matches(world, leavesPos))
                                    {
                                    	if (this.altLeaves != null)
                                    	{
                                    		if (random.nextInt(4) == 0)
                                    		{
                                    			this.setBlockAndNotifyAdequately(world, leavesPos, this.altLeaves);
                                    		}
                                    		else
                                    		{
                                    			this.setBlockAndNotifyAdequately(world, leavesPos, this.leaves);
                                    		}
                                    	}
                                    	else
                                    	{
                                    		this.setBlockAndNotifyAdequately(world, leavesPos, this.leaves);
                                    	}
                                    }
                                }
                            }
                        }
                    }
                    
                    this.generateTrunk(world, pos, height);

                    if (this.vine != null)
                    {
                        for (int y = pos.getY() - leavesLayers + height; y <= pos.getY() + height; y++)
                        {
                            //Determines the distance from the top of the tree as a negative number
                            int currentLayer = y - (pos.getY() + height);
                            //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                            //the radius closer to the top of the tree. (3, 3, 2, 2)
                            int leavesRadius = (this.maxLeavesRadius + this.leavesOffset) - currentLayer / this.leavesLayerHeight;

                            for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                            {
                                for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; z++)
                                {
                                    BlockPos blockpos3 = new BlockPos(x, y, z);

                                    //Surround leaves on the edge of the tree with vines and extend them downwards
                                    if (world.getBlockState(blockpos3).getBlock().isLeaves(world.getBlockState(blockpos3), world, blockpos3))
                                    {
                                        BlockPos westPos = blockpos3.west();
                                        BlockPos eastPos = blockpos3.east();
                                        BlockPos northPos = blockpos3.north();
                                        BlockPos southPos = blockpos3.south();

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, westPos))
                                        {
                                            this.extendVines(world, westPos, EnumFacing.EAST);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, eastPos))
                                        {
                                            this.extendVines(world, eastPos, EnumFacing.WEST);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, northPos))
                                        {
                                            this.extendVines(world, northPos, EnumFacing.SOUTH);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, southPos))
                                        {
                                            this.extendVines(world, southPos, EnumFacing.NORTH);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    //Generate fruit or any other blocks that may hang off of the tree
                    if (this.hanging != null) this.generateHanging(world, pos, height);

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
    
    protected void generateTrunk(World world, BlockPos start, int height)
    {
        //Create the trunk from the bottom up, using < to ensure it is covered with one layer of leaves
        for (int layer = 0; layer < height; ++layer)
        {
            BlockPos blockpos2 = start.up(layer);
            if (this.replace.matches(world, blockpos2))
            {
                this.setBlockAndNotifyAdequately(world, start.up(layer), this.log);
            }
        }
    }
    
    protected void generateHanging(World world, BlockPos start, int height)
    {
        //Generate below the bottom layer of leaves
        int y = start.getY() + (height - this.leafLayers);
        
        for (int x = start.getX() - (maxLeavesRadius + 1); x <= start.getX() + (maxLeavesRadius + 1); x++)
        {
            for (int z = start.getZ() - (maxLeavesRadius + 1); z <= start.getZ() + (maxLeavesRadius + 1); z++)
            {
                BlockPos hangingPos = new BlockPos(x, y, z);
                
                if (!world.isAirBlock(hangingPos.up()) && (world.isAirBlock(hangingPos)) && world.rand.nextFloat() <= this.hangingChance)
                {
                    this.setHanging(world, hangingPos);
                }
            }
        }
    }
    
    private IBlockState getVineStateForSide(EnumFacing side)
    {
        return this.vine.getBlock() instanceof BlockVine ? this.vine.withProperty(BlockVine.getPropertyFor(side), Boolean.valueOf(true)) : this.vine;
    }

    private void extendVines(World world, BlockPos pos, EnumFacing side)
    {
        IBlockState vineState = this.getVineStateForSide(side);
        this.setBlockAndNotifyAdequately(world, pos, vineState);
        
        int length = 4;

        //Extend vine downwards for a maximum of 4 blocks
        for (pos = pos.down(); this.placeVinesOn.matches(world, pos) && length > 0; length--)
        {
            this.setBlockAndNotifyAdequately(world, pos, vineState);
            pos = pos.down();
        }
    }
    
    public void setBlockAndNotifyAdequately(World world, BlockPos pos, IBlockState state)
    {
        if (this.updateNeighbours)
        {
            world.setBlockState(pos, state, 3);
        }
        else
        {
            world.setBlockState(pos, state, 2);
        }
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.updateNeighbours = conf.getBool("updateNeighbours", this.updateNeighbours);
        int minHeight = conf.getInt("minHeight", this.minHeight);
        int maxHeight = conf.getInt("maxHeight", this.maxHeight);
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
        
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
        this.vine = conf.getBlockState("vineState", this.vine);
        this.hanging = conf.getBlockState("hangingState", this.hanging);
        this.altLeaves = conf.getBlockState("altLeavesState", this.altLeaves);
        
        this.hangingChance = conf.getFloat("hangingChance", this.hangingChance);
    }
}
