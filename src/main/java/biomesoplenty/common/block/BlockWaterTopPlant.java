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
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;

public class BlockWaterTopPlant extends BlockPlantBOP
{
    public BlockWaterTopPlant(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos)
    {
        IFluidState ifluidstate = worldIn.getFluidState(pos.down());
        Block ground = worldIn.getBlockState(pos.down(2)).getBlock();
        return (ifluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE) && (ground == Blocks.DIORITE || ground == Blocks.GRANITE || ground == Blocks.ANDESITE || ground == Blocks.STONE || ground == Blocks.DIRT || ground == Blocks.COARSE_DIRT || ground == Blocks.GRASS_BLOCK || ground == Blocks.GRAVEL || ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.white_sand || ground == BOPBlocks.mud || ground == BOPBlocks.dried_sand);
    }
}
