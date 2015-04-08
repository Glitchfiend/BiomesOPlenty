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
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.biome.generation.CustomizableWeightedGenerator;
import biomesoplenty.common.util.biome.GeneratorUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class GeneratorBasicTree extends CustomizableWeightedGenerator
{
    private boolean updateNeighbours;
    private int minHeight;
    private int maxHeight;
    private IBlockState log;
    private IBlockState leaves;
    private IBlockState vine;
    
    public GeneratorBasicTree() {}
    
    public GeneratorBasicTree(int weight, boolean updateNeighbours, int minHeight, int maxHeight, IBlockState log, IBlockState leaves, IBlockState vine)
    {
        super(weight);
        
        this.updateNeighbours = updateNeighbours;
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
        
        this.log = log;
        this.leaves = leaves;
        this.vine = vine;
    }
    
    public GeneratorBasicTree(int weight, boolean updateNeighbours, int minHeight, int maxHeight, IBlockState log, IBlockState leaves)
    {
        this(weight, updateNeighbours, minHeight, maxHeight, log, leaves, null);
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
                            if (!GeneratorUtils.canTreeReplace(world, new BlockPos(x, y, z)))
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
                boolean isSoil = soil.canSustainPlant(world, soilPos, EnumFacing.UP, (BlockSapling)Blocks.sapling);

                if (isSoil && pos.getY() < 256 - height - 1)
                {
                    soil.onPlantGrow(world, soilPos, pos);
                    int leavesLayers = 3;
                    
                    //Generates leaves at the top of the tree, going one block above the top log (<= rather than <)
                    for (int y = pos.getY() + height - leavesLayers; y <= pos.getY() + height; y++)
                    {
                        //Determines the distance from the top of the tree as a negative number
                        int currentLayer = y - (pos.getY() + height);
                        //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                        //the radius closer to the top of the tree. (2, 2, 1, 1)
                        int leavesRadius =  1 - currentLayer / 2;

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
                                    Block block = world.getBlockState(leavesPos).getBlock();

                                    if (block.isAir(world, leavesPos) || block.isLeaves(world, leavesPos) || block.getMaterial() == Material.vine)
                                    {
                                        this.setBlockAndNotifyAdequately(world, leavesPos, this.leaves);
                                    }
                                }
                            }
                        }
                    }

                    //Create the trunk from the bottom up, using < to ensure it is covered with one layer of leaves
                    for (int layer = 0; layer < height; ++layer)
                    {
                        BlockPos blockpos2 = pos.up(layer);
                        Block block2 = world.getBlockState(blockpos2).getBlock();

                        if (block2.isAir(world, blockpos2) || block2.isLeaves(world, blockpos2) || block2.getMaterial() == Material.vine)
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(layer), this.log);

                            //If vines are enabled, randomly cover the sides of the trunk with vines from the bottom up
                            if (this.vine != null && layer > 0)
                            {
                                if (random.nextInt(3) > 0 && world.isAirBlock(pos.add(-1, layer, 0)))
                                {
                                    this.setBlockAndNotifyAdequately(world, pos.add(-1, layer, 0), this.getVineStateForSide(EnumFacing.EAST));
                                }

                                if (random.nextInt(3) > 0 && world.isAirBlock(pos.add(1, layer, 0)))
                                {
                                    this.setBlockAndNotifyAdequately(world, pos.add(1, layer, 0), this.getVineStateForSide(EnumFacing.WEST));
                                }

                                if (random.nextInt(3) > 0 && world.isAirBlock(pos.add(0, layer, -1)))
                                {
                                    this.setBlockAndNotifyAdequately(world, pos.add(0, layer, -1), this.getVineStateForSide(EnumFacing.SOUTH));
                                }

                                if (random.nextInt(3) > 0 && world.isAirBlock(pos.add(0, layer, 1)))
                                {
                                    this.setBlockAndNotifyAdequately(world, pos.add(0, layer, 1), this.getVineStateForSide(EnumFacing.NORTH));
                                }
                            }
                        }
                    }

                    if (this.vine != null)
                    {
                        for (int y = pos.getY() - leavesLayers + height; y <= pos.getY() + height; y++)
                        {
                            //Determines the distance from the top of the tree as a negative number
                            int currentLayer = y - (pos.getY() + height);
                            //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                            //the radius closer to the top of the tree. (3, 3, 2, 2)
                            int leavesRadius = 2 - currentLayer / 2;

                            for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                            {
                                for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; z++)
                                {
                                    BlockPos blockpos3 = new BlockPos(x, y, z);

                                    //Surround leaves on the edge of the tree with vines and extend them downwards
                                    if (world.getBlockState(blockpos3).getBlock().isLeaves(world, blockpos3))
                                    {
                                        BlockPos westPos = blockpos3.west();
                                        BlockPos eastPos = blockpos3.east();
                                        BlockPos northPos = blockpos3.north();
                                        BlockPos southPos = blockpos3.south();

                                        if (random.nextInt(4) == 0 && world.getBlockState(westPos).getBlock().isAir(world, westPos))
                                        {
                                            this.extendVines(world, westPos, EnumFacing.EAST);
                                        }

                                        if (random.nextInt(4) == 0 && world.getBlockState(eastPos).getBlock().isAir(world, eastPos))
                                        {
                                            this.extendVines(world, eastPos, EnumFacing.WEST);
                                        }

                                        if (random.nextInt(4) == 0 && world.getBlockState(northPos).getBlock().isAir(world, northPos))
                                        {
                                            this.extendVines(world, northPos, EnumFacing.SOUTH);
                                        }

                                        if (random.nextInt(4) == 0 && world.getBlockState(southPos).getBlock().isAir(world, southPos))
                                        {
                                            this.extendVines(world, southPos, EnumFacing.NORTH);
                                        }
                                    }
                                }
                            }
                        }
                    }

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
    
    private IBlockState getVineStateForSide(EnumFacing side)
    {
        return this.vine.withProperty(BlockVine.getPropertyFor(side), Boolean.valueOf(true));
    }

    private void extendVines(World world, BlockPos pos, EnumFacing side)
    {
        IBlockState vineState = this.getVineStateForSide(side);
        this.setBlockAndNotifyAdequately(world, pos, vineState);
        
        int length = 4;

        //Extend vine downwards for a maximum of 4 blocks
        for (pos = pos.down(); world.getBlockState(pos).getBlock().isAir(world, pos) && length > 0; length--)
        {
            this.setBlockAndNotifyAdequately(world, pos, vineState);
            pos = pos.down();
        }
    }
    
    private void setBlockAndNotifyAdequately(World world, BlockPos pos, IBlockState state)
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
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        json.addProperty("weight", this.itemWeight);
        json.addProperty("update_neighbours", this.updateNeighbours);
        json.addProperty("min_height", this.minHeight);
        json.addProperty("max_height", this.maxHeight);
        json.add("log_state", context.serialize(this.log));
        json.add("leaves_state", context.serialize(this.leaves));
        json.add("vine_state", context.serialize(this.vine));
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        this.itemWeight = json.get("weight").getAsInt();
        this.updateNeighbours = json.get("update_neighbours").getAsBoolean();
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
        
        this.log = context.deserialize(json.get("log_state"), IBlockState.class);
        this.leaves = context.deserialize(json.get("leaves_state"), IBlockState.class);
        this.vine = context.deserialize(json.get("vine_state"), IBlockState.class);
    }
}
