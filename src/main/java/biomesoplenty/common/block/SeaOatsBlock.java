/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
            if (soil.getBlock() == Blocks.SAND || soil.getBlock() == BOPBlocks.ROOTED_SAND.get())
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

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
    {
        for (int i = 1; i < 5; i++)
        {
            if (world.getBlockState(pos.below(i)).getBlock() != Blocks.SAND && world.getBlockState(pos.below(i)).getBlock() != BOPBlocks.ROOTED_SAND.get())
            {
                break;
            }

            if (world.getBlockState(pos.below(i)).getBlock() == BOPBlocks.ROOTED_SAND.get() && world.getBlockState(pos.below(i+1)).isAir())
            {
                if (rand.nextInt(10) == 0)
                {
                    world.setBlockAndUpdate(pos.below(i+1), Blocks.HANGING_ROOTS.defaultBlockState());
                }
            }

            if (world.getBlockState(pos.below(i)).getBlock() == Blocks.SAND)
            {
                if (rand.nextInt(10) == 0)
                {
                    world.setBlockAndUpdate(pos.below(i), BOPBlocks.ROOTED_SAND.get().defaultBlockState());
                }
                break;
            }
        }
    }
}
