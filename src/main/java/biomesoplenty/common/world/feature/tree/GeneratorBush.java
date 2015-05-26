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
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorBush extends GeneratorCustomizable
{
    private int amountPerChunk;
    private IBlockState log;
    private IBlockState leaves;
    
    public GeneratorBush()
    {
        // default
        this(1, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
    }
    
    public GeneratorBush(int amountPerChunk, IBlockState log, IBlockState leaves)
    {
        this.amountPerChunk = amountPerChunk;
        this.log = log;
        this.leaves = leaves;
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            BlockPos genPos = world.getHeight(pos.add(x, 0, z));
            
            generate(world, random, genPos);
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block;

        //Move down until we reach the ground
        do
        {
            block = world.getBlockState(pos).getBlock();
            if (!block.isAir(world, pos) && !block.isLeaves(world, pos)) break;
            pos = pos.down();
        } 
        while (pos.getY() > 0);

        //Check if the ground block can sustain a sapling before generating above it
        if (block.canSustainPlant(world, pos, EnumFacing.UP, ((BlockSapling)Blocks.sapling)))
        {
            pos = pos.up();
            world.setBlockState(pos, this.log, 2);

            //Generate a bush 3 blocks tall, with the bottom block already set to a log
            for (int y = pos.getY(); y <= pos.getY() + 2; ++y)
            {
                //Determines the distance away from the bottom of the bush
                int currentLayer = y - pos.getY();
                //Reduces the radius closer to the top of the bush
                int leavesRadius = 2 - currentLayer;

                for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; ++x)
                {
                    int xDiff = x - pos.getX();

                    for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; ++z)
                    {
                        int zDiff = z - pos.getZ();

                        //Randomly prevent the generation of leaves on the corners of each layer
                        if (Math.abs(xDiff) != leavesRadius || Math.abs(zDiff) != leavesRadius || random.nextInt(2) != 0)
                        {
                            BlockPos leavesPos = new BlockPos(x, y, z);

                            //Ensures the leaves can replace surrounding blocks, preventing the existing log from being overriden alongside
                            //other terrain
                            if (world.getBlockState(leavesPos).getBlock().canBeReplacedByLeaves(world, leavesPos))
                            {
                                world.setBlockState(leavesPos, this.leaves, 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);        
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
    }
    
}
