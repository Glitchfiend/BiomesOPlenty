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
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorBush extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorBush>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockState log = Blocks.log.getDefaultState();
        protected IBlockState leaves = Blocks.leaves.getDefaultState();
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder log(IBlockState a) {this.log = a; return this;}
        public Builder log(BOPWoods a) {this.log = BlockBOPLog.paging.getVariantState(a); return this;}
        public Builder leaves(IBlockState a) {this.leaves = a; return this;}
        public Builder leaves(BOPTrees a) {this.leaves = BlockBOPLeaves.paging.getVariantState(a); return this;}

        @Override
        public GeneratorBush create()
        {
            return new GeneratorBush(this.amountPerChunk, this.log, this.leaves);
        }
    }
    
    private IBlockState log;
    private IBlockState leaves;
    
    public GeneratorBush(float amountPerChunk, IBlockState log, IBlockState leaves)
    {
        super(amountPerChunk);
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
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);        
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
    }
    
}
