/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.block;

import biomesoplenty.forge.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WebbingBlock extends MultifaceBlock
{
    public static final MapCodec<WebbingBlock> CODEC = simpleCodec(WebbingBlock::new);

    public WebbingBlock(BlockBehaviour.Properties p_153282_) {
        super(p_153282_);
    }

    @Override
    public MapCodec<WebbingBlock> codec()
    {
        return CODEC;
    }

    @Override
    public void entityInside(BlockState p_58180_, Level p_58181_, BlockPos p_58182_, Entity p_58183_)
    {
        p_58183_.setDeltaMovement(p_58183_.getDeltaMovement().multiply(0.625D, 0.75D, 0.625D));
    }

    @Override
    public boolean canBeReplaced(BlockState p_153299_, BlockPlaceContext p_153300_) {
        return !p_153300_.getItemInHand().is(BOPBlocks.WEBBING.get().asItem()) || super.canBeReplaced(p_153299_, p_153300_);
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