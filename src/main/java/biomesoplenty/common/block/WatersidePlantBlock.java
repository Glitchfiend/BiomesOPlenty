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
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.PlantType;

import java.util.Iterator;

public class WatersidePlantBlock extends PlantBlockBOP
{
    public WatersidePlantBlock(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
    	Block block = world.getBlockState(pos).getBlock();
    	
    	return PlantType.BEACH;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldReader, BlockPos pos)
    {
        BlockState soil = worldReader.getBlockState(pos.below());
        if (soil.canSustainPlant(worldReader, pos.below(), Direction.UP, this))
        {
            BlockPos blockpos = pos.below();
            Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

            BlockState BlockState;
            FluidState ifluidstate;
            do {
                if (!var7.hasNext()) {
                    return false;
                }

                Direction Direction = (Direction)var7.next();
                BlockState = worldReader.getBlockState(blockpos.relative(Direction));
                ifluidstate = worldReader.getFluidState(blockpos.relative(Direction));
            } while(!ifluidstate.is(FluidTags.WATER) && BlockState.getBlock() != Blocks.FROSTED_ICE);

            return true;
        }
        else
            return false;
    }
}
