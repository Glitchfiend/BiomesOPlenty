/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockLeavesBOP extends BlockLeaves
{
    public BlockLeavesBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
    {
    	Block block = state.getBlock();
        
        if (block == BOPBlocks.origin_leaves)
        {
        	return BOPBlocks.origin_sapling;
        }
        if (block == BOPBlocks.flowering_oak_leaves)
        {
        	return BOPBlocks.flowering_oak_sapling;
        }
        if (block == BOPBlocks.yellow_autumn_leaves)
        {
        	return BOPBlocks.yellow_autumn_sapling;
        }
        if (block == BOPBlocks.orange_autumn_leaves)
        {
        	return BOPBlocks.orange_autumn_sapling;
        }
        if (block == BOPBlocks.maple_leaves)
        {
        	return BOPBlocks.maple_sapling;
        }
        if (block == BOPBlocks.fir_leaves)
        {
        	return BOPBlocks.fir_sapling;
        }
        if (block == BOPBlocks.redwood_leaves)
        {
        	return BOPBlocks.redwood_sapling;
        }
        if (block == BOPBlocks.pink_cherry_leaves)
        {
        	return BOPBlocks.pink_cherry_sapling;
        }
        if (block == BOPBlocks.white_cherry_leaves)
        {
        	return BOPBlocks.white_cherry_sapling;
        }
        if (block == BOPBlocks.mahogany_leaves)
        {
        	return BOPBlocks.mahogany_sapling;
        }
        if (block == BOPBlocks.jacaranda_leaves)
        {
        	return BOPBlocks.jacaranda_sapling;
        }
        if (block == BOPBlocks.palm_leaves)
        {
        	return BOPBlocks.palm_sapling;
        }
        if (block == BOPBlocks.willow_leaves)
        {
        	return BOPBlocks.willow_sapling;
        }
        if (block == BOPBlocks.dead_leaves)
        {
        	return BOPBlocks.dead_sapling;
        }
        if (block == BOPBlocks.magic_leaves)
        {
        	return BOPBlocks.magic_sapling;
        }
        if (block == BOPBlocks.umbran_leaves)
        {
        	return BOPBlocks.umbran_sapling;
        }
        if (block == BOPBlocks.hellbark_leaves)
        {
        	return BOPBlocks.hellbark_sapling;
        }
        if (block == BOPBlocks.ethereal_leaves)
        {
        	return BOPBlocks.ethereal_sapling;
        }
        
        return BOPBlocks.origin_sapling;
    }
    
    @Override
    public int getFlammability(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
    {
    	return Blocks.OAK_LEAVES.getFlammability(state, world, pos, face);
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
    {
        return Blocks.OAK_LEAVES.getFireSpreadSpeed(state,world, pos, face);
    }
}
