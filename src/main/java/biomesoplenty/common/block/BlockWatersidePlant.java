/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;

import java.util.Iterator;

public class BlockWatersidePlant extends BlockPlantBOP
{
    public BlockWatersidePlant(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldReader, BlockPos pos)
    {
        IBlockState soil = worldReader.getBlockState(pos.down());
        if (soil == this)
            return true;
        else if (soil.canSustainPlant(worldReader, pos.down(), EnumFacing.UP, this))
        {
            BlockPos blockpos = pos.down();
            Iterator var7 = EnumFacing.Plane.HORIZONTAL.iterator();

            IBlockState iblockstate;
            IFluidState ifluidstate;
            do {
                if (!var7.hasNext()) {
                    return false;
                }

                EnumFacing enumfacing = (EnumFacing)var7.next();
                iblockstate = worldReader.getBlockState(blockpos.offset(enumfacing));
                ifluidstate = worldReader.getFluidState(blockpos.offset(enumfacing));
            } while(!ifluidstate.isTagged(FluidTags.WATER) && iblockstate.getBlock() != Blocks.FROSTED_ICE);

            return true;
        }
        else
            return false;
    }
}
