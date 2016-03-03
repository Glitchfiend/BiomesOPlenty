/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class GeneratorHugeTree extends GeneratorTreeBase
{

    protected GeneratorHugeTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight) {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
    }

    protected int chooseHeight(Random rand)
    {
        return GeneratorUtils.nextIntBetween(rand, this.minHeight, this.maxHeight);
    }

    private boolean checkEnoughSpace(World worldIn, BlockPos pos, int height)
    {
        for (int y = 0; y <= 1 + height; ++y)
        {
            // require 3x3 for the first layer, 5x5 for the others
            int radius = ((y == 0) ? 1 : 2);

            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    BlockPos pos1 = pos.add(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.replace.matches(worldIn, pos1))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkSoil(BlockPos pos, World worldIn)
    {
        BlockPos soilPos = pos.down();
        
        if (!this.placeOn.matches(worldIn, soilPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        Block block = worldIn.getBlockState(soilPos).getBlock();
        this.onPlantGrow(worldIn, soilPos, pos);
        this.onPlantGrow(worldIn, soilPos.east(), pos);
        this.onPlantGrow(worldIn, soilPos.south(), pos);
        this.onPlantGrow(worldIn, soilPos.south().east(), pos);
        
        return true;

    }

    protected boolean canGrowHere(World worldIn, Random rand, BlockPos pos, int height)
    {
        return this.checkEnoughSpace(worldIn, pos, height) && this.checkSoil(pos, worldIn);
    }

    protected void addWideLeafLayer(World worldIn, BlockPos pos, int radius)
    {
        int rSquared = radius * radius;

        for (int x = -radius; x <= radius + 1; ++x)
        {
            for (int z = -radius; z <= radius + 1; ++z)
            {
                int x1East = x - 1;
                int z1North = z - 1;

                // skip corners
                if (x * x + z * z <= rSquared || x1East * x1East + z1North * z1North <= rSquared || x * x + z1North * z1North <= rSquared || x1East * x1East + z * z <= rSquared)
                {
                    this.setLeaves(worldIn, pos.add(x, 0, z));
                }
            }
        }
    }

    protected void addLeafLayer(World worldIn, BlockPos pos, int radius)
    {
        int rSquared = radius * radius;

        for (int x = -radius; x <= radius; ++x)
        {
            for (int z = -radius; z <= radius; ++z)
            {
                // skip corners
                if (x * x + z * z <= rSquared)
                {
                    this.setLeaves(worldIn, pos.add(x, 0, z));
                }
            }
        }
    }

    //Just a helper macro
    private void onPlantGrow(World world, BlockPos pos, BlockPos source)
    {
        world.getBlockState(pos).getBlock().onPlantGrow(world.getBlockState(pos), world, pos, source);
    }
}