/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMushroomBOP extends MushroomBlock
{
    public BlockMushroomBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
    	BlockPos blockpos = pos.down();
    	BlockState BlockState = worldIn.getBlockState(blockpos);
    	return BlockState.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
    }

    @Override
    public boolean generateBigMushroom(IWorld worldIn, BlockPos pos, BlockState state, Random rand)
    {
    	return false;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
    	return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return false;
    }
}
