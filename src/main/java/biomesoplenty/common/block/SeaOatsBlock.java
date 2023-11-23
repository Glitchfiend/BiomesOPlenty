/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.common.PlantType;

public class SeaOatsBlock extends DoublePlantBlockBOP
{
    public SeaOatsBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return PlantType.DESERT;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldReader, BlockPos pos)
    {
        if (state.getBlock() != this) return super.canSurvive(state, worldReader, pos);
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER)
        {
            BlockState soil = worldReader.getBlockState(pos.below());
            if (soil.getBlock() == Blocks.SAND)
            {
                return true;
            }
        }
        else
        {
            BlockState below = worldReader.getBlockState(pos.below());
            return below.getBlock() == this && below.getValue(HALF) == DoubleBlockHalf.LOWER;
        }

        return false;
    }
}
