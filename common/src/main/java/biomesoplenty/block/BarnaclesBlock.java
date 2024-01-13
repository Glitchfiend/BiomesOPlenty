/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BarnaclesBlock extends MultifaceBlock
{
    public static final MapCodec<BarnaclesBlock> CODEC = simpleCodec(BarnaclesBlock::new);

    public BarnaclesBlock(Properties p_153282_) {
        super(p_153282_);
    }

    @Override
    public MapCodec<BarnaclesBlock> codec()
    {
        return CODEC;
    }

    @Override
    public boolean canBeReplaced(BlockState p_153299_, BlockPlaceContext p_153300_) {
        return !p_153300_.getItemInHand().is(BOPBlocks.BARNACLES.asItem()) || super.canBeReplaced(p_153299_, p_153300_);
    }

    @Override
    public MultifaceSpreader getSpreader()
    {
        return new MultifaceSpreader(new MultifaceSpreader.DefaultSpreaderConfig(null)
        {
            @Nullable
            public BlockState getStateForPlacement(BlockState p_221694_, BlockGetter p_221695_, BlockPos p_221696_, Direction p_221697_)
            {
                return null;
            }

            @Override
            protected boolean stateCanBeReplaced(BlockGetter p_221688_, BlockPos p_221689_, BlockPos p_221690_, Direction p_221691_, BlockState p_221692_)
            {
                return false;
            }

            @Override
            public boolean canSpreadInto(BlockGetter p_221685_, BlockPos p_221686_, MultifaceSpreader.SpreadPos p_221687_)
            {
                return false;
            }
        });
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_181225_, BlockGetter p_181226_, BlockPos p_181227_) {
        return p_181225_.getFluidState().isEmpty();
    }
}