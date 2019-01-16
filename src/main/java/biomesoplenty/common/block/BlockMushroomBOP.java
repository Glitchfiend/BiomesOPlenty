/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class BlockMushroomBOP extends BlockMushroom
{
    public BlockMushroomBOP(Block.Builder properties)
    {
        super(properties);
    }
    
    @Override
    public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
    {
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
    {
    	BlockPos blockpos = pos.down();
    	IBlockState iblockstate = worldIn.getBlockState(blockpos);
    	return iblockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.EnumFacing.UP, this);
    }

    @Override
    public boolean generateBigMushroom(IWorld worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	return false;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
    	return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }
}
