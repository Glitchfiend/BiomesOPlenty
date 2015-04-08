/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.biome;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorUtils
{
    public static Pair<Integer, Integer> validateMinMaxHeight(int minHeight, int maxHeight)
    {
        if (maxHeight < minHeight)
        {
            //Swap min and max height so that max is higher than min
            int prevMinHeight = minHeight;
            minHeight = maxHeight;
            maxHeight = prevMinHeight;
        }
        else if (maxHeight == minHeight)
        {
            if (minHeight < 255)
            {
                //Increase max height to be higher than min height
                ++maxHeight;
            }
            else
            {
                //Decrease min height so that max is higher
                --minHeight;
            }
        }
        
        return Pair.of(minHeight, maxHeight);
    }

    public static boolean isBlockTreeReplacable(Block block)
    {
        return block.getMaterial() == Material.air || block.getMaterial() == Material.leaves || block == Blocks.grass || block == Blocks.dirt || block == Blocks.log || block == Blocks.log2 || block == Blocks.sapling || block == Blocks.vine;
    }
    
    public static boolean canTreeReplace(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(world, pos) || state.getBlock().isLeaves(world, pos) || state.getBlock().isWood(world, pos) || isBlockTreeReplacable(state.getBlock());
    }
}
