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
import net.minecraft.block.Blocks;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.PlantType;

import java.util.Iterator;

public class DoubleWatersidePlantBlock extends DoublePlantBlockBOP
{
    public DoubleWatersidePlantBlock(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
    	return PlantType.Beach;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldReader, BlockPos pos)
    {
        if (state.getBlock() != this) return super.isValidPosition(state, worldReader, pos);
        if (state.get(HALF) != DoubleBlockHalf.UPPER)
        {
            BlockState soil = worldReader.getBlockState(pos.down());
            if (soil.canSustainPlant(worldReader, pos.down(), Direction.UP, this))
            {
                BlockPos blockpos = pos.down();
                Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

                BlockState BlockState;
                IFluidState ifluidstate;
                do {
                    if (!var7.hasNext()) {
                        return false;
                    }

                    Direction Direction = (Direction)var7.next();
                    BlockState = worldReader.getBlockState(blockpos.offset(Direction));
                    ifluidstate = worldReader.getFluidState(blockpos.offset(Direction));
                } while(!ifluidstate.isTagged(FluidTags.WATER) && BlockState.getBlock() != Blocks.FROSTED_ICE);

                return true;
            }
        }
        else
        {
           BlockState BlockState = worldReader.getBlockState(pos.down());
           return BlockState.getBlock() == this && BlockState.get(HALF) == DoubleBlockHalf.LOWER;
        }
        
        return false;
    }
}
