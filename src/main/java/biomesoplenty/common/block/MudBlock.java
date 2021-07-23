/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.PlantType;

public class MudBlock extends Block
{
    public MudBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity)
    {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.5D, 1.0D, 0.5D));
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));

        if (type == PlantType.BEACH)
        {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState blockstate1 = world.getBlockState(pos.relative(direction));
                FluidState fluidstate = world.getFluidState(pos.relative(direction));
                if (fluidstate.is(FluidTags.WATER) || blockstate1.is(Blocks.FROSTED_ICE)) {
                    return true;
                }
            }
        }

        return false;
    }
}
