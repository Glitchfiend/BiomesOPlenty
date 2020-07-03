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
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class PlantBlockBOP extends BushBlock implements IPlantable
{
	protected static final VoxelShape NORMAL = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected static final VoxelShape ROOT = Block.box(2.0D, 3.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	
    public PlantBlockBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.root)
        {
            return ROOT;
        }

        return NORMAL;
    }
    
    @Override
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XZ;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block ceiling = worldIn.getBlockState(pos.above()).getBlock();

        if (this == BOPBlocks.root)
        {
            return ceiling == Blocks.DIRT || ceiling == Blocks.GRASS_BLOCK || ceiling == Blocks.PODZOL || ceiling == Blocks.GRASS_PATH || ceiling == Blocks.MYCELIUM || ceiling == Blocks.FARMLAND || ceiling == Blocks.COARSE_DIRT || ceiling == Blocks.NETHERRACK;
        }

        return super.canSurvive(state, worldIn, pos);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
    	return PlantType.PLAINS;
    }
}
